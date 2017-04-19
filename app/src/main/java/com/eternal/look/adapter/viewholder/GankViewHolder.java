package com.eternal.look.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternal.look.R;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

/**
 * @author qiuyongheng
 * @time 2017/4/19  13:22
 * @desc 干货
 */

public class GankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  ImageView mIvGank;
    public  TextView mTvTitle;
    public  OnRecyclerViewOnClickListener mListener;

    public GankViewHolder(View itemView, OnRecyclerViewOnClickListener mListener) {
        super(itemView);
        this.mListener = mListener;
        mIvGank = (ImageView) itemView.findViewById(R.id.iv_gank);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.OnItemClick(view, getLayoutPosition());
    }
}
