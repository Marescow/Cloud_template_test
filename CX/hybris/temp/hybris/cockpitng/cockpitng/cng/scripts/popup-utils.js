PopupUtils = function (domElement) {

};

PopupUtils.sendAnchorCoordinates = function (eventName, popupUUID, anchorUUID, verticalAlignment, horizontalAlignment) {
    var anchor = $('#' + anchorUUID);
    if (anchor.is(':visible')) {
        var coordinates = {};
        var popup = $('#' + popupUUID);
        var mainWindowHeight = $('body').innerHeight();
        var mainWindowWidth = $('body').innerWidth();
        var anchorOffset = anchor.offset();

        coordinates.vertical = {};
        var bottom = anchor.height();
        var top = -popup.height();
        if ("TOP" === verticalAlignment) {
            coordinates.vertical.inverted = anchorOffset.top - popup.height() < 0;
            if (coordinates.vertical.inverted) {
                coordinates.vertical.transition = bottom;
            }
            else {
                coordinates.vertical.transition = top;
            }
        } else if ("BOTTOM" === verticalAlignment) {
            coordinates.vertical.inverted = anchorOffset.top + anchor.height() + popup.height() > mainWindowHeight;
            if (coordinates.vertical.inverted) {
                coordinates.vertical.transition = top;
            }
            else {
                coordinates.vertical.transition = bottom;
            }
        }

        coordinates.horizontal = {};
        var left = 0;
        var right = anchor.width() - popup.width();
        if ("LEFT" === horizontalAlignment) {
            coordinates.horizontal.inverted = anchorOffset.left + popup.width() > mainWindowWidth;
            if (coordinates.horizontal.inverted) {
                coordinates.horizontal.transition = right;
            }
            else {
                coordinates.horizontal.transition = left;
            }
        }
        else if ("RIGHT" === horizontalAlignment) {
            coordinates.horizontal.inverted = anchorOffset.left + right < 0;
            if (coordinates.horizontal.inverted) {
                coordinates.horizontal.transition = left;
            }
            else {
                coordinates.horizontal.transition = right;
            }
        }

        CockpitNG.sendEvent('#' + popupUUID, eventName, coordinates);
    }
};

PopupUtils.adjustCompareViewValidationPopupPosition = function (popupUUID, anchorUUID) {
    var anchor = $('#' + anchorUUID);
    var popup = $('#' + popupUUID);
    var anchorOffset = anchor.offset();

    var bottom = anchor.height();
    var right = anchor.width() - popup.width();

    popup.css({
        "left": anchorOffset.left + right,
        "top": anchorOffset.top + bottom
    });

};
