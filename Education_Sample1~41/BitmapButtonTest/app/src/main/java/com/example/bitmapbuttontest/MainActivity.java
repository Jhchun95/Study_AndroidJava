package com.example.bitmapbuttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TitleButton titleBtn;
    BitmapTitleButton bitmapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleBtn = findViewById(R.id.titleBtn);
        bitmapBtn = findViewById(R.id.bitmapBtn);

//      TitleButton은 AppCompatAcitivity 클래스를 상속받아 만든 버튼이므로 TouchListener와 ClickListener를 모두 사용할 수 있다.

        /*
        titleBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getApplicationContext(), "동작", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
         */
//      Main에서는 clickListener를 권장함.
//      이미지 버튼에 ClickListener를 걸어주면 1번 실행된다 -> 이것을 권장한다.
        /*titleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ClickListener 동작", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ClickListener 잘 동작", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

//  BitmapTitleButton은 버튼이 아니고 RelativeLayout 이므로 TouchListener와 ClickListener를 사용해 동작을 지정할 수 없고
//  BitmapTitleButton에서 전개된 레이아웃을 구성하는 버튼에 TouchListener와 ClickListener를 사용 동작을 지정해야한다.
//  MainActivity에서 이미지 버튼에 TouchListener를 걸어주면 2번 실행된다.
//  bitmap_button.xml 파일에서 정의한 레이아웃이 전개되는 BitmapTitleButton의 TouchListener가 실행되지 않는다.

    public void clickTitleBtn(View view) {
        Toast.makeText(getApplicationContext(), "android:onClick 동작", Toast.LENGTH_SHORT).show();
    }

//  BitmapTitleButton을 레이아웃

}
