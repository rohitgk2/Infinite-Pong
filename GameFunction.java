package com.example.rohit.infinitepong;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class GameFunction extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 354;
    public static final int HEIGHT = 700;
    private MainThread thread;
    private int x;
    private int y;
    private Background bg;
    private Ball ball;
    private Paddle paddle;


    public GameFunction(Context context) {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.court));
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.ball));
        paddle = new Paddle(BitmapFactory.decodeResource(getResources(), R.drawable.paddle));
        //start the game loop

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) (WIDTH / (getWidth() * 1.f) * (int) event.getX());
        y = (int) (HEIGHT / (getHeight() * 1.f) * (int) event.getY());
        return true;
    }

    public void update() {

            if (ball.getGameState()) {
                bg.update();
                ball.update(paddle.getX(), paddle.getY(), paddle.getWidth());
                paddle.update(x);
            }

    }

    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            ball.draw(canvas);
            paddle.draw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("Score: " + (ball.getScore()), WIDTH/2- 50, HEIGHT/2, paint);

            canvas.restoreToCount(savedState);

        }
    }


        }


