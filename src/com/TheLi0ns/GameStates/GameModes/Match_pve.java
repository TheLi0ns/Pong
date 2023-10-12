package com.TheLi0ns.GameStates.GameModes;

import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameObject.Balls.Ball;
import com.TheLi0ns.GameObject.Players.ComputerPlayer;
import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.GameStates.MenusHandling.Menus.PowersSelectionMenu_PvE;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Match_pve extends GameMode_super {

    Player player;
    ComputerPlayer bot;
    Ball ball;

    /**
     * The string that will be displayed
     * when someone wins
     */
    String finish_str;

    static ComputerPlayer.Difficulties difficulty;

    public Match_pve(GameLogic gl, ComputerPlayer.Difficulties difficulty) {
        super(gl);
        Match_pve.difficulty = difficulty;
        start();
    }

    public Match_pve(GameLogic gl){
        super(gl);
        start();
    }

    /**
     * Creates the player, the bot and the first ball
     * {@link #setupPowers() Assigns to the player his powers}
     * And set the playing game state
     */
    @Override
    protected void start() {
        player = new Player(391, 909, 6);
        bot = new ComputerPlayer(391, 53, 6, difficulty, this);

        ball = new Ball(Utils.genRandomXVelocity(), 6);

        setupPowers();

        state = GameMode_super.States.PLAYING;
    }

    /**
     * Assign to the player the powers selected
     * in the power selection menu
     */
    private void setupPowers(){
        if (gl.arePowersEnabled()) {
            if (gl.isDefensivePowerRechargeable()) {
                player.setDefensivePower(PowersSelectionMenu_PvE.getSelectedDefensivePower());
            }
            if (gl.isOffensivePowerRechargeable()) {
                player.setOffensivePower(PowersSelectionMenu_PvE.getSelectedOffensivePower(), bot);
            }
        }
    }

    @Override
    public void updateGame() {
        player.update();
        bot.update();
        ball.update(player, bot);
        scoreUpdate();
    }

    /**
     * Checks if someone has scored
     * so gives the goal
     * and charge the opponent powers
     * and if someone has win {@link #finish() finish the game}
     */
    private void scoreUpdate(){
        switch (ball.checkScored()){
            case "UP" -> {
                player.hasScored();
                if(!hasSomeoneWins()) ball = new Ball(Utils.genRandomXVelocity(), 6);
                else finish();
                player.resetHitPerRound();
                bot.resetHitPerRound();
            }
            case "DOWN" -> {
                bot.hasScored();
                if(gl.arePowersEnabled()) player.chargingPowers();
                if(!hasSomeoneWins()) ball = new Ball(Utils.genRandomXVelocity(), 6);
                else finish();
                player.resetHitPerRound();
                bot.resetHitPerRound();
            }
        }
    }

    private boolean hasSomeoneWins(){
        return player.hasWon() || bot.hasWon();
    }

    /**
     * Make the gameState finish
     * Writes the {@link #finish_str finish string}
     */
    private void finish(){
        player.stopPowers();
        if (player.hasWon()) finish_str = "PLAYER 1\n WINS";
        else finish_str = "BOT \n WINS";
        state = GameMode_super.States.FINISH;
    }

    @Override
    public void drawGame(Graphics2D g2d) {
        drawField(g2d);
        player.draw(g2d);
        bot.draw(g2d);
        ball.draw(g2d);
        drawScore(g2d);
    }

    private void drawScore(Graphics2D g2d){
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.format("%d - %d", bot.getScore(), player.getScore()), 450, -775);
        g2d.setTransform(old_at);
    }

    @Override
    protected void drawPauseScreen(Graphics2D g2d) {
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 300;

        //PLAYER LEFT KEY
        g2d.drawString("PLAYER LEFT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Left_key), 700, y);

        //PLAYER RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER RIGHT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Right_key),700, y);

        //PLAYER OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER OFFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1OffensivePower_key),700, y);

        //PLAYER DEFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER DEFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1DefensivePower_key),700, y);

        //ESCAPE
        y += 60;
        g2d.drawString("RETURN TO TITLE SCREEN", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE), 700, y);

        //RESTART
        y += 60;
        g2d.drawString("RESTART MATCH", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_R), 700, y);

        //RESUME
        y += 60;
        g2d.drawString("PAUSE/RESUME", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_P), 700, y);
    }

    @Override
    protected void drawFinishScreen(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        g2d.drawString(finish_str, 100, 525);
    }

    public static void setDifficulty(ComputerPlayer.Difficulties difficulty) {
        Match_pve.difficulty = difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public ComputerPlayer getBot() {
        return bot;
    }

    public Ball getBall() {
        return ball;
    }
}
