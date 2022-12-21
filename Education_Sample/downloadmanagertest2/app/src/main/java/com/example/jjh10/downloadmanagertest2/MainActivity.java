package com.example.jjh10.downloadmanagertest2;



import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends Activity {

    final String DOWNLOAD_FILE = "http://developer.android.com/assets/images/home/honeycomb-android.png";
    /** Uri downloadUri = Uri.parse(DOWNLOAD_FILE); **/
    DownloadManager downloadManager;
    ImageView image;
    long id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        Button StartDownload = (Button)findViewById(R.id.startdownload);
        Button StopDownload = (Button)findViewById(R.id.stopdownload);
        StartDownload.setOnClickListener(StartDownloadOnClickListener);
        StopDownload.setOnClickListener(StopDownloadOnClickListener);
        image = (ImageView)findViewById(R.id.image);
    }
    Button.OnClickListener StartDownloadOnClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Uri downloadUri = Uri.parse(DOWNLOAD_FILE);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            long id = downloadManager.enqueue(request);
            request.setTitle("다운로드 제목");
            request.setDescription("항목 설명");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            id = downloadManager.enqueue(request);
        }
    };
    Button.OnClickListener StopDownloadOnClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (id != 0)
                downloadManager.remove(id);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        //다운로드가 완료되었을 때 발생하는 브로드캐스트를 등록한다
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(downloadReceiver);
    }

    private final BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            //ACTION_VIEW_DOWNLOADS 액션은 '다운로드' 액티비티를 호출할 때 사용한다
            //startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

            //Query 객체를 생성하고 다운로드한 데이터를 찾기 위한 조건을 넣는다
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            //다운로드한 데이터의 정보를 얻어 커서로 만든다
            Cursor cursor = downloadManager.query(query);
            if(cursor.moveToFirst()){
                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                int status = cursor.getInt(columnIndex);
                // 데이터의 상태를 읽어 정상적으로 다운로드되었는지 파악한다
                if(status == DownloadManager.STATUS_SUCCESSFUL){
                    try {
                        //다운로드되어 있는 파일을 처리한다
                        ParcelFileDescriptor file = downloadManager.openDownloadedFile(id);
                        FileInputStream fileInputStream =
                                new ParcelFileDescriptor.AutoCloseInputStream(file);
                        Bitmap bm = BitmapFactory.decodeStream(fileInputStream);
                        image.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}
