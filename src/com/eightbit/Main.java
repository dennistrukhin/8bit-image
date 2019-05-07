package com.eightbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final int K = 5;
    private static final int C = 4;

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("/Users/dennis/input.jpg"));
            int width = img.getWidth();
            int height = img.getHeight();

            if (false) {
                for (int i = 0; i < Math.floor(height / C); i++) {
                    for (int j = 0; j < Math.floor(width / C); j++) {
                        Point p = new Point(0, 0, 0);
                        int cnt = 0;
                        for (int k = 0; k < C; k++) {
                            for (int l = 0; l < C; l++) {
                                if (j * C + l < width && i * C + k < height) {
                                    p.add(DataPointBuilder.create().fromImagePixel(img, j * C + l, i * C + k).build());
                                    cnt++;
                                }
                            }
                        }
                        p.divideBy(cnt);
                        for (int k = 0; k < C; k++) {
                            for (int l = 0; l < C; l++) {
                                if (j * C + l < width && i * C + k < height) {
                                    img.setRGB(j * C + l, i * C + k, new Color(p.getRed(), p.getGreen(), p.getBlue()).getRGB());
                                }
                            }
                        }
                    }
                }
            }

            ArrayList<DataPoint> points = new ArrayList<>();
            ArrayList<Centroid> centroids = new ArrayList<>();
            ArrayList<Point> mean = new ArrayList<>();
            ArrayList<Integer> dpCount = new ArrayList<>();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    points.add(DataPointBuilder.create().fromImagePixel(img, j, i).build());
                }
            }
            ArrayList<Integer> usedPointIndices = new ArrayList<>();

            int index;
            for (int i = 0; i < K; i++) {
                while (true) {
                    index = ThreadLocalRandom.current().nextInt(0, points.size());
                    if (!usedPointIndices.contains(index)) {
                        usedPointIndices.add(index);
                        break;
                    }
                }
                centroids.add(CentroidBuilder.create().fromDataPoint(points.get(index)).build());
                mean.add(new Point(0, 0, 0));
                dpCount.add(0);
            }

            int iteration = 0;
            while (true) {
                System.out.println("==================================================");
                for (int i = 0; i < K; i++) {
                    dpCount.set(i, 0);
                }
                iteration++;
                for (DataPoint dp : points) {
                    double minDistance = 1e12;
                    int closestCentroidIndex = 0;
                    for (int j = 0; j < centroids.size(); j++) {
                        double d = Metric.getDistance(dp, centroids.get(j));
                        if (d < minDistance) {
                            minDistance = d;
                            closestCentroidIndex = j;
                        }
                    }
                    dp.setCentroidIndex(closestCentroidIndex);
                    Point m = mean.get(closestCentroidIndex);
                    m.add(dp);
                    mean.set(closestCentroidIndex, m);
                    dpCount.set(closestCentroidIndex, dpCount.get(closestCentroidIndex) + 1);
                }

                System.out.println(dpCount);

                int totalUpdated = 0;
                for (int i = 0; i < mean.size(); i++) {
                    if (dpCount.get(i) > 0) {
                        mean.get(i).divideBy(dpCount.get(i));
                    }
                    centroids.get(i).update(
                            mean.get(i).getRed(),
                            mean.get(i).getGreen(),
                            mean.get(i).getBlue()
                    );
                    if (!centroids.get(i).isStable()) {
                        totalUpdated++;
                    }
                }

                System.out.println("Iteration " + iteration + " completed");
                System.out.println("Updated centroids: " + totalUpdated);

                for (Point p : mean) {
                    System.out.println(p);
                }

                if (totalUpdated == 0) {
                    System.out.println("FINISHED");
                    break;
                }
            }

            centroids.get(0).setRed(255);
            centroids.get(0).setGreen(255);
            centroids.get(0).setBlue(255);

            centroids.get(1).setRed(0);
            centroids.get(1).setGreen(0);
            centroids.get(1).setBlue(143);

            centroids.get(2).setRed(150);
            centroids.get(2).setGreen(0);
            centroids.get(2).setBlue(0);

            centroids.get(3).setRed(216);
            centroids.get(3).setGreen(62);
            centroids.get(3).setBlue(82);

            centroids.get(4).setRed(242);
            centroids.get(4).setGreen(233);
            centroids.get(4).setBlue(78);

//            centroids.get(0).setRed(26);
//            centroids.get(0).setGreen(83);
//            centroids.get(0).setBlue(92);
//
//            centroids.get(1).setRed(78);
//            centroids.get(1).setGreen(205);
//            centroids.get(1).setBlue(196);
//
//            centroids.get(2).setRed(247);
//            centroids.get(2).setGreen(255);
//            centroids.get(2).setBlue(247);
//
//            centroids.get(3).setRed(255);
//            centroids.get(3).setGreen(107);
//            centroids.get(3).setBlue(107);
//
//            centroids.get(4).setRed(255);
//            centroids.get(4).setGreen(130);
//            centroids.get(4).setBlue(109);

//            centroids.get(0).setRed(23);
//            centroids.get(0).setGreen(18);
//            centroids.get(0).setBlue(25);
//
//            centroids.get(1).setRed(34);
//            centroids.get(1).setGreen(85);
//            centroids.get(1).setBlue(96);
//
//            centroids.get(2).setRed(237);
//            centroids.get(2).setGreen(240);
//            centroids.get(2).setBlue(96);
//
//            centroids.get(3).setRed(240);
//            centroids.get(3).setGreen(128);
//            centroids.get(3).setBlue(60);
//
//            centroids.get(4).setRed(49);
//            centroids.get(4).setGreen(13);
//            centroids.get(4).setBlue(32);

            for (DataPoint dp: points) {
                Centroid c = centroids.get(dp.getCentroidIndex());
                img.setRGB(dp.getX(), dp.getY(), new Color(c.getRed(), c.getGreen(), c.getBlue()).getRGB());
            }

            ImageIO.write(img, "jpg", new File("/Users/dennis/output.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
