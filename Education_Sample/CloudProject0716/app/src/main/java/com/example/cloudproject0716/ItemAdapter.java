package com.example.cloudproject0716;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    Context context;                                // ListView가 출력될 액티비니
    ArrayList<Item> list = new ArrayList<>();       // ListView에 출력할 데이터

    public ItemAdapter() { }
    public ItemAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemView itemView = new ItemView(context, list.get(i));
        return itemView;
    }
}
