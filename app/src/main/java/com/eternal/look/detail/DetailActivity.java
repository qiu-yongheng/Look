package com.eternal.look.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.eternal.look.R;
import com.eternal.look.bean.BeanType;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qiuyongheng
 * @time 2017/4/12  17:15
 * @desc ${TODD}
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.layout_container)
    FrameLayout layoutContainer;
    private DetailFragment fragment;
    private DetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        /** 1. 初始化fragment, 添加到activity */
        initFragment(savedInstanceState);
        /** 2. 实例化Presenter*/
        initPresenter();
        /** 3. 获取数据*/
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        presenter.setType((BeanType) intent.getSerializableExtra("type"));
        presenter.setId(intent.getIntExtra("id", 0));
        presenter.setTitle(intent.getStringExtra("title"));
    }

    private void initPresenter() {
        presenter = new DetailPresenter(this, fragment);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            fragment = (DetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,"detailFragment");
        } else {
            fragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_container, fragment)
                    .commit();
        }
    }
}
