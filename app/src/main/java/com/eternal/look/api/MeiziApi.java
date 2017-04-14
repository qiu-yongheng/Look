package com.eternal.look.api;

import com.eternal.look.bean.meizi.MeiziData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author qiuyongheng
 * @time 2017/4/12  10:28
 * @desc ${TODD}
 */

public interface MeiziApi {
    @GET("福利/20/{page}")
    Observable<MeiziData> getMeiPic (@Path("page") int page);
}
