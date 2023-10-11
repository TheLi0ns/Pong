package com.TheLi0ns.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Utils {
    public static int xForCenteredText(Graphics2D g2d, int widthArea, String text){
        int length = (int)g2d.getFontMetrics(g2d.getFont()).getStringBounds(text, g2d).getWidth();
        return widthArea/2 - length/2;
    }

    public static int yForCenteredText(Graphics2D g2d, int heightArea, String text){
        int length = (int)g2d.getFontMetrics(g2d.getFont()).getStringBounds(text, g2d).getHeight();
        return heightArea/2 - length/2;
    }

    public static int genRandomXVelocity(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(3, 5) * (random.nextBoolean() ? 1 : -1);
    }

    /**
     * Calculate the x position of the object
     * when it will reach the given y
     * @param velocity velocity of the object
     * @param startingPoint startingPoint of the object
     * @param finalY y to reach
     * @return x
     */
    public static int predictXonY(Vector2D velocity, Vector2D startingPoint, int finalY){
        return startingPoint.getX() + velocity.getX() * ((finalY - startingPoint.getY()) / velocity.getY());
    }

    public static Image flipImageVertically(Image image){
        BufferedImage image_flipped = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = image_flipped.createGraphics();

        g2d.drawImage(
                image,
                0,
                image.getHeight(null),
                image.getWidth(null),
                -image.getHeight(null),
                null
        );

        return image_flipped;
    }
}
