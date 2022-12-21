package com.example.tabtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

//      tab을 표시할 Adapter 내부 클래스 객체를 만들고 TabLayout과 ViewPager에 추가할 내용을 넣어준다.
        MainPagerAdpater adapter = new MainPagerAdpater(getSupportFragmentManager());

//      ViewPager에 넣어줄 Fragment : app 폴더 우클릭 => New => Fragment => Fragment(Blank)
//      ViewPager에 넣어줄 Fragment 객체를 생성한다.
        Fragment1 frag1 = new Fragment1();
        adapter.addItem("코딩", frag1);
        Fragment2 frag2 = new Fragment2();
        adapter.addItem("엑셀", frag2);
        Fragment3 frag3 = new Fragment3();
        adapter.addItem("한글", frag3);

//      ViewPager에 Adapter를 넣어준다.
        viewPager.setAdapter(adapter);
//      TabLayout을 활성화 시킨다.
        tab.setupWithViewPager(viewPager);
    }

//  TabLayout과 ViewPager에 추가할 내용을 넣어주는 내부 클래스를 FragmentPagerAdapter 클래스를 상속받아 만든다.
    class MainPagerAdpater extends FragmentPagerAdapter {

//      TabLayout에 표시될 데이터를 기억할 ArrayList
        ArrayList<String> title = new ArrayList<>();
//      ViewPager에 표시될 Fragment를 기억할 ArrayList
        ArrayList<Fragment> item = new ArrayList<>();

//      클래스 이름 위에서 alt + Enter를 눌러 자동으로 입력한 생성자
        public MainPagerAdpater(FragmentManager fm) {
                super(fm);
            }

//          여기부터
//          ViewPager에 전개될 Fragment를 얻어오는 메소드
            @Override
            public Fragment getItem(int position) {
//              return null;을 아래와 같이 수정한다.
                return item.get(position);
            }

//          ViewPager에 전개될 Fragment의 개수를 얻어오는 메소드
            @Override
            public int getCount() {
//              return 0; 을 아래와 같이 수정한다.
                return item.size();
        }
//      여기까지 클래스 이름 위에서 alt + Enter를 눌러서 자동으로 입력한 FragmentPagerAdapter 추상 클래스의 추상 메소드 Override

//      TabLayout에 표시할 문자열을 읽어오는 메소드를 (alt + Enter를 눌러 수동으로) Override 한다.
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//          return super.getPageTitle(position);를 아래와 같이 수정한다.
            return title.get(position);
        }

//      Adapter에 TabLayout과 ViewPager에 표시할 데이터를 추가하는 메소드를 만든다.
         public void addItem(String label, Fragment fragment) {
            title.add(label);   // TabLayout에 표시할 문자열을 추가한다.
            item.add(fragment); // ViewPager에 표시할 Fragment를 추가한다.
        }
    }

}
