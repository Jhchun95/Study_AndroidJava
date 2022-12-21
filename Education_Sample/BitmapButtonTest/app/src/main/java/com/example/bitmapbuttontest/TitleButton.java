package com.example.bitmapbuttontest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

// 1개의 위젯으로 구성되는 버튼은 androidx.appcompat.widget 패키지의 AppCompatButton 클래스를 상속받아서 만든다.
// AppCompatButton 클래스를 상속받으면 에러가 발생되는데 alt + Enter를 눌러 2번째 생성자를 구현한다.
public class TitleButton extends AppCompatButton {

    public TitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
//      이미지에서 늘어날 수 있는 영역(stretchable)과 원본 크기대로 표시되야 할 영역을 구분하여 이미지가 그려질 영역의 크기가
//      늘어나거나 줄어들더라도 원본 이미지의 형태를 유지하도록 만들어진 이미지를 나인 패치(9-Patch) 이미지라 부른다.
//      생성자에서 setBackgroundResource() 메소드로 버튼의 배경으로 사용할 9-Patch 이미지를 지정한다.
//      안드로이드 9-Patch 이미지에 관련된 자세한 사항은 http://recipes4dev.tistory.com/131를 참조한다.
//      9-Patch 이미지 제작 방법은 http://itpangpang.xyz/169를 참조한다.
//      9-Patch 이미지 제작 링크

//      생성자에서 setBackgroundResource() 메소드로 버튼의 배경으로 사용할 9-patch 이미지를 지정한다.
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
    }

//  1개의 위젯으로 구성되는 버튼은 onTouchEvent() 메소드를 Override 해서 버튼이 터치되면 실행할 기능을 정의한다.
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            setBackgroundResource(R.drawable.title_bitmap_button_clicked);

//           버튼이 터치되면 실행할 기능을 정의한다.
//            Toast.makeText(getContext().getApplicationContext(), "TitleButton동작", Toast.LENGTH_SHORT).show();

        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            setBackgroundResource(R.drawable.title_bitmap_button_normal);
        }
        return super.onTouchEvent(event);
    }






}
