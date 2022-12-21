package com.example.jjh10.webserverimagedownload0308;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.Button01);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    // 1~6 까지의 랜덤한 정수를 구한다.
                    Random random = new Random();
                    int i = (random.nextInt(6)) + 1;

                    //photo1.jpg, photo2.jpg..........
                    String imageUrl = "http://www.telepasi.kr/share/";
                    String fileUrl = imageUrl + "photo" + i + ".jgp";
                    URL url = new URL(fileUrl);
                    Log.i("MY_TAG", fileUrl);

                    //Open a HTTP connection to the URL
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); //Allow Inputs
                    conn.connect();

                    InputStream is = conn.getInputStream();

                    Bitmap mBitmap = BitmapFactory.decodeStream(is);
                    ImageView imageview = (ImageView) findViewById(R.id.ImageView01);
                    imageview.setImageBitmap(mBitmap);

                    conn.disconnect();
                } catch (IOException e) {
                    Log.e("MY_TAG", "Network Error");
                    e.printStackTrace();
                }
            }
        });
    }
}
