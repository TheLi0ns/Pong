package com.TheLi0ns.GameObject;

import com.TheLi0ns.Powers.DefensivePowers.DefensivePowerParry;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * The player during bossfights.
 * It has 3 HP and can parry 5 times/round
 */
public class Fighter extends Player{

    final Image[] HEART_IMAGES = Assets.HEARTS;
    int health = 3;
    final int MAX_PARRY_PER_ROUND = 5;
    int parry_counter = 5;

    DefensivePowerParry parry = new DefensivePowerParry(this);

    public Fighter() {
        super(391, 909, 6);
    }

    public int getHealth() {
        return health;
    }

    /**
     * Makes the player {@link DefensivePowerParry parry} and decrease the parry counter
     */
    public void parry(){
        if(parry_counter > 0) parry.activate();
        parry_counter--;
    }

    /**
     * Decreases fighter's health
     * and play the sound
     * @return true if the fighter died
     */
    public boolean damaged(){
        Sound.play(Sound.FIGHTER_DAMAGED_SOUND);

        this.health--;
        if(health == 0) return true;

        return false;
    }

    /**
     * set to 0 the hit/round
     * reset the parry counter
     */
    public void roundFinished(){
        this.resetHitPerRound();
        this.resetParryCounter();
    }

    public void resetParryCounter(){
        parry_counter = MAX_PARRY_PER_ROUND;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        //STAMINA BAR
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(10, 966, 304, 20);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(12, 968, 300 - (MAX_PARRY_PER_ROUND - parry_counter)*(300/MAX_PARRY_PER_ROUND), 16);

        //HEARTS
        if(health >= 3){
            g2d.drawImage(HEART_IMAGES[1], 850, 955, null);
        }else g2d.drawImage(HEART_IMAGES[0], 860, 965, null);

        if(health >= 2){
            g2d.drawImage(HEART_IMAGES[1], 895, 955, null);
        }else g2d.drawImage(HEART_IMAGES[0], 905, 965, null);

        if(health >= 1){
            g2d.drawImage(HEART_IMAGES[1], 940, 955, null);
        }else g2d.drawImage(HEART_IMAGES[0], 950, 965, null);
    }
}
