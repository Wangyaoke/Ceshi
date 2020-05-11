package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.SecretProcurementRecyclerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.MPChart.LineChartUtil;
import com.ansiyida.cgjl.util.MPChart.PieChartUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.Bind;

public class SecretProcurementActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.pieChart)
    PieChart pieChart;
    @Bind(R.id.LinechartOne_choose)
    RadioButton LinechartOneChoose;
    @Bind(R.id.LinechartOne_RecyclerView)
    RecyclerView LinechartOneRecyclerView;
    @Bind(R.id.LinechartOne)
    LineChart LinechartOne;
    private SecretProcurementRecyclerAdapter LineChartOneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_secret_procurement;
    }

    @Override
    protected void initView() {
        imageBack.setVisibility(View.VISIBLE);
        textPutOut.setVisibility(View.INVISIBLE);
        PieChartUtil.initPieChart(pieChart);
        LineChartUtil.initLineChart(LinechartOne);

        LineChartOneAdapter = new SecretProcurementRecyclerAdapter();
        GridLayoutManager GridOne = new GridLayoutManager(this,3);
        GridOne.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinechartOneRecyclerView.setLayoutManager(GridOne);
        LinechartOneRecyclerView.setAdapter(LineChartOneAdapter);

    }

    @Override
    protected void initData() {
        textTitle.setText("涉密采购");
        LineChartUtil.setManyDatas(5,3,LinechartOne);
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
}
