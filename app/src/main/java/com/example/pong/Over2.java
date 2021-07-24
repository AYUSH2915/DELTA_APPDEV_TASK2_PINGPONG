package com.example.pong;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class Over2 extends AppCompatActivity {
    TextView textview4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over2);
        textview4=findViewById(R.id.textView7);
        int r=getIntent().getIntExtra("point",0);
        textview4.setText("FINAL SCORE: "+r);

    }
}
