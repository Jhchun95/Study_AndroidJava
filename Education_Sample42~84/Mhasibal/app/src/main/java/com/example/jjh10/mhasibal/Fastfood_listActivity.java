package com.example.jjh10.mhasibal;

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

/**
 * Created by 문경태 on 2018-05-25.
 */

public class Fastfood_listActivity extends Activity {
    ArrayList<Restraurant> rs = new ArrayList<Restraurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fastfood_list);
        setTitle("패스트푸드");

        rs.add(new Restraurant("맘스터치 성결대점", R.drawable.ff_ssmomstouch, "안양시 만안구 성결대학로 38"));
        rs.add(new Restraurant("요거프레소 성결대점", R.drawable.ff_ssyogur, "안양시 만안구 성결대학로 36"));
        rs.add(new Restraurant("피자스쿨 성결대점", R.drawable.ff_sspizzaschool, "안양시 만안구 성결대학로 28"));
        rs.add(new Restraurant("롯데리아 만안구청점", R.drawable.ff_sslotte, "안양시 만안구 안양로 122 만안빌딩"));

        rs.add(new Restraurant("맘스터치 범계점", R.drawable.ff_momstouch, "안양시 동안구 평촌대로223번길 65"));
        rs.add(new Restraurant("모스버거 범계점", R.drawable.ff_mosburger, "안양시 동안구 평촌대로223번길 41"));
        rs.add(new Restraurant("미스터피자 범계점", R.drawable.ff_mrpizza, "안양시 동안구 평촌대로223번길 55"));
        rs.add(new Restraurant("오리지널시카고피자 범계점", R.drawable.ff_originalcikagopizza, "안양시 동안구 시민대로 214"));

        rs.add(new Restraurant("맘스터치 안양1번가", R.drawable.ff_a1momstouch, "안양시 만안구 안양로304번길 4"));
        rs.add(new Restraurant("미스터피자 안양1번가", R.drawable.ff_a1mrpizza, "안양시 만안구 안양로304번길 13"));
        rs.add(new Restraurant("맥도날드 안양1번가", R.drawable.ff_a1macdonald, "안양시 만안구 안양로292번길 22"));
        rs.add(new Restraurant("KFC 안양1번가", R.drawable.ff_a1kfc, "안양시 만안구 안양로292번길 7"));

        MyAdapter adapter = new MyAdapter(
                getApplicationContext(),
                R.layout.ff_row,
                rs);

        ListView listView = (ListView)findViewById(R.id.listviewFf);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        FfListDetail.class);

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
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivff01);
        TextView tvName = (TextView)convertView.findViewById(R.id.tvff01);
        TextView tvInfo = (TextView)convertView.findViewById(R.id.tvff02);

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