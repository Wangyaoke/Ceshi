package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyRecyclerBean;

import java.util.List;

public class StudyMenuAdapter extends RecyclerView.Adapter<StudyMenuAdapter.ViewHolder> {
    private List<StudyRecyclerBean.DataBean.AllBean> list;
    private Context context;
    private int JudgeNum;
    private int Type ;
    public StudyMenuAdapter(List<StudyRecyclerBean.DataBean.AllBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void SetList(List<StudyRecyclerBean.DataBean.AllBean> list){
        this.list = list;
        Type=1;
    }
    public void JudgeColor(int position){
        this.JudgeNum= position;
        Type=2;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.activity_knowledgemenu_recycler,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.search_title_show.setText((position+1)+"."+list.get(position).getTitle());
        if (position == JudgeNum && Type==2){
            holder.search_title_show.setTextColor(context.getResources().getColor(R.color.F4E86ED));
        }else{
            holder.search_title_show.setTextColor(context.getResources().getColor(R.color.F6));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studyMenuAdapterClick.clickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView search_title_show;
        public ViewHolder(View itemView) {
            super(itemView);
            search_title_show = itemView.findViewById(R.id.search_title_show);
        }
    }
    //接口回调
    public interface  StudyMenuAdapterClick{
        void clickItem(int position);
    }
    public StudyMenuAdapterClick studyMenuAdapterClick;
    public void setStudyMenuAdapterClick(StudyMenuAdapterClick studyMenuAdapterClick){
        this.studyMenuAdapterClick= studyMenuAdapterClick;
    }
}
