package com.example.layouttestti2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    ImageView iv1, iv2;
//    boolean flag = true;

    ImageView[] imageViews = new ImageView[4];
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      iv1 = findViewById(R.id.iv1);
//      iv2 = findViewById(R.id.iv2);

//      imageViews[0] = findViewById(R.id.iv1); 되는 코드인데 임의로 보여드린 것
//      imageViews[1] = findViewById(R.id.iv2);
//      imageViews[2] = findViewById(R.id.iv3);
//      imageViews[3] = findViewById(R.id.iv4);

        for(int i=0; i< imageViews.length; i++) {
            imageViews[i] = findViewById(R.id.iv1 + i);
//            Log.e("R.id.iv" + (i + 1), R.id.iv1 + i + "");
        }

    }

    public void changeImage(View view) {
        /*
        if(flag) {
            iv1.setVisibility(View.INVISIBLE);
            iv2.setVisibility(View.VISIBLE);
        } else {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.INVISIBLE);
        }
        flag = !flag;
         */
//      FrameLayout의 모든 이미지를 숨긴다.
        for(int i=0; i< imageViews.length; i++) {
            imageViews[i].setVisibility(View.INVISIBLE);
        }
//      표시할 이미지만 FrameLayout에 표시한다.
        /*
        if(++index == imageViews.length) {
            index = 0;
        }
        imageViews[index].setVisibility(View.VISIBLE);
        */
        imageViews[++index % imageViews.length].setVisibility(View.VISIBLE);
        // index가 4 값이 될때 오류가 발생하게 되는데 4가 될때의 값을 0으로 만들어서 0123 0123 반복한다.
         /*
         if(--index == imageViews.length-1) { // 이거 한번 적어본거임
            index = -1;
        }
         */
    }
}
