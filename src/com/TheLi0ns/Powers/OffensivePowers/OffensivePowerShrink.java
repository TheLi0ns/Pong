package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Logic.GameLogic;
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

    private Image previous_opponent_image;

    /**
     * @param player   the player who has this power
     * @param opponent the opponent who get the negative effect
     */
    public OffensivePowerShrink(Player player, Player opponent) {
        super(player, opponent);
    }

    /**
     * Shrinks the opponent racket for 7 secs
     */
    @Override
    public void activate() {
        new Thread(() -> {
            previous_opponent_image = opponent.getPLAYER_IMAGE();
            if(previous_opponent_image == Assets.PARRY_RACKET) previous_opponent_image = opponent.getNORMAL_PLAYER_IMAGE();
            effectOnOpponent(true);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //CHECK IF THE MATCH IS STILL GOING
            if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING) effectOnOpponent(false);
        }).start();
    }

    @Override
    protected void effectOnOpponent(boolean on_off) {
        if(on_off){
            opponent.setPLAYER_IMAGE(SHRUNK_PLAYER_IMAGE);
            opponent.setX(opponent.getX() + 35);
            Sound.play(SHRINK_SOUND[1]);
        }
        else{
            opponent.setPLAYER_IMAGE(previous_opponent_image);
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
