package com.example.tetrisgame;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Control {
    boolean[] status = new boolean[16];
    int[][] admin = new int[16][10];
    int[][] color = new int[16][10];
    final int defaultColor = Color.rgb(0,0,0);  //black
    private int score = 0;
    ArrayList<Integer> clearRow = new ArrayList<>();
    public int setAdmin(int[][] bl, int starty, int startx, int color){
        for (int y=0; y<bl.length; y++){
            for (int x=0; x<bl[y].length; x++){
                if (bl[y][x] == 1){
                    admin[starty+y][startx+x] = 1;
                    this.color[starty+y][startx+x] = color;
                }
            }
        }
        return isClear(starty, starty+bl.length);
    }
    public int isClear(int y, int cnt){
        col : for (; y < cnt; y++){
            for (int index = 0; index < 10; index++){
                if (admin[y][index] !=1){
                    continue col;
                }
            }
            status[y] = true;
            clearRow.add(y);
        }
        score += 1000*clearRow.size();
        return clearRow.size();
    }
    void clear(){
        Collections.reverse(clearRow);
        int[] empty = {0,0,0,0,0,0,0,0,0,0};
        int[] resetcolor = {defaultColor,defaultColor,defaultColor,defaultColor,defaultColor,defaultColor,defaultColor,defaultColor,defaultColor,defaultColor};
        int line = clearRow.get(0);
        for (int y=line-1; y>=0; y--){
            if (!status[y]){
                admin[line] = admin[y];
                color[line] = color[y];
                line--;
            }
            admin[y] = empty;
            color[y] = resetcolor;
        }
        clearRow.clear();
    }
    public boolean next(int[][] bl, int starty, int startx){
        int y=bl.length-1;
        if (starty+y ==15){
            return false;
        }
        boolean cmd = true;
        for (int x=0; x<bl[0].length; x++){
            for (y=bl.length-1; y>=0; y--) {
                if (admin[starty+y+1][startx+x] ==1 && bl[y][x] ==1){
                    return false;
                }
                if (admin[starty+y][startx+x] ==1 && bl[y][x] ==0){
                    continue;
                }else break;
            }
        }
        return cmd;
    }
    public boolean isFull(int[][] bl, int startx){
        for (int x=0; x<bl[0].length; x++ ){
            if (admin[bl.length][startx+x] == 1) {
                System.out.println("is full");
                return true;
            }
        }
        System.out.println("not full");
        return false;
    }
    int getScore(){
        return score;
    }
    int[][] getAdmin(){
        return admin;
    }
    int[][] getColor(){
        return color;
    }
}
