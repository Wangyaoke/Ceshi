package com.ansiyida.cgjl.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ansiyida.cgjl.adapter.NewsTopAdapter;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.fragment.Fragment1;
import com.ansiyida.cgjl.listener.IFragment1Presenter;

import java.util.ArrayList;

/**
 * Created by ansiyida on 2017/11/30.
 */
public class Fragment1Presenter implements IFragment1Presenter {
    private Fragment1 fragment1;
    private FragmentManager mannager;
    private FragmentActivity activity;
    private int localPosition;
    private ArrayList<String> list;
    private ArrayList<Integer> list2;
    private NewsTopAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DbMannager dbMannager;
    private ArrayList<Fragment> fragmentList;

    @Override
    public void renderTop() {

    }

    @Override
    public void renderViewpager() {

    }

    @Override
    public void showMenu() {

    }

    @Override
    public void pageMove(int position, RecyclerView recyclerView) {

    }

//    public Fragment1Presenter(Fragment1 fragment1) {
//        this.fragment1 = fragment1;
//        activity = fragment1.getActivity();
//        this.mannager = activity.getSupportFragmentManager();
//        dbMannager=DbMannager.getInstance();
//        fragmentList= new ArrayList<>();
//    }
//
//    @Override
//    public void renderTop() {
//        ArrayList<ChannelItem> choiceItem = dbMannager.getChoiceItem();
//        int length=choiceItem.size();
//        list = new ArrayList();
//        list2 = new ArrayList<Integer>();
//        for (int x=0;x<length;x++){
//            list.add(choiceItem.get(x).getName());
//            if (x==0){
//                list2.add(0);
//            }else {
//                list2.add(1);
//
//            }
//        }
//        adapter = new NewsTopAdapter(activity, list, list2, fragment1);
//        linearLayoutManager = new LinearLayoutManager(activity);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        fragment1.renderNewsTop(adapter, linearLayoutManager);
//    }
//
//    @Override
//    public void renderViewpager() {
//        fragmentList.clear();
//        ArrayList<ChannelItem> choiceItem = dbMannager.getChoiceItem();
//        int length=choiceItem.size();
//        for (int x = 0; x < length; x++) {
//            NewsFragment fragment = new NewsFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("index", x);
//            fragment.setArguments(bundle);
//            fragmentList.add(fragment);
//        }
//        Fragment1ViewPagerAdapter adapter = new Fragment1ViewPagerAdapter(mannager, fragmentList);
//        fragment1.renderViewPager(adapter,localPosition);
//
//    }
//
//    @Override
//    public void showMenu() {
//
//        activity.startActivity(new Intent(activity, ChannelActivity.class));
//    }
//
//    @Override
//    public void pageMove(int position,RecyclerView recyclerView) {
//        localPosition = position;
//
//        int length = list2.size();
//        for (int x = 0; x < length; x++) {
//            if (x != position) {
//                if (list2.get(x) == 0) {
//
//                    list2.remove(x);
//                    list2.add(x, 1);
//                }
//            } else {
//                list2.remove(x);
//                list2.add(x, 0);
//            }
//        }
//        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
//        int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
//
//        int left = recyclerView.getChildAt(position - firstPosition).getLeft();
//        int right = recyclerView.getChildAt(lastPosition - position).getLeft();
//        adapter.notifyDataSetChanged();
//        recyclerView.smoothScrollBy((left - right) / 2, 0);
//
//    }
}
