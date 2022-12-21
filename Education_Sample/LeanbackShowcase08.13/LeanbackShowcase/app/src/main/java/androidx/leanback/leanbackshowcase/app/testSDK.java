package androidx.leanback.leanbackshowcase.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.leanback.leanbackshowcase.app.grid.SlideShow;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.app.network.LoginRequestDTO;
import androidx.leanback.leanbackshowcase.app.network.RetrofitClient;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

import com.cloudike.cloudikelog.Logger;
import com.cloudike.cloudikephotos.BuildConfig;
import com.cloudike.cloudikephotos.core.PhotoManager;
import com.cloudike.cloudikephotos.core.data.dto.PhotoItem;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class testSDK extends Activity {
    PhotoManager photoManager;
    Button loginBtn;
    EditText username;
    EditText password;

    String id;
    String pw;

    ImageButton passwordvisibility;
    ImageView emailcheck;
    ImageView passwordcheck;

    String emailValidation= "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

    public String emailInput;
    public String passwordInput;
    int count = 0 , count1 = 0;

    // String backendUrl, token, userProfileId, appUserAgent, uniqueDeviceId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginBtn = (Button) findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        passwordvisibility = (ImageButton) findViewById(R.id.passwordvisibility);
        emailcheck = (ImageView) findViewById(R.id.emailcheck);
        passwordcheck = (ImageView) findViewById(R.id.passwordcheck);

        loginBtn.setFocusableInTouchMode(true);
        loginBtn.setFocusable(true);

        // Initialize logger first because Photos SDK uses it
        Logger.init(getApplicationContext());
        Logger.setDebugMode(BuildConfig.DEBUG);

        // initRxErrorHandler()/

//        PhotoManager.initInstance(
//               this,
//                PhotoManager.CompetitionMode.STANDALONE,
//               "<competitor app package name>", true);
//        photoManager = PhotoManager.INSTANCE;

        // Input EmailAddress
        username.addTextChangedListener(new TextWatcher() {
            @Override
            // 텍스트가 변경되기 바로 이전에 동작
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            // 텍스트가 변경되는 동시에 동작
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            // 텍스트가 변경된 이후에 동작
            @Override
            public void afterTextChanged(Editable s) {
                emailInput = username.getText().toString().trim();
                if(emailInput.matches(emailValidation) && s.length() > 0 ) {
                    count=1;
                    emailcheck.setVisibility(View.VISIBLE);
                } else {
                    count=0;
                    emailcheck.setVisibility(View.INVISIBLE);
                }
                if(count==1&count1==1) {
                    loginBtn.setBackgroundResource(R.drawable.intro_login_btn_abled);
                } else {
                    loginBtn.setBackgroundResource(R.drawable.intro_login_btn_disabled);
                }
                Log.d("username.after", count + "");
                Log.d("username.after", count1 + "");
            }
        });

        // Input Password
        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                passwordInput = password.getText().toString().trim();
                if(passwordInput.length() > 0) {
                    count1 = 1;
                    //passwordcheck.setVisibility(View.VISIBLE);
                } else {
                    count1 = 0;
                    //passwordcheck.setVisibility(View.INVISIBLE);
                }
                if(count==1&count1==1) {
                    loginBtn.setBackgroundResource(R.drawable.intro_login_btn_abled);
                    loginBtn.setEnabled(true);
                } else {
                    loginBtn.setBackgroundResource(R.drawable.intro_login_btn_disabled);
                    loginBtn.setEnabled(false);

                }
                Log.d("username.after", count + "");
                Log.d("username.after", count1 + "");

            }
        });

        // LoginButton
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 투명도.
                loginBtn.setBackgroundColor(Color.TRANSPARENT);
                loginBtn.setBackgroundResource(R.drawable.intro_login_btn_abled);
                //loginBtn.setSelected(true);

                id = username.getText().toString();
                pw = password.getText().toString();
