package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

// 안드로이드 서비스 구현 방법
// 1. 서비스를 구현할 클래스는 android.app 패키지의 Service 클래스를 상속받고 Runnable 인터페이스를 구현받아 만든다.
//      => 클래스 이름에서 alt + enter를 눌러 onBind() 메소드와 run() 메소드를 Override 한다.
// 2. AndroidManifest.xml 파일의 <application> 태그 내부에 실행할 서비스를 등록한다.
//      => <service android:name=".MyService"/>
// 3. 액티비티에서 startService() 메소드로 서비스를 실행한다.
//      => startService() 메소드가 실행될 때 자동으로 실행되는 onStartCommend() 메소드를 Override 시키고 서비스의 기능을 구현한다.
// 4. 액티비티에서 stopService() 메소드로 서비스를 종료한다.
//      => stopService() 메소드가 실행될 때 자동으로 실행되는 onDestroy() 메소드를 Override 시키고 서비스가 종료될 때 실행할 기능을 코딩한다.


public class MyService extends Service implements Runnable {

    boolean flag = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
//      서비스 기능을 구현한다.
        int count = 0;
        while (count < 100) {

            if(!flag) {
                break;
            }

            Log.e("서비스 테스트", ++count + "번째 호출중");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

//  액티비티에서 startService() 메소드가 실행될 때 자동으로 실행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "서비스 시작", Toast.LENGTH_SHORT).show();
        Log.e("서비스", "시작");

        /*
//      서비스 기능이 구현된 스레드를 실행한다.
//      1. Runnable 인터페이스를 구현받아 스레드를 작성한 클래스의 객체를 만든다.
        MyService myService = new MyService();
//      Runnable 인터페이스는 스레드를 실행하는 기능이 없으므로 스레드를 Thread 클래스의 도움을 받아 실행해야한다.
//      Thread 클래스의 객체를 생성할 때 생성자의 인수로 Runnable 인터페이스를 구현받아 생성한 클래스의 객체를 넘겨준다.
        Thread thread = new Thread(myService);

//      3. Thread 클래스 객체에서 run() 메소드를 실행하면 run() 이라는 일반 메소드가 실행되므로 반드시 start() 메소드로
//      스레드를 실행한다.
        thread.start();
         */
//      위의 3줄을 1문장으로 줄여쓰면 아래와 같다.
//      new Thread(new MyService()).start();

//      MyService 클래스는 현재 클래스이므로 아래와 같이 스레드를 실행해도 된다.
//        Thread thread = new Thread(this);
//        thread.start();
        new Thread(this).start();


        return super.onStartCommand(intent, flags, startId);
    }

//  액티비티에서 stopService() 메소드가 실행될 때 자동으로 실행되는 메소드
//  startService() 메소드로 서비스가 실행이 된 상태일 경우에만 stopService() 메소드가 실행되기 때문에 onDestroy() 메소드도
//  서비스가 실행된 상태일 경우에만 실행된다.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "서비스 종료", Toast.LENGTH_SHORT).show();
        Log.e("서비스", "종료");

        flag = false ;
    }
}
