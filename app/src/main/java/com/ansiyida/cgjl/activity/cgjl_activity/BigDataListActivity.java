package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.BigDataRecyclerViewAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.BigDataListBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BigDataListActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.BigData_recyclerView)
    RecyclerView BigDataRecyclerView;
    private BigDataRecyclerViewAdapter bigDataRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_big_data;
    }

    @Override
    protected void initView() {
        imageBack.setVisibility(View.VISIBLE);
        textPutOut.setVisibility(View.GONE);
        bigDataRecyclerViewAdapter = new BigDataRecyclerViewAdapter(this,"BigData");
        BigDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BigDataRecyclerView.setAdapter(bigDataRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        textTitle.setText("大数据");
    }

    @Override
    protected void httpData() {
        String app_token =(String) SharedPreferenceUtils.get(this,"app_token","");
        Call<BigDataListBean> bigDataListDataCall = MyApplication.getNetApi().getBigDataListData(app_token);
        bigDataListDataCall.enqueue(new Callback<BigDataListBean>() {
            @Override
            public void onResponse(Call<BigDataListBean> call, Response<BigDataListBean> response) {
                if(response.isSuccessful()){
                    BigDataListBean body = response.body();
                    if(body!=null && body.getStatus()==200){
                        bigDataRecyclerViewAdapter.setBigDataList(body.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<BigDataListBean> call, Throwable t) {
                Log.e("onFailure大数据列表", t.getMessage() );
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

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.mFirebaseAnalytics.setUserProperty("favorite_food", "用户属性"); // 2. 上报用户属性favorite_food
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Android大数据");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        MyApplication.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        MyApplication.mFirebaseAnalytics.setCurrentScreen(BigDataListActivity.this,"MainActivity",null);
    }
}
