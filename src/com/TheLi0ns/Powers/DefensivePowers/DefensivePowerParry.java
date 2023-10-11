package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * @see DefensivePowersEnum#PARRY
 */
public class DefensivePowerParry extends DefensivePowers_super {

    private final Image PARRY_IMAGE;

    public DefensivePowerParry(Player player) {
        super(player);
        PARRY_IMAGE = player.getPos() == Player.Positions.DOWN ?
                Assets.PARRY_RACKET : Utils.flipImageVertically(Assets.PARRY_RACKET);
    }

    /**
     * Make the racket block any shot for 100ms
     */
    @Override
    public void performAction() {
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
