package com.example.jjh10.searchsource;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private AutoCompleteTextView autoComplete;
    private String item[] = {

            "돈카츠" , "돈카츠덮밥" , "일본 돈카츠",

            "네네 치킨" , "교촌 치킨" , "BHC 치킨" , "BBQ 치킨" , "치킨카츠" , "피자나라 치킨공주",

            "라멘", "돈코츠라멘", "소유라멘", "규카츠"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoComplete = (AutoCompleteTextView) findViewById(R.id.myautocomplete);

        autoComplete.addTextChangedListener(this);

        autoComplete.setAdapter(new ArrayAdapter<String>(

                this,

                android.R.layout.simple_dropdown_item_1line,

                item));

        autoComplete.setTextColor(Color.RED);


    }
    public void afterTextChanged(Editable arg0) {

        // TODO Auto-generated method stub

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        // TODO Auto-generated method stub


    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        // TODO Auto-generated method stub
    }
}
