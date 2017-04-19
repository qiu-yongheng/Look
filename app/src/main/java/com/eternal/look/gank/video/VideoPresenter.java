package com.eternal.look.gank.video;

import android.content.Context;

/**
 * @author qiuyongheng
 * @time 2017/4/18  13:29
 * @desc ${TODD}
 */

public class VideoPresenter implements VideoContract.Presenter{
    private final Context context;
    private final VideoContract.View view;

    public VideoPresenter(Context context, VideoContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadPosts(boolean clearing) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void showDetail(int position) {

    }
}
