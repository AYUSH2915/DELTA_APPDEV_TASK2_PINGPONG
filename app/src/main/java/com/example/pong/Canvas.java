package com.example.pong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.example.pong.Velocity;

import java.util.Random;

public class Canvas extends View {
    Context context;
    float ballX, ballY=120;
    Velocity velocity = new Velocity(25, 32);
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 70;
    float paddleX, paddleY;
    float oldX, oldPaddleX;
    int points = 0;
    int life = 3;
    Bitmap ball, paddle;
    int dWidth, dHeight;
    MediaPlayer mpHit, mpOver,mpWall;
    Random random;
    SharedPreferences sharedPreferences;
    Boolean audioState;

    public Canvas(Context context) {
        super(context);
        this.context = context;
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        mpHit = MediaPlayer.create(context, R.raw.hit);
        mpOver = MediaPlayer.create(context, R.raw.over);
        mpWall = MediaPlayer.create(context,R.raw.wall);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        healthPaint.setColor(Color.GREEN);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y-120;
        random = new Random();
        ballX = random.nextInt(dWidth/2);
        paddleY = (dHeight * 45) / 50;
        paddleX = dWidth / 2 - paddle.getWidth() /2;

    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);
        ballX += velocity.getX();
        ballY += velocity.getY();
        if((ballX >= dWidth - ball.getWidth()) || ballX <= 0){

                mpWall.start();

            velocity.setX(velocity.getX() * -1);
        }
        if(ballY <= 120){

                mpWall.start();

            velocity.setY(velocity.getY() * -1);
        }
        if(ballY > paddleY + paddle.getHeight()){
            ballX = 1 + random.nextInt(dWidth - ball.getWidth() -1);
            ballY = 121;

            velocity.setX(xVelocity());
            velocity.setY(32);
            life--;
            if(life == 0){
                if(mpOver!=null){
                    mpOver.start();
                }
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("points", points);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }
        if(((ballX+ball.getWidth()) >= paddleX)
                && (ballX <= paddleX + paddle.getWidth())
                && (ballY + ball.getHeight() >= paddleY)
                && (ballY + ball.getHeight() <= paddleY + paddle.getHeight())){
            if(mpHit != null ){
                mpHit.start();
            }
            velocity.setX(velocity.getX() + 1);
            velocity.setY((velocity.getY() + 1) * -1);
            points++;
        }
        canvas.drawBitmap(ball, ballX, ballY, null);
        canvas.drawBitmap(paddle, paddleX, paddleY, null);
        canvas.drawText("Score: "+points, 20, TEXT_SIZE, textPaint);
        canvas.drawLine(0,120,dWidth,120,textPaint);
        if(life == 2){
            healthPaint.setColor(Color.YELLOW);
        }else if(life == 1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30,dWidth - 200 + 60*life, 80, healthPaint);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if(touchY >= paddleY){
            int action = event.getAction();
            if(action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldPaddleX = paddleX;
            }
            if(action == MotionEvent.ACTION_MOVE){
                float shift = oldX - touchX;
                float newPaddleX = oldPaddleX - shift;
                if(newPaddleX <= 0)
                    paddleX = 0;
                else if(newPaddleX >= dWidth - paddle.getWidth())
                    paddleX = dWidth - paddle.getWidth();
                else
                    paddleX = newPaddleX;
            }
        }
        return true;
    }

    private int xVelocity() {
        int[] values = {-35, -30, -25, 25, 30, 35};
        int index = random.nextInt(6);
        return values[index];
    }


}
