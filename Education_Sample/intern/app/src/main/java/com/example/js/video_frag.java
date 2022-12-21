package com.example.js;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;

import androidx.annotation.Nullable;


public class video_frag extends Fragment {
    ViewGroup rootView;
    Button convertVideo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.activity_video_frag, container, false);

        return rootView;
    }

}
