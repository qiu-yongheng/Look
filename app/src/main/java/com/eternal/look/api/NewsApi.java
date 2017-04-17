package com.eternal.look.api;

import com.eternal.look.bean.news.NewsList;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author qiuyongheng
 * @time 2017/4/7  10:59
 * @desc ${TODD}
 */

public interface NewsApi {
    // TODO: 16/8/17 string or int
    @GET("nc/article/headline/T1348647909107/{id}-20.html")
    Observable<NewsList> getNews(@Path("id") int id );

    @GET("nc/article/{id}/full.html")
    Observable<ResponseBody> getNewsDetail(@Path("id") String id);
}
