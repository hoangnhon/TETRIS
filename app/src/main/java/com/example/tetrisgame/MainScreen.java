package com.example.tetrisgame;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.io.IOException;

public class MainScreen extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    SharedPreferences musicPref;
    SharedPreferences.Editor musicsetting;
    int pauseIcon = android.R.drawable.ic_media_play;
    int playIcon = android.R.drawable.ic_media_pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //画面上のパーツ宣言
        Button playBtn = findViewById(R.id.PlayBtn);
        Button ScoreBtn = findViewById(R.id.ScoreBtn);

        //インテントの宣言
        Intent PlayIntent = new Intent(getApplicationContext(), PlayScreen.class);
        Intent ScoreIntent = new Intent(getApplicationContext(), ScoreScreen.class);

        // インタンスを生成
        mediaPlayer = new MediaPlayer();
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
        musicPref = getSharedPreferences("musicStatus",Context.MODE_PRIVATE);
        musicsetting = musicPref.edit();
        if (musicPref.getBoolean("music",false)) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
        playBtn.setOnClickListener(v->{
            mediaPlayer.stop();
            startActivity(PlayIntent);
        });
        ScoreBtn.setOnClickListener(v->{
            mediaPlayer.stop();
            startActivity(ScoreIntent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu){
        getMenuInflater().inflate(R.menu.music_setting,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.music){
            System.out.println("music ボタンを押しました！");
            if (musicPref.getBoolean("music",false)){
                musicsetting.putBoolean("music",false).apply();
                mediaPlayer.pause();
                System.out.println("            PAUSE            ");
            }else{
                musicsetting.putBoolean("music",true).apply();
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                System.out.println("            PLAY             ");
            }
        }
        return true;
    }
}