package com.eightbit;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

class DataPointBuilder {

    private DataPoint p;

    private DataPointBuilder() {
        p = new DataPoint(0, 0, 0, 0, 0);
    }

    @NotNull
    static DataPointBuilder create() {
        return new DataPointBuilder();
    }

    DataPointBuilder fromImagePixel(BufferedImage img, int x, int y) {
        int clr = img.getRGB(x, y);
        p.setRed((clr & 0x00ff0000) >> 16);
        p.setGreen((clr & 0x0000ff00) >> 8);
        p.setBlue(clr & 0x000000ff);
        p.setX(x);
        p.setY(y);
        return this;
    }

    DataPoint build() {
        return p;
    }

}
