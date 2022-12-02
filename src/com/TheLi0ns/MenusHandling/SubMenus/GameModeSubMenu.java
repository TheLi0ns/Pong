package com.TheLi0ns.MenusHandling.SubMenus;

import com.TheLi0ns.AI.AI;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Display a game mode selection sub menu
 * @see GameModeSubMenu#PVP_OPTION
 * @see GameModeSubMenu#NORMAL_PVE_OPTION
 * @see GameModeSubMenu#IMPOSSIBLE_PVE_OPTION
 */
public class GameModeSubMenu extends SubMenu {

    /**
     * Start a pvp match
     * @see CenteredOption
     */
    public static final CenteredOption PVP_OPTION = new CenteredOption("PvP", 1);

    /**
     * Start a pve match with a normal AI
     * @see CenteredOption
     */
    public static final CenteredOption NORMAL_PVE_OPTION = new CenteredOption("PvE NORMAL", 2);

    /**
     * Start a pve match with an impossible AI
     * @see CenteredOption
     */
    public static final CenteredOption IMPOSSIBLE_PVE_OPTION = new CenteredOption("PvE IMPOSSIBLE", 3);

    private static AI.Difficulties difficultyChosen;

    public GameModeSubMenu() {
        super(3, 160);
    }

    public AI.Difficulties getDifficultyChosen() {
        return difficultyChosen;
    }

    @Override
    public void back() {
        MyFrame.gameLogic.setGameState(GameLogic.GameStates.TITLE_SCREEN);
    }

    @Override
    public void clickOption() {

        if(selectedOption == PVP_OPTION.ID){
            MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVP);
            if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
            else MyFrame.gameLogic.startMatch();
        }

        else if(selectedOption == NORMAL_PVE_OPTION.ID){
            difficultyChosen = AI.Difficulties.NORMAL;
            MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
            if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
            else MyFrame.gameLogic.startMatch();
        }

        else if(selectedOption == IMPOSSIBLE_PVE_OPTION.ID){
            difficultyChosen = AI.Difficulties.IMPOSSIBLE;
            MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
            if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
            else MyFrame.gameLogic.startMatch();
        }

        selectedOption = 1;

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int y = 460;
        int y_offset = 60;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);

        y = PVP_OPTION.draw(y, 0, PVP_OPTION.isSelected(selectedOption), g2d);

        y = NORMAL_PVE_OPTION.draw(y, y_offset, NORMAL_PVE_OPTION.isSelected(selectedOption), g2d);

        y = IMPOSSIBLE_PVE_OPTION.draw(y, y_offset, IMPOSSIBLE_PVE_OPTION.isSelected(selectedOption), g2d);
    }
}
