package com.example.jjh10.recyclerviewddd;

/**
 * Created by jjh10 on 2019-05-03.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * Created by jjh10 on 2019-04-30.
 */

public class SplahActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(700); // 대기 초 설정
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
