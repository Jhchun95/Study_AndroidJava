package com.example.doublebudderlotto;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.Random;

class LottoView2 extends View implements Runnable {

    Context context;
    Paint paint;
    Bitmap bgBitmap;                // 이미지 버퍼
    Bitmap lottoBitmap;             // 로또 공 이미지를 기억하는 변수
    Canvas canvas;
    Handler handler;
    int xpos = 0, ypos = 0;         // 로또 공이 화면에 표시될 좌표
    int width = 320, height = 320;  // 로또 공 1개 이미지 크기
    int imageWidth, imageHeight;    // 로또 공 45개 이미지 크기
    int screenWidth, screenHeight;  // 스크린 크기

    public LottoView2(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        handler = new Handler();
//      프로그램에서 사용할 이미지를 읽어서 Bitmap 변수에 저장시킨다.
        lottoBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lotto_ball_horz);
//      lottoBitmap에 저장된 이미지의 크기를 얻어온다.
        imageWidth = lottoBitmap.getWidth();
        imageHeight = lottoBitmap.getHeight();
//      Log.e("로또 세로 이미지 크기", imageWidth + " * " + imageHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        bgBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bgBitmap);
        new Thread(this).start();
        drawing();
    }

    public void drawing() {
//      이미지 자르기
//      lottoBitmap에 저장된 원본 이미지를 액티비티에 공 1개의 크기로 표시하기 위해 시작 좌표(0, ypos) 부터 숫자 이미지 1개의
//      폭과 높이 만큼 잘라서 Bitmap 객체에 저장시킨다.
//      createBitmap(원본 이미지, 자르기 시작할 x좌표, 자르기 시작할 y좌표, 자를 폭, 자를 높이)
        Bitmap bitmap = Bitmap.createBitmap(lottoBitmap, xpos, 0, width, height);
//      잘라낸 이미지를 캔버스에 그려준다.
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bgBitmap != null) {
            canvas.drawBitmap(bgBitmap, 0, 0, paint);
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        int r = random.nextInt(45);
        Log.e("로또 번호", r + 1 + "");
//      while (xpos < imageHeight - width) {   // 45까지 진행된다.
        while (xpos < width * r) {             // r 까지 진행된다.
            xpos++;
            try { Thread.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    drawing();
                }
            });
        }
    }
}
