package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity3 extends Activity {
    Canvas2 canvas2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvas2=new Canvas2(this);
        setContentView(canvas2);

    }
}