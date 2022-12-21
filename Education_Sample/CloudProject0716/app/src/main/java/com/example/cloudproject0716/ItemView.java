package com.example.cloudproject0716;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//  item.xml 파일에서 정의한 레이아웃을 전개하고 데이터를 넣어주는 클래스
//  item.xml의 메인 레이아웃이 LinearLayout 이므로 LinearLayout을 상속받아 만든다.
public class ItemView extends LinearLayout {

    ImageView iv;
    TextView tv;

//  ListView를 출력할 액티비티와 ListView에 넣어줄 데이터가 저장된 객체를 넘겨받는 생성자
    public ItemView(Context context, Item item) {
        super(context);
//      레이아웃을 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item, this, true);
//      전개된 레이아웃의 위젯에 데이터를 넣어준다.
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        iv.setImageResource(item.getIv());
        tv.setText(item.getTv());
    }
}
