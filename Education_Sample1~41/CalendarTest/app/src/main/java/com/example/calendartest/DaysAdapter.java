package com.example.calendartest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// 전개된 레이아웃에 채워진 데이터를 GridView에 나타내기 위한 adapter를 만드는 클래스
public class DaysAdapter extends BaseAdapter {

    Context context;
    ArrayList<Days> list = new ArrayList<>();

    public DaysAdapter(Context context, ArrayList<Days> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        DaysView daysView = new DaysView(context, list.get(position));
        return daysView;
    }
}
