package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.MenusHandling.Options.ValueOption;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Display the keybindings and make the player modify them
 * @see KeyBindingsMenu#P1_LEFT_KEY_OPTION
 * @see KeyBindingsMenu#P1_RIGHT_KEY_OPTION
 * @see KeyBindingsMenu#P1_OFFENSIVE_POWER_KEY_OPTION
 * @see KeyBindingsMenu#P1_DEFENSIVE_POWER_KEY_OPTION
 * @see KeyBindingsMenu#P2_LEFT_KEY_OPTION
 * @see KeyBindingsMenu#P2_RIGHT_KEY_OPTION
 * @see KeyBindingsMenu#P2_OFFENSIVE_POWER_KEY_OPTION
 * @see KeyBindingsMenu#P2_DEFENSIVE_POWER_KEY_OPTION
 * @see KeyBindingsMenu#BACK
 */
public class KeyBindingsMenu extends Menu{

    //KEY BINDINGS MENU OPTIONS
    /**
     * Make the players choose the key for p1 left
     * @see KeyHandler#p1Left_key
     * @see ValueOption
     */
    public static final ValueOption P1_LEFT_KEY_OPTION = new ValueOption("PLAYER 1 LEFT KEY", 1);

    /**
     * Make the players choose the key for p1 right
     * @see KeyHandler#p1Right_key
     * @see ValueOption
     */
    public static final ValueOption P1_RIGHT_KEY_OPTION = new ValueOption("PLAYER 1 RIGHT KEY", 2);

    /**
     * Make the players choose the key for p1 offensive power
     * @see KeyHandler#p1OffensivePower_key
     * @see ValueOption
     */
    public static final ValueOption P1_OFFENSIVE_POWER_KEY_OPTION = new ValueOption("PLAYER 1 OFFENSIVE POWER KEY", 3);

    /**
     * Make the players choose the key for p1 defensive power
     * @see KeyHandler#p1DefensivePower_key
     * @see ValueOption
     */
    public static final ValueOption P1_DEFENSIVE_POWER_KEY_OPTION = new ValueOption("PLAYER 1 DEFENSIVE POWER KEY", 4);

    /**
     * Make the players choose the key for p2 left
     * @see KeyHandler#p2Left_key
     * @see ValueOption
     */
    public static final ValueOption P2_LEFT_KEY_OPTION = new ValueOption("PLAYER 2 LEFT KEY", 5);

    /**
     * Make the players choose the key for p2 right
     * @see KeyHandler#p2Right_key
     * @see ValueOption
     */
    public static final ValueOption P2_RIGHT_KEY_OPTION = new ValueOption("PLAYER 2 RIGHT KEY", 6);

    /**
     * Make the players choose the key for p2 offensive power
     * @see KeyHandler#p2OffensivePower_key
     * @see ValueOption
     */
    public static final ValueOption P2_OFFENSIVE_POWER_KEY_OPTION = new ValueOption("PLAYER 2 OFFENSIVE POWER KEY", 7);

    /**
     * Make the players choose the key for p2 offensive power
     * @see KeyHandler#p2OffensivePower_key
     * @see ValueOption
     */
    public static final ValueOption P2_DEFENSIVE_POWER_KEY_OPTION = new ValueOption("PLAYER 2 DEFENSIVE POWER KEY", 8);

    /**
     * Return to the {@link SettingsMenu settings menu}
     * @see CenteredOption
     */
    public static final CenteredOption BACK = new CenteredOption("BACK", 9);
    
    //BOOLEANS FOR KEY LISTENING
    public static boolean isP1LeftKeyListening = false;
    public static boolean isP1RightKeyListening = false;
    public static boolean isP1OffensivePowerKeyListening = false;
    public static boolean isP1DefensivePowerKeyListening = false;
    
    public static boolean isP2LeftKeyListening = false;
    public static boolean isP2RightKeyListening = false;
    public static boolean isP2OffensivePowerKeyListening = false;
    public static boolean isP2DefensivePowerKeyListening = false;
    
    public KeyBindingsMenu() {
        super(9);
    }

    @Override
    public void clickOption() {
        
        if(selectedOption == P1_LEFT_KEY_OPTION.ID) isP1LeftKeyListening = true;
        else if(selectedOption == P1_RIGHT_KEY_OPTION.ID) isP1RightKeyListening = true;
        else if(selectedOption == P1_OFFENSIVE_POWER_KEY_OPTION.ID) isP1OffensivePowerKeyListening = true;
        else if(selectedOption == P1_DEFENSIVE_POWER_KEY_OPTION.ID) isP1DefensivePowerKeyListening = true;
        
        else if(selectedOption == P2_LEFT_KEY_OPTION.ID) isP2LeftKeyListening = true;
        else if(selectedOption == P2_RIGHT_KEY_OPTION.ID) isP2RightKeyListening = true;
        else if(selectedOption == P2_OFFENSIVE_POWER_KEY_OPTION.ID) isP2OffensivePowerKeyListening = true;
        else if(selectedOption == P2_DEFENSIVE_POWER_KEY_OPTION.ID) isP2DefensivePowerKeyListening = true;

        else if(selectedOption == BACK.ID) MyFrame.gameLogic.setGameState(GameLogic.GameStates.SETTINGS_MENU);

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
        
        y = P1_LEFT_KEY_OPTION.draw(x, y, value_x, 0, P1_LEFT_KEY_OPTION.isSelected(selectedOption),
                (isP1LeftKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1Left_key)), g2d);
        
        y = P1_RIGHT_KEY_OPTION.draw(x, y, value_x, y_offset, P1_RIGHT_KEY_OPTION.isSelected(selectedOption),
                (isP1RightKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1Right_key)), g2d);
                
        y = P1_OFFENSIVE_POWER_KEY_OPTION.draw(x, y, value_x, y_offset, P1_OFFENSIVE_POWER_KEY_OPTION.isSelected(selectedOption),
                (isP1OffensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1OffensivePower_key)), g2d);
        
        y = P1_DEFENSIVE_POWER_KEY_OPTION.draw(x, y, value_x, y_offset, P1_DEFENSIVE_POWER_KEY_OPTION.isSelected(selectedOption),
                (isP1DefensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1DefensivePower_key)), g2d);

        y = P2_LEFT_KEY_OPTION.draw(x, y, value_x, y_offset, P2_LEFT_KEY_OPTION.isSelected(selectedOption),
                (isP2LeftKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2Left_key)), g2d);

        y = P2_RIGHT_KEY_OPTION.draw(x, y, value_x, y_offset, P2_RIGHT_KEY_OPTION.isSelected(selectedOption),
                (isP2RightKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2Right_key)), g2d);

        y = P2_OFFENSIVE_POWER_KEY_OPTION.draw(x, y, value_x, y_offset, P2_OFFENSIVE_POWER_KEY_OPTION.isSelected(selectedOption),
                (isP2OffensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2OffensivePower_key)), g2d);

        y = P2_DEFENSIVE_POWER_KEY_OPTION.draw(x, y, value_x, y_offset, P2_DEFENSIVE_POWER_KEY_OPTION.isSelected(selectedOption),
                (isP2DefensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2DefensivePower_key)), g2d);

        y = BACK.draw(y, y_offset, BACK.isSelected(selectedOption), g2d);
    }
}
