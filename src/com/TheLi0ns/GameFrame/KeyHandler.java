package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.Logic.MiniGames.Dribble;
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

        if(gl.getGameState() == GameLogic.GameStates.PLAYING){
            switch(gl.getGameMode()){
                case PVE -> {
                    if(code == p1Left_key) gl.p1.setLeftPressed(true);
                    else if(code == p1Right_key) gl.p1.setRightPressed(true);

                    else if(code == p1DefensivePower_key) gl.p1.activateDefensivePower();
                    else if(code == p1OffensivePower_key) gl.p1.activateOffensivePower();
                }

                case PVP -> {
                    if(code == p1Left_key) gl.p1.setLeftPressed(true);
                    else if(code == p1Right_key) gl.p1.setRightPressed(true);

                    else if(code == p2Left_key) gl.p2.setLeftPressed(true);
                    else if(code == p2Right_key) gl.p2.setRightPressed(true);

                    else if(code == p1DefensivePower_key) gl.p1.activateDefensivePower();
                    else if(code == p1OffensivePower_key) gl.p1.activateOffensivePower();

                    else if(code == p2DefensivePower_key) gl.p2.activateDefensivePower();
                    else if(code == p2OffensivePower_key) gl.p2.activateOffensivePower();
                }

                case DRIBBLE -> {
                    Dribble current_minigame = (Dribble) gl.getMiniGame();

                    if(code == p1Left_key) current_minigame.getPlayer().setLeftPressed(true);
                    else if(code == p1Right_key) current_minigame.getPlayer().setRightPressed(true);
                }

                case BOSS_FIGHTS -> {
                    BossFights current_minigame = (BossFights) gl.getMiniGame();

                    if(code == p1Left_key) current_minigame.getFighter().setLeftPressed(true);
                    else if(code == p1Right_key) current_minigame.getFighter().setRightPressed(true);
                    else if(code == p1DefensivePower_key || code == KeyEvent.VK_SPACE)current_minigame.getFighter().parry();
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        //MATCH
        if(gl.getGameState() == GameLogic.GameStates.PLAYING){

            switch (gl.getGameMode()){
                case PVE ->{
                    if(code == p1Left_key) gl.p1.setLeftPressed(false);
                    else if(code == p1Right_key) gl.p1.setRightPressed(false);

                    else if(code == KeyEvent.VK_P) gl.togglePause();

                    else if(code == KeyEvent.VK_ESCAPE) {
                        gl.backToMainMenu();
                        Sound.stop();
                    }

                    else if(code == KeyEvent.VK_R) gl.startMatch();
                }

                case PVP -> {
                    if(code == p1Left_key) gl.p1.setLeftPressed(false);
                    else if(code == p1Right_key) gl.p1.setRightPressed(false);

                    else if(code == p2Left_key) gl.p2.setLeftPressed(false);
                    else if(code == p2Right_key) gl.p2.setRightPressed(false);

                    else if(code == KeyEvent.VK_P) gl.togglePause();

                    else if(code == KeyEvent.VK_ESCAPE) {
                        gl.backToMainMenu();
                        Sound.stop();
                    }

                    else if(code == KeyEvent.VK_R) gl.startMatch();
                }

                case DRIBBLE -> {
                    Dribble current_minigame = (Dribble) gl.getMiniGame();

                    if(code == p1Left_key) current_minigame.getPlayer().setLeftPressed(false);
                    else if(code == p1Right_key) current_minigame.getPlayer().setRightPressed(false);

                    else if(code == KeyEvent.VK_P) gl.togglePause();

                    else if(code == KeyEvent.VK_ESCAPE) {
                        gl.backToMainMenu();
                        Sound.stop();
                    }

                    else if(current_minigame.isFinished() && code == KeyEvent.VK_SPACE){
                        gl.setMiniGame(new Dribble(gl));
                    }
                }

                case BOSS_FIGHTS -> {
                    BossFights current_minigame = (BossFights) gl.getMiniGame();

                    if(code == p1Left_key) current_minigame.getFighter().setLeftPressed(false);
                    else if(code == p1Right_key) current_minigame.getFighter().setRightPressed(false);

                    else if(code == KeyEvent.VK_ESCAPE) {
                        gl.backToMainMenu();
                        Sound.stop();
                        Sound.stopBackgroundMusic();
                    }

                    else if(current_minigame.isFinished() && !current_minigame.hasPlayerWon() && code == KeyEvent.VK_SPACE){
                        gl.setMiniGame(new BossFights(gl));
                    }
                }
            }
        }

        //CUTSCENE
        else if(gl.getGameState() == GameLogic.GameStates.CUTSCENE){
            switch (code){
                case KeyEvent.VK_ENTER -> CutsceneHandler.skip();
            }
        }

        //FINISH SCREEN
        else if(gl.getGameState() == GameLogic.GameStates.FINISH){
            switch (code){
                case KeyEvent.VK_SPACE -> gl.startMatch();
                case KeyEvent.VK_ESCAPE -> gl.backToMainMenu();
            }
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

        else if(gl.getGameState() == GameLogic.GameStates.PAUSE){
            if(code == KeyEvent.VK_ESCAPE) gl.backToMainMenu();
            else if(code == KeyEvent.VK_R) gl.startMatch();
            else if(code == KeyEvent.VK_P) gl.togglePause();
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
