package com.TheLi0ns.GameStates.MenusHandling.Menus;

import com.TheLi0ns.GameStates.GameState;
import com.TheLi0ns.GameStates.MenusHandling.SubMenus.SubMenu;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Sound;

/**
 * Super class for Menus
 * @see SettingsMenu
 * @see TitleScreen
 * @see PowersSelectionMenu_PvP
 * @see PowersSelectionMenu_PvE
 * @see KeyBindingsMenu
 */
public abstract class Menu implements GameState {

    protected int selectedOption = 1;

    protected final int N_OPTIONS;

    protected boolean isInSubMenu = false;
    protected SubMenu subMenu;

    protected final GameLogic gl;

    Input input = Input.NONE;
    public enum Input{NEXT, PREVIOUS, SELECT, NONE}

    Menu(int nOptions, GameLogic gl){N_OPTIONS = nOptions;
        this.gl = gl;
    }

    @Override
    public void update() {
        switch (input){
            case NEXT -> nextOption();
            case PREVIOUS -> previousOption();
            case SELECT -> clickOption();
            case NONE -> {}
        }
    }

    public void nextOption(){
        input = Input.NONE;
        if(!isInSubMenu){
            Sound.play(Sound.OPTION_SELECTION);
            selectedOption++;
            if(selectedOption > N_OPTIONS) selectedOption = 1;
        }else subMenu.nextOption();
    }

    public void previousOption(){
        input = Input.NONE;
        if(!isInSubMenu){
            Sound.play(Sound.OPTION_SELECTION);
            selectedOption--;
            if(selectedOption <= 0) selectedOption = N_OPTIONS;
        }else subMenu.previousOption();
    }

    public final void exitSubMenu(){
        isInSubMenu = false;
    }

    /**
     * Fires the action of the selected option
     */
    public final void clickOption(){
        input = Input.NONE;
        if(!isInSubMenu) performOption();
        else subMenu.clickOption();
    }

    /**
     * This method define the action of the options
     */
    public abstract void performOption();

    public void resetSelectedOption(){
        selectedOption = 1;
    }

    protected void back(){
        resetSelectedOption();
        isInSubMenu = false;
    }

    public void setInput(Input input) {
        if(this.input == Input.NONE){
            this.input = input;
        }
    }
}
