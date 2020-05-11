package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.FriendDynamicActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.FansCareBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/1/3.
 */
public class CareAdapter extends RecyclerView.Adapter {
    private ArrayList<FansCareBean.ListBean> list;
    private Context context;
    private ArrayList<Integer> gzList;

    public CareAdapter(ArrayList<FansCareBean.ListBean> list, Context context, ArrayList<Integer> gzList) {
        this.list = list;
        this.context = context;
        this.gzList = gzList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CareViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_care, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final CareViewHolder viewHolder = (CareViewHolder) holder;
        final FansCareBean.ListBean listBean = list.get(position);
        if (gzList.get(position) == 0) {
            viewHolder.image_care.setImageResource(R.mipmap.icon_guanzhu_none);
        } else {
            viewHolder.image_care.setImageResource(R.mipmap.icon_guanzhu_yes);
        }
        viewHolder.image_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sign = "G";
                if (gzList.get(position) == 0) {
                    //未关注
                    sign = "F";
                }
                viewHolder.image_care.setClickable(false);
                Call<DefaultBean2> call = MyApplication.getNetApi().guanZhu(listBean.getJmi_id() + "", sign, "P", (String) SharedPreferenceUtils.get(context.getApplicationContext(), "app_token", ""));
                call.enqueue(new Callback<DefaultBean2>() {
                    @Override
                    public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                        if (response.isSuccessful()) {
                            DefaultBean2 body = response.body();
                            if ("200".equals( response.body().getStatus())) {
                                if (gzList.get(position) == 0) {
                                    ToastUtils.showMessage(context.getApplicationContext(), "关注成功");
                                    viewHolder.image_care.setImageResource(R.mipmap.icon_guanzhu_yes);
                                    gzList.remove(position);
                                    gzList.add(position,1);
                                } else {
                                    ToastUtils.showMessage(context.getApplicationContext(), "取消成功");
                                    viewHolder.image_care.setImageResource(R.mipmap.icon_guanzhu_none);
                                    gzList.remove(position);
                                    gzList.add(position,0);
                                }
                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), "失败");
                            }

                        }
                        viewHolder.image_care.setClickable(true);

                    }

                    @Override
                    public void onFailure(Call<DefaultBean2> call, Throwable t) {
                        ToastUtils.showMessage(context.getApplicationContext(), "解析失败");
                        viewHolder.image_care.setClickable(true);
                    }
                });
            }
        });
        viewHolder.iv_touXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendDynamicActivity.class);
                intent.putExtra("jm_id", listBean.getJmi_id() + "");
                context.startActivity(intent);
            }
        });

        viewHolder.tv_name.setText(listBean.getJmi_username());
        viewHolder.tv_jieShao.setText(listBean.getJmi_des());
        Glide.with(context.getApplicationContext()).load(listBean.getJmi_img()).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context.getApplicationContext())).into(viewHolder.iv_touXiang);
    }

    class CareViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_care)
        ImageView image_care;
        @Bind(R.id.iv_touXiang)
        ImageView iv_touXiang;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_jieShao)
        TextView tv_jieShao;

        public CareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
