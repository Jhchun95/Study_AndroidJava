package com.example.xyeventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button event_btn;
    TextView box,txt_view;
    boolean isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        event_btn = findViewById(R.id.event_btn);
        txt_view = findViewById(R.id.txt_view);
        box = findViewById(R.id.box);

        event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCheck = !isCheck;
            }
        });

        txt_view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent Event) {

                String msg = "";
                int x = 0;
                int y = 0;

                switch (Event.getAction() ) {

                    case MotionEvent.ACTION_DOWN :
                        msg = "result : down";
                        break;

                    case MotionEvent.ACTION_UP :
                        msg = "result : up";
                        break;

                    case MotionEvent.ACTION_MOVE :

                        x= (int) event_btn.getX();
                        y= (int) event_btn.getY();
                        msg = "x : " + x + " y : " + "y";
                        break;
                }

                txt_view.setText(msg);

                return isCheck;
            }
        });


    }
}
