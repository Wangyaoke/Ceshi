package com.ansiyida.cgjl.view.cgjl_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;

import java.util.ArrayList;
import java.util.List;

public class MyFloatLayout extends LinearLayout {
    private int mScreenWidth;
    private int mScreenHeight;
    private List<String> list = new ArrayList<>();
    public MyFloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        //设置这个布局垂直显示
        setOrientation(VERTICAL);
    }

    public void removeChildView() {
        //移除所有子控件
        removeAllViews();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
    public void setDataZJ(ArrayList<String> data) {
        LinearLayout linearLayout = getLin();//[lvxx,lxs,lzs,lzzs]
        for (int i = 0; i < data.size(); i++) {//lvxx
            final String tmp = data.get(i);
            int numWidth = 0;
            //得到一行LinearLayout到底有多少子控件  因为我要计算每个子控件加在一起的宽度
            int childCount = linearLayout.getChildCount();
            //这个for循环只是计算一行LinearLayout的所有子控件的宽的和
            for (int j = 0; j < childCount; j++) {
                //通过index得到每一个子控件
                TextView tv = (TextView) linearLayout.getChildAt(j);
                LayoutParams layoutParams = (LayoutParams) tv.getLayoutParams();
                int leftMargin = layoutParams.leftMargin;
                //测量这个tv的高和宽
                tv.measure(getMeasuredWidth(), getMeasuredHeight());
                numWidth += tv.getMeasuredWidth() + leftMargin + tv.getPaddingLeft() + getPaddingRight();
            }

            final TextView dataText = getText1();
            dataText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //设置属性
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            params.topMargin = 10;
            dataText.setLayoutParams(params);
            dataText.setText(tmp);
            dataText.measure(getMeasuredWidth(), getMeasuredHeight());
            int dataTextWidth = dataText.getMeasuredWidth() + dataText.getPaddingLeft() + dataText.getPaddingRight();
            //考虑到一个字符串很长 就直接超过整个屏幕的高了
            if (mScreenWidth >= numWidth + dataTextWidth) {//lvxx
                linearLayout.addView(dataText);
            } else {
                //这里面对LinearLayout重新赋值  通过getLin换行
                linearLayout = getLin();
                linearLayout.addView(dataText);
            }
        }
    }
    public void setData(ArrayList<String> data) {
            LinearLayout linearLayout = getLin();//[lvxx,lxs,lzs,lzzs]
            for (int i = 0; i < data.size(); i++) {//lvxx
                final String tmp = data.get(i);
                int numWidth = 0;
                //得到一行LinearLayout到底有多少子控件  因为我要计算每个子控件加在一起的宽度
                int childCount = linearLayout.getChildCount();
                //这个for循环只是计算一行LinearLayout的所有子控件的宽的和
                for (int j = 0; j < childCount; j++) {
                    //通过index得到每一个子控件
                    TextView tv = (TextView) linearLayout.getChildAt(j);
                    LayoutParams layoutParams = (LayoutParams) tv.getLayoutParams();
                    int leftMargin = layoutParams.leftMargin;
                    //测量这个tv的高和宽
                    tv.measure(getMeasuredWidth(), getMeasuredHeight());
                    numWidth += tv.getMeasuredWidth() + leftMargin + tv.getPaddingLeft() + getPaddingRight();
                }

                final TextView dataText = getText();
                    dataText.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myFloatLayoutListnear.onclick(dataText.getText().toString());
                        }
                    });
                    //设置属性
                    LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params.leftMargin = 15;
                    params.topMargin = 10;
                    dataText.setLayoutParams(params);
                    dataText.setText(tmp);
                    dataText.measure(getMeasuredWidth(), getMeasuredHeight());
                    int dataTextWidth = dataText.getMeasuredWidth() + dataText.getPaddingLeft() + dataText.getPaddingRight();
                    //考虑到一个字符串很长 就直接超过整个屏幕的高了

                    if (mScreenWidth >= numWidth + dataTextWidth) {//lvxx
                        linearLayout.addView(dataText);
                    } else {
                        //这里面对LinearLayout重新赋值  通过getLin换行
                        linearLayout = getLin();
                        linearLayout.addView(dataText);
                    }
                }
    }


    //初始化子LinearLayout

    private LinearLayout getLin() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        //LayoutParams 控制组件大小的一个工具类
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        //this本类对象
        this.addView(linearLayout);//只要重新添加View了自动换行了
        return linearLayout;
    }

    //初始化TextView
    private TextView getText() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(16);
        textView.setBackground(getResources().getDrawable(R.drawable.knowledge_serach));
        textView.setPadding(25,18 , 25, 18);
        return textView;
    }

    //初始化TextView
    private TextView getText1() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(14);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(15,15 , 15, 15);
        return textView;
    }
    public List<String> getList(){
        return list;
    }
    public interface MyFloatLayoutListnear{
        void onclick(String str);
    }
    public MyFloatLayoutListnear myFloatLayoutListnear;
    public void setMyFloatLayoutListnear(MyFloatLayoutListnear myFloatLayoutListnear){
        this.myFloatLayoutListnear = myFloatLayoutListnear;
    }
}
