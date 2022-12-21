package com.example.fippertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[4]; // ImageView iv1 ~ iv4의 id를 저장할 배열
    int index = 0;                              // imageView 배열의 첨자로 사용할 변수
    float downX, upX;                          // 스크린에 손가락이 터치되는 좌표를 기억하는 변수
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      imageViews 배열에 iv1 ~ iv4의 id를 저장한다.
        for(int i=0; i<imageViews.length; i++) {
            imageViews[i] = findViewById(R.id.iv1 + i);
        }
        viewFlipper = findViewById(R.id.flipper);
    }

//  스크린에 손가락이 터치되면 자동으로 실행되는 onTouchEvent() 메소드를 Override 해서 구현한다.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//      Toast.makeText(getApplicationContext(), "터치됨", Toast.LENGTH_SHORT).show();
//      스크린에 손가락이 터치되기 시작하는 좌표와 손가락이 떨어진 좌표를 저장한다.
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//          손가락이 스크린에 터치될 때 X 좌표를 기억한다.
            downX = event.getX();
         } else if(event.getAction() == MotionEvent.ACTION_UP) {
//          손가락이 스크린에 터치될 때 X 좌표를 기억한다.
            upX = event.getX();

//          스크린에서 손가락이 떨어지는 순간 어느 방향으로 손가락이 이동되었나 판단한 후 처리한다.
            if(downX < upX) {
//              스크린에 표시되는 현재 이미지의 위치를 나타내는 녹색 이미지를 흰색 이미지로 변경한다.
//              setImageResource() : ImageView에 표시되는 이미지를 변경한다.
                imageViews[index].setImageResource(R.drawable.white);

//              첫 번째 이미지의 이전 이미지의 위치는 마지막 이미지이므로 배열의 첨자를 조정한다.
                index = --index < 0 ? imageViews.length - 1 : index;

//              스크린에 표시되는 이전 이미지를 나타내던 흰색 이미지를 녹색 이미지로 변경한다.
                imageViews[index].setImageResource(R.drawable.green);

//              setInAnimation() : ViewFlipper로 들어올 때 실행되는 애니메이션을 지정한다.
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out));

//              showPrevious() : ViewFlipper에 표시되는 이미지를 이전 이미지로 변경한다.
                viewFlipper.showPrevious();

            } else if(downX > upX) {
//              스크린에 표시되는 현재 이미지의 위치를 나타내는 녹색 이미지를 흰색 이미지로 변경한다.
                imageViews[index].setImageResource(R.drawable.white);

//              마지막 이미지의 다음 이미지의 위치는 첫 번째 이미지이므로 배열의 첨자를 조정한다.
                index = ++index == imageViews.length ? 0 : index;

//              스크린에 표시되는 다음 이미지를 나타내던 흰색 이미지를 녹색 이미지로 변경한다.
                imageViews[index].setImageResource(R.drawable.green);

//              setOutAnimation() : ViewFlipper로 나갈 때 실행되는 애니메이션을 지정한다.
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in));
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out));
                viewFlipper.showNext();

            }

        }

        return super.onTouchEvent(event);
    }
}
