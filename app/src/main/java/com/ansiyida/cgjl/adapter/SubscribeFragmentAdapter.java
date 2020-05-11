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

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.DYDetailsActivity;
import com.ansiyida.cgjl.bean.DYListBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/6/5.
 */
public class SubscribeFragmentAdapter extends RecyclerView.Adapter {
    private List<DYListBean.DataBean.ListBean> lists;
    private Context context;
    private ArrayList<Integer> isDYLists;
    public SubscribeFragmentAdapter(List<DYListBean.DataBean.ListBean> lists,Context context,ArrayList<Integer> isDYLists) {
        this.lists = lists;
        this.context=context.getApplicationContext();
        this.isDYLists=isDYLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dy_list, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DefaultViewHolder viewHolder = (DefaultViewHolder) holder;
        final DYListBean.DataBean.ListBean listBean = lists.get(position);
        Glide.with(context).load(listBean.getJs_logo()).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_head);
        viewHolder.tv_name.setText(listBean.getJs_name());
        viewHolder.tv_content.setText(listBean.getJs_remark());
        if (isDYLists.get(position)==1){
            viewHolder.iv_dy.setImageResource(R.mipmap.icon_dy_yes);
        }else {
            viewHolder.iv_dy.setImageResource(R.mipmap.icon_dy_none);
        }
        viewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DYDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("js_id",listBean.getJs_id()+"");
                intent.putExtra("type",0);
                context.startActivity(intent);
            }
        });
        viewHolder.iv_dy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isDYLists.get(position)==0){
                   Call<DefaultBean> call= MyApplication.getNetApi().addDingYue(listBean.getJs_id()+"","", (String) SharedPreferenceUtils.get(context, "app_token", ""));
                   call.enqueue(new Callback<DefaultBean>() {
                       @Override
                       public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                           if (response.isSuccessful()){
                               if (response.body().isData()){
                                   viewHolder.iv_dy.setImageResource(R.mipmap.icon_dy_yes);
                                   isDYLists.set(position,1);
                               }else {
                                   ToastUtils.showMessage(context, response.body().getMessage());
                               }
                           }

                           call.cancel();
                       }

                       @Override
                       public void onFailure(Call<DefaultBean> call, Throwable t) {
                           call.cancel();

                       }
                   });
               }else {
                   Call<DefaultBean> call= MyApplication.getNetApi().deleteDingYueUnZDY(listBean.getJs_id()+"", (String) SharedPreferenceUtils.get(context, "app_token", ""));
                   call.enqueue(new Callback<DefaultBean>() {
                       @Override
                       public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                           if (response.isSuccessful()){
                               if (response.body().isData()){
                                   viewHolder.iv_dy.setImageResource(R.mipmap.icon_dy_none);
                                   isDYLists.set(position,0);
                               }else {
                                   ToastUtils.showMessage(context, response.body().getMessage());
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
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_head_dyListAdapter)
        ImageView iv_head;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_content)
        TextView tv_content;
        @Bind(R.id.iv_dy_dyListAdapter)
        ImageView iv_dy;
        @Bind(R.id.relative)
        RelativeLayout relative;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
