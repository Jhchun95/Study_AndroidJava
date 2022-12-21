package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] datas = { "이상해씨", "이상해풀", "이상해꽃" ,"파이리", "리자드" , "리자몽" ,"꼬부기", "어니부기", "거북왕"
                        , "캐터피", "단데기", "버터플", "뿔충이", "딱충이", "독침붕"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);

//      ListView에 데이터를 넣어주려면 반드시 Adapter를 이용해서 데이터를 할당해야 한다.

//      배열이나 ArrayList와 같은 List 타입의 데이터(간단한 데이터, 1차원 데이터)는 안드로이드에서 제공하는 Adapter를 사용한다.
//      ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, datas);
//      ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, Arrays.asList(datas));

/*
//      배열이나 ArrayList에 저장된 데이터와 라디오 버튼을 ListView에 표시한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, datas);
//      위와 같이 코딩하면 ListView에 라디오 버튼이 표시되지만 선택되지 않기 때문에 아래와 같이 ChoiceMode를 지정한다.
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 단일 선택 모드
*/
/*
//      배열이나 ArrayList에 저장된 데이터와 체크 박스를 ListView에 표시한다.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,datas);
//      위와 같이 코딩하면 ListView에 라디오 버튼이 표시되지만 선택되지 않기 때문에 아래와 같이 ChoiceMode를 지정한다.
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_checked,datas);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


//      ListView에 adapter를 넣어준다.
        listView.setAdapter(adapter);

//      ListView를 클릭했을 때 실행해야할 동작이 있다면 OnItemClickListener를 걸어준다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + 1 + "현재 데이터 선택"+ datas[position] + "선택",
                        Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + 1 + "현재 데이터를 길게 누르셨네요",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
