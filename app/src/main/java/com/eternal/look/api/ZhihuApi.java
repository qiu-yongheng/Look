package com.eternal.look.api;

import com.eternal.look.bean.ZhihuNews;
import com.eternal.look.bean.ZhihuStory;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author qiuyongheng
 * @time 2017/3/31  14:48
 * @desc 知乎日报api
 */

public interface ZhihuApi {

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuNews> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhihuStory(@Path("id") String id);
}
