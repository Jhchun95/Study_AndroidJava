package com.example.listviewtest3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// BaseAdapter 클래스를 상속받아 chunja_view.xml 파일에서 정의한 레이아웃을 ChunjaView 클래스 객체를 만들고(전개하고) 데이터를 넣어준 것을
// ListView에 뿌려줄 Adapter를 만드는 클래스

public class ChunjaAdapter extends BaseAdapter {

//  Context와 ListView에 넣어줄 데이터가 저장될 객체를 멤버로 선언한다.
    Context context;
    ArrayList<Chunja> list;

    public ChunjaAdapter(Context context, ArrayList<Chunja> list) {
        this.context = context;
        this.list = list;
    }

//  여기부터
    @Override
    public int getCount() {
//      return 0;
//      ListView에 넣어줄 데이터의 개수를 리턴하게 수정한다.
        return list.size();
    }

    @Override
    public Object getItem(int position) {
//      return null;
//      ListView에 넣어줄 데이터를 얻어와서 리턴하게 수정한다.
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
//      return 0;
//      ListView에 넣어줄 데이터의 위치를 리턴하게 수정한다.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//      return null;
//      ListView에 넣을 수 있도록 레이아웃을 확장한 후 데이터를 넣어준 객체를 만들어 리턴시킨다.
        ChunjaView chunjaView = new ChunjaView(context, list.get(position));
        return chunjaView;
    }
//  여기까지 alt + enter를 눌러 상속받은 추상 클래스 BaseAdapter의 추상 메소드를 Override 시킨다.

}
