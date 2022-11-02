package com.TheLi0ns.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
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
     */
    public static final int PLAY_OPTION = 1;

    /**
     * Go to the {@link SettingsMenu settings menu}
     */
    public static final int SETTINGS_OPTION = 2;

    /**
     * Close the game
     */
    public static final int QUIT_OPTION = 3;

    public TitleScreen() {
        super(3);
    }

    @Override
    public void clickOption(){
        switch (selectedOption){
            case PLAY_OPTION -> MyFrame.gameLogic.startMatch();
            case SETTINGS_OPTION -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.SETTINGS_MENU);
            case QUIT_OPTION -> System.exit(0);
        }
    }

    @Override
    public void draw(Graphics2D g2d){

        g2d.setColor(Color.WHITE);

        Font font = new Font("Comic Sans MS", 0, 100);
        g2d.setFont(font);

        //GAME TITLE
        int x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, GAME_NAME);
        g2d.drawString(GAME_NAME, x, 300);


        font = new Font("Comic Sans MS", 0, 50);
        g2d.setFont(font);

        //PLAY
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "PLAY");
        g2d.drawString("PLAY", x, 400);
        if(selectedOption == PLAY_OPTION) g2d.drawString(">", x-30, 400);

        //SETTINGS
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "SETTINGS");
        g2d.drawString("SETTINGS", x, 500);
        if(selectedOption == SETTINGS_OPTION) g2d.drawString(">", x-30, 500);

        //QUIT
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "QUIT");
        g2d.drawString("QUIT", x, 600);
        if(selectedOption == QUIT_OPTION) g2d.drawString(">", x-30, 600);
    }
}
