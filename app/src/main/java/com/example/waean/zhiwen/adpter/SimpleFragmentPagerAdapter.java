package com.example.waean.zhiwen.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.ViewGroup;

import com.example.waean.zhiwen.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waean on 2017/04/11.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;


    final int PAGE_COUNT = 10;
    private String tabTitles[] = new String[]{
            "头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"
    };
    public static final String typeTitles[] = new String[]{
            "top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"
    };
    private Context context;
    private FragmentManager fm;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fm = fm;
        list = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment pageFragment;

        if (list.size() <= position) {
            pageFragment = PageFragment.newInstance(position);
            list.add(pageFragment);
        } else {
            pageFragment = (PageFragment) list.get(position);
        }
        return pageFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();

        /*
        *
        *
        *
        *
        *
        * */
        /*
        *
        *
        * */

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        Fragment fragment = list.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }


}
