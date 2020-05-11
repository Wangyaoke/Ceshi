package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.BigDataRecyclerViewAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ListPopWindowAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseBusinessBean;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseDataBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.cgjl_view.ChartView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterpriseBigDataActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.ChooseTime_)
    RadioButton ChooseTime;
    @Bind(R.id.CivilMilitary_Num)
    TextView CivilMilitaryNum;
    @Bind(R.id.IndustryProportion_ChooseTime)
    RadioButton IndustryProportionChooseTime;
    @Bind(R.id.IndustryProportion_recycler)
    RecyclerView IndustryProportionRecycler;
    @Bind(R.id.pieChart)
    PieChart pieChart;
    @Bind(R.id.chartview)
    ChartView chartview;
    private ListPopupWindow listPopupWindow;
    private List<String> poplist = new ArrayList<>();
    private ListPopWindowAdapter listPopWindowAdapter;
    private BigDataRecyclerViewAdapter recyclerViewAdapter;
    public String pieChartPosition ="";
    //环形图
    private ArrayList<PieEntry> entries = new ArrayList<>();
    public  List<EnterpriseDataBean.DataBean> enterpriseDataBean ;
    private int max;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();
    private List<EnterpriseDataBean.DataBean> data ;
    private float PieChartNum=0;
    private int PurchaseBusinessIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(EnterpriseBigDataActivity.this,"Android企业大数据","EnterpriseBigDataActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_enterprise_big_data;
    }

    @Override
    protected void initView() {
        SharedPreferenceUtils.put(this,"SetPopItemSize",12);
        SharedPreferenceUtils.put(this,"ChartViewJudge","EnterpriseBigData");
        //初始化View
        listPopupWindow = new ListPopupWindow(this);
        listPopWindowAdapter = new ListPopWindowAdapter(poplist, this);
        recyclerViewAdapter = new BigDataRecyclerViewAdapter(this, "Enterprise");
        //初始化设置
        IndustryProportionRecycler.setLayoutManager(new LinearLayoutManager(this));
        IndustryProportionRecycler.setAdapter(recyclerViewAdapter);
        IndustryProportionRecycler.setFocusable(false);
        listPopupWindow.setAdapter(listPopWindowAdapter);
        listPopupWindow.setModal(true);
        listPopupWindow.setHeight(200);
    }

    @Override
    protected void initData() {
        textTitle.setText("企业大数据");
        Intent intent = getIntent();
        //Log.e("接收传递过来的数据", "initData: " + intent.getSerializableExtra("data").toString());
        data = (List<EnterpriseDataBean.DataBean>) intent.getSerializableExtra("data");
        if(data.size() > 0){
            ChartViewData();
        }else{
            for (int i = 0; i <5; i++) {
                EnterpriseDataBean.DataBean dataBean = new EnterpriseDataBean.DataBean();
                dataBean.setProvinceName("无数据");
                dataBean.setProvinceNums(0);
                data.add(dataBean);
            }
            ChartViewData();
        }
    }

    private void ChartViewData() {
        int ChartViewDataNum=0;
        max = 0;
        for (int i = 0; i < data.size(); i++) {
            xValue.add(data.get(i).getProvinceName());
            value.put(data.get(i).getProvinceName(),data.get(i).getProvinceNums());//60--240
            if(data.get(i).getProvinceNums()> max){
                max = data.get(i).getProvinceNums();
            }
            ChartViewDataNum+=data.get(i).getProvinceNums();
        }
        for (int i = 0; i < 6; i++) {
            yValue.add(i * (max/4));
        }
        CivilMilitaryNum.setText("总量："+ChartViewDataNum);
        chartview.setValue(value, xValue, yValue);
        chartview.setChartViewListnear(new ChartView.ChartViewListnear() {
            @Override
            public void ChartViewClick(int Index) {
                Log.e("折线图", "下标"+Index );
            }
        });
    }

    @Override
    protected void httpData() {
        String app_token = (String) SharedPreferenceUtils.get(this,"app_token","");
        Call<EnterpriseBusinessBean> enterpriseBusinessDataCall = MyApplication.getNetApi().getEnterpriseBusinessData(app_token);
        enterpriseBusinessDataCall.enqueue(new Callback<EnterpriseBusinessBean>() {
            @Override
            public void onResponse(Call<EnterpriseBusinessBean> call, Response<EnterpriseBusinessBean> response) {
                if(response.isSuccessful()){
                    EnterpriseBusinessBean body = response.body();
                    if(body.getStatus()==200 && body.getData().size()>0){
                        List<EnterpriseBusinessBean.DataBean> list = new ArrayList<>();
                        list.add(new EnterpriseBusinessBean.DataBean());
                        if(body.getData().size()>10){
                            PurchaseBusinessIndex=10;
                        }
                        for (int i = 0; i < PurchaseBusinessIndex; i++) {
                            list.add(body.getData().get(i));
                            PieChartNum+=body.getData().get(i).getBusinessNum();
                        }
                        PieChartData((ArrayList<EnterpriseBusinessBean.DataBean>) body.getData());
                        recyclerViewAdapter.setEnterBusinessList(list,PieChartNum);
                    }
                }
            }

            @Override
            public void onFailure(Call<EnterpriseBusinessBean> call, Throwable t) {
                Log.e("onFailure", "EnterpriseBigData"+t.getMessage() );
            }
        });
    }

    @Override
    protected void setClickListener() {
        chartview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showMessage(EnterpriseBigDataActivity.this, poplist.get(position));
            }
        });
        ChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.setWidth(ChooseTime.getMeasuredWidth());
                listPopupWindow.setAnchorView(ChooseTime);
                listPopupWindow.show();
                listPopWindowAdapter.notifyDataSetChanged();
            }
        });
        IndustryProportionChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.setWidth(IndustryProportionChooseTime.getMeasuredWidth());
                listPopupWindow.setAnchorView(IndustryProportionChooseTime);
                listPopupWindow.show();
                listPopWindowAdapter.notifyDataSetChanged();
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void PieChartData(final ArrayList<EnterpriseBusinessBean.DataBean> list) {
        pieChartPosition = "";
        entries.clear();
        final DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i <PurchaseBusinessIndex; i++) {
            float Percentage = Float.parseFloat(df.format(list.get(i).getBusinessNum()/PieChartNum*100));
            entries.add(new PieEntry(Percentage, list.get(i).getBusinessName()));
        }
        pieChart.setCenterText("行业比重");//设置PieChart内部圆文字的内容
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(1, 1, 1, 1);//饼形图上下左右边距
        pieChart.setEntryLabelTextSize(0);
        pieChart.setCenterTextSize(12);
        pieChart.setDrawHoleEnabled(true);//是否显示PieChart内部圆环(true:下面属性才有意义)
        pieChart.setHoleColor(Color.WHITE);//设置PieChart内部圆的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        pieChart.setTransparentCircleAlpha(120);//设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        pieChart.setHoleRadius(75f);//设置PieChart内部圆的半径(这里设置28.0f)
        pieChart.setTransparentCircleRadius(31f);//设置PieChart内部透明圆的半径(这里设置31.0f)
        pieChart.setDrawCenterText(true);//是否绘制PieChart内部中心文本（true：下面属性才有意义）
        pieChart.setRotationAngle(0);//设置pieChart图表起始角度
        pieChart.setRotationEnabled(false);//设置pieChart图表是否可以手动旋转
        pieChart.setHighlightPerTapEnabled(true);//设置piecahrt图表点击Item高亮是否可用
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // 不显示全部分类
        final Legend l = pieChart.getLegend();
        l.setEnabled(false);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        String [] color = new String[]{"#2cfdff","#ff6d00","#69f7a2","#f94d4d","#9e6ffd","#9f655a","#ff96bb","#00b0ff","#00bfa5","#ffde34"};
        for (int i = 0; i <color.length; i++) {
            colors.add(Color.parseColor(color[i]));
            list.get(i).setColor(Color.parseColor(color[i]));
        }
        dataSet.setColors(colors);
        //数字展示切换为百分比展示，字体大小设置为0不展示
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0);

        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
        recyclerViewAdapter.setEnterData(null, colors);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
               //点击的下标
                float x = h.getX();
                pieChartPosition = entries.get((int)x).getLabel();
                int index = 0;
                for (int i = 0; i <list.size(); i++) {
                    if(list.get(i).getBusinessName()==pieChartPosition){
                        index=i;
                    }
                }
                pieChart.setCenterText(entries.get((int)x).getLabel() + "\n" + df.format(list.get(index).getBusinessNum()/PieChartNum  *100) + "%");
                List<EnterpriseBusinessBean.DataBean> beanList = new ArrayList<>();
                beanList.add(new EnterpriseBusinessBean.DataBean());
                beanList.add(list.get(index));
                for (int i = 0; i <list.size(); i++) {
                    if(!list.get(i).getBusinessName().equals("") && !list.get(i).getBusinessName().equals(beanList.get(1).getBusinessName())){
                        beanList.add(list.get(i));
                    }
                }
                recyclerViewAdapter.setEnterBusinessList(beanList,PieChartNum);
            }
            @Override
            public void onNothingSelected() {
                pieChart.setCenterText("行业比重");
                pieChartPosition = "";
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferenceUtils.remove(this,"SetPopItemSize");
        SharedPreferenceUtils.remove(this,"ChartViewJudge");
        data=null;xValue=null;yValue=null;value=null;poplist=null;chartview=null;enterpriseDataBean=null;entries=null;
    }
}
