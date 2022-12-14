package com.TheLi0ns.MenusHandling.Menus;

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

    Menu(int nOptions){N_OPTIONS = nOptions;}

    public void nextOption(){
        Sound.play(Sound.OPTION_SELECTION);
        selectedOption++;
        if(selectedOption > N_OPTIONS) selectedOption = 1;
    }

    public void previousOption(){
        Sound.play(Sound.OPTION_SELECTION);
        selectedOption--;
        if(selectedOption <= 0) selectedOption = N_OPTIONS;
    }

    public void resetSelectedOption(){
        selectedOption = 1;
    }

    protected void back(){
        resetSelectedOption();
    }

    /**
     * This method define the action of the options
     * and fires the action of the selected option
     */
    public abstract void clickOption();
    public abstract void draw(Graphics2D g2d);
}
