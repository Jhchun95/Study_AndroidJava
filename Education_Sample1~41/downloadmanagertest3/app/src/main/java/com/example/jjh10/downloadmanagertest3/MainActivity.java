package com.example.jjh10.downloadmanagertest3;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private long downloadID;

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(MainActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
        }


        setContentView(R.layout.activity_main);
        button=findViewById(R.id.download);
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginDownload();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);

    }
    private void beginDownload(){

        try {
        /*        File file=new File(getExternalFilesDir(null),"Dummy");

        Create a DownloadManager.Request with all the information necessary to start the download
         */
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://developer.android.com/images/home/honeycomb-android.png"));
            request.setTitle("TestImage");// Title of the Download Notification
            request.setDescription("Downloading");// Description of the Download Notification
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);// Visibility of the download Notification
            request.setDestinationInExternalPublicDir("Download", "TestImage" + ".png");// Uri of the destination file
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}