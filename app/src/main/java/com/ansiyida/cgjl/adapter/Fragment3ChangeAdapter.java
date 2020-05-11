package com.ansiyida.cgjl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ansiyida.cgjl.fragment.SearchFragment;

import java.util.ArrayList;

/**
 * Created by chenyaoxiang on 2017/11/8.
 */
public class Fragment3ChangeAdapter extends FragmentPagerAdapter {
    private ArrayList<SearchFragment> lists;
    public Fragment3ChangeAdapter(FragmentManager fm, ArrayList<SearchFragment> lists) {
        super(fm);
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
        SearchFragment f =lists.get(position);
             return f;
         }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SearchFragment f = (SearchFragment) super.instantiateItem(container, position);
        return f;
    }
    @Override
    public int getCount() {
        return lists.size();
    }
}
