package com.eightbit;

public class DataPoint extends Point {

    private int centroidIndex = 0;
    private int x = 0;
    private int y = 0;

    DataPoint(int r, int g, int b, int x, int y) {
        super(r, g, b);
        this.x = x;
        this.y = y;
    }

    public int getCentroidIndex() {
        return centroidIndex;
    }

    void setCentroidIndex(int centroidIndex) {
        this.centroidIndex = centroidIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return getRed() + " " + getGreen() + " " + getBlue() + " [" + centroidIndex + "]";
    }

}