//
//                Log.d("logincheck", "id:" + id);
//                Log.d("logincheck", "pw:" + pw);

                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
                new NetworkCall().execute();

            }
        });

        // setOnFocusChangeListener in LoginBtn
        loginBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("안","dddddddddd");
                if (hasFocus == true) {

                    if(count==1&count1==1) {
                        loginBtn.setBackgroundResource(R.drawable.intro_login_btn_onfocus);
                    } else {
                        loginBtn.setBackgroundResource(R.drawable.intro_login_btn_disabled);
                    }
                    Log.d("안", "sssssssssss");
                } else {
                    if(count==1&count1==1) {
                        loginBtn.setBackgroundResource(R.drawable.intro_login_btn_abled);

                    } else {
                        loginBtn.setBackgroundResource(R.drawable.intro_login_btn_disabled);
                    }
                }
            }

        });
    }

    // Password hide and show
    public void ShowHidePass(View view) {

        if(view.getId()== R.id.passwordvisibility) {
            // Hide
            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                if(passwordInput.length() > 0 ) {
                    passwordvisibility.setImageResource(R.drawable.ic_cloudike_passwordhide);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    count =  1;
                    count1 = 1;
                }
            } else {
                // Show
                if(passwordInput.length() > 0 ) {
                    passwordvisibility.setImageResource(R.drawable.ic_cloudike_passwordshow);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    count = 1;
                    count1 = 1;
                }
            }
            if(count==1&count1==1) {
                loginBtn.setBackgroundResource(R.drawable.intro_login_btn_abled);
                loginBtn.setEnabled(true);
            } else {
                loginBtn.setBackgroundResource(R.drawable.intro_login_btn_disabled);
                loginBtn.setEnabled(false);
            }
    }
    }

    public static void main(String[] args)  {

        // ExecutorService 인터페이스 구현객체 Executors 정적 메소드를 통해 최대 스레드 개수가 2인 스레드 풀 생성
        // interface ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드에게 시킬 작업 내용
                    // What to do with the thread
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    // 스레드 풀 사이즈 얻기
                    // Get ThreadPoolSize
                    int poolSize = threadPoolExecutor.getPoolSize();
                    // 스레드 풀에 있는 해당 스레드 이름 얻기
                    // Get Thread.currentThread().getName();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수:" + poolSize + "] 작업 스레드 이름: "+threadName);

                    // Exception
                    int value = Integer.parseInt("예외");
                }
            };
            // 스레드풀에게 작업 처리 요청
            // ThreadPool execute
            executorService.execute(runnable);
            //executorService.submit(runnable);

            // 콘솔 출력 시간을 주기 위해 메인스레드 0.01초 sleep을 걸어둠.
            // To give Console OutPut time, we set up Thread.sleep(10);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    private class NetworkCall extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            try {
                return RetrofitClient.getAuthService().login(new LoginRequestDTO(id, pw)).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("AUTH", "Login Error : " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (result != null) {
                // Log.d("result","진아");
                // Log.d("result",result.toString());
                // Get auth
                SharedPreferences sf = getSharedPreferences("auth", MODE_PRIVATE);
                // Edit editor
                SharedPreferences.Editor editor = sf.edit();
                for (String key : Objects.requireNonNull(result).keySet()) {
                    Log.d("rrrr",key);
                    Log.d("rrr", result.get(key));
                    editor.putString(key, result.get(key));
                    //result 에서 key를 받아와,
                    //editor 에 key, value 값을 저장함
                }

                editor.apply(); //수정끗 - commit이랑 비슷함
                Intent intent = new Intent(getApplicationContext(),SlideShow.class);
                startActivity(intent);

/*
                SharedPreferences sdf = getSharedPreferences("auth", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                for (String key : Objects.requireNonNull(result).keySet()) {
                    editor.putString(key, result.get(key));
                    //result 에서 key를 받아와,
                    //editor 에 key, value 값을 저장함
                }
                sf.getString("auth");
 */
                //editor에서 get하기.
            }

        }

    }

}