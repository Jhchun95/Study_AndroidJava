package com.example.jjh10.recyclerviewddd;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyRecyclerViewClickListener {

    public static final String EXTRA_URL = "img_url";
    public static final String EXTRA_DATE = "date";

    private RecyclerView recyclerView;
    private ArrayList<MyData> list = new ArrayList();
    String url ="http://192.168.0.3:3000/";

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
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        //AsyncTask 작동시킴(파싱)
        new Description().execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), ""+ position , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveButtonClicked(int position) {

        try {
            MyData clickedItem = list.get(position);
            Uri uri = Uri.parse(clickedItem.getImg_url());
            DownloadManager.Request request = new DownloadManager.Request(uri);

            request.setTitle("Image");// Title of the Download Notification
            request.setDescription("Downloading");// Description of the Download Notification
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);// Visibility of the download Notification
            request.setDestinationInExternalPublicDir("Download", "Image" + ".png");// Uri of the destination file

            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.

        }catch (Exception e){
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {                                                      //1. if no permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {                                   //3. if user click deny, re-requestPermission
                    Toast.makeText(getBaseContext(), "첨부파일 다운로드를 위해\n동의가 필요합니다.", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            110);
                } else {                                                                                          // 2.first requestPermission : yes or no
                    Toast.makeText(getBaseContext(), "첨부파일 다운로드를 위해\n동의가 필요합니다.", Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            110);
                }
            }
        }
    }

    @Override
    public void lookButtonClicked(int position) {
        Intent GalleryPreview = new Intent(this, GalleryPreview.class);
        MyData clickedItem = list.get(position);

        GalleryPreview.putExtra(EXTRA_URL, clickedItem.getImg_url());
        GalleryPreview.putExtra(EXTRA_DATE, clickedItem.getDate());

        startActivity(GalleryPreview);
    }


    private class Description extends AsyncTask<Void, Void, Void> {

        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(url).get();
                for(Element table : doc.select("table[class=detectfail]")){
                    for(Element row : table.select("tr")){
                        Elements tds = row.select("td");
                        String my_date = tds.get(0).text();
                        Elements imgSrc = row.select("img");
                        String my_imgUrl = imgSrc.attr("src");

                        list.add(new MyData(my_date, my_imgUrl));
                        Log.e("태그","날짜" + my_date + "이미지" + my_imgUrl);
                    }
                }
                //Log.e("debug :", "List " + doc.select("table[class=detectfail]").size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            //ArraList를 인자로 해서 어답터와 연결한다.
            MyAdapter myAdapter = new MyAdapter(list);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();

            myAdapter.setOnMyClickListener(MainActivity.this);

        }

    }

}