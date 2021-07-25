package com.example.pong;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class Over2 extends AppCompatActivity {
    TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        textView4=findViewById(R.id.textView3);
        int m=getIntent().getIntExtra("point",0);
        textView4.setText("FINAL SCORE: "+m);

    }
}
