var cropperStage;
var cropperPositions = {
    x: 0,
    y: 0,
    w: 0,
    h: 0
};

function getCurrentCropperPosition() {
    if (cropperStage) {
        var pos = cropperStage.active.pos;
        cropperPositions = {
            x: Math.round(pos.x),
            y: Math.round(pos.y),
            w: Math.round(pos.w),
            h: Math.round(pos.h)
        };
        return cropperPositions;
    } else {
        return null;
    }
}

function attachCropperToImage(id, squareLength) {
    if (!Jcrop) {
        return;
    }

    var image = document.getElementById(id);
    if (image) {
        if (cropperStage) {
            clearCropper();
        }

        cropperStage = Jcrop.attach(image, {
            aspectRatio: 1,
            multi: false
        });

        var rect = Jcrop.Rect.create(0, 0, squareLength, squareLength);
        cropperStage.newWidget(rect, {});

        cropperStage.listen("crop.change", (widget, e) => {
            cropperPositions = widget.pos;
        });
    }
}

function clearCropper() {
    if (cropperStage) {
        cropperStage.destroy();
    }
}
