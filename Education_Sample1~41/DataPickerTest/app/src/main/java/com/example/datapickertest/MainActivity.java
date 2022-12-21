package com.example.datapickertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        datePicker = findViewById(R.id.datePicker);

//      init() 메소드로 DatePicker를 특정 날짜로 초기화 시킬 수 있다. => 기본 값은 컴퓨터 시스템의 현재 날짜
//      init(년, 월, 일, 날짜가 선택되면 실행될 리스너) => 월을 1작게 입력해야한다.
//      DatePicker에서 날짜를 선택하면 DateChangedListener가 실행된다.

        datePicker.init(2020, 3, 25, new DatePicker.OnDateChangedListener() {
            @Override
//          DatePicker에서 날짜를 선택하면(변경되면) onDateChanged()
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.format("%4d년 %2월 %2d일", year, monthOfYear + 1, dayOfMonth);
                Toast.makeText(getApplicationContext(),date, Toast.LENGTH_SHORT).show();
                tv1.setText(date);
            }
        });
    }


    public void selectDate(View view) {
//      DatePickerDialog 객체로 날짜를 선택할 수 있는 위젯을 액티비티에 띄울 수 있다.
//      new DatePickerDialog(this, DateSetListener, 년, 월, 일).show()
//      날짜를 선택하고 OK를 클릭하면 실행할 동작을 DateSetListener의 onDateSet() 메소드에서 구현한다.

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = String.format("%4d년 %2월 %2d일", year, month + 1, dayOfMonth);
                    Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                    tv1.setText(date);
            }
        }, 2020, 11, 25).show();
    }

    public void selectTime(View view) {
//      TimePickerDialog 객체로 시간을 선택할 수 있는 위젯을 액티비티에 띄울 수 있다.
//      new TimePickerDialog(this, DateSetListener, 시, 분, true or false).show()
//      마지막 인수에 true를 지정하면 24시각으로 false를 지정하면 12시각으로 시간이 표시된다.
//      시간을 선택하고 OK를 클릭하면 실행할 동작을 TimeSetListener의 onTimeSet() 메소드에서 구현한다.

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String date = String.format("%2d시 %2d분", hourOfDay, minute);
                Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                tv2.setText(date);
            }
        }, 15 ,25, false).show();

    }
}
