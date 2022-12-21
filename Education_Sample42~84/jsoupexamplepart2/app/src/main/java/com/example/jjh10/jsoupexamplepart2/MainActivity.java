package com.example.jjh10.jsoupexamplepart2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    String url ="https://developer.android.com/";
    String title, link;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

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
            textView.setText(link);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(url).get();
                //gives the title of the page
                title = doc.title();
                //gives the every links on that page
                Elements links = doc.select("a[href]");
                link=links.text().toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}