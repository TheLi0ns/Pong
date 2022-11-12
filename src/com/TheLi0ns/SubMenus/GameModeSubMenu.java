package com.TheLi0ns.SubMenus;

import com.TheLi0ns.AI.AI;
import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

/**
 * Display a game mode selection sub menu
 * @see GameModeSubMenu#PVP_OPTION
 * @see GameModeSubMenu#NORMAL_PVE_OPTION
 * @see GameModeSubMenu#IMPOSSIBLE_PVE_OPTION
 */
public class GameModeSubMenu extends SubMenu {

    //GAME MODE MENU OPTIONS

    /**
     * Start a pvp match
     */
    public static final int PVP_OPTION = 1;

    /**
     * Start a pve match with a normal AI
     */
    public static final int NORMAL_PVE_OPTION = 2;

    /**
     * Start a pve match with an impossible AI
     */
    public static final int IMPOSSIBLE_PVE_OPTION = 3;

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
        switch (selectedOption){
            case PVP_OPTION -> {
                MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVP);
                if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
                else MyFrame.gameLogic.startMatch();
            }

            case NORMAL_PVE_OPTION -> {
                difficultyChosen = AI.Difficulties.NORMAL;
                MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
                if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
                else MyFrame.gameLogic.startMatch();
            }

            case IMPOSSIBLE_PVE_OPTION -> {
                difficultyChosen = AI.Difficulties.IMPOSSIBLE;
                MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
                if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
                else MyFrame.gameLogic.startMatch();
            }
        }
        selectedOption = 1;
    }

    @Override
    public void draw(Graphics2D g2d) {

        int x;
        int y = 460;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);

        //PVP
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "PvP");
        g2d.drawString("PvP", x, y);
        if(selectedOption == PVP_OPTION) g2d.drawString(">", x-30, y);

        //PVE NORMAL
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "PVE NORMAL");
        g2d.drawString("PvE NORMAL", x, y);
        if(selectedOption == NORMAL_PVE_OPTION) g2d.drawString(">", x-30, y);

        //PVE IMPOSSIBLE
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "PVE IMPOSSIBLE");
        g2d.drawString("PvE IMPOSSIBLE", x, y);
        if(selectedOption == IMPOSSIBLE_PVE_OPTION) g2d.drawString(">", x-30, y);
    }
}
