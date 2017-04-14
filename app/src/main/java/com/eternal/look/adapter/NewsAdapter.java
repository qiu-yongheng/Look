package com.eternal.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eternal.look.R;
import com.eternal.look.adapter.viewholder.FooterViewHolder;
import com.eternal.look.adapter.viewholder.NormalViewHolder;
import com.eternal.look.bean.news.NewsList;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/4/11  10:40
 * @desc ${TODD}
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    private final List<NewsList.NewsBean> list;
    private OnRecyclerViewOnClickListener mListener;

    public NewsAdapter(Context context, List<NewsList.NewsBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 对不同的ViewHolder做不同的处理
        if (holder instanceof NormalViewHolder) {
            NewsList.NewsBean newsBean = list.get(position);
            if (newsBean.getImgsrc() == null) {
                ((NormalViewHolder) holder).itemImg.setImageResource(R.drawable.placeholder);
            } else {
                //网络请求获取图片并设置
                Glide.with(context)
                        .load(newsBean.getImgsrc()) //图片地址
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((NormalViewHolder) holder).itemImg);
            }
            ((NormalViewHolder) holder).tvLatestNewsTitle.setText(newsBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    /**
     * 让外界传入监听器, 当item被点击时, 回调当前点击的item
     *
     * @param listener
     */
    public void setItemClickListener(OnRecyclerViewOnClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 获取item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }
}
