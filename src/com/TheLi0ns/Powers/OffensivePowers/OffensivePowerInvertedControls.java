package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameObject.Players.Player;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;
import java.net.URL;

/**
 * @see OffensivePowersEnum#INVERTED_CONTROLS
 */
public class OffensivePowerInvertedControls extends OffensivePowers_super{

    private final Image INVERTED_PLAYER_IMAGE = Assets.INVERTED_RACKET;

    private final URL INVERTED_CONTROLS_SOUND = Sound.INVERTED_CONTROLS_SOUND;

    /**
     * @param player   the player who has this power
     * @param opponent the opponent who get the negative effect
     */
    public OffensivePowerInvertedControls(Player player, Player opponent) {
        super(player, opponent);
    }

    /**
     * Inverts the opponent controls for 7 secs
     */
    @Override
    public void performAction() {
        effectOnOpponent(true);
        powerThread_onPlayer = new Thread(() -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //CHECK IF THE MATCH IS STILL GOING
            if(!Thread.currentThread().isInterrupted()) effectOnOpponent(false);
        });
        powerThread_onPlayer.start();
    }

    @Override
    protected void effectOnOpponent(boolean on_off) {
        opponent.setAreControlsInverted(on_off);

        if(on_off) {
            Sound.play(INVERTED_CONTROLS_SOUND);
            opponent.setPLAYER_IMAGE(INVERTED_PLAYER_IMAGE);
        }
        else opponent.setPLAYER_IMAGE(opponent.getNORMAL_PLAYER_IMAGE());
    }

    @Override
    public String getName() {
        return OffensivePowersEnum.INVERTED_CONTROLS.name;
    }
}
