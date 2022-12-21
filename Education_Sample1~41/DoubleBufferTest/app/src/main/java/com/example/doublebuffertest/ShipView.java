package com.example.doublebuffertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

class ShipView extends View implements Runnable {

    Bitmap bitmap;
    Bitmap bg1, bg2;                // 배경 이미지를 기억할 Bitmap 변수
    Bitmap[] ship = new Bitmap[4];  // 우주선 이미지를 기억할 Bitmap 배열
    int index = 0;                  // 배열의 첨자로 사용할 변수
    Canvas canvas;
    Paint paint;
    float xpos = 100, ypos = 200;   // 우주선 이미지가 표시될 좌표
    Handler handler;
    int width, height;

    public ShipView(Context context) {
        super(context);
        paint = new Paint();
        handler = new Handler();
//      생성자에서 사용할 이미지를 읽어 저장한다.
//      Birmap 변수에 BitmapFactory 클래스의 decodeResource() 메소드를 이용해 이미지를 읽어들인다.
        bg1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_a);
        bg2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_b);
        for(int i = 0; i < ship.length; i++) {
            ship[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship2_1 + i);
        }
    }

    @Override
    public void run() {
        while (true) {
            if(++xpos > width) {
                xpos = -122;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    drawing();
                }
            });
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        new Thread(this).start();
        drawing();
    }

    public void drawing() {
        canvas.drawColor(Color.WHITE);
//      배경 이미지를 이미지 버퍼에 넣어준다.
        canvas.drawBitmap(bg1, 0, 0, paint);
        canvas.drawBitmap(bg2, 0, 0, paint);
//      우주선 이미지를 이미지 버퍼에 넣어준다.
        canvas.drawBitmap(ship[index % ship.length], xpos, ypos, paint);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }
    }
}