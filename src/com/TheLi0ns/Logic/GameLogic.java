package com.TheLi0ns.Logic;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;

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

    private int pointsToWin = 15;
    public static final int MAX_POINTS = 30;

    private final int FPS = 90;

    private boolean arePowersEnabled = true;

    /**
     * The defensive power is rechargeable if the points to win
     * are greater than 3 the charge of defensive powers
     */
    private boolean isDefensivePowerRechargeable;

    /**
     * The offensive power is rechargeable if the points to win
     * are greater than 5 the charge of offensive powers
     */
    private boolean isOffensivePowerRechargeable;

    /**
     * The string that will be displayed
     * when someone wins
     */
    String finish = null;

    private GameStates gameState = GameStates.TITLE_SCREEN;

    public enum GameStates{
        TITLE_SCREEN,
        SETTINGS_MENU,
        PAUSE,
        FINISH,
        SELECTING_POWERS,
        PLAYING
    }

    public GameLogic(){
        Thread gameLoop = new Thread(this);
        gameLoop.start();

    }

    /**
     * Creates the player and the first ball
     * Calculate if the powers are rechargeable
     * {@link GameLogic#setUpPowers() Assigns to the players their powers}
     * And set the playing game state
     */
    public void startMatch(){
        gameState = GameStates.PAUSE;

        p1 = new Player(391, 909, 6);
        p2 = new Player(391, 53, 6);
        ball = new Ball(472, 468, genRandomxVelocity(), 6);

        isOffensivePowerRechargeable = pointsToWin > 5;
        isDefensivePowerRechargeable = pointsToWin > 3;

        if(isDefensivePowerRechargeable) arePowersEnabled = false;

        setUpPowers();

        gameState = GameStates.PLAYING;
    }

    /**
     * GAME LOOP
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            if(gameState == GameStates.SELECTING_POWERS &&
                    (GamePanel.p1PowerSelectionMenu.isReady() &&
                            GamePanel.p2PowerSelectionMenu.isReady())){
                startMatch();
                GamePanel.p1PowerSelectionMenu.setReady(false);
                GamePanel.p2PowerSelectionMenu.setReady(false);
            }


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

    /**
     * Checks if someone has scored
     * so gives the goal
     * and charge the opponent powers
     * and if someone has win {@link GameLogic#finish() finish the game}
     */
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

    /**
     * Make the gameState finish
     * Writes the {@link GameLogic#finish finish string}
     */
    public void finish(){
        if(p1.hasWon()){
            finish = "PLAYER 1\n  WINS";
        }else{
            finish = "PLAYER 2\n  WINS";
        }
        gameState = GameStates.FINISH;
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

    /**
     * Assign to the player the powers selected
     * in the power selection menu
     */
    public void setUpPowers(){
        if(arePowersEnabled){
            if(isOffensivePowerRechargeable){
                p1.setOffensivePower(GamePanel.p1PowerSelectionMenu.getSelectedOffensivePower(), p2);
                p2.setOffensivePower(GamePanel.p2PowerSelectionMenu.getSelectedOffensivePower(), p1);
            }
            if(isDefensivePowerRechargeable){
                p1.setDefensivePower(GamePanel.p1PowerSelectionMenu.getSelectedDefensivePower());
                p2.setDefensivePower(GamePanel.p1PowerSelectionMenu.getSelectedDefensivePower());
            }
        }
    }

    public boolean arePowersEnabled() {
        return arePowersEnabled;
    }
    public void setArePowersEnabled(boolean arePowersEnabled) {
        this.arePowersEnabled = arePowersEnabled;
    }

    public void setPointsToWin(int pointsToWin){
        this.pointsToWin = pointsToWin;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public boolean isDefensivePowerRechargeable() {
        return isDefensivePowerRechargeable;
    }

    public boolean isOffensivePowerRechargeable() {
        return isOffensivePowerRechargeable;
    }

}
