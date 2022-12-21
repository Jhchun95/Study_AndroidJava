package com.example.jjh10.pleaseend;


import java.io.File;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("암시적 인텐트 예제");

        Button btnDial = (Button) findViewById(R.id.btnDial);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnGoogle = (Button) findViewById(R.id.btnGoogle);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnSms = (Button) findViewById(R.id.btnSms);
        Button btnPhoto = (Button) findViewById(R.id.btnPhoto);

        btnDial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:010-2925-9400");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://maps.google.com/maps?q="
                        + 37.554264 + "," + 126.913598);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "안드로이드");
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "안녕하세요");
                intent.setData(Uri.parse("smsto:" + Uri.encode("010-2925-9400")));
                startActivity(intent);
            }
        });

        // 아래는 Nougat AVD에서는 작동하지 않고, Marshmallow AVD에서 작동함
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},MODE_PRIVATE);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/jeju13.jpg"));
                intent.setDataAndType(uri, "image/jpeg");
                startActivity(intent);
            }
        });
    }

}