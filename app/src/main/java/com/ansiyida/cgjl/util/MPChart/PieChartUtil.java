package com.ansiyida.cgjl.util.MPChart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class PieChartUtil {
    public static void initPieChart(PieChart pieChart) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            float Percentage = 10 * i;
            entries.add(new PieEntry(Percentage, Percentage + ""));
        }
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(0, 1, 1, 0);//饼形图上下左右边距
        pieChart.setEntryLabelTextSize(12);
        pieChart.setCenterTextSize(12);
        pieChart.setDrawHoleEnabled(false);//是否显示PieChart内部圆环(true:下面属性才有意义)
        pieChart.setHoleColor(Color.WHITE);//设置PieChart内部圆的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        pieChart.setTransparentCircleAlpha(120);//设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        pieChart.setHoleRadius(75f);//设置PieChart内部圆的半径(这里设置28.0f)
        pieChart.setTransparentCircleRadius(31f);//设置PieChart内部透明圆的半径(这里设置31.0f)
        pieChart.setDrawCenterText(true);//是否绘制PieChart内部中心文本（true：下面属性才有意义）
        pieChart.setRotationAngle(0);//设置pieChart图表起始角度
        pieChart.setRotationEnabled(false);//设置pieChart图表是否可以手动旋转
        pieChart.setHighlightPerTapEnabled(true);//设置piecahrt图表点击Item高亮是否可用
        // 不显示全部分类
        pieChart.getLegend().setEnabled(false);

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        String[] color = new String[]{"#2cfdff", "#ff6d00", "#69f7a2", "#f94d4d", "#9e6ffd", "#9f655a", "#ff96bb", "#00b0ff", "#00bfa5", "#ffde34"};
        for (int i = 0; i < color.length; i++) {
            colors.add(Color.parseColor(color[i]));
        }
        dataSet.setColors(colors);
        //数字展示切换为百分比展示，字体大小设置为0不展示
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0);

        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
}
