package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.CgjlMore_Adapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.ViewpointBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CgjlMore_Activity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.recyclerView_spinner_zb)
    RecyclerView recyclerViewSpinnerZb;
    @Bind(R.id.xrefreshView_spinner_zb)
    SmartRefreshLayout xrefreshViewSpinnerZb;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_txt_no)
    TextView tvTxtNo;
    @Bind(R.id.iv_repeat)
    ImageView ivRepeat;
    @Bind(R.id.relative_fragment_lable)
    RelativeLayout relativeFragmentLable;
    private CgjlMore_Adapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(CgjlMore_Activity.this, "Android精灵智库", "CgjlMore_Activity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cgjl_more;
    }

    @Override
    protected void initView() {
        activity = this;
        lists = new ArrayList<>();
        adapter = new CgjlMore_Adapter(lists, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSpinnerZb.setLayoutManager(mLayoutManager);
        recyclerViewSpinnerZb.setAdapter(adapter);
        xrefreshViewSpinnerZb.setHeaderHeight(50);
        xrefreshViewSpinnerZb.setFooterHeight(50);
    }


    @Override
    protected void initData() {
        refresh();
        textTitle.setText("精灵智库");
    }

    private void refresh() {
        xrefreshViewSpinnerZb.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉加载
                httpLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                httpData_RecyclerView();
            }
        });
    }

    private void httpData_RecyclerView() {
        pageNum = 1;
        lists.clear();
        httpData();
        xrefreshViewSpinnerZb.finishRefresh();
    }

    private void httpLoadMore() {
        pageNum++;
        httpData();
        xrefreshViewSpinnerZb.finishLoadMore();
    }

    @Override
    protected void httpData() {
        String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        if (app_token.length() > 11) {
            if (NetWorkUtils.isNetworkConnected(this)) {
                Call<ViewpointBean> call = MyApplication.getNetApi().getviewpoint((String) SharedPreferenceUtils.get(this, "app_token", ""), pageNum + "", "10", "");
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<ViewpointBean>() {
                    @Override
                    public void onResponse(Call<ViewpointBean> call, Response<ViewpointBean> response) {
                        if (response.isSuccessful()) {
                            ViewpointBean body = response.body();
                            //  caigoulist body = response.body();
                            if (body != null) {
                                List<ViewpointBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                                if (list != null && list.size() > 0) {
                                    for (ViewpointBean.DataBean.list_law_bean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setContent(listBean.gettitle());
                                        newBean.setId(listBean.getid());
                                        Log.e("fragment1", "onResponse: " + listBean.getid());
                                        newBean.setImg(listBean.getimg());
                                        newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        newBean.setsource("来源：" + listBean.gettypes());
                                        lists.add(newBean);
                                    }
                                    adapter.notifyDataSetChanged();
                                    ivEmpty.setVisibility(View.GONE);
                                    tvTxtNo.setVisibility(View.GONE);

                                } else {
                                    LogUtil.i("yantao", "else");
                                    if (pageNum == 1) {
                                        ivEmpty.setVisibility(View.VISIBLE);
                                        tvTxtNo.setVisibility(View.VISIBLE);
                                        lists.clear();
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ToastUtils.showMessage(CgjlMore_Activity.this, "数据已加载到底");
                                    }
                                }

                            } else {
                                if (pageNum == 1) {
                                    ivEmpty.setVisibility(View.VISIBLE);
                                    tvTxtNo.setVisibility(View.VISIBLE);
                                    lists.clear();
                                    adapter.notifyDataSetChanged();
                                } else {
                                    ToastUtils.showMessage(CgjlMore_Activity.this, "数据已加载到底");
                                }
                            }

                        } else {
                            if (pageNum == 1) {
                                ivEmpty.setVisibility(View.VISIBLE);
                                tvTxtNo.setVisibility(View.VISIBLE);
                                lists.clear();
                                adapter.notifyDataSetChanged();
                            } else {
                                ToastUtils.showMessage(CgjlMore_Activity.this, "数据已加载到底");
                            }
                        }
                        LogUtil.i("yantao", "Gone--1");
                        ivRepeat.setVisibility(View.GONE);
                        ivRepeat.setClickable(false);
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<ViewpointBean> call, Throwable t) {
                        call.cancel();
                        LogUtil.i("yantao", "onFailure");
                        LogUtil.i("yantao", "Gone--2");
                        ivRepeat.setVisibility(View.GONE);
                        ivRepeat.setClickable(false);
                    }
                });
            } else {
                ToastUtils.showMessage(this, "当前网络不可用");
                lists.clear();
                adapter.notifyDataSetChanged();
                ivRepeat.setVisibility(View.VISIBLE);
                ivRepeat.setClickable(true);
            }
        } else {
            ToastUtils.showMessage(this, "请您先登录");
        }
        xrefreshViewSpinnerZb.finishRefresh();
    }


    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CgjlMore_Activity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lists = null;
    }

}

