package com.TheLi0ns.Logic.MiniGames;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.DribbleBall;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.GameLogic.GameModes;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @see GameModes#DRIBBLE
 */
public class Dribble extends MiniGame {
    
    Player player;
    Ball ball;

    static int highestScore;

    public Dribble(GameLogic gl){
        super(gl);
        start();
    }

    @Override
    public void start() {
        gl.setGameMode(GameModes.DRIBBLE);
        gl.setGameState(GameLogic.GameStates.PAUSE);

        player = new Player(391, 909, 6);

        ball = new DribbleBall(Utils.genRandomXVelocity(), 6);

        gl.setGameState(GameLogic.GameStates.PLAYING);
    }

    @Override
    public void update() {
        if(ball.fell()){
            finished = true;
            highestScore = Math.max(highestScore, player.getScore());
            return;
        }

        ball.update(player);
        player.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
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

        player.draw(g2d);
        ball.draw(g2d);

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

        if(finished){
            String string = String.valueOf(player.getScore());
            Font font = new Font("Comic Sans MS", Font.PLAIN, 90);
            g2d.setFont(font);
            g2d.drawString(string,
                            Utils.xForCenteredText(g2d, GamePanel.WIDTH, string),
                            Utils.yForCenteredText(g2d, GamePanel.HEIGHT, string)
                        );
        }
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
