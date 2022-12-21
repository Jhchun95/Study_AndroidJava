package com.example.listviewtest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChunjaView extends LinearLayout {

    TextView tv1, tv2, tv3;

//  생성자에서 chunja_view.xml 파일에서 정의한 레이아웃을 전개하고 데이터를 넣어준다.
//  생성자의 인수로 chunja_view.xml 파일에서 정의한 레이아웃이 전개된 후 넣어줄 데이터가 포함된 객체를 받아야 한다.
    public ChunjaView(Context context, Chunja chunja) {
        super(context);

//      Chunja view.xml 파일에서 정의한 레이아웃을 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chunja_view, this, true);

//      레이아웃을 전개한 후 레이아웃의 위젯을 얻어온다.
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

//      전개된 위젯에 Chunja 클래스 객체의 데이터를 넣어준다.
        tv1.setText(chunja.getH());
        tv2.setText(chunja.getK());
        tv3.setText(chunja.getC());
    }
}
