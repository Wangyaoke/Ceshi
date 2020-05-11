package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.SubscribeFragmentAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.DYListBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.FeedRootRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/11/8.
 */
public class SubscribeFragment extends BaseFragment {
    @Bind(R.id.recyclerView_newsFragment)
    FeedRootRecyclerView recyclerView;
    @Bind(R.id.xrefreshView_newsFragment)
    XRefreshView refreshView;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private int jst_id;
    private int pageNum;
    private ArrayList<Integer> isDYLists;
    private List<DYListBean.DataBean.ListBean> dataList;
    private SubscribeFragmentAdapter adapter;

    /**
     * 使用静态的内部类，不会持有当前对象的引用
     */
    private static class MyHandler extends Handler {
        private final WeakReference<SubscribeFragment> mActivity;

        public MyHandler(SubscribeFragment activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
            mActivity.get().todo(msg);
        }
    }

    /**
     * 处理消息
     *
     * @param msg 消息标记
     */
    private void todo(Message msg) {
        int what = msg.what;
    }

    public static SubscribeFragment newInstance(int position) {
        SubscribeFragment myFragment = new SubscribeFragment();
        myFragment.setPosition(position);
        return myFragment;
    }

    public void setPosition(int jst_id) {
        this.jst_id = jst_id;
    }

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_news_fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        loadingDialog.showDialog(getActivity(), "");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        LogUtil.i("length", "id:" + jst_id);

        notifyData();
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        isDYLists=new ArrayList<>();
        dataList=new ArrayList<>();
        adapter = new SubscribeFragmentAdapter(dataList, getActivity(), isDYLists);
        recyclerView.setAdapter(adapter);

    }


//    @OnClick({})
//    public void click(View view) {
//        switch (view.getId()) {
//
//        }
//    }
    @Override
    protected void initData() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                notifyData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                loadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindDrawables(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void notifyData() {
        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Call<DYListBean> call= MyApplication.getNetApi().getDYList(pageNum+"",jst_id+"",(String) SharedPreferenceUtils.get(getActivity(),"app_token",""));
            call.enqueue(new Callback<DYListBean>() {
                @Override
                public void onResponse(Call<DYListBean> call, Response<DYListBean> response) {
                    if (response.isSuccessful()){
                       if ("0001".equals(response.body().getStatus())){
                           DYListBean.DataBean data = response.body().getData();
                            if (data!=null){
                                List<DYListBean.DataBean.ListBean> list = data.getList();
                                isDYLists.clear();
                                for (DYListBean.DataBean.ListBean listBean:list){
                                    if ("Y".equals(listBean.getIsSubscribe())){
                                        isDYLists.add(1);
                                    }else {
                                        isDYLists.add(0);
                                    }
                                }
                                if (list!=null&&list.size()>0){
                                    dataList.clear();
                                    dataList.addAll(list);
                                    adapter.notifyDataSetChanged();
                                }else {

                                }
                            }

                       }
                    }
                    refreshView.stopRefresh();

                    call.cancel();
                }

                @Override
                public void onFailure(Call<DYListBean> call, Throwable t) {
                    refreshView.stopRefresh();
                    call.cancel();
                }
            });
        } else {
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            iv_repeat.setVisibility(View.VISIBLE);
            iv_empty.setVisibility(View.GONE);
//            if (loadingDialog != null && loadingDialog.isDialogShow()) {
//                loadingDialog.disMissDialog();
//            }
        }
    }

    private void loadMore() {
        pageNum++;
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Call<DYListBean> call= MyApplication.getNetApi().getDYList(pageNum+"",jst_id+"",(String) SharedPreferenceUtils.get(getActivity(),"app_token",""));
            call.enqueue(new Callback<DYListBean>() {
                @Override
                public void onResponse(Call<DYListBean> call, Response<DYListBean> response) {
                    if (response.isSuccessful()){
                        if ("0001".equals(response.body().getStatus())){
                            DYListBean.DataBean data = response.body().getData();
                            if (data!=null){
                                List<DYListBean.DataBean.ListBean> list = data.getList();
                                for (DYListBean.DataBean.ListBean listBean:list){
                                    if ("Y".equals(listBean.getIsSubscribe())){
                                        isDYLists.add(1);
                                    }else {
                                        isDYLists.add(0);
                                    }
                                }
                                if (list!=null&&list.size()>0){
                                    dataList.addAll(list);
                                    adapter.notifyDataSetChanged();
                                }else {
                                    ToastUtils.showMessage(getActivity(), "无数据可加载");
                                }
                            }

                        }
                    }
                    refreshView.stopLoadMore();
                    call.cancel();
                }

                @Override
                public void onFailure(Call<DYListBean> call, Throwable t) {
                    refreshView.stopLoadMore();
                    call.cancel();
                }
            });
        } else {
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopLoadMore();
            iv_repeat.setVisibility(View.VISIBLE);
            iv_empty.setVisibility(View.GONE);
//            if (loadingDialog != null && loadingDialog.isDialogShow()) {
//                loadingDialog.disMissDialog();
//            }
        }
    }


}
