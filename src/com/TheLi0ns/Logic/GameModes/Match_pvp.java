package com.TheLi0ns.Logic.GameModes;

import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Menus.PowersSelectionMenu_PvP;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Match_pvp extends GameMode_super {

    Player p1;
    Player p2;
    Ball ball;

    /**
     * The string that will be displayed
     * when someone wins
     */
    String finish_str;

    public Match_pvp(GameLogic gl){
        super(gl);
        start();
    }

    /**
     * Creates the players and the first ball
     * {@link #setupPowers() Assigns to the players their powers}
     * And set the playing game state
     */
    @Override
    protected void start() {
        p1 = new Player(391, 909, 6);
        p2 = new Player(391, 53, 6);

        ball = new Ball(Utils.genRandomXVelocity(), 6);
        
        setupPowers();

        gl.setGameState(GameLogic.GameStates.PLAYING);
        state = States.PLAYING;
    }

    /**
     * Assign to the players the powers selected
     * in the power selection menu
     */
    private void setupPowers(){
        if (gl.arePowersEnabled()) {
            if (gl.isDefensivePowerRechargeable()) {
                p1.setDefensivePower(PowersSelectionMenu_PvP.getP1SelectedDefensivePower());
                p2.setDefensivePower(PowersSelectionMenu_PvP.getP2SelectedDefensivePower());
            }
            if (gl.isOffensivePowerRechargeable()) {
                p1.setOffensivePower(PowersSelectionMenu_PvP.getP1SelectedOffensivePower(), p2);
                p2.setOffensivePower(PowersSelectionMenu_PvP.getP2SelectedOffensivePower(), p1);
            }
        }
    }

    @Override
    public void updateGame() {
        p1.update();
        p2.update();
        ball.update(p1, p2);
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
                p1.hasScored();
                if(gl.arePowersEnabled()) p2.chargingPowers();
                if(!hasSomeoneWins()) ball = new Ball(Utils.genRandomXVelocity(), 6);
                else finish();
                p1.resetHitPerRound();
                p2.resetHitPerRound();
            }
            case "DOWN" -> {
                p2.hasScored();
                if(gl.arePowersEnabled()) p1.chargingPowers();
                if(!hasSomeoneWins()) ball = new Ball(Utils.genRandomXVelocity(), -6);
                else finish();
                p2.resetHitPerRound();
                p1.resetHitPerRound();
            }
        }
    }

    private boolean hasSomeoneWins(){
        return p1.hasWon() || p2.hasWon();
    }

    /**
     * Make the gameState finish
     * Writes the {@link #finish_str finish string}
     */
    private void finish(){
        if (p1.hasWon()) finish_str = "PLAYER 1\n WINS";
        else finish_str = "PLAYER 2\n WINS";
        state = States.FINISH;
    }


    @Override
    public void drawGame(Graphics2D g2d) {
        drawField(g2d);
        p1.draw(g2d);
        p2.draw(g2d);
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
        g2d.drawString(String.format("%d - %d", p2.getScore(), p1.getScore()), 450, -775);
        g2d.setTransform(old_at);
    }

    @Override
    protected void drawPauseScreen(Graphics2D g2d) {
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 200;

        //PLAYER 1 LEFT KEY
        g2d.drawString("PLAYER 1 LEFT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Left_key), 700, y);

        //PLAYER 1 RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER 1 RIGHT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Right_key),700, y);

        //PLAYER 1 OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 1 OFFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1OffensivePower_key),700, y);

        //PLAYER 1 DEFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 1 DEFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1DefensivePower_key),700, y);

        //PLAYER 2 LEFT KEY
        y += 60;
        g2d.drawString("PLAYER 2 LEFT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p2Left_key),700, y);

        //PLAYER 2 RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER 2 RIGHT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p2Right_key),700, y);

        //PLAYER 2 OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 2 OFFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p2OffensivePower_key),700, y);

        //PLAYER 2 DEFENSIVE POWER KEY
        y += 60;
        g2d.drawString("PLAYER 2 DEFENSIVE POWER KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p2DefensivePower_key),700, y);

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

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }
}
