package com.eightbit;

public class Metric {

    public static double getDistance(Point p1, Point p2) {
        return Math.pow(p1.getRed() - p2.getRed(), 2)
                + Math.pow(p1.getRed() - p2.getRed(), 2)
                + Math.pow(p1.getRed() - p2.getRed(), 2);
    }

}
