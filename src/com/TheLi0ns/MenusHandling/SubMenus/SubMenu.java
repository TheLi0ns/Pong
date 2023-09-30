package com.TheLi0ns.MenusHandling.SubMenus;

import com.TheLi0ns.MenusHandling.Menus.Menu;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Super class for SubMenus
 * @see GameModeSubMenu
 */
public abstract class SubMenu {
    protected int selectedOption = 1;

    private final int N_OPTIONS;

    private final int Y_OFFSET;

    private final Menu MENU;

    SubMenu(int nOptions, int y_offset, Menu menu){
        N_OPTIONS = nOptions;
        Y_OFFSET = y_offset;
        MENU = menu;
    }

    public void nextOption(){
        Sound.play(Sound.OPTION_SELECTION);
        selectedOption++;
        if(selectedOption > N_OPTIONS){
            back();
            selectedOption = 1;
        }
    }

    public void previousOption(){
        Sound.play(Sound.OPTION_SELECTION);
        selectedOption--;
        if(selectedOption <= 0){
            back();
            selectedOption = 1;
        }
    }

    /**
     * This method makes the player return to the menu
     */
    public final void back(){
        MENU.exitSubMenu();
    }

    public final void clickOption(){
        performOption();
        MENU.exitSubMenu();
    }

    /**
     * This method define the action of the options
     * and fires the action of the selected option
     */
    public abstract void performOption();

    public final int draw(Graphics2D g2d, int y){
        Stroke s = g2d.getStroke();
        Color c = g2d.getColor();
        Font f = g2d.getFont();
        drawOptions(g2d, y);
        g2d.setStroke(s);
        g2d.setColor(c);
        g2d.setFont(f);
        return y + Y_OFFSET;
    }

    public abstract void drawOptions(Graphics2D g2d, int y);
}
