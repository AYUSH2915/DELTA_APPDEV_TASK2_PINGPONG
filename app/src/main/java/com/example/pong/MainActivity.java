package com.example.pong;
import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity{
    Canvas canvas;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvas=new Canvas(this);
        setContentView(canvas);
    }
}
