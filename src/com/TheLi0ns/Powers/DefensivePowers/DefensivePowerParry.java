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
     * Make the racket block any shot for 200ms
     */
    @Override
    public void activate() {
        new Thread(() -> {
            int x_tmp = player.getX();

            player.setParrying(true);
            player.setPLAYER_IMAGE(PARRY_IMAGE);
            player.updateWidth();
            player.setX(0);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            player.setPLAYER_IMAGE(player.getNORMAL_PLAYER_IMAGE());
            player.updateWidth();
            player.setX(x_tmp);
            player.setParrying(false);
        }).start();
    }

    @Override
    public String getName() {
        return DefensivePowersEnum.PARRY.name;
    }
}
