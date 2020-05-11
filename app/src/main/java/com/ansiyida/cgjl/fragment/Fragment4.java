package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseFragment;

/**
 * Created by ansiyida on 2017/11/7.
 */
public class Fragment4 extends BaseFragment {

    private FragmentActivity activity;

    @Override
    protected void initTheam() {
        activity = getActivity();
//        int model = (int) SharedPreferenceUtils.get(activity, "model", 0);
//        if (model == 0) {
//            activity.setTheme(R.style.AppTheme_day);
//
//        } else {
//            activity.setTheme(R.style.AppTheme_night);
//
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment4;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


    }

    @Override
    protected void initData() {

    }


}
