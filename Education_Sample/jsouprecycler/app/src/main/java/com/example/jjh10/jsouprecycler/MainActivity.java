package com.example.jjh10.jsouprecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.MyRecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //리스트뷰랑 다른 점은 리사이클뷰는 리스트뷰처럼 할 수 있고, 그리드뷰처럼 사용할 수 있다.
        //그리고 레이아웃매니져가 존재한다.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // 리니어레이아웃이 리스트뷰랑 비슷하다고 생각
        recyclerView.setLayoutManager(layoutManager);

        List<CardItem> dataList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            dataList.add(new CardItem(i + "번째", "설명" + i));
        }

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰랑 리스트뷰의 차이점은 onClickListener (클릭 이벤트)가 없다는 것이다.

        adapter.setOnMyClickListener(this);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "" +position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSharedButtonClicked(int position) {
        Toast.makeText(this, "Share" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLearnMoreButtonClicked(int position) {
        Toast.makeText(this, "More" + position, Toast.LENGTH_SHORT).show();
    }
}
