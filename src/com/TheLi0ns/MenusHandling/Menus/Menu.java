package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.MenusHandling.SubMenus.SubMenu;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Super class for Menus
 * @see SettingsMenu
 * @see TitleScreen
 * @see PowerSelectionMenu
 * @see KeyBindingsMenu
 */
public abstract class Menu {

    protected int selectedOption = 1;

    private final int N_OPTIONS;

    protected boolean isInSubMenu = false;
    protected SubMenu subMenu;

    Menu(int nOptions){N_OPTIONS = nOptions;}

    public final void nextOption(){
        if(!isInSubMenu){
            Sound.play(Sound.OPTION_SELECTION);
            selectedOption++;
            if(selectedOption > N_OPTIONS) selectedOption = 1;
        }else subMenu.nextOption();
    }

    public final void previousOption(){
        if(!isInSubMenu){
            Sound.play(Sound.OPTION_SELECTION);
            selectedOption--;
            if(selectedOption <= 0) selectedOption = N_OPTIONS;
        }else subMenu.previousOption();
    }

    public void resetSelectedOption(){
        selectedOption = 1;
    }

    protected void back(){
        resetSelectedOption();
        isInSubMenu = false;
    }

    public final void exitSubMenu(){
        isInSubMenu = false;
    }

    /**
     * Fires the action of the selected option
     */
    public final void clickOption(){
        if(!isInSubMenu) performOption();
        else subMenu.clickOption();
    }

    /**
     * This method define the action of the options
     */
    public abstract void performOption();
    public abstract void draw(Graphics2D g2d);
}
