package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.bean.FriendYantaoBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ansiyida on 2018/3/16.
 */
public class FriendYantaoAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<FriendYantaoBean.DataBean.ListBean> lists;
    public FriendYantaoAdapter(Context context,ArrayList<FriendYantaoBean.DataBean.ListBean> lists){
        this.context=context;
        this.lists=lists;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolderYanTao2(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_yantao_dynamic, parent,false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolderYanTao2 viewHolder = (MyViewHolderYanTao2) holder;
        final FriendYantaoBean.DataBean.ListBean listBean = lists.get(position);
        viewHolder.relative_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YanTaoActivity.class);
                intent.putExtra("jc_id",listBean.getArt_id()+"");
                context.startActivity(intent);
            }
        });
        viewHolder.tv_newsTitle.setText(listBean.getArt_title());
        viewHolder.tv_des.setText(listBean.getArt_des());
        viewHolder.tv_zanCount.setText(listBean.getArt_thumbusnum()+"");
        viewHolder.tv_readCount.setText(listBean.getArt_browsenum()+"");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
    //研讨新闻
    class MyViewHolderYanTao2 extends RecyclerView.ViewHolder {
        @Bind(R.id.relative)
        RelativeLayout relative_jump;
        @Bind(R.id.tv_newsTitle)
        TextView tv_newsTitle;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_readCount)
        TextView tv_readCount;

        public MyViewHolderYanTao2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
