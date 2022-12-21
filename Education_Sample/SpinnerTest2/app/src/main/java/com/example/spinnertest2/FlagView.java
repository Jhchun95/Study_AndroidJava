package com.example.spinnertest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// flag.xml 파일에서 정의한 레이아웃을 전개하고 위젯에 데이터를 넣어주는 클래스
// flag.xml 파일에서 정의한 레이아웃의 메인 레이아웃이 LinearLayout이므로 LinearLayout을 상속받아 만든다.
public class FlagView extends LinearLayout {

    ImageView iv2;
    TextView tv;

//  Spinner를 표시할 액티비티와 Spinner에 표시할 데이터가 저장된 객체를 넘겨받는 생성자를 만든다.
    public FlagView(Context context, Flag flag) {
        super(context);
//      flag.xml 파일에서 정의한 레이아웃을 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.flag, this, true);
//      전개된 레이아웃의 위젯을 얻어온다.
        iv2 = findViewById(R.id.iv2);
        tv = findViewById(R.id.tv);
//      얻어온 위젯에 데이터를 넣어준다.
        iv2.setImageResource(flag.getFlagId());
        tv.setText(flag.getName());
    }

}
