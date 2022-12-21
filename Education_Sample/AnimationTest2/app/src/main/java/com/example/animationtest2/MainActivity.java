package com.example.animationtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSequence , btnFadeIn , btnFadeOut, btnCrossFade, btnZoomIn, btnZoomOut, btnSlideUp, btnSlideOut;
    TextView tvSequence , tvFadeIn , tvFadeOut, tvCrossFadeIn, tvCrossFadeOut, tvZoomIn, tvZoomOut, tvSlideUp, tvSlideOut;
    Animation sequence , fadeIn, fadeOut, zoomIn , zoomOut ,slideUp, slideOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Sequence
        btnSequence = findViewById(R.id.btnSequence);
        tvSequence = findViewById(R.id.tvSequence);
        sequence = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sequence);
        btnSequence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSequence.startAnimation(sequence);
            }
        });

//      FadeIn
        btnFadeIn = findViewById(R.id.btnFadeIn);
        tvFadeIn  = findViewById(R.id.tvFadeIn);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        btnFadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFadeIn.startAnimation(fadeIn);
            }
        });

//      FadeOut
        btnFadeOut = findViewById(R.id.btnFadeOut);
        tvFadeOut  = findViewById(R.id.tvFadeOut);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        btnFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFadeOut.startAnimation(fadeOut);
            }
        });

//      CrossFade
        btnCrossFade = findViewById(R.id.btnCrossFade);
        tvCrossFadeIn  = findViewById(R.id.tvCrossFadeIn);
        tvCrossFadeOut  = findViewById(R.id.tvCrossFadeOut);
        btnCrossFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCrossFadeIn.startAnimation(fadeIn);
                tvCrossFadeOut.startAnimation(fadeOut);
            }
        });

//      ZoomIn
        btnZoomIn = findViewById(R.id.btnZoomIn);
        tvZoomIn  = findViewById(R.id.tvZoomIn);
        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        btnCrossFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZoomIn.startAnimation(zoomIn);
            }
        });


//      ZoomOut
        btnZoomOut = findViewById(R.id.btnZoomOut);
        tvZoomOut  = findViewById(R.id.tvZoomOut);
        zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        btnCrossFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZoomOut.startAnimation(zoomOut);
            }
        });

        btnSlideUp = findViewById(R.id.btnSlideUp);
        tvSlideUp  = findViewById(R.id.tvSlideUp);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        btnSlideUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSlideUp.startAnimation(slideUp);
            }
        });

        btnSlideOut = findViewById(R.id.btnSlideOut);
        tvSlideOut  = findViewById(R.id.tvSlideOut);
        slideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out);
        btnSlideOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSlideOut.startAnimation(slideOut);
            }
        });

    }
}
