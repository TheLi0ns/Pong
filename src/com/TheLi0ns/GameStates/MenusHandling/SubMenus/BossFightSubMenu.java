package com.TheLi0ns.GameStates.MenusHandling.SubMenus;

import com.TheLi0ns.GameObject.Players.Bosses.BossEnum;
import com.TheLi0ns.GameObject.Players.Bosses.BossThePyromancer;
import com.TheLi0ns.GameObject.Players.Bosses.BossTheShrinker;
import com.TheLi0ns.GameStates.CutscenesHandling.Cutscene;
import com.TheLi0ns.GameStates.CutscenesHandling.CutsceneEnum;
import com.TheLi0ns.GameStates.GameModes.BossFights;
import com.TheLi0ns.GameStates.GameModes.GameMode_super;
import com.TheLi0ns.GameStates.MenusHandling.Menus.Menu;
import com.TheLi0ns.GameStates.MenusHandling.Options.CenteredOption;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Utility.Sound;

import java.awt.*;

/**
 * Display a boss selection sub-menu
 * @see #THE_PYROMANCER_OPTION
 */
public class BossFightSubMenu extends SubMenu{

    /**
     * Starts the fight against {@link BossThePyromancer The Pyromancer}
     * @see CenteredOption
     */
    public static final CenteredOption THE_PYROMANCER_OPTION = new CenteredOption("The Pyromancer", 1);

    /**
     * Starts the fight against {@link BossTheShrinker The Shrinker}
     * @see CenteredOption
     */
    public static final CenteredOption THE_SHRINKER_OPTION = new CenteredOption("The Shrinker", 2);

    public static final CenteredOption THE_DISORIENTATOR_OPTION = new CenteredOption("The Disorientator", 3);

    public BossFightSubMenu(Menu menu, GameLogic gl) {
        super(3, 130, menu, gl);
    }

    @Override
    public void performOption() {
        if(selectedOption == THE_PYROMANCER_OPTION.ID){
            gl.setGameState(new BossFights(BossEnum.THE_PYROMANCER, gl));
            gl.setGameState(new Cutscene(CutsceneEnum.ThePyromancer_Cutscene,
                    (GameMode_super) gl.getGameState()));
        }

        else if(selectedOption == THE_SHRINKER_OPTION.ID){
            gl.setGameState(new BossFights(BossEnum.THE_SHRINKER, gl));
            gl.setGameState(new Cutscene(CutsceneEnum.TheShrinker_Cutscene,
                    (GameMode_super) gl.getGameState()));
        }

        else if(selectedOption == THE_DISORIENTATOR_OPTION.ID){
            gl.setGameState(new BossFights(BossEnum.THE_DISORIENTATOR, gl));
            gl.setGameState(new Cutscene(CutsceneEnum.TheDisorientator_Cutscene,
                    (GameMode_super) gl.getGameState()));
        }

        selectedOption = 1;

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void drawOptions(Graphics2D g2d, int y) {
        int y_offset = 50;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 25);
        g2d.setFont(font);

        y = THE_PYROMANCER_OPTION.draw(y, y_offset, THE_PYROMANCER_OPTION.isSelected(selectedOption), g2d);

        y = THE_SHRINKER_OPTION.draw(y, y_offset, THE_SHRINKER_OPTION.isSelected(selectedOption), g2d);

        y = THE_DISORIENTATOR_OPTION.draw(y, y_offset, THE_DISORIENTATOR_OPTION.isSelected(selectedOption), g2d);
    }
}
