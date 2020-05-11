 package com.ansiyida.cgjl.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.qyml_detail_listbean;
import com.ansiyida.cgjl.bean.wintenderbean;
import com.ansiyida.cgjl.bean.yzml_detail_listbean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class qytable_scape_activity extends Activity {
    @Bind(R.id.iv_full)
    ImageView iv_full;
    @Bind(R.id.recyclerView_fragment1)
    RecyclerView recyclerView;
   public static qyml_detail_listbean detailBean_qyml_scape;
    private ArrayList<NewBean2> lists2;
    private NewsOneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qytable_scape);
        ButterKnife.bind(this);
        if(this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        lists2 = new ArrayList<>();
        // lists1 = new ArrayList<>();
        adapter = new NewsOneAdapter(lists2, this, this.getWindow());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setAdapter(adapter1);
        recyclerView.setAdapter(adapter);
    //    List<qyml_detail_listbean.DataBean> list = detailBean_qyml_scape.getData().;
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<wintenderbean> call = MyApplication.getNetApi().getwinTender((String) SharedPreferenceUtils.get(this, "app_token", ""),detailBean_qyml_scape.getData().getcompanyName());
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<wintenderbean>() {
                @Override
                public void onResponse(Call<wintenderbean> call, Response<wintenderbean> response) {
                    if (response.isSuccessful()) {
                        wintenderbean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<wintenderbean.DataBean> list = body.getData();

                            if (list != null && list.size() > 0) {

                                //lists2.clear();
                                for (wintenderbean.DataBean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("TB_y_s");
                                    List<wintenderbean.DataBean.companySummaries_bean> list_company = listBean.getcompanySummaries();
                                    if (list_company != null && list_company.size() > 0) {
                                        String company = "";
                                        String company_id = "";
                                        for (wintenderbean.DataBean.companySummaries_bean listBean_company : list_company) {
                                            company = company + listBean_company.getname();
                                            company_id= company_id + listBean_company.getid();
                                        }
                                        newBean.setcompany(company);
                                        newBean.setcompany_id(company_id);
                                    }
                                    newBean.setTitle(listBean.getpurchaseInfoSummary().gettitle());
                                    newBean.setmoney(listBean.getpurchaseInfoSummary().getmoney());
                                    newBean.setyz(listBean.getproprietorSummary_bean().getname());
                                    newBean.setyzid(listBean.getproprietorSummary_bean().getid());
                                    newBean.setproject_id(listBean.getpurchaseInfoSummary().getid());


                                    lists2.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                            }


                        }
                    }


                    call.cancel();
                }

                @Override
                public void onFailure(Call<wintenderbean> call, Throwable t) {
                    call.cancel();
                }
            });


        } else {
            ToastUtils.showMessage(qytable_scape_activity.this, "当前网络不可用");
        }
        adapter.notifyDataSetChanged();
    }



    @OnClick({R.id.iv_full})
    public void click(View view) {
        switch (view.getId()) {

            case R.id.iv_full:               //1.返回上一级

                this.finish();
                break;
            default:
                break;

        }
    }



    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }

            @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

