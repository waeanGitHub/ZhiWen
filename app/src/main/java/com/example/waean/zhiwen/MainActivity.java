package com.example.waean.zhiwen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.waean.zhiwen.adpter.SimpleFragmentPagerAdapter;
import com.example.waean.zhiwen.utils.ActivityCollector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.sliding_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
//    @Bind(R.id.swipe_refresh)
//    SwipeRefreshLayout mSwipeRefresh;

    private SimpleFragmentPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu);
        }
        mNavigationView.setCheckedItem(R.id.nav_home);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //menu菜单项改变逻辑
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_collect:
                        mDrawerLayout.closeDrawers();
                        Intent collect_intent = new Intent(MainActivity.this, CollectActivity.class);
                        startActivity(collect_intent);
//                        Toast.makeText(MainActivity.this, "soonner...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        mDrawerLayout.closeDrawers();
                        Intent about_intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(about_intent);
//                        Toast.makeText(MainActivity.this, "soonner...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_set:
                        Toast.makeText(MainActivity.this, "soonner...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_quit:
                        ActivityCollector.finishAll();
                        break;
                }
                return true;
            }
        });

        //设置Tablayout
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewpager.setAdapter(pagerAdapter);

        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
