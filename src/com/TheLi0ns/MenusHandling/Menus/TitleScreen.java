package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.MenusHandling.SubMenus.GameModeSubMenu;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display the name of the game
 * @see TitleScreen#PLAY_OPTION
 * @see TitleScreen#SETTINGS_OPTION
 * @see TitleScreen#QUIT_OPTION
 */
public class TitleScreen extends Menu{
    private static final String GAME_NAME = "PONG";


    //TITLE SCREEN OPTIONS

    /**
     * Start the match
     * @see CenteredOption
     */
    public static final CenteredOption PLAY_OPTION = new CenteredOption("PLAY", 1);

    /**
     * Go to the {@link MiniGamesMenu mini-games menu}
     * @see CenteredOption
     */
    public static final CenteredOption MINI_GAMES_OPTION = new CenteredOption("MINI-GAMES", 2);

    /**
     * Go to the {@link SettingsMenu settings menu}
     * @see CenteredOption
     */
    public static final CenteredOption SETTINGS_OPTION = new CenteredOption("SETTINGS", 3);

    /**
     * Close the game
     * @see CenteredOption
     */
    public static final CenteredOption QUIT_OPTION = new CenteredOption("QUIT", 4);

    public TitleScreen() {
        super(4);
        subMenu = new GameModeSubMenu(this);
    }

    @Override
    public void performOption() {

        if (selectedOption == PLAY_OPTION.ID) isInSubMenu = true;

        else if(selectedOption == MINI_GAMES_OPTION.ID) MyFrame.gameLogic.setGameState(GameLogic.GameStates.MINI_GAMES_MENU);

        else if (selectedOption == SETTINGS_OPTION.ID)
            MyFrame.gameLogic.setGameState(GameLogic.GameStates.SETTINGS_MENU);

        else if (selectedOption == QUIT_OPTION.ID) MyFrame.quit();

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void draw(Graphics2D g2d){

        int y = 350;
        int y_offset = 80;

        g2d.setColor(Color.WHITE);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 80);
        g2d.setFont(font);

        //GAME IMAGE
        g2d.drawImage(Assets.GAME_PREVIEW, 350, 70, null);

        //GAME TITLE
        int x = Utils.xForCenteredText(g2d, GamePanel.WIDTH, GAME_NAME);
        g2d.drawString(GAME_NAME, x, y);


        font = new Font("Comic Sans MS", Font.PLAIN, 40);
        g2d.setFont(font);

        y = PLAY_OPTION.draw(y, y_offset, PLAY_OPTION.isSelected(selectedOption), g2d);

        //SUBMENU
        if(isInSubMenu) y = subMenu.draw(g2d, y);

        y = MINI_GAMES_OPTION.draw(y, y_offset, MINI_GAMES_OPTION.isSelected(selectedOption), g2d);

        y = SETTINGS_OPTION.draw(y, y_offset, SETTINGS_OPTION.isSelected(selectedOption), g2d);

        y = QUIT_OPTION.draw(y, y_offset, QUIT_OPTION.isSelected(selectedOption), g2d);
    }
}
