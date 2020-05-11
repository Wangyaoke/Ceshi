package com.ansiyida.cgjl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.LoginActivity;
import com.ansiyida.cgjl.activity.dy_setting_layout;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.minedy_bean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/6/14.
 * 订阅
 */
public class DYNewFragment extends BaseFragment implements NewsOneAdapter.IonSlidingViewClickListener {

    @Bind(R.id.tv_txt)
    TextView tv_txt;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.recyclerView_dyNewFragment)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_dyNewFragment)
    XRefreshView refreshView;
    @Bind(R.id.relative_empty)
    RelativeLayout relative_empty;
    @Bind(R.id.btn_dy)
    TextView btn_dy;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    private int pageNum = 0;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private FragmentActivity activity;
    private String mode = "purchaseInfo";
    private String[] tabList = new String[]{"招采信息","采购需求","涉密采购"};

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dy_new;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        lists = new ArrayList<>();
        activity = getActivity();
        adapter = new NewsOneAdapter(lists, activity, getActivity().getWindow());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //允许加载更多
        refreshView.setPullLoadEnable(false);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);

        //    refreshView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i <tabList.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(tabList[i]));
        }
        if (pageNum == 0) {
            refreshView.startRefresh();
        }
        initData();
        onclick();
    }

    private void onclick() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                adapter.setAllopen(false);
                adapter.notifyDataSetChanged();
            }
        });
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals("招采信息")){
                    mode="purchaseInfo";
                }else if(tab.getText().equals("采购需求")){
                    mode="purchaseDemand";
                }else if(tab.getText().equals("涉密采购")){
                    mode="purchaseSecret";
                }
                initData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {
        if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
            tablayout.setVisibility(View.VISIBLE);
            btn_dy.setText("设置订阅");
            if ("true".equals((String) SharedPreferenceUtils.get(getActivity(), "isActive", ""))) {
                httpData();
                refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷
                        //mHandler.sendEmptyMessageDelayed(200, 1000);
                        httpData();
                    }

                    @Override
                    public void onRefresh(boolean isPullDown) {

                    }

                    @Override
                    public void onLoadMore(boolean isSilence) {
                        //上拉加载
                        httpLoadMore();
                    }

                    @Override
                    public void onRelease(float direction) {

                    }

                    @Override
                    public void onHeaderMove(double headerMovePercent, int offsetY) {

                    }
                });
            } else {
                refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                    @Override
                    public void onRefresh() {
                        //下拉刷新
                        httpData();
                    }

                    @Override
                    public void onRefresh(boolean isPullDown) {

                    }

                    @Override
                    public void onLoadMore(boolean isSilence) {
                        //上拉加载
                        httpLoadMore();
                    }

                    @Override
                    public void onRelease(float direction) {

                    }

                    @Override
                    public void onHeaderMove(double headerMovePercent, int offsetY) {

                    }
                });
            }
        } else {
            btn_dy.setText("请先登录");
            tablayout.setVisibility(View.GONE);
            refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {
                    //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                    httpData();
                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    //上拉加载
                    httpLoadMore();
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });
        }
    }

    public void refresgdate() {
        initData();
        httpData();
    }

    protected void httpData() {
        if ("true".equals((String) SharedPreferenceUtils.get(getActivity(), "isActive", ""))) {
            pageNum = 1;
            if (NetWorkUtils.isNetworkConnected(getActivity())) {
                Call<minedy_bean> call = MyApplication.getNetApi().get_dy((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20");
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<minedy_bean>() {
                    @Override
                    public void onResponse(Call<minedy_bean> call, Response<minedy_bean> response) {
                        if (response.isSuccessful()) {
                            minedy_bean body = response.body();
                            //  caigoulist body = response.body();
                            lists.clear();
                            if ("200".equals(body.getStatus())) {
                                List<minedy_bean.DataBean.list_dy> list = body.getData().getlist_dy_bean();

                                if (list != null && list.size() > 0) {
                                    int i = 0;
                                    for (minedy_bean.DataBean.list_dy listBean : list) {
                                        if (mode.equals(listBean.getgenre())) {
                                            i++;
                                            NewBean2 newBean = new NewBean2();
                                            newBean.setArtype("DY");
                                            if (listBean.getcreateTime() == null)
                                                newBean.setTime("");
                                            else
                                                newBean.setTime(TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                            newBean.setId(listBean.getid());
                                            newBean.setTitle(listBean.getname());
                                            newBean.setgenre(listBean.getgenre());
                                            if (listBean.getsource() == null)
                                                newBean.setsource("");
                                            else
                                                newBean.setsource(listBean.getsource());
                                            if (listBean.gettype() == null)
                                                newBean.setContent("");
                                            else
                                                newBean.setContent(listBean.gettype());
                                            if (listBean.getaddress() == null)
                                                newBean.setaddress("");
                                            else
                                                newBean.setaddress(listBean.getaddress());
                                            if (listBean.getprovince() == null)
                                                newBean.setprovince("");
                                            else
                                                newBean.setprovince(listBean.getprovince());
                                            newBean.setupdateNum(listBean.getupdateNum());
                                            if (listBean.getprovince() == null)
                                                newBean.setAuthor(listBean.getaddress());
                                            else
                                                newBean.setAuthor(listBean.getprovince() + listBean.getaddress());
                                            if (listBean.getkeyword() == null)
                                                newBean.setLable("");
                                            else
                                                newBean.setLable(listBean.getkeyword());
                                            lists.add(newBean);
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                    iv_empty.setVisibility(View.GONE);
                                    btn_dy.setVisibility(View.GONE);
                                    tv_txt.setVisibility(View.GONE);
                                    refreshView.setPullLoadEnable(true);
                                    //允许下拉刷新
                                    refreshView.setPullRefreshEnable(true);
                                    if (i == 0) {
                                        iv_empty.setVisibility(View.VISIBLE);
                                        tv_txt.setVisibility(View.VISIBLE);
                                    } else {
                                        iv_empty.setVisibility(View.GONE);
                                        tv_txt.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                refreshView.setPullLoadEnable(false);
                                //允许下拉刷新
                                refreshView.setPullRefreshEnable(false);
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.VISIBLE);
                                btn_dy.setVisibility(View.VISIBLE);
                                tv_txt.setVisibility(View.VISIBLE);
                                //    if("没有数据".equals(body.getMessage()))
                                //   ToastUtils.showMessage(getActivity(),body.getMessage());
                            }

                        }

                        refreshView.stopRefresh();
                        iv_repeat.setVisibility(View.GONE);
                        iv_repeat.setClickable(false);
                        call.cancel();

                    }

                    @Override
                    public void onFailure(Call<minedy_bean> call, Throwable t) {
                        call.cancel();
                        iv_empty.setVisibility(View.VISIBLE);
                        btn_dy.setVisibility(View.VISIBLE);
                        refreshView.stopRefresh();
                        ToastUtils.showMessage(getActivity(), t.toString());

                    }
                });


            } else {
                iv_repeat.setVisibility(View.VISIBLE);
                ToastUtils.showMessage(getActivity(), "当前网络不可用");
                iv_repeat.setClickable(true);
                refreshView.stopRefresh();
            }
        }
    }

    private void httpLoadMore() {
        if ("true".equals((String) SharedPreferenceUtils.get(getActivity(), "isActive", ""))) {
            refreshView.stopLoadMore();
            pageNum++;
            Call<minedy_bean> call = MyApplication.getNetApi().get_dy((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20");
            call.enqueue(new Callback<minedy_bean>() {
                @Override
                public void onResponse(Call<minedy_bean> call, Response<minedy_bean> response) {
                    if (response.isSuccessful()) {
                        minedy_bean body = response.body();
                        if ("200".equals(body.getStatus())) {
                            List<minedy_bean.DataBean.list_dy> list = body.getData().getlist_dy_bean();
                            if (list != null && list.size() > 0) {
                                int i = 0;
                                LogUtil.i("yantao", "length:" + list.size());
                                //  lists.clear();
                                for (minedy_bean.DataBean.list_dy listBean : list) {
                                    if (mode.equals(listBean.getgenre())) {
                                        i++;
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("DY");
                                        newBean.setId(listBean.getid());
                                        newBean.setTitle(listBean.getname());
                                        newBean.setgenre(listBean.getsource());
                                        newBean.setContent(listBean.gettype());
                                        newBean.setAuthor(listBean.getprovince() + listBean.getaddress());
                                        newBean.setLable(listBean.getkeyword());

                                        lists.add(newBean);
                                    }
                                }

                                adapter.notifyDataSetChanged();
                                relative_empty.setVisibility(View.GONE);
                            } else {
                                // ToastUtils.showMessage(getActivity(), "已加载到底部");
                                pageNum--;
                            }
                        } else {
                            //  ToastUtils.showMessage(getActivity(), "已加载到底部");
                            pageNum--;
                        }

                    }
                    refreshView.stopLoadMore();
                    call.cancel();
                }

                @Override
                public void onFailure(Call<minedy_bean> call, Throwable t) {
                    call.cancel();
                    refreshView.stopLoadMore();
                    ToastUtils.showMessage(getActivity(), t.toString());
                }
            });
        }
    }

    @OnClick({R.id.btn_dy, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_dy:                   //1.马上订阅按钮
                if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
                    btn_dy.setText("设置订阅");
                    if ("true".equals((String) SharedPreferenceUtils.get(getActivity(), "isActive", ""))) {
                        //  startActivity(new Intent(getActivity(),dy_setting_layout.class));}
                        this.startActivityForResult(new Intent(getActivity(), dy_setting_layout.class), ActivityCodeUtil.DY);
                    } else
                        ToastUtils.showMessage(getActivity(), "请充值成为会员！");
                } else {
                    //      btn_dy.setText("请先登录");
                    this.startActivityForResult(new Intent(getActivity(), LoginActivity.class), ActivityCodeUtil.DY);
                }
                break;
            case R.id.text_putOut:                   //1.马上订阅按钮
                if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
                    if ("true".equals((String) SharedPreferenceUtils.get(getActivity(), "isActive", ""))) {
                        this.startActivityForResult(new Intent(getActivity(), dy_setting_layout.class), ActivityCodeUtil.DY);
                    } else
                        ToastUtils.showMessage(getActivity(), "请充值成为会员！");
                } else
                    ToastUtils.showMessage(getActivity(), "请先登录！");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(pageNum==1) {
            initData();
        }
    }

    /**
     * 点击tab刷新当前页面
     */
    public void btnRefresh() {
        if (refreshView != null) {
            refreshView.startRefresh();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
