package com.example.layouttestit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


// app 좌측 상단 우클릭 - activity - EmptyActivity 과정을 거치면 MainActivity, xml , manifest 추가가 자동으로 된다.

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void goBack(View view) {

//      안드로이드는 주기적으로 화면이 쌓이는 방식이라서 finish를 선언하게 되면 mainAcitivy1 위에 2가 사라지며 1이 뜬다.
//      현재 액티비티를 닫는다.
        finish();
    }
}
