package com.example.cloudproject0716;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemViewAdapter extends ArrayAdapter<Item> {

    Context context;
    int resource;
    List<Item> objects;

    public ItemViewAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//      레이아웃을 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
//      ListView에 넣어줄 데이터를 얻어온다.
        Item item = objects.get(position);
//      View에 전개된 레이아웃에서 데이터를 넣어줄 위젯을 얻어온다.
        ImageView iv = view.findViewById(R.id.iv);
        TextView tv = view.findViewById(R.id.tv);
//      얻어온 위젯에 데이터를 넣어준다.
        iv.setImageResource(item.getIv());
        tv.setText(item.getTv());
//      ListView에 넣어줄 View를 리턴시킨다.
        return view;
    }
}
