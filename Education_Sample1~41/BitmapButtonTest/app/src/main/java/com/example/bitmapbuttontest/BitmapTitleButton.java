package com.example.bitmapbuttontest;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


// bitmap_button.xml 파일의 레이아웃을 전개해서 이미지 버튼을 만드는 클래스
// bitmap)button.xml 파일에서 사용한 레이아웃이 Relativelayout 이므로 RelativeLayout을 상속받아 만든다.
// RelativeLayout 클래스를 상속받으면 에러가 발생되는데 alt+Enter를 눌러 2번째 생성자를 구현한다.
public class BitmapTitleButton extends RelativeLayout {

    Button baseBtn;     // 배경 버튼
    Button leftBtn;     // "<" 버튼
    Button rightBtn;    // ">" 버튼

//  AttributeSet 인터페이스 객체를 넘겨받는 생성자를 만들어야 xml 파일의 레이아웃 속성을 자동으로 받아올 수 있다.

    public BitmapTitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

//      bitmap_button.xml 파일에서 작성한 레이아웃을 읽어 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bitmap_button, this, true);

//      위의 위젯을 얻어온다.
//      예전 안드로이드 스튜디오는 레이아웃이 전개되지 않은 상태에서 위젯을 얻어오는 것이 불가능했다. => 지금은 가능하다.
        baseBtn = findViewById(R.id.baseBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);

//      bitmap_button.xml 파일에서 작성한 레이아웃을 전개해서 생성한 버튼의 동작을 설정하기 위해 OnTouchLstener 인터페이스를 구현받은
//      내부 클래스를 만들어 동작을 설정한다.
//      레이아웃을 전개해서 생성한 버튼에 내부 클래스를 이용해 OnTouchListener를 걸어준다.
        MyTouchListener myTouchListener = new MyTouchListener();
        baseBtn.setOnTouchListener(myTouchListener);
        leftBtn.setOnTouchListener(myTouchListener);
        rightBtn.setOnTouchListener(myTouchListener);
    }

//  레이아웃을 전개해서 생성한 버튼의 이벤트를 발생시킬 내부 클래스를 OnTouchListener 인터페이스를 구현받아 만든다.
    private class MyTouchListener implements OnTouchListener {


//      alt + Enter를 눌러 onTouch() 메소드를 Override한 후 기능을 구현한다.
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                leftBtn.setBackgroundResource(R.drawable.arrow_left_clicked);
                rightBtn.setBackgroundResource(R.drawable.arrow_right_clicked);
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                leftBtn.setBackgroundResource(R.drawable.arrow_left);
                rightBtn.setBackgroundResource(R.drawable.arrow_right);
            }
//          return false;를 아래와 같이 수정한다.
            return true ;
        }
    }

}
