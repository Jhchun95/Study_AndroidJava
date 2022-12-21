package androidx.leanback.leanbackshowcase.app;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.leanback.leanbackshowcase.R;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;

public class Menu extends Activity implements View.OnClickListener{
    private static final String TAG = "VideoFragment";
    private static final String TAG_CATEGORY = "categories";

    FragmentManager fm;
    FragmentTransaction tran;
    MenuFragment menuf;
    AccountFragment accf;

    EditText tv1;
    TextView tv2,tv3,tv4;
    ImageButton b1,b2,b3,b4;
    RelativeLayout r1,r2,r3,r4;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dfdf);

        menuf=new MenuFragment();
        accf=new AccountFragment();

        setFrag(0);

        // RelativeLayout
        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);

        // TextView
        tv1=findViewById(R.id.search_tv);
        tv2=findViewById(R.id.account_tv);
        tv3=findViewById(R.id.setting_tv);
        tv4=findViewById(R.id.home_tv);

        // ImageButton
        b1=findViewById(R.id.search_btn);
        b2=findViewById(R.id.account_btn);
        b3=findViewById(R.id.setting_btn);
        b4=findViewById(R.id.home_btn);

        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);

        // This is r1, r2, r3, r4 setOnFocusChangeListener Code.
        // Their code are almost the same.
        r1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus==true){
                    Log.d("ssss","btn1");
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 1.0);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);
                }
                else if(hasFocus==false){
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);
                }
            }
        });
        r2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus==true){
                    Log.d("ssss","btn2");

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 1.0);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);
                }
                else if(hasFocus==false){

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);

                }
            }
        });
        r3.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus==true){
                    Log.d("ssss","btn3");

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.VISIBLE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 1.0);
                    b4.setAlpha((float) 0.4);
                }
                else if(hasFocus==false){

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);


                }
            }
        });

        r4.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus==true){
                    Log.d("ssss","btn3");

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.VISIBLE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 1.0);

                }
                else if(hasFocus==false){

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    b1.setAlpha((float) 0.4);
                    b2.setAlpha((float) 0.4);
                    b3.setAlpha((float) 0.4);
                    b4.setAlpha((float) 0.4);

                }
            }
        });

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.r1:
                Log.d("ononon","클릭1");
                setFrag(1);
                break;
            case R.id.r2:
                Log.d("ononon","클릭2");
                setFrag(2);
                break;
            case R.id.r3:
                Log.d("ononon","클릭3");
                setFrag(3);
                break;
            case R.id.r4:
                Log.d("ononon","클릭3");
                setFrag(4);
                break;
        }
    }

    // To replace Fragment Method
    public void setFrag(int n) {
        fm = getFragmentManager();
        tran = fm.beginTransaction();
        switch (n) {
            case 0:
                tran.replace(R.id.main_frame,menuf);
                tran.commit();
                break;
            case 1:
                Log.d("ononon","셋1");
                tran.replace(R.id.main_frame,accf);
                tran.commit();
                break;
            case 2:
                Log.d("ononon","셋1");
                tran.replace(R.id.main_frame,accf);
                tran.commit();
                break;
            case 3:
                Log.d("ononon","셋1");
                tran.replace(R.id.main_frame,accf);
                tran.commit();
                break;
            case 4:
                Log.d("ononon","셋1");
                tran.replace(R.id.main_frame,menuf);
                tran.commit();
                break;
        }
    }

}
