package com.eightbit;

public class Point {

    private int red;
    private int green;
    private int blue;

    Point(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    int getRed() {
        return red;
    }

    int getGreen() {
        return green;
    }

    int getBlue() {
        return blue;
    }

    void setRed(int red) {
        this.red = red;
    }

    void setGreen(int green) {
        this.green = green;
    }

    void setBlue(int blue) {
        this.blue = blue;
    }

    void add(Point p) {
        red += p.getRed();
        green += p.getGreen();
        blue += p.getBlue();
    }

    void divideBy(int n) {
        red = Math.round((float)red / n);
        green = Math.round((float)green / n);
        blue = Math.round((float)blue / n);
    }

    @Override
    public String toString() {
        return red + " " + green + " " + blue;
    }
}
