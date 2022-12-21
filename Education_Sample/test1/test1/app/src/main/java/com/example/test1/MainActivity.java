package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvContents = (TextView)findViewById(R.id.tv_contents);
        ImageView ivGlide = (ImageView)findViewById(R.id.iv_glide);

        Glide.with(this)
        .load("http://goo.gl/gEgYUd")
        .override(300, 200)
        .fitCenter()
        .into(ivGlide); }
}


