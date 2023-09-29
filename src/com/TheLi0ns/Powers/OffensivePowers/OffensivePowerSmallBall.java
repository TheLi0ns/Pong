package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameObject.Player;

/**
 * @see OffensivePowersEnum#SMALL_BALL
 */
public class OffensivePowerSmallBall extends OffensivePowers_super{
    /**
     * @param player   the player who has this power
     * @param opponent the opponent who get the negative effect
     */
    public OffensivePowerSmallBall(Player player, Player opponent) {
        super(player, opponent);
    }

    @Override
    public void performAction() {
        player.setSmallBallActivated(true);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setSmallBallActivated(false);
        }).start();
    }

    @Override
    protected void effectOnOpponent(boolean on_off) {}

    @Override
    public String getName() {
        return OffensivePowersEnum.SMALL_BALL.name;
    }
}
