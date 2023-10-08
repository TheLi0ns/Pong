package com.TheLi0ns.Cutscenes;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Bosses.BossTheDisorientator;
import com.TheLi0ns.Logic.GameModes.BossFights;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

import javax.sound.sampled.Clip;
import java.awt.*;

import static com.TheLi0ns.Logic.GameLogic.GameStates;

public class CutsceneHandler {

    static CutsceneEnum currentCutscene;
    static int timeRemaining = 0;

    static GameStates previousGameState;

    public static void draw(Graphics2D g2d){
        if(timeRemaining == 0) MyFrame.gameLogic.setGameState(previousGameState);
        else timeRemaining--;
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

    public static void playCutscene(CutsceneEnum cutscene){
        currentCutscene = cutscene;
        timeRemaining = cutscene.duration + 1;
        if(MyFrame.gameLogic.getGameState() != GameStates.CUTSCENE){
            previousGameState = MyFrame.gameLogic.getGameState();
        }
        MyFrame.gameLogic.setGameState(GameStates.CUTSCENE);

        switch (currentCutscene){
            case ThePyromancer_Cutscene -> bossFight_cutscene_generalSetup();
            case TheShrinker_Cutscene -> bossFight_cutscene_generalSetup();
            case TheShrinker_SecondPhase -> theShrinkerSecondPhase_cutscene_setup();
            case TheDisorientator_Cutscene -> bossFight_cutscene_generalSetup();
            case TheDisorientator_SkillActivation -> theDisorientatorSkillActivation_cutscene_setup();
            case TheDisorientator_SkillDeactivation -> theDisorientatorSkillDeactivation_cutscene_setup();
            case GameOver -> Sound.play(Sound.GAME_OVER_SOUND);
            case YOU_WIN -> Sound.play(Sound.YOU_WIN_SOUND);
        }
    }

    private static void bossFight_cutscene_generalSetup(){
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        ENV.getFighter().setLeftPressed(false);
        ENV.getFighter().setRightPressed(false);
        ENV.getFighter().setParrying(false);
    }

    private static void draw_ThePyromancer_cutscene(Graphics2D g2d){
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

    private static void draw_TheShrinker_cutscene(Graphics2D g2d){
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
    
    private static void theShrinkerSecondPhase_cutscene_setup() {
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        ENV.getBoss().setX(391);
        ENV.getFighter().setX(391);
        ENV.getFighter().setPLAYER_IMAGE(ENV.getFighter().getNORMAL_PLAYER_IMAGE());
        ENV.getFighter().setLeftPressed(false);
        ENV.getFighter().setRightPressed(false);
        ENV.getFighter().setParrying(false);
        ENV.getBall().setX(472);
        ENV.getBall().setY(448);
        Sound.play(Sound.GIGALASER_SOUND);
    }

    private static void draw_TheShrinkerSecondPhase_cutscene(Graphics2D g2d){
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        if(timeRemaining == 135){
            ENV.getBoss().setBOSS_IMAGE(Assets.THE_SHRINKER_SECOND_PHASE);
        }
        else if(timeRemaining == 60){
            ENV.getBall().setShrunk_lock(true);
            if(ENV.getBall().getVelocity().getYDirection() == Directions.UP) ENV.getBall().getVelocity().flipVertically();
            ENV.getBall().shrinkBall(ENV.getBoss());
            ENV.getFighter().setPLAYER_IMAGE(Assets.MEDIUM_SHRUNK_RACKET);
            ENV.getFighter().setX(409);
        }
        else if(timeRemaining == 45){
            ENV.getFighter().setPLAYER_IMAGE(Assets.SHRUNK_RACKET);
            ENV.getFighter().setX(426);
        }

        ENV.drawGame(g2d);

        g2d.setColor(new Color(0, 75, 212));

        if(timeRemaining >= 105 && timeRemaining <= 120){
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2,
                         ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getFighter().getY() + ENV.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 90){
            g2d.setStroke(new BasicStroke(20));
            g2d.drawLine(ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2,
                         ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getFighter().getY() + ENV.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 75){
            g2d.setStroke(new BasicStroke(30));
            g2d.drawLine(ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2,
                         ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getFighter().getY() + ENV.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 30){
            g2d.setStroke(new BasicStroke(20));
            g2d.drawLine(ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2,
                         ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getFighter().getY() + ENV.getFighter().getHeight() / 2);
        }
        else if(timeRemaining >= 15){
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2,
                         ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2,
                         ENV.getFighter().getY() + ENV.getFighter().getHeight() / 2);
        }
    }

    private static void draw_GameOver(Graphics2D g2d){
        if(timeRemaining >= 90 && timeRemaining < 160){
            g2d.drawImage(Assets.GAME_OVER[0], 200, 380, null);
        }
        else if(timeRemaining >= 0 && timeRemaining < 90){
            g2d.drawImage(Assets.GAME_OVER[0], 200, 380, null);
            g2d.drawImage(Assets.GAME_OVER[1], 210, 500, null);
        }
    }

    private static void draw_YouWin(Graphics2D g2d){
        if(timeRemaining >= 90 && timeRemaining < 125){
            g2d.drawImage(Assets.YOU_WIN[0], 280, 330,  null);
        }
        else if(timeRemaining >= 0 && timeRemaining < 90){
            g2d.drawImage(Assets.YOU_WIN[0], 280, 330, null);
            g2d.drawImage(Assets.YOU_WIN[1], 280, 530, null);
        }
    }

    private static void draw_TheDisorientator_cutscene(Graphics2D g2d){
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
    
    private static void theDisorientatorSkillActivation_cutscene_setup() {
        bossFight_cutscene_generalSetup();
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        new Thread(() -> {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ((BossTheDisorientator)(ENV.getBoss())).setSkillSound_clip(Sound.playLoop(Sound.DISORIENTATOR_SKILL_LOOPABLE_SOUND));
        }).start();
    }

    private static void draw_TheDisorientatorSkillActivation_cutscene(Graphics2D g2d){
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        ENV.drawGame(g2d);
        if(timeRemaining > 110) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[0], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else if(timeRemaining > 92) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[1], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else if(timeRemaining > 74) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[2], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL, ENV.getBoss().getX(), ENV.getBoss().getY(), null);
    }

    private static void theDisorientatorSkillDeactivation_cutscene_setup() {
        bossFight_cutscene_generalSetup();
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        new Thread(() -> {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Clip clip = ((BossTheDisorientator)(ENV.getBoss())).getSkillSound_clip();
            if(clip != null && clip.isRunning()) Sound.stopClip(clip);
        }).start();
    }

    private static void draw_TheDisorientatorSkillDeactivation_cutscene(Graphics2D g2d){
        BossFights ENV = (BossFights) MyFrame.gameLogic.getGameMode();
        ENV.drawGame(g2d);
        if(timeRemaining > 64) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL, ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else if(timeRemaining > 46) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[2], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else if(timeRemaining > 28) g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[1], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
        else g2d.drawImage(Assets.THE_DISORIENTATOR_SKILL_ACTIVATION[0], ENV.getBoss().getX(), ENV.getBoss().getY(), null);
    }

    public static void skip(){
        MyFrame.gameLogic.setGameState(previousGameState);
        Sound.stop();
    }
}
