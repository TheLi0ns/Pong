package com.TheLi0ns.Powers.DefensivePowers;

import com.TheLi0ns.GameObject.Player;

import java.awt.*;

/**
 * Super-class of all the defensive powers
 * a defensive power can be activated
 * after 3 suffered goals
 * @see DefensivePowersEnum
 */
public abstract class DefensivePowers_super {

    Player player;
    private int batteryCharge = 0;

    /**
     * @param player the player who has this power
     */
    DefensivePowers_super(Player player){
        this.player = player;
    }

    /**
     * The method to define what the power do
     */
    protected abstract void performAction();

    public final void activate(){
        if(isCharged()){
            batteryCharge = 0;
            performAction();
        }
    }

    private boolean isCharged(){
        return batteryCharge == 3;
    }

    public final void charge(){
        if(!isCharged()) batteryCharge++;
    }

    public final int getBatteryCharge(){
        return batteryCharge;
    }

    public final void drawBattery(Graphics2D g2d){
        if(player.getPos() == Player.Positions.UP) {
            g2d.setPaint(Color.gray);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(50, 820, 70, 125);
            g2d.drawLine(50, 860, 120, 860);
            g2d.drawLine(50, 900, 120, 900);

            g2d.setPaint(Color.CYAN);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
            g2d.drawString(getName(), 50, 810);

            g2d.setPaint(Color.GREEN);
            if (batteryCharge > 0) {
                g2d.fillRect(55, 905, 60, 35);
            }
            if (batteryCharge > 1) {
                g2d.fillRect(55, 865, 60, 30);
            }
            if (batteryCharge > 2) {
                g2d.fillRect(55, 825, 60, 30);
                g2d.setPaint(Color.CYAN);
                g2d.drawRect(45, 815, 80, 135);
            }
        }else{
            g2d.setPaint(Color.gray);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(880, 55, 70, 125);
            g2d.drawLine(880, 95, 950, 95);
            g2d.drawLine(885, 135, 950, 135);

            g2d.setPaint(Color.CYAN);
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
            g2d.drawString(getName(), 880, 200);

            g2d.setPaint(Color.GREEN);
            if(batteryCharge > 0){
                g2d.fillRect(885, 60, 60, 30);
            }
            if(batteryCharge > 1){
                g2d.fillRect(885, 100, 60, 30);
            }
            if(batteryCharge > 2){
                g2d.fillRect(885, 140, 60,35);
                g2d.setPaint(Color.CYAN);
                g2d.drawRect(875, 50, 80, 135);
            }
        }
    }

    public abstract String getName();
}
