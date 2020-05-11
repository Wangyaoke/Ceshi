package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.ProjectInformationActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectFollowUpRecyclerViewAdapyer extends RecyclerView.Adapter<ProjectFollowUpRecyclerViewAdapyer.ViewHolder> {
    //判断是不是要展示选择按钮
    private boolean CheckBol;
    //判断是要全选
    private boolean CheckAll;
    //一个一个的选中记录
    public List<String> jlList = new ArrayList<>();
    private List<ProjectListBean.DataBean> ProjectList = new ArrayList<>();
    private String article;
    private String source;

    public void setChecked(boolean bol, boolean AllCheck,List<ProjectListBean.DataBean> projectList) {
        this.ProjectList = projectList;
        jlList = new ArrayList<>();
        CheckBol = bol;
        CheckAll = AllCheck;
        if(CheckAll) {
            for (int i = 0; i < ProjectList.size(); i++) {
                jlList.add(ProjectList.get(i).getArticle());
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.projectfollowup_recyclerview_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        article = ProjectList.get(position).getArticle();
        source = judgesource(ProjectList.get(position).getSource());
        holder.titleContent.setText(article);
        holder.reclcyerResouce.setText(source);
        final Context context = holder.itemView.getContext();
        if (CheckBol) {
            holder.recyclerCheckbox.setVisibility(View.VISIBLE);
            holder.detailImage.setVisibility(View.GONE);
        } else {
            holder.recyclerCheckbox.setVisibility(View.GONE);
            holder.detailImage.setVisibility(View.VISIBLE);
        }

        if(CheckAll){
            holder.recyclerCheckbox.setChecked(true);
        }else{
            holder.recyclerCheckbox.setChecked(false);
        }

        holder.detailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectInformationActivity.class);
                intent.putExtra("title",ProjectList.get(position).getArticle());
                intent.putExtra("source",ProjectList.get(position).getSource());
                context .startActivity(intent);
            }
        });

        holder.recyclerCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //一个一个选中的情况
                if(holder.recyclerCheckbox.isChecked()){
                    if(jlList.size()==ProjectList.size()){
                        jlList.clear();
                    }
                    jlList.add(ProjectList.get(position).getArticle());
                    //判断添加的是不是等于集合的个数，是的话选中全选按钮
                    if(jlList.size()==ProjectList.size()){
                        projectFollowListnear.Allcheck();
                    }

                }else{
                    for (int i = 0; i <jlList.size(); i++) {
                        if(jlList.get(i).equals(ProjectList.get(position).getArticle())){
                            jlList.remove(i);
                        }
                    }
                    //判断添加的是不是等于集合的个数，不是的话取消全选按钮
                    if(jlList.size()!=ProjectList.size()){
                        projectFollowListnear.NoAllCheck();
                    }
                }
            }
        });
    }

    private String judgesource(String source) {
        if(source.contains("装备采购网")){
            return "装备采购网";
        }else if(source.contains("政府采购网")){
            return "政府采购网";
        }
        return source;
    }

    @Override
    public int getItemCount() {
        return ProjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_checkbox)
        CheckBox recyclerCheckbox;
        @Bind(R.id.detail_image)
        ImageView detailImage;
        @Bind(R.id.title_content)
        TextView titleContent;
        @Bind(R.id.recycler_address)
        TextView recyclerAddress;
        @Bind(R.id.reclcyer_resouce)
        TextView reclcyerResouce;
        @Bind(R.id.reclcyer_time)
        TextView reclcyerTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public List<String> getDelList(){
        return jlList;
    }
    public interface ProjectFollowListnear{
        //一个一个全选
        void Allcheck();
        //取消全选
        void NoAllCheck();
    }
    public  ProjectFollowListnear projectFollowListnear;
    public void setProjectFollowListnear(ProjectFollowListnear projectFollowListnear){
        this.projectFollowListnear = projectFollowListnear;
    }
}
