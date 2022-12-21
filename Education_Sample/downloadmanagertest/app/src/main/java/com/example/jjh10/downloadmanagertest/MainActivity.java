package com.example.jjh10.downloadmanagertest;


import java.util.List;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    private EditText downloadUrl;
    private Button addToQueueButton;
    private Button cancelLatestButton;
    private Button viewDownloadsButton;

    private long latestId = -1;

    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private Uri urlToDownload;

    private BroadcastReceiver completeReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "다운로드가 완료되었습니다.",Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadUrl = (EditText)findViewById(R.id.downloadUrl);
        addToQueueButton = (Button)findViewById(R.id.addQueueButton);
        cancelLatestButton = (Button)findViewById(R.id.cancelDownloadButton);
        viewDownloadsButton = (Button)findViewById(R.id.viewDownloadsButton);

        downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

        addToQueueButton.setOnClickListener(this);
        cancelLatestButton.setOnClickListener(this);
        viewDownloadsButton.setOnClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        IntentFilter completeFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(completeReceiver, completeFilter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addQueueButton:
                urlToDownload = Uri.parse(downloadUrl.getText().toString());
                List<String> pathSegments = urlToDownload.getPathSegments();
                request = new DownloadManager.Request(urlToDownload);
                request.setTitle("다운로드 예제");
                request.setDescription("항목 설명");
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pathSegments.get(pathSegments.size()-1));
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
                latestId = downloadManager.enqueue(request);
                downloadUrl.setText("");
                break;

            case R.id.cancelDownloadButton:
                downloadManager.remove(latestId);
                break;

            case R.id.viewDownloadsButton:
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(completeReceiver);
    }
}