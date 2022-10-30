package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;
import java.net.URL;

/**
 * @see DefensivePowersEnum#SPEED
 */
public class DefensivePowerSpeed extends DefensivePowers_super {
    private final Image SPEEDY_PLAYER_IMAGE = Assets.SPEEDY_RACKET;

    private final URL[] SPEED_SOUND = Sound.SPEED_SOUND;

    public DefensivePowerSpeed(Player player) {
        super(player);
    }

    /**
     * Increase the xVelocity for 7 secs
     */
    @Override
    public void activate() {
        new Thread(() -> {
            int initialxVelocity = player.getxVelocity();

            player.setPLAYER_IMAGE(SPEEDY_PLAYER_IMAGE);
            player.setxVelocity(15);
            Sound.play(SPEED_SOUND[1]);

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            player.setxVelocity(initialxVelocity);
            Sound.play(SPEED_SOUND[0]);
            player.setPLAYER_IMAGE(player.getNORMAL_PLAYER_IMAGE());
        }).start();
    }

    @Override
    public String getName() {
        return DefensivePowersEnum.SPEED.name;
    }
}
