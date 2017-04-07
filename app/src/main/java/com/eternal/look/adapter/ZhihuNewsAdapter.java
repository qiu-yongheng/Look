package com.eternal.look.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eternal.look.R;
import com.eternal.look.bean.zhihu.ZhihuNews;
import com.eternal.look.interfaze.OnRecyclerViewOnClickListener;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/4/5  11:18
 * @desc ${TODD}
 */

public class ZhihuNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<ZhihuNews.Question> list;
    private final LayoutInflater inflater;
    /**
     * item类型: 文字 + 图片
     */
    private static final int TYPE_NORMAL = 0;
    /**
     * item类型: footer，加载更多
     */
    private static final int TYPE_FOOTER = 1;
    private OnRecyclerViewOnClickListener mListener;

    public ZhihuNewsAdapter(Context context, List<ZhihuNews.Question> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 根据ViewType加载不同布局
        switch (viewType) {
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.home_list_item_layout, parent, false), mListener);
            case TYPE_FOOTER:
                return new FooterViewHolder(inflater.inflate(R.layout.list_footer, parent, false));
        }
        return null;
    }
    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView itemImg;
        private TextView tvLatestNewsTitle;
        private OnRecyclerViewOnClickListener listener;

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

    /**
     * 加载数据item
     */
    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 给holder中的view绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 对不同的ViewHolder做不同的处理
        if (holder instanceof NormalViewHolder) {
            ZhihuNews.Question item = list.get(position);
            if (item.getImages().get(0) == null){
                ((NormalViewHolder)holder).itemImg.setImageResource(R.drawable.placeholder);
            } else {
                //网络请求获取图片并设置
                Glide.with(context)
                        .load(item.getImages().get(0)) //图片地址
                        .asBitmap()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.placeholder)
                        .centerCrop()
                        .into(((NormalViewHolder)holder).itemImg);
            }
            ((NormalViewHolder)holder).tvLatestNewsTitle.setText(item.getTitle());
        }
    }
    /**
     * 让外界传入监听器, 当item被点击时, 回调当前点击的item
     * @param listener
     */
    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }
    /**
     * 获取item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    /**
     * 获取item类型
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
