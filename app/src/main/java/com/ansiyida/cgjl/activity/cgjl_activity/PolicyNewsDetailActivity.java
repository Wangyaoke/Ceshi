package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.IndustrialRecyclerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.ParkDetailsListContentBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolicyNewsDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    private int id;
    private String title;
    private IndustrialRecyclerAdapter industrialRecyclerAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_policy_news_detail;
    }

    @Override
    protected void initView() {
        industrialRecyclerAdapter = new IndustrialRecyclerAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(industrialRecyclerAdapter);
    }

    @Override
    protected void initData() {
        //intent传递的值
        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        id = intent.getIntExtra("Id", 0);
        //赋值操作
        textTitle.setText(title);
    }

    @Override
    protected void httpData() {
        String app_token = (String)SharedPreferenceUtils.get(this,"app_token","");
        Call<ParkDetailsListContentBean> parkDetailsListContentCall = MyApplication.getNetApi().getParkDetailsListContent(app_token, id);
        parkDetailsListContentCall.enqueue(new Callback<ParkDetailsListContentBean>() {
            @Override
            public void onResponse(Call<ParkDetailsListContentBean> call, Response<ParkDetailsListContentBean> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus()==200) {
                        ParkDetailsListContentBean.DataBean data = response.body().getData();
                        if (data.getList().size() > 0) {
                            industrialRecyclerAdapter.setParkDetailList(data.getList());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParkDetailsListContentBean> call, Throwable t) {
                Log.e("政策新闻", "onFailure: "+t.getMessage() );
            }
        });
    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                this.finish();
                break;
        }
    }
}
