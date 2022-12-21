package com.example.jjh10.mhasibal3;

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

public class Bar_listActivity extends Activity {
    ArrayList<Restraurant> rs = new ArrayList<Restraurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_list);
        setTitle("주 점");

        rs.add(new Restraurant("하쿠하쿠 안양1번가",R.drawable.bar_a1hakuhaku, "안양시 만안구 장내로143번길 12"));
        rs.add(new Restraurant("지스타에일 안양1번가",R.drawable.bar_a1gstaroil, "안양시 만안구 장내로143번길 30"));
        rs.add(new Restraurant("아지트 안양1번가",R.drawable.bar_a1agit, "안양시 만안구 장내로139번길 58"));
        rs.add(new Restraurant("임창정의 소주한잔",R.drawable.bar_a1sojuhanzan, "안양시 만안구 안양동 674-149"));
        rs.add(new Restraurant("알콜트리 안양1번가",R.drawable.bar_a1alcholtree, "안양시 만안구 만안로 221"));
        rs.add(new Restraurant("삼구포차 안양1번가",R.drawable.bar_a139pocha, "안양시 만안구 안양로292번길 13"));

        rs.add(new Restraurant("교동전선생 범계점",R.drawable.bar_bggyodong, "안양시 동안구 평촌대로223번길 19"));
        rs.add(new Restraurant("포차어게인 범계점",R.drawable.bar_bgpochaagain, "안양시 동안구 평촌대로223번길 31"));
        rs.add(new Restraurant("에잇피스 범계점",R.drawable.bar_bgeightpiece, "안양시 동안구 평촌대로223번길 49"));
        rs.add(new Restraurant("사니스펍앤그릴 범계점",R.drawable.bar_bgpopandgril, "안양시 동안구 평촌대로223번길 28"));

        rs.add(new Restraurant("술도가전집 만안구청점",R.drawable.bar_sssouldoga, "안양시 만안구 성결대학로 12"));
        rs.add(new Restraurant("옥이네칼국수 철이네포차",R.drawable.bar_ssoakpocha, "안양시 만안구 안양로 137"));
        rs.add(new Restraurant("종로빈대떡 성결대점",R.drawable.bar_ssjongro, "안양시 만안구 만안로 25"));
        rs.add(new Restraurant("자갈치회포차 성결대점",R.drawable.bar_ssjagalchi, "안양시 만안구 성결대학로 31"));



        MyAdapter adapter = new MyAdapter(
                getApplicationContext(),
                R.layout.bar_row,
                rs);

        ListView listView = (ListView)findViewById(R.id.listviewBar);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        BarListDetail.class);

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
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivbar01);
        TextView tvName = (TextView)convertView.findViewById(R.id.tvbar01);
        TextView tvInfo = (TextView)convertView.findViewById(R.id.tvbar02);

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