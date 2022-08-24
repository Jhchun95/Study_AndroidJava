package com.example.jjh10.CheckPhotos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button go, right, left;
    EditText uri;
    WebView wv;
    View.OnClickListener cl;
    String weburi;


    class MyWeb extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        go = (Button) findViewById(R.id.go);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        uri = (EditText) findViewById(R.id.uri);
        wv = (WebView) findViewById(R.id.wv);
        wv.setWebViewClient(new MyWeb());

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.loadUrl("http://192.168.0.174:3000/");

        wv.setInitialScale(35); //35%
        wv.getSettings().setUseWideViewPort(true);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){

                    case R.id.go :
                        weburi = uri.getText().toString();
                        if(weburi.startsWith("http://192.168.0.174:3000/")) {
                            wv.loadUrl(weburi);
                        } else {
                            wv.loadUrl("http://192.168.0.174:3000/" + weburi); }
                        break;

                    case R.id.left :
                        wv.goBack();
                        break;

                    case R.id.right :
                        wv.goForward();
                        break;

                }
            }
        };
        go.setOnClickListener(cl);
        right.setOnClickListener(cl);
        left.setOnClickListener(cl);

    }


}
