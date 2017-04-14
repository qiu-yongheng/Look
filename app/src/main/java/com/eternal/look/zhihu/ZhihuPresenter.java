package com.eternal.look.zhihu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.eternal.look.api.ZhihuApi;
import com.eternal.look.bean.BeanType;
import com.eternal.look.bean.zhihu.ZhihuNews;
import com.eternal.look.detail.DetailActivity;
import com.eternal.look.util.DateFormatterUtil;
import com.eternal.look.util.NetworkUtil;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * @author qiuyongheng
 * @time 2017/3/24  10:35
 * @desc ${TODD}
 */

public class ZhihuPresenter implements ZhihuContract.Presenter {
    private final Context context;
    private final ZhihuContract.View view;
    private final String TAG = "ZhihuPresenter";
    /**
     * 封装item的数据的集合
     */
    private ArrayList<ZhihuNews.Question> list = new ArrayList<ZhihuNews.Question>();

    /**
     * 持有view的引用
     *
     * @param context
     * @param view
     */
    public ZhihuPresenter(Context context, ZhihuContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }

    @Override
    public void loadPosts(long date, final boolean clearing) {
        if (clearing) {
            list.clear();
            view.showLoading();
        }
        // 判断是否有网
        if (NetworkUtil.networkConnected(context)) {
            new Retrofit.Builder()
                    .baseUrl("http://news-at.zhihu.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(ZhihuApi.class)
                    .getTheDaily(DateFormatterUtil.ZhihuDailyDateFormat(date)) // getTheDaily是retrofit接口中的方法, 将获取到的数据封装到bean中
                    .flatMap(new Func1<ZhihuNews, Observable<ZhihuNews.Question>>() { // 平铺数据, 转换后返回
                        @Override
                        public Observable<ZhihuNews.Question> call(ZhihuNews zhihuNews) {
                            // 遍历
                            return Observable.from(zhihuNews.getStories());
                        }
                    })
                    .subscribeOn(Schedulers.io()) // 读写文件、读写数据库、网络信息交互 -> 事件产生
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhihuNews.Question>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "onCompleted事件队列完成");
                            Log.d(TAG, "list.size() = " + list.size());
                            // 回调数据
                            view.showResults(list);
                            view.stopLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.e(TAG, "没有网络连接");
                            view.stopLoading();
                            view.showError();
                        }

                        @Override
                        public void onNext(ZhihuNews.Question question) {
                            list.add(question);
                        }
                    });
        } else {
            view.stopLoading();
            view.showError();
        }
    }

    @Override
    public void refresh() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }

    @Override
    public void loadMore(long date) {
        loadPosts(date, false);
    }

    @Override
    public void showDetail(int position) {
        context.startActivity(new Intent(context, DetailActivity.class)
                .putExtra("type", BeanType.TYPE_ZHIHU) //设置详细页的类型
                .putExtra("id", list.get(position).getId()) //获取数据的id
                .putExtra("title", list.get(position).getTitle())); //获取数据的标题
    }
}
