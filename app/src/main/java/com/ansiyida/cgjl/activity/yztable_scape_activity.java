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
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.yzml_detail_listbean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class yztable_scape_activity extends Activity {
    @Bind(R.id.iv_full)
    ImageView iv_full;
    @Bind(R.id.recyclerView_fragment1)
    RecyclerView recyclerView;
   public static yzml_detail_listbean detailBean_yzml_scape;
    private ArrayList<NewBean2> lists2;
    private NewsOneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yztable_scape);
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
        final List<yzml_detail_listbean.DataBean.tender_bean> list = detailBean_yzml_scape.getData().gettender();
        for (yzml_detail_listbean.DataBean.tender_bean listBean : list) {
            NewBean2 newBean = new NewBean2();
            newBean.setArtype("TB_S");
            newBean.setTitle(listBean.getpurchaseInfoSummary().gettitle());
            newBean.setLable("暂无");
            newBean.setZhiDing(listBean.getpurchaseInfoSummary().getmoney());
            newBean.setId(listBean.getpurchaseInfoSummary().getid());
            lists2.add(newBean);
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

