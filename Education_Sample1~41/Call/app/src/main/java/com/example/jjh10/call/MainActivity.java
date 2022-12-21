package com.example.jjh10.call;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    DataPicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp= (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.ediDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

    }
}
