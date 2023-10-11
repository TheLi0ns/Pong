package com.TheLi0ns.GameStates.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameStates.GameModes.Match_pvp;
import com.TheLi0ns.GameStates.MenusHandling.Options.BooleanOption;
import com.TheLi0ns.GameStates.MenusHandling.Options.ValueOption;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Powers.DefensivePowers.DefensivePowersEnum;
import com.TheLi0ns.Powers.OffensivePowers.OffensivePowersEnum;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display powers selection menu for the two players
 * @see PowersSelectionMenu_PvP#SET_P1_DEFENSIVE_POWER_OPTION
 * @see PowersSelectionMenu_PvP#SET_P1_OFFENSIVE_POWER_OPTION
 * @see PowersSelectionMenu_PvP#P1_READY_OPTION
 * @see PowersSelectionMenu_PvP#SET_P2_DEFENSIVE_POWER_OPTION
 * @see PowersSelectionMenu_PvP#SET_P2_OFFENSIVE_POWER_OPTION
 * @see PowersSelectionMenu_PvP#P2_READY_OPTION
 */
public class PowersSelectionMenu_PvP extends Menu{

    private final String player1Name = "PLAYER 1";
    private final String player2Name = "PLAYER 2";

    //POWER SELECTION MENU OPTIONS
    /**
     * Make the player1 choose his offensive power
     * @see ValueOption
     */
    public static final ValueOption SET_P1_OFFENSIVE_POWER_OPTION = new ValueOption("P1 OFFENSIVE POWER", 1);


    /**
     * Make the player1 choose his defensive power
     * @see ValueOption
     */
    public static final ValueOption SET_P1_DEFENSIVE_POWER_OPTION = new ValueOption("P1 DEFENSIVE POWER", 2);

    /**
     * Make the player1 ready to play
     * @see BooleanOption
     */
    public static final BooleanOption P1_READY_OPTION = new BooleanOption("P1 READY", 3);

    /**
     * Make the player2 choose his offensive power
     * @see ValueOption
     */
    public static final ValueOption SET_P2_OFFENSIVE_POWER_OPTION = new ValueOption("P2 OFFENSIVE POWER", 4);


    /**
     * Make the player2 choose his defensive power
     * @see ValueOption
     */
    public static final ValueOption SET_P2_DEFENSIVE_POWER_OPTION = new ValueOption("P2 DEFENSIVE POWER", 5);

    /**
     * Make the player2 ready to play
     * and start the match
     * @see BooleanOption
     */
    public static final BooleanOption P2_READY_OPTION = new BooleanOption("P2 READY", 6);
    
    //POWERS INDEXES BASED ON THE POSITION IN THE POWERS ENUMS
    public static int p1_offensivePower_index = 0;
    public static int p1_defensivePower_index = 0;
    
    public static int p2_offensivePower_index = 0;
    public static int p2_defensivePower_index = 0;
    
    private boolean ready = false;

    public PowersSelectionMenu_PvP(GameLogic gl) {
        super(6, gl);
    }

    public static OffensivePowersEnum getP1SelectedOffensivePower() {
        return OffensivePowersEnum.values()[p1_offensivePower_index];
    }

    public static DefensivePowersEnum getP1SelectedDefensivePower() {
        return DefensivePowersEnum.values()[p1_defensivePower_index];
    }

    public static OffensivePowersEnum getP2SelectedOffensivePower() {
        return OffensivePowersEnum.values()[p2_offensivePower_index];
    }

    public static DefensivePowersEnum getP2SelectedDefensivePower() {
        return DefensivePowersEnum.values()[p2_defensivePower_index];
    }

    @Override
    public void nextOption() {
        input = Input.NONE;
        Sound.play(Sound.OPTION_SELECTION);
        if(!ready){
            selectedOption++;
            if(selectedOption > N_OPTIONS/2) selectedOption = 1;
        }else{
            selectedOption++;
            if(selectedOption > N_OPTIONS) selectedOption = N_OPTIONS/2 + 1;
        }
    }

    @Override
    public void previousOption() {
        input = Input.NONE;
        Sound.play(Sound.OPTION_SELECTION);
        if(!ready){
            selectedOption--;
            if(selectedOption <= 0) selectedOption = N_OPTIONS/2;
        }else{
            selectedOption--;
            if(selectedOption <= 3) selectedOption = N_OPTIONS;
        }
    }

    @Override
    public void performOption() {
        input = Input.NONE;
        if(selectedOption == SET_P1_OFFENSIVE_POWER_OPTION.ID){
            p1_offensivePower_index++;
            if (p1_offensivePower_index > OffensivePowersEnum.values().length - 1) p1_offensivePower_index = 0;
        }

        else if(selectedOption == SET_P1_DEFENSIVE_POWER_OPTION.ID){
            p1_defensivePower_index++;
            if (p1_defensivePower_index > DefensivePowersEnum.values().length - 1) p1_defensivePower_index = 0;
        }

        else if(selectedOption == P1_READY_OPTION.ID){
            ready = true;
            selectedOption = 4;
        }

        else if(selectedOption == SET_P2_OFFENSIVE_POWER_OPTION.ID){
            p2_offensivePower_index++;
            if (p2_offensivePower_index > OffensivePowersEnum.values().length - 1) p2_offensivePower_index = 0;
        }

        else if(selectedOption == SET_P2_DEFENSIVE_POWER_OPTION.ID){
            p2_defensivePower_index++;
            if (p2_defensivePower_index > DefensivePowersEnum.values().length - 1) p2_defensivePower_index = 0;
        }

        else if(selectedOption == P2_READY_OPTION.ID){
            gl.setGameState(new Match_pvp(gl));
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
        int y = 300;
        int y_offset = 60;

        //PLAYER NAME
        g2d.drawString(player1Name, Utils.xForCenteredText(g2d, GamePanel.WIDTH ,player1Name), y);

        y = SET_P1_OFFENSIVE_POWER_OPTION.draw(x, y, value_x, y_offset, SET_P1_OFFENSIVE_POWER_OPTION.isSelected(selectedOption), OffensivePowersEnum.values()[p1_offensivePower_index].name, g2d);

        y = SET_P1_DEFENSIVE_POWER_OPTION.draw(x, y, value_x, y_offset, SET_P1_DEFENSIVE_POWER_OPTION.isSelected(selectedOption), DefensivePowersEnum.values()[p1_defensivePower_index].name, g2d);

        y = P1_READY_OPTION.draw(x, y, value_x, y_offset, P1_READY_OPTION.isSelected(selectedOption), ready, g2d);
        
        y = 600;

        //PLAYER NAME
        g2d.drawString(player2Name, Utils.xForCenteredText(g2d, GamePanel.WIDTH ,player2Name), y);

        y = SET_P2_OFFENSIVE_POWER_OPTION.draw(x, y, value_x, y_offset, SET_P2_OFFENSIVE_POWER_OPTION.isSelected(selectedOption), OffensivePowersEnum.values()[p2_offensivePower_index].name, g2d);

        y = SET_P2_DEFENSIVE_POWER_OPTION.draw(x, y, value_x, y_offset, SET_P2_DEFENSIVE_POWER_OPTION.isSelected(selectedOption), DefensivePowersEnum.values()[p2_defensivePower_index].name, g2d);

        y = P2_READY_OPTION.draw(x, y, value_x, y_offset, P2_READY_OPTION.isSelected(selectedOption), false, g2d);
    }
}
