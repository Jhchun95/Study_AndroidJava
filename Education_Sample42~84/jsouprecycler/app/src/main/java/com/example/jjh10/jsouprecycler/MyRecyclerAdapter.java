package com.example.jjh10.jsouprecycler;

/**
 * Created by jjh10 on 2019-03-25.
 */

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jjh10 on 2019-03-24.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<CardItem> mDataList;

    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);//내가 누른 아이템의 포지션을 외부에서 알 수 있도록 전달하겠다
        void onSharedButtonClicked(int position);
        void onLearnMoreButtonClicked(int position);
    }

    private MyRecyclerViewClickListener mListener;

    public void setOnMyClickListener(MyRecyclerViewClickListener listener) { //외부에서 리스너 지정
        mListener = listener;
    }


    public MyRecyclerAdapter(List<CardItem> dataList) {  //카드아이템 형태의 데이터 리스트를 다루겠다
        mDataList = dataList;  // 데이터를 외부에서 들고온다라고 해석하면 됨
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());

        if(mListener == null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
                }
            });

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onSharedButtonClicked(pos);
                }
            });

            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLearnMoreButtonClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;
        Button share;
        Button more;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.contents_text);
            share = itemView.findViewById(R.id.share_button);
            more = itemView.findViewById(R.id.more_button);
        }
    }
}
