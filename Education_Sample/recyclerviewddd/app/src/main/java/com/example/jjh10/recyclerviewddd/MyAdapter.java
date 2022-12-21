package com.example.jjh10.recyclerviewddd;

/**
 * Created by jjh10 on 2019-04-01.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //데이터 배열 선언
    private ArrayList<MyData> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView_img;
        private TextView textView_date;
        private Button save_button;
        private Button look_button;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView_img = (ImageView) itemView.findViewById(R.id.imageView_img);
            textView_date = (TextView) itemView.findViewById(R.id.textView_date);
            save_button = (Button) itemView.findViewById(R.id.save_button);
            look_button = (Button) itemView.findViewById(R.id.look_button);

        }
    }

    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);
        void saveButtonClicked(int position);
        void lookButtonClicked(int position);
    }
    private MyRecyclerViewClickListener mListener;

    public void setOnMyClickListener(MyRecyclerViewClickListener listener) { //외부에서 리스너 지정
        mListener = listener;
    }


    //생성자
    public MyAdapter(ArrayList<MyData> list) {
            this.mList = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        holder.textView_date.setText(String.valueOf(mList.get(position).getDate()));
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
        Glide.with(holder.itemView).load(mList.get(position).getImg_url()).signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                .override(300,400)
                .into(holder.imageView_img);
        ///Glide.with(this).load(img_url).signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).into(imageViewpreview);
        if(mListener != null){
            final int pos = position;
            if(position != RecyclerView.NO_POSITION) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClicked(pos);
                    }
                });

                holder.save_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.saveButtonClicked(pos);
                    }
                });

                holder.look_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.lookButtonClicked(pos);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
