package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.purchaseDemandBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class purchaseDemandAdapter extends RecyclerView.Adapter<purchaseDemandAdapter.ViewHolder> {

    private List<purchaseDemandBean.DataBean> list;
    private Context context;
    private List<String> ll = new ArrayList<>();
    private List<List<purchaseDemandBean.DataBean>> LIST = new ArrayList<>();
    private List<purchaseDemandBean.DataBean> listt;

    public purchaseDemandAdapter(List<purchaseDemandBean.DataBean> list, Context context) {
        this.list = list;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.purchaseinfo_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.time.setText(ll.get(position));
        holder.ShowOrHideText.setText("展开");
        holder.ShowOrHideImg.setImageResource(R.mipmap.point_down);
        purchaseDemandChildAdapter purchaseDemandChildAdapter = new purchaseDemandChildAdapter(LIST.get(position));
        holder.purchaseinfoRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.purchaseinfoRecycler.setAdapter(purchaseDemandChildAdapter);
        holder.ShowOrHideRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (holder.ShowOrHideText.getText().equals("展开")) {
                        holder.ShowOrHideText.setText("收起");
                        holder.ShowOrHideImg.setImageResource(R.mipmap.point_up);
                        holder.purchaseinfoRecycler.setVisibility(View.VISIBLE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(1000);
                            }
                        }).start();

                        LinearLayoutManager manager = (LinearLayoutManager)holder.purchaseinfoRecycler.getLayoutManager();
                        //平滑滚动
                        manager.scrollToPosition(1);
                    } else {
                        holder.ShowOrHideText.setText("展开");
                        holder.ShowOrHideImg.setImageResource(R.mipmap.point_down);
                        holder.purchaseinfoRecycler.setVisibility(View.GONE);
                    }
                }
        });
    }


    @Override
    public int getItemCount() {
        String Ti = "";
        LIST.clear();
        ll.clear();
        for (int i = 0; i <list.size(); i++) {
            if(!Ti.equals(changeTime(list.get(i).getcreateTime()))){
                if(i!=0){
                    LIST.add(listt);
                }
                Ti=changeTime(list.get(i).getcreateTime());
                ll.add(changeTime(list.get(i).getcreateTime()));
                listt = new ArrayList<>();
                listt.add(list.get(i));
            }else{
                listt.add(list.get(i));
            }
            if(i==list.size()-1){
                LIST.add(listt);
            }
        }
        return ll.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.view)
        View view;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.ShowOrHideImg)
        ImageView ShowOrHideImg;
        @Bind(R.id.ShowOrHideText)
        TextView ShowOrHideText;
        @Bind(R.id.ShowOrHideRel)
        RelativeLayout ShowOrHideRel;
        @Bind(R.id.purchaseinfo_recycler)
        RecyclerView purchaseinfoRecycler;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public String changeTime(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(time);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }
}
