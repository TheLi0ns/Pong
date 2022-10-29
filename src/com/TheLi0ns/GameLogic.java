package com.TheLi0ns;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Creates the game loop
 * Manages the game loop update
 * Contains the settings of the current game
 */
public class GameLogic implements Runnable{

    public Player p1;
    public Player p2;
    public Ball ball;

    private int pointToWin = 15;

    private final int FPS = 90;

    private boolean isFinished = false;
    private boolean isPaused = false;

    private final boolean arePowersEnabled;
    private final boolean IS_DEFENSIVE_POWER_RECHARGEABLE;
    private final boolean IS_FIRESHOT_POWER_RECHARGEABLE;

    String finish = null;

    public GameLogic(){
        setPointToWin();

        IS_FIRESHOT_POWER_RECHARGEABLE = pointToWin > 6;
        IS_DEFENSIVE_POWER_RECHARGEABLE = pointToWin > 3;

        p1 = new Player(391, 909, 6);
        p2 = new Player(391, 53, 6);

        arePowersEnabled = powerSelection();

        ball = new Ball(472, 468, genRandomxVelocity(), 6);

        Thread gameLoop = new Thread(this);
        gameLoop.start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!hasSomeoneWins()) {
            if(!isPaused) {
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

    private void scoreUpdate(){
        switch(ball.checkScored()){
            case "UP" -> {
                p1.hasScored();
                if(arePowersEnabled) p2.ChargingPowerUps();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), 5);
                }else{
                    finish();
                }
            }
            case "DOWN" -> {
                p2.hasScored();
                if(arePowersEnabled) p1.ChargingPowerUps();
                if(!hasSomeoneWins()) {
                    ball = new Ball(472, 468, genRandomxVelocity(), -5);
                }else{
                    finish();
                }
            }
        }
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean hasSomeoneWins(){
        return p1.hasWon() || p2.hasWon();
    }

    public void finish(){
        isFinished = true;
        if(p1.hasWon()){
            finish = "PLAYER 1\n  WINS";
        }else{
            finish = "PLAYER 2\n  WINS";
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getFinish() {
        return finish;
    }

    private int genRandomxVelocity(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(3, 5) * (random.nextBoolean() ? 1 : -1);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean powerSelection(){
        if(JOptionPane.showConfirmDialog(null, "Enable Powers?", "POWERS", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
            p1.setArePowersEnabled(false);
            p2.setArePowersEnabled(false);
            return false;
        }

        JComboBox<String> defensivePower_combobox = new JComboBox<>();
        JComboBox<String> offensivePower_combobox = new JComboBox<>();

        for(Player.OffensivePowerUps i : Player.OffensivePowerUps.values()){
            offensivePower_combobox.addItem(i.name);
        }

        for(Player.DefensivePowerUps i : Player.DefensivePowerUps.values()){
            defensivePower_combobox.addItem(i.name);
        }

        JOptionPane.showConfirmDialog(null, defensivePower_combobox, "PLAYER 1 SELECT DEFENSIVE POWER", JOptionPane.YES_NO_OPTION);
        p1.setDefensivePowerUp(Player.DefensivePowerUps.powerNamed((String) defensivePower_combobox.getSelectedItem()));

        JOptionPane.showConfirmDialog(null, offensivePower_combobox, "PLAYER 1 SELECT OFFENSIVE POWER", JOptionPane.YES_NO_OPTION);
        p1.setOffensivePowerup(Player.OffensivePowerUps.powerNamed((String) offensivePower_combobox.getSelectedItem()));

        JOptionPane.showConfirmDialog(null, defensivePower_combobox, "PLAYER 2 SELECT DEFENSIVE POWER", JOptionPane.YES_NO_OPTION);
        p2.setDefensivePowerUp(Player.DefensivePowerUps.powerNamed((String) defensivePower_combobox.getSelectedItem()));

        JOptionPane.showConfirmDialog(null, offensivePower_combobox, "PLAYER 2 SELECT OFFENSIVE POWER", JOptionPane.YES_NO_OPTION);
        p2.setOffensivePowerup(Player.OffensivePowerUps.powerNamed((String) offensivePower_combobox.getSelectedItem()));

        return true;
    }

    public boolean arePowersEnabled() {
        return arePowersEnabled;
    }

    /**
     * Asks the User how many points have to score to win the match
     */
    public void setPointToWin(){
        togglePause();

        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(30);
        slider.setValue(1);
        slider.setPreferredSize(new Dimension(750, 100));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setFont(new Font("CALIBRI CORPO", Font.PLAIN, 15));
        slider.setValue(10);

        JOptionPane.showConfirmDialog(null, slider, "   POINTS TO WIN    ", JOptionPane.DEFAULT_OPTION);
        pointToWin = slider.getValue();

        togglePause();
    }

    public int getPointToWin() {
        return pointToWin;
    }

    public boolean isDefensivePowerRechargeable() {
        return IS_DEFENSIVE_POWER_RECHARGEABLE;
    }

    public boolean isOffensivePowerRechargeable() {
        return IS_FIRESHOT_POWER_RECHARGEABLE;
    }

}
