package com.example.listviewtest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChunjaViewAdapter extends ArrayAdapter<Chunja> {

//  Context, 전개할 레이아웃의 id, ListView에 출력할 데이터를 저장할 멤버를 선언한다.
    Context context;
    int resource;
    List<Chunja> objects;

    public ChunjaViewAdapter(Context context, int resource, List<Chunja> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

//  getView() 메소드를 Override해서 레이아웃을 전개하고 전개된 레이아웃에 데이터를 저장해서 리턴시킨다.

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//      레이아웃을 View에 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);

//      ListView에 넣어줄 데이터를 얻어온다.
        Chunja chunja = objects.get(position);

//      View에 전개된 레이아웃의 위젯에 데이터를 넣어준다.
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv3 = view.findViewById(R.id.tv3);
        tv1.setText(chunja.getH());
        tv2.setText(chunja.getK());
        tv3.setText(chunja.getC());


//      return super.getView(position, convertView, parent);
//      ListView에 넣어줄 view를 리턴시킨다.
        return view;
    }
}
