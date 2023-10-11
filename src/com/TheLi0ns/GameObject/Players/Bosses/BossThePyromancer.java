package com.TheLi0ns.GameObject.Players.Bosses;

import com.TheLi0ns.GameStates.GameModes.BossFights;
import com.TheLi0ns.Utility.Assets;

import java.awt.*;

/**
 * @see BossEnum#THE_PYROMANCER
 */
public class BossThePyromancer extends Boss_super {
    public BossThePyromancer(BossFights env) {
        super(6,  Assets.THE_PYROMANCER, Color.RED, env);
    }

    @Override
    public boolean skillCondition() {
        return hitPerRound == 2 && !secondPhase;
    }

    @Override
    public void activateSkill() {
        fireShotActivated = true;
    }

    @Override
    public boolean secondPhaseCondition() {
        return health == 3;
    }

    @Override
    public void enterSecondPhase() {
        secondPhaseSkill();
    }

    @Override
    public void secondPhaseSkill() {
        ENV.getBall().fireShot(this);
    }

    /**
     * Resets the number of hit per round deactivate the skill
     * and activates the second phase skill every time
     */
    @Override
    public void roundFinished() {
        super.roundFinished();
        if(secondPhase) secondPhaseSkill();
    }

    @Override
    public String getName() {
        return BossEnum.THE_PYROMANCER.name;
    }

}
