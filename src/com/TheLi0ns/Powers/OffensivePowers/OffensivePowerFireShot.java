package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameObject.Player;

/**
 * @see OffensivePowersEnum#FIRE_SHOT
 */
public class OffensivePowerFireShot extends OffensivePowers_super{

    public OffensivePowerFireShot(Player player, Player opponent) {
        super(player, opponent);
    }

    /**
     * Make the fireshot active for 5 secs
     */
    @Override
    public void activate() {
        player.setFireShotActivated(true);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setFireShotActivated(false);
        }).start();
    }

    @Override
    protected void effectOnOpponent(boolean on_off) {}

    @Override
    public String getName() {
        return OffensivePowersEnum.FIRE_SHOT.name;
    }
}
