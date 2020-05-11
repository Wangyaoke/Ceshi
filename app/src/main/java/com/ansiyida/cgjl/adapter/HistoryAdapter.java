package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoDetailsActivity;
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.bean.CollegeBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.listener.ICollegeActivity;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/12/28.
 */
public class HistoryAdapter extends RecyclerView.Adapter {
    private ArrayList<CollegeBean> list;
    private ICollegeActivity iCollegeActivity;
    private Context context;

    public HistoryAdapter(ICollegeActivity iCollegeActivity, ArrayList<CollegeBean> list, Context context) {
        this.list = list;
        this.iCollegeActivity = iCollegeActivity;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:          //1.有图片新闻条目
                return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_college_new, parent,false));
            case 2:          //2.无图片新闻条目
                return new TimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_college_time, parent,false));
            default:
                return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        String picUrl = list.get(position).getImgUrl();
        if (picUrl!=null&&!"".equals(picUrl)){
            //有图
            return 1;
        }else {
            //无图
            return 2;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof TimeViewHolder) {
            final TimeViewHolder viewHolder = (TimeViewHolder) holder;
            //我让第一个对象的Edit属性去当是否进入编辑模式
            if (list.get(0).getEdit()) {
                viewHolder.checkBox.setChecked(false);
                viewHolder.checkBox.setVisibility(View.VISIBLE);
            } else {
                viewHolder.checkBox.setVisibility(View.GONE);
            }
            final CollegeBean collegeBean = list.get(position);


            if (position == 0) {
                viewHolder.relativeTop.setVisibility(View.VISIBLE);
                viewHolder.time.setText(collegeBean.getTime());
            } else {
                if (collegeBean.getTime().equals(list.get(position - 1).getTime())) {

                    viewHolder.relativeTop.setVisibility(View.GONE);
                } else {
                    viewHolder.relativeTop.setVisibility(View.VISIBLE);
                    viewHolder.time.setText(collegeBean.getTime());
                }
            }
            if (collegeBean.getIsCheck()) {
                viewHolder.checkBox.setChecked(true);
            } else {
                viewHolder.checkBox.setChecked(false);
            }

            viewHolder.relative_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(0).getEdit()) {
                        boolean checked = viewHolder.checkBox.isChecked();
                        if (checked) {
                            viewHolder.checkBox.setChecked(false);
                            collegeBean.setIsCheck(false);
                            iCollegeActivity.addDeleteCount(-1);
                        } else {
                            viewHolder.checkBox.setChecked(true);
                            collegeBean.setIsCheck(true);
                            iCollegeActivity.addDeleteCount(1);
                        }
                    } else {
                        Intent intent=null;
                        switch (collegeBean.getType()){
                            case "P"://普通
                                intent = new Intent(context, NewsDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);

                                break;
                            case "S"://视频
                                intent = new Intent(context, VideoDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);

                                break;
                            case "T"://图片
                                intent = new Intent(context, PhotoDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);

                                break;
                            case "C"://用户投稿
                                intent = new Intent(context, NewsDetailsActivity.class);
                                intent.putExtra("type",collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);
                                break;
                        }
                    }
                }
            });
            if (collegeBean.getTitle()!=null){
                viewHolder.newBody.setText(collegeBean.getTitle());
            }else {
                viewHolder.newBody.setText("");

            }

        } else if (holder instanceof NewsViewHolder) {

            final NewsViewHolder viewHolder = (NewsViewHolder) holder;
            //我让第一个对象的Edit属性去当是否进入编辑模式
            if (list.get(0).getEdit()) {
                viewHolder.checkBox.setChecked(false);
                viewHolder.checkBox.setVisibility(View.VISIBLE);
            } else {
                viewHolder.checkBox.setVisibility(View.GONE);
            }
            final CollegeBean collegeBean = list.get(position);

            if (position == 0) {
                viewHolder.relativeTop.setVisibility(View.VISIBLE);
                viewHolder.time.setText(collegeBean.getTime());
            } else {
                if (collegeBean.getTime().equals(list.get(position - 1).getTime())) {

                    viewHolder.relativeTop.setVisibility(View.GONE);
                } else {
                    viewHolder.relativeTop.setVisibility(View.VISIBLE);
                    viewHolder.time.setText(collegeBean.getTime());
                }
            }
            if (collegeBean.getIsCheck()) {
                viewHolder.checkBox.setChecked(true);
            } else {
                viewHolder.checkBox.setChecked(false);
            }

            viewHolder.relative_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(0).getEdit()) {
                        boolean checked = viewHolder.checkBox.isChecked();
                        if (checked) {
                            viewHolder.checkBox.setChecked(false);
                            collegeBean.setIsCheck(false);
                            iCollegeActivity.addDeleteCount(-1);
                        } else {
                            viewHolder.checkBox.setChecked(true);
                            collegeBean.setIsCheck(true);
                            iCollegeActivity.addDeleteCount(1);
                        }
                    } else {
                        Intent intent=null;
                        switch (collegeBean.getType()){
                            case "P"://普通
                                intent = new Intent(context, NewsDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);

                                break;
                            case "S"://视频
                                intent = new Intent(context, VideoDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);
                                break;
                            case "T"://图片
                                intent = new Intent(context, PhotoDetailsActivity.class);
                                intent.putExtra("type", collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);

                                break;
                            case "C"://用户投稿
                                intent = new Intent(context, NewsDetailsActivity.class);
                                intent.putExtra("type",collegeBean.getType());
                                intent.putExtra("id",collegeBean.getId());
                                context.startActivity(intent);
                                break;
                        }
                    }
                }
            });

            if ("S".equals(collegeBean.getType())){
                viewHolder.tv_videoTime.setVisibility(View.VISIBLE);
                viewHolder.tv_videoTime.setText(collegeBean.getVideoTime());
            }else {
                viewHolder.tv_videoTime.setVisibility(View.GONE);

            }
            if (collegeBean.getTitle()!=null){
                viewHolder.newBody.setText(collegeBean.getTitle());
            }else {
                viewHolder.newBody.setText("");
            }
            String imgUrl = collegeBean.getImgUrl();
            if (imgUrl!=null){
                Glide.with(context).load(imgUrl).centerCrop().into(viewHolder.image);
            }else {
                Glide.with(context).load("").centerCrop().into(viewHolder.image);

            }

        }
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.select_collegeNew)
        CheckBox checkBox;
        @Bind(R.id.newBody_collegeNew)
        TextView newBody;
        @Bind(R.id.relative_new)
        RelativeLayout relative_new;
        @Bind(R.id.relative_top)
        RelativeLayout relativeTop;
        @Bind(R.id.time)
        TextView time;

        public TimeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.select_collegeNew)
        CheckBox checkBox;
        @Bind(R.id.newBody_collegeNew)
        TextView newBody;
        @Bind(R.id.newImage_collegeNew)
        ImageView image;
        @Bind(R.id.relative_new)
        RelativeLayout relative_new;
        @Bind(R.id.relative_top)
        RelativeLayout relativeTop;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.tv_videoTime)
        TextView tv_videoTime;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void deleteItem() {
        int lenth = list.size();
        if (lenth > 0) {
            StringBuffer sb=new StringBuffer();
            for (int x = lenth - 1; x > -1; x--) {
                CollegeBean collegeBean = list.get(x);
                if (collegeBean.getIsCheck()) {
                    list.remove(x);
                    sb.append(collegeBean.getJc_id()+",");
                }
            }
            Call<DefaultBean> call= MyApplication.getNetApi().deleteReadHistory((String) SharedPreferenceUtils.get(context, "app_token", ""), sb.toString());
            call.enqueue(new Callback<DefaultBean>() {
                @Override
                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                    if (response.isSuccessful()){
                        if(!"0001".equals(response.body().getStatus())){
                            ToastUtils.showMessage(context,response.body().getMessage()+"");
                        }
                    }else {
                        ToastUtils.showMessage(context,"删除失败");

                    }
                    iCollegeActivity.addDeleteCount(100);
                    notifyDataSetChanged();
                    call.cancel();
                }

                @Override
                public void onFailure(Call<DefaultBean> call, Throwable t) {
                    ToastUtils.showMessage(context,"删除失败");
                    iCollegeActivity.addDeleteCount(100);
                    notifyDataSetChanged();
                    call.cancel();

                }
            });

        } else {
            iCollegeActivity.addDeleteCount(100);
        }
    }

    public void deleteAll() {
        int lenth = list.size();
        for (int x = 0; x < lenth; x++) {
            if (!list.get(x).getIsCheck()) {
                list.get(x).setIsCheck(true);
                iCollegeActivity.addDeleteCount(1);
            }
        }
        notifyDataSetChanged();
    }
    public void cancelAll() {
        int lenth = list.size();
        for (int x = 0; x < lenth; x++) {
            if (list.get(x).getIsCheck()) {
                list.get(x).setIsCheck(false);
                iCollegeActivity.addDeleteCount(-1);
            }
        }
        notifyDataSetChanged();
    }
}
