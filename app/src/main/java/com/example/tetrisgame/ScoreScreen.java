package com.example.tetrisgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.animation.AlphaAnimation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        getSupportActionBar().hide();
        //変数宣言
        int score;
        final int limit = 10;   //スコアボードのリミット
        final int[] rankImages = {  R.drawable.level1,
                                    R.drawable.level2,
                                    R.drawable.level3,
                                    R.drawable.level4,
                                    R.drawable.level5,
                                    R.drawable.level6,
                                    R.drawable.level7,
                                    R.drawable.level8,
                                    R.drawable.level9,
                                    R.drawable.level10};
        //画面上のパーツ宣言
        ConstraintLayout Screen = findViewById(R.id.Screen);
        ListView ScoreListView = findViewById(R.id.ScoreList);
        TextView PressAnyKey = findViewById(R.id.PressAnyKey);
        ImageButton ReplayBtn = findViewById(R.id.replayButton);
        ImageButton resetBtn = findViewById(R.id.resetButton);
        SharedPreferences scorePref = getPreferences( Context.MODE_PRIVATE);
        SharedPreferences.Editor scoreEdit = scorePref.edit();
        //scoreEdit.clear().apply();

        // MusicControl ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        MediaPlayer mediaPlayer = new MediaPlayer();
        SharedPreferences musicPref = getSharedPreferences("musicStatus",Context.MODE_PRIVATE);
        //音楽ファイル名, あるいはパス
        String filePath = "sergeistern.mp3";
        // assetsから mp3 ファイルを読み込み
        try (AssetFileDescriptor afdescripter = getAssets().openFd(filePath)) {
            // MediaPlayerに読み込んだ音楽ファイルを指定
            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
            // 音量調整を端末のボタンに任せる
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (musicPref.getBoolean("music",false)) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
        // MusicControl ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        //scoreを管理する配列
        ArrayList<Integer> scoreList = new ArrayList<Integer>((Collection<? extends Integer>) scorePref.getAll().values());
        System.out.println("scoreList print");
        for (int n : scoreList){
            System.out.println(n);
        }
        ArrayList<String> keynameList = new ArrayList<>((Collection<String>)scorePref.getAll().keySet());
        System.out.println("keynameList print ");
        for (String key : keynameList){
            System.out.println(key);
        }
        Collections.sort(scoreList);    //スコアを並び替える（小➝大）

        //インテント
        Intent intent = getIntent();
        if (intent.getIntExtra("Activity_check",0) == 1){   //playScreenから呼ばれた時のみ、以下の処理を行う
            score = intent.getIntExtra("score",0);      //スコアをゲット
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());      //SharedPreferencesにput専用のキー名
            System.out.println("scoreListの要素数 = "+ scoreList.size());
            if (scoreList.size() < limit){      //点数を記録した回数が10未満だったら、今回の点数を保存
                scoreList.add(score);
                scoreEdit.putInt(timestamp,score).apply();
            }else if ( score > scoreList.get(0)){     //点数記録回数が10であった時、最低点数より高い場合、保存する
                scoreList.remove(0);
                System.out.println("removed scoreListの要素数 = "+ scoreList.size());
                scoreList.add(score);
                scoreEdit.putInt(timestamp,score).apply();
            }
        }
        Collections.sort(scoreList);    //再度スコアリストを並べ替える
        Collections.reverse(scoreList);     //リストを反転する

        //リストビューに表示する要素を設定
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (int i=0; i<scoreList.size(); i++){
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),rankImages[i]);
            ListItem item = new ListItem(bmp,scoreList.get(i));
            listItems.add(item);
        }
        //出力結果をリストビューに表示
        ListAdapter rankAdapter = new ListAdapter(this,R.layout.rank_image_view,listItems);
        ScoreListView.setAdapter(rankAdapter);

        //ブリンクアニメーション
        Animation animation =  new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(1000);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        PressAnyKey.startAnimation(animation);

        //Screenをタッチしたらホムペに戻る
        Screen.setOnClickListener(view -> {
            mediaPlayer.stop();
            Intent HomeScreen = new Intent(getApplicationContext(),MainScreen.class);
            startActivity(HomeScreen);
        });
        //リプライボタンを押すとプレイする
        ReplayBtn.setOnClickListener(view -> {
            mediaPlayer.stop();
            Intent Replay = new Intent(getApplicationContext(),PlayScreen.class);
            startActivity(Replay);
        });

        resetBtn.setOnClickListener(v->{
            scoreEdit.clear().apply();
            listItems.clear();
            ScoreListView.setAdapter(rankAdapter);
        });
    }
}