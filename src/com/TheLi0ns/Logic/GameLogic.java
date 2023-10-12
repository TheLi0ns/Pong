package com.TheLi0ns.Logic;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameStates.GameState;
import com.TheLi0ns.GameStates.MenusHandling.Menus.TitleScreen;

/**
 * Creates the game loop
 * Manages the game loop update
 * Contains the settings
 */
public class GameLogic implements Runnable{

    private int pointsToWin = 15;
    public static final int MAX_POINTS = 30;

    public final int FPS = 90;

    private boolean arePowersEnabled = true;

    private GameState gameState = new TitleScreen(this);

    public GameLogic(){
        Thread gameLoop = new Thread(this);
        gameLoop.start();
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
            gameState.update();

            MyFrame.gamePanel.repaint();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void backToMainMenu(){
        gameState = new TitleScreen(this);
    }

    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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

    /**
     * The defensive power is rechargeable if the points to win
     * are greater than 3 the charge of defensive powers
     */
    public boolean isDefensivePowerRechargeable() {
        return pointsToWin > 3;
    }

    /**
     * The offensive power is rechargeable if the points to win
     * are greater than 6 the charge of offensive powers
     */
    public boolean isOffensivePowerRechargeable() {
        return pointsToWin > 6;
    }

}
