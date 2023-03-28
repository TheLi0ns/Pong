package com.TheLi0ns.Cutscenes;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

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
            case TheShrinker_SecondPhase -> draw_TheShrinkerSecondPhase_cutscene(g2d);
            case GameOver -> draw_GameOver(g2d);
            case YOU_WIN -> draw_YouWin(g2d);
        }
    }

    public static void playCutscene(CutsceneEnum cutscene){
        currentCutscene = cutscene;
        timeRemaining = cutscene.duration + 1;
        previousGameState = MyFrame.gameLogic.getGameState();
        MyFrame.gameLogic.setGameState(GameStates.CUTSCENE);

        switch (currentCutscene){
            case TheShrinker_SecondPhase -> TheShrinkerSecondPhase_cutscene_setup();
            case GameOver -> Sound.play(Sound.GAME_OVER_SOUND);
            case YOU_WIN -> Sound.play(Sound.YOU_WIN_SOUND);
        }
    }

    private static void TheShrinkerSecondPhase_cutscene_setup() {
        BossFights ENV = (BossFights)MyFrame.gameLogic.miniGame;
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
        BossFights ENV = (BossFights)MyFrame.gameLogic.miniGame;
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

        ENV.draw(g2d);

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

    public static void skip(){
        MyFrame.gameLogic.setGameState(previousGameState);
        Sound.stop();
    }
}
