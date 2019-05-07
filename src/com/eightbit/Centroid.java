package com.eightbit;

class Centroid extends Point {

    private int previous_red;
    private int previous_green;
    private int previous_blue;

    Centroid(int r, int g, int b) {
        super(r, g ,b);
    }

    void update(int r, int g, int b) {
        previous_red = getRed();
        previous_green = getGreen();
        previous_blue = getBlue();
        setRed(r);
        setGreen(g);
        setBlue(b);
    }

    boolean isStable() {
        return previous_red == getRed() &&
                previous_green == getGreen() &&
                previous_blue == getBlue();
    }
}
