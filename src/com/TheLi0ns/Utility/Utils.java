package com.TheLi0ns.Utility;

import java.awt.*;

public class Utils {
    public static int xForCenteredText(Graphics2D g2d, Font font, int widthArea, String text){
        int length = (int)g2d.getFontMetrics(font).getStringBounds(text, g2d).getWidth();
        return widthArea/2 - length/2;
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
}
