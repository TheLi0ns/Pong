package com.TheLi0ns.Utility;

public class Vector2D {
    int x;
    int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Directions getXDirection(){
        return x > 0 ? Directions.RIGHT : Directions.LEFT;
    }

    public Directions getYDirection(){
        return y > 0 ? Directions.DOWN : Directions.UP;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increment(Vector2D vector2D){
        this.x += getXDirection() == Directions.RIGHT ? vector2D.getX() : vector2D.getX() * -1;
        this.y += getYDirection() == Directions.DOWN ? vector2D.getY() : vector2D.getY() * -1;
    }

    public void add(Vector2D vector2D){
        this.x += vector2D.getX();
        this.y += vector2D.getY();
    }

    public void flipHorizontally(){
        this.x *= -1;
    }

    public void flipVertically(){
        this.y *= -1;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void normalize(){
        double magnitude = magnitude();
        this.x /= magnitude;
        this.y /= magnitude;
    }
}
