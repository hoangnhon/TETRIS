package com.example.tetrisgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Control {
    int[][] admin = new int[16][10];
    int[] clearRow = null;
    public boolean setAdmin(int[][] bl, int starty, int startx){
        for (int y=0; y<bl.length; y++){
            for (int x=0; x<bl[y].length; x++){
                if (bl[y][x] == 1){
                    admin[starty+y][startx+x] = 1;
                }
            }
        }
        return isClear(starty, starty+bl.length);
    }
    public int setEndrow(){
        return 0;
    }
    public boolean isClear(int from, int to){
        col : for (; from < to; from++){
            for (int index = 0; index < 10; index++){
                if (admin[from][index] !=1){
                    continue col;
                }
            }
        }
        return false;
    }
}
