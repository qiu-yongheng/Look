package com.eternal.look.news;

import android.content.Context;

import com.eternal.look.api.NewsApi;
import com.eternal.look.bean.news.NewsList;
import com.eternal.look.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author qiuyongheng
 * @time 2017/3/24  10:56
 * @desc ${TODD}
 */

public class NewsPresenter implements NewsContract.Presenter{
    private final String TAG = "NewsPresenter";
    private final Context context;
    private final NewsContract.View view;
    private List<NewsList.NewsBean> list = new ArrayList<>();
    private int new_id = 0;

    public NewsPresenter(Context context, NewsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadPosts(true);
    }

    @Override
    public void loadPosts(boolean clearing) {
        if (clearing) {
            new_id = 0;
            list.clear();
            view.showLoading();
        } else {
            new_id += 20;
        }

        if (NetworkUtil.networkConnected(context)) {
            new Retrofit.Builder()
                    .baseUrl("http://c.m.163.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsApi.class)
                    .getNews(new_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsList>() {
                        @Override
                        public void onCompleted() {
                            view.showResults(list);
                            view.stopLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.stopLoading();
                            view.showError();
                        }

                        @Override
                        public void onNext(NewsList newsList) {
                            list.addAll(newsList.getT1348647909107());
                        }
                    });
        } else {
            view.stopLoading();
            view.showError();
        }
    }

    @Override
    public void refresh() {
        loadPosts(true);
    }

    @Override
    public void loadMord() {
        loadPosts(false);
    }

    @Override
    public void showDetail(int position) {

    }
}
