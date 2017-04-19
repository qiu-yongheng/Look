package com.eternal.look.gank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eternal.look.R;
import com.eternal.look.adapter.GankPagerAdapter;
import com.eternal.look.gank.android.AndroidFragment;
import com.eternal.look.gank.android.AndroidPresenter;
import com.eternal.look.gank.meizi.MeiziFragment;
import com.eternal.look.gank.meizi.MeiziPresenter;
import com.eternal.look.gank.video.VideoFragment;
import com.eternal.look.gank.video.VideoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author qiuyongheng
 * @time 2017/4/18  11:07
 * @desc ${TODD}
 */

public class GankFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    private Context context;
    private MeiziFragment meiziFragment;
    private AndroidFragment androidFragment;
    private VideoFragment videoFragment;
    private GankPagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Activity的context
        this.context = getActivity();

        /**1. 创建fragment*/
        if (savedInstanceState != null) {
            //创建管理器, 获取Fragment
            FragmentManager manager = getChildFragmentManager();
            androidFragment = (AndroidFragment) manager.getFragment(savedInstanceState, "android");
            videoFragment = (VideoFragment) manager.getFragment(savedInstanceState, "video");
            meiziFragment = (MeiziFragment) manager.getFragment(savedInstanceState, "meizi");
        } else {
            // 创建View实例
            androidFragment = new AndroidFragment();
            videoFragment = new VideoFragment();
            meiziFragment = new MeiziFragment();
        }

        /**2. 创建Presenter实例*/
        new AndroidPresenter(context, androidFragment);
        new VideoPresenter(context, videoFragment);
        new MeiziPresenter(context, meiziFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        /**2. 初始化控件*/
        initViews(view);

        /**3. 设置可以在fragment添加menu*/
        setHasOptionsMenu(true);
        return view;
    }

    /**
     * 初始化控件, 导航页关联数据
     *
     * @param view
     */
    private void initViews(View view) {
        //导航页
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        // 设置离线数为3
        viewPager.setOffscreenPageLimit(3);

        adapter = new GankPagerAdapter(
                getChildFragmentManager(),
                androidFragment,
                videoFragment,
                meiziFragment);

        viewPager.setAdapter(adapter);
        //关联数据
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
