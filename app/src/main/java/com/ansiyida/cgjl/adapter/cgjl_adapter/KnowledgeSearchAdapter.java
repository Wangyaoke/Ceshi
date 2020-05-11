package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.KnowledgeDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.StudySearchBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnowledgeSearchAdapter extends RecyclerView.Adapter<KnowledgeSearchAdapter.ViewHolder> {
    private List<StudySearchBean.DataBean.ListBean> list ;
    private Context context;
    private String keyword;
    public KnowledgeSearchAdapter(List<StudySearchBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<StudySearchBean.DataBean.ListBean> list,String keyword) {
        this.list = list;
        this.keyword = keyword;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.activity_knowledgesearch_recycler,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SpannableString spannableString = matcherSearchText(context.getResources().getColor(R.color.yellow), list.get(position).getTitle(), keyword);
        holder.search_title_show.setText(spannableString+"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(list.get(position).getCreateTime());
        Date date = new Date(lt);
        holder.search_time.setText(simpleDateFormat.format(date));
        if(list.get(position).getIsCollection() !=0 ){
            holder.search_image.setImageResource(R.mipmap.yantao_college_yes);
        }else{
            holder.search_image.setImageResource(R.mipmap.icon_subscription);
        }

        holder.search_title_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KnowledgeDetailActivity.class);
                intent.putExtra("resoure","knowledgeSearch");
                intent.putExtra("content",list.get(position).getContent());
                intent.putExtra("studyTypeId",list.get(position).getTypeId()+"");
                intent.putExtra("id",list.get(position).getId()+"");
                intent.putExtra("title",list.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        holder.search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getIsCollection() !=0 ){
                    //取消点赞
                   knowledgeSearchListnear.nocollet(list.get(position).getId()+"",position);
                }else{
                    //点赞
                    knowledgeSearchListnear.collet(list.get(position).getId()+"",position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  list.size();
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
    public interface  KnowledgeSearchListnear{
        void collet(String id, int position);
        void nocollet(String id, int position);
    }
    public KnowledgeSearchListnear knowledgeSearchListnear;
    public void setKnowledgeSearchListnear(KnowledgeSearchListnear knowledgeSearchListnear){
        this.knowledgeSearchListnear = knowledgeSearchListnear;
    }
    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}
