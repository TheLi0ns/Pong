package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;
import java.net.URL;

/**
 * @see OffensivePowersEnum#SHRINK
 */
public class OffensivePowerShrink extends OffensivePowers_super{

    private final Image SHRUNK_PLAYER_IMAGE = Assets.SHRUNK_RACKET;

    private final URL[] SHRINK_SOUND = Sound.SHRINK_SOUND;

    public OffensivePowerShrink(Player player, Player opponent) {
        super(player, opponent);
    }

    /**
     * Shrinks the opponent racket for 7 secs
     */
    @Override
    public void activate() {
        effectOnOpponent(true);
        new Thread(() -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            effectOnOpponent(false);
        }).start();
    }

    @Override
    protected void effectOnOpponent(boolean on_off) {
        if(on_off){
            opponent.setPLAYER_IMAGE(SHRUNK_PLAYER_IMAGE);
            opponent.updateWidth();
            opponent.setX(opponent.getX() + 35);
            Sound.play(SHRINK_SOUND[1]);
        }
        else{
            opponent.setPLAYER_IMAGE(opponent.getNORMAL_PLAYER_IMAGE());
            opponent.updateWidth();
            opponent.setX(opponent.getX() - 35);
            Sound.play(SHRINK_SOUND[0]);
        }

        if(opponent.getX() > GamePanel.RIGHT_BOUND){
            opponent.setX(GamePanel.RIGHT_BOUND - opponent.getWidth());
        }else if(opponent.getX() < GamePanel.LEFT_BOUND){
            opponent.setX(GamePanel.LEFT_BOUND);
        }
    }

    @Override
    public String getName() {
        return OffensivePowersEnum.SHRINK.name;
    }
}
