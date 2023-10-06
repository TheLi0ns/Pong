package com.TheLi0ns.MenusHandling.Menus;

import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.GameLogic.GameModes;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.Logic.MiniGames.Dribble;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.MenusHandling.SubMenus.BossFightSubMenu;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Display the mini-games and make
 * the player plays one of them
 * @see MiniGamesMenu#DRIBBLE_OPTION
 * @see MiniGamesMenu#BOSS_FIGHT_OPTION
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
     * Start the {@link BossFights BossFights mini-game}
     * @see CenteredOption
     */
    public static final CenteredOption BOSS_FIGHT_OPTION = new CenteredOption("BOSS FIGHTS", 2);

    /**
     * Return to the {@link TitleScreen title screen}
     * @see CenteredOption
     */
    public static final CenteredOption BACK = new CenteredOption("BACK", 3);

    public MiniGamesMenu(GameLogic gl) {
        super(3, gl);

    }

    @Override
    public void performOption() {
        if(selectedOption == DRIBBLE_OPTION.ID){
            gl.setMiniGame(new Dribble(gl));
        }

        else if(selectedOption == BOSS_FIGHT_OPTION.ID){
            subMenu = new BossFightSubMenu(this, gl);
            isInSubMenu = true;
        }

        else if (selectedOption == BACK.ID) back();

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    protected void back() {
        super.back();
        gl.setMenu(new TitleScreen(gl));
    }

    @Override
    public void draw(Graphics2D g2d) {
        Font font = new Font("Comic Sans MS", Font.PLAIN, 40);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        int y = 300;
        int y_offset = 80;

        y = DRIBBLE_OPTION.draw(y, y_offset, DRIBBLE_OPTION.isSelected(selectedOption), g2d);

        y = BOSS_FIGHT_OPTION.draw(y, y_offset, BOSS_FIGHT_OPTION.isSelected(selectedOption), g2d);

        //SUBMENU
        if(isInSubMenu) y = subMenu.draw(g2d, y);

        y = BACK.draw(y, y_offset, BACK.isSelected(selectedOption), g2d);
    }
}
