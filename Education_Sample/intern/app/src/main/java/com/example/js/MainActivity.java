package com.example.js;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnImg, btnVideo;
    FragmentManager fm;
    FragmentTransaction tran;
    picture_frag pic;
    video_frag video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnImg = (Button) findViewById(R.id.btnImg);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnImg.setOnClickListener(this);
        btnVideo.setOnClickListener(this);


        pic = new picture_frag();
        video = new video_frag();
        setFrag(0); //디폴트는 사진으로 해놈
    }
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnImg:
                    setFrag(0);
                    break;
                case R.id.btnVideo:
                    setFrag(1);
                    break;

            }
        }

    public void setFrag(int n) {    //프레그먼트 교체 메소드
        fm = getFragmentManager();
        tran = fm.beginTransaction();
        switch (n) {
            case 0:
                tran.replace(R.id.main_frame,pic);
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.main_frame,video);
                tran.commit();
                break;

        }
    }

}