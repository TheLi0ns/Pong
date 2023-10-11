package com.TheLi0ns.GameObject.Players.Bosses;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.GameStates.GameModes.BossFights;
import com.TheLi0ns.Utility.Directions;
import com.TheLi0ns.Utility.Sound;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Super-class of bosses. They have a skill and a second phase
 */
public abstract class Boss_super extends Player {

    final BossFights ENV;

    final protected Color BASE_COLOR;

    Image BOSS_IMAGE;

    int health;

    final int MAX_HP;

    boolean skillActivated = false;

    boolean secondPhase = false;

    public Boss_super(int MAX_HP, Image BOSS_IMAGE, Color BASE_COLOR, BossFights env) {
        super(391, 53, 7);
        this.MAX_HP = MAX_HP;
        this.health = MAX_HP;
        this.BOSS_IMAGE = BOSS_IMAGE;
        this.BASE_COLOR = BASE_COLOR;
        ENV = env;
    }

    /**
     * Make the boss as smart as the bot in normal difficulty
     * activate the skill if it can be activated
     * and enter the second phase if the {@link Boss_super#secondPhaseCondition} is true
     */
    @Override
    public void update() {
        //MAKES THE BOSS FOLLOW THE BALL IF THE BALL IS MOVING AGAINST HIM
        if(ENV.getBall().getVelocity().getYDirection() == Directions.UP){
            if(ENV.getBall().getX() > this.x + this.width/2) move(Directions.RIGHT);
            else if(ENV.getBall().getX() < this.x + this.width/2) move(Directions.LEFT);
        }
        //MAKES THE BOSS RETURN TO THE CENTER IF THE BALL IS MOVING AWAY FROM HIM
        else{
            if(this.x + this.width/2 < GamePanel.CENTER) move(Directions.RIGHT);
            else if(this.x + this.width/2 > GamePanel.CENTER) move(Directions.LEFT);
        }

        if(!skillActivated && skillCondition()){
            skillActivated = true;
            activateSkill();
        }
        if(!secondPhase && secondPhaseCondition()) {
            enterSecondPhase();
            secondPhase = true;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(this.BOSS_IMAGE, this.x, this.y, null);

        Font font = new Font("Algerian", Font.PLAIN, 25);
        g2d.setFont(font);
        g2d.setColor(BASE_COLOR);

        g2d.drawString(getName(), Utils.xForCenteredText(g2d, GamePanel.WIDTH, getName()), 25);

        //HEALTH BAR

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(10, 30, 700, 20);

        g2d.setColor(Color.RED);
        g2d.fillRect(12, 32, 696 - (MAX_HP - health)*(696/MAX_HP), 16);

    }

    public int getHealth() {
        return health;
    }

    /**
     * Boss' health decreases
     * Play the sound
     * @return true if the boss had died
     */
    public boolean damaged(){
        Sound.play(Sound.BOSS_DAMAGED_SOUND);

        this.health--;

        return health == 0;
    }

    /**
     * Resets the number of hit per round
     * deactivate the skill
     */
    public void roundFinished(){
        this.resetHitPerRound();
        this.skillActivated = false;
    }

    /**
     * @return true if the skill can be activated
     */
    public abstract boolean skillCondition();

    /**
     * What the skill does
     */
    public abstract void activateSkill();

    /**
     * @return true if the second phase can be started
     */
    public abstract boolean secondPhaseCondition();

    /**
     * What to do when the boss enters the second phase
     */
    public abstract void enterSecondPhase();

    /**
     * The skill that will be always used if the boss is in second phase
     */
    public abstract void secondPhaseSkill();

    public void setBOSS_IMAGE(Image BOSS_IMAGE) {
        this.BOSS_IMAGE = BOSS_IMAGE;
    }

    public abstract String getName();

}
