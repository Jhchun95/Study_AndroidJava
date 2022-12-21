package com.example.radiobuttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox check;
    TextView tv;
    RadioButton radio1, radio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = findViewById(R.id.check);
        tv = findViewById(R.id.tv);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
    }

    public void selectCheckBox(View view) {

//      isChecked() : RadioButton이나 CheckBox가 선택된 상태면 true, 해제된 상태면 false를 리턴한다.
//      setChecked() : RadioButton이나 CheckBox를 선택(true) 또는 해제(false)시킨다.
//
//        if(check.isChecked()) {
//            check.setChecked(false);
//        } else {
//            check.setChecked(true);
//        }
//        check.setChecked(check.isChecked() ? false : true );
        check.setChecked(!check.isChecked()); // true이면 false, false 이면 true로 바꿀 수 있다.

    }

    public void ViewResult(View view) {

//        Toast.makeText(getApplicationContext(), radio1.isChecked() ? "남자" : "여자", Toast.LENGTH_SHORT).show();
        String str = (radio1.isChecked() ? "남자" : "여자") + "는" + (check.isChecked() ? "하루종일" : "반나절만") + "일을 합니다." ;
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

//      setText() : TextView에 문자열을 넣어준다. => 기존에 입력된 문자열을 지운다.
//        tv.setText(str);
//      append() : TextView에 문자열을 넣어준다. => 기존에 입력된 문자열 뒤에 추가한다.
        tv.append(str);

    }
}
