package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameObject.Player;

/**
 * Super-class of all the offensive powers
 * @see OffensivePowersEnum
 */
public abstract class OffensivePowers_super {

    Player player, opponent;

    /**
     * @param player   the player who has this power
     * @param opponent the opponent who get the negative effect
     */
    OffensivePowers_super(Player player, Player opponent){
        this.player = player;
        this.opponent = opponent;
    }

    /**
     * The method that define some effect on the player
     * and choose the time
     */
    public abstract void activate();

    /**
     * The method that define the negative effect on the opponent
     * @param on_off activate or deactivate the effect
     */
    protected abstract void effectOnOpponent(boolean on_off);

    public abstract String getName();
}
