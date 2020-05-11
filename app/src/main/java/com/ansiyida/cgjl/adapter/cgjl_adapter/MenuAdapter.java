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
import com.ansiyida.cgjl.activity.ClassifiedActivity1;
import com.ansiyida.cgjl.activity.bidding_class_activity1;
import com.ansiyida.cgjl.bean.cgjl_bean.MenuBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context context;
    private List<MenuBean.DataBean> list;

    public MenuAdapter(Context context, List<MenuBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(context, R.layout.menu_layout, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = list.get(position).getName();
        Glide.with(context).load(list.get(position).getIcon()).centerCrop().into(holder.menuIcon);
        holder.menuTitle.setText(name);
        holder.menu_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.equals("涉密采购") || name.equals("采购需求")){
                    Intent intent_read = new Intent(context, ClassifiedActivity1.class);
                    intent_read.putExtra("title", name);
                    context.startActivity(intent_read);
                }else{
                    Intent intent_read = new Intent(context, bidding_class_activity1.class);
                    intent_read.putExtra("title", name);
                    context.startActivity(intent_read);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.menu_icon)
        ImageView menuIcon;
        @Bind(R.id.menu_title)
        TextView menuTitle;
        @Bind(R.id.menu_rel)
        RelativeLayout menu_rel;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
