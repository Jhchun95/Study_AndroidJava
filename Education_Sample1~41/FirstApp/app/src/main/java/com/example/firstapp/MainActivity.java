package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//  류종우 강사님
//  레이아웃에서 설정한 id를 지정한 위젯을 기억할 객체(변수)를 선언한다.
    TextView tv, tv2;
    Button btn, btn2;

    @Override
    // onCreate 안드로이드 화면이 생성될때 가장 먼저 실행되는 메소드이다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 해당 레이아웃에서 화면을 표시하라
        setContentView(R.layout.activity_main);

//      레이아웃 설정시 사용하는 android:autoLink를 사용하지 않고 액티비티에서 TextView에 자동 연결 설정하기
//      Linkify.addLinks(TextView 객체, Linkify.연결 방식)
//      Linkify.WEB_URLS => 인터넷
//      Linkify.EMAIL_ADDRESSES => 이메일

        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);

        Linkify.addLinks(tv, Linkify.WEB_URLS);
//      Linkify.addLinks(tv2, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(tv2, Linkify.PHONE_NUMBERS);



//      레이아웃에서 설정한 id에 해당되는 위젯을 찾아서 변수에 저장시킨다.
//      안드로이드 스튜디오 3.0 버전 전에는 아래와 같이 반드시 형변환을 시켜서 변수에 저장해야 했었다.
//      btn = (Button) findViewById(R.id.btn);
//      지금은 형변환 시키지 않아도 상관없다.


        //정적 변수 staic final , 추상메소드를 인터페이스라고 부르는데 상속되어 있으면 재정되어서 사용되어야 하는데 입력하면 알아서 중간에 생긴다.
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast는 스크린에 간단한 메세지를 출력한다.
//              makeText(Toast가 표시될 액티비티, 메시지, Toast 메세지 표시시간 ).show
                Toast.makeText(getApplicationContext(), "그렇습니다", Toast.LENGTH_SHORT).show();
            }
        });

        btn2 = findViewById(R.id.btn2);

    }

    public void callPhone(View view) {

        Toast.makeText(getApplicationContext(), "전화 걸려고?", Toast.LENGTH_SHORT).show();
//      안드로이드에서 Intent는 무엇인가를 하고자 하는 행위를 의미하여 Intent를 통해서 애플리케이션의 구성 요소들 간에
//      데이터를 전달하거나 실행하기 위한 기능이 무엇인지 안드로이드 시스템에게 알려줄 수 있다.
//      Intent.ACTION_VIEW => 새 액티비티를 띄운다.
//      전화걸기
//      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:031-1234-5678"));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:031-1234-5678"));
//      startActivity() 메소드는 Intent 객체의 정보를 바탕으로 새 액티비티를 실행한다.
        startActivity(intent);
    }

    public void gotoNaver(View view) {
        Toast.makeText(getApplicationContext(), "네이버로 가려고??", Toast.LENGTH_SHORT).show();
//      웹 페이지 띄위기
//        String addr = "http://m.naver.com";
//        Uri uri = Uri.parse(addr);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//합체     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));

//      구글맵 띄위기
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:46.8131873,8.22421005"));
        startActivity(intent);

    }
}
