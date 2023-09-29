package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Player;

/**
 * Super-class of all the defensive powers
 * @see DefensivePowersEnum
 */
public abstract class DefensivePowers_super {

    Player player;

    /**
     * @param player the player who has this power
     */
    DefensivePowers_super(Player player){
        this.player = player;
    }

    /**
     * The method to define what the power do
     */
    public abstract void activate();

    public abstract String getName();
}
