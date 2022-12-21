package com.example.jjh10.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {
    public static final String testContent = "<html><body>" +
            "<b>HTML 문서를 웹뷰로 출력합니다.</b>" +
            "<font size='+1'>크게</font>" +
            "<img src=\"http://developer.android.com/assets/images/home/honeycomb-android.png\"/>" +
            "</body></html>";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        WebView mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.loadData(testContent, "text/html", "UTF-8");
        //mWebView.loadDataWithBaseURL("http", testContent, "text/html", "UTF-8", null);
    }
}