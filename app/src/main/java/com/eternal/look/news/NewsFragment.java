package com.eternal.look.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternal.look.R;

/**
 * @author qiuyongheng
 * @time 2017/3/24  10:56
 * @desc gagaasdfasdfasdf
 */

public class NewsFragment extends Fragment implements NewsContract.View{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**1. 初始化布局*/
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Log.d("==", "newsFragemnt");
        return view;
    }
    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

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
