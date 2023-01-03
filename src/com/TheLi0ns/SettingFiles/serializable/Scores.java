package com.TheLi0ns.SettingFiles.serializable;

import com.TheLi0ns.Logic.MiniGames.Dribble;
import com.TheLi0ns.SettingFiles.SettingFilesHandler;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Scores {
    int dribbleHighestScore;

    public Scores(){
        this.dribbleHighestScore = Dribble.getHighestScore();
    }

    public void save(){
        if(get().dribbleHighestScore > dribbleHighestScore) dribbleHighestScore = get().dribbleHighestScore;

        Gson gson = new Gson();
        String json = gson.toJson(this);
        try {
            File f = new File(SettingFilesHandler.dir + "/scores.json");
            if(!f.exists()) f.createNewFile();
            Formatter formatter = new Formatter(f);
            formatter.format("%s", json);
            formatter.flush();
            formatter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Scores get(){
        Scores scores;

        Gson gson = new Gson();

        try {
            FileInputStream fileIn = new FileInputStream(SettingFilesHandler.dir + "/scores.json");
            Scanner sc = new Scanner(fileIn);
            scores = gson.fromJson(sc.nextLine(), Scores.class);
            fileIn.close();
        } catch (JsonSyntaxException | IOException exception){
            scores = new Scores();
        }

        return scores;
    }

    public static void load(){
        Scores scores = get();

        checkDataIntegrity(scores);
        setScores(scores);
    }

    private static void checkDataIntegrity(Scores scores){
        if(scores.dribbleHighestScore < 0) scores.dribbleHighestScore = 0;
    }

    private static void setScores(Scores scores){
        Dribble.setHighestScore(scores.dribbleHighestScore);
    }
}
