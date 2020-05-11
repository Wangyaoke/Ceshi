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
import com.ansiyida.cgjl.activity.cgjl_activity.KnowledgeDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyTypeBean;

import java.util.List;

public class StudyRecyclerAdapter extends RecyclerView.Adapter<StudyRecyclerAdapter.ViewHolder> {
    private List<StudyTypeBean.DataBean> list ;
    private Context context;

    public StudyRecyclerAdapter(List<StudyTypeBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = View.inflate(context, R.layout.study_recycler_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(position%3==0){
            holder.relativeLayout.setBackgroundResource(R.mipmap.stufy3);
        }else if(position%3==1){
            holder.relativeLayout.setBackgroundResource(R.mipmap.stufy1);
        }else{
            holder.relativeLayout.setBackgroundResource(R.mipmap.stufy2);
        }
        holder.study_type_name.setText(list.get(position).getTitle());
        holder.startstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, KnowledgeDetailActivity.class);
                intent1.putExtra("resoure","fragment2");
                intent1.putExtra("studyTypeId",list.get(position).getId()+"");
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView study_back;
        TextView study_type_name;
        TextView startstudy;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.relative_1);
            study_back = itemView.findViewById(R.id.study_backImage);
            study_type_name = itemView.findViewById(R.id.study_type_name);
            startstudy = itemView.findViewById(R.id.start_study);
        }
    }
}
