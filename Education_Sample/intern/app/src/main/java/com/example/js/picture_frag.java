package com.example.js;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.app.Fragment;
import android.widget.Button;

public class picture_frag extends Fragment {
    ViewGroup rootView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_picture_frag, container, false);

        // 그리드뷰 선언
        GridView gridView = rootView.findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(getContext()));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // FullImageActivity로 갈 인텐트 생성
                Intent i = new Intent(getContext(), FullImageActivity.class);
                // 선택한 이미지의 포지션을 인텐트에 실어줌
                i.putExtra("id", position);
                // 인텐트 보내기
                startActivity(i);
            }
        });

        return rootView;
    }

}
