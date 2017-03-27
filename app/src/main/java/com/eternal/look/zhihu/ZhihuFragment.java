package com.eternal.look.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternal.look.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author qiuyongheng
 * @time 2017/3/24  10:35
 * @desc ${TODD}
 */

public class ZhihuFragment extends Fragment implements ZhihuContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**1. 初始化布局*/
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
