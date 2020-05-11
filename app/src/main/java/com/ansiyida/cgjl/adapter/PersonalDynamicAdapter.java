package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoDetailsActivity;
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.bean.PersonalDynamicBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ansiyida on 2018/1/31.
 */
public class PersonalDynamicAdapter extends RecyclerView.Adapter {
    private ArrayList<PersonalDynamicBean.DataBean.ListBean> lists;
    private Context context;

    public PersonalDynamicAdapter(Context context, ArrayList<PersonalDynamicBean.DataBean.ListBean> lists) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:        //1.个人动态发表文章

                return new PublishViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dynamic_publish, parent, false));

            default:

                return new PublishViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_dynamic_publish, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PublishViewHolder) {
            PublishViewHolder viewHolder = (PublishViewHolder) holder;
            final PersonalDynamicBean.DataBean.ListBean listBean = lists.get(position);
            String touXiangUrl = listBean.getJmi_img();
            if (touXiangUrl==null){
                touXiangUrl="";
            }
            Glide.with(context).load(touXiangUrl).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_touXiang);
            String userName = listBean.getJmi_username();
            viewHolder.tv_name.setText(userName);
            String jc_type = listBean.getArt_type();
            String str_type = "";
            switch (jc_type) {
                case "S":
                    str_type = "视频";
                    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, VideoDetailsActivity.class);
                            intent.putExtra("type", listBean.getArt_type() + "");
                            intent.putExtra("id", listBean.getArt_id() + "");
                            context.startActivity(intent);
                        }
                    });
                    break;
                case "T":
                    str_type = "图片";
                    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PhotoDetailsActivity.class);
                            intent.putExtra("type", listBean.getArt_type() + "");
                            intent.putExtra("id", listBean.getArt_id() + "");
                            context.startActivity(intent);
                        }
                    });
                    break;
                case "D":
                    str_type = "提问";
                    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, YanTaoActivity.class);
                            intent.putExtra("jc_id", listBean.getArt_id()+"");
                            context.startActivity(intent);
                        }
                    });
                    break;
                default:
                    str_type = "文章";
                    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, NewsDetailsActivity.class);
                            intent.putExtra("type", listBean.getArt_type() + "");
                            intent.putExtra("id", listBean.getArt_id() + "");
                            context.startActivity(intent);
                        }
                    });

                    break;
            }
            viewHolder.tv_type.setText("发表了" + str_type);
            viewHolder.tv_content.setText(listBean.getArt_title());
            String art_time = listBean.getArt_time();
            if (art_time!=null&&art_time.length()>10){
                art_time=art_time.substring(0,10);
            }
            viewHolder.tv_time.setText(art_time+"");
            String jca_img = listBean.getArt_img();
            if (jca_img != null && !"".equals(jca_img)) {
                viewHolder.iv_image.setVisibility(View.VISIBLE);
                Glide.with(context).load(jca_img).centerCrop().into(viewHolder.iv_image);

            } else {
                viewHolder.iv_image.setVisibility(View.GONE);
            }


        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {

        return 1;
    }

    class PublishViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_touXiang)
        ImageView iv_touXiang;
        @Bind(R.id.iv_image)
        ImageView iv_image;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_type)
        TextView tv_type;
        @Bind(R.id.tv_content)
        TextView tv_content;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.relative)
        RelativeLayout relative;

        public PublishViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
