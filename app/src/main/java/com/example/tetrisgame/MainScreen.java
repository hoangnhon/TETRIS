package com.example.tetrisgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button playBtn = findViewById(R.id.playBtn);
        Intent intent = new Intent(getApplicationContext(),PlayActivity.class);

        playBtn.setOnClickListener(v -> startActivity(intent));
    }
}