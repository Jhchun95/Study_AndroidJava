package com.example.layouttest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
//  boolean flag = true;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        loadImage(R.drawable.image01);


    }

    private void loadImage(int id) {
//      id는 정수라고 한다.

//      프로그램 실행 중에 이미지를 읽어온다.
//      이미지는 리소스(res 폴더와 하위 폴더 전체)에 있으므로 Resources 객체에 얻어온다.
//      getResources() : 리소스를 얻어온다.
        Resources resources = getResources();
//      Resources 객체에서 이미지를 읽어서 BitmapDrawable 객체에 저장시킨다.
//      getDrawable() : 인수로 지정된 이미지(이미지 id)를 Resources 객체(res 폴더)에서 얻어온다.
        BitmapDrawable bitmapDrawable = (BitmapDrawable) resources.getDrawable(id);
//      ImageView 객체 Resources 객체에서 읽어서 BitmapDrawable 객체에 저장한 이미지를 넣어준다.
//      setImageDrawable() : ImageView에 이미지를 넣어준다.
        iv.setImageDrawable(bitmapDrawable);

//      getIntrinsicWidth() : 원래 이미지의 폭
//      getIntrinsicHeight() : 원래 이미지의 높이
        Toast.makeText(getApplicationContext(),
                bitmapDrawable.getIntrinsicWidth() + " * " + bitmapDrawable.getIntrinsicHeight(),
                Toast.LENGTH_SHORT).show();
        /*Toast.makeText(getApplicationContext(),
                iv.getLayoutParams().width + " * " + iv.getLayoutParams().height,
                Toast.LENGTH_SHORT).show(); */
    }

    public void changeImage(View view) {

//        flag = !flag;
//        loadImage(flag ? R.drawable.image01 : R.drawable.image02);

          int id = 0;
          switch (++index % 4) {

              case 0:
                  id = R.drawable.image01;
                  break;

              case 1:
                  id = R.drawable.image02;
                  break;

              case 2:
                  id = R.drawable.dream01;
                  break;

              case 3:
                  id = R.drawable.dream02;
                  break;
          }
          loadImage(id);
    }
}
