package com.TheLi0ns.MenusHandling.Options;

import java.awt.*;

/**
 * Option that works with an int variable
 * represented by a slider
 */
public class SliderOption extends Option{
    final int N_TILES;

    public SliderOption(String name, int id, int n_tiles) {
        super(name, id);
        N_TILES = n_tiles;
    }

    public int draw(int x, int y, int value_x, int value_width, int value_height, int y_offset, boolean isSelected, int value, Graphics2D g2d) {
        y = super.draw(x, y, y_offset, isSelected, g2d);

        int tile_length = value_width/N_TILES;

        g2d.drawRect(value_x, y-value_height, tile_length * N_TILES, value_height);
        g2d.fillRect(value_x, y-value_height, tile_length * value, value_height);
        return y;
    }
}
