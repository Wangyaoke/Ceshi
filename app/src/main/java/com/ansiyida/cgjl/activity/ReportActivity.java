package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.edit_userFeedback)
    EditText edit_userFeedback;
    @Bind(R.id.tv_editCount)
    TextView tv_editCount;
    @Bind(R.id.checkbox1)
    CheckBox checkbox1;
    @Bind(R.id.checkbox2)
    CheckBox checkbox2;
    @Bind(R.id.checkbox3)
    CheckBox checkbox3;
    @Bind(R.id.checkbox4)
    CheckBox checkbox4;
    @Bind(R.id.checkbox5)
    CheckBox checkbox5;
    @Bind(R.id.checkbox6)
    CheckBox checkbox6;
    private ArrayList<String> strandList;
    private HashMap<String,String> hashMap;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_report;
    }

    @Override
    protected void initView() {
        hashMap=new HashMap<>();
        strandList=new ArrayList<>();
        userId=getIntent().getStringExtra("userId");
    }

    @Override
    protected void initData() {
        title.setText("举报");
        setStateColor(this,true);
    }

    @Override
    protected void httpData() {
        Call<JuBaoBean> call = MyApplication.getNetApi().getJuBao("24");
        call.enqueue(new Callback<JuBaoBean>() {
            @Override
            public void onResponse(Call<JuBaoBean> call, Response<JuBaoBean> response) {
                if (response.isSuccessful()) {
                    JuBaoBean body = response.body();
                    List<JuBaoBean.DataBean> data = body.getData();
                    if (data != null && data.size() > 0) {
                        int length = data.size();
                        if (length > 6) {
                            length = 6;
                        }
                        for (int x = 0; x < length; x++) {
                            String text = data.get(x).getJl_name();
                            String id = data.get(x).getJl_id() + "";
                            if (x == 0) {
                                checkbox1.setText(text);
                                checkbox1.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox1);
                            } else if (x == 1) {
                                checkbox2.setText(text);
                                checkbox2.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox2);
                            } else if (x == 2) {
                                checkbox3.setText(text);
                                checkbox3.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox3);
                            } else if (x == 3) {
                                checkbox4.setText(text);
                                checkbox4.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox4);
                            } else if (x == 4) {
                                checkbox5.setText(text);
                                checkbox5.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox5);
                            } else if (x == 5) {
                                checkbox6.setText(text);
                                checkbox6.setVisibility(View.VISIBLE);
                                checkboxClick(checkbox6);
                            }
                            hashMap.put(text, id);
                        }

                    }

                } else {
                    ToastUtils.showMessage(ReportActivity.this, "服务器解析失败");
                    ReportActivity.this.finish();
                }

                call.cancel();
            }

            @Override
            public void onFailure(Call<JuBaoBean> call, Throwable t) {
                ToastUtils.showMessage(ReportActivity.this, "服务器解析失败");
                ReportActivity.this.finish();
                call.cancel();
            }
        });
    }

    @Override
    protected void setClickListener() {
        edit_userFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                int length = msg.length();
                tv_editCount.setText(length + "");
            }
        });
    }
    private void checkboxClick(CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    strandList.add(hashMap.get(buttonView.getText().toString().trim()));
                }else {
                    strandList.remove(hashMap.get(buttonView.getText().toString().trim()));

                }
            }
        });
    }
    @OnClick({R.id.image_back,R.id.btn_record})
    public void click(View view){
        switch (view.getId()){
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.btn_record:
                String juBaoId="";
                boolean reportFlag=false;
                if (strandList.size()>0){
                    StringBuffer sb=new StringBuffer();
                    for (String str:strandList){
                        sb.append(str+",");
                    }
                    juBaoId=sb.toString();
                    reportFlag=true;
                }else {
                    if (!"".equals(edit_userFeedback.getText().toString().trim())){
                        reportFlag=true;

                    }
                }
                if (reportFlag){
                    LogUtil.i("jubao","juBaoId:"+juBaoId+",userId:"+userId);
                    Call<DefaultBean> call=MyApplication.getNetApi().http_report((String) SharedPreferenceUtils.get(ReportActivity.this, "app_token", ""),"B","",juBaoId,userId);
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                if ("0001".equals(response.body().getStatus())) {
                                    ToastUtils.showMessage(ReportActivity.this, "举报成功");

                                } else {
                                    ToastUtils.showMessage(ReportActivity.this, response.body().getMessage());
                                }

                            } else {
                                ToastUtils.showMessage(ReportActivity.this, "异常错误1");
                            }
                            ReportActivity.this.finish();
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            ToastUtils.showMessage(ReportActivity.this, "异常错误2");
                            ReportActivity.this.finish();
                            call.cancel();

                        }
                    });
                }else {
                    ToastUtils.showMessage(ReportActivity.this,"请填写举报内容");

                }

                break;
        }

    }
}
