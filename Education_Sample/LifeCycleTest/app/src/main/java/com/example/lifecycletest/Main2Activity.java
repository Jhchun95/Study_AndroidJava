package com.example.lifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e("LifeCycle", "MainActivity onCreate()");
        et = findViewById(R.id.et);

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LifeCycle", "MainActivity onPause() 실행");

//      데이터를 xml 파일로 안드로이드 기기에 저장한다. => 간단한 데이터 저장
//      안드로이드는 앱이 종료되면 앱이 메모리에 가지고 있던 데이터가 사라지기 때문에 재 실행시 필요한 데이터라면
//      SharedPreferences 인터페이스 객체를 사용해 안드로이드 단말기 내부에 xml 파일 형태로 key와 value가 쌍으로 구성되는
//      데이터를 저장할 수 있다. => Device File Explorer에 파일을 확인할 수 있다.

//      SharedPreferences는 Editor로 접근해서 데이터를 저장하고 SharedPreferences로 접근해서 데이터를 가져온다.
//      getSharedPreferences("xml 파일 이름", 저장방법);
//      저장 방법
//      MODE_PRIVATE => xml 파일을 새로 만들고 데이터를 저장한다.
//      MODE_APPEND => xml 파일이 존재할 경우 xml 파일에 데이터를 추가한다.
//      MODE_WORLD_READABLE => 다른 앱이 xml 파일을 읽을 수 있도록 허용한다.
//      MODE_WORLD_WRITEABLE => 다른 앱이 xml 파일에 쓸 수 있또록 허용한다.

//      LifeCycle.xml 파일을 새로 만들어 데이터를 저장할 수 있도록 준비한다.
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);

//      LifeCycle.xml 파일에 데이터를 저장할 수 있도록 Editor를 이용해 편집할 수 있는 상태로 만든다.
        SharedPreferences.Editor editor = sharedPreferences.edit();

//      LifeCycle.xml 파일에 데이터를 넣어준다. => 저장
        editor.putString("message", "오늘도 수고하셨습니다 ~~~~");
        editor.putString("message", "오늘도 수고하셨습니다 ~~");

//      LifeCycle.xml 파일에 EditText에 입력한 데이터를 넣어준다.
        editor.putString("id1", String.valueOf(et.getText()));
        editor.putString("id2", et.getText().toString());
        editor.putString("id3", et.getText() + "");

//      LifeCycle.xml 파일을 저장한다. => 작업이 완료 => commit
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LifeCycle", "Main2Activity onResume() 실행");

//      LifeCycle.xml 파일에서 데이터를 읽어와 복원한다.
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);
//      contains() : sharedPreferences 인터페이스 객체에 인수로 지정된 name이 있으면 true, 없으면 false를 리턴한다.
        if(sharedPreferences != null && sharedPreferences.contains("id1")) {
            et.setText(sharedPreferences.getString("id1", "없어요"));
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void goBack2(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("LifeCycle", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//      LifeCycle.xml 파일의 id1 이라는 name을 제거한다.
//      실제로 name이 제거되는 것을 확인하려면 onPause() 메소드를 주석으로 처리하면 확인이 가능하다.
        editor.remove("id1");
        editor.commit();
        finish();
    }
}
