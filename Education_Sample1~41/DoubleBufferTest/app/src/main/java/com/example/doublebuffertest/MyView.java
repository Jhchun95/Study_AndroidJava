package com.example.doublebuffertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

//  화면에 보여줄 사용자 View를 만들기 위해 View 클래스를 상속받았고 움직임을 구현하기 위해 Runnable 인터페이스를 구현받아 만든다.
//  DoubleBuffer란 스크린에 그래픽을 표시할 때 스크린의 내용을 지운 다음 그래픽을 만들어 넣게 되면 화면이 깜박가리는 현상이 생기는
//  것을 방지하기 위해 그래픽을 표시할 때 표시할 그래픽을 메모리에서 미리 만들어 두었다가 화면을 지우지 않고 그 위에 넣어주는 방법이다.
class MyView extends View implements Runnable {

    Context context;
    Bitmap bitmap;                  // 더블 버퍼링에 사용할 메모리 버퍼
    Paint paint;                    // 펜
    Canvas canvas;                  // 그래픽이 표시될 객체, 종이
    float xpos = 300, ypos = 300;   // 그래픽이 표시될 좌표
    int width, height;              // 스크린 크기
    int xsw = 1, ysw = 1;           // 반전 변수
    Handler handler;                // UI를 변경하는 스레드 실행에 사용할 핸들러

    public MyView(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        handler = new Handler();
    }

    @Override
    public void run() {
        while (true) {
            xpos += xsw;
            ypos += ysw;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    drawing();
                }
            });
            if(xpos <= 70 || xpos >= width - 80) {
                xsw *= -1;
            }
            if(ypos <= 70 || ypos >= height - 80) {
                ysw *= -1;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //  DoubleBuffer 기법은 View 클래스 객체가 생성될 때 자동으로 실행되는 onSizeChanged()(onDraw() 메소드 보다 먼저 실행된다.) 메소드를
//  Override 해서 그래픽이 표시될 View의 크기를 알아내고 View의 크기 만큼 Canvas를 만든 후 이미지를 넣어주는 메소드
//  onDraw() 메소드로 그래픽을 표시하기 전에 onDraw() 메소드가 표시할 그래픽을 메모리에 미리 그려두는 메소드
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//      Toast.makeText(context, "onSizeChanged() 메소드 실행", Toast.LENGTH_SHORT).show();
//      스크린의 크기를 얻어낸다
//      Log.e("스크린 크기", w + " * " + h);
        width = w;
        height = h;
//      더블 버퍼링 기능을 실행하기 위해 메모리에 그래픽을 표시할 메모리 버퍼를 스크린 크기와 같게 생성한다.
//      createBitmap(스크린의 폭, 스크린의 높이, 색상 표현 방법)
//      색상 표현 방법
//      Bitmap.Config.ARGB_8888 => 알파(투명도) 값이 있고 표현 색상이 풍부하다.
//      Bitmap.Config.ARGB_4444 => 알파(투명도) 값이 있고 표현 색상이 가장적다.
//      Bitmap.Config.RGB_565 => 알파(투명도) 값이 없고 표현 색상이 ARGB_8888 보다 줄어든다.
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//      그래픽을 구현할 캔버스 객체를 만든다.
        canvas = new Canvas();
//      캔버스에 그래픽을 작성할 메모리를 넣어준다.
        canvas.setBitmap(bitmap);
//      스레드를 실행한다.
//      Runnable 인터페이스를 구현받아 작성한 스레드는 반드시 Thread 클래스의 생성자로 넘겨서 실행해야 한다.
        new Thread(this).start();
//      메모리 버퍼에 그래픽을 작성하는 메소드를 실행한다.
        drawing();
    }

    //  메모리 버퍼에 그래픽을 작성하는 메소드로 onSizeChanged() 메소드에서 처음에 실행되야 하고 그 다음 부터는 run() 메소드에서 계속
//  실행되야 하기 때문에 같은 코드가 2군데 입력되어있던 부분을 메소드로 도출했다.
    private void drawing() {
//      더블 버퍼링은 기존의 이미지를 제거하지 않기 때문에 화면에 이전 이미지의 잔상이 남는다. => 캔버스 배경을 흰색으로 다시 설정한다.
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        canvas.drawCircle(xpos, ypos, 80, paint);
        invalidate();
    }

    //  그래픽을 스크린에 표시하는 메소드
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//      Toast.makeText(context, "onDraw() 메소드 실행", Toast.LENGTH_SHORT).show();
//      onSizeChanged() 메소드로 메모리에 미리 작성해둔 그래픽을 스크린 왼쪽 상단(0, 0) 부터 스크린 전체에 표시한다.
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}

