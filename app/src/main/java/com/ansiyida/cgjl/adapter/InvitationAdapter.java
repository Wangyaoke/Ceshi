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
import com.ansiyida.cgjl.bean.DefaultBean;
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
public class InvitationAdapter extends RecyclerView.Adapter {
    private ArrayList<FansCareBean.ListBean> list;
    private Context context;
    private ArrayList<Integer> gzList;
    private String jp_id;
    public InvitationAdapter(ArrayList<FansCareBean.ListBean> list, Context context, ArrayList<Integer> gzList,String jp_id) {
        this.list = list;
        this.context = context;
        this.gzList = gzList;
        this.jp_id=jp_id;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CareViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_invitation, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final CareViewHolder viewHolder = (CareViewHolder) holder;
        final FansCareBean.ListBean listBean = list.get(position);
        if (gzList.get(position) == 0) {
            viewHolder.image_care.setImageResource(R.mipmap.icon_invitation_none);
        } else {
            viewHolder.image_care.setImageResource(R.mipmap.icon_invitation_yes);
        }
        viewHolder.image_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gzList.get(position) == 0) {
                    //未邀请
                    Call<DefaultBean> call= MyApplication.getNetApi().http_invitation((String) SharedPreferenceUtils.get(context,"app_token",""),jp_id,listBean.getJmi_id()+"");
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {

                                if ("0001".equals(response.body().getStatus())) {
                                    viewHolder.image_care.setImageResource(R.mipmap.icon_invitation_yes);
                                    if (position==gzList.size()-1){
                                        gzList.remove(position);
                                        gzList.add(1);
                                    }else {
                                        gzList.remove(position);

                                        gzList.add(position, 1);
                                    }

                                } else {
                                    viewHolder.image_care.setImageResource(R.mipmap.icon_invitation_yes);
                                    if (position==gzList.size()-1){
                                        gzList.remove(position);
                                        gzList.add(1);
                                    }else {
                                        gzList.remove(position);
                                        gzList.add(position, 1);
                                    }
                                }
                                ToastUtils.showMessage(context.getApplicationContext(), response.body().getMessage());


                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), "解析错误1");

                            }


                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            ToastUtils.showMessage(context.getApplicationContext(), "解析错误2");
                            call.cancel();
                        }
                    });

                }else {
                    viewHolder.image_care.setImageResource(R.mipmap.icon_invitation_yes);
                }


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
