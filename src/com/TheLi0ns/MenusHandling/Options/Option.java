package com.TheLi0ns.MenusHandling.Options;

import java.awt.*;

/**
 * Super class for Options
 * @see ValueOption
 * @see BooleanOption
 * @see CenteredOption
 * @see SliderOption
 */
public abstract class Option {
    final String NAME;
    public final int ID;

    protected Option(String name, int id) {
        NAME = name;
        ID = id;
    }

    protected int draw(int x, int y, int y_offset, boolean isSelected, Graphics2D g2d){
        y += y_offset;
        g2d.drawString(NAME, x, y);
        if(isSelected) g2d.drawString(">", x-30, y);
        return y;
    }

    public boolean isSelected(int selectedOption){
        return ID == selectedOption;
    }
}
