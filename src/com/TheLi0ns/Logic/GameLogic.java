package com.TheLi0ns.Logic;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.ComputerPlayer;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Logic.MiniGames.MiniGame;
import com.TheLi0ns.MenusHandling.Menus.Menu;
import com.TheLi0ns.MenusHandling.Menus.PowersSelectionMenu_PvE;
import com.TheLi0ns.MenusHandling.Menus.PowersSelectionMenu_PvP;
import com.TheLi0ns.MenusHandling.Menus.TitleScreen;
import com.TheLi0ns.Utility.Utils;

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
     * The string that will be displayed
     * when someone wins
     */
    String finish = null;

    private Menu menu = new TitleScreen(this);

    private GameStates gameState = GameStates.MENU;

    public enum GameStates {
        MENU,
        PLAYING,
        PAUSE,
        CUTSCENE,
        FINISH,
    }

    private GameModes gameMode = GameModes.PVP;

    public enum GameModes{
        PVP,
        PVE,
        /**
         * A mini-game in which you have
         * to dribble to get the highest score
         */
        DRIBBLE,

        /**
         * A mini-game in which you will face
         * some bosses
         */
        BOSS_FIGHTS
    }

    private ComputerPlayer.Difficulties computer_difficulty;

    private MiniGame miniGame;

    public GameLogic(){
        Thread gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * Creates the player and the first ball
     * {@link GameLogic#setUpPowers() Assigns to the players their powers}
     * And set the playing game state
     */
    public void startMatch(){
        gameState = GameStates.PAUSE;

        p1 = new Player(391, 909, 6);

        if(gameMode == GameModes.PVP) p2 = new Player(391, 53, 6);
        else if(gameMode == GameModes.PVE) p2 = new ComputerPlayer(391, 53, 7, computer_difficulty);

        ball = new Ball(Utils.genRandomXVelocity(), 6);

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
            if(gameState == GameStates.PLAYING) {
                switch(gameMode){
                    case PVP, PVE -> update();
                    case DRIBBLE, BOSS_FIGHTS -> miniGame.update();
                }
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
                if(arePowersEnabled && gameMode == GameModes.PVP) p2.ChargingPowers();
                if(!hasSomeoneWins()) {
                    ball = new Ball(Utils.genRandomXVelocity(), 5);
                }else{
                    finish();
                }
                p1.resetHitPerRound();
                p2.resetHitPerRound();
            }
            case "DOWN" -> {
                p2.hasScored();
                if(arePowersEnabled) p1.ChargingPowers();
                if(!hasSomeoneWins()) {
                    ball = new Ball(Utils.genRandomXVelocity(), -5);
                }else{
                    finish();
                }
                p1.resetHitPerRound();
                p2.resetHitPerRound();
            }
        }
    }

    public void backToMainMenu(){
        this.gameState = GameStates.MENU;
        menu = new TitleScreen(this);
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public GameStates getGameState(){
        return gameState;
    }

    public void togglePause() {
        if(gameState == GameStates.PAUSE) gameState = GameStates.PLAYING;
        else if(gameState == GameStates.PLAYING) gameState = GameStates.PAUSE;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public MiniGame getMiniGame() {
        return miniGame;
    }

    public void setMiniGame(MiniGame miniGame) {
        this.miniGame = miniGame;
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

    public String getFinish() {
        return finish;
    }

    /**
     * Assign to the player the powers selected
     * in the power selection menu
     */
    public void setUpPowers(){
        if(arePowersEnabled){
            if(isDefensivePowerRechargeable()){
                if(gameMode == GameModes.PVE){
                    p1.setDefensivePower(PowersSelectionMenu_PvE.getSelectedDefensivePower());
                }else{
                    p1.setDefensivePower(PowersSelectionMenu_PvP.getP1SelectedDefensivePower());
                    p2.setDefensivePower(PowersSelectionMenu_PvP.getP2SelectedDefensivePower());
                }
            }
            if(isOffensivePowerRechargeable()){
                if(gameMode == GameModes.PVE){
                    p1.setOffensivePower(PowersSelectionMenu_PvE.getSelectedOffensivePower(), p2);
                }else{
                    p1.setOffensivePower(PowersSelectionMenu_PvP.getP1SelectedOffensivePower(), p2);
                    p2.setOffensivePower(PowersSelectionMenu_PvP.getP2SelectedOffensivePower(), p2);
                }
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

    public void setGameMode(GameModes gameMode) {
        this.gameMode = gameMode;
    }

    public GameModes getGameMode() {
        return gameMode;
    }

    public void setComputer_difficulty(ComputerPlayer.Difficulties computer_difficulty) {
        this.computer_difficulty = computer_difficulty;
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
