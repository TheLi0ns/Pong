package com.TheLi0ns.Powers.DefensivePowers;

/**
 * Enum that contains all the defensive powers
 */
public enum DefensivePowersEnum {

    /**
     * Increase the xVelocity
     * @see DefensivePowerSpeed
     */
    SPEED("SPEED"),

    /**
     * Stretches the racket
     * @see DefensivePowerLargeRacket
     */
    LARGE_RACKET("LARGE RACKET"),

    /**
     * Make the racket block any shot for 200ms
     * @see DefensivePowerParry
     */
    PARRY("PARRY");

    public final String name;

    DefensivePowersEnum(String name){
        this.name = name;
    }

    public static DefensivePowersEnum powerNamed(String name){
        for(DefensivePowersEnum i : DefensivePowersEnum.values()){
            if(name.equals(i.name)) return i;
        }
        return null;
    }
}
