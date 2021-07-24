package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity4 extends Activity {
    Canvas3 canvas3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvas3=new Canvas3(this);
        setContentView(canvas3);

    }
}