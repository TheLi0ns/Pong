package com.TheLi0ns.MenusHandling.SubMenus;

import java.awt.*;

/**
 * Super class for SubMenus
 * @see GameModeSubMenu
 */
public abstract class SubMenu {
    protected int selectedOption = 1;

    private final int N_OPTIONS;

    private static int Y_OFFSET;

    SubMenu(int nOptions, int y_offset){
        N_OPTIONS = nOptions;
        Y_OFFSET = y_offset;
    }

    public static int getY_OFFSET() {
        return Y_OFFSET;
    }

    public void nextOption(){
        selectedOption++;
        if(selectedOption > N_OPTIONS){
            back();
            selectedOption = 1;
        }
    }

    public void previousOption(){
        selectedOption--;
        if(selectedOption <= 0){
            back();
            selectedOption = 1;
        }
    }

    /**
     * This method makes the player return to the menu
     */
    public abstract void back();

    /**
     * This method define the action of the options
     * and fires the action of the selected option
     */
    public abstract void clickOption();
    public abstract void draw(Graphics2D g2d);
}
