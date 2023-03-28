package com.TheLi0ns.GameObject.Bosses;

public enum BossEnum {
    /**
     * A boss who use fireshot
     * @see BossThePyromancer
     */
    THE_PYROMANCER("The Pyromancer"),

    /**
     * A boss who shrinks the ball and the enemy
     * @see BossTheShrinker
     */
    THE_SHRINKER("The Shrinker");

    public final String name;

    BossEnum(String name) {
        this.name = name;
    }
}
