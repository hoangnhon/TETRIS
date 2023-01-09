package com.example.tetrisgame;

import android.graphics.Color;

public class Blocks{
    private final int[][][][] list = {
/*I*/       {   {   {1},
                    {1},
                    {1},
                    {1}         },
                {   {1,1,1,1}   }   },  /*I*/
/*L*/       {   {   {1, 0},
                    {1, 0},
                    {1, 1}      },
                {   {1, 1, 1},
                    {1, 0, 0}   },
                {   {1, 1},
                    {0, 1},
                    {0, 1}      },
                {   {0, 0, 1},
                    {1, 1, 1}   }   } , /*L*/
/*J*/       {   {   {0, 1},
                    {0, 1},
                    {1, 1}      },
                {   {1, 0, 0},
                    {1, 1, 1}   },
                {   {1, 1},
                    {1, 0},
                    {1, 0}      },
                {   {1, 1, 1},
                    {0, 0, 1}   }   },  /*J*/
/*T*/       {   {   {1, 0},
                    {1, 1},
                    {1, 0}      },
                {   {1, 1, 1},
                    {0, 1, 0}   },
                {   {0, 1},
                    {1, 1},
                    {0, 1}      },
                {   {0, 1, 0},
                    {1, 1, 1}   }   },  /*T*/
/*S*/       {   {   {0, 1, 1},
                    {1, 1, 0}   },
                {   {1, 0},
                    {1, 1},
                    {0, 1}      }   },  /*S*/
/*Z*/       {   {   {1, 1, 0},
                    {0, 1, 1}   },
                {   {0, 1},
                    {1, 1},
                    {1, 0}      }   },  /*Z*/
/*o*/       {   {   {1, 1},
                    {1, 1}      }   },  /*o*/
    };
    int[][][] currentBlock;
    int index;
    int[][] getblock(){
        currentBlock = list[(int)(Math.random()*list.length)];
        index = (int)(Math.random()* currentBlock.length);
        return currentBlock[index];
    }
    int[][] turnedBlock(){
        index = index== currentBlock.length-1? 0: index+1;
        return currentBlock[index];
    }
}
class color{
    final int[] colorList = {   Color.rgb(255,255,0),   //Yellow
                                Color.rgb(255,20,147),   //deeppink
                                Color.rgb(0,255,255),   //aqua
                                Color.rgb(0,255,0),     //lime
                                Color.rgb(255,140,0),   //darkorange
                                Color.rgb(30,144,255),  //dodgerblue
                                Color.rgb(153,255,255),
                                Color.rgb(255,255,204),
                                Color.rgb(255,105,180), //hotpink
                                Color.rgb(143,188,143)  //darkseagreen
    };

    int getColor(){
        return colorList[(int)(Math.random()*10)];
    }
}
class Score{
    int score ;
    void setScore(){

    }
}