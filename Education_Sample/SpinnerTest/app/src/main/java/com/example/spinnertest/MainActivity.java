package com.example.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] datas = {"스위스", "프랑스", "독일", "스웨덴", "영국", "도쿄", "오사카"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);

//      Spinner에 넣어줄 데이터를 Adapter를 만들어 등록한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item, datas);
        spinner.setAdapter(adapter);

//      Spinner에서 특정 항목을 선택했을 때 동작으 저정도게 하려면 ItemSelectedListener를 걸어준다.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
//          Spinner를 선택했을 때 실행할 동작을 지정한다.
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), datas[position] + "에 가다", Toast.LENGTH_SHORT).show();
            }

            @Override
//          아무것도 선택하지 않았을 때 실행할 동작을 지정한다.
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
