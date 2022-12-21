package com.example.jjh10.menu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static Typeface typeface;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if(typeface == null) {
            typeface = Typeface.createFromAsset(this.getAssets(), "billabong_1.ttf");
        }
        setGlobalFont(getWindow().getDecorView());
    }

    private void setGlobalFont(View view) {
        if(view != null) {
            if(view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup)view;
                int vgCnt = viewGroup.getChildCount();
                for(int i = 0; i<vgCnt; i++) {
                    View v = viewGroup.getChildAt(i);
                    if(v instanceof TextView) {
                        ((TextView) v).setTypeface(typeface);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }

}
