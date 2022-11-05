package com.TheLi0ns.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Powers.DefensivePowers.DefensivePowersEnum;
import com.TheLi0ns.Powers.OffensivePowers.OffensivePowersEnum;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display a power selection menu
 * @see PowerSelectionMenu#SET_OFFENSIVE_POWER
 * @see PowerSelectionMenu#SET_OFFENSIVE_POWER
 * @see PowerSelectionMenu#READY
 */
public class PowerSelectionMenu extends Menu{

    private final String playerName;

    private final int STARTING_Y;

    //POWER SELECTION MENU OPTIONS
    /**
     * Make the player choose his offensive power
     */
    public static final int SET_OFFENSIVE_POWER = 1;


    /**
     * Make the player choose his defensive power
     */
    public static final int SET_DEFENSIVE_POWER = 2;

    /**
     * Make the player ready to play
     */
    public static final int READY = 3;

    //POWERS INDEXES BASED ON THE POSITION IN THE POWERS ENUMS
    public int offensivePower_index = 0;
    public int defensivePower_index = 0;
    private boolean ready = false;

    public PowerSelectionMenu(int nPlayer) {
        super(3);
        this.playerName = nPlayer == 1 ? "PLAYER 1" : "PLAYER 2";
        this.STARTING_Y = nPlayer == 1 ? 300 : 600;
    }

    public OffensivePowersEnum getSelectedOffensivePower() {
        return OffensivePowersEnum.values()[offensivePower_index];
    }

    public DefensivePowersEnum getSelectedDefensivePower() {
        return DefensivePowersEnum.values()[defensivePower_index];
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
    }

    @Override
    public void clickOption() {
        switch (selectedOption){
            case SET_OFFENSIVE_POWER -> {
                offensivePower_index++;
                if (offensivePower_index > OffensivePowersEnum.values().length - 1) offensivePower_index = 0;
            }

            case SET_DEFENSIVE_POWER -> {
                defensivePower_index++;
                if (defensivePower_index > DefensivePowersEnum.values().length - 1) defensivePower_index = 0;
            }

            case READY -> ready = true;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = STARTING_Y;

        //PLAYER NAME
        g2d.drawString(playerName, Utils.xForCenteredText(g2d, font, GamePanel.WIDTH ,playerName), y);

        //SET OFFENSIVE POWER
        y += 60;
        g2d.drawString("OFFENSIVE POWER", x, y);
        if(selectedOption == SET_OFFENSIVE_POWER) g2d.drawString(">", x-30, y);
        g2d.drawString(OffensivePowersEnum.values()[offensivePower_index].name, 700, y);

        //SET DEFENSIVE POWER
        y += 60;
        g2d.drawString("DEFENSIVE POWER", x, y);
        if(selectedOption == SET_DEFENSIVE_POWER) g2d.drawString(">", x-30, y);
        g2d.drawString(DefensivePowersEnum.values()[defensivePower_index].name, 700, y);

        //READY
        y += 60;
        g2d.drawString("READY", x, y);
        if(selectedOption == READY && !ready) g2d.drawString(">", x-30, y);
        if(ready) g2d.fillRect(700, y-30, 30, 30);
        else g2d.drawRect(700, y-30, 30, 30);
    }
}
