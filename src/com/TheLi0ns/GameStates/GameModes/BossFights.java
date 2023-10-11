package com.TheLi0ns.GameStates.GameModes;

import com.TheLi0ns.Cutscenes.CutsceneEnum;
import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.GameFrame.KeyHandler;
import com.TheLi0ns.GameObject.Balls.Ball;
import com.TheLi0ns.GameObject.Players.Bosses.*;
import com.TheLi0ns.GameObject.Players.Fighter;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * A mini-game in which you will face
 * some bosses
 */
public class BossFights extends GameMode_super {
    
    Boss_super boss;

    Fighter fighter;

    Ball ball;

    static BossEnum selectedBoss;

    boolean hasPlayerWon = false;

    public BossFights(BossEnum selectedBoss, GameLogic gl){
        super(gl);
        BossFights.selectedBoss = selectedBoss;
        start();
    }

    /**
     * Start the bossfight with the last boss faced
     */
    public BossFights(GameLogic gl){
        super(gl);
        start();
    }

    @Override
    protected void start() {
        fighter = new Fighter();

        switch(selectedBoss){
            case THE_PYROMANCER -> boss = new BossThePyromancer(this);
            case THE_SHRINKER -> boss = new BossTheShrinker(this);
            case THE_DISORIENTATOR -> boss = new BossTheDisorientator(this);
        }

        ball = new Ball(Utils.genRandomXVelocity(), 6);

        Sound.playBackgroundMusic(Sound.BOSS_FIGHT_MUSIC);

        gl.setState(GameLogic.States.RUNNING);
        state = GameMode_super.States.PLAYING;
    }

    @Override
    public void updateGame() {
        if(!scoreUpdate()){
            boss.update();
            fighter.update();
            ball.update(fighter, boss);
        }
    }

    /**
     * checks if someone has scored and then
     * update the health of the player and boss
     * @return true if the fight ended after the update of the healts
     */
    private boolean scoreUpdate(){
        switch(ball.checkScored()){
            case "UP" -> {
                if(boss.damaged()) {
                    finish();
                }else{
                    ball = new Ball(Utils.genRandomXVelocity(), 5);
                }
                fighter.roundFinished();
                boss.roundFinished();
            }
            case "DOWN" -> {
                if(fighter.damaged()) {
                    finish();
                }else{
                    ball = new Ball(Utils.genRandomXVelocity(), -5);
                }
                fighter.roundFinished();
                boss.roundFinished();
            }
        }
        return isFinished();
    }

    public void finish(){
        state = GameMode_super.States.FINISH;
        Sound.stop();
        Sound.stopBackgroundMusic();
        if(fighter.getHealth() == 0) CutsceneHandler.playCutscene(CutsceneEnum.GameOver);
        else CutsceneHandler.playCutscene(CutsceneEnum.YOU_WIN);
    }

    @Override
    public void drawGame(Graphics2D g2d) {
        drawField(g2d);
        boss.draw(g2d);
        fighter.draw(g2d);
        ball.draw(g2d);
    }

    @Override
    protected void drawField(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(0, 0, 0, 1000);
        g2d.drawLine(1000, 0, 1000, 1000);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(400, 400, 200, 200);
        g2d.drawLine(0, 500, 1000, 500);
        g2d.fillOval(490, 490, 20, 20);
    }

    @Override
    protected void drawPauseScreen(Graphics2D g2d) {
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2d.setColor(Color.WHITE);
        int x = 100;
        int y = 300;

        //FIGHTER LEFT KEY
        g2d.drawString("FIGHTER LEFT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Left_key), 700, y);

        //FIGHTER RIGHT KEY
        y += 60;
        g2d.drawString("FIGHTER RIGHT KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyHandler.p1Right_key),700, y);

        //FIGHTER OFFENSIVE POWER KEY
        y += 60;
        g2d.drawString("FIGHTER PARRY KEY", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_SPACE),700, y);

        //ESCAPE
        y += 60;
        g2d.drawString("RETURN TO TITLE SCREEN", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE), 700, y);

        //RESTART
        y += 60;
        g2d.drawString("RESTART FIGHT", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_R), 700, y);

        //RESUME
        y += 60;
        g2d.drawString("PAUSE/RESUME", x, y);
        g2d.drawString(KeyEvent.getKeyText(KeyEvent.VK_P), 700, y);
    }

    @Override
    protected void drawFinishScreen(Graphics2D g2d) {
        if (fighter.getHealth() == 0) {
            g2d.drawImage(Assets.GAME_OVER[0], 200, 380, null);
            g2d.drawImage(Assets.GAME_OVER[1], 210, 500, null);
        } else {
            g2d.drawImage(Assets.YOU_WIN[0], 280, 330, null);
            g2d.drawImage(Assets.YOU_WIN[1], 280, 530, null);
        }
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
