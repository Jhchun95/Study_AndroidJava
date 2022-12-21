package com.example.js;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

public class pgAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater inflater;
    public pgAdapter(LayoutInflater inflater, Context c) {
        mContext = c;
        this.inflater=inflater;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.sky1, R.drawable.sky2,
            R.drawable.sky3 ,R.drawable.sky4,
            R.drawable.sky5, R.drawable.sky6,
            R.drawable.sky7 ,R.drawable.sky8
    };

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=null;
        view= inflater.inflate(R.layout.viewpager_childview, null);
        ImageView img= (ImageView)view.findViewById(R.id.img_viewpager_childimage);
        img.setImageResource(mThumbIds[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v==obj;
    }
}