package com.eternal.look.api.gank;

import com.eternal.look.bean.gank.AndroidBean;
import com.eternal.look.bean.gank.VideoBean;
import com.eternal.look.bean.meizi.MeiziData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author qiuyongheng
 * @time 2017/4/12  10:28
 * @desc ${TODD}
 */

public interface GankApi {
    @GET("data/福利/10/{page}")
    Observable<MeiziData> getMeiPic (@Path("page") int page);

    @GET("data/Android/10/{page}")
    Observable<AndroidBean> getAndroidList (@Path("page") int page);

    @GET("data/休息视频/10/{page}")
    Observable<VideoBean> getVideoList (@Path("page") int page);

    @GET("random/data/福利/10")
    Observable<MeiziData> getRandomPic ();

}
