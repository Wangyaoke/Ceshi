package com.ansiyida.cgjl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.ansiyida.cgjl.fragment.DYNewFragment;
import com.ansiyida.cgjl.fragment.NewsFragment;

import java.util.ArrayList;

/**
 * Created by chenyaoxiang on 2017/11/8.
 */
public class Fragment1ChangeAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> lists;
    public Fragment1ChangeAdapter(FragmentManager fm, ArrayList<Fragment> lists) {
        super(fm);
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
//        NewsFragment f =NewsFragment.newInstance(position);
//             return f;
        if (position==0){
            DYNewFragment f1= new DYNewFragment();
            return f1;
        }else {
            NewsFragment f2= NewsFragment.newInstance(position);
            return f2;
        }
         }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        NewsFragment f = (NewsFragment) super.instantiateItem(container, position);
//        return f;
        if (position==0){
            DYNewFragment f1= (DYNewFragment) super.instantiateItem(container, position);
            return f1;

        }else {
            NewsFragment f2= (NewsFragment) super.instantiateItem(container, position);
            return f2;
        }
    }
    @Override
    public int getCount() {
        return lists.size();
    }
}
