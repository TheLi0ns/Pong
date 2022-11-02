package com.TheLi0ns.Logic;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Menus.SettingsMenu;

import java.util.Random;

/**
 * Creates the game loop
 * Manages the game loop update
 * Contains the settings
 */
public class GameLogic implements Runnable{

    public Player p1;
    public Player p2;
    public Ball ball;

    private int pointToWin = 15;
    public static final int MAX_POINTS = 30;

    private final int FPS = 90;

    private GameStates gameState = GameStates.TITLE_SCREEN;

    private boolean arePowersEnabled;
    private boolean isDefensivePowerRechargeable;
    private boolean isOffensivePowerRechargeable;

    String finish = null;

    public enum GameStates{
        TITLE_SCREEN,
        SETTINGS_MENU,
        PAUSE,
        FINISH,
        PLAYING
    }

    public GameLogic(){
        Thread gameLoop = new Thread(this);
        gameLoop.start();

    }

    public void startMatch(){
        gameState = GameStates.PAUSE;

        p1 = new Player(391, 909, 6);
        p2 = new Player(391, 53, 6);
        ball = new Ball(472, 468, genRandomxVelocity(), 6);

        isOffensivePowerRechargeable = pointToWin > 6;
        isDefensivePowerRechargeable = pointToWin > 3;

        setUpPowers();

        gameState = GameStates.PLAYING;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            if(gameState == GameStates.PLAYING) {
                update();
            }

            MyFrame.gamePanel.repaint();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update(){

        p1.update();

        p2.update();

        ball.update(p1, p2);

        scoreUpdate();
    }

    private void scoreUpdate(){
        switch(ball.checkScored()){
            case "UP" -> {
                p1.hasScored();
                if(arePowersEnabled) p2.ChargingPowers();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), 5);
                }else{
                    finish();
                }
            }
            case "DOWN" -> {
                p2.hasScored();
                if(arePowersEnabled) p1.ChargingPowers();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), -5);
                }else{
                    finish();
                }
            }
        }
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public void togglePause() {
        if(gameState == GameStates.PAUSE) gameState = GameStates.PLAYING;
        else if(gameState == GameStates.PLAYING) gameState = GameStates.PAUSE;
    }

    public boolean hasSomeoneWins(){
        return p1.hasWon() || p2.hasWon();
    }

    public void finish(){
        gameState = GameStates.FINISH;
        if(p1.hasWon()){
            finish = "PLAYER 1\n  WINS";
        }else{
            finish = "PLAYER 2\n  WINS";
        }
    }

    public GameStates getGameState(){
        return gameState;
    }

    public String getFinish() {
        return finish;
    }

    private int genRandomxVelocity(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(3, 5) * (random.nextBoolean() ? 1 : -1);
    }


    public void setUpPowers(){
        if(arePowersEnabled){
            if(isOffensivePowerRechargeable){
                p1.setOffensivePower(SettingsMenu.getP1OffensivePower(), p2);
                p2.setOffensivePower(SettingsMenu.getP2OffensivePower(), p1);
            }
            if(isDefensivePowerRechargeable){
                p1.setDefensivePower(SettingsMenu.getP1DefensivePower());
                p2.setDefensivePower(SettingsMenu.getP2DefensivePower());
            }
        }
    }

    public boolean arePowersEnabled() {
        return arePowersEnabled;
    }
    public void setArePowersEnabled(boolean arePowersEnabled) {
        this.arePowersEnabled = arePowersEnabled;
    }

    public void setPointToWin(int pointToWin){
        this.pointToWin = pointToWin;
    }

    /**
     * Asks the User how many points have to score to win the match
     */
    /*
    public void setPointToWin(){

        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(30);
        slider.setValue(1);
        slider.setPreferredSize(new Dimension(750, 100));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setFont(new Font("CALIBRI CORPO", Font.PLAIN, 15));
        slider.setValue(10);

        JOptionPane.showConfirmDialog(null, slider, "   POINTS TO WIN    ", JOptionPane.DEFAULT_OPTION);
        pointToWin = slider.getValue();
    }
    */

    public int getPointToWin() {
        return pointToWin;
    }

    public boolean isDefensivePowerRechargeable() {
        return isDefensivePowerRechargeable;
    }

    public boolean isOffensivePowerRechargeable() {
        return isOffensivePowerRechargeable;
    }

}
