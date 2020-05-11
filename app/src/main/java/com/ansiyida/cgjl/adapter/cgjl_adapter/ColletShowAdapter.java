package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.KnowledgeDetailActivity;
import com.ansiyida.cgjl.bean.college_bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ColletShowAdapter extends RecyclerView.Adapter<ColletShowAdapter.ViewHolder> {
    private List<college_bean.DataBean.list_law_bean> list;
    private Context context;
    private String mode;

    public interface ColletShowAdapterListnear{
        void noCollet(String id, int Position);
    }
    private ColletShowAdapterListnear colletShowAdapterListnear;
    public void setColletShowAdapterListnear(ColletShowAdapterListnear colletShowAdapterListnear){
        this.colletShowAdapterListnear = colletShowAdapterListnear;
    }
    public ColletShowAdapter(List<college_bean.DataBean.list_law_bean> list, Context context,String mode) {
        this.list = list;
        this.context = context;
        this.mode = mode;
    }

    public void setList(List<college_bean.DataBean.list_law_bean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.activity_knowledgesearch_recycler, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.search_title_show.setText(list.get(position).gettitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(list.get(position).getcreateTime());
        Date date = new Date(lt);
        holder.search_time.setText(simpleDateFormat.format(date));
        holder.search_image.setImageResource(R.mipmap.yantao_college_yes);

        holder.search_title_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("viewpointInfo")){
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "VP");
                    intent.putExtra("id", list.get(position).getinfoId());
                    intent.putExtra("title", list.get(position).gettitle());
                    context.startActivity(intent);
                }else if(mode.equals("knowledge")) {
                    Intent intent = new Intent(context, KnowledgeDetailActivity.class);
                    intent.putExtra("resoure", "Collet");
                    intent.putExtra("studyTypeId", list.get(position).gettype());
                    intent.putExtra("title",list.get(position).gettitle());
                    intent.putExtra("id", list.get(position).getinfoId() + "");
                    context.startActivity(intent);
                }
            }
        });
        holder.search_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("viewpointInfo")){
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "VP");
                    intent.putExtra("id", list.get(position).getinfoId());
                    intent.putExtra("title", list.get(position).gettitle());
                    context.startActivity(intent);
                }else if(mode.equals("knowledge")) {
                    Intent intent = new Intent(context, KnowledgeDetailActivity.class);
                    intent.putExtra("resoure", "Collet");
                    intent.putExtra("studyTypeId", list.get(position).gettype());
                    intent.putExtra("id", list.get(position).getinfoId() + "");
                    context.startActivity(intent);
                }
            }
        });
        holder.search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击就是取消点赞
                colletShowAdapterListnear.noCollet(list.get(position).getinfoId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView search_title_show;
        TextView search_time;
        ImageView search_image;

        public ViewHolder(View itemView) {
            super(itemView);
            search_title_show = itemView.findViewById(R.id.search_title_show);
            search_time = itemView.findViewById(R.id.knowledge_time);
            search_image = itemView.findViewById(R.id.knowledge_image);
        }
    }

}

