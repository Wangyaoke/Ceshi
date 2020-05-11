package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.EnterprisesRecommendoneselfActivity;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class qyml_activity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(qyml_activity.this,"Android企业名录","qyml_activity");
    }

    @Override
    protected int getContentView() {
        return R.layout.qyml_first_layout;
    }

    @Override
    protected void initView() {
        text_title.setText("企业名录");
        text_putOut.setText("");
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {


    }

    @OnClick({R.id.image_back, R.id.image_yzml,R.id.image_qyml,R.id.image_qyzj})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_yzml:
                Intent intent_read = new Intent(this, yzfx_activity.class);
                this.startActivity(intent_read);
                break;
            case R.id.image_qyml:                 //5.用户协议
                Intent  intent = new Intent(this, qyml1_activity.class);
                this.startActivity(intent);
                break;
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.image_qyzj:
                if("true".equals(SharedPreferenceUtils.get(this, "vistor", ""))) {
                    Intent intentqyzj = new Intent(this, EnterprisesRecommendoneselfActivity.class);
                    this.startActivity(intentqyzj);
                }else{
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
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

