package com.example.tvtest2;

import android.os.Bundle;
import android.util.Log;

import androidx.leanback.app.BrowseFragment;


public class MainFragment extends BrowseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }
}
