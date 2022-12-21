
package com.example.pageslidingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Animation left, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
        right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);

//      애니메이션이 실행되기 전과 후에 필요한 기능을 실행하기 위해 AnimationListener를 추가한다.
        left.setAnimationListener(new Animation.AnimationListener() {
            @Override
//          애니메이션이 실행되기 전에 자동으로 실행되는 메소드
            public void onAnimationStart(Animation animation) {
                tv.setVisibility(View.VISIBLE);
            }

            @Override
//          애니메이션이 종료된 후에 자동으로 실행되는 메소드
            public void onAnimationEnd(Animation animation) {
                btn.setText("Close");
            }

            @Override
//          애니메이션이 반복될 때 자동으로 실행되는 메소드
            public void onAnimationRepeat(Animation animation) { }
        });

        right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn.setText("Open");
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }

    public void pageSliding(View view) {
//      버튼 위의 문자열에 따라서 애니메이션을 실행한다.
        String str = String.valueOf(btn.getText());
        if(str.equals("Open")) {
//          tv.setVisibility(View.VISIBLE);
            tv.startAnimation(left);
//          btn.setText("Close");
        } else {
            tv.startAnimation(right);
//          btn.setText("Open");
//          tv.setVisibility(View.INVISIBLE);
        }
    }

}
