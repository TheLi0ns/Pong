package com.TheLi0ns.MenusHandling.SubMenus;

import com.TheLi0ns.GameObject.ComputerPlayer;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.MenusHandling.Menus.Menu;
import com.TheLi0ns.MenusHandling.Menus.PowersSelectionMenu_PvE;
import com.TheLi0ns.MenusHandling.Menus.PowersSelectionMenu_PvP;
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
     * Start a pve match with a normal ComputerPlayer
     * @see CenteredOption
     */
    public static final CenteredOption NORMAL_PVE_OPTION = new CenteredOption("PvE NORMAL", 2);

    /**
     * Start a pve match with an impossible ComputerPlayer
     * @see CenteredOption
     */
    public static final CenteredOption IMPOSSIBLE_PVE_OPTION = new CenteredOption("PvE IMPOSSIBLE", 3);

    public GameModeSubMenu(Menu menu, GameLogic gl) {
        super(3, 150, menu, gl);
    }

    @Override
    public void performOption() {

        if(selectedOption == PVP_OPTION.ID){
            gl.setGameMode(GameLogic.GameModes.PVP);
            if(gl.arePowersEnabled() && gl.isDefensivePowerRechargeable()) gl.setMenu(new PowersSelectionMenu_PvP(gl));
            else gl.startMatch();
        }

        else if(selectedOption == NORMAL_PVE_OPTION.ID){
            gl.setComputer_difficulty(ComputerPlayer.Difficulties.NORMAL);
            gl.setGameMode(GameLogic.GameModes.PVE);
            if(gl.arePowersEnabled() && gl.isDefensivePowerRechargeable()) gl.setMenu(new PowersSelectionMenu_PvE(gl));
            else gl.startMatch();
        }

        else if(selectedOption == IMPOSSIBLE_PVE_OPTION.ID){
            gl.setComputer_difficulty(ComputerPlayer.Difficulties.NORMAL);
            gl.setGameMode(GameLogic.GameModes.PVE);
            if(gl.arePowersEnabled() && gl.isDefensivePowerRechargeable()) gl.setMenu(new PowersSelectionMenu_PvE(gl));
            else gl.startMatch();
        }

        selectedOption = 1;

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void drawOptions(Graphics2D g2d, int y) {

        int y_offset = 50;
        y += y_offset;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 25);
        g2d.setFont(font);

        y = PVP_OPTION.draw(y, 0, PVP_OPTION.isSelected(selectedOption), g2d);

        y = NORMAL_PVE_OPTION.draw(y, y_offset, NORMAL_PVE_OPTION.isSelected(selectedOption), g2d);

        y = IMPOSSIBLE_PVE_OPTION.draw(y, y_offset, IMPOSSIBLE_PVE_OPTION.isSelected(selectedOption), g2d);
    }
}
