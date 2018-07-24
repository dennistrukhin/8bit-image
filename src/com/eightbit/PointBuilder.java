package com.eightbit;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public class PointBuilder {

    private BufferedImage image;
    private int c_x;
    private int c_y;


    @NotNull
    public static PointBuilder create() {
        return new PointBuilder();
    }

    public PointBuilder fromImage(BufferedImage img) {
        image = img;
        return this;
    }

    public PointBuilder atCoordinates(int x, int y) {
        c_x = x;
        c_y = y;
        return this;
    }

    public Point build() {
        int clr =  image.getRGB(c_x, c_y);
        return new Point(
                (clr & 0x00ff0000) >> 16,
                (clr & 0x0000ff00) >> 8,
                clr & 0x000000ff
        );
    }

}
