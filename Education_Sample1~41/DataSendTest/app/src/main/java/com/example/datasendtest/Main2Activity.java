package com.example.datasendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.tv);

//      getIntent() 메소드로 이전 액티비티에서 Intent 객체에 저장해서 넘겨주는 데이터를 받는다.
        Intent intent = getIntent();
//      Intent 객체에 저장되서 넘어온 데이터를 받으려면 get자료형Extra() 메소드를 사용한다. ex) 문자열 getStringExtra, 정수 getInter~
        String name = intent.getStringExtra("name");
        if(name == null) {
            name = "이름이 넘어오지 않았습니다.";
        }
//      안드로이드 스튜디오 3.0 이전 버전에서는 getStringExtra() 메소드로 넘어오는 데이터가 null이면 예외가 발생되었기
//      때문에 아래와 같이 예외 처리를 해야 했었다.
//        String name1 = "";
//        try {
//            name1 = intent.getStringExtra("name");
//        } catch (Exception e) {
//            name1 = "이름이 넘어오지 않았습니다.";
//        }

//      Toast.makeText(getApplicationContext(), name + "님 어서오세요", Toast.LENGTH_SHORT).show();

//      getStringExtra() 메소드 이외의 메소드들은 넘어오는 데이터가 없을 경우 사용할 기본값을 두 번째 인수로 지정해야 한다.
        int age = intent.getIntExtra("age", 0);
        int count = intent.getIntExtra("count", 0);

//      Toast.makeText(getApplicationContext(), count + " 번째 메세지" + name + "님은 " + age + "살 입니다." , Toast.LENGTH_SHORT).show();
        tv.append("\n" + count + " 번째 메세지" + name + "님은 " + age + "살 입니다.") ;
    }

    public void FinishButton(View view) {

        finish();
    }
}
