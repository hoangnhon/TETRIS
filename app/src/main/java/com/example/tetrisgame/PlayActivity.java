package com.example.tetrisgame;

import static java.lang.Thread.sleep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayActivity extends AppCompatActivity {

    /*変数宣言 */
    final int defaultColor = Color.rgb(0,0,0);  //black
    int color;
    int score = 0;          //獲得したポイント
    int sleepTime = 1000;   //blockが落ちるスピード
    final int[] startY = {0};
    final int[] startX = {0};
    int[][] bl = null;  //現在のblock
    Blocks setblock = new Blocks();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ImageView[][] Gview={//[0-15][0-9]
                { findViewById(R.id.imageView0_0),
                    findViewById(R.id.imageView0_1),
                    findViewById(R.id.imageView0_2),
                    findViewById(R.id.imageView0_3),
                    findViewById(R.id.imageView0_4),
                    findViewById(R.id.imageView0_5),
                    findViewById(R.id.imageView0_6),
                    findViewById(R.id.imageView0_7),
                    findViewById(R.id.imageView0_8),
                    findViewById(R.id.imageView0_9)
                },
                {   findViewById(R.id.imageView1_0),
                    findViewById(R.id.imageView1_1),
                    findViewById(R.id.imageView1_2),
                    findViewById(R.id.imageView1_3),
                    findViewById(R.id.imageView1_4),
                    findViewById(R.id.imageView1_5),
                    findViewById(R.id.imageView1_6),
                    findViewById(R.id.imageView1_7),
                    findViewById(R.id.imageView1_8),
                    findViewById(R.id.imageView1_9)
                },
                {   findViewById(R.id.imageView2_0),
                    findViewById(R.id.imageView2_1),
                    findViewById(R.id.imageView2_2),
                    findViewById(R.id.imageView2_3),
                    findViewById(R.id.imageView2_4),
                    findViewById(R.id.imageView2_5),
                    findViewById(R.id.imageView2_6),
                    findViewById(R.id.imageView2_7),
                    findViewById(R.id.imageView2_8),
                    findViewById(R.id.imageView2_9)
                },
                {   findViewById(R.id.imageView3_0),
                    findViewById(R.id.imageView3_1),
                    findViewById(R.id.imageView3_2),
                    findViewById(R.id.imageView3_3),
                    findViewById(R.id.imageView3_4),
                    findViewById(R.id.imageView3_5),
                    findViewById(R.id.imageView3_6),
                    findViewById(R.id.imageView3_7),
                    findViewById(R.id.imageView3_8),
                    findViewById(R.id.imageView3_9)
                },
                {   findViewById(R.id.imageView4_0),
                    findViewById(R.id.imageView4_1),
                    findViewById(R.id.imageView4_2),
                    findViewById(R.id.imageView4_3),
                    findViewById(R.id.imageView4_4),
                    findViewById(R.id.imageView4_5),
                    findViewById(R.id.imageView4_6),
                    findViewById(R.id.imageView4_7),
                    findViewById(R.id.imageView4_8),
                    findViewById(R.id.imageView4_9)
                },
                {   findViewById(R.id.imageView5_0),
                    findViewById(R.id.imageView5_1),
                    findViewById(R.id.imageView5_2),
                    findViewById(R.id.imageView5_3),
                    findViewById(R.id.imageView5_4),
                    findViewById(R.id.imageView5_5),
                    findViewById(R.id.imageView5_6),
                    findViewById(R.id.imageView5_7),
                    findViewById(R.id.imageView5_8),
                    findViewById(R.id.imageView5_9)
                },
                {   findViewById(R.id.imageView6_0),
                    findViewById(R.id.imageView6_1),
                    findViewById(R.id.imageView6_2),
                    findViewById(R.id.imageView6_3),
                    findViewById(R.id.imageView6_4),
                    findViewById(R.id.imageView6_5),
                    findViewById(R.id.imageView6_6),
                    findViewById(R.id.imageView6_7),
                    findViewById(R.id.imageView6_8),
                    findViewById(R.id.imageView6_9)
                },
                {   findViewById(R.id.imageView7_0),
                    findViewById(R.id.imageView7_1),
                    findViewById(R.id.imageView7_2),
                    findViewById(R.id.imageView7_3),
                    findViewById(R.id.imageView7_4),
                    findViewById(R.id.imageView7_5),
                    findViewById(R.id.imageView7_6),
                    findViewById(R.id.imageView7_7),
                    findViewById(R.id.imageView7_8),
                    findViewById(R.id.imageView7_9)
                },
                {   findViewById(R.id.imageView8_0),
                    findViewById(R.id.imageView8_1),
                    findViewById(R.id.imageView8_2),
                    findViewById(R.id.imageView8_3),
                    findViewById(R.id.imageView8_4),
                    findViewById(R.id.imageView8_5),
                    findViewById(R.id.imageView8_6),
                    findViewById(R.id.imageView8_7),
                    findViewById(R.id.imageView8_8),
                    findViewById(R.id.imageView8_9)
                },
                {   findViewById(R.id.imageView9_0),
                    findViewById(R.id.imageView9_1),
                    findViewById(R.id.imageView9_2),
                    findViewById(R.id.imageView9_3),
                    findViewById(R.id.imageView9_4),
                    findViewById(R.id.imageView9_5),
                    findViewById(R.id.imageView9_6),
                    findViewById(R.id.imageView9_7),
                    findViewById(R.id.imageView9_8),
                    findViewById(R.id.imageView9_9)
                },
                {   findViewById(R.id.imageView10_0),
                    findViewById(R.id.imageView10_1),
                    findViewById(R.id.imageView10_2),
                    findViewById(R.id.imageView10_3),
                    findViewById(R.id.imageView10_4),
                    findViewById(R.id.imageView10_5),
                    findViewById(R.id.imageView10_6),
                    findViewById(R.id.imageView10_7),
                    findViewById(R.id.imageView10_8),
                    findViewById(R.id.imageView10_9)
                },
                {   findViewById(R.id.imageView11_0),
                    findViewById(R.id.imageView11_1),
                    findViewById(R.id.imageView11_2),
                    findViewById(R.id.imageView11_3),
                    findViewById(R.id.imageView11_4),
                    findViewById(R.id.imageView11_5),
                    findViewById(R.id.imageView11_6),
                    findViewById(R.id.imageView11_7),
                    findViewById(R.id.imageView11_8),
                    findViewById(R.id.imageView11_9)
                },
                {   findViewById(R.id.imageView12_0),
                    findViewById(R.id.imageView12_1),
                    findViewById(R.id.imageView12_2),
                    findViewById(R.id.imageView12_3),
                    findViewById(R.id.imageView12_4),
                    findViewById(R.id.imageView12_5),
                    findViewById(R.id.imageView12_6),
                    findViewById(R.id.imageView12_7),
                    findViewById(R.id.imageView12_8),
                    findViewById(R.id.imageView12_9)
                },
                {   findViewById(R.id.imageView13_0),
                    findViewById(R.id.imageView13_1),
                    findViewById(R.id.imageView13_2),
                    findViewById(R.id.imageView13_3),
                    findViewById(R.id.imageView13_4),
                    findViewById(R.id.imageView13_5),
                    findViewById(R.id.imageView13_6),
                    findViewById(R.id.imageView13_7),
                    findViewById(R.id.imageView13_8),
                    findViewById(R.id.imageView13_9)
                },
                {   findViewById(R.id.imageView14_0),
                    findViewById(R.id.imageView14_1),
                    findViewById(R.id.imageView14_2),
                    findViewById(R.id.imageView14_3),
                    findViewById(R.id.imageView14_4),
                    findViewById(R.id.imageView14_5),
                    findViewById(R.id.imageView14_6),
                    findViewById(R.id.imageView14_7),
                    findViewById(R.id.imageView14_8),
                    findViewById(R.id.imageView14_9)
                },
                {   findViewById(R.id.imageView15_0),
                    findViewById(R.id.imageView15_1),
                    findViewById(R.id.imageView15_2),
                    findViewById(R.id.imageView15_3),
                    findViewById(R.id.imageView15_4),
                    findViewById(R.id.imageView15_5),
                    findViewById(R.id.imageView15_6),
                    findViewById(R.id.imageView15_7),
                    findViewById(R.id.imageView15_8),
                    findViewById(R.id.imageView15_9)
                }
        };
        FloatingActionButton left = findViewById(R.id.left);
        FloatingActionButton right = findViewById(R.id.right);
        FloatingActionButton turn = findViewById(R.id.turn);
        FloatingActionButton down = findViewById(R.id.down);

        class showBlock extends Thread{
            Control ctl = new Control();
            public void run(){
                while (!ctl.isFull( bl = setblock.getblock(), startX[0] = (10 - bl[0].length) / 2)){
                    sleepTime = 1000;
//                    bl = setblock.getblock();
                    color = new color().getColor();      //Yellow
                    startY[0] = 0; //block[0][0]を配置する縦の位置
//                    final int[] startX = { (10 - bl[0].length) / 2 };
                    System.out.println("new block");
                    while (true){
                        setColor( startX[0], color);     //画面描画
                        try {   sleep(sleepTime); }              //時間を開ける
                        catch (InterruptedException e) {    e.printStackTrace();    }
                        if ( ctl.next(bl, startY[0], startX[0]) ){        //最終行の前だったら、次の行へ進む前に黒に戻す
                            setColor( startX[0], defaultColor);
                        }else {    //最終行に行った場合、admin[][]に状態を保存し、ループに抜ける
                            ctl.setAdmin(bl, startY[0], startX[0]);
                            break;
                        }
                        startY[0]++;
                        //left　Buttonが押された場合、block配置の横の位置が一個左にずらす
                        left.setOnClickListener(v -> {
                            if (startX[0]>0){
                                startX[0]--;
                                setColor( startX[0]+1, defaultColor);
                                setColor( startX[0], color);
                            }
                        });
                        //right　Buttonが押された場合、block配置の横の位置が一個右にずらす
                        right.setOnClickListener( view ->{
                            if (startX[0]+bl[0].length<10){
                                startX[0]++;
                                setColor(startX[0]-1, defaultColor);
                                setColor( startX[0], color);
                            }
                        });
                        //blockの向きを変えるボタン
                        turn.setOnClickListener( view ->{
                            setColor(startX[0], defaultColor);
                            bl = setblock.turnedBlock();
                            if (bl[0].length > bl.length && startX[0] == 10- bl.length){
                                startX[0] += bl[0].length - bl.length;
                            }
                            setColor(startX[0], color);
                        });
                        down.setOnClickListener( view ->{
                            sleepTime = sleepTime/10;
                        });
                    }//一つのブロックが最終点に到着するまでのwhile文　↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
                }
            }//the end of run method ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
            void setColor( int startX, int color){
                for (int y = 0; y < bl.length; y++){
                    for (int x = 0; x < bl[y].length; x++){
                        if (bl[y][x] == 1){
                            Gview[startY[0]+y][startX +x].setBackgroundColor(color);
                        }
                    }
                }
            }// the end of setColor method ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        }//the end of showBlock class ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        new showBlock().start();
//        Intent gotoHighScore = new Intent(getApplicationContext(), HighscoreActivity.class);
//        gotoHighScore.putExtra("score",score);
//        startActivity(gotoHighScore);
    }
}
