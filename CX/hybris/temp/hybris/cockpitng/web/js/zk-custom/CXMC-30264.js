/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved
 */

/**
 * Customization for column min-width in mesh widget by adding column styles into table hdfaker colgroup.
 * ZK Ticket: https://tracker.zkoss.org/browse/ZK-5416
 */

zk.afterLoad('zul.sel', function () {
    var _xListbox = {};
    zk.override(zul.sel.Listbox.prototype, _xListbox, {
        domFaker_: function (out, fakeId) {
            // Transport percent value to int value
            function toPoint(percent) {
                var str = percent.replace("%", "");
                str = str / 100;
                return str;
            }

            // Workaround: calculate the percent width into certain px based on the width of parent container.
            function calculatePercentWidth(parent, percentWidth) {
                if (!parent || !percentWidth) {
                    return '';
                }
                const parentDom = parent.$n();
                if (parentDom) {
                    const point = toPoint(percentWidth);
                    if (point) {
                        const clientWidth = parentDom.clientWidth;
                        return clientWidth > 0 ? Math.floor(clientWidth * point) + 'px' : '';
                    }
                    return '';
                } else {
                    return calculatePercentWidth(parent.parent, percentWidth);
                }
            }

            //used by redraw
            const head = this.head;
            out.push('<colgroup id="', head.uuid, fakeId, '">');

            for (let w = head.firstChild; w; w = w.nextSibling) {
                let wd = this.$class._getWidth(w, w._hflexWidth ? w._hflexWidth + 'px' : w.getWidth());
                const visibility = w.isVisible() ? '' : 'visibility: collapse;'; // B70-ZK-2036: Style width should end with 'px'.

                // Customized part start
                /**
                 * 1. Calculate percent width based on container width;
                 * 2. Set min-width css style to columns acording to configured minWidth;
                 * 3. Once certain column is set fixed width in XML, min-width will be the minimal one from widget level minWidth and column level minWidth.
                 */
                if (wd) {
                    if (wd.indexOf('%') > 0) {
                        const calculatedWidth = calculatePercentWidth(this.parent, wd);
                        if (calculatedWidth) {
                            wd = calculatedWidth;
                            w.setWidth(calculatedWidth);
                        }
                    }
                    if (this.customizedMinWidth) {
                        const configuredWd = zk.parseInt(this.customizedMinWidth);
                        const styleWd = zk.parseInt(wd);
                        const minWidth = configuredWd > styleWd ? styleWd : configuredWd;
                        w.setStyle(w.getStyle() ? w.getStyle() : '' + "; min-width: " + minWidth + "px;");
                    }
                }
                wd = wd != null ? 'width: ' + wd + ';' : '';
                out.push('<col id="', w.uuid, fakeId, '" style="', wd, visibility, w.getStyle(), '"></col>');
                // Customized part end
            }
            if (fakeId.indexOf('hd') > 0 || fakeId.indexOf('ft') > 0) {
                out.push('<col id="', head.uuid, fakeId, '-bar" style="width: 0px" ></col>');
            }
            out.push('</colgroup>');
        },
    });
});
