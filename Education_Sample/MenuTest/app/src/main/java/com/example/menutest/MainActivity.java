package com.example.menutest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    LinearLayout mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        mainActivity = findViewById(R.id.mainActivity);

//      onCreate() 메소드에서 버튼을 짧게 누르면 나타나는 메뉴인 popup 메뉴 액티비티 화면에 표시하는 코드를 작성한다.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              popup 메뉴를 액티비티 화면에 표시하기 위해서 PopupMenu 클래스 객체를 생성한다.
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
//              popup 메뉴를 화면에 전개하기 위해서 MenuInflater(메뉴 전개자) 클래스 객체를 생성한다.
                MenuInflater menuInflater = popupMenu.getMenuInflater();
//              메뉴로 표시할 내용이 작성된 xml 파일을 읽어서 전개한다.
//              메뉴를 정의하는 xml 파일은 res 폴더 아래에 menu라는 이름의 폴더를 만들고 그 안에 작성한다.
                menuInflater.inflate(R.menu.menu, popupMenu.getMenu());
//              popup 메뉴를 액티비티 화면에 표시한다.
                popupMenu.show();

//              popup 메뉴가 클릭되면 실행할 동작을 지정하기 위해서 MenuItemClickListener를 걸어준다.
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                      어떤 메뉴가 클릭되었나 알아내야 하기 때문에 MenuItem 인터페이스 객체 item으로 넘어온 클릭한 메뉴의 id를
//                      얻어온다.
                        int itemId = item.getItemId();
                        Toast.makeText(getApplicationContext(), itemId+ "", Toast.LENGTH_SHORT).show();
//                      switch 명령을 이용해서 넘어온 id에 따라 실행할 동작을 지정한다.
                        switch (itemId)  {
                            case R.id.menu1 : // 오늘도 메뉴
                                Toast.makeText(getApplicationContext(), "오늘도 메뉴", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu2 : // 오늘도 메뉴
                                Toast.makeText(getApplicationContext(), "공부하느라 메뉴", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu3 : // 오늘도 메뉴
                                Toast.makeText(getApplicationContext(), "힘들었지요 메뉴", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

            }
        });

//      위젯을 길게 누르고 있으면 나타나는 메뉴를 컨텍스트 메뉴라 부른다.
//      onCreate() 메소드에서 registerForContextMenu() 메소드로 위젯을 길게 누르면 나타나는 메뉴인 컨텍스트 메뉴를 등록한다.
        registerForContextMenu(btn2);

//      onCreateContextMenu() 메소드를 Override 해서 컨텍스트 메뉴를 만든다.
//      onContextitem


    } // onCreate()

//  컨텍스트 메뉴를 만든다.

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        /*
//      xml 파일에서 정의한 메뉴의 내용을 읽어서 표시한다.
//      2개 이상의 위젯에서 컨텍스트 메뉴를 사용해야 한다면 인수로 받은 View 클래스 객체에서 컨텍스트 메뉴가 실행된 위젯의 id를 얻어와서
//      컨텍스트 메뉴가 실행될 위젯을 구분해주면 된다.
        int contextItmeId = v.getId();
        switch (contextItmeId) {
            case R.id.btn2 :
//              getMenuInflater() 메소드로 xml 파일에서 정의한 메뉴를 읽어와 컨텍스트 메뉴로 전개한다.
                getMenuInflater().inflate(R.menu.menu, menu);
                break;

        }
        */
//  xml 파일을 사용하지 않고 프로그램에서 직접 메뉴를 만들어 표시한다.
//  xml 파일을 사용하지 않고 메뉴를 만들려면 onCreateContextMenu() 메소드의 첫 번째 인수인 ContextMenu 인터페이스 객체를
//  사용해서 만든다.

        menu.setHeaderTitle("컨텍스트 메뉴"); // 컨텍스트 메뉴의 제목을 지정한다.

//      서브 메뉴를 만들어 사용하려면 SubMenu 인터페이스 객체를 사용해서 만든다.
        SubMenu subMenu = menu.addSubMenu("메인메뉴");

//      add(groupId, itemId, order, title) 메소드로 메뉴 항목을 추가한다.
//      groupId : 그룹 id를 지정하면 메뉴에서 사용할 수 있는 그룹 옵션을 사용할 때 쓰인다.
//      itemId : 메뉴 각각의 메뉴 항목 id를 지정한다.
//      order : 메뉴 항목의 순서를 지정한다.
//      title : 메뉴 내용을 지정한다.
        subMenu.add(1, 1, Menu.NONE, "서브메뉴1");
        subMenu.add(1, 2, Menu.NONE, "서브메뉴2");
        subMenu.add(1, 3, Menu.NONE, "서브메뉴3");

        SubMenu subMenu2 = menu.addSubMenu("공부");
        subMenu2.add(1, 4, Menu.NONE, "Android").setIcon(R.mipmap.ic_launcher);
        subMenu2.add(1, 5, Menu.NONE, "Java").setIcon(R.drawable.ic_launcher_foreground);
        subMenu2.add(1, 6, Menu.NONE, "Kotlin");


        SubMenu subMenu3 = menu.addSubMenu("배경색 변경");
        subMenu2.add(1, 4, Menu.NONE, "빨강");
        subMenu2.add(1, 5, Menu.NONE, "녹색");
        subMenu2.add(1, 6, Menu.NONE, "파랑");
    }

//  컨텍스트 메뉴를 선택했을 때 실행할 기능을 정의한다.

    @Override
    public boolean onContextItemSelected(MenuItem item) {

//      MenuItem 인터페이스 객체 item으로 넘어온 클릭한 메뉴의 id를 얻어온다.
        int contextMenuId = itme.getItemId();
//      switch를 이용해서 넘어온 id에 따라 실행할 동작을 지정한다.
        switch (contextMenuId) {
            case R.id.menuRed : case 7: // Red 메뉴
                mainActivity.setBackground(Color.RED);
                break;
            case R.id.menuGreen : case 8: // Green 메뉴
                mainActivity.setBackground(Color.GREEN);
                break;
            case R.id.menuBlue : case 9: // Blue 메뉴
                mainActivity.setBackground(Color.BLUE);
                break;

        }

        return super.onContextItemSelected(item);
    }

//  옵션 메뉴는 onCreateOptionsMenu() 메소드를 Override해서 만든다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//      getMenuInflater() 메소드로 xml 파일에서 정의한 메뉴를 읽어와 옵션 메뉴로 전개한다.
        getMenuInflater().inflate(R.menu.menu, menu);
//      Menu 인터페이스 개체를 이용해 xml 파일의 내용을 전개한 후 메뉴에 새 메뉴 항목을 추가한다.
        menu.add(1, 10 ,Menu.NONE, "쉬는날 공부하려니 힘들죠");

        return super.onCreateOptionsMenu(menu);
    }

//  옵션 메뉴를 선택하면 실행할 기능은 onOptionsItemSelected() 메소드를 Override 해서 만든다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int optionMenuId = itme.getItemId();
        switch (optionMenuId) {
            case R.id.menuRed:
            case 7: // Red 메뉴
                mainActivity.setBackground(Color.RED);
                break;
            case R.id.menuGreen:
            case 8: // Green 메뉴
                mainActivity.setBackground(Color.GREEN);
                break;
            case R.id.menuBlue:
            case 9: // Blue 메뉴
                mainActivity.setBackground(Color.BLUE);
                break;
            case 10:
                Toast.makeText(getApplicationContext(), "네", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
