package com.ansiyida.cgjl.view.cgjl_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;

import java.util.ArrayList;
import java.util.List;

public class FloatLayout extends LinearLayout {

    private int widthSccren;
    private int heightSccren;
    final List<String> list = new ArrayList<>();
    private List<String> liststr = new ArrayList<>();

    public FloatLayout(Context context) {
        this(context,null);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public FloatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //把方向改为垂直
        setOrientation(VERTICAL);
        //获取屏幕宽高
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        widthSccren= displayMetrics.widthPixels-(displayMetrics.widthPixels/10);
        //注意高度不需要
        heightSccren=displayMetrics.heightPixels;

    }


    public void setAdapter(String[][] data,List<String> click){
        //去掉所有的子view
        removeAllViews();
         innerSetAdapter(data,click);
    }

    public void add(String[][] data,List<String> click) {
        innerSetAdapter(data,click);
    }

    private void innerSetAdapter(final String[][] data,List<String> click){
        liststr.clear();
        liststr.addAll(click);

        LinearLayout linearLayout = iniHorL();

        for (int i = 0; i < data[0].length; i++) {
            String temp = data[0][i];
            int childCount = linearLayout.getChildCount();
            int totalWith=0;
			//获取前几个的长度
            for (int j = 0; j <childCount ; j++) {
                TextView childAt = (TextView) linearLayout.getChildAt(j);
                //一定要调用一次测量方法
                childAt.measure(getMeasuredWidth(),getMeasuredHeight());
				
                int width = childAt.getMeasuredWidth();
				
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();

                totalWith+=width+layoutParams.leftMargin+layoutParams.rightMargin;

            }
            final TextView textView = iniTextView();
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity=Gravity.CENTER;
            params.leftMargin=30;
            params.rightMargin=10;
            params.topMargin=10;
            params.bottomMargin=10;
            textView.setText(temp);
            if(liststr.contains(temp)){
                textView.setTextColor(Color.BLUE);
            }
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean contains =liststr.contains(textView.getText().toString());
                    if(contains) {
                        textView.setTextColor(getResources().getColor(R.color.F6));
                        liststr.remove(textView.getText().toString());
                    }else{
                        textView.setTextColor(getResources().getColor(R.color.F4E86ED));
                        liststr.add(textView.getText().toString());
                    }
                }
            });
            textView.measure(getMeasuredWidth(),getMeasuredHeight());
            int measuredWidth = textView.getMeasuredWidth();
            if (widthSccren>= (totalWith+ measuredWidth+params.leftMargin+params.rightMargin+10)) {

                linearLayout.addView(textView,params);
            }else {
                //这里注意不能新声明变量
                linearLayout = iniHorL();
                linearLayout.addView(textView,params);
            }

        }
    }


    public List<String> getList(){
        return liststr;
    }
    private LinearLayout iniHorL(){
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addView(linearLayout,params);
        return linearLayout;
    }

    private TextView iniTextView(){
        TextView textView = new TextView(getContext());
        textView.setTextColor(getResources().getColor(R.color.F6));
        textView.setTextSize(12);
        return textView;
    }



    public void TypeData(){
        /*liststr.clear();
        liststr.addAll(click);*/
        String bj = null;


        for (int i = 0; i < 6; i++) {
            //父
            bj="f";
            String temp = "这是第"+i+"个父标题";
            final TextView textView = iniTextView();
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity=Gravity.CENTER;
            params.leftMargin=30;
            params.rightMargin=10;
            params.topMargin=10;
            params.bottomMargin=10;
            textView.setText(temp);

            LinearLayout linearLayout1 = iniHorL();
            linearLayout1.addView(textView,params);
            LinearLayout linearLayout = iniHorL();
            for (int j = 0; j <6; j++) {
                //子
                bj="z";
                int childCount = linearLayout.getChildCount();
                int totalWith=0;
                //获取前几个的长度
                for (int h = 0; h <childCount ; h++) {
                    TextView childAt = (TextView) linearLayout.getChildAt(h);
                    //一定要调用一次测量方法
                    childAt.measure(getMeasuredWidth(),getMeasuredHeight());

                    int width = childAt.getMeasuredWidth();

                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();

                    totalWith+=width+layoutParams.leftMargin+layoutParams.rightMargin;

                }
                 LayoutParams  param = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                param.gravity=Gravity.CENTER;
                param.leftMargin=30;
                param.rightMargin=10;
                param.topMargin=10;
                param.bottomMargin=10;
                textView.setText("这是子标题哦"+j);
                textView.measure(getMeasuredWidth(),getMeasuredHeight());
                int measuredWidth = textView.getMeasuredWidth();
                if (widthSccren>= (totalWith+ measuredWidth+params.leftMargin+params.rightMargin+10)) {

                    linearLayout.addView(textView,params);
                }else {
                    //这里注意不能新声明变量
                    linearLayout = iniHorL();
                    linearLayout.addView(textView,params);
                }
            }

            if(liststr.contains(temp)){
                textView.setTextColor(Color.BLUE);
            }
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean contains =liststr.contains(textView.getText().toString());
                    if(contains) {
                        textView.setTextColor(getResources().getColor(R.color.text_tile));
                        liststr.remove(textView.getText().toString());
                    }else{
                        textView.setTextColor(Color.BLUE);
                        liststr.add(textView.getText().toString());
                    }
                }
            });
        }
    }

}
