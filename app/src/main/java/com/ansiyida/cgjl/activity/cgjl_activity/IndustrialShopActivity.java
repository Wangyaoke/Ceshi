package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.IndustrialShopAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialShopActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.Recycler)
    RecyclerView Recycler;
    private IndustrialShopAdapter industrialShopAdapter;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(IndustrialShopActivity.this,"Android融资服务","IndustrialShopActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_industrial_shop;
    }

    @Override
    protected void initView() {

        loadingDialog = new LoadingDialog();
        industrialShopAdapter = new IndustrialShopAdapter(this);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        Recycler.setAdapter(industrialShopAdapter);
    }

    @Override
    protected void initData() {
        textTitle.setText("融资服务");
    }

    @Override
    protected void httpData() {
        if(loadingDialog!=null){
            loadingDialog.showDialog(this);
        }
        String app_token = (String)SharedPreferenceUtils.get(this,"app_token","");
        Call<FinancialServeBean> financialServeBeanCall = MyApplication.getNetApi().getfinancialServe(app_token);
        financialServeBeanCall.enqueue(new Callback<FinancialServeBean>() {
            @Override
            public void onResponse(Call<FinancialServeBean> call, Response<FinancialServeBean> response) {
                if(response.isSuccessful()){
                    FinancialServeBean body = response.body();
                    if(body.getStatus()== 200){
                        List<FinancialServeBean.DataBean> data = body.getData();
                        industrialShopAdapter.setList(data);
                    }else{
                        Toast.makeText(IndustrialShopActivity.this, ""+body.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.e("onResponse", "onResponse: "+ response.toString() );
                }
                if(loadingDialog!=null && loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
            }

            @Override
            public void onFailure(Call<FinancialServeBean> call, Throwable t) {

            }
        });

    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }





}
