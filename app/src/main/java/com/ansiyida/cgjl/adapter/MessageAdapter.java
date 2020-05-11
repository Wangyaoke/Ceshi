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
import com.ansiyida.cgjl.activity.FriendDynamicActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.MessageBean;
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
 * Created by chenyaoxiang on 2017/12/27.
 */
public class MessageAdapter extends RecyclerView.Adapter {
    private ArrayList<MessageBean.DataBean.ListBean> list;
    private Context context;

    public MessageAdapter(Context context, ArrayList<MessageBean.DataBean.ListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:                     //1.被人邀请发表观点
                return new InvitationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_message_invitation, parent, false));
            case 2:                     //2.被人关注
                return new FollowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_message_follow, parent, false));
            case 3:                     //3.系统通知
                return new SystemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_message_system, parent, false));

            default:
                return new SystemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_message_system, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageBean.DataBean.ListBean listBean = list.get(position);
        Object jmi_id = listBean.getJmi_id();
        Object jp_id = listBean.getJp_id();
        if (jmi_id != null && jp_id != null) {
            return 1;
        } else {
            return 3;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof InvitationViewHolder) {                         //1.邀请发表观点
            InvitationViewHolder viewHolder = (InvitationViewHolder) holder;
            final MessageBean.DataBean.ListBean listBean = list.get(position);
            viewHolder.tv_userName.setText(listBean.getJmi_username() + "");
            viewHolder.tv_body.setText(listBean.getJp_title() + "");
            if (listBean.getAnswerNum() != null) {
                int answerNum = (int)(double) listBean.getAnswerNum();
                viewHolder.tv_optionCount.setText(answerNum + "");
            }else {
                viewHolder.tv_optionCount.setText("0");
            }
            viewHolder.tv_time.setText(listBean.getJmi_datetime());
            String imgUrl = listBean.getJmi_img() + "";
            Glide.with(context.getApplicationContext()).load(imgUrl).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context.getApplicationContext())).into(viewHolder.iv_touXiang);
            viewHolder.relative_jump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, YanTaoActivity.class);
                    intent.putExtra("jc_id", (int) (double) listBean.getJp_id() + "");
                    context.startActivity(intent);
                }
            });
            viewHolder.iv_touXiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FriendDynamicActivity.class);
                    intent.putExtra("jm_id", (int) (double) listBean.getJmi_id()+"");
                    context.startActivity(intent);

                }
            });
            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<DefaultBean> call= MyApplication.getNetApi().deleteMessage((String) SharedPreferenceUtils.get(context.getApplicationContext(),"app_token",""),listBean.getJmm_id()+"");
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()){
                                DefaultBean body = response.body();
                                if ("0001".equals(body.getStatus())){
                                    list.remove(position);
                                    notifyDataSetChanged();
                                }else {
                                    ToastUtils.showMessage(context.getApplicationContext(),body.getMessage());
                                }


                            }else {
                                ToastUtils.showMessage(context.getApplicationContext(),"数据解析错误1");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(context.getApplicationContext(),"数据解析错误2");
                        }
                    });
                }
            });

        } else if (holder instanceof FollowViewHolder) {                       //2.有人关注
            FollowViewHolder viewHolder = (FollowViewHolder) holder;
        } else if (holder instanceof SystemViewHolder) {                       //3.系统消息

            SystemViewHolder viewHolder = (SystemViewHolder) holder;
            final MessageBean.DataBean.ListBean listBean = list.get(position);
            viewHolder.tv_message_system.setText(listBean.getJmm_content() + "");
            viewHolder.tv_time_system.setText(listBean.getJmi_datetime());
            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    Call<DefaultBean> call = MyApplication.getNetApi().deleteMessage((String) SharedPreferenceUtils.get(context.getApplicationContext(), "app_token", ""), listBean.getJmm_id() + "");
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                DefaultBean body = response.body();
                                if ("0001".equals(body.getStatus())) {
                                    notifyDataSetChanged();
                                } else {
                                    ToastUtils.showMessage(context.getApplicationContext(), body.getMessage());
                                }


                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), "数据解析错误1");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(context.getApplicationContext(), "数据解析错误2");
                        }
                    });
                }
            });

        }
    }

    class InvitationViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_userName)
        TextView tv_userName;
        @Bind(R.id.tv_body_invitation)
        TextView tv_body;
        @Bind(R.id.tv_optionCount_invitation)
        TextView tv_optionCount;
        @Bind(R.id.tv_time_invitation)
        TextView tv_time;
        @Bind(R.id.iv_touXiang_invitation)
        ImageView iv_touXiang;
        @Bind(R.id.relative_jump)
        RelativeLayout relative_jump;
        @Bind(R.id.iv_delete)
        ImageView iv_delete;

        public InvitationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FollowViewHolder extends RecyclerView.ViewHolder {

        public FollowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SystemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_message_system)
        TextView tv_message_system;
        @Bind(R.id.tv_time_system)
        TextView tv_time_system;
        @Bind(R.id.iv_delete)
        ImageView iv_delete;

        public SystemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
