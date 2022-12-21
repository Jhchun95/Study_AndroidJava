package com.example.listviewtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> list = new ArrayList<>();
//  HashMap은 데이터가 2개이다. 데이터를 만들어준다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

//      데이터를 만든다.
        for(int i=0; i<30; i++) {
            HashMap<String,String> hmap = new HashMap<>();
            hmap.put("name", "사람" + (i+1));
            hmap.put("age", new Random().nextInt(100) + 1 + "세");
            list.add(hmap);
        }

//      한 개짜리 데이터를 ListView에 추가할 때는 안드로이드에서 제공하는 ArrayAdapter를 사용했지만 두 개짜리 데이터를 ListView에 추가할 때는
//      SimpleAdapter를 사용한다.
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                list,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {"name", "age"},
                new int[] {android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);
    }

}
