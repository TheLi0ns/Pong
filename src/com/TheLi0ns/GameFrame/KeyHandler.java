package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.GameModes.*;
import com.TheLi0ns.Utility.Sound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    //KEYBINDINGS
    public static int p1Left_key = KeyEvent.VK_A;
    public static int p1Right_key = KeyEvent.VK_D;

    public static int p1OffensivePower_key = KeyEvent.VK_W;
    public static int p1DefensivePower_key = KeyEvent.VK_S;


    public static int p2Left_key = KeyEvent.VK_LEFT;
    public static int p2Right_key = KeyEvent.VK_RIGHT;

    public static int p2OffensivePower_key = KeyEvent.VK_UP;
    public static int p2DefensivePower_key = KeyEvent.VK_DOWN;

    static PlayerKey playerKeyListened;
    static boolean playerKeyListening = false;

    public enum PlayerKey {LEFT_P1, RIGHT_P1, OFFENSIVE_POWER_P1, DEFENSIVE_POWER_P1, LEFT_P2, RIGHT_P2, OFFENSIVE_POWER_P2, DEFENSIVE_POWER_P2}

    private final GameLogic gl;

    public KeyHandler(GameLogic gl){
        this.gl = gl;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //PLAYING
        if(gl.getGameState() == GameLogic.GameStates.PLAYING){
            GameMode_super gameMode = gl.getGameMode();

            if (gameMode instanceof Match_pve matchPve) {

                if (code == p1Left_key) matchPve.getPlayer().setLeftPressed(true);
                else if (code == p1Right_key) matchPve.getPlayer().setRightPressed(true);

                else if (code == p1DefensivePower_key) matchPve.getPlayer().activateDefensivePower();
                else if (code == p1OffensivePower_key) matchPve.getPlayer().activateOffensivePower();

            } else if (gameMode instanceof Match_pvp matchPvp) {

                if (code == p1Left_key) matchPvp.getP1().setLeftPressed(true);
                else if (code == p1Right_key) matchPvp.getP1().setRightPressed(true);

                else if (code == p2Left_key) matchPvp.getP2().setLeftPressed(true);
                else if (code == p2Right_key) matchPvp.getP2().setRightPressed(true);

                else if (code == p1DefensivePower_key) matchPvp.getP1().activateDefensivePower();
                else if (code == p1OffensivePower_key) matchPvp.getP1().activateOffensivePower();

                else if (code == p2DefensivePower_key) matchPvp.getP2().activateDefensivePower();
                else if (code == p2OffensivePower_key) matchPvp.getP2().activateOffensivePower();

            } else if (gameMode instanceof Dribble dribble) {

                if (code == p1Left_key) dribble.getPlayer().setLeftPressed(true);
                else if (code == p1Right_key) dribble.getPlayer().setRightPressed(true);

            } else if (gameMode instanceof BossFights bossFights) {

                if (code == p1Left_key) bossFights.getFighter().setLeftPressed(true);
                else if (code == p1Right_key) bossFights.getFighter().setRightPressed(true);
                else if (code == p1DefensivePower_key || code == KeyEvent.VK_SPACE)
                    bossFights.getFighter().parry();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        //PLAYING
        if(gl.getGameState() == GameLogic.GameStates.PLAYING){

            GameMode_super minigame = gl.getGameMode();

            if (minigame instanceof Match_pve matchPve) {

                if (code == p1Left_key) matchPve.getPlayer().setLeftPressed(false);
                else if (code == p1Right_key) matchPve.getPlayer().setRightPressed(false);

                else if (code == KeyEvent.VK_P) minigame.togglePause();

                else if (code == KeyEvent.VK_ESCAPE) {
                    gl.backToMainMenu();
                    Sound.stop();
                } else if (code == KeyEvent.VK_R) gl.setGameMode(new Match_pve(gl));

                else if(matchPve.isFinished() && code == KeyEvent.VK_SPACE) gl.setGameMode(new Match_pve(gl));

            } else if (minigame instanceof Match_pvp matchPvp) {

                if (code == p1Left_key) matchPvp.getP1().setLeftPressed(false);
                else if (code == p1Right_key) matchPvp.getP1().setRightPressed(false);

                else if (code == p2Left_key) matchPvp.getP2().setLeftPressed(false);
                else if (code == p2Right_key) matchPvp.getP2().setRightPressed(false);

                else if (code == KeyEvent.VK_P) minigame.togglePause();

                else if (code == KeyEvent.VK_ESCAPE) {
                    gl.backToMainMenu();
                    Sound.stop();
                } else if (code == KeyEvent.VK_R) gl.setGameMode(new Match_pvp(gl));

                else if (matchPvp.isFinished() && code == KeyEvent.VK_SPACE) gl.setGameMode(new Match_pvp(gl));

            } else if (minigame instanceof Dribble dribble) {

                if (code == p1Left_key) dribble.getPlayer().setLeftPressed(false);
                else if (code == p1Right_key) dribble.getPlayer().setRightPressed(false);

                else if (code == KeyEvent.VK_P) minigame.togglePause();

                else if (code == KeyEvent.VK_ESCAPE) {
                    gl.backToMainMenu();
                    Sound.stop();
                } else if (dribble.isFinished() && code == KeyEvent.VK_SPACE) {
                    gl.setGameMode(new Dribble(gl));
                }

            } else if (minigame instanceof BossFights bossFights) {

                if (code == p1Left_key) bossFights.getFighter().setLeftPressed(false);
                else if (code == p1Right_key) bossFights.getFighter().setRightPressed(false);

                else if (code == KeyEvent.VK_ESCAPE) {
                    gl.backToMainMenu();
                    Sound.stop();
                    Sound.stopBackgroundMusic();
                } else if (bossFights.isFinished() && !bossFights.hasPlayerWon() && code == KeyEvent.VK_SPACE) {
                    gl.setGameMode(new BossFights(gl));
                }
                else if (code == KeyEvent.VK_R) gl.setGameMode(new BossFights(gl));

                else if (code == KeyEvent.VK_P) minigame.togglePause();
            }
        }

        //CUTSCENE
        else if(gl.getGameState() == GameLogic.GameStates.CUTSCENE){
            if (code == KeyEvent.VK_ENTER) CutsceneHandler.skip();
        }

        //MENU
        else if(gl.getGameState() == GameLogic.GameStates.MENU) {
            //KEY BINDINGS
            if(playerKeyListening){
                if(!checkDuplicateKey(code)){
                    switch (playerKeyListened){
                        case LEFT_P1 -> p1Left_key = code;
                        case LEFT_P2 -> p2Left_key = code;
                        case RIGHT_P1 -> p1Right_key = code;
                        case RIGHT_P2 -> p2Right_key = code;
                        case DEFENSIVE_POWER_P1 -> p1DefensivePower_key = code;
                        case DEFENSIVE_POWER_P2 -> p2DefensivePower_key = code;
                        case OFFENSIVE_POWER_P1 -> p1OffensivePower_key = code;
                        case OFFENSIVE_POWER_P2 -> p2OffensivePower_key = code;
                    }
                }
                playerKeyListening = false;
                playerKeyListened = null;
            }

            switch (code) {
                case KeyEvent.VK_UP, KeyEvent.VK_W -> gl.getMenu().previousOption();
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> gl.getMenu().nextOption();
                case KeyEvent.VK_ENTER, KeyEvent.VK_E -> gl.getMenu().clickOption();
                case KeyEvent.VK_ESCAPE -> gl.backToMainMenu();
            }
        }
    }

    public static void listening(PlayerKey playerKey){
        playerKeyListened = playerKey;
        playerKeyListening = true;
    }

    private static boolean checkDuplicateKey(int code){
        return p1Left_key == code || p1Right_key == code || p1DefensivePower_key == code || p1OffensivePower_key == code ||
                p2Left_key == code || p2Right_key == code || p2DefensivePower_key == code || p2OffensivePower_key == code;
    }

    public static PlayerKey getKeyListened(){
        return playerKeyListened;
    }
}
