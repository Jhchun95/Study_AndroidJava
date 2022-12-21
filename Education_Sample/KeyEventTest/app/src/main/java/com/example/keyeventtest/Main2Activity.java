package com.example.keyeventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//      Build.VERSION.SDK_INT => 현재 사용 중인 안드로이드 버전을 얻어온다. => 21(롤리파)
        Toast.makeText(getApplicationContext(), Build.VERSION.SDK_INT + "", Toast.LENGTH_SHORT).show();
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.RED);
            getWindow().setNavigationBarColor(Color.GREEN);
        }

    }

    public void goBack(View view) {
        finish();
    }

    @Override
//  onKeyDown() : 안드로이드 기기의 버튼이 눌리면 자동으로 실행되는 메소드
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//      KeyEvent.KEYCODE_BACK => 안드로이드 기기의 뒤로가기 버튼을 의미한다.
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getApplicationContext(), "Back 키 누름", Toast.LENGTH_SHORT).show();
//          뒤로가기 키가 눌렸을 때 true를 리턴시키면 뒤로 가기 키를 눌러도 액티비티를 빠져나가지 않는다.
//          return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
