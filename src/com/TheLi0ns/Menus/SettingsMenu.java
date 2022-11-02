package com.TheLi0ns.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Powers.DefensivePowers.DefensivePowersEnum;
import com.TheLi0ns.Powers.OffensivePowers.OffensivePowersEnum;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display the settings of the game
 * @see SettingsMenu#SET_POINTS_TO_WIN_OPTION
 * @see SettingsMenu#ENABLE_POWERS_OPTION
 * @see SettingsMenu#P1_OFFENSIVE_POWER_OPTION
 * @see SettingsMenu#P1_DEFENSIVE_POWER_OPTION
 * @see SettingsMenu#P2_OFFENSIVE_POWER_OPTION
 * @see SettingsMenu#P2_DEFENSIVE_POWER_OPTION
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
     * Make the players choose the p1 offensive power
     */
    public static final int P1_OFFENSIVE_POWER_OPTION = 3;

    /**
     * Make the players choose the p1 defensive power
     */
    public static final int P1_DEFENSIVE_POWER_OPTION = 4;

    /**
     * Make the players choose the p2 offensive power
     */
    public static final int P2_OFFENSIVE_POWER_OPTION = 5;

    /**
     * Make the players choose the p2 defensive power
     */
    public static final int P2_DEFENSIVE_POWER_OPTION = 6;

    /**
     * Return to the title screen
     */
    public static final int BACK = 7;

    //POWERS INDEXES BASED ON THE POSITION IN THE POWERS ENUMS
    public static int p1OffensivePower_index = 0;
    public static int p1DefensivePower_index = 0;
    public static int p2OffensivePower_index = 0;
    public static int p2DefensivePower_index = 0;
    
    public SettingsMenu() {
        super(7);
    }

    public static OffensivePowersEnum getSelectedP1OffensivePower() {
        return OffensivePowersEnum.values()[p1OffensivePower_index];
    }

    public static DefensivePowersEnum getSelectedP1DefensivePower() {
        return DefensivePowersEnum.values()[p1DefensivePower_index];
    }

    public static OffensivePowersEnum getSelectedP2OffensivePower() {
        return OffensivePowersEnum.values()[p2OffensivePower_index];
    }

    public static DefensivePowersEnum getSelectedP2DefensivePower() {
        return DefensivePowersEnum.values()[p2DefensivePower_index];
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


            case P1_OFFENSIVE_POWER_OPTION -> {
                p1OffensivePower_index++;
                if(p1OffensivePower_index > OffensivePowersEnum.values().length-1) p1OffensivePower_index = 0;
            }

            case P1_DEFENSIVE_POWER_OPTION -> {
                p1DefensivePower_index++;
                if(p1DefensivePower_index > DefensivePowersEnum.values().length-1) p1DefensivePower_index = 0;
            }


            case P2_OFFENSIVE_POWER_OPTION -> {
                p2OffensivePower_index++;
                if(p2OffensivePower_index > OffensivePowersEnum.values().length-1) p2OffensivePower_index = 0;
            }

            case P2_DEFENSIVE_POWER_OPTION -> {
                p2DefensivePower_index++;
                if(p2DefensivePower_index > DefensivePowersEnum.values().length-1) p2DefensivePower_index = 0;
            }


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
        g2d.drawString(String.valueOf(MyFrame.gameLogic.arePowersEnabled()), 700, y);

        //P1 OFFENSIVE POWER
        y += 60;
        g2d.drawString("P1 OFFENSIVE POWER", x, y);
        if(selectedOption == P1_OFFENSIVE_POWER_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawString(OffensivePowersEnum.values()[p1OffensivePower_index].name, 700, y);

        //P1 DEFENSIVE POWER
        y += 60;
        g2d.drawString("P1 DEFENSIVE POWER", x, y);
        if(selectedOption == P1_DEFENSIVE_POWER_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawString(DefensivePowersEnum.values()[p1DefensivePower_index].name, 700, y);

        //P2 OFFENSIVE POWER
        y += 60;
        g2d.drawString("P2 OFFENSIVE POWER", x, y);
        if(selectedOption == P2_OFFENSIVE_POWER_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawString(OffensivePowersEnum.values()[p2OffensivePower_index].name, 700, y);

        //P2 DEFENSIVE POWER
        y += 60;
        g2d.drawString("P2 DEFENSIVE POWER", x, y);
        if(selectedOption == P2_DEFENSIVE_POWER_OPTION) g2d.drawString(">", x-30, y);
        g2d.drawString(DefensivePowersEnum.values()[p2DefensivePower_index].name, 700, y);

        //BACK
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "BACK");
        g2d.drawString("BACK", x, y);
        if(selectedOption == BACK) g2d.drawString(">", x-30, y);
    }
}
