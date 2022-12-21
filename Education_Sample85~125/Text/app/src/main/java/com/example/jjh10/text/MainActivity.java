package com.example.jjh10.text;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = { "CSI-뉴욕" , "CSI-라스베가스", "CSI-마이애미", "Friends",
                            "Fringe", "Lost" };

        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R)
    }
}
