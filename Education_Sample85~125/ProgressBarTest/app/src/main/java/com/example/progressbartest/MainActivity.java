package com.example.progressbartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable{

    TextView tv;
//  ProgressBar에 대한 변경 작업을 프로그램에서 하려면 Thread에서 메시지를 만들어 Bundle 객체에 저장한 후 Handler로 넘겨준다.
    ProgressBar progressBar;
    SeekBar seekBar;
//  Handler 객체를 선언한다.
    ProgressHandler handler;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        progressBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);
        ratingBar = findViewById(R.id.ratingBar);

//      Handler 클래스를 상속받아 작성한 내부 클래스 ProgressHandler 객체를 선언한다.
        handler = new ProgressHandler();
        new Thread(this).start();

//      SeekBar가 변경될 때 마다 실행할 동작이 있으면 SeekBarChangeListener를 걸어준다.
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
//          SeekBar가 움직일 때 마다 실행된다.
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("SeekBar 현재값 : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "SeekBar 터치 시작", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "SeekBar 터치 끝", Toast.LENGTH_SHORT).show();
            }
        });

//      RatingBar의 변경이 완료된 후 실행할 동작이 있으면
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv.setText("별점: " + ratingBar.getRating());
            }
        });



    }

    @Override
    public void run() {
        int value = 0;
        while(value < 1000) {
            value ++;
//          스레드에서 변경된 내용으로 UI를 갱신하도록 알려준다.
//          Handler를 통해서 ProgressBar로 메세지를 전달하는 Message 클래스 객체를 만든다.
            Message message = handler.obtainMessage();
//          ProgressBar로 전달할 데이터를 저장하는 Bundle 클래스 객체를 만든다.
            Bundle bundle = new Bundle();
//          Bundle 객체에 ProgressBar로 전달할 데이터를 저장한다.
            bundle.putInt("value", value);
//          Bundle 객체에 저장된 데이터를 Message 객체에 넣어준다.
            message.setData(bundle);
//          Message를 Handler로 전달한다.
            handler.sendMessage(message);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//  Handler 클래스를 상속받아 UI를 갱신하는 내부 클래스를 만든다.
    class ProgressHandler extends Handler {
//      Handler를 통해 넘어오는 데이터를 받아주는

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//          Handler를 통해 넘어오는 데이터를 받는다.
            int value = msg.getData().getInt("value");
//          Handler를 통해서 넘어온 데이터로 UI를 갱신한다.
            if(value < 1000) {
                tv.setText("현재 값: " + value);
            } else {
                tv.setText("작업완료");
            }
//          ProgressBar에 데이터를 넣어준다.
            progressBar.setProgress(value);

        }
    }
}
