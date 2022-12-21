package com.example.jjh10.jsoupexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // private ImageView imageView1, imageView2, imageView3, imageView4;
    String url = "https://www.canva.com/photos/free/";
    String title, link, src, imgName;

    //now to store the links we use array list
    ArrayList list = new ArrayList();
    ArrayList imgNameList = new ArrayList();
    ProgressDialog progressDialog;

    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Content content = new Content();
        content.execute();
        adapter = new MyRecyclerViewAdapter(this, imgNameList, list);
        recyclerView.setAdapter(adapter);
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(url).get();

                //gives the title of the page
                //title = doc.title();
                //gives the every links on that page
                //Elements links = doc.select("a[href]");
                //link=links.text().toString();

                Elements img = doc.getElementsByTag("img");
                for(Element el : img) {
                    src = el.absUrl("src");
                    imgName = el.attr("alt");
                    imgNameList.add(imgName);
                    list.add(src);
                }
                Log.d("image links", list.toString());
                Log.d("image Names", imgNameList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
