package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.ActivityCodeUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class BuyInvoiveAvtivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.buyinvoicelayout;
    }

    @Override
    protected void initView() {
        text_title.setText("申请开票");
        text_putOut.setText("开票说明");
        text_putOut.setTextColor(getResources().getColor(R.color.color_blue));

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

    @OnClick({R.id.image_back, R.id.text_putOut,R.id.linr_apply,R.id.line_history})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.text_putOut:                 //5.用户协议
                this.startActivityForResult(new Intent(this, BuyInvoiveNoteAvtivity.class), 1);

                break;
            case R.id.line_history:               //1.返回上一级
                this.startActivityForResult(new Intent(this, BuyInvoiveHistoryAvtivity.class), 1);
                break;
            case R.id.linr_apply:               //1.返回上一级
                this.startActivityForResult(new Intent(this, BuyInvoiveApllyAvtivity.class), 1);
                break;
            case R.id.image_back:               //1.返回上一级

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

