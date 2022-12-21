package com.example.layouttestit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

// 레이아웃에서 id를 지정한 위젯 제어에 사용할 객체를 선언한다.
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

//      제어할 위젯을 찾아서 객체에 대입한다.
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

    }

    public void goBack(View view) {
        finish();
    }

    public void viewData(View view) {

        String id = String.valueOf(et1.getText());
// %d => 정수, %f => 실수, %c => 문자, %s => 문자열
        String pw = String.format("%s", et2.getText());
        Toast.makeText(getApplicationContext(), id + "(" + pw +")", Toast.LENGTH_SHORT).show();

        et1.setText("");
        et2.setText("");
//      id를 입력하는 EditText로 focus를 옮겨준다.
        et1.requestFocus();


    }
}
