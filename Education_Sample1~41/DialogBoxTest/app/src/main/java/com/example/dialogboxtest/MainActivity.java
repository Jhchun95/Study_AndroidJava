package com.example.dialogboxtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewDialog(View view) {

//      현재 액티비티 위에 표시할 대화 상자(AlertDialog)를 만드는 Builder 객체를 생성한다.
//      현재 액티비티 위에 대화 상자를 표시할 것이므로 생성자의 인수로 this를 넘겨준다.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//      대화 상자를 설정한다.
        builder.setTitle("나는 대화 상자");       // 대화 상자 제목
        builder.setMessage("그래서요");           // 대화 상장 표시할 메시지
        builder.setIcon(R.mipmap.ic_launcher);  // 대화 상자에 표시할 아이콘

//      대화 상자에 버튼을 추가한다. => 대화 상자에 메시지만 표시하려고 할 경우 이부분의 코드를 생략하면 된다.
        int id = view.getId();
        switch (id) {

            case R.id.btn3:
//              setNeutralButton() : 대화 상자의 왼쪽 버튼을 만든다.
//              setNeutralButton("버튼 위에 표시할 문자열", 버튼이 클릭되면 실행할 리스너(이벤트))
                builder.setNeutralButton("무시", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "무시 눌렀냐?", Toast.LENGTH_SHORT).show();
                    }
                });

//              break 명령을 사용하면 버튼이 무조건 1개씩 밖에 대화 상자에 표시되지 않는다.
//              break;

            case R.id.btn2:
//              setNegativeButton() : setPositiveButton 왼쪽에 버튼을 만든다.
//              setNegativeButton("버튼 위에 표시할 문자열", 버튼이 클릭되면 실행할 리스너(이벤트))
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"취소 눌렀냐?", Toast.LENGTH_SHORT).show();
                    }
                });
//              break 명령을 사용하면 버튼이 무조건 1개씩 밖에 대화 상자에 표시되지 않는다.
//              break;

            case R.id.btn1:
//              setPositiveButton() : 대화 상자의 오른쪽 버튼을 만든다,
//              setPositiveButton("버튼 위에 표시할 문자열", 버튼이 클릭되면 실행할 리스너(이벤트))
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"확인 눌렀냐?", Toast.LENGTH_SHORT).show();
//                      Toast.makeText(getApplicationContext(),which + "", Toast.LENGTH_SHORT).show();

                    }
                });
                break;

        }

//      대화 상자를 설정한 내용으로 만들어 화면에 표시한다.
        builder.create().show();
    }
}
