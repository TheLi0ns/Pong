package com.TheLi0ns.MenusHandling.SubMenus;

import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;

import java.awt.*;

public class GameModeSubMenu extends SubMenu {

    /**
     * Start a pvp match
     * @see CenteredOption
     */
    public static final CenteredOption PVP_OPTION = new CenteredOption("PvP", 1);

    /**
     * Start a pve match
     * @see CenteredOption
     */
    public static final CenteredOption PVE_OPTION = new CenteredOption("PvE", 2);

    public GameModeSubMenu() {
        super(2, 100);
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

        else if(selectedOption == PVE_OPTION.ID){
            MyFrame.gameLogic.setGameMode(GameLogic.GameModes.PVE);
            if(MyFrame.gameLogic.arePowersEnabled())MyFrame.gameLogic.setGameState(GameLogic.GameStates.SELECTING_POWERS);
            else MyFrame.gameLogic.startMatch();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {

        int y = 460;
        int y_offset = 60;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", 0, 30);
        g2d.setFont(font);

        y = PVP_OPTION.draw(y, 0, PVP_OPTION.isSelected(selectedOption), g2d);

        y = PVE_OPTION.draw(y, y_offset, PVE_OPTION.isSelected(selectedOption), g2d);
    }
}
