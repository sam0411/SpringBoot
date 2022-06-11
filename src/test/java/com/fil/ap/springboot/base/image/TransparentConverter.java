package com.fil.ap.base.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TransparentConverter {

    private static final Color backColor = new Color(21,21,21);;
    private static final int THRESHOLD = 50;
    private static final int TRANSPARENT = 0;  // 0x00000000;

    static File base  = new File("C:\\image1");
    static File base2 = new File("C:\\image2");

    public static void main(String[] args) throws IOException {
    	
        System.out.println("TransparentConverter.main()");

        for (File file : base.listFiles()) {
            System.out.println(file);
            BufferedImage initImage = ImageIO.read(file);
            int width = initImage.getWidth(null),
                height = initImage.getHeight(null);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            g.drawImage(initImage, 0, 0, null);

            //System.out.println("before: " + image.getRGB(0, 0));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    Color color = new Color(pixel);

                    int dr = Math.abs(color.getRed()   - backColor.getRed()),
                        dg = Math.abs(color.getGreen() - backColor.getGreen()),
                        db = Math.abs(color.getBlue()  - backColor.getBlue());

                    if (dr < THRESHOLD && dg < THRESHOLD && db < THRESHOLD) {
                        image.setRGB(x, y, TRANSPARENT);
                    }
                }
            }
            //System.out.println("   after: " + image.getRGB(0, 0));

            file = new File(base2, file.getName());
            //System.out.println("   " + file);
            ImageIO.write(image, "png", file);
        }
    }
}