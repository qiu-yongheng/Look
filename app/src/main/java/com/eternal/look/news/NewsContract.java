package com.eternal.look.news;

import com.eternal.look.BasePresenter;
import com.eternal.look.BaseView;
import com.eternal.look.bean.news.NewsList;

import java.util.List;

/**
 * @author qiuyongheng
 * @time 2017/3/24  10:54
 * @desc ${TODD}
 */

public interface NewsContract {
    interface View extends BaseView<Presenter> {
        //显示加载或其他类型的错误
        void showError();
        //显示正在加载
        void showLoading();
        //停止显示正在加载
        void stopLoading();
        //成功获取到数据后, 在界面中显示
        void showResults(List<NewsList.NewsBean> list);
        //显示用于加载指定日期的date picker dialog
        void showPickDialog();
    }

    interface Presenter extends BasePresenter {
        //请求数据
        void loadPosts(boolean clearing);
        //刷新数据
        void refresh();
        //加载更多
        void loadMord();
        //显示详情
        void showDetail(int position);
    }
}
