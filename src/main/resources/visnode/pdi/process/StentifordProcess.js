var STEP_1 = 1;
var STEP_2 = 2;
var STEP_3 = 3;
var STEP_4 = 4;

function process(image) {
    return processImage(image);
}

function calc(pixels, step, image) {
    var values = neighborhood(pixels, image);
    if (!isConnected(values)) {
        return pixels[1][1];
    }
    var n = pixels[1][0];
    var l = pixels[2][1];
    var s = pixels[1][2];
    var o = pixels[0][1];
    var no = pixels[0][0];
    if (step == STEP_1) {
        if (!(!isHigher(image, n) && isHigher(image, s))) {
            return pixels[1][1];
        }
    }
    if (step == STEP_2) {
        if (!(!isHigher(image, no) && isHigher(image, l))) {
            return pixels[1][1];
        }
    }
    if (step == STEP_3) {
        if (!(!isHigher(image, s) & isHigher(image, n))) {
            return pixels[1][1];
        }
    }
    if (step == STEP_4) {
        if (!(!isHigher(image, l) && isHigher(image, o))) {
            return pixels[1][1];
        }
    }
    return 0;
}

function processImage(image) {
    var change = true;
    var step = 0;
    var resultImage = ImageFactory.buildEmptyImage(image);
    var processImageResult = ImageFactory.buildImage(image);
    while (change) {
        change = false;
        step++;
        for (var x = 1; x < processImageResult.getWidth() - 1; x++) {
            for (var y = 1; y < processImageResult.getHeight() - 1; y++) {
                if (processImageResult.get(0, x, y) == image.getPixelValueRange().getHigher()) {
                    var values = pixels(x, y, processImageResult);
                    var v = Math.max(Math.min(calc(values, step, image), image.getPixelValueRange().getHigher()), 0);
                    if (v != processImageResult.get(0, x, y)) {
                        change = true;
                    }
                    resultImage.set(0, x, y, v);
                }
            }
        }
        processImageResult = ImageFactory.buildImage(resultImage);
        if (step == STEP_4) {
            step = 0;
        }
    }
    return processImageResult;
}


function isHigher(image, value) {
    return value == image.getPixelValueRange().getHigher();
}


function neighborhood(pixels, image) {
    var p2 = Math.floor(pixels[1][0] / image.getPixelValueRange().getHigher());
    var p3 = Math.floor(pixels[2][0] / image.getPixelValueRange().getHigher());
    var p4 = Math.floor(pixels[2][1] / image.getPixelValueRange().getHigher());
    var p5 = Math.floor(pixels[2][2] / image.getPixelValueRange().getHigher());
    var p6 = Math.floor(pixels[1][2] / image.getPixelValueRange().getHigher());
    var p7 = Math.floor(pixels[0][2] / image.getPixelValueRange().getHigher());
    var p8 = Math.floor(pixels[0][1] / image.getPixelValueRange().getHigher());
    var p9 = Math.floor(pixels[0][0] / image.getPixelValueRange().getHigher());
    return [p2, p3, p4, p5, p6, p7, p8, p9];
}

function isEdge(neighborhood) {
    var np = neighborhood[0] + neighborhood[1] + neighborhood[2]
            + neighborhood[3] + neighborhood[4] + neighborhood[5]
            + neighborhood[6] + neighborhood[7];
    return (np >= 2 && np <= 6) && isConnected(neighborhood);
}

function isConnected(neighborhood) {
    var sp = (neighborhood[0] < neighborhood[1] ? 1 : 0)
            + (neighborhood[1] < neighborhood[2] ? 1 : 0)
            + (neighborhood[2] < neighborhood[3] ? 1 : 0)
            + (neighborhood[3] < neighborhood[4] ? 1 : 0)
            + (neighborhood[4] < neighborhood[5] ? 1 : 0)
            + (neighborhood[5] < neighborhood[6] ? 1 : 0)
            + (neighborhood[6] < neighborhood[7] ? 1 : 0)
            + (neighborhood[7] < neighborhood[0] ? 1 : 0);
    return sp == 1;
}

function pixels(x, y, image) {
    var pixels = [];
    for (var x2 = 0; x2 < 3; x2++) {
        pixels.push([])
        for (var y2 = 0; y2 < 3; y2++) {
            pixels[x2][y2] = image.get(0, x + x2 - 1, y + y2 - 1);
        }
    }
    return pixels;
}