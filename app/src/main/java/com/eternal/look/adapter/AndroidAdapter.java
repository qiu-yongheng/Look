package com.eternal.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eternal.look.R;
import com.eternal.look.adapter.viewholder.FooterViewHolder;
import com.eternal.look.adapter.viewholder.GankViewHolder;
import com.eternal.look.bean.gank.AndroidBean;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/4/19  11:07
 * @desc ${TODD}
 */

public class AndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * item类型: 文字 + 图片
     */
    private static final int TYPE_NORMAL = 0;
    /**
     * item类型: footer，加载更多
     */
    private static final int TYPE_FOOTER = 1;
    private final Context context;
    private final List<AndroidBean.ResultsBean> list;
    private final LayoutInflater inflater;
    private OnRecyclerViewOnClickListener mListener;

    public AndroidAdapter(Context context, List<AndroidBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new GankViewHolder(inflater.inflate(R.layout.item_gank, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GankViewHolder) {
            AndroidBean.ResultsBean resultsBean = list.get(position);
            ((GankViewHolder) holder).mIvGank.setImageResource(R.drawable.placeholder);

            ((GankViewHolder) holder).mTvTitle.setText(resultsBean.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    /**
     * 让外界传入监听器, 当item被点击时, 回调当前点击的item
     *
     * @param listener
     */
    public void setItemClickListener(OnRecyclerViewOnClickListener listener) {
        this.mListener = listener;
    }
}
