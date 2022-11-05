package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Menus.KeyBindingsMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    //KEYBINDINGS
    public static int p1Left_key = KeyEvent.VK_A;
    public static  int p1Right_key = KeyEvent.VK_D;

    public static  int p1OffensivePower_key = KeyEvent.VK_W;
    public static int p1DefensivePower_key = KeyEvent.VK_S;


    public static int p2Left_key = KeyEvent.VK_LEFT;
    public static int p2Right_key = KeyEvent.VK_RIGHT;

    public static int p2OffensivePower_key = KeyEvent.VK_UP;
    public static int p2DefensivePower_key = KeyEvent.VK_DOWN;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            if(code == p1Left_key) MyFrame.gameLogic.p1.setLeftPressed(true);
            else if(code == p1Right_key) MyFrame.gameLogic.p1.setRightPressed(true);

            else if(code == p2Left_key) MyFrame.gameLogic.p2.setLeftPressed(true);
            else if(code == p2Right_key) MyFrame.gameLogic.p2.setRightPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        //MATCH
        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){

            if(code == p1Left_key) MyFrame.gameLogic.p1.setLeftPressed(false);
            else if(code == p1Right_key) MyFrame.gameLogic.p1.setRightPressed(false);

            else if(code == p2Left_key) MyFrame.gameLogic.p2.setLeftPressed(false);
            else if(code == p2Right_key) MyFrame.gameLogic.p2.setRightPressed(false);

            else if(code == p1DefensivePower_key) MyFrame.gameLogic.p1.activateDefensivePower();
            else if(code == p1OffensivePower_key) MyFrame.gameLogic.p1.activateOffensivePower();

            else if(code == p2DefensivePower_key) MyFrame.gameLogic.p2.activateDefensivePower();
            else if(code == p2OffensivePower_key) MyFrame.gameLogic.p2.activateOffensivePower();

            else if(code == KeyEvent.VK_P) MyFrame.gameLogic.togglePause();

            else if(code == KeyEvent.VK_ESCAPE) MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);

            else if(code == KeyEvent.VK_R) MyFrame.gameLogic.startMatch();
        }

        //FINISH SCREEN
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.FINISH){
            switch (code){
                case KeyEvent.VK_SPACE -> MyFrame.gameLogic.startMatch();
                case KeyEvent.VK_ESCAPE -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
            }
        }

        //TITLE SCREEN
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.TITLE_SCREEN){
            switch (code){
                case KeyEvent.VK_UP, KeyEvent.VK_W -> GamePanel.titleScreen.previousOption();
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> GamePanel.titleScreen.nextOption();
                case KeyEvent.VK_ENTER, KeyEvent.VK_E -> GamePanel.titleScreen.clickOption();
            }
        }

        //SETTINGS MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SETTINGS_MENU){
            switch (code){
                case KeyEvent.VK_UP, KeyEvent.VK_W -> GamePanel.settingsMenu.previousOption();
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> GamePanel.settingsMenu.nextOption();
                case KeyEvent.VK_ENTER, KeyEvent.VK_E -> GamePanel.settingsMenu.clickOption();
            }
        }

        //SELECTING POWER MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SELECTING_POWERS){

            if(!GamePanel.p1PowerSelectionMenu.isReady()){
                switch (code) {
                    case KeyEvent.VK_W -> GamePanel.p1PowerSelectionMenu.previousOption();
                    case KeyEvent.VK_S -> GamePanel.p1PowerSelectionMenu.nextOption();
                    case KeyEvent.VK_E -> GamePanel.p1PowerSelectionMenu.clickOption();
                }
            }

            if(!GamePanel.p2PowerSelectionMenu.isReady()) {
                switch (code) {
                    case KeyEvent.VK_UP -> GamePanel.p2PowerSelectionMenu.previousOption();
                    case KeyEvent.VK_DOWN -> GamePanel.p2PowerSelectionMenu.nextOption();
                    case KeyEvent.VK_ENTER -> GamePanel.p2PowerSelectionMenu.clickOption();
                }
            }

            if(code == KeyEvent.VK_ESCAPE) MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
        }

        //KEY BINDINGS MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.KEY_BINDINGS_MENU){

            //P1 RIGHT KEY BINDING
            if(KeyBindingsMenu.isP1RightKeyListening){
                p1Right_key = code;
                KeyBindingsMenu.isP1RightKeyListening = false;
            }
            //P1 LEFT KEY BINDING
            else if(KeyBindingsMenu.isP1LeftKeyListening){
                p1Left_key = code;
                KeyBindingsMenu.isP1LeftKeyListening = false;
            }
            //P1 DEFENSIVE POWER KEY BINDING
            else if(KeyBindingsMenu.isP1DefensivePowerKeyListening){
                p1DefensivePower_key = code;
                KeyBindingsMenu.isP1DefensivePowerKeyListening = false;
            }
            //P1 OFFENSIVE POWER KEY BINDING
            else if(KeyBindingsMenu.isP1OffensivePowerKeyListening){
                p1OffensivePower_key = code;
                KeyBindingsMenu.isP1OffensivePowerKeyListening = false;
            }
            //P2 RIGHT KEY BINDING
            else if(KeyBindingsMenu.isP2RightKeyListening){
                p2Right_key = code;
                KeyBindingsMenu.isP2RightKeyListening = false;
            }
            //P2 LEFT KEY BINDING
            else if(KeyBindingsMenu.isP2LeftKeyListening){
                p2Left_key = code;
                KeyBindingsMenu.isP2LeftKeyListening = false;
            }
            //P2 DEFENSIVE POWER KEY BINDING
            else if(KeyBindingsMenu.isP2DefensivePowerKeyListening){
                p2DefensivePower_key = code;
                KeyBindingsMenu.isP2DefensivePowerKeyListening = false;
            }
            //P2 OFFENSIVE POWER KEY BINDING
            else if(KeyBindingsMenu.isP2OffensivePowerKeyListening){
                p2OffensivePower_key = code;
                KeyBindingsMenu.isP2OffensivePowerKeyListening = false;
            }

            //MENU NAVIGATION
            else{
                switch (code){
                    case KeyEvent.VK_UP, KeyEvent.VK_W -> GamePanel.keyBindingsMenu.previousOption();
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S -> GamePanel.keyBindingsMenu.nextOption();
                    case KeyEvent.VK_E, KeyEvent.VK_ENTER -> GamePanel.keyBindingsMenu.clickOption();
                }
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PAUSE){
            if(code == KeyEvent.VK_ESCAPE) MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
            else if(code == KeyEvent.VK_R) MyFrame.gameLogic.startMatch();
            else if(code == KeyEvent.VK_P) MyFrame.gameLogic.togglePause();
        }
    }
}
