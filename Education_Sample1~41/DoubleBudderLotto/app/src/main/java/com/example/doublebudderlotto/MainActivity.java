package com.example.doublebudderlotto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_main);
//      setContentView(new LottoView1(getApplicationContext()));
        setContentView(new LottoView3(getApplicationContext()));
    }
}
