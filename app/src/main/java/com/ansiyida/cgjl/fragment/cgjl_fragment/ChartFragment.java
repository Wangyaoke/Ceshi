package com.ansiyida.cgjl.fragment.cgjl_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseBigDataTrendBean;
import com.ansiyida.cgjl.view.cgjl_view.ChartView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    @Bind(R.id.chartview)
    ChartView chartview;
    @Bind(R.id.ChartRel)
    RelativeLayout ChartRel;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();
    private List<PurchaseBigDataTrendBean.DataBean> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);
        data = (List<PurchaseBigDataTrendBean.DataBean>) this.getArguments().getSerializable("data");
        ChartViewData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chartview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void ChartViewData() {
        int ChartViewDataNum = 0;
        int max = 0;
        for (int i = 0; i < data.size(); i++) {
            xValue.add(data.get(i).getPublish_time());
            value.put(data.get(i).getPublish_time(), data.get(i).getDaily_data_volume());//60--240
            if (data.get(i).getDaily_data_volume() > max) {
                max = data.get(i).getDaily_data_volume();
            }
            ChartViewDataNum += data.get(i).getDaily_data_volume();
        }
        for (int i = 0; i < 6; i++) {
            yValue.add(i * (max / 4));
        }
        chartview.setChartViewListnear(new ChartView.ChartViewListnear() {
            @Override
            public void ChartViewClick(int Index) {

            }
        });
        chartview.setValue(value, xValue, yValue);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        chartview=null;
    }
}
