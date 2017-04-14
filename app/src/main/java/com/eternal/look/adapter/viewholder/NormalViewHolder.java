package com.eternal.look.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternal.look.R;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

/**
 * @author qiuyongheng
 * @time 2017/4/11  10:59
 * @desc 普通item 图片 + 文字
 */

public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView itemImg;
    public TextView tvLatestNewsTitle;
    public OnRecyclerViewOnClickListener listener;

    public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
        super(itemView);
        itemImg = (ImageView) itemView.findViewById(R.id.imageViewCover);
        tvLatestNewsTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.OnItemClick(v,getLayoutPosition());
        }
    }
}
