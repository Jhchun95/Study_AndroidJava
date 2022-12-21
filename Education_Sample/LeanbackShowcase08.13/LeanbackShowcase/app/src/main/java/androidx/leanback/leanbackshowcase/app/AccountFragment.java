package androidx.leanback.leanbackshowcase.app;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

import androidx.leanback.leanbackshowcase.R;
import androidx.annotation.Nullable;


public class AccountFragment extends Fragment {

    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // acc_frag means 'AccountFragment'
        rootView = (ViewGroup) inflater.inflate(R.layout.acc_frag,container,false);

        return rootView;
    }

}