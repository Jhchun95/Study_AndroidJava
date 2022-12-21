package com.example.gesturetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
//  onTouchEvent() 메소드가 실행할 기능을 위임받을 GestureDetector 클래스 객체를 선언한다.
    GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

//      onTouchEvent() 메소드가 실행할 기능을 위임받을 GestureDetector 클래스 객체를 선언한다.
//      위임받은 TouchEvent를 처리할 GestureDetector 객체를 생성하고 SimpleOnGestureListener 클래스의 메소드 중에서
//      필요한 메소드를 Override 해서 TouchEvent를 구현한다.
        gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

//      스크린에 터치가 감지되면 실행되는 메소드

            @Override
            public boolean onDown(MotionEvent e) {
                tv.append("onDown()\n");
                return super.onDown(e);
            }

//          스크린에 터치가 감지되고 스크린을 짧게 누르고 있을 때 실행되는 메소드
            @Override
            public void onShowPress(MotionEvent e) {
                super.onShowPress(e);
                tv.append("onShowPress()\n");
            }

//          스크린에 터치가 감지되고 스크린을 갈게 누르고 있을 때 실행되는 메소드
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                tv.append("onLongPress()\n");
            }

//          스크린을 짧게 터치한 상태로 일정한 속도와 방향으로 움직일 경우 실행되는 메소드
//          터치를 길게 해서 onLongPress() 메소드로 제어가 넘어가면 실행되지 않는다.
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                tv.append("onScroll()\n" + distanceX + "," + distanceY + "\n");
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

//          스크린을 짧게 터치한 상태로 가속도를 붙여 손가락을 움직였을 때 실행되는 메소드
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                tv.append("onFling()" + velocityX + "," + velocityY + "\n");
                return super.onFling(e1, e2 , velocityX, velocityY);
            }


//          스크린을 손가락으로 한번 눌렀다 뗄 경우 실행된다. => 클릭 (기억하기)
//          터치를 길게 해서 onLongPress() 메소드로 제어가 넘어가면 실행되지 않는다.
//          Down은 터치되는 순간, 싱글탭은 클릭
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                tv.append("onSingleTapUp()\n");
                return super.onSingleTapUp(e);
            }

//          스크린을 손가락으로 한 번 눌렀다 뗄 경우 실행된다.
//          onSingleTapUp() 메소드가 실행되고 난 후 실행된다.
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                tv.append("onSingleTapConfirmed\n");
                return super.onSingleTapConfirmed(e);
            }


//          스크린을 손가락으로 연속해서 두 번 눌렀다 뗄 경우 실행된다. => 더블 클릭 (기억하기)
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                tv.append("onDoubleTap\n");
                return super.onDoubleTap(e);
            }

//          스크린을 손가락으로 연속해서 두 번 눌렀다 뗄 경우 실행된다.
//          onDoubleTap() 메소드가 실행되고 난 후 실행된다.
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                tv.append("onDoubleTapEvent\n");
                return super.onDoubleTapEvent(e);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gestureDetector != null) {
//          스크린이 터치되면 자동으로 실행되는 onTouchEvent() 메소드가 실행할 기능을 GestureDetector 객체로 위임한다.
            return gestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
