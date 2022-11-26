package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.MenusHandling.SubMenus.GameModeSubMenu;
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
     * Go to the {@link SettingsMenu settings menu}
     * @see CenteredOption
     */
    public static final CenteredOption SETTINGS_OPTION = new CenteredOption("SETTINGS", 2);

    /**
     * Close the game
     * @see CenteredOption
     */
    public static final CenteredOption QUIT_OPTION = new CenteredOption("QUIT", 3);

    public TitleScreen() {
        super(3);
    }

    @Override
    public void clickOption(){

        if(selectedOption == PLAY_OPTION.ID) MyFrame.gameLogic.setGameState(GameLogic.GameStates.PLAY_SUBMENU);

        else if(selectedOption == SETTINGS_OPTION.ID) MyFrame.gameLogic.setGameState(GameLogic.GameStates.SETTINGS_MENU);

        else if(selectedOption == QUIT_OPTION.ID) MyFrame.quit();
    }

    @Override
    public void draw(Graphics2D g2d){

        int y = 300;
        int y_offset = 100;

        g2d.setColor(Color.WHITE);

        Font font = new Font("Comic Sans MS", 0, 100);
        g2d.setFont(font);

        //GAME TITLE
        int x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, GAME_NAME);
        g2d.drawString(GAME_NAME, x, y);


        font = new Font("Comic Sans MS", 0, 50);
        g2d.setFont(font);

        y = PLAY_OPTION.draw(y, y_offset, PLAY_OPTION.isSelected(selectedOption), g2d);

        //SUBMENU OFFSET
        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAY_SUBMENU) y += GameModeSubMenu.getY_OFFSET();

        y = SETTINGS_OPTION.draw(y, y_offset, SETTINGS_OPTION.isSelected(selectedOption), g2d);

        y = QUIT_OPTION.draw(y, y_offset, QUIT_OPTION.isSelected(selectedOption), g2d);
    }
}
