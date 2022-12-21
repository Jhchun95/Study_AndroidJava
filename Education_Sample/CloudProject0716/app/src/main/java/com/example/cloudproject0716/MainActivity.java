package com.example.cloudproject0716;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Item> list = new ArrayList<>();       // ListView에 넣어줄 데이터를 기억하는 ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
//      ListView에 넣어줄 데이터를 만든다.
        for(int i=0 ; i<9 ; i++) {
            Item item = new Item();
            item.setIv(R.drawable.apples_001 + i);
            item.setTv("사과" + (i + 1));
            list.add(item);
        }

//      ItemAdapter adapter = new ItemAdapter(getApplicationContext(), list);
        ItemViewAdapter adapter = new ItemViewAdapter(getApplicationContext(), R.layout.item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), i + 1 + " 번째 사과", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

