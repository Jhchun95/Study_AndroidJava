package com.example.videotest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

// SurfaceView를 사용해 동영상을 재생하는 클래스는 SurfaceHolder.Callback 인터페이스를 구현받아 만든다.
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Button startBtn, stopBtn;
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
//  SurfaceView를 이용해서 버퍼에 그림을 그리면 SurfaceView에 반영되고 그 결과가 사용자의 View에 표시된다.
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        surfaceView = findViewById(R.id.surfaceView);

//      SurfaceView에 표시될 그림을 그리기 위해서 SurfaceView에서 Holder를 얻어낸다.
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

    }

    public void videoView(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.startBtn:
//              isPlaying() : 미디어 플레이어가 재생중이면 true, 그렇지 않으면 false를 가리킨다.
                if(mediaPlayer.isPlaying()) { // 미디어 플레이어가 재생중인가?
//                  미디어 플레이어가 재생중이면 비디오 재생을 멈추고 버튼 위의 문자열을 Pause로 변경한다.
                    mediaPlayer.pause();; // 미디어 플레이어를 일시정지 시킨다.
                    startBtn.setText("Pause");
                } else {
//                  미디어 플레이어가 재생중이 아니면 비디오 재생을 시작하고 문자열을 Play로 변경한다.
                    mediaPlayer.start();
                    startBtn.setText("Play");
                }
                break;
            case  R.id.stopBtn:
                mediaPlayer.stop();     // 미디어 플레이어를 중지시킨다.
                break;
        }
    }



    public void videoView(View view) {
        finish();
    }

//  여기부터

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Toast.makeText(getApplicationContext(), "비디오 재생 준비 완료", Toast.LENGTH_SHORT).show();
        if(mediaPlayer == null ) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }

//      AssetFileDescriptor 클래스 객체에 MediaPlayer가 재생할 비디오 파일을 지정한다.
//      재생할 비디오 파일이 assets 폴더에 있을 경우
        try {
            AssetFileDescriptor afd = getAssets().openFd("movie.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
