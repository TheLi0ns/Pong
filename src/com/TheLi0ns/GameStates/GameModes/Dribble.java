package com.TheLi0ns.GameStates.GameModes;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameObject.Balls.Ball;
import com.TheLi0ns.GameObject.Balls.DribbleBall;
import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

/**
 * A mini-game in which you have
 * to juggle to get the highest score
 */
public class Dribble extends GameMode_super {
    
    Player player;
    Ball ball;

    static int highestScore;

    public Dribble(GameLogic gl){
        super(gl);
        start();
    }

    @Override
    protected void start() {
        player = new Player(391, 909, 6);

        ball = new DribbleBall(Utils.genRandomXVelocity(), 6);

        gl.setState(GameLogic.States.RUNNING);
        state = GameMode_super.States.PLAYING;
    }

    @Override
    public void updateGame() {
        if(ball.fell()){
            state = GameMode_super.States.FINISH;
            highestScore = Math.max(highestScore, player.getScore());
            return;
        }

        ball.update(player);
        player.update();
    }

    @Override
    public void drawGame(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));

        drawField(g2d);

        player.draw(g2d);
        ball.draw(g2d);

        drawScore(g2d);
    }

    @Override
    protected void drawField(Graphics2D g2d) {
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(0, 0, 0, 1000);
        g2d.drawLine(1000, 0, 1000, 1000);
        g2d.drawLine(0, 0, 1000, 0);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(400, 400, 200, 200);
        g2d.drawLine(0, 500, 1000, 500);
        g2d.fillOval(490, 490, 20, 20);

        g2d.setPaint(Color.darkGray);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3,5}, 0));
        g2d.drawLine(0, 954, 1000,954);
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.valueOf(player.getScore()), 450, -775);
        g2d.setTransform(old_at);

        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        g2d.setColor(Color.gray);
        g2d.drawString("Highest Score: " + highestScore,
                Utils.xForCenteredText(g2d, GamePanel.WIDTH, "Highest Score: " + highestScore),
                GamePanel.HEIGHT-10);
        g2d.setColor(Color.WHITE);
    }

    @Override
    protected void drawPauseScreen(Graphics2D g2d) {
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 350;

        //PLAYER LEFT KEY
        g2d.drawString("PLAYER LEFT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Left_key), 700, y);

        //PLAYER RIGHT KEY
        y += 60;
        g2d.drawString("PLAYER RIGHT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Right_key),700, y);

        //ESCAPE
        y += 60;
        g2d.drawString("RETURN TO TITLE SCREEN", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE), 700, y);

        //RESTART
        y += 60;
        g2d.drawString("RESTART", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_R), 700, y);

        //RESUME
        y += 60;
        g2d.drawString("PAUSE/RESUME", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_P), 700, y);
    }

    @Override
    protected void drawFinishScreen(Graphics2D g2d) {
        drawGame(g2d);
        String string = String.valueOf(player.getScore());
        Font font = new Font("Comic Sans MS", Font.PLAIN, 90);
        g2d.setFont(font);
        g2d.drawString(string,
                Utils.xForCenteredText(g2d, GamePanel.WIDTH, string),
                Utils.yForCenteredText(g2d, GamePanel.HEIGHT, string)
        );
    }

    public Player getPlayer() {
        return player;
    }

    public Ball getBall() {
        return ball;
    }

    public static int getHighestScore() {
        return highestScore;
    }

    public static void setHighestScore(int highestScoreLoaded) {
        highestScore = highestScoreLoaded;
    }
}
