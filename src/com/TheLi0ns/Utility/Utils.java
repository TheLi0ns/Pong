package com.TheLi0ns.Utility;

import java.awt.*;

public class Utils {
    public static int xForCenteredText(Graphics2D g2d, Font font, int widthArea, String text){
        int length = (int)g2d.getFontMetrics(font).getStringBounds(text, g2d).getWidth();
        return widthArea/2 - length/2;
    }
}
