package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Player;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Assets;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;
import java.net.URL;

/**
 * @see DefensivePowersEnum#LARGE_RACKET
 */
public class DefensivePowerLargeRacket extends DefensivePowers_super {

    private final Image LARGE_PLAYER_IMAGE = Assets.LARGE_RACKET;

    private final URL[] LARGE_SOUND = Sound.LARGE_SOUND;

    public DefensivePowerLargeRacket(Player player) {
        super(player);
    }

    /**
     * Stretches the racket for 7 secs
     */
    @Override
    public void performAction() {
        new Thread(() -> {
            Image previous_player_image = player.getPLAYER_IMAGE() == Assets.PARRY_RACKET ?
                                            player.getNORMAL_PLAYER_IMAGE() : player.getPLAYER_IMAGE();
            player.setPLAYER_IMAGE(LARGE_PLAYER_IMAGE);
            Sound.play(LARGE_SOUND[1]);
            player.setX(player.getX() - 35);

            if(player.getX() < GamePanel.LEFT_BOUND){
                player.setX(GamePanel.LEFT_BOUND);
            }else if(player.getX() + player.getWidth() > GamePanel.RIGHT_BOUND){
                player.setX(GamePanel.RIGHT_BOUND - player.getWidth());
            }

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //CHECK IF THE MATCH IS STILL GOING
            if(MyFrame.gameLogic.getGameState() == GameLogic.GameStates.PLAYING){
                player.setX(player.getX() + 35);
                player.setPLAYER_IMAGE(previous_player_image);
                Sound.play(LARGE_SOUND[0]);
            }
        }).start();
    }

    @Override
    public String getName() {
        return DefensivePowersEnum.LARGE_RACKET.name;
    }
}
