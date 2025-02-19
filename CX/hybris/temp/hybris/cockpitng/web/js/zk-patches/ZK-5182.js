/**
 * Purpose: Workaround Prevent XSS exploit on attributes
 * For version: 8.X to 10.X
 * Last update: 10.0.0
 */
zk.afterLoad('zk', function() {
    var xWidget = {};
    zk.override(zk.Widget.prototype, xWidget, {
        domTooltiptext_ : function() {
            var result = xWidget.domTooltiptext_.apply(this, arguments);
            return zUtl.encodeXMLAttribute(result);
        },
        domClass_ : function() {
            var result = xWidget.domClass_.apply(this, arguments);
            return zUtl.encodeXMLAttribute(result);
        }
    });//zk.override
});//zk.afterLoad
