package com.example.picassotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

// Picasso 외부 라이브러리
// Picasso는 외부로부터 이미지를 불러와야 할 경우 유용하게 사용되는 라이브러리이다.
// 간단한 코드 몇 줄로 이미지 로딩 & 디스크 캐싱, 변형 등을 가능하게 한다.
// Picasso 라이브러리는 TabTest 프로젝트에서 TabLayout을 추가하는 방식과 동일하게 추가하면 된다.
// 보다 더 자세한 사용 방법은 https://dwfox.tistory.com/33 블로그를 참조한다.

// Picasso 수동 설치
// Picasso 다운로드 링크 => https://square.github.io/picasso/ => picasso-2.5.2.jar
// Project 보기로 변경한 후 프로젝트 이름 => app => libs 폴더에 복사한 후 안드로이드 스튜디오를 재실행한다.

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);

        /*
        Picasso.with(getApplicationContext())  // Picasso를 이용해 불러온 이미지를 표시할 액티비티
                .load(R.drawable.naver) // Picasso를 이용해 불러올 이미지를 지정한다.
                .resize(1200, 400) // 이미지의 크기를 지정한다.
                .rotate(45) // 이미지를 회전시킨다.
                .into(iv);  // 이미지가 표시될 위젯
        */

//      load() 메소드에 이미지의 url 주소를 넣어서 해당 주소의 이미지를 읽어와 표시할 수 있다.
//      이미지가 표시되지 않으면 <uses-permission android:name="android.permission.INTERNET"/>를 AndroidManifest.xml 파일에 추가한다.
//      Picasso 2.7.x 버전부터는 with(getApplicationContext()) 대신 get() 메소드를 사용한다.
        Picasso.with(getApplicationContext())
                .load("https://www.google.co.kr/search?q=%EB%A9%94%EC%9D%B4%ED%94%8C+%EA%B4%91%EA%B3%A0&tbm=isch&source=iu&ictx=1&fir=DqGfr80J5GO5ZM%253A%252CqNl4usBSbTp8CM%252C_&vet=1&usg=AI4_-kQKVU9FDFHGh79GcZ-CAgnC5kbjgg&sa=X&ved=2ahUKEwiZ1r2C-ObnAhWp3mEKHSIZCEMQ9QEwAHoECAgQEQ#imgrc=DqGfr80J5GO5ZM:")
                .into(iv);

    }
}
