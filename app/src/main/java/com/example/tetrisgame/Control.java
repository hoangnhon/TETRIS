package com.example.tetrisgame;

import java.util.ArrayList;

public class Control {
    int[][] admin = new int[16][10];
    ArrayList<Integer> clearRow = new ArrayList<>();
    public boolean setAdmin(int[][] bl, int starty, int startx){
        for (int y=0; y<bl.length; y++){
            for (int x=0; x<bl[y].length; x++){
                if (bl[y][x] == 1){
                    admin[starty+y][startx+x] = 1;
                }
            }
        }
        return true;
//        return isClear(starty, starty+bl.length);
    }
    public boolean isClear(int from, int to){
        col : for (; from < to; from++){
            for (int index = 0; index < 10; index++){
                if (admin[from][index] !=1){
                    continue col;
                }
            }
            clearRow.add(from);
        }
        return false;
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
            if (admin[bl.length][startx+x] == 1)   return true;
        }
        return false;
    }
}
