package com.eternal.look.detail;

import android.content.Context;
import android.content.res.Configuration;

import com.eternal.look.api.NewsApi;
import com.eternal.look.api.ZhihuApi;
import com.eternal.look.bean.BeanType;
import com.eternal.look.bean.news.NewsDetail;
import com.eternal.look.bean.zhihu.ZhihuStory;
import com.eternal.look.util.NetworkUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author qiuyongheng
 * @time 2017/4/13  10:46
 * @desc ${TODD}
 */

public class DetailPresenter implements DetailContract.Presenter {
    private BeanType type;
    private int id;
    private String title;
    private final Context context;
    private final DetailContract.View view;
    private String docId;
    private Gson gson = new Gson();
    private String imageSrc;

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setType(BeanType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DetailPresenter(Context context, DetailContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        requestData();
    }

    @Override
    public void requestData() {
        if (type == null) {
            view.showError();
            return;
        }
        view.showLoading();
        view.setTitle(title);

        if (NetworkUtil.networkConnected(context)) {
            switch (type) {
                case TYPE_ZHIHU:
                    new Retrofit.Builder()
                            .baseUrl("http://news-at.zhihu.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build()
                            .create(ZhihuApi.class)
                            .getZhihuStory(String.valueOf(id))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ZhihuStory>() {
                                @Override
                                public void onCompleted() {
                                    view.stopLoading();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.stopLoading();
                                    view.showError();
                                }

                                @Override
                                public void onNext(ZhihuStory zhihuStory) {
                                    view.showCover(zhihuStory.getImage());
                                    if (zhihuStory.getBody() == null) {
                                        // 直接用webview加载网址
                                        view.showResultWithoutBody(zhihuStory.getShare_url());
                                    } else {
                                        // 解析HTML
                                        view.showResult(convertZhihuContent(zhihuStory.getBody()));
                                    }
                                }
                            });
                    break;
                case TYPE_NEWS:
                    new Retrofit.Builder()
                            .baseUrl("http://c.m.163.com/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(NewsApi.class)
                            .getNewsDetail(docId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onCompleted() {
                                    view.stopLoading();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.stopLoading();
                                    view.showError();
                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(responseBody.string());
                                        JSONObject object = jsonObject.getJSONObject(docId);
                                        NewsDetail newsDetail = gson.fromJson(object.toString(), NewsDetail.class);

                                        view.showCover(imageSrc);
                                        if (newsDetail.getBody() == null) {
                                            view.showResultWithoutBody(newsDetail.getShareLink());
                                        } else {
                                            view.showResult(newsDetail.getBody());
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    break;
                case TYPE_MEIZI:
                    break;
            }
        } else {
            view.stopLoading();
            view.showError();
        }
    }

    /**
     * 转换知乎的HTML
     *
     * @param preResult
     * @return
     */
    private String convertZhihuContent(String preResult) {

        preResult = preResult.replace("<div class=\"img-place-holder\">", "");
        preResult = preResult.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(preResult)
                .append("</body></html>").toString();
    }
}
