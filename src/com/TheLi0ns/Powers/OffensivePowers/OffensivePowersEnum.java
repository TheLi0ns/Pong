package com.TheLi0ns.Powers.OffensivePowers;

/**
 * Enum that contains all the defensive powers
 */
public enum OffensivePowersEnum {

    /**
     * Increase the ball speed
     * after the collision
     * @see OffensivePowerFireShot
     */
    FIRE_SHOT("FIRE SHOT"),

    /**
     * Inverts the controls
     * of the opponent
     * @see OffensivePowerInvertedControls
     */
    INVERTED_CONTROLS("INVERTED"),

    /**
     * Shrinks the opponent racket
     * @see OffensivePowerShrink
     */
    SHRINK("SHRINK"),

    /**
     * If the ball is going against the opponents
     * shrinks it until he hit it
     */
    SMALL_BALL("SMALL BALL");

    public final String name;

    OffensivePowersEnum(String name){
        this.name = name;
    }

    public static OffensivePowersEnum powerNamed(String name){
        for(OffensivePowersEnum i : OffensivePowersEnum.values()){
            if(name.equals(i.name)) return i;
        }
        return null;
    }
}
