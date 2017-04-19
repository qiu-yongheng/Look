package com.eternal.look.gank.android;

import android.content.Context;

import com.eternal.look.api.Api;
import com.eternal.look.api.gank.GankApi;
import com.eternal.look.bean.gank.AndroidBean;
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
 * @time 2017/4/18  13:28
 * @desc ${TODD}
 */

public class AndroidPresenter implements AndroidContract.Presenter{

    private final Context context;
    private final AndroidContract.View view;
    private int page = 1;
    private List<AndroidBean.ResultsBean> list = new ArrayList<>();

    public AndroidPresenter(Context context, AndroidContract.View view) {
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
            page = 1;
            list.clear();
            view.showLoading();
        } else {
            page += 1;
        }
        if (NetworkUtil.networkConnected(context)) {
            new Retrofit.Builder()
                    .baseUrl(Api.GANK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(GankApi.class)
                    .getAndroidList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AndroidBean>() {
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
                        public void onNext(AndroidBean androidBean) {
                            list.addAll(androidBean.getResults());
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
    public void loadMore() {
        loadPosts(false);
    }

    @Override
    public void showDetail(int position) {

    }
}
