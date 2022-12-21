package com.example.datasendtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tv2 = findViewById(R.id.tv2);

        Intent intent = getIntent();
//      getParcelableExtra() : Parcelable 인터페이스를 구현받아 직렬화된 클래스 객체를 받는다.
//      getParcelableExtra() 메소드는 Parcelable 인터페이스를 구현받은 객체를 Intent 객체에서 받는 메소드로 Parcelable
//      인터페이스가 구현된 클래스가 무엇인지 모르기 때문에 반드시 형변환 시켜서 저장해야 한다.

//      getParcelableExtra() 메소드는 Parcelable 인터페이스를 구현받은 객체를 Serializable 인터페이스를 구현해 직렬화된
//      객체를 받을 때 처럼 형변환 시키지 않아도 정상적으로 처리된다. => Parcelable 인터페이스로 직렬화를 권장한다.
//      만약, 에러가 발생된다면 형변환시키면 된다.


        SimpleData simpleData = (SimpleData) intent.getParcelableExtra("simpleData");
        Toast.makeText(getApplicationContext(), simpleData.toString(), Toast.LENGTH_SHORT).show();

        String str = simpleData.getName() + "(" + simpleData.getAge() + "," + (simpleData.isGender() ? "남" : "여") + ")";
        // true false를 return 시키는 것에선 is가 붙음
        tv2.setText(simpleData.toString());
    }

    public void goBack(View view) {

        finish();
    }
}
