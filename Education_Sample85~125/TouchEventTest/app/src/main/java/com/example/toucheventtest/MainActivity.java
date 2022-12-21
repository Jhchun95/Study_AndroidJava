package com.example.toucheventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//  스크린의 좌표를 얻어오는 메소드 실수 형태로 좌표를 얻어오기 때문에 실수형으로 좌표를 기억할 변수를 선언한다.
//  xpos1, ypos1 => 스크린에 손가락이 터치되기 시작하는 좌표를 기억한다.
//  xpos2, ypos2 => 스크린에 손가락이 터치된 후 손가락이 털어지는 좌표를 기억한다.
    float xpos1, ypos1, xpos2, ypos2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


// Alt + insert => Override Methods 를 클릭하여 onTouchEvent를 찾는다.
// 스크린을 손가락으로 터치하면 자동으로 실행되는 onTouchEvent() 메소드를 Override 한 후 구현한다.

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//      getAction() : 스크린에 감지된 이벤트를 얻어온다.
//      MotionEvent.ACTION_DOWN => 손가락으로 스크린을 터치했을 때 발생되는 이벤트
//      MotionEvent.ACTION_UP => 스크린에서 손가락을 떼면 발생되는 이벤트
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//          Toast.makeText(getApplicationContext(), "손가락으로 스크린을 터치함", Toast.LENGTH_LONG).show(); // 현재 의미하는 것, 텍스트
            // Log.e("스크린", "터치 시작");
//          손가락으로 터치한 스크린의 좌표를 얻어 변수에 저장한다.
            xpos1 = event.getX();   // 스크린의 x좌표를 얻어온다.
            ypos1 = event.getY();   // 스크린의 y좌표를 얻어온다.
//          Log.e("손가락으로 스크린을 터치한 좌표", xpos1 + "," + ypos1);
        } else if(event.getAction() == MotionEvent.ACTION_UP){
//          Toast.makeText(getApplicationContext(), "손가락으로 스크린에서 떨어짐", Toast.LENGTH_SHORT).show();
//           Log.e("스크린", "터치 끝");
//          손가락이 스크린에서 떨어졌을 때 좌표를 얻어 변수에 저장한다.
            xpos2 = event.getX();   // 스크린의 x좌표를 얻어온다.
            ypos2 = event.getY();   // 스크린의 y좌표를 얻어온다.
//            Log.e("손가락이 스크린에서 떨어진 좌표", xpos2 + "," + ypos2);

//          손가락이 스크린에서 떨어지는 순간 터치가 시작된 좌표와 비교해야 하므로 이곳에서 손가락이 이동한 방향을 알아내는 코딩을 해야한다.
            if(xpos1 > xpos2) {
                Log.e("손가락이 움직인 방향", "왼쪽으로 " + Math.abs(xpos1 - xpos2) + "만큼 움직임"); // Math 수학 메소드 , abs 절댓값
            } else if(xpos1 < xpos2) {
                Log.e("손가락이 움직인 방향", "오른쪽으로 " + Math.abs(xpos1 - xpos2) + "만큼 움직임"); // Math 수학 메소드 , abs 절댓값
            }
            if(ypos1 > ypos2) {
                Log.e("손가락이 움직인 방향", "위쪽으로 " + Math.abs(ypos1 - ypos2) + "만큼 움직임"); // Math 수학 메소드 , abs 절댓값
            } else if(ypos1 < ypos2) {
                Log.e("손가락이 움직인 방향", "아래쪽으로 " + Math.abs(ypos1 - ypos2) + "만큼 움직임"); // Math 수학 메소드 , abs 절댓값
            }
        }
        return super.onTouchEvent(event);
    }
}
