package com.TheLi0ns.MenusHandling.Options;

import java.awt.*;

/**
 * Option that works with a value (double, int, char, string)
 * represented by its string
 */
public class ValueOption extends Option{

    public ValueOption(String name, int id) {
        super(name, id);
    }

    public int draw(int x, int y, int value_x, int y_offset, boolean isSelected, int value, Graphics2D g2d) {
        y = super.draw(x, y, y_offset, isSelected, g2d);
        g2d.drawString(String.valueOf(value), value_x, y);
        return y;
    }

    public int draw(int x, int y, int value_x, int y_offset, boolean isSelected, double value, Graphics2D g2d) {
        y = super.draw(x, y, y_offset, isSelected, g2d);
        g2d.drawString(String.valueOf(value), value_x, y);
        return y;
    }

    public int draw(int x, int y, int value_x, int y_offset, boolean isSelected, String value, Graphics2D g2d) {
        y = super.draw(x, y, y_offset, isSelected, g2d);
        g2d.drawString(value, value_x, y);
        return y;
    }

    public int draw(int x, int y, int value_x, int y_offset, boolean isSelected, char value, Graphics2D g2d) {
        y = super.draw(x, y, y_offset, isSelected, g2d);
        g2d.drawString(String.valueOf(value), value_x, y);
        return y;
    }

}
