package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.VisitedAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.VisitedDYBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisistedActivity extends BaseActivity {
    @Bind(R.id.recyclerView_visitedActivity)
    RecyclerView recyclerView;
    private int pageNum=1;
    private String keyword;
    @Bind(R.id.text_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_visisted;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        keyword = getIntent().getStringExtra("keyword");
        title.setText("我的订阅");
    }

    @Override
    protected void httpData() {
        pageNum=1;
        LogUtil.i("keyword","keyword:"+keyword);
        Call<VisitedDYBean> call= MyApplication.getNetApi().visitedMyDYItem(pageNum+"",keyword);
        call.enqueue(new Callback<VisitedDYBean>() {
            @Override
            public void onResponse(Call<VisitedDYBean> call, Response<VisitedDYBean> response) {
                if (response.isSuccessful()) {
                    if ("0001".equals(response.body().getStatus())) {
                        List<VisitedDYBean.DataBean> data = response.body().getData();
                        if (data != null && data.size() > 0) {
                            recyclerView.setAdapter(new VisitedAdapter(VisistedActivity.this, data));
                        } else {
                            ToastUtils.showMessage(VisistedActivity.this, "无内容展示");
                        }
                    } else {
                        ToastUtils.showMessage(VisistedActivity.this, "无内容展示");
                    }


                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<VisitedDYBean> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    protected void setClickListener() {

    }
    @OnClick({R.id.image_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.image_back:
                this.finish();
                break;


        }


    }
}
