package com.example.waean.zhiwen.adpter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.waean.zhiwen.R;
import com.example.waean.zhiwen.pojo.New;

import java.util.List;

/**
 * Created by waean on 2017/04/06.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private Context mContext;
    private List<New> mNewList;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public NewAdapter(List<New> mNewList) {
        this.mNewList = mNewList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView newTitle;
        ImageView newImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            newImage = (ImageView) itemView.findViewById(R.id.new_image);
            newTitle = (TextView) itemView.findViewById(R.id.new_title);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.new_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        New aNew = mNewList.get(position);
        holder.newTitle.setText(aNew.getTitle());
        Glide.with(mContext).load(aNew.getThumbnail_pic_s()).into(holder.newImage);
        if (mOnItemClickListener != null) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.cardView, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.cardView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNewList.size();
    }

}
