package com.ansiyida.cgjl.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.NewDetailBean;
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
 * Created by ansiyida on 2018/1/5.
 */
public class VideoDetailAdapter extends RecyclerView.Adapter {
    private ArrayList<Integer> list;
    private VideoDetailsActivity context;
    private ArrayList<Integer> zanFlag;
    private NewDetailBean newDetailBean;
    private List<CommentBean.DataBean.ContentBean> comentBeanList;
    private ArrayList<Integer> comentCountList = new ArrayList<>();
    private List<JuBaoBean.DataBean> data = null;
    private FragmentManager supportFragmentManager;

    public VideoDetailAdapter(ArrayList<Integer> list, VideoDetailsActivity context, ArrayList<Integer> zanFlag, NewDetailBean newDetailBean, List<CommentBean.DataBean.ContentBean> comentBeanList,FragmentManager supportFragmentManager) {
        this.list = list;
        this.context = context;
        this.zanFlag = zanFlag;
        this.newDetailBean = newDetailBean;
        this.comentBeanList = comentBeanList;
        this.supportFragmentManager=supportFragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:                 //1.内容头部

                return new MsgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_msg, parent, false));

            case 1:                 //2.用户评论

                return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_comment, parent, false));


            default:
                return new MsgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_msg, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final NewDetailBean.DataBeanX.ArticleBean article = newDetailBean.getData().getArticle();
        if (holder instanceof MsgViewHolder) {//---------------------------------------------------------------0.视频信息
            MsgViewHolder viewHolder = (MsgViewHolder) holder;
            viewHolder.relative_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.report();
                }
            });

            String jca_strand_id = article.getJca_strand_id();
            if (jca_strand_id != null && !"".equals(jca_strand_id) && !"null".equals(jca_strand_id)) {
                jca_strand_id = jca_strand_id.replace("，", ",");
                String[] split = jca_strand_id.split(",");
                int length = split.length;
                for (int x = 0; x < length; x++) {
                    if (x == 0) {
                        viewHolder.tab1.setText(split[x]);
                        viewHolder.tab1.setVisibility(View.VISIBLE);
                        setTextSize(viewHolder.tab1);
                    } else if (x == 1) {
                        viewHolder.tab2.setText(split[x]);
                        viewHolder.tab2.setVisibility(View.VISIBLE);
                        setTextSize(viewHolder.tab2);
                    } else if (x == 2) {
                        viewHolder.tab3.setText(split[x]);
                        viewHolder.tab3.setVisibility(View.VISIBLE);
                        setTextSize(viewHolder.tab3);
                    } else if (x == 3) {
                        viewHolder.tab4.setText(split[x]);
                        viewHolder.tab4.setVisibility(View.VISIBLE);
                        setTextSize(viewHolder.tab4);
                    } else if (x == 4) {
                        viewHolder.tab5.setText(split[x]);
                        viewHolder.tab5.setVisibility(View.VISIBLE);
                        setTextSize(viewHolder.tab5);
                    }
                }
            }

            String jca_soure = article.getJca_soure();
            if (jca_soure!=null){
                if (jca_soure.length()>9){
                    jca_soure=jca_soure.substring(0,9)+"...";
                }
            }else {
                jca_soure="未知";
            }
            viewHolder.tv_name.setText(jca_soure);
            viewHolder.tv_playCount.setText(article.getJca_browse()+"");
            viewHolder.tv_time.setText(article.getDateformat());


        } else if (holder instanceof CommentViewHolder) {//--------------------------------------------------------1.用户评论
            final int localPosition = position - 1;
            final CommentViewHolder viewHolder = (CommentViewHolder) holder;
            Drawable drawableLeft = context.getApplicationContext().getResources().getDrawable(
                    R.mipmap.zan1);
            final Drawable drawableLeft2 = context.getApplicationContext().getResources().getDrawable(
                    R.mipmap.zan2);

            if (zanFlag.get(localPosition) == 0) {       //没有点赞状态
                viewHolder.tv_zan.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                viewHolder.tv_zan.setTextColor(context.getApplicationContext().getResources().getColor(R.color.gray_dan));
                viewHolder.tv_zan.setText("   123");
            } else {     //点赞状态
                viewHolder.tv_zan.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                        null, null, null);
                viewHolder.tv_zan.setTextColor(context.getApplicationContext().getResources().getColor(R.color.text_red));
                viewHolder.tv_zan.setClickable(false);
                viewHolder.tv_zan.setText("   124");

            }
            final CommentBean.DataBean.ContentBean contentBean = comentBeanList.get(localPosition);
            viewHolder.tv_zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (zanFlag.get(localPosition) == 0) {               //没有点赞状态
                        Call<DefaultBean> call = MyApplication.getNetApi().addgread(contentBean.getJc_id() + "", "B", "up", (String) SharedPreferenceUtils.get(context.getApplicationContext(), "app_token", ""));
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
                                        viewHolder.tv_zan.setTextColor(Color.parseColor("#FE3060"));
                                        viewHolder.tv_zan.setClickable(false);
                                        zanFlag.remove(localPosition);
                                        zanFlag.add(localPosition, 1);
                                    } else {
                                        ToastUtils.showMessage(context.getApplicationContext(), message);
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
            Glide.with(context.getApplicationContext()).load(contentBean.getUserhead()).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context.getApplicationContext())).into(viewHolder.iv_touXiang);
            viewHolder.tv_nickName.setText(contentBean.getUsernickname());
            viewHolder.tv_zan.setText("   " + contentBean.getGreadnum());
            viewHolder.tv_content.setText(contentBean.getJc_cont());
            viewHolder.tv_time.setText(contentBean.getDateformat());
            //一级评论表
            final int commnum = comentCountList.get(localPosition);
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
                            Call<DefaultBean> call = MyApplication.getNetApi().saveComment(contentBean.getComment_id() + "", contentBean.getArticle_type(), inputText, (String) SharedPreferenceUtils.get(context.getApplicationContext(),"app_token",""), contentBean.getJc_id() + "");
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        int integer = comentCountList.get(localPosition);
                                        comentCountList.remove(localPosition);
                                        comentCountList.add(localPosition, ++integer);
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
                    },null, "发布");
                    commentDialog2.show(supportFragmentManager, "comment");
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNewDetailBean(NewDetailBean newDetailBean) {
        this.newDetailBean = newDetailBean;
        list.add(0);
        notifyDataSetChanged();
    }
    private void setTextSize(TextView textView) {
        int lenth = textView.getText().toString().trim().length();
        if (lenth > 4 && lenth < 6) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        } else if (lenth >= 6 && lenth < 10) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        } else if (lenth >= 10) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 5);

        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }
    }
    public void setComments(List<CommentBean.DataBean.ContentBean> contentList) {
        if (contentList != null) {
            int size = contentList.size();
            comentBeanList.clear();
            int size1 = list.size();
            if (size1>1){
                for (int x=size1-1;x>0;x--){
                    list.remove(x);
                }
            }
            zanFlag.clear();
            comentCountList.clear();
            comentBeanList.addAll(contentList);
            for (int x = 0; x < size; x++) {
                list.add(1);
                if ("Y".equals(contentList.get(x).getIsGread())){
                    zanFlag.add(1);
                }else {
                    zanFlag.add(0);
                }
                comentCountList.add(contentList.get(x).getCommnum());
            }
            notifyDataSetChanged();

        } else {
            ToastUtils.showMessage(context.getApplicationContext(), "已加载到底部");
        }

    }
    public void setLoadComments(List<CommentBean.DataBean.ContentBean> contentList) {
        if (contentList != null&&contentList.size()>0) {
            int size = contentList.size();
            comentBeanList.addAll(contentList);
            for (int x = 0; x < size; x++) {
                list.add(1);
                if ("Y".equals(contentList.get(x).getIsGread())){
                    zanFlag.add(1);
                }else {
                    zanFlag.add(0);
                }
                comentCountList.add(contentList.get(x).getCommnum());
            }
            notifyDataSetChanged();

        } else {
            ToastUtils.showMessage(context.getApplicationContext(), "已加载到底部");
        }

    }


    class MsgViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.relative_record)
        RelativeLayout relative_record;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_playCount)
        TextView tv_playCount;
        @Bind(R.id.tab1)
        TextView tab1;
        @Bind(R.id.tab2)
        TextView tab2;
        @Bind(R.id.tab3)
        TextView tab3;
        @Bind(R.id.tab4)
        TextView tab4;
        @Bind(R.id.tab5)
        TextView tab5;

        public MsgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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
}
