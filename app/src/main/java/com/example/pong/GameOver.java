package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        textView4=findViewById(R.id.textView7);
        int r=getIntent().getIntExtra("points",0);
        textView4.setText("FINAL SCORE: "+r);
    }
}