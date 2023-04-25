package com.TheLi0ns.GameObject.Bosses;

import com.TheLi0ns.Cutscenes.CutsceneEnum;
import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * @see BossEnum#THE_SHRINKER
 */
public class BossTheShrinker extends Boss_super{

    final int RAY_DURATION = 3;
    int ray_remaining_time;
    int[] rayPos = new int[4];

    public BossTheShrinker(BossFights env) {
        super(12, Assets.THE_SHRINKER_FIRST_PHASE[0], new Color(0, 75, 212), env);
    }

    @Override
    public boolean skillCondition() {
        return hitPerRound == 2 && !secondPhase;
    }

    @Override
    public void activateSkill() {
        this.BOSS_IMAGE = Assets.THE_SHRINKER_FIRST_PHASE[1];
        this.smallBallActivated = true;
        Sound.play(Sound.LASER_SOUND);
        ENV.getBall().setShrunk_lock(true);
        ray_remaining_time = RAY_DURATION;
        //Points for the ray
        rayPos[0] = ENV.getBoss().getX() + ENV.getBoss().getWidth() / 2;
        rayPos[1] = ENV.getBoss().getY() + ENV.getBoss().getHeight() / 2;
        rayPos[2] = ENV.getBall().getX() + ENV.getBall().getWidth() / 2;
        rayPos[3] = ENV.getBall().getY() + ENV.getBall().getHeight() / 2;
    }

    @Override
    public boolean secondPhaseCondition() {
        return health == 6;
    }

    @Override
    public void enterSecondPhase() {
        CutsceneHandler.playCutscene(CutsceneEnum.TheShrinker_SecondPhase);
    }

    @Override
    public void secondPhaseSkill() {
        if(ENV.getBall().getVelocity().getYDirection() == Directions.UP) ENV.getBall().getVelocity().flipVertically();
        this.smallBallActivated = true;
        ENV.getBall().setShrunk_lock(true);
    }

    @Override
    public void roundFinished() {
        super.roundFinished();
        if(!secondPhase) this.BOSS_IMAGE = Assets.THE_SHRINKER_FIRST_PHASE[0];
        else secondPhaseSkill();
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if(ray_remaining_time > 0){
            Stroke base_stroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(BASE_COLOR);
            g2d.drawLine(rayPos[0], rayPos[1], rayPos[2], rayPos[3]);
            ray_remaining_time--;
            g2d.setStroke(base_stroke);
        }
    }

    @Override
    public String getName() {
        return BossEnum.THE_SHRINKER.name;
    }
}
