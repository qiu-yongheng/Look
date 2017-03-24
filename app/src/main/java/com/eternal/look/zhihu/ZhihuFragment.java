package com.eternal.look.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author qiuyongheng
 * @time 2017/3/24  10:35
 * @desc ${TODD}
 */

public class ZhihuFragment extends Fragment implements ZhihuContract.View{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void setPresenter(ZhihuContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showResults() {

    }

    @Override
    public void showPickDialog() {

    }
}
