package com.TheLi0ns.MenusHandling.SubMenus;

import com.TheLi0ns.Cutscenes.CutsceneEnum;
import com.TheLi0ns.Cutscenes.CutsceneHandler;
import com.TheLi0ns.GameFrame.MyFrame;
import com.TheLi0ns.GameObject.Bosses.BossEnum;
import com.TheLi0ns.GameObject.Bosses.BossThePyromancer;
import com.TheLi0ns.GameObject.Bosses.BossTheShrinker;
import com.TheLi0ns.Logic.GameLogic;
import com.TheLi0ns.Logic.MiniGames.BossFights;
import com.TheLi0ns.MenusHandling.Options.CenteredOption;
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

    public BossFightSubMenu() {
        super(3, 120);
    }

    @Override
    public void back() {
        MyFrame.gameLogic.setGameState(GameLogic.GameStates.MINI_GAMES_MENU);
    }

    @Override
    public void clickOption() {
        if(selectedOption == THE_PYROMANCER_OPTION.ID){
            MyFrame.gameLogic.miniGame = new BossFights(BossEnum.THE_PYROMANCER);
            CutsceneHandler.playCutscene(CutsceneEnum.ThePyromancer_Cutscene);
        }

        else if(selectedOption == THE_SHRINKER_OPTION.ID){
            MyFrame.gameLogic.miniGame = new BossFights(BossEnum.THE_SHRINKER);
            CutsceneHandler.playCutscene(CutsceneEnum.TheShrinker_Cutscene);
        }

        else if(selectedOption == THE_DISORIENTATOR_OPTION.ID){
            MyFrame.gameLogic.miniGame = new BossFights(BossEnum.THE_DISORIENTATOR);
            CutsceneHandler.playCutscene(CutsceneEnum.TheDisorientator_Cutscene);
        }

        selectedOption = 1;

        Sound.play(Sound.OPTION_CLICK);
    }

    @Override
    public void draw(Graphics2D g2d) {

        int y = 460;
        int y_offset = 50;

        g2d.setColor(Color.GRAY);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 25);
        g2d.setFont(font);

        y = THE_PYROMANCER_OPTION.draw(y, y_offset, THE_PYROMANCER_OPTION.isSelected(selectedOption), g2d);

        y = THE_SHRINKER_OPTION.draw(y, y_offset, THE_SHRINKER_OPTION.isSelected(selectedOption), g2d);

        y = THE_DISORIENTATOR_OPTION.draw(y, y_offset, THE_DISORIENTATOR_OPTION.isSelected(selectedOption), g2d);
    }
}
