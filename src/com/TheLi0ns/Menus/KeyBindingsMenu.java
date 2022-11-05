package com.TheLi0ns.Menus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Utils;

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
     */
    public static final int P1_LEFT_KEY_OPTION = 1;

    /**
     * Make the players choose the key for p1 right
     * @see KeyHandler#p1Right_key
     */
    public static final int P1_RIGHT_KEY_OPTION = 2;

    /**
     * Make the players choose the key for p1 offensive power
     * @see KeyHandler#p1OffensivePower_key
     */
    public static final int P1_OFFENSIVE_POWER_KEY_OPTION = 3;

    /**
     * Make the players choose the key for p1 defensive power
     * @see KeyHandler#p1DefensivePower_key
     */
    public static final int P1_DEFENSIVE_POWER_KEY_OPTION = 4;

    /**
     * Make the players choose the key for p2 left
     * @see KeyHandler#p2Left_key
     */
    public static final int P2_LEFT_KEY_OPTION = 5;

    /**
     * Make the players choose the key for p2 right
     * @see KeyHandler#p2Right_key
     */
    public static final int P2_RIGHT_KEY_OPTION = 6;

    /**
     * Make the players choose the key for p2 offensive power
     * @see KeyHandler#p2OffensivePower_key
     */
    public static final int P2_OFFENSIVE_POWER_KEY_OPTION = 7;

    /**
     * Make the players choose the key for p2 offensive power
     * @see KeyHandler#p2OffensivePower_key
     */
    public static final int P2_DEFENSIVE_POWER_KEY_OPTION = 8;

    /**
     * Return to the {@link SettingsMenu settings menu}
     */
    public static final int BACK = 9;
    
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
        switch (selectedOption){
            case P1_LEFT_KEY_OPTION -> isP1LeftKeyListening = true;
            case P1_RIGHT_KEY_OPTION -> isP1RightKeyListening = true;
            case P1_OFFENSIVE_POWER_KEY_OPTION -> isP1OffensivePowerKeyListening = true;
            case P1_DEFENSIVE_POWER_KEY_OPTION -> isP1DefensivePowerKeyListening = true;

            case P2_LEFT_KEY_OPTION -> isP2LeftKeyListening = true;
            case P2_RIGHT_KEY_OPTION -> isP2RightKeyListening = true;
            case P2_OFFENSIVE_POWER_KEY_OPTION -> isP2OffensivePowerKeyListening = true;
            case P2_DEFENSIVE_POWER_KEY_OPTION -> isP2DefensivePowerKeyListening = true;

            case BACK -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.SETTINGS_MENU);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 300;

        //PLAYER 1 LEFT KEY
        g2d.drawString("PLAYER 1 LEFT KEY", x, y);
        if (selectedOption == P1_LEFT_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP1LeftKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1Left_key)),
                700, y);

        //PLAYER 1 RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER 1 RIGHT KEY", x, y);
        if (selectedOption == P1_RIGHT_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP1RightKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1Right_key)),
                700, y);

        //PLAYER 1 OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 1 OFFENSIVE POWER KEY", x, y);
        if (selectedOption == P1_OFFENSIVE_POWER_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP1OffensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1OffensivePower_key)),
                700, y);

        //PLAYER 1 DEFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 1 DEFENSIVE POWER KEY", x, y);
        if (selectedOption == P1_DEFENSIVE_POWER_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP1DefensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p1DefensivePower_key)),
                700, y);

        //PLAYER 2 LEFT KEY
        y += 60;
        g2d.drawString("PLAYER 2 LEFT KEY", x, y);
        if (selectedOption == P2_LEFT_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP2LeftKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2Left_key)),
                700, y);

        //PLAYER 2 RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER 2 RIGHT KEY", x, y);
        if (selectedOption == P2_RIGHT_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP2RightKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2Right_key)),
                700, y);

        //PLAYER 2 OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 2 OFFENSIVE POWER KEY", x, y);
        if (selectedOption == P2_OFFENSIVE_POWER_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP2OffensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2OffensivePower_key)),
                700, y);

        //PLAYER 2 DEFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 2 DEFENSIVE POWER KEY", x, y);
        if (selectedOption == P2_DEFENSIVE_POWER_KEY_OPTION) g2d.drawString(">", x - 30, y);
        g2d.drawString(
                (isP2DefensivePowerKeyListening ? "?" : KeyEvent.getKeyText(KeyHandler.p2DefensivePower_key)),
                700, y);

        //BACK
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "BACK");
        g2d.drawString("BACK", x, y);
        if (selectedOption == BACK) g2d.drawString(">", x - 30, y);
    }
}
