package com.TheLi0ns.Logic.MiniGames;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Ball;
import com.TheLi0ns.GameObject.Bosses.BossEnum;
import com.TheLi0ns.GameObject.Bosses.BossThePyromancer;
import com.TheLi0ns.GameObject.Bosses.Boss_super;
import com.TheLi0ns.GameObject.Fighter;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.GameLogic.GameModes;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * @see GameModes#BOSS_FIGHTS
 */
public class BossFights extends MiniGame{

    Boss_super boss;

    Fighter fighter;

    Ball ball;

    static BossEnum selectedBoss;

    String finish;

    boolean hasPlayerWon = false;

    public BossFights(BossEnum selectedBoss){
        this.selectedBoss = selectedBoss;
        start();
    }

    /**
     * Start the bossfight with the last boss faced
     */
    public BossFights(){
        start();
    }

    @Override
    public void start() {
        MyFrame.gameLogic.setGameMode(GameModes.BOSS_FIGHTS);
        MyFrame.gameLogic.setGameState(GameLogic.GameStates.PAUSE);

        fighter = new Fighter();

        switch(selectedBoss){
            case THE_PYROMANCER -> boss = new BossThePyromancer(this);
        }

        ball = new Ball(Utils.genRandomXVelocity(), 6);

        Sound.playBackground(Sound.BOSS_FIGHT_MUSIC);

        MyFrame.gameLogic.setGameState(GameLogic.GameStates.PLAYING);
    }

    @Override
    public void update() {
        if(hasSomeoneWins()) return;
        scoreUpdate();
        boss.update();
        fighter.update();
        ball.update(fighter, boss);
    }

    private void scoreUpdate(){
        switch(ball.checkScored()){
            case "UP" -> {
                boss.damaged();
                if(!hasSomeoneWins()) {
                    ball = new Ball(Utils.genRandomXVelocity(), 5);
                }else{
                    finish();
                }
                fighter.roundFinished();
                boss.roundFinished();
            }
            case "DOWN" -> {
                fighter.damaged();
                if(!hasSomeoneWins()) {
                    ball = new Ball(Utils.genRandomXVelocity(), -5);
                }else{
                    finish();
                }
                fighter.roundFinished();
                boss.roundFinished();
            }
        }
    }

    public void finish(){
        if(boss.getHealth() == 0){
            finish = "BOSS\nDEFEATED";
            hasPlayerWon = true;
        }else{
            finish = "GAME\nOVER";
        }
        finished = true;
    }

    private boolean hasSomeoneWins(){
        return boss.getHealth() == 0 || fighter.getHealth() == 0;
    }


    @Override
    public void draw(Graphics2D g2d) {
        if(!finished){
            drawField(g2d);
            boss.draw(g2d);
            fighter.draw(g2d);
            ball.draw(g2d);
        }else {
            g2d.setFont(new Font("Algerian", Font.PLAIN, 100));
            g2d.setColor(Color.RED);
            int y = Utils.yForCenteredText(g2d, GamePanel.HEIGHT+50, finish);
            for (String line : finish.split("\n")){
                g2d.drawString(line, Utils.xForCenteredText(g2d, GamePanel.WIDTH, line), y);
                y += g2d.getFontMetrics().getHeight();
            }
        }
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
    }

    public Fighter getFighter() {
        return fighter;
    }

    public Ball getBall() {
        return ball;
    }

    public Boss_super getBoss() {
        return boss;
    }

    public boolean hasPlayerWon() {
        return hasPlayerWon;
    }
}
