package com.eternal.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eternal.look.R;
import com.eternal.look.adapter.viewholder.FooterViewHolder;
import com.eternal.look.adapter.viewholder.MeiziViewHolder;
import com.eternal.look.bean.meizi.MeiziData;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/4/12  13:06
 * @desc ${TODD}
 */

public class MeiziAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater inflater;
    /**
     * item类型: 文字 + 图片
     */
    private static final int TYPE_NORMAL = 0;
    /**
     * item类型: footer，加载更多
     */
    private static final int TYPE_FOOTER = 1;
    private final Context context;
    private final List<MeiziData.ResultsBean> list;
    private OnRecyclerViewOnClickListener mListener;

    public MeiziAdaper(Context context, List<MeiziData.ResultsBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new MeiziViewHolder(inflater.inflate(R.layout.meizi_item, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MeiziViewHolder) {
            MeiziData.ResultsBean resultsBean = list.get(position);
            if (resultsBean.getUrl() == null) {
                ((MeiziViewHolder) holder).itemImg.setImageResource(R.drawable.placeholder);
            } else {
                //网络请求获取图片并设置
                Glide.with(context)
                        .load(resultsBean.getUrl()) //图片地址
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((MeiziViewHolder) holder).itemImg);
            }
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
