package com.eternal.look.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eternal.look.R;
import com.eternal.look.meizi.MeiziFragment;
import com.eternal.look.news.NewsFragment;
import com.eternal.look.zhihu.ZhihuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ZhihuFragment zhihuFragment;
    private NewsFragment newsFragment;
    private MeiziFragment meiziFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /** 1. 初始化控件 */
        initView();
        /** 2. 初始化fragment, 添加到activity */
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getSupportFragmentManager().getFragment(savedInstanceState, "");
            getSupportFragmentManager().getFragment(savedInstanceState, "");
            getSupportFragmentManager().getFragment(savedInstanceState, "");
        } else {
            zhihuFragment = new ZhihuFragment();
            newsFragment = new NewsFragment();
            meiziFragment = new MeiziFragment();
        }

        if (!zhihuFragment.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add()
        }
    }

    private void initView() {
        // 替换actionbar
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
    }

    /**
     * 按返回键关闭drawerLayout
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 创建选择菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 设置选择菜单的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * drawerLayout菜单的点击事件
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        // start表示左边
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 保存状态
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (zhihuFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "ZhihuFragment", zhihuFragment);
        }
        if (newsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "NewsFragment", newsFragment);
        }
        if (meiziFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "MeiziFragment", meiziFragment);
        }
    }
}
