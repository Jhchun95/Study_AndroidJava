package com.example.layouttestit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

// 버튼이 클릭되면 activity_main.xml 파일에서 onClick 속성이 지정된 위젯의 정보가 View 클래스 타입의 인수 view로 넘어온다.
    public void viewActivity(View view) {
        /*
//      view로 넘어온 버튼 정보를 받는다.
//      activity_main.xml 파일에서 onClick 속성이 지정된 위젯이 무조건 버튼이 아닐 수 있기 때문에 넘겨받은 위젯의 정보를
//      Button으로 형변환 시켜서 받아야 한다.
        Button button = (Button) view;

//      view로 넘어온 버튼 위의 문자열을 얻어온다.
//      String cmd = (String) button.getText();   // 버튼 위의 문자열을 String 타입으로 형변환 시켜서 저장한다.
        String cmd = button.getText().toString(); // 버튼 위의 문자열을 toString() 메소드를 사용해 문자열로 저장한다.
//      Toast.makeText(getApplicationContext(), cmd, Toast.LENGTH_SHORT).show();

        switch (cmd) {
            case "LinearLayout 연습1(가로)" :
                Toast.makeText(getApplicationContext(), "연습1", Toast.LENGTH_SHORT).show();
                break;

            case "LinearLayout 연습2(세로)" :
                Toast.makeText(getApplicationContext(), "연습2", Toast.LENGTH_SHORT).show();
                break;

            case "LinearLayout 연습3(활용)" :
                Toast.makeText(getApplicationContext(), "연습3", Toast.LENGTH_SHORT).show();
                break;
        }
         */
//      view로 넘어온 버튼의 id를 받는다.
        int id = view.getId();
//      Toast.makeText(getApplicationContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
        // 중간 id를 입력하게 되면 text 자리이기 때문에 에러가 발생한다. String으로 cast해도 똑같이 에러난다. 그래서 String.valueof(id)로 선언한다.
//      Toast.makeText(getApplicationContext(), String.format("%d", id), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_SHORT).show();

        switch (id) {
            case R.id.btn1 :
                Toast.makeText(getApplicationContext(), "연습1", Toast.LENGTH_SHORT).show();
//              새로운 액티비티 만들기 : app 우클릭 => New => Activity => Empty Activity
//              new Intent(getApplicationContext(), 실행할 액티비티 클래스.class)
                Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent1);
                break;

            case R.id.btn2 :
                Toast.makeText(getApplicationContext(), "연습2", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent2);
                break;

            case R.id.btn3 :
                Toast.makeText(getApplicationContext(), "연습3", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent3);
                break;

        }
    }
}
