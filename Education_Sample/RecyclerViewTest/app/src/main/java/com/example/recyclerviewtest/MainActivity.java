package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.MyRecyclerViewClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        List<CardItem> dataList = new ArrayList<>();
        for(int i=0; i<10 ; i++) {
            dataList.add(new CardItem(i + "번째", "설명 " + i));
        }

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

//      클릭이벤트는 리스트뷰랑 다르게 onClickListener가 없어서 adapter에서 구현한다.

        adapter.setOnClickListener(this);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, ""+ position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareButtonClicked(int position) {
        Toast.makeText(this, "Share "+ position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLearnMoreButtonClicked(int position) {
        Toast.makeText(this, "More "+ position, Toast.LENGTH_SHORT).show();
    }
}
