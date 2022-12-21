package com.example.swichertest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    ImageSwitcher is;
    TextSwitcher ts;
    String[] texts = {"Android", "Java", "Kotlin"};
    int[] images = {R.drawable.background, R.drawable.sample, R.drawable.sample_2};
    int index = 0;
    float downX, upX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        is = findViewById(R.id.is);
        ts = findViewById(R.id.ts);

//      프로그램에서 TestSwitcher에 문자열을 표시하는 TextView를 ViewFactory 객체로 만들어 setFactory() 메소드로 넣어준다.
        ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
//              TextSwitcher에 넣어줄 TextView를 만든다.
                TextView textView = new TextView(getApplicationContext());
//              필요하다면 TextView의 속성을 변경한다.
                textView.setTextSize(50.0f);
                textView.setTextColor(Color.MAGENTA);
                textView.setGravity(Gravity.CENTER);

                return textView;
            }
        });

//      TextSwitcher에 setText() 메소드로 표시할 문자열을 넣어준다.
        ts.setText(texts[index]);

//      프로그램에서 ImageSwitcher에 이미지를 표시하는 ImageView를 ViewFactory 객체로 만들어 setFactory() 메소드로 넣어준다.
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
//              ImageSwitcher에 넣어줄 ImageView를 만든다.
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
//      ImageSwitcher에 setImageResource() 메소드로 표시할 그림을 넣어준다.
        is.setImageResource(images[index]);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN) {
            downX = event.getX();
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();

            if(downX < upX) {

                ts.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in));
                ts.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out));
                ts.setText(texts[index]);

                is.setImageResource(images[index]);

            } else if(downX > upX) {

                ts.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out));
                ts.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in));
                ts.setText(texts[index]);

            }
        }
        return super.onTouchEvent(event);
    }
}
