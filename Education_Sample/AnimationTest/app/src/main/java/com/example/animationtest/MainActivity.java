package com.example.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
//  애니메이션 실행에 사용할
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

//      애니메이션을 구현하려면 res폴더 아래 반드시 anim 이라는 이름의 폴더를 만들고 anim 폴더 내부에 애니메이션을 설정하는 xml 파일을
//      만들어 구현한다.
//      anim 폴더 만들기 : res 폴더 우클릭 => New => Directory
//      anim 폴더에 애니메이션 설정 xml 파일 만들기 : anim 폴더 우클릭 => New => Animation resource file
//
//      애니메이션을 실행할 준비를 한다.
//      AnimationUtils 클래스의 loadAnimation() 메소드로 실행할 애니메이션 설정 파일을 읽어들인다.
//      loadAnimation(this, R.anim.애니메이션 설정 xml 파일 이름)

        animation = AnimationUtils.loadAnimation(this, R.anim.flow);
    }

    public void startAnim(View view) {
//      애니메이션이 실행될 위젯에서 startAnimation() 메소드로 애니메이션을 실행한다.
//      startAnimation(애니메이션 설정 정보가 저장된 anim 폴더의 xml 파일을 loadAnimation() 메소드로 읽어들인 Animation 클래스 객체)
        tv.startAnimation(animation);
    }
}
