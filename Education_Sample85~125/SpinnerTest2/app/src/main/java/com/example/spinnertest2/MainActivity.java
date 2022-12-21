package com.example.spinnertest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ImageView iv;

//  큰 국기 이미지의 id를 기억할 ArrayList
    ArrayList<Integer> country = new ArrayList<>();
//  국가 이름을 기억할 문자열 배열
    String[] nation = {"안도라", "아랍에미레이트연합", "아프가니스탄", "엔티카바부다", "알바니아", "아르메니아", "앙골라", "아르헨티나", "오스트리아", "오스트레일리아"};
//  Spinner에 넣어줄 데이터를 기억할 ArrayList
    ArrayList<Flag> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        iv = findViewById(R.id.iv);

//      Spinner와 ImageView에 표시할 데이터를 저장한다.
        for(int i=0; i<20; i+= 2) {
//          country에 큰 국기 이미지의 id를 저장한다.
            country.add(R.drawable.ad + i + 1);
//          Flag 클래스 객체에 작은 국기 이미지의 id와 국가 이름을 저장해 list에 저장한다.
            Flag flag = new Flag();
            flag.setFlagId(R.drawable.ad + i);
            flag.setName(nation[i/2]);
            list.add(flag);
        }

//      Spinner에 나라 이름만 넣어주는 Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, nation);
//        spinner.setAdapter(adapter);

//      레이아웃을 전개하고 데이터를 넣어주는 클래스를 이용해 Adapter 클래스를 만드는 경우 => 클래스 2개 이용
//        FlagAdapter adapter = new FlagAdapter(getApplicationContext(), list);
//        spinner.setAdapter(adapter);

//      Adapter 클래스에서 레이아웃을 전개하고 데이터를 넣어주는 경우 => 클래스 1개 이용
        FlagViewAdapter adapter = new FlagViewAdapter(getApplicationContext(), R.layout.flag, list);
        spinner.setAdapter(adapter);



//      Spinner에서 나라가 선택되면 ImageView의 국기 이미지가 변경되도록 한다.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//              Toast.makeText(getApplicationContext(), nation[position],Toast.LENGTH_SHORT).show();
//              Toast.makeText(getApplicationContext(), list.get(position).getName(),Toast.LENGTH_SHORT).show();
                iv.setImageResource(country.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
