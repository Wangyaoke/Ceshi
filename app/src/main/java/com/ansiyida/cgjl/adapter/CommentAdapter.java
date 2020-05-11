package com.ansiyida.cgjl.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.CommentActivity;
import com.ansiyida.cgjl.activity.FriendDynamicActivity;
import com.ansiyida.cgjl.activity.ReportActivity;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.LogUtil;
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
 * Created by ansiyida on 2018/3/5.
 */
public class CommentAdapter extends RecyclerView.Adapter {
    private CommentActivity context;
    private ArrayList<CommentBean.DataBean.ContentBean> lists;
    private ArrayList<Integer> zanFlag;
    private ArrayList<Integer> comentCountList=new ArrayList<>();
    private boolean isFirst;//判断是一级分类还是二级分类的标识
    public CommentAdapter(CommentActivity context, ArrayList<CommentBean.DataBean.ContentBean> lists, ArrayList<Integer> zanFlag,boolean isFirst) {
        this.context = context;
        this.lists = lists;
        this.zanFlag = zanFlag;
        this.isFirst=isFirst;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_comment, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        LogUtil.i("qaz", "name:" + lists.get(position).getUsernickname());

        final CommentViewHolder viewHolder = (CommentViewHolder) holder;
        Drawable drawableLeft = context.getResources().getDrawable(
                R.mipmap.zan1);
        final Drawable drawableLeft2 = context.getResources().getDrawable(
                R.mipmap.zan2);

        if (zanFlag.get(position) == 0) {       //没有点赞状态
            viewHolder.tv_zan.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            viewHolder.tv_zan.setTextColor(context.getResources().getColor(R.color.gray_dan));
            viewHolder.tv_zan.setText("   0");
        } else {     //点赞状态
            viewHolder.tv_zan.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                    null, null, null);
            viewHolder.tv_zan.setTextColor(context.getResources().getColor(R.color.text_red));
            viewHolder.tv_zan.setClickable(false);
            viewHolder.tv_zan.setText("   0");
        }
        final CommentBean.DataBean.ContentBean contentBean = lists.get(position);
        viewHolder.tv_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zanFlag.get(position) == 0) {               //没有点赞状态
                    Call<DefaultBean> call = MyApplication.getNetApi().addgread(contentBean.getJc_id() + "", "B", "up",(String) SharedPreferenceUtils.get(context, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String message = response.body().getMessage();
                                if ("0001".equals(response.body().getStatus())) {
                                    String count = viewHolder.tv_zan.getText().toString().trim();
                                    int newCount = Integer.parseInt(count) + 1;
                                    viewHolder.tv_zan.setText("   " + newCount);
                                    viewHolder.tv_zan.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                                            null, null, null);
                                    viewHolder.tv_zan.setTextColor(context.getResources().getColor(R.color.text_red));
                                    viewHolder.tv_zan.setClickable(false);
                                    zanFlag.remove(position);
                                    zanFlag.add(position, 1);
                                } else {
                                    ToastUtils.showMessage(context, message);
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


        viewHolder.relative_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReportActivity.class);
                intent.putExtra("userId",contentBean.getUser_id()+"");
                context.startActivity(intent);

            }
        });
        viewHolder.iv_touXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendDynamicActivity.class);
                intent.putExtra("jm_id",contentBean.getUser_id()+"");
                context.startActivity(intent);

            }
        });

        LogUtil.i("imgUrl", "img-->" + contentBean.getUserhead());
        Glide.with(context).load(contentBean.getUserhead()).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_touXiang);
        viewHolder.tv_nickName.setText(contentBean.getUsernickname());
        viewHolder.tv_zan.setText("   " + contentBean.getGreadnum());
        viewHolder.tv_content.setText(contentBean.getJc_cont());
        viewHolder.tv_time.setText(contentBean.getDateformat());
        if (!isFirst) {
            //二级评论表
            viewHolder.tv_talk.setVisibility(View.GONE);
            viewHolder.text_huiFu.setVisibility(View.GONE);

        } else {
            //一级评论表
            final int commnum=comentCountList.get(position);
            if (commnum > 0) {
                viewHolder.text_huiFu.setVisibility(View.VISIBLE);
                viewHolder.text_huiFu.setText("查看全部" + commnum + "条回复＞");
                viewHolder.text_huiFu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CommentActivity.class);
                        intent.putExtra("id", contentBean.getComment_id() + "");
                        intent.putExtra("type", contentBean.getArticle_type());
                        intent.putExtra("jc_id", contentBean.getJc_id() + "");
                        intent.putExtra("commentCount", commnum);
                        context.startActivity(intent);
                    }
                });
            } else {
                viewHolder.text_huiFu.setVisibility(View.GONE);

            }
            viewHolder.tv_talk.setVisibility(View.VISIBLE);
            viewHolder.tv_talk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommentDialog2 commentDialog2 = new CommentDialog2("优质评论将会优先展示", "", 300, new CommentDialog2.SendListener() {
                        @Override
                        public void sendComment(String inputText) {
                            Call<DefaultBean> call = MyApplication.getNetApi().saveComment(contentBean.getComment_id() + "", contentBean.getArticle_type(), inputText,(String) SharedPreferenceUtils.get(context,"app_token",""), contentBean.getJc_id() + "");
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        ToastUtils.showMessage(context, response.body().getMessage());
                                        int integer = comentCountList.get(position);
                                        comentCountList.remove(position);
                                        comentCountList.add(position, ++integer);
                                        notifyDataSetChanged();
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });

                        }
                    }, context, "发布");
                    commentDialog2.show(context.getSupportFragmentManager(), "comment");
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_zan)              //1.点赞
                TextView tv_zan;
        @Bind(R.id.relative_record)     //2.对评论举报
                RelativeLayout relative_record;
        @Bind(R.id.iv_touXiang)         //3.头像
                ImageView iv_touXiang;
        @Bind(R.id.tv_nickName)
        TextView tv_nickName;
        @Bind(R.id.tv_content)
        TextView tv_content;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_talk)
        TextView tv_talk;
        @Bind(R.id.text_huiFu)
        TextView text_huiFu;


        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setCommentBeans(CommentBean commentBean) {
        List<CommentBean.DataBean.ContentBean> content = commentBean.getData().getContent();
        int size = content.size();
        LogUtil.i("qaz", "size:" + size);
        if (size > 0) {
            lists.clear();
            zanFlag.clear();
            lists.addAll(content);
            for (int x = 0; x < size; x++) {
                if ("Y".equals(content.get(x).getIsGread())){
                    zanFlag.add(1);
                }else {
                    zanFlag.add(0);
                }
                comentCountList.add(lists.get(x).getCommnum());
            }
            notifyDataSetChanged();
        }

    }
    public void loadCommentBeans(CommentBean commentBean) {
        List<CommentBean.DataBean.ContentBean> content = commentBean.getData().getContent();
        int size = content.size();
        LogUtil.i("qaz", "size:" + size);
        if (size > 0) {
            lists.addAll(content);
            for (int x = 0; x < size; x++) {
                if ("Y".equals(content.get(x).getIsGread())){
                    zanFlag.add(1);
                }else {
                    zanFlag.add(0);
                }
                comentCountList.add(lists.get(x).getCommnum());
            }
            notifyDataSetChanged();
        }else {
            ToastUtils.showMessage(context,"无数据可加载");
        }

    }
}
