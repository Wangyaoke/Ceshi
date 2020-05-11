package com.ansiyida.cgjl.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ansiyida.cgjl.listener.IHttpStatues;
import com.ansiyida.cgjl.util.NetType;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.ButterKnife;

/**
 * Created by ansiyida on 2017/12/5.
 */
public abstract class BaseFragment extends Fragment implements IHttpStatues {
    protected Activity mActivity;
    public FirebaseAnalytics mFirebaseAnalytics;
    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        initTheam();
        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);

        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    protected abstract void initTheam();

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData();


    public void httpCheck(){
        int apnType = NetWorkUtils.getAPNType(mActivity);
        onHttpStatues(apnType);
    }
    /**
     * 判断网络状态
     * */
    @Override
    public void onHttpStatues(int connectType) {
        if (connectType== NetType.TYPE_NO){

            ToastUtils.showMessage(getActivity(), "无网络连接");
        }else if (connectType==NetType.TYPE_WIFI){
            ToastUtils.showMessage(getActivity(),"Wifi连接");

        }
        else if (connectType== NetType.TYPE_2G){
            ToastUtils.showMessage(getActivity(),"2G连接");

        }else if (connectType==NetType.TYPE_3G){
            ToastUtils.showMessage(getActivity(),"3G连接");

        }else if (connectType==NetType.TYPE_4G){
            ToastUtils.showMessage(getActivity(),"4G连接");
        }
    }
}

