package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.AuditCenterAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.fragment.cgjl_fragment.CP_QYAuditFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuditCenterActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.audit_tablelayout)
    TabLayout auditTablayout;
    @Bind(R.id.audit_viewpager)
    ViewPager auditViewpager;
    private CP_QYAuditFragment cpAuditFragment;
    private CP_QYAuditFragment qyAuditFragment;
    private List<CP_QYAuditFragment> fragmentList;
    private AuditCenterAdapter auditCenterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        GoogleAssistant(AuditCenterActivity.this,"Android自荐审核","SettingActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_audit_center;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<CP_QYAuditFragment>();
        cpAuditFragment = new CP_QYAuditFragment();
        cpAuditFragment.Mode = "company";
        qyAuditFragment = new CP_QYAuditFragment();
        qyAuditFragment.Mode = "product";
        fragmentList.add(cpAuditFragment);
        fragmentList.add(qyAuditFragment);

        //适配器
        auditCenterAdapter = new AuditCenterAdapter(getSupportFragmentManager(),fragmentList,AuditCenterActivity.this);
        auditViewpager.setAdapter(auditCenterAdapter);
        //关联tablelayout
        auditTablayout.setupWithViewPager(auditViewpager);
    }

    @Override
    protected void initData() {
        auditTablayout.getTabAt(0).setText("企业自荐审核");
        auditTablayout.getTabAt(1).setText("产品自荐审核");
        textTitle.setText("审核页面");
    }

    @Override
    protected void httpData() {

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
    protected void onDestroy() {
        super.onDestroy();
        fragmentList =null;
    }
}
