package com.example.tetrisgame;

import android.content.SharedPreferences;

import java.util.Comparator;

public class SaveScore {
    private String keyname;
    private int score;
    public SaveScore(String key, int score){
        this.keyname = key;
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public String getKeyname(){
        return keyname;
    }
}
class ScoreComparator implements Comparator<SaveScore> {
    @Override
    public int compare(SaveScore s1, SaveScore s2){
        if (s1.getScore() < s2.getScore()){
            return 1;
        }
        if (s2.getScore() < s1.getScore()){
            return -1;
        }
        return 0;
    }
}
