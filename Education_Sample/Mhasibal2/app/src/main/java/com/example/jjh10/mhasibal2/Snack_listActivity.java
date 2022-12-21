package com.example.jjh10.mhasibal2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Snack_listActivity extends Activity {
    ArrayList<Restraurant> rs = new ArrayList<Restraurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_list);
        setTitle("분 식");

        rs.add(new Restraurant("고봉민김밥인 범계점",R.drawable.sn_bggobong, "안양시 동안구 평촌대로217번길 45"));
        rs.add(new Restraurant("신전떡볶이 범계점",R.drawable.sn_bgsigeon, "안양시 동안구 평촌대로211번길 16"));
        rs.add(new Restraurant("진미떡볶이 범계점",R.drawable.sn_bgzinmi, "안양시 동안구 평촌대로223번길 42"));
        rs.add(new Restraurant("신포우리만두 범계점",R.drawable.sn_bgsinpo, "안양시 동안구 동안로 128"));

        rs.add(new Restraurant("빨봉분식 안양1번가",R.drawable.sn_a1thebalbong, "안양시 만안구 안양로292번길 8"));

        MyAdapter adapter = new MyAdapter(
                getApplicationContext(),


                R.layout.sn_list_detail,
                rs);

        ListView listView = (ListView)findViewById(R.id.listviewSn);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(



                        getApplicationContext(),
                        SnListDetail.class);

                intent.putExtra("title", rs.get(position).title);
                intent.putExtra("img", rs.get(position).img);
                intent.putExtra("address", rs.get(position).address);

                startActivity(intent);
            }
        });
    }
}

class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Restraurant> rs;
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, ArrayList<Restraurant> rs) {
        this.context = context;
        this.layout = layout;
        this.rs = rs;
        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivsn01);
        TextView tvName = (TextView)convertView.findViewById(R.id.tvsn01);
        TextView tvInfo = (TextView)convertView.findViewById(R.id.tvsn02);

        Restraurant m = rs.get(position);
        imageView.setImageResource(m.img);
        tvName.setText(m.title);
        tvInfo.setText(m.address);

        return convertView;
    }
}

class Restraurant {
    String title = ""; // 점포명
    int img; // 점포 사진
    String address = ""; // 점포 주소
    public Restraurant(String title, int img, String address) {
        super();
        this.title = title;
        this.img = img;
        this.address = address;
    }
    public Restraurant() {}
}