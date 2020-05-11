package com.ansiyida.cgjl.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.CitylocationActivity;
import com.ansiyida.cgjl.activity.ClassifiedActivity;
import com.ansiyida.cgjl.activity.ClassifiedActivity1;
import com.ansiyida.cgjl.activity.InterstingTabActivity;
import com.ansiyida.cgjl.activity.LoginActivity;
import com.ansiyida.cgjl.activity.bidding_class_activity1;
import com.ansiyida.cgjl.activity.cgjl_activity.CgjlMore_Activity;
import com.ansiyida.cgjl.activity.cgjl_activity.EnterprisesRecommendoneselfActivity;
import com.ansiyida.cgjl.activity.cpml_activity;
import com.ansiyida.cgjl.activity.qyml_activity;
import com.ansiyida.cgjl.activity.recom_self_activity;
import com.ansiyida.cgjl.activity.yzfx_activity;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.ViewpointBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ShouYeBannerBean;
import com.ansiyida.cgjl.bean.todayUpdateCountBean;
import com.ansiyida.cgjl.city.provinceList;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.ansiyida.cgjl.view.NoPreloadViewPager;

/**
 * Created by ansiyida on 2017/11/7.
 * 首页模块
 */
public class Fragment1 extends BaseFragment {

    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.more)
    TextView more;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.icon1_mainAcitivity)
    RadioButton icon1MainAcitivity;
    @Bind(R.id.icon2_mainAcitivity)
    RadioButton icon2MainAcitivity;
    @Bind(R.id.icon3_mainAcitivity)
    RadioButton icon3MainAcitivity;
    @Bind(R.id.icon4_mainAcitivity)
    RadioButton icon4MainAcitivity;
    @Bind(R.id.line_visibility)
    LinearLayout line_visibility;
    ImageView textview3;
    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.point1)
    RelativeLayout point;
    @Bind(R.id.todaycount)
    TextView todaycount_text;
    @Bind(R.id.home_flyBanner)
    FlyBanner homeFlyBanner;
    @Bind(R.id.iv_1)
    TextView etxt_city;
    @Bind(R.id.recyclerView_fragment1)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_fragment1)
    SmartRefreshLayout refreshView;
    @Bind(R.id.tv_hotText)
    TextView tv_hotText;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;

    public String chuan = "";
    public String all_county = "";
    public String loctioncity = "";
    public Boolean up_or_down = null;
    private String up_str = "";
    private String down_str = "";
    private static final int REQUEST_CODE_PICK_CITY = 100;
    private static final int REQUEST_CODE_zb = 1;
    private static final int REQUEST_CODE_channel = 2;
    private String todaycount = "";
    private int localPosition = 0;
    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private ArrayList<NewBean2> lists1;
    private int pageNum = 1;
    private int pageCount = 10;
    private String city_Serch = "";
    private boolean newstate;
    private ArrayList<View> pageview;
    private PagerAdapter mPagerAdapter;
    private int bj = 0;
    private Drawable drawable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment1;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //获取轮播图
        getBanner();
        activity = getActivity();
        lists = new ArrayList<>();
        adapter = new NewsOneAdapter(lists, activity, getActivity().getWindow());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        getToDayCount();
        RadioButton[] rbs = new RadioButton[5];
        refreshView.setHeaderHeight(50);
        refreshView.setFooterHeight(50);

    }


    //每天的消息
    private void getToDayCount() {
        Call<todayUpdateCountBean> call = MyApplication.getNetApi().gettodayUpdateCount();
        call.enqueue(new Callback<todayUpdateCountBean>() {
            @Override
            public void onResponse(Call<todayUpdateCountBean> call, Response<todayUpdateCountBean> response) {
                if (response.isSuccessful()) {
                    todayUpdateCountBean body = response.body();
                    if (body != null) {
                        try {
                            todaycount = body.getData();
                            todaycount_text.setText("今天新增" + todaycount + "条信息");
                        } catch (Exception e) {
                        }
                    }
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<todayUpdateCountBean> call, Throwable t) {
                call.cancel();
            }
        });
    }

    //获取轮播图
    private void getBanner() {
        String app_token = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
        Call<ShouYeBannerBean> call = MyApplication.getNetApi().getadInfo(SharedPreferenceUtils.get(getActivity(), "app_token", ""), "banner");
        call.enqueue(new Callback<ShouYeBannerBean>() {
            @Override
            public void onResponse(Call<ShouYeBannerBean> call, Response<ShouYeBannerBean> response) {
                if (response.body() != null) {
                    ShouYeBannerBean body = response.body();
                    final List<ShouYeBannerBean.DataBean> data = body.getData();
                    //给轮播图赋值
                    //存储图片的集合
                    final List<String> bannerList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        bannerList.add(data.get(i).getImg());
                    }
                    //banner设置
                    homeFlyBanner.setImagesUrl(bannerList);
                    //banner监听点击的图片
                    homeFlyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            String app_token1 = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
                            if (data.get(position).getTarget().equals("业主分析")) {
                                Intent intent_read = new Intent(getActivity(), yzfx_activity.class);
                                getActivity().startActivity(intent_read);
                            } else if (data.get(position).getTarget().equals("产品自荐")) {
                                if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
                                    Intent intent = new Intent(getActivity(), recom_self_activity.class);
                                    getActivity().startActivity(intent);
                                } else {
                                    ToastUtils.showMessage(getActivity(), "请先登录");
                                }
                            } else if (data.get(position).getTarget().equals("企业自荐")) {
                                if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
                                    Intent intent = new Intent(getActivity(), EnterprisesRecommendoneselfActivity.class);
                                    getActivity().startActivity(intent);
                                } else {
                                    ToastUtils.showMessage(getActivity(), "请先登录");
                                }
                            } else if (data.get(position).getTarget().equals("订阅")) {
                                if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
                                    MainActivity activity = (MainActivity) getActivity();
                                    activity.addFragment();
                                } else {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }
                            }
                        }
                    });

                } else {
                    Log.e("fragment1", "onResponse:没有数据 ");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<ShouYeBannerBean> call, Throwable t) {
                ToastUtils.showMessage(getActivity(), t.getMessage());
            }
        });
    }

    public void refresh_toale() {
        getToDayCount();
    }

    public void setlocation() {
        try {
            if (!loctioncity.isEmpty()) {
                etxt_city.setText(loctioncity);
            }
        } catch (Exception e) {

        }

    }

    @Override
    protected void initData() {
        httpData();
        refresh();
    }

    public void refreshLocal() {
        httpLoadresume();
        refresh_toale();
    }


    private void refresh() {
        refreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                httpLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                httpData();
            }
        });


    }

    public void inthttpdate() {
        httpData();
    }

    private void httpData() {

        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            if ("全国".equals((String) SharedPreferenceUtils.get(getActivity(), "city_loction", ""))) {
                city_Serch = "";
                all_county = "";
            } else {
                city_Serch = (String) SharedPreferenceUtils.get(getActivity(), "city_loction", "");
                all_county = "";
            }
            Call<ViewpointBean> call = MyApplication.getNetApi().getviewpoint((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", "");
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<ViewpointBean>() {
                @Override
                public void onResponse(Call<ViewpointBean> call, Response<ViewpointBean> response) {
                    if (response.isSuccessful()) {
                        ViewpointBean body = response.body();
                        boolean equals = body.getData().equals("");

                        if (body != null && !equals) {
                            List<ViewpointBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (ViewpointBean.DataBean.list_law_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("VP");
                                    newBean.setContent(listBean.gettitle());
                                    newBean.setId(listBean.getid());
                                    Log.e("fragment1", "onResponse: " + listBean.getid());
                                    newBean.setImg(listBean.getimg());
                                    newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    newBean.setsource("来源：" + listBean.gettypes());
                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            } else {
                                LogUtil.i("yantao", "else");
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.GONE);
                                lists.clear();

                                adapter.notifyDataSetChanged();
                            }

                        } else {
                            iv_empty.setVisibility(View.GONE);
                            tv_txt_no.setVisibility(View.GONE);
                            lists.clear();
                            adapter.notifyDataSetChanged();

                        }

                    } else {
                        iv_empty.setVisibility(View.GONE);
                        tv_txt_no.setVisibility(View.GONE);
                        lists.clear();
                        adapter.notifyDataSetChanged();
                    }
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);

                    call.cancel();
                }

                @Override
                public void onFailure(Call<ViewpointBean> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    LogUtil.i("yantao", "Gone--2");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);

                }
            });
        } else {
            lists.clear();
            adapter.notifyDataSetChanged();
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);

        }
        refreshView.finishRefresh();
    }

    private void httpLoadresume() {
        int size;

        if ("全国".equals((String) SharedPreferenceUtils.get(getActivity(), "city_loction", ""))) {
            city_Serch = "";
            all_county = "";
        } else {
            city_Serch = (String) SharedPreferenceUtils.get(getActivity(), "city_loction", "");
            all_county = "";
        }
        if (lists != null) {
            if (lists.size() != 0)
                size = lists.size() - 1;
            else
                size = 20;
        } else
            size = 20;
        Call<ViewpointBean> call = MyApplication.getNetApi().getviewpoint((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), "1", size + "", "");
        call.enqueue(new Callback<ViewpointBean>() {
            @Override
            public void onResponse(Call<ViewpointBean> call, Response<ViewpointBean> response) {
                if (response.isSuccessful()) {
                    ViewpointBean body = response.body();
                    List<ViewpointBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                        lists.clear();
                        for (ViewpointBean.DataBean.list_law_bean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("VP");
                            newBean.setContent(listBean.gettitle());
                            newBean.setId(listBean.getid());
                            newBean.setImg(listBean.getimg());
                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                            newBean.setsource("来源：" + listBean.gettypes());
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<ViewpointBean> call, Throwable t) {
                call.cancel();
                LogUtil.i("yantao", "onFailure");

            }
        });
        refreshView.finishLoadMore();
    }

    private void httpLoadMore() {

        pageNum++;
        if ("全国".equals((String) SharedPreferenceUtils.get(getActivity(), "city_loction", ""))) {
            city_Serch = "";
            all_county = "";
        } else {
            city_Serch = (String) SharedPreferenceUtils.get(getActivity(), "city_loction", "");
            all_county = "";
        }

        Call<ViewpointBean> call = MyApplication.getNetApi().getviewpoint((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", "");
        call.enqueue(new Callback<ViewpointBean>() {
            @Override
            public void onResponse(Call<ViewpointBean> call, Response<ViewpointBean> response) {
                if (response.isSuccessful()) {
                    ViewpointBean body = response.body();
                    List<ViewpointBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());

                        for (ViewpointBean.DataBean.list_law_bean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("VP");

                            newBean.setContent(listBean.gettitle());

                            newBean.setId(listBean.getid());

                            newBean.setImg(listBean.getimg());
                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                            newBean.setsource("来源：" + listBean.gettypes());
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);
                    } else {
                        pageNum--;
                    }

                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<ViewpointBean> call, Throwable t) {
                call.cancel();
                LogUtil.i("yantao", "onFailure");
            }
        });
        refreshView.finishLoadMore();
    }

    @OnClick({R.id.more, R.id.iv_back, R.id.relative_search_bg, R.id.iv_1, R.id.point_1, R.id.icon5_mainAcitivity, R.id.icon2_mainAcitivity, R.id.icon1_mainAcitivity, R.id.icon3_mainAcitivity, R.id.icon4_mainAcitivity, R.id.line_qiye, R.id.line_cpml})
//
    public void menuClick(View view) {
        Intent intent_read;
        Intent intent = new Intent(getContext(), CgjlMore_Activity.class);
        intent.putExtra("title", "精灵智库");
        switch (view.getId()) {
            case R.id.more:
                getContext().startActivity(intent);
                break;
            case R.id.iv_back:
                getContext().startActivity(intent);
                break;
            case R.id.relative_search_bg:                   //3.搜索
                //this.startActivityForResult(new Intent(getActivity(), Fragment3.class), REQUEST_CODE_PICK_CITY);
                MainActivity activity1 = (MainActivity) getActivity();
                activity1.choiceSearch();

                break;
            case R.id.point_1:                   //4.地址
                intent_read = new Intent(getActivity(), CitylocationActivity.class);
                intent_read.putExtra("city_loction_static", (String) SharedPreferenceUtils.get(activity, "city_loction", ""));
                this.startActivityForResult(intent_read, REQUEST_CODE_PICK_CITY);

                break;
            case R.id.iv_1:                   //4.地址
                intent_read = new Intent(getActivity(), CitylocationActivity.class);
                intent_read.putExtra("city_loction_static", (String) SharedPreferenceUtils.get(activity, "city_loction", ""));
                this.startActivityForResult(intent_read, REQUEST_CODE_PICK_CITY);

                break;
            case R.id.icon5_mainAcitivity:                   //4.地址
                this.startActivityForResult(new Intent(getActivity(), provinceList.class), REQUEST_CODE_zb);

                break;

            case R.id.icon2_mainAcitivity:                   //4.地址
                intent_read = new Intent(getActivity(), ClassifiedActivity.class);
                intent_read.putExtra("title", "采购需求");
                startActivityForResult(intent_read, REQUEST_CODE_zb);
                break;
            case R.id.icon1_mainAcitivity:
                intent_read = new Intent(getActivity(), bidding_class_activity1.class);
                intent_read.putExtra("title", "公开招标");
                startActivityForResult(intent_read, REQUEST_CODE_zb);
                break;
            case R.id.line_cpml:
                intent_read = new Intent(getActivity(), cpml_activity.class);
                getActivity().startActivity(intent_read);
                break;
            case R.id.line_qiye:
                intent_read = new Intent(getActivity(), qyml_activity.class);
                getActivity().startActivity(intent_read);
                //  startActivityForResult(intent_read, REQUEST_CODE_zb);
                break;
            case R.id.icon3_mainAcitivity:
                intent_read = new Intent(getActivity(), ClassifiedActivity1.class);
                intent_read.putExtra("title", "涉密采购");
                startActivityForResult(intent_read, REQUEST_CODE_zb);
                break;
            case R.id.icon4_mainAcitivity:
                intent_read = new Intent(getActivity(), bidding_class_activity1.class);
                intent_read.putExtra("title", "单一来源公示");
                startActivityForResult(intent_read, REQUEST_CODE_zb);
                break;
            default:
                break;
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY) {
            if (data != null) {

                String city = data.getStringExtra("city_loction");// data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                SharedPreferenceUtils.put(getActivity(), "city_loction", city);
                etxt_city.setText(city);
                httpData();
            }
        }
        if (requestCode == 1) {

            etxt_city.setText((String) SharedPreferenceUtils.get(getActivity(), "city_loction", ""));
            httpData();

        } else if (requestCode == ActivityCodeUtil.OPTION)
            chuan = data.getStringExtra("option");

    }

    /**
     * 下拉菜单
     */
    private PopupWindow popupWindow1;
    private View contentView1;

    private void initPopuWindow_dropMenu() {
        final Activity activity = getActivity();
        if (popupWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView1 = mLayoutInflater.inflate(R.layout.saoyisao_layout, null);
            popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        LinearLayout sao = (LinearLayout) contentView1.findViewById(R.id.linear_sao);
        LinearLayout interest = (LinearLayout) contentView1.findViewById(R.id.linear_interest);
        LinearLayout net = (LinearLayout) contentView1.findViewById(R.id.linear_net);
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫一扫按钮
                ToastUtils.showMessage(getActivity(), "功能未开放");

            }
        });
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //兴趣按钮
                activity.startActivity(new Intent(activity, InterstingTabActivity.class));
                popupWindow1.dismiss();
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //网址提交按钮
                popupWindow1.dismiss();
                initPopuWindow_net();
            }
        });

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        //   popupWindow1.showAsDropDown(saoYiSao);
        popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
        popupWindow1.update();
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }


    /**
     * 网址提交
     */
    private PopupWindow popupWindow_net;
    private View contentView_net;

    private void initPopuWindow_net() {
        final Activity activity = getActivity();
        if (popupWindow_net == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView_net = mLayoutInflater.inflate(R.layout.popwindow_net, null);
            popupWindow_net = new PopupWindow(contentView_net, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        Button btn_cancel = (Button) contentView_net.findViewById(R.id.btn_cancel);
        Button btn_submit = (Button) contentView_net.findViewById(R.id.btn_submit);
        RelativeLayout relativeLayout = (RelativeLayout) contentView_net.findViewById(R.id.relative);
        LinearLayout linear = (LinearLayout) contentView_net.findViewById(R.id.linear);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_net.dismiss();
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final EditText edit_1 = (EditText) contentView_net.findViewById(R.id.edit_1);
        final EditText edit_2 = (EditText) contentView_net.findViewById(R.id.edit_2);
        final EditText edit_3 = (EditText) contentView_net.findViewById(R.id.edit_3);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_net.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = EditTextUtil.getEditTextStr(edit_1);
                String str_url = EditTextUtil.getEditTextStr(edit_2);
                String str_email = EditTextUtil.getEditTextStr(edit_3);
                if (!"".equals(str_name) || !"".equals(str_url)) {
                    Call<DefaultBean2> call = MyApplication.getNetApi().uploadUrl(str_name, str_url, str_email, (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
                    call.enqueue(new Callback<DefaultBean2>() {
                        @Override
                        public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                            if (response.isSuccessful()) {
                                ToastUtils.showMessage(activity, "提交成功");
                            }
                            popupWindow_net.dismiss();
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean2> call, Throwable t) {
                            ToastUtils.showMessage(activity, "提交失败");
                            popupWindow_net.dismiss();
                            call.cancel();
                        }
                    });
                } else {
                    ToastUtils.showMessage(activity, "请填写网站名称或网址");
                }
            }
        });

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow_net.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_net.setOutsideTouchable(true);
        popupWindow_net.setFocusable(true);
        popupWindow_net.showAtLocation(contentView_net, Gravity.CENTER, 0, 0);
        popupWindow_net.update();
        popupWindow_net.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 不感兴趣弹窗
     */
    private PopupWindow popupWindow_dismiss;
    private View contentView_dismiss;
    private int choiceNum = 0;

    public void initPopuWindow_dismiss() {
        final Activity activity = getActivity();
        if (popupWindow_dismiss == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView_dismiss = mLayoutInflater.inflate(R.layout.popwindow_dismiss, null);
            popupWindow_dismiss = new PopupWindow(contentView_dismiss, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        CheckBox cb1 = (CheckBox) contentView_dismiss.findViewById(R.id.cb1);                     //不感兴趣
        CheckBox cb2 = (CheckBox) contentView_dismiss.findViewById(R.id.cb2);                     //内容质量差
        CheckBox cb3 = (CheckBox) contentView_dismiss.findViewById(R.id.cb3);                     //来源：联合早报
        CheckBox cb4 = (CheckBox) contentView_dismiss.findViewById(R.id.cb4);                     //内容太水
        CheckBox cb5 = (CheckBox) contentView_dismiss.findViewById(R.id.cb5);                     //重复推荐
        CheckBox cb6 = (CheckBox) contentView_dismiss.findViewById(R.id.cb6);                     //不想看：选美

        final TextView sure = (TextView) contentView_dismiss.findViewById(R.id.btn_sure);                   //确定按钮
        final TextView choiceCount = (TextView) contentView_dismiss.findViewById(R.id.tv_choiceCount);  //已选个数
        final TextView txt_title = (TextView) contentView_dismiss.findViewById(R.id.txt_title);         //弹框标题

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choiceNum++;
                } else {
                    choiceNum--;
                }
                if (choiceNum == 0) {
                    sure.setVisibility(View.GONE);
                    choiceCount.setVisibility(View.GONE);
                    txt_title.setVisibility(View.VISIBLE);
                } else {
                    sure.setVisibility(View.VISIBLE);
                    choiceCount.setVisibility(View.VISIBLE);
                    txt_title.setVisibility(View.GONE);
                    choiceCount.setText("已选" + choiceNum + "个理由");
                }
            }
        };
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_dismiss.dismiss();
            }
        });
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);
        cb6.setChecked(false);
        choiceNum = 0;
        sure.setVisibility(View.GONE);
        choiceCount.setVisibility(View.GONE);
        txt_title.setVisibility(View.VISIBLE);
        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);
        cb4.setOnCheckedChangeListener(listener);
        cb5.setOnCheckedChangeListener(listener);
        cb6.setOnCheckedChangeListener(listener);

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow_dismiss.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_dismiss.setOutsideTouchable(true);
        popupWindow_dismiss.setFocusable(true);
        popupWindow_dismiss.showAtLocation(contentView_dismiss, Gravity.CENTER, 0, 0);
        popupWindow_dismiss.update();
        popupWindow_dismiss.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
