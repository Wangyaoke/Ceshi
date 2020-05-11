package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.IntrodusiralParkAuditDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.IntrodusiralAuditCenterBean;

import java.util.List;

public class IndustrialParkAuditCenterAdapter extends RecyclerView.Adapter<IndustrialParkAuditCenterAdapter.VeiwHolder> {

    private Context context;
    private List<IntrodusiralAuditCenterBean.DataBean.ListBean> listBeans;

    public IndustrialParkAuditCenterAdapter(Context context, List<IntrodusiralAuditCenterBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public VeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.industrialparkauditcenteradapter_layout, null);
        return new VeiwHolder(view);
    }

    @Override
    public void onBindViewHolder(VeiwHolder holder, final int position) {
        if(listBeans.get(position).getAudit().equals("ING")){
            holder.industrialparkauditType.setText("审核中");
        }else if(listBeans.get(position).getAudit().equals("PASS")){
            holder.industrialparkauditType.setText("审核通过");
        }else if(listBeans.get(position).getAudit().equals("NOTPASS")){
            holder.industrialparkauditType.setText("未通过");
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, IntrodusiralParkAuditDetailActivity.class);
                intent.putExtra("id",listBeans.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class VeiwHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView industrialparkauditType;
        public VeiwHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rel);
            industrialparkauditType = itemView.findViewById(R.id.industrialparkaudit_type);
        }
    }
}
