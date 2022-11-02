package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Menus.SettingsMenu;
import com.TheLi0ns.Menus.TitleScreen;
import com.TheLi0ns.Utility.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Manages the rendering
 */
public class GamePanel extends JPanel {

    public static final int WIDTH = 1000, HEIGHT = 1000;
    public static final int LEFT_BOUND = 5, RIGHT_BOUND = 995, CENTER = 500;

    public static TitleScreen titleScreen = new TitleScreen();
    public static SettingsMenu settingsMenu = new SettingsMenu();

    GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new KeyHandler());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            drawField(g2d);

            if(MyFrame.gameLogic.arePowersEnabled()){
                if(MyFrame.gameLogic.isDefensivePowerRechargeable()){
                    drawDefensivePowerBattery(g2d);
                }
                if(MyFrame.gameLogic.isOffensivePowerRechargeable()){
                    drawOffensivePowerBattery(g2d);
                }
            }

            drawGameObjects(g2d);

            drawScore(g2d);
        }else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PAUSE){
            g2d.drawImage(Assets.PAUSE_MENU, 0, 0, null);
        }else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.FINISH){
            drawFinishScreen(g2d);
        }else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.TITLE_SCREEN){
            titleScreen.draw(g2d);
        }else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SETTINGS_MENU){
            settingsMenu.draw(g2d);
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

    private void drawDefensivePowerBattery(Graphics2D g2d){

        //p1
        g2d.setPaint(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(50, 820, 70, 125);
        g2d.drawLine(50, 860, 120, 860);
        g2d.drawLine(50, 900, 120, 900);

        g2d.setPaint(Color.CYAN);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        g2d.drawString(MyFrame.gameLogic.p1.getDefensivePower().getName(), 50, 810);

        g2d.setPaint(Color.GREEN);
        if(MyFrame.gameLogic.p1.getChargeDefensivePower() > 0){
            g2d.fillRect(55, 905, 60, 35);
        }
        if(MyFrame.gameLogic.p1.getChargeDefensivePower() > 1){
            g2d.fillRect(55, 865, 60, 30);
        }
        if(MyFrame.gameLogic.p1.getChargeDefensivePower() > 2){
            g2d.fillRect(55, 825, 60,30);
            g2d.setPaint(Color.CYAN);
            g2d.drawRect(45, 815, 80, 135);
        }

        //p2
        g2d.setPaint(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(880, 55, 70, 125);
        g2d.drawLine(880, 95, 950, 95);
        g2d.drawLine(885, 135, 950, 135);

        g2d.setPaint(Color.CYAN);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        g2d.drawString(MyFrame.gameLogic.p2.getDefensivePower().getName(), 880, 200);

        g2d.setPaint(Color.GREEN);
        if(MyFrame.gameLogic.p2.getChargeDefensivePower() > 0){
            g2d.fillRect(885, 60, 60, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeDefensivePower() > 1){
            g2d.fillRect(885, 100, 60, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeDefensivePower() > 2){
            g2d.fillRect(885, 140, 60,35);
            g2d.setPaint(Color.CYAN);
            g2d.drawRect(875, 50, 80, 135);
        }
    }

    private void drawOffensivePowerBattery(Graphics2D g2d){

        //p1
        g2d.setPaint(Color.gray);
        g2d.drawRect(880, 820, 70, 125);
        g2d.drawLine(880, 860, 950, 860);
        g2d.drawLine(880, 900, 950, 900);

        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        g2d.drawString(MyFrame.gameLogic.p1.getOffensivePower().getName(), 880, 810);

        g2d.setPaint(Color.GREEN);
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 0){
            g2d.fillRect(885, 905, 30, 35);
        }
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 1){
            g2d.fillRect(915, 905, 30, 35);
        }
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 2){
            g2d.fillRect(885, 865, 30, 30);
        }
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 3){
            g2d.fillRect(915, 865, 30, 30);
        }
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 4){
            g2d.fillRect(885, 825, 30,30);
        }
        if(MyFrame.gameLogic.p1.getChargeOffensivePower() > 5){
            g2d.fillRect(915, 825, 30, 30);
            g2d.setPaint(Color.RED);
            g2d.drawRect(875, 815, 80, 135);
        }

        //p2
        g2d.setPaint(Color.gray);
        g2d.drawRect(50, 55, 70, 125);
        g2d.drawLine(50, 95, 120, 95);
        g2d.drawLine(50, 135, 120, 135);

        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        g2d.drawString(MyFrame.gameLogic.p2.getOffensivePower().getName(), 50, 200);

        g2d.setPaint(Color.GREEN);
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 0){
            g2d.fillRect(55, 60, 30, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 1){
            g2d.fillRect(85, 60, 30, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 2){
            g2d.fillRect(55, 100, 30, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 3){
            g2d.fillRect(85, 100, 30, 30);
        }
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 4){
            g2d.fillRect(55, 140, 30,35);
        }
        if(MyFrame.gameLogic.p2.getChargeOffensivePower() > 5){
            g2d.fillRect(85, 140, 30, 35);
            g2d.setPaint(Color.RED);
            g2d.drawRect(45, 50, 80, 135);
        }
    }

    private void drawGameObjects(Graphics2D g2d){
        MyFrame.gameLogic.p1.draw(g2d);
        MyFrame.gameLogic.p2.draw(g2d);

        MyFrame.gameLogic.ball.draw(g2d);
    }

    private void drawScore(Graphics2D g2d){
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        AffineTransform old_at = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 80, 100);
        g2d.setTransform(at);
        g2d.drawString(String.format("%d - %d", MyFrame.gameLogic.p2.getScore(), MyFrame.gameLogic.p1.getScore()), 450, -775);
        g2d.setTransform(old_at);
    }

    private void drawFinishScreen(Graphics2D g2d){
        g2d.setPaint(Color.RED);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        g2d.drawString(MyFrame.gameLogic.getFinish(), 100, 525);
    }
}
