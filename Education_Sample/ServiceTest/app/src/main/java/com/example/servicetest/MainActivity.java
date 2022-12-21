package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn2.setEnabled(false);
    }

    public void start(View view) {
//      서비스 실행에 사용할 Intent 객체를 만든다.
//      서비스를 실행할 때 Intent 객체는 생성자의 첫 번째 인수로 getApplicationContext() 대신 getBaseContext()를 사용하는 것을 권장하고
//      사용하는 것을 권장하고 두 번째 인수로 서비스 기능이 구현된 클래스를 넘겨줘야 한다.
        Intent intent = new Intent(getBaseContext(), MyService.class);
//      액티비티를 새로 띄우는 것이 아니기 때문에 startActivity() 메소드를 사용하면 안되고 startService() 메소드로
//      서비스를 실행한다.
        startService(intent);
//      startService(new Intent(getBaseContext(), MyService.class));
        btn1.setEnabled(false);
        btn1.setEnabled(true);

    }

    public void end(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
        btn2.setEnabled(true);
        btn2.setEnabled(false);

    }
}
