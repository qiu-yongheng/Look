package com.eternal.look.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ZhihuFragment zhihuFragment;
    private NewsFragment newsFragment;
    private MeiziFragment meiziFragment;
    private long exitTime = 0;
    private SwitchCompat mThemeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /** 1. 初始化控件 */
        initView();
        /** 2. 初始化fragment, 添加到activity */
        initFragment(savedInstanceState);
        /** 3. 实例化BookmarksPresenter*/

        /** 4. 默认显示知乎内容*/
        switchFragment(zhihuFragment);
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
        mThemeSwitch = (SwitchCompat) MenuItemCompat.getActionView(navView.getMenu().findItem(R.id.nav_theme)).findViewById(R.id.view_switch);
        mThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mThemeSwitch.setChecked(isChecked);
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "打开夜间模式", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "关闭夜间模式", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 按返回键关闭drawerLayout
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再点一次，退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * 创建选择菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 设置选择菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            goAboutActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 跳转到关于界面
     */
    private void goAboutActivity() {

    }

    /**
     * drawerLayout菜单的点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_zhihu:
                switchFragment(zhihuFragment);
                break;
            case R.id.nav_news:
                switchFragment(newsFragment);
                break;
            case R.id.nav_meizi:
                switchFragment(meiziFragment);
                break;
            case R.id.nav_theme:
                break;
            case R.id.nav_setting:
                break;
        }
        // start表示左边
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 切换显示Fragemnt
     */
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, fragment)
                .commit();
        if (fragment.equals(zhihuFragment)) {
            toolbar.setTitle(R.string.title_zhihu);
        } else if (fragment.equals(newsFragment)) {
            toolbar.setTitle(R.string.title_news);
        } else if (fragment.equals(meiziFragment)) {
            toolbar.setTitle(R.string.title_meizi);
        }
    }


    /**
     * 保存状态
     *
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
