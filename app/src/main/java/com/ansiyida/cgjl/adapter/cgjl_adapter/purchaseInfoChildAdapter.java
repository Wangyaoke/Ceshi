package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.caigoulist;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class purchaseInfoChildAdapter extends RecyclerView.Adapter<purchaseInfoChildAdapter.ViewHolder> {
    private List<caigoulist.DataBean> list;

    public purchaseInfoChildAdapter(List<caigoulist.DataBean> list) {
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.caigoulist_shouye, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.titlePicFragment1.setText(list.get(position).gettitle());
        holder.gonggaoTime.setText(changeTime(list.get(position).getcreateTime()));
        holder.newsTimePicFragment1.setText(list.get(position).gettype());
        holder.newsSourcePicFragment1.setText(list.get(position).getprovince());
         holder.newsSource.setVisibility(View.GONE);
         if(list.get(position).getisCollection()) {
             holder.deletePicFragment1.setImageResource(R.mipmap.yantao_college_yes);
         }
         else {
             holder.deletePicFragment1.setImageResource(R.mipmap.icon_subscription_default3x);
         }
        holder.deletePicFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("true".equals(SharedPreferenceUtils.get(holder.itemView.getContext(), "vistor", ""))) {
                        if (!list.get(position).getisCollection()) {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(holder.itemView.getContext(), "app_token", ""), "purchaseInfo",  list.get(position).getid());
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        //  ToastUtils.showMessage(context, response.body().getMessage());
                                        if ("200".equals(response.body().getStatus())) {
                                            holder.deletePicFragment1.setImageResource(R.mipmap.yantao_college_yes);
                                            list.get(position).setisCollection(true);
                                        } else
                                            ToastUtils.showMessage(holder.itemView.getContext(), "登录后才能收藏");
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });
                        } else {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(holder.itemView.getContext(), "app_token", ""), "purchaseInfo", list.get(position).getid());
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        // ToastUtils.showMessage(context, response.body().getMessage());
                                        if ("200".equals(response.body().getStatus())) {
                                            holder.deletePicFragment1.setImageResource(R.mipmap.icon_subscription_default3x);
                                            ToastUtils.showMessage(holder.itemView.getContext(), "成功取消收藏！");
                                        }
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });
                        }
                    }else{
                    ToastUtils.showMessage(holder.itemView.getContext(),"请先登录");
                }
                }
        });
         holder.titlePicFragment1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                 intent.putExtra("type", "P");
                 intent.putExtra("id", list.get(position).getid());
                 intent.putExtra("idcollect", "true");
                 intent.putExtra("idcollect", "false");
                 holder.itemView.getContext().startActivity(intent);
             }
         });
         holder.provinceorrescou.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                 intent.putExtra("type", "P");
                 intent.putExtra("id", list.get(position).getid());
                 intent.putExtra("idcollect", "true");
                 intent.putExtra("idcollect", "false");
                 holder.itemView.getContext().startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlePicFragment1;
        ImageView deletePicFragment1;
        TextView newsSourcePicFragment1;
        TextView newsTimePicFragment1;
        TextView newsSource;
        TextView gonggaoTime;
        LinearLayout provinceorrescou;
        public ViewHolder(View itemView) {
            super(itemView);
            titlePicFragment1 = itemView.findViewById(R.id.title_pic_fragment1);
            deletePicFragment1 = itemView.findViewById(R.id.delete_pic_fragment1);
            newsSourcePicFragment1 = itemView.findViewById(R.id.newsSource_pic_fragment1);
            newsTimePicFragment1 = itemView.findViewById(R.id.newsTime_pic_fragment1);
            newsSource = itemView.findViewById(R.id.news_source);
            gonggaoTime = itemView.findViewById(R.id.gonggao_time);
            provinceorrescou = itemView.findViewById(R.id.provinceorrescou);
        }
    }
    public String changeTime(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(time);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }
}
