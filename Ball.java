package com.example.rohit.infinitepong;
import android.graphics.Bitmap;
import android.graphics.Canvas;


import java.util.Random;

public class Ball {
    Bitmap image;
    int x;
    int y;
    int ballWidth;
    int ballSpeedY;
    int ballSpeedX;
    public int score;
    private boolean gameState;

    Ball(Bitmap image) {

    this.image= image;
        Random rand = new Random();
        int n = rand.nextInt(500);
        int m= rand.nextInt(500);
        x = n;
        y = m;
        ballWidth = 19;
        ballSpeedY= -8;
        ballSpeedX= -8;
        gameState = true;
        int score = 0;
    }




    public void update(int xCoord, int yCoord, int paddleWidth) {

        
            x = x + ballSpeedX;
            y = y + ballSpeedY;

            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            if (x + ballWidth > GameFunction.WIDTH) {
                x = GameFunction.WIDTH - ballWidth;
            }
            if (y + ballWidth > GameFunction.HEIGHT) {
                y = GameFunction.HEIGHT - ballWidth;
            }
            if (x + ballWidth > GameFunction.WIDTH) {
                x = GameFunction.WIDTH - ballWidth;
            }


            //left collision
            if (x <= 0) {
                ballSpeedX = -1 * (ballSpeedX );
            }
            //right collision
            if (x + ballWidth >= GameFunction.WIDTH) {
                ballSpeedX = -1 * (ballSpeedX );
            }
            //top collision
            if (y <= 0) {
                ballSpeedY = -1 * (ballSpeedY - 1);
                score+=3;
            }
        //fixes error where ball bounces left to right at top of screen

            if (x <=0 && y <=0 || x + ballWidth >= GameFunction.WIDTH && y <= 0) {
                ballSpeedY = -ballSpeedY;

            }
            //bottom collision
            if (y + ballWidth >= GameFunction.HEIGHT) {
                gameState = false;
            }
        // paddle collision
            if (y + ballWidth > yCoord) {
                if ((x + ballWidth + ballWidth / 2) >= xCoord && (x < xCoord + paddleWidth)) {
                    ballSpeedY = -ballSpeedY;

                }
            }
        }



    public void setState (boolean a){
        gameState = a;
    }

    public boolean getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y, null);
    }


}


