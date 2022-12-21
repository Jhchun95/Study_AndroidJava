package com.example.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

public class FullImageActivity extends Activity {
    ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        pager= (ViewPager)findViewById(R.id.pager);

        //인텐트 받아옴
        Intent i = getIntent();

        // 포지션 받아옴
        int position = i.getExtras().getInt("id");

        Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();

        // 어댑터 생성
        ImageAdapter imageAdapter = new ImageAdapter(this);
        pgAdapter pagerviewAdapter=new pgAdapter(getLayoutInflater(),this);
        pager.setAdapter(pagerviewAdapter);
        pager.setCurrentItem(position);

    }

    public void mOnClick(View v){
        int position;

        switch( v.getId() ){
            case R.id.btn_previous:
                position=pager.getCurrentItem();
                pager.setCurrentItem(position-1,true);
                break;
            case R.id.btn_next://다음버튼 클릭
                position=pager.getCurrentItem();
                pager.setCurrentItem(position+1,true);
                break;
        }
    }

}
