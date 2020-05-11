package com.ansiyida.cgjl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ansiyida on 2017/11/8.
 */
public class Fragment1ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> lists;
    public Fragment1ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> lists) {
        super(fm);
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
