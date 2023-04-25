package com.TheLi0ns.GameObject.Bosses;

import com.TheLi0ns.Cutscenes.CutsceneEnum;
import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;

import javax.sound.sampled.Clip;
import java.awt.*;

/**
 * @see BossEnum#THE_DISORIENTATOR
 */
public class BossTheDisorientator extends Boss_super {

    Clip skillSound_clip;

    public BossTheDisorientator(BossFights env) {
        super(9, Assets.THE_DISORIENTATOR, new Color(179, 2, 254), env);
    }

    @Override
    public boolean skillCondition() {
        return true;
    }

    @Override
    public void activateSkill() {
        if(secondPhase) secondPhaseSkill();

        else if(hitPerRound == 2){
            if(ENV.getFighter().isParrying()){
                skillActivated = false;
                return;
            }
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                CutsceneHandler.playCutscene(CutsceneEnum.TheDisorientator_SkillActivation);
                this.setBOSS_IMAGE(Assets.THE_DISORIENTATOR_SKILL);
                ENV.getFighter().setAreControlsInverted(true);
                ENV.getFighter().setPLAYER_IMAGE(Assets.INVERTED_RACKET);
            }).start();
        }
        else{
            skillActivated = false;
        }
    }

    @Override
    public boolean secondPhaseCondition() {
        return health == 3;
    }

    @Override
    public void enterSecondPhase() {}

    @Override
    public void secondPhaseSkill() {
        if (ENV.getBall().getY() > GamePanel.CENTER && ENV.getBall().getVelocity().getYDirection() == Directions.DOWN) {
            if(ENV.getFighter().isParrying()){
                skillActivated = false;
                return;
            }
            CutsceneHandler.playCutscene(CutsceneEnum.TheDisorientator_SkillActivation);
            this.setBOSS_IMAGE(Assets.THE_DISORIENTATOR_SKILL);
            ENV.getFighter().setAreControlsInverted(true);
            ENV.getFighter().setPLAYER_IMAGE(Assets.INVERTED_RACKET);

            new Thread(() -> {
                while (ENV.getBall().getVelocity().getYDirection() == Directions.DOWN){
                    //ANTIBUG
                    System.out.println("ANTIBUG");
                }
                this.skillActivated = false;
                CutsceneHandler.playCutscene(CutsceneEnum.TheDisorientator_SkillDeactivation);
                this.setBOSS_IMAGE(Assets.THE_DISORIENTATOR);
                ENV.getFighter().setAreControlsInverted(false);
                ENV.getFighter().setPLAYER_IMAGE(ENV.getFighter().getNORMAL_PLAYER_IMAGE());
            }).start();
        }

        else skillActivated = false;
    }

    @Override
    public void roundFinished() {
        if(this.skillActivated && !ENV.isFinished()){
            CutsceneHandler.playCutscene(CutsceneEnum.TheDisorientator_SkillDeactivation);
            this.setBOSS_IMAGE(Assets.THE_DISORIENTATOR);
            skillActivated = false;
            ENV.getFighter().setAreControlsInverted(false);
            ENV.getFighter().setPLAYER_IMAGE(ENV.getFighter().getNORMAL_PLAYER_IMAGE());
        }
        super.roundFinished();
    }

    public Clip getSkillSound_clip() {
        return skillSound_clip;
    }

    public void setSkillSound_clip(Clip skillSound_clip) {
        this.skillSound_clip = skillSound_clip;
    }

    @Override
    public String getName() {
        return "The Disorientator";
    }
}
