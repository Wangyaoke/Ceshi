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
import com.ansiyida.cgjl.bean.purchaseDemandBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class purchaseDemandChildAdapter extends RecyclerView.Adapter<purchaseDemandChildAdapter.ViewHolder> {
    private List<purchaseDemandBean.DataBean> list;

    public purchaseDemandChildAdapter(List<purchaseDemandBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.lawlist_shouye, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).gettitle());
        if(list.get(position).getisFinish()) {
            holder.finish_line.setVisibility(View.VISIBLE);
            holder.finish_text.setVisibility(View.VISIBLE);
            holder.finish_text1.setVisibility(View.GONE);
            holder.finish_text.setText("已对接");
        } else {
            holder.finish_line.setVisibility(View.VISIBLE);
            holder.finish_text1.setVisibility(View.VISIBLE);
            holder.finish_text.setVisibility(View.GONE);
            holder.finish_text1.setText("未对接");
        }
        holder.creedtime.setText(changeTime(list.get(position).getcreateTime()));
        holder.line_history_top.setVisibility(View.GONE);

        if(list.get(position).getisCollection())
            holder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
        else{
            holder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
        }
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                intent.putExtra("type", "CX_dy");
                intent.putExtra("id",list.get(position).getid());
                if(list.get(position).getisCollection())
                    intent.putExtra("idcollect","true");
                else
                    intent.putExtra("idcollect","false");
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.timelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailsActivity.class);
                intent.putExtra("type", "CX_dy");
                intent.putExtra("id",list.get(position).getid());
                if(list.get(position).getisCollection())
                    intent.putExtra("idcollect","true");
                else
                    intent.putExtra("idcollect","false");
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("true".equals(SharedPreferenceUtils.get(holder.itemView.getContext(), "vistor", ""))) {
                            if (!list.get(position).getisCollection()) {
                                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(holder.itemView.getContext(), "app_token", ""), "purchaseDemand",list.get(position).getid());
                                call.enqueue(new Callback<DefaultBean>() {
                                    @Override
                                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                        if (response.isSuccessful()) {
                                            //   ToastUtils.showMessage(context, response.body().getMessage());
                                            if ("200".equals(response.body().getStatus())) {
                                                holder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
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
                                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(holder.itemView.getContext(), "app_token", ""), "purchaseDemand",list.get(position).getid());
                                call.enqueue(new Callback<DefaultBean>() {
                                    @Override
                                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                        if (response.isSuccessful()) {
                                            ToastUtils.showMessage(holder.itemView.getContext(), response.body().getMessage());
                                            if ("200".equals(response.body().getStatus())) {
                                                holder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
                                                ToastUtils.showMessage(holder.itemView.getContext(), "成功取消收藏！");
                                                list.get(position).setisCollection(false);
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
                        }
                else
                    ToastUtils.showMessage(holder.itemView.getContext(), "登录后才能收藏");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.finish_text)
        TextView finish_text;
        @Bind(R.id.finish_text1)
        TextView finish_text1;
        @Bind(R.id.finish_line)
        LinearLayout finish_line;

        @Bind(R.id.line_history_top)
        LinearLayout line_history_top;

        @Bind(R.id.delete_pic_fragment1)
        ImageView dismiss;
        @Bind(R.id.title_pic_fragment1)
        TextView title;
        @Bind(R.id.gonggao_time)
        TextView creedtime;
        @Bind({R.id.time_lin})
        LinearLayout timelin;

        public ViewHolder(View itemView) {
            super(itemView);
                ButterKnife.bind(this, itemView);
          /*  finish_text = itemView.findViewById(R.id.title_pic_fragment1);
            finish_text1 = itemView.findViewById(R.id.delete_pic_fragment1);
            finish_line = itemView.findViewById(R.id.newsSource_pic_fragment1);
            linear_user = itemView.findViewById(R.id.newsTime_pic_fragment1);
            relativeLayout = itemView.findViewById(R.id.news_source);
            line_history_top = itemView.findViewById(R.id.gonggao_time);
            provinceorrescou = itemView.findViewById(R.id.provinceorrescou);*/
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
