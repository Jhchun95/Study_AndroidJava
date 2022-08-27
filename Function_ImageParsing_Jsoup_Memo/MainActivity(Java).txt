package com.example.jjh10.jsoupexmapleeee0311;

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
    private Button button;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6,
                       imageView7, imageView8, imageView9, imageView10;
    String url ="http://192.168.0.16:3000";
    String title, link, src;
    //now to store the links we use array list
    ArrayList list = new ArrayList();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button_id);
        imageView1 = (ImageView) findViewById(R.id.imageView_id);
        imageView2 = (ImageView) findViewById(R.id.imageView);
        imageView3 = (ImageView) findViewById(R.id.imageView2);
        imageView4 = (ImageView) findViewById(R.id.imageView3);
        imageView5 = (ImageView) findViewById(R.id.imageView4);
        imageView6 = (ImageView) findViewById(R.id.imageView5);
        imageView7 = (ImageView) findViewById(R.id.imageView6);
        imageView8 = (ImageView) findViewById(R.id.imageView7);
        imageView9 = (ImageView) findViewById(R.id.imageView8);
        imageView10 = (ImageView) findViewById(R.id.imageView9);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Content content = new Content();
                content.execute();
            }
        });
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
            Picasso.with(getApplicationContext()).load(list.get(2).toString()).into(imageView3);
            Picasso.with(getApplicationContext()).load(list.get(3).toString()).into(imageView4);
            Picasso.with(getApplicationContext()).load(list.get(4).toString()).into(imageView5);
            Picasso.with(getApplicationContext()).load(list.get(5).toString()).into(imageView6);
            Picasso.with(getApplicationContext()).load(list.get(6).toString()).into(imageView7);
            Picasso.with(getApplicationContext()).load(list.get(7).toString()).into(imageView8);
            Picasso.with(getApplicationContext()).load(list.get(8).toString()).into(imageView9);
            Picasso.with(getApplicationContext()).load(list.get(9).toString()).into(imageView10);

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
