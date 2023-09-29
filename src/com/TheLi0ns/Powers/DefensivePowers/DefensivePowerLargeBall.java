package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Player;

/**
 * @see DefensivePowersEnum#LARGE_BALL
 */
public class DefensivePowerLargeBall extends DefensivePowers_super{
    /**
     * @param player the player who has this power
     */
    public DefensivePowerLargeBall(Player player) {
        super(player);
    }

    @Override
    public void activate() {
        player.setStretchBallActivated(true);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setStretchBallActivated(false);
        }).start();
    }

    @Override
    public String getName() {
        return DefensivePowersEnum.LARGE_BALL.name;
    }
}
