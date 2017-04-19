package com.eternal.look.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eternal.look.gank.android.AndroidFragment;
import com.eternal.look.gank.meizi.MeiziFragment;
import com.eternal.look.gank.video.VideoFragment;

/**
 * @author qiuyongheng
 * @time 2017/4/18  14:05
 * @desc ${TODD}
 */

public class GankPagerAdapter extends FragmentPagerAdapter {

    private final String[] title;
    private final AndroidFragment androidFragment;
    private final VideoFragment videoFragment;
    private final MeiziFragment meiziFragment;

    public GankPagerAdapter(FragmentManager fm, AndroidFragment androidFragment, VideoFragment videoFragment, MeiziFragment meiziFragment) {
        super(fm);
        title = new String[]{
                "安卓",
                "视频",
                "妹子"
        };
        this.androidFragment = androidFragment;
        this.videoFragment = videoFragment;
        this.meiziFragment = meiziFragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return androidFragment;
        } else if (position == 1) {
            return videoFragment;
        }
        return meiziFragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
