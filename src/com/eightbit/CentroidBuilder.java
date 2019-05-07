package com.eightbit;

import org.jetbrains.annotations.NotNull;

class CentroidBuilder {

    private Centroid p;

    private CentroidBuilder() {
        p = new Centroid(0, 0, 0);
    }

    @NotNull
    static CentroidBuilder create() {
        return new CentroidBuilder();
    }

    CentroidBuilder fromDataPoint(DataPoint dp) {
        p.setRed(dp.getRed());
        p.setGreen(dp.getGreen());
        p.setBlue(dp.getBlue());
        return this;
    }

    Centroid build() {
        return p;
    }

}
