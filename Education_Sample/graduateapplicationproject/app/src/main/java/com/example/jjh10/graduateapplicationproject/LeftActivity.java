package com.example.jjh10.graduateapplicationproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jjh10 on 2019-03-31.
 */

public class LeftActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.left);

        Button returnbtn = (Button) findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
