package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic.GameModes;
import com.TheLi0ns.Logic.GameLogic.GameStates;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Display the mini-games and make
 * the player plays one of them
 * @see MiniGamesMenu#DRIBBLE_OPTION
 */
public class MiniGamesMenu extends Menu{

    //MINI-GAMES MENU OPTION
    /**
     * To play dribble mini-game
     * @see GameModes#DRIBBLE
     * @see CenteredOption
     */
    public static final CenteredOption DRIBBLE_OPTION = new CenteredOption("DRIBBLE", 1);

    /**
     * Return to the {@link TitleScreen title screen}
     * @see CenteredOption
     */
    public static final CenteredOption BACK = new CenteredOption("BACK", 2);

    public MiniGamesMenu() {
        super(2);
    }

    @Override
    public void clickOption() {
        if(selectedOption == DRIBBLE_OPTION.ID){

        }

        else if (selectedOption == BACK.ID) back();

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    protected void back() {
        super.back();
        MyFrame.gameLogic.setGameState(GameStates.TITLE_SCREEN);
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", Font.PLAIN, 40);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int y = 300;
        int y_offset = 80;

        y = DRIBBLE_OPTION.draw(y, y_offset, DRIBBLE_OPTION.isSelected(selectedOption), g2d);
        y = BACK.draw(y, y_offset, BACK.isSelected(selectedOption), g2d);
    }
}
