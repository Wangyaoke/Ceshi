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
import com.ansiyida.cgjl.bean.VisitedDYBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ansiyida on 2018/6/11.
 */
public class VisitedAdapter extends RecyclerView.Adapter {
    private List<VisitedDYBean.DataBean> lists;
    private Context context;

    public VisitedAdapter(Context context, List<VisitedDYBean.DataBean> lists) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0://-------------------0.头部信息
                return new TopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dy_detail_top, parent, false));
            case 1://-------------------1.没有图片的订阅文章
                return new NoPhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_txt_dingyue, parent, false));
            case 2://-------------------2.一张图片的订阅文章
                return new OnePhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_pic_dingyue, parent, false));
            case 3://-------------------3.三张图片的订阅文章
                return new ThreePhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_threepic_dingyue, parent, false));
            case 4://-------------------4.一张大图片的订阅文章
                return new BigPhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_bigpic_dingyue, parent, false));
            case 5://-------------------5.视频订阅文章
                return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video_dingyue, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final VisitedDYBean.DataBean contentBean = lists.get(position);
            if (holder instanceof NoPhotoViewHolder){//----------------------------------------------1.没有图片的订阅文章（P）
                NoPhotoViewHolder viewHolder= (NoPhotoViewHolder) holder;
                String headImg = contentBean.getLogo();
                if (headImg!=null&&!"".equals(headImg)){
                    viewHolder.iv_head.setVisibility(View.VISIBLE);
                    Glide.with(context).load(headImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
                }else {
                    viewHolder.iv_head.setVisibility(View.GONE);
                }
                viewHolder.tv_name.setText(contentBean.getSource());
                String isusetime = contentBean.getIsusetime();
                if (isusetime!=null){
                    isusetime=isusetime.split(" ")[0];
                }else {
                    isusetime="";
                }
                viewHolder.tv_time.setText(isusetime);
                String translateTitle = contentBean.getTranslateTitle();
                if (translateTitle!=null&&!"".equals(translateTitle)){
                    viewHolder.tv_des.setText(translateTitle);
                }else {
                    viewHolder.tv_des.setText(contentBean.getTitle());
                }
                viewHolder.tv_zanCount.setText("0");
                viewHolder.tv_collegeCount.setText("0");
                viewHolder.tv_shareCount.setText("0");
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", contentBean.getType());
                        intent.putExtra("id", contentBean.getPkey()+"");
                        context.startActivity(intent);
                    }
                });

            }else if (holder instanceof OnePhotoViewHolder){//---------------------------------------------2.一张图片的订阅文章（P）
                OnePhotoViewHolder viewHolder= (OnePhotoViewHolder) holder;
                String headImg = contentBean.getLogo();
                if (headImg!=null&&!"".equals(headImg)){
                    viewHolder.iv_head.setVisibility(View.VISIBLE);
                    Glide.with(context).load(headImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
                }else {
                    viewHolder.iv_head.setVisibility(View.GONE);
                }
                viewHolder.tv_name.setText(contentBean.getSource());
                String isusetime = contentBean.getIsusetime();
                if (isusetime!=null){
                    isusetime=isusetime.split(" ")[0];
                }else {
                    isusetime="";
                }
                viewHolder.tv_time.setText(isusetime);
                String translateTitle = contentBean.getTranslateTitle();
                if (translateTitle!=null&&!"".equals(translateTitle)){
                    viewHolder.tv_des.setText(translateTitle);
                }else {
                    viewHolder.tv_des.setText(contentBean.getTitle());
                }
                viewHolder.tv_zanCount.setText("0");
                viewHolder.tv_collegeCount.setText("0");
                viewHolder.tv_shareCount.setText("0");
                String imgpath = contentBean.getImgpath();
                Glide.with(context).load(imgpath).centerCrop().into(viewHolder.iv_pic);
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", contentBean.getType());
                        intent.putExtra("id", contentBean.getPkey()+"");
                        context.startActivity(intent);
                    }
                });

            }else if (holder instanceof ThreePhotoViewHolder){//-------------------------------------------3.三张图片的订阅文章（T）
                ThreePhotoViewHolder viewHolder= (ThreePhotoViewHolder) holder;
                String headImg = contentBean.getLogo();
                if (headImg!=null&&!"".equals(headImg)){
                    viewHolder.iv_head.setVisibility(View.VISIBLE);
                    Glide.with(context).load(headImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
                }else {
                    viewHolder.iv_head.setVisibility(View.GONE);
                }
                viewHolder.tv_name.setText(contentBean.getSource());
                String isusetime = contentBean.getIsusetime();
                if (isusetime!=null){
                    isusetime=isusetime.split(" ")[0];
                }else {
                    isusetime="";
                }
                viewHolder.tv_time.setText(isusetime);
                String translateTitle = contentBean.getTranslateTitle();
                if (translateTitle!=null&&!"".equals(translateTitle)){
                    viewHolder.tv_des.setText(translateTitle);
                }else {
                    viewHolder.tv_des.setText(contentBean.getTitle());
                }
                viewHolder.tv_zanCount.setText("0");
                viewHolder.tv_collegeCount.setText("0");
                viewHolder.tv_shareCount.setText("0");
                List<String> arr= (List<String>) contentBean.getImgArray();
                String img1=arr.get(0);
                String img2=arr.get(1);
                String img3=arr.get(2);
                Glide.with(context).load(img1).centerCrop().into(viewHolder.iv_pic_one);
                Glide.with(context).load(img2).centerCrop().into(viewHolder.iv_pic_two);
                Glide.with(context).load(img3).centerCrop().into(viewHolder.iv_pic_three);
                int size = contentBean.getContent().split("c_y_x").length-1;
                if (size>3){
                    viewHolder.tv_picNum.setVisibility(View.VISIBLE);
                    viewHolder.tv_picNum.setText(size+"图");
                }else {
                    viewHolder.tv_picNum.setVisibility(View.GONE);
                }
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PhotoDetailsActivity.class);
                        intent.putExtra("type", contentBean.getType());
                        intent.putExtra("id", contentBean.getPkey()+"");
                        context.startActivity(intent);
                    }
                });

            }else if (holder instanceof BigPhotoViewHolder){//---------------------------------------------4.一张大图片的订阅文章（T）
                BigPhotoViewHolder viewHolder= (BigPhotoViewHolder) holder;
                String headImg = contentBean.getLogo();
                if (headImg!=null&&!"".equals(headImg)){
                    viewHolder.iv_head.setVisibility(View.VISIBLE);
                    Glide.with(context).load(headImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
                }else {
                    viewHolder.iv_head.setVisibility(View.GONE);
                }
                viewHolder.tv_name.setText(contentBean.getSource());
                String isusetime = contentBean.getIsusetime();
                if (isusetime!=null){
                    isusetime=isusetime.split(" ")[0];
                }else {
                    isusetime="";
                }
                viewHolder.tv_time.setText(isusetime);
                String translateTitle = contentBean.getTranslateTitle();
                if (translateTitle!=null&&!"".equals(translateTitle)){
                    viewHolder.tv_des.setText(translateTitle);
                }else {
                    viewHolder.tv_des.setText(contentBean.getTitle());
                }
                viewHolder.tv_zanCount.setText("0");
                viewHolder.tv_collegeCount.setText("0");
                viewHolder.tv_shareCount.setText("0");
                String imgpath = contentBean.getImgpath();
                Glide.with(context).load(imgpath).centerCrop().into(viewHolder.iv_pic);
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PhotoDetailsActivity.class);
                        intent.putExtra("type", contentBean.getType());
                        intent.putExtra("id", contentBean.getPkey()+"");
                        context.startActivity(intent);
                    }
                });

            }else if (holder instanceof VideoViewHolder){//------------------------------------------------5.视频订阅文章(S)
                VideoViewHolder viewHolder= (VideoViewHolder) holder;
                String headImg = contentBean.getLogo();
                if (headImg!=null&&!"".equals(headImg)){
                    viewHolder.iv_head.setVisibility(View.VISIBLE);
                    Glide.with(context).load(headImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
                }else {
                    viewHolder.iv_head.setVisibility(View.GONE);
                }
                viewHolder.tv_name.setText(contentBean.getSource());
                String isusetime = contentBean.getIsusetime();
                if (isusetime!=null){
                    isusetime=isusetime.split(" ")[0];
                }else {
                    isusetime="";
                }
                viewHolder.tv_time.setText(isusetime);
                String translateTitle = contentBean.getTranslateTitle();
                if (translateTitle!=null&&!"".equals(translateTitle)){
                    viewHolder.tv_des.setText(translateTitle);
                }else {
                    viewHolder.tv_des.setText(contentBean.getTitle());
                }
                viewHolder.tv_zanCount.setText("0");
                viewHolder.tv_collegeCount.setText("0");
                viewHolder.tv_shareCount.setText("0");
                String imgpath = contentBean.getImgpath();
                Glide.with(context).load(imgpath).centerCrop().into(viewHolder.iv_pic);
                viewHolder.tv_vTime.setText(contentBean.getVtime());

                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, VideoDetailsActivity.class);
                        intent.putExtra("type", contentBean.getType());
                        intent.putExtra("id", contentBean.getPkey()+"");
                        context.startActivity(intent);
                    }
                });
            }
        }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
            if (lists!=null&&lists.size()>0){
                VisitedDYBean.DataBean contentBean = lists.get(position);
                switch (contentBean.getType()) {
                    case "P":
                        String imgpath = contentBean.getImgpath();
                        if ("".equals(imgpath)){
                            type=1;
                        }else {
                            type=2;
                        }
                        break;
                    case "S":
                        type=5;

                        break;
                    case "T":
                        List<String> arr= (List<String>) contentBean.getImgArray();
                        if (arr!=null&&arr.size()>=3){
                            type=3;
                        }else {
                            type=4;
                        }
                        break;
                    case "C":
                        type=1;

                        break;
                    case "D":
                        type=1;
                        break;
                }
            }
            return type;

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_touXiang)
        ImageView iv_touXiang;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.iv_dingYue)
        ImageView iv_dingYue;
        @Bind(R.id.iv_back)
        ImageView iv_back;
        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class OnePhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_pic)
        ImageView iv_pic;
        @Bind(R.id.iv_head)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.tv_shareCount)
        TextView tv_shareCount;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        public OnePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ThreePhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_head)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.tv_shareCount)
        TextView tv_shareCount;
        @Bind(R.id.iv_pic_one)
        ImageView iv_pic_one;
        @Bind(R.id.iv_pic_two)
        ImageView iv_pic_two;
        @Bind(R.id.iv_pic_three)
        ImageView iv_pic_three;
        @Bind(R.id.tv_picNum)
        TextView tv_picNum;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        public ThreePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class BigPhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_pic)
        ImageView iv_pic;
        @Bind(R.id.iv_head)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.tv_shareCount)
        TextView tv_shareCount;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        public BigPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NoPhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_head)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.tv_shareCount)
        TextView tv_shareCount;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        public NoPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class VideoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_pic)
        ImageView iv_pic;
        @Bind(R.id.iv_head)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_des)
        TextView tv_des;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.tv_shareCount)
        TextView tv_shareCount;
        @Bind(R.id.tv_videoTime)
        TextView tv_vTime;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
