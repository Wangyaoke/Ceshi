package com.ansiyida.cgjl.util.MPChart;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class LineChartUtil {
    private static XAxis xAxis;
    private static ArrayList<String> nameList;
    private static ArrayList<Integer> colours;
    public static void initLineChart(LineChart lineChart) {
        //配置基本信息
        lineChart.getDescription().setEnabled(false);   //设置描述
        lineChart.setTouchEnabled(true);    //设置是否可以触摸
        lineChart.setDragDecelerationFrictionCoef(0.9f);    //设置滚动时的速度快慢
        lineChart.setDragEnabled(true);     // 是否可以拖拽
        lineChart.setScaleXEnabled(false);   //设置X轴是否能够放大
        lineChart.setScaleYEnabled(false);  //设置Y轴是否能够放大
        //lineChart.setScaleEnabled(true);    // 是否可以缩放 x和y轴, 默认是true
        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setBackgroundColor(Color.WHITE);  //设置背景颜色
        Matrix m = new Matrix();
        m.postScale(1.5f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        lineChart.getViewPortHandler().refresh(m, lineChart, false);//将图表动画显示之前进行缩放

        lineChart.animateX(1000);// 立即执行的动画,x轴
        //配置X轴属性
        xAxis = lineChart.getXAxis();
        //设置是否一个格子显示一条数据
        xAxis.setGranularity(1f);
        //xAxis.setLabelRotationAngle(25f);  //设置旋转偏移量
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴的位置
        //设置标签文本格式
        xAxis.setTextSize(10f);
        //设置标签文本颜色
        xAxis.setTextColor(Color.WHITE);
        //是否绘制轴线
        xAxis.setDrawAxisLine(true);
        //是否绘制网格线
        xAxis.setDrawGridLines(false);
        //设置是否一个格子显示一条数据，如果不设置这个属性，就会导致X轴数据重复并且错乱的问题
        xAxis.setGranularity(1f);

        //配置Y轴信息
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);   //设置Y轴的位置
        leftAxis.setDrawGridLines(false);    //设置左边的网格线显示
        leftAxis.setGranularityEnabled(false);//启用在放大时限制y轴间隔的粒度特性。默认值：false。
        leftAxis.setTextColor(Color.WHITE); //设置Y轴文字颜色
        YAxis rightAxis = lineChart.getAxisRight(); //获取右边的Y轴
        rightAxis.setEnabled(false);   //设置右边的Y轴不显示

        //设置图例（也就是曲线的标签）
        Legend legend = lineChart.getLegend();//设置比例图
        legend.setEnabled(false);   //因为自带的图例太丑，而且操作也不方便，楼主选择自定义，设置不显示比例图
    }
    /**
     * 设置多条折线的数据
     *
     * @param chartData //线条x轴的个数
     * @param count     //线条的个数
     */
    public static void setManyDatas(int chartData, int count,LineChart lineChart) {
        List<String> singleNameList = new ArrayList<>();
        for (int i = 0; i < chartData; i++) {
            int j = i + 1;
            singleNameList.add(j + "月");
        }
        MyXFormatter formatter = new MyXFormatter(singleNameList);
        xAxis.setValueFormatter(formatter);

        initChartColors();  //设置线条颜色集合
        initChartNames(count);   //设置不同线条名字的集合
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();   //线条数据集合

        for (int i = 0; i < count; i++) {
            //设置单条折现的Y轴数据
            ArrayList<Entry> yList = new ArrayList<Entry>();
            for (int j = 0; j < chartData; j++) {
                float value = (float) (Math.random() * 10);
                yList.add(new Entry(j, value));
            }
            LineDataSet lineDataSet = new LineDataSet(yList, nameList.get(i));   //设置单条折线
            //设置折线的属性
            lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);   //设置左右两边Y轴节点描述
            lineDataSet.setValueTextColor(ColorTemplate.getHoloBlue()); //设置节点文字颜色
            lineDataSet.setDrawCircles(false);  //设置是否显示节点的小圆点
            lineDataSet.setDrawValues(false);       //设置是否显示节点的值
            lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));//当点击节点时，将会出现与节点水平和垂直的两条线，可以对其进行定制.此方法为设置线条颜色
            lineDataSet.setHighlightEnabled(false);//设置是否显示十字线
            lineDataSet.setColor(colours.get(i));    //设置线条颜色
            lineDataSet.setCircleColor(Color.WHITE);  //设置节点的圆圈颜色
            lineDataSet.setLineWidth(1f);   //设置线条宽度
            lineDataSet.setCircleRadius(4f);//设置每个坐标点的圆大小
            lineDataSet.setDrawCircleHole(false);//是否定制节点圆心的颜色，若为false，则节点为单一的同色点，若为true则可以设置节点圆心的颜色
            lineDataSet.setValueTextSize(9f);   //设置 DataSets 数据对象包含的数据的值文本的大小（单位是dp）。
            //设置折线图填充
            lineDataSet.setDrawFilled(false);    //Fill填充，可以将折线图以下部分用颜色填满
            lineDataSet.setFillAlpha(65);       //设置填充区域透明度，默认值为85
            lineDataSet.setFillColor(ColorTemplate.getHoloBlue());//设置填充颜色
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormSize(15.f);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        lineChart.setData(data);    //添加数据
        lineChart.notifyDataSetChanged();
    }

    /**
     * 设置线条名字集合
     *
     * @param count
     */
    private static void initChartNames(int count) {
        nameList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int j = i + 1;
            String name = "线条" + j;
            nameList.add(name);
        }
    }

    /**
     * 提前设置颜色集合
     */
    private static void initChartColors() {
        //颜色集合
        colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);
        colours.add(Color.BLACK);
        colours.add(Color.GRAY);
    }

    public static class MyXFormatter implements IAxisValueFormatter {
        private List<String> list;

        public MyXFormatter(List<String> values) {
            this.list = values;
        }

        private static final String TAG = "MyXFormatter";

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.e(TAG, "----->getFormattedValue: " + value);
            if (value < list.size()) {
                return list.get((int) (value));
            } else {
                return null;
            }
        }
    }
}
