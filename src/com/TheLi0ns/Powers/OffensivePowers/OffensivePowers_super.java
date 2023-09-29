package com.TheLi0ns.Powers.OffensivePowers;

import com.TheLi0ns.GameObject.Player;

import java.awt.*;

/**
 * Super-class of all the offensive powers
 * an offensive power can be activated
 * after 6 suffered goals
 * @see OffensivePowersEnum
 */
public abstract class OffensivePowers_super {

    Player player, opponent;
    int batteryCharge = 0;

    /**
     * @param player   the player who has this power
     * @param opponent the opponent who get the negative effect
     */
    OffensivePowers_super(Player player, Player opponent){
        this.player = player;
        this.opponent = opponent;
    }

    /**
     * The method that define some effect on the player
     * and choose the time
     */
    protected abstract void performAction();

    public final void activate(){
        if(isCharged()){
            batteryCharge = 0;
            performAction();
        }
    }

    /**
     * The method that define the negative effect on the opponent
     * @param on_off performAction or deactivate the effect
     */
    protected abstract void effectOnOpponent(boolean on_off);

    private boolean isCharged(){
        return batteryCharge == 6;
    }

    public final void charge(){
        if(!isCharged()) batteryCharge++;
    }

    public final int getBatteryCharge(){
        return batteryCharge;
    }
    
    public void drawBattery(Graphics2D g2d){
        if(player.getPos() == Player.Positions.UP){
            g2d.setPaint(Color.gray);
            g2d.drawRect(880, 820, 70, 125);
            g2d.drawLine(880, 860, 950, 860);
            g2d.drawLine(880, 900, 950, 900);

            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
            g2d.drawString(getName(), 880, 810);

            g2d.setPaint(Color.GREEN);
            if(batteryCharge > 0){
                g2d.fillRect(885, 905, 30, 35);
            }
            if(batteryCharge > 1){
                g2d.fillRect(915, 905, 30, 35);
            }
            if(batteryCharge > 2){
                g2d.fillRect(885, 865, 30, 30);
            }
            if(batteryCharge > 3){
                g2d.fillRect(915, 865, 30, 30);
            }
            if(batteryCharge > 4){
                g2d.fillRect(885, 825, 30,30);
            }
            if(batteryCharge > 5){
                g2d.fillRect(915, 825, 30, 30);
                g2d.setPaint(Color.RED);
                g2d.drawRect(875, 815, 80, 135);
            }
        }else{
            g2d.setPaint(Color.gray);
            g2d.drawRect(50, 55, 70, 125);
            g2d.drawLine(50, 95, 120, 95);
            g2d.drawLine(50, 135, 120, 135);

            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
            g2d.drawString(getName(), 50, 200);

            g2d.setPaint(Color.GREEN);
            if (batteryCharge > 0) {
                g2d.fillRect(55, 60, 30, 30);
            }
            if (batteryCharge > 1) {
                g2d.fillRect(85, 60, 30, 30);
            }
            if (batteryCharge > 2) {
                g2d.fillRect(55, 100, 30, 30);
            }
            if (batteryCharge > 3) {
                g2d.fillRect(85, 100, 30, 30);
            }
            if (batteryCharge > 4) {
                g2d.fillRect(55, 140, 30, 35);
            }
            if (batteryCharge > 5) {
                g2d.fillRect(85, 140, 30, 35);
                g2d.setPaint(Color.RED);
                g2d.drawRect(45, 50, 80, 135);
            }
        }
    }

    public abstract String getName();
}
