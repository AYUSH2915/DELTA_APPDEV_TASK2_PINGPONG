package com.example.pong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Canvas3 extends View {
    Context context;
    Bitmap ball, paddle,paddle2;
    float ballX,ballY,paddleX,paddleY,paddle2X,paddle2Y,dWidth,dHeight,xDir=5,yDir=5,oldX,oldPaddleX;
    int points=0;
    Random random;
    int ran,count=0;
    Paint textPaint = new Paint();
    float TEXT_SIZE = 60;
    public Canvas3(Context context) {
        super(context);
        this.context = context;

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);
        paddle2 = BitmapFactory.decodeResource(getResources(),R.drawable.paddle2);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y-120;
        ballX=dWidth/2-ball.getWidth()/2;
        ballY=dHeight/2;
        paddleY = (dHeight * 45) / 50;
        paddleX = dWidth / 2 - paddle.getWidth() /2;
        random=new Random();
        ran=random.nextInt(30);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);

        if((ballX >= dWidth - ball.getWidth()) ){
            xDir=-5;
        }
        if(ballX<=0){
            xDir=5;
        }
        if(ballY <= 150){
            count++;
            yDir=5;
        }
        if(ballY > paddleY + paddle.getHeight()){
            ballX = dWidth/2-ball.getWidth()/2;
            ballY = dHeight/2;


        }
        if(((ballX+ball.getWidth()) >= paddleX)
                && (ballX <= paddleX + paddle.getWidth())
                && (ballY + ball.getHeight() >= paddleY)
                && (ballY + ball.getHeight() <= paddleY + paddle.getHeight())){
            yDir=-5;

            points++;
        }
        if(count==ran){

            Intent intent = new Intent(context, Over2.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        ballX += xDir;
        ballY += yDir;
        paddle2X=ballX;
        paddle2Y=120;
        canvas.drawBitmap(ball, ballX, ballY, null);
        canvas.drawBitmap(paddle, paddleX, paddleY, null);
        canvas.drawText("Score: "+points+"  "+ran, 20, TEXT_SIZE, textPaint);
        canvas.drawLine(0,100,dWidth,100,textPaint);
        canvas.drawBitmap(paddle2,paddle2X,120,null);
        invalidate();
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
}
