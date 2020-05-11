package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.bumptech.glide.Glide;

import java.util.List;

public class CgjlMore_Adapter extends RecyclerView.Adapter<CgjlMore_Adapter.ViewHolder> {
    private List<NewBean2> newBean2List;
    private Context context;
    public CgjlMore_Adapter(List<NewBean2> newBean2List, Context context) {
        this.newBean2List = newBean2List;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_viewpoint_pic,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("type", "VP");
                intent.putExtra("id", newBean2List.get(position).getId());
                intent.putExtra("title", newBean2List.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        holder.source.setText(newBean2List.get(position).getsource());
        holder.time.setText(newBean2List.get(position).getVtime());
        if(newBean2List.get(position).getImg().equals("") || newBean2List.get(position).getImg() ==null){
            holder.onepicRel.setVisibility(View.GONE);
        }else {
            holder.onepicRel.setVisibility(View.VISIBLE);
        }
        Glide.with((context.getApplicationContext())).load(newBean2List.get(position).getImg()).into(holder.pic);
        holder.title_pic_fragment1.setText(newBean2List.get(position).getContent());
}

    @Override
    public int getItemCount() {
        return newBean2List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout relativeLayout;
        TextView title_pic_fragment1;
        TextView source;
        TextView time;
        ImageView pic;
        RelativeLayout onepicRel;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout  = itemView.findViewById(R.id.relative);
            title_pic_fragment1  = itemView.findViewById(R.id.title_pic_fragment1);
            source  = itemView.findViewById(R.id.user_touXiang);
            time  = itemView.findViewById(R.id.user_name);
            pic  = itemView.findViewById(R.id.onePic_pic_fragment1);
            onepicRel = itemView.findViewById(R.id.onepic_rel);
        }
    }
}
