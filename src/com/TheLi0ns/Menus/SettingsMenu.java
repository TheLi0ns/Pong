package com.TheLi0ns.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display the settings of the game
 * @see SettingsMenu#SET_POINTS_TO_WIN_OPTION
 * @see SettingsMenu#ENABLE_POWERS_OPTION
 * @see SettingsMenu#VOLUME_SLIDER_OPTION
 * @see SettingsMenu#BACK
 */
public class SettingsMenu extends Menu {
    
    //SETTINGS MENU OPTIONS
    /**
     * Make the players set the points to win
     */
    public static final int SET_POINTS_TO_WIN_OPTION = 1;

    /**
     * Make the players choose to enable or disable the powers
     */
    public static final int ENABLE_POWERS_OPTION = 2;

    /**
     * Make the players set the volume
     */
    public static final int VOLUME_SLIDER_OPTION = 3;

    /**
     * Return to the title screen
     */
    public static final int BACK = 4;
    
    public SettingsMenu() {
        super(4);
    }

    @Override
    public void clickOption() {
        switch (selectedOption){

            case SET_POINTS_TO_WIN_OPTION -> {
                MyFrame.gameLogic.setPointsToWin(MyFrame.gameLogic.getPointsToWin()+1);
                if(MyFrame.gameLogic.getPointsToWin() > GameLogic.MAX_POINTS)
                    MyFrame.gameLogic.setPointsToWin(1);
            }

            case ENABLE_POWERS_OPTION -> MyFrame.gameLogic.setArePowersEnabled(!MyFrame.gameLogic.arePowersEnabled());

            case VOLUME_SLIDER_OPTION -> Sound.increaseVolume();

            case BACK ->{
                MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
                selectedOption = 1;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 300;

        //POINT TO WIN
        g2d.drawString("POINT TO WIN", x, y);
        if(selectedOption == SET_POINTS_TO_WIN_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawString(String.valueOf(MyFrame.gameLogic.getPointsToWin()), 700, y);

        //ENABLE POWERS
        y += 60;
        g2d.drawString("ENABLE POWERS", x, y);
        if(selectedOption == ENABLE_POWERS_OPTION) g2d.drawString(">", x-30, y);
        if(MyFrame.gameLogic.arePowersEnabled()) g2d.fillRect(700, y-30, 30, 30);
        else g2d.drawRect(700, y-30, 30, 30);

        //VOLUME SLIDER
        y += 60;
        g2d.drawString("VOLUME", x, y);
        if(selectedOption == VOLUME_SLIDER_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawRect(700, y-30, 30*Sound.MAX_VOLUME_SCALE, 30);
        g2d.fillRect(700, y-30, 30*Sound.getVolumeScale(), 30);

        //BACK
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "BACK");
        g2d.drawString("BACK", x, y);
        if(selectedOption == BACK) g2d.drawString(">", x-30, y);
    }
}
