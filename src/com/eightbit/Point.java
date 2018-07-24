package com.eightbit;

public class Point {

    private int red;
    private int green;
    private int blue;

    public Point(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    @Override
    public String toString() {
        return red + " " + green + " " + blue;
    }
}
