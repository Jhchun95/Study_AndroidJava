package com.example.datasendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv1 = findViewById(R.id.tv1);

        Intent intent = getIntent();

//      getSerializableExtra() : Serializable 인터페이스를 구현받아 직렬화된 클래스의 객체를 받는다.
//      getSerializableExtra() 메소드는 Serializable 인터페이스를 구현받은 객체를 Intent 객체에서 받는 메소드로
//      Serializable 인터페이스가 구현된 클래스가 어떤 클래스인지 모르기 때문에 반드시 형변환시켜서 저장해야한다.
        Person person = (Person) intent.getSerializableExtra("person");

        Toast.makeText(getApplicationContext(), person.toString() , Toast.LENGTH_SHORT).show();

//      Intent 객체로 넘어오는 객체 데이터에서 필요한 데이터만 얻어내려면 getter 메소드를 사용하면 된다.
        String str = person.getName() + "(" + person.getAge() + "," + (person.isGender() ? "남" : "여") + ")";
                // true false를 return 시키는 것에선 is가 붙음
        tv1.setText(person.toString());
    }

    public void goBack(View view) {

        finish();
    }
}
