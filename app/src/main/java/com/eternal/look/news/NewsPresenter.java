package com.eternal.look.news;

import android.content.Context;

import com.eternal.look.api.NewsApi;
import com.eternal.look.util.NetworkUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author qiuyongheng
 * @time 2017/3/24  10:56
 * @desc ${TODD}
 */

public class NewsPresenter implements NewsContract.Presenter{
    private final Context context;
    private final NewsContract.View view;

    public NewsPresenter(Context context, NewsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadPosts(long date, boolean clearing) {
        if (clearing) {
            view.showLoading();
        }

        if (NetworkUtil.networkConnected(context)) {
            new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsApi.class)
                    .getNews(0)
                    .
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(long date) {

    }

    @Override
    public void showDetail(int position) {

    }
}
