package com.eternal.look.meizi;

import android.content.Context;

import com.eternal.look.api.MeiziApi;
import com.eternal.look.bean.meizi.MeiziData;
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
 * @time 2017/3/24  10:57
 * @desc ${TODD}
 */

public class MeiziPresenter implements MeiziContract.Presenter{
    private final Context context;
    private final MeiziContract.View view;
    private List<MeiziData.ResultsBean> list = new ArrayList<>();
    private int page = 1;

    public MeiziPresenter(Context context, MeiziContract.View view) {
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
                    .baseUrl("http://gank.io/api/data/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MeiziApi.class)
                    .getMeiPic(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MeiziData>() {
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
                        public void onNext(MeiziData meiziData) {
                            list.addAll(meiziData.getResults());
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
