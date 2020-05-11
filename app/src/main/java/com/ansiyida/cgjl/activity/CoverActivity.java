package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;

import butterknife.Bind;

public class CoverActivity extends BaseActivity {
    @Bind(R.id.image_back)
    ImageView image_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cover;
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
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoverActivity.this.finish();
            }
        });
    }
}
