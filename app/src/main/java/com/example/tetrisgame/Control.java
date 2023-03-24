package com.example.tetrisgame;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Control extends Thread{
    boolean[] status = new boolean[16];
    int[][] admin = new int[16][10];
    int[][] color = new int[16][10];
    final int dflClr = Color.rgb(64,64,64);  //grey105
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
        setScore();
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
        score += 100*clearRow.size();
        return clearRow.size();
    }
    void clear(){
        Collections.reverse(clearRow);
        int[] empty = {0,0,0,0,0,0,0,0,0,0};
        int[] resetcolor = {dflClr, dflClr, dflClr, dflClr, dflClr, dflClr, dflClr, dflClr, dflClr, dflClr};
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
        status = new boolean[16];
        clearRow.clear();
    }
    public boolean hasNext(int[][] bl, int starty, int startx){
        if (starty+bl.length ==16){
            return false;
        }
        for (int x=0; x<bl[0].length; x++){
            for (int y=bl.length-1; y>=0; y--) {
                if (admin[starty+y+1][startx+x] ==1 && bl[y][x] ==1){
                    return false;
                }
                if (admin[starty+y+1][startx+x] ==1 && bl[y][x] ==0){
                    continue;
                }else break;
            }
        }
        return true;
    }
    public boolean hasLeft(int[][] bl, int starty, int startx){
        if (startx == 0){
            return false;
        }
        for (int y =0; y< bl.length; y++){
            for (int x=0; x < bl[0].length; x++){
                if (admin[starty+y][startx+x-1] ==1  && bl[y][x] ==1){
                    return false;
                }
                if (admin[starty+y][startx+x-1] ==1 && bl[y][x] ==0){
                    continue;
                }else break;
            }
        }
        return true;
    }
    public boolean hasRight(int[][] bl, int starty, int startx){
        if (startx + bl[0].length == 10){
            return false;
        }
        for (int y=0; y< bl.length; y++){
            for (int x=bl[0].length-1 ; x>=0; x--){
                if (admin[starty+y][startx+x+1] ==1 && bl[y][x] ==1){
                    return false;
                }
                if (admin[starty+y][startx+x+1] ==1 && bl[y][x] ==0){
                    continue;
                }else break;
            }
        }
        return true;
    }
//    public boolean isFull(int[][] bl, int startx){
//        for (int x=0; x<bl[0].length; x++ ){
//            if (admin[bl.length][startx+x] == 1) {
//                System.out.println("is full");
//                return true;
//            }
//        }
//        System.out.println("not full");
//        return false;
//    }
    void setScore(){
        score +=5;
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
