package com.example.graphictest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_main);

//      사용자 View를 액티비티에 표시하려면 setContentView() 메소드에서 사용자 View 객체를 만들고 생성자의 인수로 현재 액티비티를 넘겨준다.
        setContentView(new MyView(getApplicationContext()));

    }
}
