package com.ansiyida.cgjl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ansiyida.cgjl.fragment.SubscribeFragment;

import java.util.ArrayList;

/**
 * Created by chenyaoxiang on 2017/11/8.
 */
public class SubscribeViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<SubscribeFragment> lists;
    private ArrayList<Integer> idList;
    public SubscribeViewPagerAdapter(FragmentManager fm, ArrayList<SubscribeFragment> lists, ArrayList<Integer> idList) {
        super(fm);
        this.lists=lists;
        this.idList=idList;
    }

    @Override
    public Fragment getItem(int position) {
        SubscribeFragment f =SubscribeFragment.newInstance(idList.get(position));
             return f;
         }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SubscribeFragment f = (SubscribeFragment) super.instantiateItem(container, position);
        return f;
    }
    @Override
    public int getCount() {
        return lists.size();
    }
}
