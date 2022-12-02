package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.BooleanOption;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.MenusHandling.Options.SliderOption;
import com.TheLi0ns.MenusHandling.Options.ValueOption;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Display the settings of the game
 * @see SettingsMenu#SET_POINTS_TO_WIN_OPTION
 * @see SettingsMenu#ENABLE_POWERS_OPTION
 * @see SettingsMenu#VOLUME_SLIDER_OPTION
 * @see SettingsMenu#KEYBINDINGS_MENU_OPTION
 * @see SettingsMenu#DEFAULT_SETTINGS_OPTION
 * @see SettingsMenu#BACK
 */
public class SettingsMenu extends Menu {
    
    //SETTINGS MENU OPTIONS
    /**
     * Make the players set the points to win
     * @see ValueOption
     */
    public static final ValueOption SET_POINTS_TO_WIN_OPTION = new ValueOption("POINT TO WIN", 1);

    /**
     * Make the players choose to enable or disable the powers
     * @see BooleanOption
     */
    public static final BooleanOption ENABLE_POWERS_OPTION = new BooleanOption("ENABLE POWERS", 2);

    /**
     * Make the players set the volume
     * @see SliderOption
     */
    public static final SliderOption VOLUME_SLIDER_OPTION = new SliderOption("VOLUME", 3, Sound.MAX_VOLUME_SCALE);

    /**
     * Go to the {@link KeyBindingsMenu key bindings menu}
     * @see CenteredOption
     */
    public static final CenteredOption KEYBINDINGS_MENU_OPTION = new CenteredOption("KEYBINDINGS", 4);

    /**
     * Reset all the settings to their default value
     * @see CenteredOption
     */
    public static final CenteredOption DEFAULT_SETTINGS_OPTION = new CenteredOption("DEFAULT SETTINGS", 5);

    /**
     * Return to the {@link TitleScreen title screen}
     * @see CenteredOption
     */
    public static final CenteredOption BACK = new CenteredOption("BACK", 6);
    
    public SettingsMenu() {
        super(6);
    }

    @Override
    public void clickOption() {

        if(selectedOption == SET_POINTS_TO_WIN_OPTION.ID){
            MyFrame.gameLogic.setPointsToWin(MyFrame.gameLogic.getPointsToWin()+1);
            if(MyFrame.gameLogic.getPointsToWin() > GameLogic.MAX_POINTS)
                MyFrame.gameLogic.setPointsToWin(1);
        }

        else if(selectedOption == ENABLE_POWERS_OPTION.ID) MyFrame.gameLogic.setArePowersEnabled(!MyFrame.gameLogic.arePowersEnabled());

        else if(selectedOption == VOLUME_SLIDER_OPTION.ID) Sound.increaseVolume();

        else if(selectedOption == KEYBINDINGS_MENU_OPTION.ID) MyFrame.gameLogic.setGameState(GameLogic.GameStates.KEY_BINDINGS_MENU);

        else if(selectedOption == DEFAULT_SETTINGS_OPTION.ID) SettingFilesHandler.loadDefault();

        else if(selectedOption == BACK.ID){
            MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
            selectedOption = 1;
        }

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int x = 100;
        int value_x = 700;
        int y = 300;
        int y_offset = 60;

        y = SET_POINTS_TO_WIN_OPTION.draw(x, y, 700, 0, SET_POINTS_TO_WIN_OPTION.isSelected(selectedOption), MyFrame.gameLogic.getPointsToWin(), g2d);

        y = ENABLE_POWERS_OPTION.draw(x, y, value_x, y_offset, ENABLE_POWERS_OPTION.isSelected(selectedOption), MyFrame.gameLogic.arePowersEnabled(), g2d);

        y = VOLUME_SLIDER_OPTION.draw(x, y, value_x,30*Sound.MAX_VOLUME_SCALE, 30, y_offset, VOLUME_SLIDER_OPTION.isSelected(selectedOption), Sound.getVolumeScale(), g2d);

        y = KEYBINDINGS_MENU_OPTION.draw(y, y_offset, KEYBINDINGS_MENU_OPTION.isSelected(selectedOption), g2d);

        y = DEFAULT_SETTINGS_OPTION.draw(y, y_offset, DEFAULT_SETTINGS_OPTION.isSelected(selectedOption), g2d);

        y = BACK.draw(y, y_offset, BACK.isSelected(selectedOption), g2d);
    }
}
