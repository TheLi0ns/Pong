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

        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            switch(e.getKeyCode()){
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
            switch (e.getKeyCode()){
                case KeyEvent.VK_SPACE -> MyFrame.gameLogic.startMatch();
                case KeyEvent.VK_ESCAPE -> MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.TITLE_SCREEN){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP -> GamePanel.titleScreen.previousOption();
                case KeyEvent.VK_DOWN -> GamePanel.titleScreen.nextOption();
                case KeyEvent.VK_ENTER -> GamePanel.titleScreen.clickOption();
            }
        }

        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SETTINGS_MENU){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP -> GamePanel.settingsMenu.previousOption();
                case KeyEvent.VK_DOWN -> GamePanel.settingsMenu.nextOption();
                case KeyEvent.VK_ENTER -> GamePanel.settingsMenu.clickOption();
            }
        }
    }
}
