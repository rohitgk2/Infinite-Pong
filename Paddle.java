package com.example.rohit.infinitepong;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Paddle {
    Bitmap img;
    private int x;
    private int y;
    private int width;

    Paddle(Bitmap img)
    {
        this.img = img;
        x= GameFunction.WIDTH/2 - width/2;
        y = GameFunction.HEIGHT - 23;
        width= 70;
    }

    public void update(int xCoord)
    {
        if(xCoord < GameFunction.WIDTH/2) {
            x= x - 14;
        }
        else
            x= x + 14;

        if (x< 0)
        {
            x= 0;
        }
        if (x + width > GameFunction.WIDTH)
            x= GameFunction.WIDTH - width;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth(){
        return width;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(img, x, y, null);
    }
}
