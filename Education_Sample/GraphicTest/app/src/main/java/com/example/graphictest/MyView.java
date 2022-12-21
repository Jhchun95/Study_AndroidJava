package com.example.graphictest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

// 화면에 움직임을 주려면 View 클래스를 상속받아 사용자 View를 만들어서 MainActivity의 onCreate() 메소드에서 setContentView() 메소드로 불러준다.
public class MyView extends View{

    Context context;            // 그래픽을 표시할 액티비티
    Paint paint;                // 액티비티 화면에 그래픽을 표시할 때 사용하는 객체, 펜
    float xpos, ypos;          // 그래픽 요소의 x, y 좌표
    int width, height;         // 스크린의 폭과 높이
    int xsw=2, ysw=2;           // 스크린의 벽에 그래픽이 부딪힐 때 진행 방향의 반전에 사용할 변수
//  android.os 패키지의 Handler 객체로 스레드에서 UI 변경에 사용한다.
//  오레오 부터는 스레드 실행시 Handler를 사용하지 않아도 된다. 누가 이전 버전은 스레드에서 UI를 변경시키면 애플리케이션이 다운되므로
//  Handler를 이용해서 스레드를 실행했었다.
    Handler handler;


    public MyView(Context context) {
        super(context);
        this.context=context;

//      그래픽을 표시할 때 사용할 객체를 생성하고 펜 색상과 그래픽이 최초로 표시될 x,y 좌표를 지정한다.
        paint = new Paint();
        paint.setColor(Color.RED);
        xpos = ypos = 300;

//      스크린 정보를 얻어온다.
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

//      얻어온 스크린 정보에서 필요한 정보(스크린 크기)만 얻어낸다.
        Display display = windowManager.getDefaultDisplay();
        width = display.getWidth();         // 스크린의 가로 크기를 얻어온다.
        height = display.getHeight();       // 스크린의 세로 크기를 얻어온다.
//      Toast.makeText(context,width + "*" + height, Toast.LENGTH_SHORT).show();
        Log.e("스크린 크기", width + " *" + height);

//      누가 이전 버전은 스레드르 실행하기 위해 Handler 객체를 생성한다.
        handler = new Handler();

//      스레드를 만들어 실행한다.
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    xpos += xsw;
                    ypos += ysw;

//                  누가 이전 버전은 Handler 객체의 post() 메소드를 이용해서 UI를 갱신한다.
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                          그래픽 요소가 변경되면 반드시 invalidate() 메소드로 다시 그려줘야 한다.
                            invalidate();
                        }
                    });

//                  그래픽이 벽에 충돌했나 검사해서 충돌했으면 진행 방향을 반대로 바꿔준다.
                    if(xpos <= 70 || xpos >= width - 80) {
                        xsw *= -1;
                    }
                    if(ypos <= 70 || ypos >= height - 280) {
                        ysw *= -1;
                    }

//                  스레드가 너무 빠르면 실행되면 정상적으로 동작되지 않을 수 있으므로 Thread 클래스의 sleep() 메소드로
//                  일정한 간격마다 스레드를 멈춰준다.

                    try {
//                      sleep(시간) : 인수로 지정된 시간 만큼 프로그램을 중지시킨다. => 시간은 1/1000초 단위로 지정한다.
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

//      스크린에서 손가락이 움직일 때 그래픽을 움직이게 하려면 생성자에서 View 클래스 객체에 setOnTouchListener() 메소드로
//      TouchListener를 걸어주면 된다.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xpos = event.getX();
                ypos = event.getY();
                invalidate();
//              return false를 true로 수정한다.
                return true;
            }
        });

    }


//  스크린에 그래픽을 표시하려면 onDraw() 메ㅗ드를 Override해서 구현한다.
//  onDraw() 메소드는 그래픽이 그려질 때 자동으로 호출된다.

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
//      drawCircle(원 중심의 x좌표, 원 중심의 y좌표, 반지름, Paint 객체) : 원을 그린다.
        canvas.drawCircle(xpos, ypos, 80, paint);
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
//      drawText(문자열, 문자열이 시작되는 x좌표, 문자열이 시작되는 y좌표, Paint 객체) : 문자열을 그린다.
        canvas.drawText(width + " * " + height, 100, 100, paint);
    }
}
