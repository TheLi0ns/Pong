package com.TheLi0ns.SubMenus;

import com.TheLi0ns.GameFrame.GamePanel;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Utils;

import java.awt.*;

public class GameModeSubMenu extends SubMenu {

    //GAME MODE MENU OPTIONS

    /**
     * Start a pvp match
     */
    public static final int PVP_OPTION = 1;

    /**
     * Start a pve match
     */
    public static final int PVE_OPTION = 2;

    public GameModeSubMenu() {
        super(2, 100);
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

            case PVE_OPTION -> {
                MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
                if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
                else MyFrame.gameLogic.startMatch();
            }
        }
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

        //PVE
        y += 60;
        x = Utils.xForCenteredText(g2d, font, GamePanel.WIDTH, "PvE");
        g2d.drawString("PvE", x, y);
        if(selectedOption == PVE_OPTION) g2d.drawString(">", x-30, y);
    }
}
