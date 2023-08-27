package com.TheLi0ns.Cutscenes;

public enum CutsceneEnum {
    ThePyromancer_Cutscene(680),
    TheShrinker_Cutscene(680),
    TheShrinker_SecondPhase(150),
    TheDisorientator_Cutscene(680),
    TheDisorientator_SkillActivation(128),
    TheDisorientator_SkillDeactivation(128),
    GameOver(180),
    YOU_WIN(135);

    public final int duration;

    CutsceneEnum(int duration) {
        this.duration = duration;
    }
}
