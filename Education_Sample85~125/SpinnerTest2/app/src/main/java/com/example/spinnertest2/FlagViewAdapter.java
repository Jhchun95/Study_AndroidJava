package com.example.spinnertest2;


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

// flag.xml 파일에서 정의한 레이아웃과 전개한 레이아웃에 넣어줄 데이터를 넘겨받아 레이아웃을 전개하고 데이터를 넣어준 View를 리턴하는
// Adapter 클래스
// ArrayAdapter 클래스를 상속받고 제네릭에 데이터가 저장된 객체를 적어줘야한다. => ArrayAdapter<Flag>
public class FlagViewAdapter extends ArrayAdapter<Flag> {

    Context context;        // 레이아웃이 전개될 액티비티
    int resource;           // 전개할 레이아웃
    List<Flag> objects;     // 전개된 레이아웃에 넣어줄 데이터

    public FlagViewAdapter(@NonNull Context context, int resource, @NonNull List<Flag> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

//  getView() 메소드를 Override 해서 Spinner가 확장되지 않았을 때 액티비티에 표시할 내용을 만든다.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//      flag.xml 파일에서 정의한 레이아웃을 View에 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
//      Spinner에 넣어줄 데이터를 얻어온다.
        Flag flag = objects.get(position);
//      전개된 레이아웃에서 데이터를 넣어줄 위젯을 View에서 얻어온다.
        ImageView iv2 = view.findViewById(R.id.iv2);
        TextView tv = view.findViewById(R.id.tv);
//      얻어온 위젯에 데이터를 넣어준다.
        iv2.setImageResource(flag.getFlagId());
        tv.setText(flag.getName());
//      레이아웃을 전개하고 데이터를 채워준 View를 리턴시킨다.
        return view;
    }

//  getDropDownView() 메소드를 Override 해서 Spinner가 확장되었을 때 액티비티에 표시될 내용을 만든다.
//  getDropDownView() 메소드를 Override 하지 않으면 Spinner가 확장될 때 오류가 발생되며 앱이 강제로 종료된다.
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //      flag.xml 파일에서 정의한 레이아웃을 View에 전개한다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        Flag flag = objects.get(position);
        ImageView iv2 = view.findViewById(R.id.iv2);
        TextView tv = view.findViewById(R.id.tv);
        iv2.setImageResource(flag.getFlagId());
        tv.setText(flag.getName());
        return view;
    }
}
