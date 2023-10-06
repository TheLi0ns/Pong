package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.Logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

/**
 * Manages the rendering
 */
public class GamePanel extends JPanel {

    public static final int WIDTH = 1000, HEIGHT = 1000;
    public static final int LEFT_BOUND = 5, RIGHT_BOUND = 995, CENTER = 500;

    final GameLogic gl;

    GamePanel(GameLogic gl){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        //KeyHandler keyListener = new KeyHandler();
        //keyListener.setGl(gl);
        this.gl = gl;
        //addKeyListener(new KeyHandler(gl));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //MATCH
        if(gl.getGameState() == GameLogic.GameStates.PLAYING){
            switch(gl.getGameMode()){
                case PVP, PVE -> {
                    drawField(g2d);
                    drawGameObjects(g2d);
                    drawScore(g2d);
                }

                case DRIBBLE, BOSS_FIGHTS -> gl.getMiniGame().draw(g2d);

            }
        }
        //CUTSCENE
        if(gl.getGameState() == GameLogic.GameStates.CUTSCENE){
            CutsceneHandler.draw(g2d);
        }
        //PAUSE SCREEN
        else if(gl.getGameState() == GameLogic.GameStates.PAUSE){
            drawPauseScreen(g2d);
        }
        //FINISH SCREEN
        else if(gl.getGameState() == GameLogic.GameStates.FINISH){
            drawFinishScreen(g2d);
        }
        //MENU
        else if(gl.getGameState() == GameLogic.GameStates.MENU) {
            gl.getMenu().draw(g2d);
        }

        g2d.dispose();
    }

    private void drawField(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(0, 0, 0, 1000);
        g2d.drawLine(1000, 0, 1000, 1000);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(400, 400, 200, 200);
        g2d.drawLine(0, 500, 1000, 500);
        g2d.fillOval(490, 490, 20, 20);

        g2d.setPaint(Color.darkGray);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {3,5}, 0));
        g2d.drawLine(0, 45, 1000,45);
        g2d.drawLine(0, 954, 1000,954);
    }


    private void drawGameObjects(Graphics2D g2d){
        gl.p1.draw(g2d);
        gl.p2.draw(g2d);

        gl.ball.draw(g2d);
    }

    private void drawScore(Graphics2D g2d){
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.format("%d - %d", gl.p2.getScore(), gl.p1.getScore()), 450, -775);
        g2d.setTransform(old_at);
    }

    private void drawPauseScreen(Graphics2D g2d){
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
        g2d.drawString("RESUME", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_P), 700, y);
    }

    private void drawFinishScreen(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        g2d.drawString(gl.getFinish(), 100, 525);
    }
}
