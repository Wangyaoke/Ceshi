package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.SubscribeActivity;
import com.ansiyida.cgjl.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by ansiyida on 2017/11/7.
 */
public class MyDingYueAdapter extends RecyclerView.Adapter{
    private ArrayList<String> lists;
    private ArrayList<Integer> lists2;
    private SubscribeActivity activity;
    private Context context;
    public MyDingYueAdapter(SubscribeActivity activity, ArrayList<String> lists, ArrayList<Integer> lists2){
        this.lists=lists;
        this.lists2=lists2;
        this.activity=activity;
        this.context=activity.getApplicationContext();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder holder1= (MyViewHolder) holder;
        holder1.tv_newsAdapter.setText(lists.get(position));
        final int positon_2=position;
        if (lists2.get(position)==1){
            holder1.tv_newsAdapter.setTextColor(context.getResources().getColor(R.color.tab_1_day));
            holder1.tv_newsAdapter.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.txt_size_fifteen));
//            holder1.tv_newsAdapter.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }else {
            LogUtil.i("position","我重新刷新了一遍，改变的position："+position);
            holder1.tv_newsAdapter.setTextColor(context.getResources().getColor(R.color.tab_2_day));
            holder1.tv_newsAdapter.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimensionPixelSize(R.dimen.txt_size_sixteen));
//            holder1.tv_newsAdapter .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        }
        holder1.tv_newsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickItem(position);
            }
        });

    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_newsAdapter;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_newsAdapter= (TextView) itemView.findViewById(R.id.tv_newsAdapter);
        }
    }
    private void setTextBackgroundColor(TextView tv){
        tv.setBackgroundColor(Color.RED);

    }
}
