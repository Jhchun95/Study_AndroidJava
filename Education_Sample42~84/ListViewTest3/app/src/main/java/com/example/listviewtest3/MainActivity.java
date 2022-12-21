package com.example.listviewtest3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

// 텍스트 파일, 음악 파일, 동영상 파일 등 이미지를 제외한 기타 원본 리소스들은 res 폴더 아래에 raw 폴더를 만들어 관리한다.

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

//      raw 폴더의 텍스트 파일을 읽어 저장할 InputStream 클래스 객체를 선언한다.
        InputStream inputStream = getResources().openRawResource(R.raw.chunja2);
        Chunja.readChunja(inputStream);
        final ArrayList<Chunja> list = Chunja.readChunja(inputStream);

//      ArrayList에 저장된 데이터를 ListView에 출력할 형태로 데이터를 편집해서 ArrayList에 저장시킨다.
        ArrayList<String> listData = new ArrayList<>();
        /*
//      chunja.txt 파일의 내용을 출력할 때
        for(Chunja chunja : list) {
            String str = chunja.getIndex() + "." + chunja.getH() + "(" + chunja.getK() + ") >>> " + chunja.getC();
            listData.add(str);
            Log.e("chunja", str);
        }
        */
        for(Chunja chunja : list) {
            String str = chunja.getIndex() + "." + chunja.getH() + "(" + chunja.getK() + ") >>> " + chunja.getP();
            listData.add(str);
            Log.e("chunja2", str);
        }

//      ListView에 출력해야 할 데이터가 문자열 한 건이므로 ArrayAdapter를 사용해서 Adapter를 만든다.
//      ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listData);

//      Adapter를 ListView에 등록한다.
//      listView.setAdapter(adapter);

//      listView를 클릭했을 때 실행할 동작이 있다면 ItemClickListener를 걸어준다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position).getP(), Toast.LENGTH_SHORT).show();
            }
        });

//      chunja_view.xml 에서 정의한 레이아웃을 전개해서 ListView에 넣어줄 데이터가 저장된 Adpater를 ListView에 넣어준다.
        ChunjaAdapter adapter = new ChunjaAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);



    }

//  전개하는 놈 만들고, 코딩하는 놈 만들고, adpater 만들고
}
