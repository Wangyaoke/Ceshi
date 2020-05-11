package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.sourceTypeBean;

import java.util.ArrayList;
import java.util.List;

public class TitleRecyclerAdapter extends RecyclerView.Adapter<TitleRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<sourceTypeBean.DataBean> sourceList = new ArrayList<>();
    private int Position;
    public TitleRecyclerAdapter(Context context, List<sourceTypeBean.DataBean> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
    }
    public void setSelection(int position){
        this.Position=position;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.title_recycler_layout,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position==Position){
            holder.title.setTextColor(context.getResources().getColor(R.color.back_dark));
        }else{
            holder.title.setTextColor(context.getResources().getColor(R.color.default_text_night_day));
        }
        holder.title.setText(sourceList.get(position).getname());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titleRecyclerListnear.TitleClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
        }
    }
    public interface  TitleRecyclerListnear{
        void TitleClick(int position);
    }
    public TitleRecyclerListnear titleRecyclerListnear;
    public void setTitleRecyclerListnear(TitleRecyclerListnear titleRecyclerListnear){
        this.titleRecyclerListnear = titleRecyclerListnear;
    }
}
