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

public class LottoView3 extends View implements Runnable {

    Context context;
    Paint paint;
    Bitmap bgBitmap;
    Canvas canvas;
    Handler handler;
    int width = 320, height = 320;  // 로또 공 1개 이미지 크기
    int screenWidth, screenHeight;

//  로또 번호 생성을 위한 변수를 선언한다.
    int[] lottoBall = new int[45];      // 로또 번호를 기억할 배열
    Bitmap[] lotto = new Bitmap[45];     // 로또 번호에 따른 공 이미지를 기억할 배열
    int index = 0;                       // 배열의 첨자
    Random random = new Random();        // 로또 번호를 섞을 때 사용할 난수(무작위 수)를 얻어오는 객체

    public LottoView3(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        handler = new Handler();

//      프로그램에서 사용할 로또 번호를 기억할 배열에 1 ~ 45의 숫자를 저장하고 로또 공 이미지를 기억할 배열에 로또 공 이미지를
//      읽어서 저장한다.
        for(int i=0; i< lotto.length; i++) {
            lottoBall[i] = i + 1;
            lotto[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball1_001 + i);
        }

//      로또 번호를 섞기 전 상태를 출력한다.
        String str = "";
        for(int i=0; i< lotto.length; i++) {
            str += String.format("%2d", lottoBall[i]);
            if((i+1) % 10 == 0) {
                Log.e("섞기전", str);
                str = "";
            }
        }
        Log.e("섞기전", str);

//      섞는다.
//      lottoBall[0]와 lottoBall[1] ~ lottoBall[44] 사이의 랜덤한 위치와 값을 교환한다.
//      lotto[0]와 lotto[1] ~ lotto[44] 사이의 랜덤한 위치와 값을 교환한다.
        for(int i=0; i< 1000000; i++) {
//          nextInt() : 0 부터 인수로 지정된 숫자 - 1 사이의 무작위 수를 발생시킨다.
            int r = random.nextInt(44) + 1;
//          로또 번호 섞기
            int temp = lottoBall[0];
            lottoBall[0] = lottoBall[r];
            lottoBall[r] = temp;
//          로또 공 섞기
            Bitmap bitmapTemp = lotto[0];
            lotto[0] = lotto[r];
            lotto[r] = bitmapTemp;

        }


//      로또 번호를 섞은 후 상태를 출력한다.
        str = "";
        for(int i=0; i< lotto.length; i++) {
            str += String.format("%2d", lottoBall[i]);
            if((i+1) % 10 == 0) {
                Log.e("섞은후", str);
                str = "";
            }
        }
        Log.e("섞기후", str);

//      로또 1등 번호
        for(int i=0; i<6; i++) {
            Log.e(i+1 + "번째 로또 번호", lottoBall[i] + "");
        }
        Log.e("보너스 번호", lottoBall[6] + "");

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
        canvas.drawColor(Color.WHITE);
        for(int i=0; i < 6; i++) {
            canvas.drawBitmap(lotto[i], 0, i * height, paint);
        }
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

    }
}
