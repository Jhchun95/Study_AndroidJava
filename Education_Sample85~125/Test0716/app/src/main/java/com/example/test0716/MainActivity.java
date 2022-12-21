package com.example.test0716;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

}