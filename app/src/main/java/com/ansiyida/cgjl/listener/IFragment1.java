package com.ansiyida.cgjl.listener;

import android.support.v7.widget.LinearLayoutManager;

import com.ansiyida.cgjl.adapter.Fragment1ViewPagerAdapter;
import com.ansiyida.cgjl.adapter.NewsTopAdapter;

/**
 * Created by ansiyida on 2017/11/30.
 */
public interface IFragment1 {
    void renderNewsTop(NewsTopAdapter adapter,LinearLayoutManager linearLayoutManager);
    void renderViewPager(Fragment1ViewPagerAdapter adapter,int localPosition);
}
