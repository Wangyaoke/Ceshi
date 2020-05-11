package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class qymldetail_activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {

        return R.layout.qymldetail_layout;
    }

    @Override
    protected void initView() {


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

  /*  @OnClick({R.id.image_back, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.text_putOut:                 //5.用户协议

             //   this.finish();
                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            default:
                break;

        }
    }*/



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

