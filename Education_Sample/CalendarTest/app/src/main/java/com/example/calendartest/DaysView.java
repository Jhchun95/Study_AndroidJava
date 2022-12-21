package com.example.calendartest;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

// days.xml 파일에서 정의한 레이아웃을 전개하고 레이아웃의 위젯에 날짜를 채워주는 클래스
// days.xml 파일에서 정의한 레이아웃의 메인 레이아웃이 LinearLayout 이므로 LinearLayout를 상속받아 만든다.
public class DaysView extends LinearLayout {

    TextView tv2;

//  레이아웃이 전개될 액티비티와 전개된 레이아웃의 위젯에 채울 데이터를 받는 생성자를 만든다.
    public DaysView(Context context, Days days) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.days, this, true);
        tv2 = findViewById(R.id.tv2);

        tv2.setText(Html.fromHtml(days.getDay()));
    }


}
