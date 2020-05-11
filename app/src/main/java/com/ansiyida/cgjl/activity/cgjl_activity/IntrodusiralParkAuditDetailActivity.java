package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.IndustrialPackDetailBean;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntrodusiralParkAuditDetailActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.name_text)
    TextView nameText;
    @Bind(R.id.website_text)
    TextView websiteText;
    @Bind(R.id.legalperson_text)
    TextView legalpersonText;
    @Bind(R.id.Register_text)
    TextView RegisterText;
    @Bind(R.id.HYTyep_text)
    TextView HYTyepText;
    @Bind(R.id.MainBusiness_text)
    TextView MainBusinessText;
    @Bind(R.id.QYIntroduce_text)
    TextView QYIntroduceText;
    @Bind(R.id.MyBusinessIntroduce_text)
    TextView MyBusinessIntroduceText;
    @Bind(R.id.CivilianParticipation_text)
    TextView CivilianParticipationText;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(IntrodusiralParkAuditDetailActivity.this,"Android产业园入驻详情","IntrodusiralParkAuditDetailActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_introdusiral_check_in_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        textTitle.setText("产业园");
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void httpData() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
            Call<IndustrialPackDetailBean> call = MyApplication.getNetApi().GetindustrialPackDetail(app_token, id);
            call.enqueue(new Callback<IndustrialPackDetailBean>() {
                @Override
                public void onResponse(Call<IndustrialPackDetailBean> call, Response<IndustrialPackDetailBean> response) {
                    if (response.isSuccessful()) {
                        IndustrialPackDetailBean body = response.body();
                        if (body.getStatus() == 200){
                            IndustrialPackDetailBean.DataBean data = body.getData();
                            nameText.setText(data.getCompanyName());
                            websiteText.setText(data.getCompanyWebsite());
                            if(data.getCompanyLegal()!=null && !data.getCompanyLegal().equals("")) {
                                legalpersonText.setText(data.getCompanyLegal());
                            }
                            if(data.getCompanyAddress()!=null && !data.getCompanyAddress().equals("")) {
                                RegisterText.setText(data.getCompanyAddress());
                            }
                            if(data.getBusinessSummary()!=null && !data.getBusinessSummary().equals("")) {
                                MyBusinessIntroduceText.setText(data.getBusinessSummary());
                            }
                            HYTyepText.setText(data.getTradeType());
                            MainBusinessText.setText(data.getBusinessKeyword());
                            QYIntroduceText.setText(data.getCompanySummary());
                            CivilianParticipationText.setText(data.getDegree());
                        }
                    }
                }

                @Override
                public void onFailure(Call<IndustrialPackDetailBean> call, Throwable t) {

                }
            });
        } else {
            ToastUtils.showMessage(this, "请检查当前网络！");
        }
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
