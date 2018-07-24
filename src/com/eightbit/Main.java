package com.eightbit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static final int K = 16;

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("/Users/dennis/input.jpg"));
            int width = img.getWidth();
            int height = img.getHeight();

            ArrayList<Point> points = new ArrayList<>();
            ArrayList<Point> centroids = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                centroids.add(new Point(0, 0, 0));
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    points.add(PointBuilder.create().fromImage(img).atCoordinates(j, i).build());
                }
            }

            System.out.println(width + " " + height);
            System.out.println(points.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
