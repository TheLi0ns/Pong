package com.TheLi0ns.GameFrame;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Menus.*;
import com.TheLi0ns.MenusHandling.SubMenus.GameModeSubMenu;

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

    //MENUS
    public static TitleScreen titleScreen = new TitleScreen();
    public static GameModeSubMenu gameModeSubMenu = new GameModeSubMenu();
    public static SettingsMenu settingsMenu = new SettingsMenu();
    public static KeyBindingsMenu keyBindingsMenu = new KeyBindingsMenu();
    public static PowerSelectionMenu p1PowerSelectionMenu = new PowerSelectionMenu(1);
    public static PowerSelectionMenu p2PowerSelectionMenu = new PowerSelectionMenu(2);
    public static MiniGamesMenu miniGamesMenu = new MiniGamesMenu();

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

        //MATCH
        if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
            switch(MyFrame.gameLogic.getGameMode()){
                case PVP, PVE -> {
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
                }

                case DRIBBLE -> MyFrame.gameLogic.miniGame.draw(g2d);
            }
        }
        //PAUSE SCREEN
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PAUSE){
            drawPauseScreen(g2d);
        }
        //FINISH SCREEN
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.FINISH){
            drawFinishScreen(g2d);
        }
        //TITLE SCREEN
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.TITLE_SCREEN){
            titleScreen.draw(g2d);
        }
        //GAME MODE SUBMENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAY_SUBMENU){
            titleScreen.draw(g2d);
            gameModeSubMenu.draw(g2d);
        }
        //MINI-GAMES MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.MINI_GAMES_MENU){
            miniGamesMenu.draw(g2d);
        }
        //SETTINGS MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SETTINGS_MENU){
            settingsMenu.draw(g2d);
        }
        //SELECTING POWERS MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.SELECTING_POWERS){
            p1PowerSelectionMenu.draw(g2d);
            if(MyFrame.gameLogic.getGameMode() == GameLogic.GameModes.PVP) p2PowerSelectionMenu.draw(g2d);
        }
        //KEY BINDINGS MENU
        else if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.KEY_BINDINGS_MENU){
            keyBindingsMenu.draw(g2d);
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
        if(MyFrame.gameLogic.getGameMode() == GameLogic.GameModes.PVP){
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
        if(MyFrame.gameLogic.getGameMode() == GameLogic.GameModes.PVP) {
            g2d.setPaint(Color.gray);
            g2d.drawRect(50, 55, 70, 125);
            g2d.drawLine(50, 95, 120, 95);
            g2d.drawLine(50, 135, 120, 135);

            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
            g2d.drawString(MyFrame.gameLogic.p2.getOffensivePower().getName(), 50, 200);

            g2d.setPaint(Color.GREEN);
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 0) {
                g2d.fillRect(55, 60, 30, 30);
            }
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 1) {
                g2d.fillRect(85, 60, 30, 30);
            }
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 2) {
                g2d.fillRect(55, 100, 30, 30);
            }
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 3) {
                g2d.fillRect(85, 100, 30, 30);
            }
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 4) {
                g2d.fillRect(55, 140, 30, 35);
            }
            if (MyFrame.gameLogic.p2.getChargeOffensivePower() > 5) {
                g2d.fillRect(85, 140, 30, 35);
                g2d.setPaint(Color.RED);
                g2d.drawRect(45, 50, 80, 135);
            }
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
        g2d.drawString(MyFrame.gameLogic.getFinish(), 100, 525);
    }
}
