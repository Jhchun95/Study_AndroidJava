package com.example.calendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    GridView gridView;
    int year, month;
    float downX , upX;

    String[] day = new String[42];
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Log.e("윤년 판별", MyCalendar.isLeapYear(2020) + "");
//      Log.e("마지막 날짜",)

        tv = findViewById(R.id.tv);
        gridView = findViewById(R.id.gridView);

//      컴퓨터 시스템의 년, 월을 얻어온다.
        Date date = new Date();
//        int year = date.getYear();
//        int month = date.getMonth() + 1;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;

//      tv.setText(year + "년" + month + "월");
        tv.setText(String.format("%4d년 %02월", year, month));

//      날짜를 채우기 전에 전달의 날짜를 지우기 위해서 배열을 모두 공백으로 초기화시킨다.
        for(int i=0; i < days.length; i++){
            days[i] = "";
        }

//      1월이 출력될 위치를 맞추기 위해서 1일의 요일만큼 반복하여 배열에 공백을 채워준다.
        for(int i=0; i < MyCalendar.weekDay(year, month, 1); i++){
            days[index++] = "";
        }

//      1월부터 달력을 출력할 달의 마지막 날짜까지 반복하며
        for(int i=1; i < MyCalendar.lastday(year, month); i++) {
            days[i] = i + "";
        }

//      GridView에 넣어줄 날짜를 Adapter를 넣어준다.
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, days);

//      GridView에 날짜가 지정된 adapter를 넣어준다.
        gridView.setAdapter(adapter);

    }

    private void changeDate() {
//      날짜를 채우기 전에

    }


//  달력을 전달 날짜로 변경하는 메소드
    public void prevMonth(View view) {
//      month에서 1을 빼면 된다.
        if(--month == 0 ) {
            year--;
            month = 12;
        }
        tv.setText(String.format("%4d년 %02d월", year, month));
//      달력에 출력할 달이 변경되었으므로 날짜를 다시 채워준다.
        changeDate();
//      adapter에 넣어준 내용이 갱신되면 반드시 notifyDataSetChanged() 메소드를 실행해 adapter의 내용을 갱신해야 한다.
        adapter.notifyDataSetChanged();
    }

//  달력을 전년 날짜로 변경하는 메소드
    public void prevYear(View view) {
//      year에 1을 빼면 된다.

    }

//  달력을 내년 날짜로 변경하는 메소드
    public void nextYear(View view) {
//      year에 1을 더하면 된다.
        year++;
        tv.setText(String.format("%4d년 %02d월", year, month));
        changeDate();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();

            if(downX < upX) {
                if(--month == 0) {
                    year --;
                    month = 12;
                }
            } else if(downX > upX ) {
                if(++month == 13) {
                    year++;
                    month = 1;
                }
            }
        }

        return super.onTouchEvent(event);
    }

}
