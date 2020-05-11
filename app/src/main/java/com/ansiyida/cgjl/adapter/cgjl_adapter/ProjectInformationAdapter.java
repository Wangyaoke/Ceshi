package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.cgjl_bean.GetProjectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectInformationAdapter extends RecyclerView.Adapter<ProjectInformationAdapter.ViewHolder> {

    private Context context;
    private List<GetProjectBean.DataBean> list = new ArrayList<>();

    public ProjectInformationAdapter(Context context, List<GetProjectBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.project_information_layout, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            if (list.get(position).getTitle() != null && list.get(position).getPublishTime() != null) {
                //订单状态
                holder.tvStatus.setText(list.get(position).getTitle());
                //时间
                holder.tvTime.setText(list.get(position).getPublishTime().substring(0, 10));
                if (position == 0) {
                    //黄色的圆点
                    holder.ivStatus.setImageResource(R.drawable.logistics_shape_circle_red);
                    //黄色的竖线
                    if (list.size() < 2) {
                        holder.ivLine.setVisibility(View.GONE);
                    }
                } else {
                    holder.ivStatus.setImageResource(R.drawable.logistics_shape_circle_red);
                    if (position == list.size() - 1) {
                        //灰色的竖线
                        holder.ivLine.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.iv_status)
        ImageView ivStatus;
        @Bind(R.id.iv_line)
        ImageView ivLine;
        @Bind(R.id.tv_status)
        TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
