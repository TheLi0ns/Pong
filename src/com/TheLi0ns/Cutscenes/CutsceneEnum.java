package com.TheLi0ns.Cutscenes;

public enum CutsceneEnum {
    TheShrinker_SecondPhase(150),
    GameOver(180),
    YOU_WIN(135);

    public final int duration;

    CutsceneEnum(int duration) {
        this.duration = duration;
    }
}
