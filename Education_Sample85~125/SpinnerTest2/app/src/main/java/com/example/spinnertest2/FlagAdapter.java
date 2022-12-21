package com.example.spinnertest2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// flag.xml 파일에서 정의한 레이아웃을 전개하고 데이터를 채워넣은 FlagView 클래스를 view로 만들어 리턴하는 클래스
public class FlagAdapter extends BaseAdapter {

    Context context;                            // Spinner가 표시될 액티비티
    ArrayList<Flag> list = new ArrayList<>();   // Spinner에 넣어줄 데이터

    public FlagAdapter(Context context, ArrayList<Flag> list) {
        this.context = context;
        this.list = list;
    }

//  여기부터
    @Override
    public int getCount() { // Spinner에 넣어줄 데이터의 개수를 리턴하는 메소드
        return list.size();
    }

    @Override
    public Object getItem(int position) { // Spinner에 넣어줄 데이터를 리턴하는 메소드
        return list.get(position);
    }

    @Override
    public long getItemId(int position) { // Spinner에 넣어줄 데이터의 위치(인덱스)를 리턴하는 메소드
        return position;
    }

    @Override
//  Spinner에 넣어줄 레이아웃을 전개하고 데이터를 넣어주는 클래스 객체를 만들어 리턴시키는 메소드
    public View getView(int position, View convertView, ViewGroup parent) {
        FlagView flagView = new FlagView(context, list.get(position));
        return flagView;
    }
//  여기까지 BaseAdapter 클래스를 상속받고 alt + Enter를 눌러 자동으로 입력한 추상 메소드

}
