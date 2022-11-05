package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            switch(code){
                case KeyEvent.VK_A -> MyFrame.gameLogic.p1.setLeftPressed(true);
                case KeyEvent.VK_D -> MyFrame.gameLogic.p1.setRightPressed(true);

                case KeyEvent.VK_LEFT -> MyFrame.gameLogic.p2.setLeftPressed(true);
                case KeyEvent.VK_RIGHT -> MyFrame.gameLogic.p2.setRightPressed(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            switch(code){
                case KeyEvent.VK_A -> MyFrame.gameLogic.p1.setLeftPressed(false);
                case KeyEvent.VK_D -> MyFrame.gameLogic.p1.setRightPressed(false);

                case KeyEvent.VK_S -> MyFrame.gameLogic.p1.activateDefensivePower();
                case KeyEvent.VK_W -> MyFrame.gameLogic.p1.activateOffensivePower();


                case KeyEvent.VK_LEFT -> MyFrame.gameLogic.p2.setLeftPressed(false);
                case KeyEvent.VK_RIGHT -> MyFrame.gameLogic.p2.setRightPressed(false);

                case KeyEvent.VK_DOWN -> MyFrame.gameLogic.p2.activateDefensivePower();
                case KeyEvent.VK_UP -> MyFrame.gameLogic.p2.activateOffensivePower();

                case KeyEvent.VK_P -> MyFrame.gameLogic.togglePause();

                case KeyEvent.VK_ESCAPE -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);

                case KeyEvent.VK_R -> MyFrame.gameLogic.startMatch();
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.FINISH){
            switch (code){
                case KeyEvent.VK_SPACE -> MyFrame.gameLogic.startMatch();
                case KeyEvent.VK_ESCAPE -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.TITLE_SCREEN){
            switch (code){
                case KeyEvent.VK_UP, KeyEvent.VK_W -> GamePanel.titleScreen.previousOption();
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> GamePanel.titleScreen.nextOption();
                case KeyEvent.VK_ENTER, KeyEvent.VK_E -> GamePanel.titleScreen.clickOption();
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SETTINGS_MENU){
            switch (code){
                case KeyEvent.VK_UP, KeyEvent.VK_W -> GamePanel.settingsMenu.previousOption();
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> GamePanel.settingsMenu.nextOption();
                case KeyEvent.VK_ENTER, KeyEvent.VK_E -> GamePanel.settingsMenu.clickOption();
            }
        }

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
    }
}
