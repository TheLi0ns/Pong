package com.TheLi0ns.GameStates.CutscenesHandling;

import com.TheLi0ns.GameObject.Players.Bosses.BossTheDisorientator;
import com.TheLi0ns.GameStates.GameModes.BossFights;
import com.TheLi0ns.GameStates.GameModes.GameMode_super;
import com.TheLi0ns.GameStates.GameState;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Cutscene implements GameState {

    final GameMode_super ENV;
    final GameLogic gl;

    CutsceneEnum currentCutscene;
    int timeRemaining = 0;

    public Cutscene(CutsceneEnum cutscene, GameMode_super env) {
        ENV = env;
        this.gl = env.getGl();
        currentCutscene = cutscene;
        timeRemaining = cutscene.duration + 1;

        switch (currentCutscene){
            case ThePyromancer_Cutscene, TheShrinker_Cutscene, TheDisorientator_Cutscene -> bossFight_cutscene_generalSetup();
            case TheShrinker_SecondPhase -> theShrinkerSecondPhase_cutscene_setup();
            case TheDisorientator_SkillActivation -> theDisorientatorSkillActivation_cutscene_setup();
            case TheDisorientator_SkillDeactivation -> theDisorientatorSkillDeactivation_cutscene_setup();
            case GameOver -> Sound.play(Sound.GAME_OVER_SOUND);
            case YOU_WIN -> Sound.play(Sound.YOU_WIN_SOUND);
        }
    }


    @Override
    public void update() {
        if(timeRemaining == 0) gl.setGameState(ENV);
        else timeRemaining--;
    }

    @Override
    public void draw(Graphics2D g2d){
        switch (currentCutscene){
            case ThePyromancer_Cutscene -> draw_ThePyromancer_cutscene(g2d);
            case TheShrinker_Cutscene -> draw_TheShrinker_cutscene(g2d);
            case TheShrinker_SecondPhase -> draw_TheShrinkerSecondPhase_cutscene(g2d);
            case TheDisorientator_Cutscene -> draw_TheDisorientator_cutscene(g2d);
            case TheDisorientator_SkillActivation -> draw_TheDisorientatorSkillActivation_cutscene(g2d);
            case TheDisorientator_SkillDeactivation -> draw_TheDisorientatorSkillDeactivation_cutscene(g2d);
            case GameOver -> draw_GameOver(g2d);
            case YOU_WIN -> draw_YouWin(g2d);
        }
    }

    private void bossFight_cutscene_generalSetup(){
        BossFights bossFight = (BossFights) ENV;
        bossFight.getFighter().setLeftPressed(false);
        bossFight.getFighter().setRightPressed(false);
        bossFight.getFighter().setParrying(false);
    }

    private void draw_ThePyromancer_cutscene(Graphics2D g2d){
        if(timeRemaining == 260) Sound.play(Sound.BOSS_BANNER_SOUND);

        if(timeRemaining >= 590) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[0], 0, 0, null);
        else if(timeRemaining >= 580) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[1], 0, 0, null);
        else if(timeRemaining >= 570) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[2], 0, 0, null);
        else if(timeRemaining >= 560) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[3], 0, 0, null);
        else if(timeRemaining >= 470) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[4], 0, 0, null);
        else if(timeRemaining >= 460) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[5], 0, 0, null);
        else if(timeRemaining >= 450) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[6], 0, 0, null);
        else if(timeRemaining >= 440) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[7], 0, 0, null);
        else if(timeRemaining >= 350) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[8], 0, 0, null);
        else if(timeRemaining >= 340) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[9], 0, 0, null);
        else if(timeRemaining >= 330) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[10], 0, 0, null);
        else if(timeRemaining >= 320) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[11], 0, 0, null);
        else if(timeRemaining >= 230) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[12], 0, 0, null);
        else if(timeRemaining >= 140) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[13], 0, 0, null);
        else if(timeRemaining >= 130) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[14], 0, 0, null);
        else if(timeRemaining >= 120) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[15], 0, 0, null);
        else if(timeRemaining >= 110) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[16], 0, 0, null);
        else if(timeRemaining >= 100) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[17], 0, 0, null);
        else if(timeRemaining >= 90) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[18], 0, 0, null);
        else if(timeRemaining >= 0) g2d.drawImage(Assets.THE_PYROMANCER_CUTSCENE[19], 0, 0, null);
    }

    private void draw_TheShrinker_cutscene(Graphics2D g2d){
        if(timeRemaining == 260) Sound.play(Sound.BOSS_BANNER_SOUND);

        if(timeRemaining >= 590) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[0], 0, 0, null);
        else if(timeRemaining >= 580) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[1], 0, 0, null);
        else if(timeRemaining >= 570) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[2], 0, 0, null);
        else if(timeRemaining >= 560) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[3], 0, 0, null);
        else if(timeRemaining >= 470) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[4], 0, 0, null);
        else if(timeRemaining >= 460) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[5], 0, 0, null);
        else if(timeRemaining >= 450) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[6], 0, 0, null);
        else if(timeRemaining >= 440) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[7], 0, 0, null);
        else if(timeRemaining >= 350) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[8], 0, 0, null);
        else if(timeRemaining >= 340) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[9], 0, 0, null);
        else if(timeRemaining >= 330) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[10], 0, 0, null);
        else if(timeRemaining >= 320) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[11], 0, 0, null);
        else if(timeRemaining >= 230) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[12], 0, 0, null);
        else if(timeRemaining >= 140) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[13], 0, 0, null);
        else if(timeRemaining >= 130) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[14], 0, 0, null);
        else if(timeRemaining >= 120) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[15], 0, 0, null);
        else if(timeRemaining >= 110) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[16], 0, 0, null);
        else if(timeRemaining >= 100) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[17], 0, 0, null);
        else if(timeRemaining >= 90) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[18], 0, 0, null);
        else if(timeRemaining >= 0) g2d.drawImage(Assets.THE_SHRINKER_CUTSCENE[19], 0, 0, null);
    }
    
    private void theShrinkerSecondPhase_cutscene_setup() {
        BossFights bossFight = (BossFights) ENV;
        bossFight.getBoss().setX(391);
        bossFight.getFighter().setX(391);
        bossFight.getFighter().setPLAYER_IMAGE(bossFight.getFighter().getNORMAL_PLAYER_IMAGE());
        bossFight.getFighter().setLeftPressed(false);
        bossFight.getFighter().setRightPressed(false);
        bossFight.getFighter().setParrying(false);
        bossFight.getBall().setX(472);
        bossFight.getBall().setY(448);
        Sound.play(Sound.GIGALASER_SOUND);
    }

    private void draw_TheShrinkerSecondPhase_cutscene(Graphics2D g2d){
        BossFights bossFight = (BossFights) ENV;
        if(timeRemaining == 135){
            bossFight.getBoss().setBOSS_IMAGE(Assets.THE_SHRINKER_SECOND_PHASE);
        }
        else if(timeRemaining == 60){
            bossFight.getBall().setShrunk_lock(true);
            if(bossFight.getBall().getVelocity().getYDirection() == Directions.UP) bossFight.getBall().getVelocity().flipVertically();
            bossFight.getBall().shrinkBall(bossFight.getBoss());
            bossFight.getFighter().setPLAYER_IMAGE(Assets.MEDIUM_SHRUNK_RACKET);
            bossFight.getFighter().setX(409);
        }
        else if(timeRemaining == 45){
            bossFight.getFighter().setPLAYER_IMAGE(Assets.SHRUNK_RACKET);
            bossFight.getFighter().setX(426);
        }

        bossFight.drawGame(g2d);

        g2d.setColor(new Color(0, 75, 212));

        if(timeRemaining >= 105 && timeRemaining <= 120){
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getBoss().getY() + bossFight.getBoss().getHeight() / 2,
                         bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getFighter().getY() + bossFight.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 90){
            g2d.setStroke(new BasicStroke(20));
            g2d.drawLine(bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getBoss().getY() + bossFight.getBoss().getHeight() / 2,
                         bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getFighter().getY() + bossFight.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 75){
            g2d.setStroke(new BasicStroke(30));
            g2d.drawLine(bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getBoss().getY() + bossFight.getBoss().getHeight() / 2,
                         bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getFighter().getY() + bossFight.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 30){
            g2d.setStroke(new BasicStroke(20));
            g2d.drawLine(bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getBoss().getY() + bossFight.getBoss().getHeight() / 2,
                         bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getFighter().getY() + bossFight.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 15){
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getBoss().getY() + bossFight.getBoss().getHeight() / 2,
                         bossFight.getBoss().getX() + bossFight.getBoss().getWidth() / 2,
                         bossFight.getFighter().getY() + bossFight.getFighter().getHeight() / 2);
        }
    }

    private void draw_GameOver(Graphics2D g2d){
        if(timeRemaining >= 90 && timeRemaining < 160){
            g2d.drawImage(Assets.GAME_OVER[0], 200, 380, null);
        }
        else if(timeRemaining >= 0 && timeRemaining < 90){
            g2d.drawImage(Assets.GAME_OVER[0], 200, 380, null);
            g2d.drawImage(Assets.GAME_OVER[1], 210, 500, null);
        }
    }

    private void draw_YouWin(Graphics2D g2d){
        if(timeRemaining >= 90 && timeRemaining < 125){
            g2d.drawImage(Assets.YOU_WIN[0], 280, 330,  null);
        }
        else if(timeRemaining >= 0 && timeRemaining < 90){
            g2d.drawImage(Assets.YOU_WIN[0], 280, 330, null);
            g2d.drawImage(Assets.YOU_WIN[1], 280, 530, null);
        }
    }

    private void draw_TheDisorientator_cutscene(Graphics2D g2d){
        if(timeRemaining == 260) Sound.play(Sound.BOSS_BANNER_SOUND);

        if(timeRemaining >= 590) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[0], 0, 0, null);
        else if(timeRemaining >= 580) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[1], 0, 0, null);
        else if(timeRemaining >= 570) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[2], 0, 0, null);
        else if(timeRemaining >= 560) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[3], 0, 0, null);
        else if(timeRemaining >= 470) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[4], 0, 0, null);
        else if(timeRemaining >= 460) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[5], 0, 0, null);
        else if(timeRemaining >= 450) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[6], 0, 0, null);
        else if(timeRemaining >= 440) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[7], 0, 0, null);
        else if(timeRemaining >= 350) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[8], 0, 0, null);
        else if(timeRemaining >= 340) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[9], 0, 0, null);
        else if(timeRemaining >= 330) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[10], 0, 0, null);
        else if(timeRemaining >= 320) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[11], 0, 0, null);
        else if(timeRemaining >= 230) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[12], 0, 0, null);
        else if(timeRemaining >= 140) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[13], 0, 0, null);
        else if(timeRemaining >= 130) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[14], 0, 0, null);
        else if(timeRemaining >= 120) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[15], 0, 0, null);
        else if(timeRemaining >= 110) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[16], 0, 0, null);
        else if(timeRemaining >= 100) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[17], 0, 0, null);
        else if(timeRemaining >= 90) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[18], 0, 0, null);
        else if(timeRemaining >= 0) g2d.drawImage(Assets.THE_DISORIENTATOR_CUTSCENE[19], 0, 0, null);
    }
    
    private void theDisorientatorSkillActivation_cutscene_setup() {
        bossFight_cutscene_generalSetup();
        BossFights bossFight = (BossFights) ENV;
        new Thread(() -> {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ((BossTheDisorientator)(bossFight.getBoss())).setSkillSound_clip(Sound.playLoop(Sound.DISORIENTATOR_SKILL_LOOPABLE_SOUND));
        }).start();
    }

    private void draw_TheDisorientatorSkillActivation_cutscene(Graphics2D g2d){
        BossFights bossFight = (BossFights) ENV;
        bossFight.drawGame(g2d);
        if(timeRemaining > 110) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[0], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else if(timeRemaining > 92) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[1], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else if(timeRemaining > 74) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[2], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL, bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
    }

    private void theDisorientatorSkillDeactivation_cutscene_setup() {
        bossFight_cutscene_generalSetup();
        BossFights bossFight = (BossFights) ENV;
        new Thread(() -> {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Clip clip = ((BossTheDisorientator)(bossFight.getBoss())).getSkillSound_clip();
            if(clip != null && clip.isRunning()) Sound.stopClip(clip);
        }).start();
    }

    private void draw_TheDisorientatorSkillDeactivation_cutscene(Graphics2D g2d){
        BossFights bossFight = (BossFights) ENV;
        bossFight.drawGame(g2d);
        if(timeRemaining > 64) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL, bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else if(timeRemaining > 46) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[2], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else if(timeRemaining > 28) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[1], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
        else g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[0], bossFight.getBoss().getX(), bossFight.getBoss().getY(), null);
    }

    public void skip(){
        gl.setGameState(ENV);
        Sound.stop();
    }
}
