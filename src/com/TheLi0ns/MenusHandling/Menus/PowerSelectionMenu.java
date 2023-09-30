package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.BooleanOption;
import com.TheLi0ns.MenusHandling.Options.ValueOption;
import com.TheLi0ns.Powers.DefensivePowers.DefensivePowersEnum;
import com.TheLi0ns.Powers.OffensivePowers.OffensivePowersEnum;
import com.TheLi0ns.Utility.Sound;
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
     * @see ValueOption
     */
    public static final ValueOption SET_OFFENSIVE_POWER = new ValueOption("OFFENSIVE POWER", 1);


    /**
     * Make the player choose his defensive power
     * @see ValueOption
     */
    public static final ValueOption SET_DEFENSIVE_POWER = new ValueOption("DEFENSIVE POWER", 2);

    /**
     * Make the player ready to play
     * @see BooleanOption
     */
    public static final BooleanOption READY = new BooleanOption("READY", 3);

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
        if(!ready) selectedOption = 1;
    }

    @Override
    public void performOption() {

        if(selectedOption == SET_OFFENSIVE_POWER.ID){
            offensivePower_index++;
            if (offensivePower_index > OffensivePowersEnum.values().length - 1) offensivePower_index = 0;
        }

        else if(selectedOption == SET_DEFENSIVE_POWER.ID){
            defensivePower_index++;
            if (defensivePower_index > DefensivePowersEnum.values().length - 1) defensivePower_index = 0;
        }

        else if(selectedOption == READY.ID){
            if(MyFrame.gameLogic.getGameMode() == GameLogic.GameModes.PVE){
                MyFrame.gameLogic.startMatch();
                selectedOption = 1;
            }
            else ready = true;
        }

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void resetSelectedOption() {
        super.resetSelectedOption();
        ready = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", Font.PLAIN, 30);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int x = 100;
        int value_x = 700;
        int y = STARTING_Y;
        int y_offset = 60;

        //PLAYER NAME
        g2d.drawString(playerName, Utils.xForCenteredText(g2d, GamePanel.WIDTH ,playerName), y);

        y = SET_OFFENSIVE_POWER.draw(x, y, value_x, y_offset, SET_OFFENSIVE_POWER.isSelected(selectedOption), OffensivePowersEnum.values()[offensivePower_index].name, g2d);

        y = SET_DEFENSIVE_POWER.draw(x, y, value_x, y_offset, SET_DEFENSIVE_POWER.isSelected(selectedOption), DefensivePowersEnum.values()[defensivePower_index].name, g2d);

        y = READY.draw(x, y, value_x, y_offset, READY.isSelected(selectedOption), ready, g2d);
    }
}
