package com.eternal.look.gank.android;

import com.eternal.look.BasePresenter;
import com.eternal.look.BaseView;
import com.eternal.look.bean.gank.AndroidBean;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/4/18  13:29
 * @desc ${TODD}
 */

public interface AndroidContract {
    interface View extends BaseView<Presenter> {
        //显示加载或其他类型的错误
        void showError();
        //显示正在加载
        void showLoading();
        //停止显示正在加载
        void stopLoading();
        //成功获取到数据后, 在界面中显示
        void showResults(List<AndroidBean.ResultsBean> list);
        //显示用于加载指定日期的date picker dialog
        void showPickDialog();
    }

    interface Presenter extends BasePresenter {
        //请求数据
        void loadPosts(boolean clearing);
        //刷新数据
        void refresh();
        //家现在更多文章
        void loadMore();
        //显示详情
        void showDetail(int position);
    }
}
