/**
 * Fire an event when the image onLoad, in order to execute code after the an image has been loaded.
 */
zk.afterLoad('zul.wgt', function () { //specify zk widget package name
    var _xImage = {};
    zk.override(zul.wgt.Image.prototype, _xImage, { //specify zk full widget name
        setSrc: function (src) {
            _xImage.setSrc.apply(this, arguments); //call the original widget's overridden function
        },
        bind_: function (desktop, skipper, after) {
            _xImage.bind_.apply(this, arguments);
            var self = this;
            jq(this.getImageNode()).on("load", function (e) {
                self.fire("onImageLoad", e, { toServer: true });
            });
        },
        unbind_: function (skipper, after, keepRod) {
            jq(this.getImageNode()).off("load");
            _xImage.unbind_.apply(this, arguments);
        }
    });
});
