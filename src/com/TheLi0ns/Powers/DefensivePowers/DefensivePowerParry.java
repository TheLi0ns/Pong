package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Utility.Assets;

import java.awt.*;

/**
 * @see DefensivePowersEnum#PARRY
 */
public class DefensivePowerParry extends DefensivePowers_super {

    private final Image PARRY_IMAGE = Assets.PARRY_RACKET;

    public DefensivePowerParry(Player player) {
        super(player);
    }

    /**
     * Make the racket block any shot for 100ms
     */
    @Override
    public void activate() {
        new Thread(() -> {
            Image previous_player_image = player.getPLAYER_IMAGE();
            int x_tmp = player.getX();

            player.setParrying(true);
            player.setPLAYER_IMAGE(PARRY_IMAGE);
            player.setX(0);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            player.setPLAYER_IMAGE(previous_player_image);
            player.setX(x_tmp);
            player.setParrying(false);
        }).start();
    }

    @Override
    public String getName() {
        return DefensivePowersEnum.PARRY.name;
    }
}
