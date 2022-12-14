package com.example.jjh10.imageparsingaddlistview0324;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView1, imageView2;
    String url ="https://www.canva.com/photos/free/";
    String title, link, src;
    //now to store the links we use array list
    ArrayList list = new ArrayList();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Content content = new Content();
        content.execute();

        imageView1 = (ImageView) findViewById(R.id.imageView_id);
        imageView2 = (ImageView) findViewById(R.id.imageView);


    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Picasso.with(getApplicationContext()).load(list.get(0).toString()).into(imageView1);
            Picasso.with(getApplicationContext()).load(list.get(1).toString()).into(imageView2);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(url).get();

                Elements img = doc.getElementsByTag("img");
                for(Element el: img) {
                    src = el.absUrl("src");
                    list.add(src);

                }
                Log.d("images links", list.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
