package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.IndustrialParkAuditCenterAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.IntrodusiralAuditCenterBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialParkAuditCenterActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.xRecyclerView)
    XRefreshView Xrecycelr;
    @Bind(R.id.noText)
    RelativeLayout noText;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private int PageNum = 1;
    private int Pagesize = 15;
    private IndustrialParkAuditCenterAdapter industrialParkAuditCenterAdapter;
    private List<IntrodusiralAuditCenterBean.DataBean.ListBean> AuditList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(IndustrialParkAuditCenterActivity.this,"Android产业园","IndustrialParkAuditCenterActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_industrial_park_audit_center;
    }

    @Override
    protected void initView() {
        industrialParkAuditCenterAdapter = new IndustrialParkAuditCenterAdapter(this, AuditList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(industrialParkAuditCenterAdapter);
    }

    @Override
    protected void initData() {
        textTitle.setText("产业园");
        Xrecycelr.setPullLoadEnable(true);
        Xrecycelr.setPullRefreshEnable(true);
    }

    @Override
    protected void httpData() {
        HttpData();
    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Xrecycelr.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

            }
            @Override
            public void onRefresh(boolean isPullDown) {
                PageNum = 1;
                AuditList.clear();
                httpData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                PageNum++;
                httpData();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    private void HttpData() {
        String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        try {
            Call<IntrodusiralAuditCenterBean> auditIntrodusiral = MyApplication.getNetApi().getAuditIntrodusiral(app_token, PageNum, Pagesize, "", "");
            auditIntrodusiral.enqueue(new Callback<IntrodusiralAuditCenterBean>() {
                @Override
                public void onResponse(Call<IntrodusiralAuditCenterBean> call, Response<IntrodusiralAuditCenterBean> response) {
                    if (response.isSuccessful()) {
                        IntrodusiralAuditCenterBean.DataBean body = response.body().getData();
                        if (body != null) {
                            List<IntrodusiralAuditCenterBean.DataBean.ListBean> list = body.getList();
                            if (list.size() > 0) {
                                AuditList.addAll(list);
                                industrialParkAuditCenterAdapter.notifyDataSetChanged();
                            } else {
                                if(PageNum>1){
                                    ToastUtils.showMessage(IndustrialParkAuditCenterActivity.this,"暂无更多数据");
                                }else {
                                    setHide();
                                }
                            }
                        }

                    } else {
                        setHide();
                    }
                    Xrecycelr.stopRefresh();
                    Xrecycelr.stopLoadMore();
                }

                @Override
                public void onFailure(Call<IntrodusiralAuditCenterBean> call, Throwable t) {
                    setHide();
                    Xrecycelr.stopRefresh();
                    Xrecycelr.stopLoadMore();
                }
            });
        } catch (Exception e) {
            Xrecycelr.stopRefresh();
            Xrecycelr.stopLoadMore();
        }
    }

    private void setHide() {
        if (PageNum == 1) {
            noText.setVisibility(View.VISIBLE);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        AuditList=null;
    }
}
