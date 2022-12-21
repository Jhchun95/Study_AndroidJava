package com.example.jjh10.webserverfiledownloadsdcard0308;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.Button01);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    String fileUrl = "http://developer.android.com/images/home/honeycomb-android.png";
                    String fileName = "honeycomb-android.png";
                    URL url = new URL(fileUrl + fileName); // URL 객체 생성

                    //Open a HTTP connection to the URL
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.connect();

                    //입력 스트림 객체를 얻는다.
                    InputStream is = conn.getInputStream();

                    //파일 출력 스트림 객체를 얻는다. (SD 카드에 저장한다.)
                    File fileDir = Environment.getExternalStorageDirectory();
                    File newFile = new File(fileDir, fileName);
                    newFile.createNewFile();

                    FileOutputStream fos = new FileOutputStream(newFile.getAbsolutePath());

                    //입력 스트림으로부터 읽은 내용을 출력 파일에 저장한다.
                    byte[] readBuf = new byte[1024*1024];
                    int readSize = 0;

                    while(readSize != -1) {
                        readSize = is.read(readBuf);

                        if(readSize > 0 ) {
                            fos.write(readBuf, 0, readSize);
                        }
                    }

                    is.close();
                    fos.close();

                    conn.disconnect();

                    Toast.makeText(MainActivity.this, "파일이 저장되었습니다", Toast.LENGTH_SHORT).show();
                } catch (IOException e ){
                    Log.e("MY_TAG", "Network Error");
                    e.printStackTrace();
                }
            }
        });
    }
}
