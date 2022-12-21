package com.example.jjh10.downmanager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://developer.android.com/images/home/honeycomb-android.png");
                //** Uri uri = Uri.parse("http://192.168.0.18:3000/image0.jpg"); **/

                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setTitle("newFilename");
                request.setDescription("Android Data download using DownloadManager");
                request.allowScanningByMediaScanner();

                /**
                 *                 request.setDestinationInExternalFilesDir(MainActivity.this, Environment.DIRECTORY_DOWNLOADS, newFilename);
                 request.setDestinationInExternalPublicDir("Download", ".png");

                 *
                 *  request.setDestinationInExternalFilesDir(MainActivity.this, Environment.DIRECTORY_DOWNLOADS, "Large image.jpg");


                 *
                 *
                 *  request.setDescription("Download image");

                 request.setVisibleInDownloadsUi(true);
                 request.allowScanningByMediaScanner();

                 *
                 * request.setDescription("Downloading " + "Sample" + ".png"); // 다운로드 항목에 표시될 세부 설명을 지정합니다
                 * request.setDestinationInExternalPublicDir("/Path", "test.jpg");

                  **/

                downloadManager.enqueue(request);

            }

        });
    }
}

