package com.example.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// ViewHolder : 뷰홀더의 타입을 지정해야한다. 먼저 뷰홀더를 만드는 것이 좋다.
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<CardItem> mDataList;

    //  리사이클러뷰의 뷰홀더를 상속받는다.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView contents;
        Button share;
        Button more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.contents_text);
            share = itemView.findViewById(R.id.share_button);
            more = itemView.findViewById(R.id.more_button);
        }
    }

    public interface MyRecyclerViewClickListener {
        // 내가 눌른 아이템의 위치를 외부에서 알리겠다.
        void onItemClicked(int position);
        // onShareButtonClicked이 어디에서 눌렀는지 알리겠다.
        void onShareButtonClicked(int position);
        void onLearnMoreButtonClicked(int position);
    }

    private  MyRecyclerViewClickListener mListener;

    //외부에서 리스너를 지정할 수 있도록 하는 메소드
    public void setOnClickListener(MyRecyclerViewClickListener listener) {
    // 실제로 외부에서 클릭이 일어나면 BindViewHolder에서 처리하도록 한다.
        mListener = listener;
    }

    //  CardItem의 데이터 리스트를 다루겠다. 데이터를 외부에서 들고온다.
    public MyRecyclerAdapter(List<CardItem> dataList) {
        mDataList = dataList;
    }

    // 뷰홀더를 만드는 부분. return을 해주면 onBindViewHolder로 간다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //      리스트뷰와 같다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    //  위의 onCreateViewHolder에서 return 받은 값을 받는다. 리사이클러뷰는 뷰홀더가 강제이다.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());

        if(mListener == null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
//                  mListener.onItemClicked(holder.getAdapterPosition());
                }
            });
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onShareButtonClicked(pos);
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

    //  아이템 카운터가 가지고 있는 리스트뷰의 개수
    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
