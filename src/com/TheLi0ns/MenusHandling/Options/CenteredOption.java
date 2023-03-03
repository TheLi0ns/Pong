package com.TheLi0ns.MenusHandling.Options;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Option without any value or similar
 * represented by its name centered
 */
public class CenteredOption extends Option{

    public CenteredOption(String name, int id) {
        super(name, id);
    }

    public int draw(int y, int y_offset, boolean isSelected, Graphics2D g2d) {
        y += y_offset;
        int x = Utils.xForCenteredText(g2d, GamePanel.WIDTH, NAME);
        g2d.drawString(NAME, x, y);
        if(isSelected) g2d.drawString(">", x-30, y);
        return y;
    }
}
