package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.BigDataListActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.IndustrialParkActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.IndustrialShopActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitrayTypeBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class CivilimitaryTypeAdapter extends RecyclerView.Adapter<CivilimitaryTypeAdapter.ViewHolder> {


    private List<CiViMilitrayTypeBean.DataBean.ListBean> CiviTypeList;
    private Context context;

    public CivilimitaryTypeAdapter(List<CiViMilitrayTypeBean.DataBean.ListBean> civiTypeList, Context context) {
        CiviTypeList = civiTypeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.civilimitarytype_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(CiviTypeList.get(position).getImg()).into(holder.CiviTypeBack);
        holder.CiviTypeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = CiviTypeList.get(position).getName();
                Intent intent = null;
                if(name.equals("产业园入驻")){
                     intent = new Intent(context, IndustrialParkActivity.class);
                }else if(name.equals("金融服务")){
                     intent = new Intent(context, IndustrialShopActivity.class);
                }else if(name.equals("大数据")){
                    intent = new Intent(context, BigDataListActivity.class);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CiviTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView CiviTypeBack;

        public ViewHolder(View itemView) {
            super(itemView);
            CiviTypeBack = itemView.findViewById(R.id.CiviTypeBack);
        }
    }
}
