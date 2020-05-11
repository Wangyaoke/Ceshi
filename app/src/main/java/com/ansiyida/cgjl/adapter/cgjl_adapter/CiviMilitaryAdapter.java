package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.IndustrialParkDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitaryBean;
import com.ansiyida.cgjl.view.cgjl_view.CornerFlagView;
import com.bumptech.glide.Glide;

import java.util.List;

public class CiviMilitaryAdapter extends RecyclerView.Adapter<CiviMilitaryAdapter.ViewHolder> {


    private List<CiViMilitaryBean.DataBean.ListBean> CiviList;
    private Context context;

    public CiviMilitaryAdapter(List<CiViMilitaryBean.DataBean.ListBean> civiList, Context context) {
        this.CiviList = civiList;
        this.context = context;
    }
    public void setCiviList(List<CiViMilitaryBean.DataBean.ListBean> civiList){
        this.CiviList =civiList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.civimilitary_recycler_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(CiviList.get(position).getImg()).into(holder.backImage);
        holder.recyclerName.setText(" "+CiviList.get(position).getName()+" ");
        holder.textmode.setTextContent(CiviList.get(position).getMode());
        String[] split = CiviList.get(position).getLabel().split(",");
        for (int i = 0; i <split.length; i++) {
            if(i==0){
                holder.textOne.setText(split[i]);
            }else if(i==1){
                holder.textTwo.setText(split[i]);
            }else if(i==2){
                holder.textThree.setText(split[i]);
            }
        }
        if(CiviList.get(position).getMode() .equals("加盟")){
            holder.textmode.setBackGroundColor(context.getResources().getColor(R.color.jmrh_jm));
        }else if(CiviList.get(position).getMode().equals("自营")){
            holder.textmode.setBackGroundColor(context.getResources().getColor(R.color.jmrh_zy));
        }
        holder.Industrial_Park_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, IndustrialParkDetailActivity.class);
               intent.putExtra("id",CiviList.get(position).getId());
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CiviList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recyclerName;
        RelativeLayout textRel;
        TextView textOne;
        TextView textTwo;
        TextView textThree;
        ImageView backImage;
        CornerFlagView textmode;
        RelativeLayout Industrial_Park_rel;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerName = itemView.findViewById(R.id.recycler_name);
            textRel = itemView.findViewById(R.id.text_rel);
            textOne = itemView.findViewById(R.id.textOne);
            textTwo = itemView.findViewById(R.id.textTwo);
            textThree = itemView.findViewById(R.id.textThree);
            backImage = itemView.findViewById(R.id.backImage);
            textmode = itemView.findViewById(R.id.text_mode);
            Industrial_Park_rel= itemView.findViewById(R.id.Industrial_Park_rel);
        }
    }
}
