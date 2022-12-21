package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        webView = findViewById(R.id.webView);

//      WebView에 인터넷 사이트 또는 html 파일을 표시할 수 있도록 하기 위해서 setWebViewClient() 메소드로 WebViewClient 클래스 객체를 만들어
//      넣어줘야한다.

        webView.setWebViewClient(new WebViewClient()); // WebView를 사용할 수 있는 상태로 만든다.

//      assets 폴더는 안드로이드 애플리케이션에서 사용하는 안드로이드 이외의 파일을 저장하는 폴더이다.
//      assets 폴더 만들기 : app 우클릭 => New => Folder => Assets Folder
//      web에 관련된 파일은 asset 폴더 아래에 www라는 이름으로 디렉토리를 만들어 보관한다.

//      WebView에 html 파일을 나타낸다.
//      assets 폴더의 경로 표시는 file:///android_asset 이다. => assets 라고 안 쓰고 asset 라고 쓰는 것에 주의한다.
//      loadUrl() 메소드로 assets 폴더의 www 디렉토리에 저장된 sample.html 파일을 WebView에 불러온다.
        webView.loadUrl("file:///android_asset/www/sample.html");

//      안드로이드에서 html 파일에서 선언한 javascript 함수를 실행할 수 있게 한다.
//      WebSettings 클래스 객체를 이용해서 WebView 설정 정보를 읽어온다.
//      getSettings() : WebView의 설정 정보를 읽어온다.
        WebSettings webSettings = webView.getSettings();
//      setJavaScriptEnabled(true) 메소드를 실행해 WebView에서 javascript를 사용할 수 있게 한다.
        webSettings.setJavaScriptEnabled(true);

//      여기까지 코딩해서 곰돌이가 인상쓰지 않으면 아래 /* ~ */ 로 지정한 주석을 해제한다.
//      ======================================================================

        /*
//      addJavascriptInterface() 메소드로 실행할 javascript 함수를 실행하는 객체와 실행할 javascript 함수가 포함된 html 파일을 WebView에 추가시킨다.
//      addJavascriptInterface(javascript 함수를 실행하는 객체, "실행할 함수가 포함된 html 파일명")
//      webView.addJavascriptInterface(new JavaScriptMethod(), "sample");

//      ======================================================================

//      javascript 함수를 실행할 객체를 만든다.
//      안드로이드는 스레드를 Thread 클래스를 이용하지 않고 그냥 실행하면 어플리케이션이 멈춰버리는 현상이 발생될 수 있으므로
//      Handler 객체를 사용해 스레드를 실행하는 방법을 권장한다.
        final Handler handler = new Handler();

//      javascript 함수를 실행할 객체를 만든다.
//      다른 클래스에서는 사용할 일이 없는 현재 클래스에서만 사용하면 되는 객체이기 때문에 반드시 final 내부 클래스로 만든다.
//      Thread를 이용해 javascript 함수를 실행한다.
        final class JavaScriptMethod {
                public JavaScriptMethod() {     } // 생성자
//              javascript 함수를 실행하는 메소드를 만든다.
//              javascript 함수를 실행하는 메소드임을 안드로이드에게 알려주기 위해서 @android.webkit.JavascriptInterface 어노테이션을
//              붙여야 했었다. => 오레오부터는 안 붙여도 실행이 된다.
                @android.webkit.JavascriptInterface
                public void changeFace() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                          안드로이드에서 WebView에 올려진 html 파일의 javascript 함수를 호출한다.
                            webView.loadUrl("javascript:changeFace()");
                        }
                    });
                }
            }
        */
    }

    public void gotoURL(View view) {

//          WebView에 EditText에 입력한 웹 주소를 표시하기 위해 EditText에 입력한 문자열을 얻어온다.
//          String addr = et.getText() + "";
//          String addr = et.getText().toString();
            String addr = String.valueOf(et.getText());

//          WebView에 EditText에 입력한 웹 페이지의 주소 또는 html 문서를 불러온다.
//          인터넷이 안되서 웹페이지가 WebView에 나타나지 않을 경우 AndroidManifest.xml 파일에 아래와 같이 permission을 추가한다.
//          <uses-permission android:name="android.permission.INTERNET"/>
//          WebView에 나타나게 할 웹 사이트의 주소가 http로 시작하면 나타나지 않을 수 있다. => https로 변경하고 실행한다.
            webView.loadUrl(addr);
    }
}
