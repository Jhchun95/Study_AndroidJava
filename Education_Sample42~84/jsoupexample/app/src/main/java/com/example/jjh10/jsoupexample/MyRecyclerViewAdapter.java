package com.example.jjh10.jsoupexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jjh10 on 2019-03-11.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private List<String> mimgurl;
    private LayoutInflater mInflater;
    Context mcontext;

    //data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<String> data, List<String> imgurl) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mimgurl = imgurl;
        mcontext = context;
    }

    //inflates the row layout from xml when needed

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row,parent, false);
        return new ViewHolder(view);
    }

    //binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = mData.get(position);
        String img = mimgurl.get(position);
        holder.myTextView.setText(text);
        Log.e("data..?", text);
        Picasso.with(mcontext).load(img).error(R.drawable.ic_launcher_background).into(holder.imageView);
    }

    //total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView myTextView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textView_id);
            imageView = itemView.findViewById(R.id.imageView_id);

        }

    }

}
