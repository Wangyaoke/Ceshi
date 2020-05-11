package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.IndustrialParkShopDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeBean;
import com.ansiyida.cgjl.view.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IndustrialShopAdapter extends RecyclerView.Adapter<IndustrialShopAdapter.ViewHolder> {

    private Context context;
    private List<FinancialServeBean.DataBean> list = new ArrayList<>();

    public void setList(List<FinancialServeBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public IndustrialShopAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.industrial_shoplayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context)
                .load(list.get(position).getCoverImg())
                .transform(new GlideCircleTransform(context))
                .centerCrop()
                .into(holder.recyclerShopImg);
        holder.recyclerShopName.setText(list.get(position).getName());
        holder.ApplyNum.setText(list.get(position).getApplyForNumber()+"人已申请");
        holder.recyclerShopAddress.setText(list.get(position).getAddress());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        String[] keyword = list.get(position).getLabel().split(",");
        IndustrialRecyclerAdapter industrialRecyclerAdapter = new IndustrialRecyclerAdapter(context);
        holder.recyclerView.setAdapter(industrialRecyclerAdapter);
        industrialRecyclerAdapter.setKeywordSplit(keyword);

        holder.recyclerShopRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IndustrialParkShopDetailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_shop_rel)
        RelativeLayout recyclerShopRel;
        @Bind(R.id.recycler_shop_img)
        ImageView recyclerShopImg;
        @Bind(R.id.recycler_shop_name)
        TextView recyclerShopName;
        @Bind(R.id.recycler_shop_address)
        TextView recyclerShopAddress;
        @Bind(R.id.ApplyNum)
        TextView ApplyNum;
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
