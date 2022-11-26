package com.TheLi0ns.MenusHandling.Options;

import java.awt.*;

/**
 * Option that works with a boolean variable
 * represented by a square
 */
public class BooleanOption extends Option{
    int value_dimension;

    public BooleanOption(String name, int id) {
        super(name, id);
        value_dimension = 0;
    }

    protected BooleanOption(int value_dimension, String name, int id) {
        super(name, id);
        this.value_dimension = value_dimension;
    }

    public int draw(int x, int y, int value_x, int y_offset, boolean isSelected, boolean value, Graphics2D g2d) {
        if(value_dimension == 0) value_dimension = y_offset/2;
        y = super.draw(x, y, y_offset, isSelected, g2d);
        if(value) g2d.fillRect(value_x, y-value_dimension, value_dimension, value_dimension);
        else g2d.drawRect(value_x, y-value_dimension, value_dimension, value_dimension);
        return y;
    }
}
