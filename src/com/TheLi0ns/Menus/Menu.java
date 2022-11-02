package com.TheLi0ns.Menus;

import java.awt.*;

public abstract class Menu {

    protected int selectedOption = 1;

    private final int N_OPTIONS;

    Menu(int nOptions){
        N_OPTIONS = nOptions;
    }

    public void nextOption(){
        selectedOption++;
        if(selectedOption > N_OPTIONS) selectedOption = 1;
    }

    public void previousOption(){
        selectedOption--;
        if(selectedOption <= 0) selectedOption = N_OPTIONS;
    }

    public abstract void clickOption();
    public abstract void draw(Graphics2D g2d);
}
