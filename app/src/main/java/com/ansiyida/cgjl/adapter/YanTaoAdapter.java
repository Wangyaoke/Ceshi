package com.ansiyida.cgjl.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.FriendDynamicActivity;
import com.ansiyida.cgjl.activity.InvitationActivity;
import com.ansiyida.cgjl.activity.OpinionActivity;
import com.ansiyida.cgjl.activity.ReportActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.YanTaoDetailBean;
import com.ansiyida.cgjl.bean.YanTaoOptionsBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.LogUtil;
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
 * Created by ansiyida on 2018/1/25.
 */
public class YanTaoAdapter extends RecyclerView.Adapter {
    private YanTaoActivity context;
    private YanTaoDetailBean.DataBean.QuestionBean question;
    private ArrayList<YanTaoOptionsBean.DataBean.AnswersBean> beanList;
    private ArrayList<Integer> lineCountList;
    private ArrayList<Boolean> visbleList;
    private int maxLineLength = 10000;
    private ArrayList<Integer> zanFlag;
    private int imgUrl;

    public YanTaoAdapter(YanTaoActivity context, YanTaoDetailBean.DataBean.QuestionBean question, ArrayList<YanTaoOptionsBean.DataBean.AnswersBean> beanList, ArrayList<Integer> lineCountList, ArrayList<Boolean> visbleList, ArrayList<Integer> zanFlag,int imgUrl) {
        this.context = context;
        this.beanList = beanList;
        this.question = question;
        this.lineCountList = lineCountList;
        this.visbleList = visbleList;
        this.zanFlag=zanFlag;
        this.imgUrl=imgUrl;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:             //1.研讨详情顶部item
                return new YanTaoTopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yantao_top, parent, false));

            case 2:             //2.优选观点&其他观点
                return new YanTaoOtherViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yantao_other, parent, false));
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int lineNum = lineCountList.get(position);
        if (holder instanceof YanTaoTopViewHolder) {//-------------------------------------------------------------1.研讨详情顶部
            final YanTaoTopViewHolder viewHolder = (YanTaoTopViewHolder) holder;
            viewHolder.image_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.finish();
                }
            });
            String jca_strand_id = question.getJp_strand_id();
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
            viewHolder.tv_title.setText(question.getJp_title());
            viewHolder.iv_photo.setBackgroundResource(imgUrl);
            viewHolder.tv_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.tv_mineYanLun.setMaxLines(maxLineLength);
                    viewHolder.tv_down.setVisibility(View.GONE);
                    viewHolder.tv_up.setVisibility(View.VISIBLE);
                    visbleList.remove(position);
                    visbleList.add(position, true);
                }
            });
            viewHolder.tv_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.tv_mineYanLun.setMaxLines(2);
                    viewHolder.tv_down.setVisibility(View.VISIBLE);
                    viewHolder.tv_up.setVisibility(View.GONE);
                    visbleList.remove(position);
                    visbleList.add(position, false);
                }
            });
            LogUtil.i("ll", "position:" + position + ",flag:" + visbleList.get(position));
            if (visbleList.get(position)) {
                viewHolder.tv_down.setVisibility(View.GONE);
                viewHolder.tv_up.setVisibility(View.VISIBLE);
                viewHolder.tv_mineYanLun.setMaxLines(maxLineLength);
            } else {
                viewHolder.tv_mineYanLun.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = viewHolder.tv_mineYanLun.getLineCount();
                        if (lineCount > 2) {
                            lineCountList.remove(position);
                            lineCountList.add(position, lineCount);
                            viewHolder.tv_down.setVisibility(View.VISIBLE);
                            viewHolder.tv_up.setVisibility(View.GONE);
                            viewHolder.tv_mineYanLun.setMaxLines(2);
                            LogUtil.i("nihao", "text:" + viewHolder.tv_mineYanLun.getText());

                        } else {
                            if (lineCountList.get(position) > 2) {
                                viewHolder.tv_down.setVisibility(View.VISIBLE);
                                viewHolder.tv_up.setVisibility(View.GONE);
                            } else {
                                viewHolder.tv_down.setVisibility(View.GONE);
                                viewHolder.tv_up.setVisibility(View.GONE);
                            }

                        }
                    }
                });
            }

            String isCollection = question.getIsCollection();
            if ("Y".equals(isCollection)) {
                viewHolder.iv_isCollect.setImageResource(R.mipmap.tantao_college2);
                viewHolder.tv_isCollect.setTextColor(Color.parseColor("#0288ac"));
            } else {
                viewHolder.iv_isCollect.setImageResource(R.mipmap.tantao_college1);
                viewHolder.tv_isCollect.setTextColor(Color.parseColor("#666666"));
            }
            viewHolder.tv_mineYanLun.setText(question.getJp_content());
            Glide.with(context).load(question.getJmi_img() + "").placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_touXiang);
            viewHolder.tv_userName.setText(question.getJmi_username() + "");
            viewHolder.tv_count_guanDian.setText(question.getAnswerCount() + "个观点");
            viewHolder.linear_college.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //收藏研讨
                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews("D", question.getJp_id() + "", (String) SharedPreferenceUtils.get(context, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                DefaultBean body = response.body();
                                if ("0001".equals(response.body().getStatus())) {
                                    viewHolder.iv_isCollect.setImageResource(R.mipmap.tantao_college2);
                                    viewHolder.tv_isCollect.setTextColor(Color.parseColor("#0288ac"));
                                    question.setIsCollection("Y");
                                }
                                ToastUtils.showMessage(context, response.body().getMessage());

                            }
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            ToastUtils.showMessage(context, "未知错误");

                            call.cancel();
                        }
                    });
                }
            });
            viewHolder.linear_yaoQing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //邀请研讨
                    Intent intent = new Intent(context, InvitationActivity.class);
                    intent.putExtra("jp_id", question.getJp_id() + "");
                    context.startActivity(intent);
                }
            });
            viewHolder.linear_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //分享研讨
                    context.share();
                }
            });
            viewHolder.iv_touXiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FriendDynamicActivity.class);
                    intent.putExtra("jm_id", question.getJp_user_id() + "");
                    context.startActivity(intent);

                }
            });


        } else if (holder instanceof YanTaoOtherViewHolder) {//------------------------------------------------------2.优选观点&其他观点
            final int localPositon = position - 1;
            final YanTaoOptionsBean.DataBean.AnswersBean answersBean = beanList.get(localPositon);
            final YanTaoOtherViewHolder viewHolder = (YanTaoOtherViewHolder) holder;
            if (position == 1) {
                //优选观点
                String ja_is_opend = answersBean.getJa_is_opend();
                if ("Y".equals(ja_is_opend)) {
                    viewHolder.tv_txt.setText("优选观点");
                } else {
                    viewHolder.tv_txt.setText("其他观点");
                }
                viewHolder.tv_txt.setVisibility(View.VISIBLE);

            } else if (position == 2) {
                //其他观点第一条
                if ("N".equals(beanList.get(0).getJa_is_opend())) {
                    viewHolder.tv_txt.setVisibility(View.GONE);

                } else {
                    viewHolder.tv_txt.setText("其他观点");
                    viewHolder.tv_txt.setVisibility(View.VISIBLE);
                }


            } else {
                //其他观点非第一条
                viewHolder.tv_txt.setVisibility(View.GONE);
            }
            if ("Y".equals(beanList.get(0).getIsMyProblem())){
                if ("Y".equals(beanList.get(0).getJa_is_opend())){
                    viewHolder.iv_youXuan.setVisibility(View.GONE);

                }else {
                    viewHolder.iv_youXuan.setVisibility(View.VISIBLE);
                }
            }else {
                viewHolder.iv_youXuan.setVisibility(View.GONE);
            }
            viewHolder.iv_youXuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<DefaultBean> call=MyApplication.getNetApi().setBestOption(beanList.get(localPositon).getJa_id()+"");
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()){
                                if ("0001".equals(response.body().getStatus())){
                                    YanTaoOptionsBean.DataBean.AnswersBean answersBean1 = beanList.get(localPositon);
                                    answersBean1.setJa_is_opend("Y");
                                    beanList.remove(localPositon);
                                    beanList.add(0, answersBean1);
                                    notifyDataSetChanged();
                                }
                                ToastUtils.showMessage(context,response.body().getMessage());
                            }
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                        }
                    });
                }
            });
            viewHolder.tv_zanCount.setText(answersBean.getCount() + "");
            Drawable drawableLeft = context.getResources().getDrawable(
                    R.mipmap.zan1);
            final Drawable drawableLeft2 = context.getResources().getDrawable(
                    R.mipmap.zan2);

            if (zanFlag.get(localPositon) == 0) {       //没有点赞状态
                viewHolder.tv_zanCount.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                viewHolder.tv_zanCount.setTextColor(context.getResources().getColor(R.color.gray_dan));
                viewHolder.tv_zanCount.setText("   "+answersBean.getCount());
            } else {     //点赞状态
                viewHolder.tv_zanCount.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                        null, null, null);
                viewHolder.tv_zanCount.setTextColor(context.getResources().getColor(R.color.text_red));
                viewHolder.tv_zanCount.setClickable(false);
                viewHolder.tv_zanCount.setText("   "+answersBean.getCount());
            }
            viewHolder.tv_zanCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (zanFlag.get(localPositon) == 0) {               //没有点赞状态
                        Call<DefaultBean> call = MyApplication.getNetApi().addgread( answersBean.getJa_id()+ "", "A", "up",(String) SharedPreferenceUtils.get(context, "app_token", ""));
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    String message = response.body().getMessage();
                                    if ("0001".equals(response.body().getStatus())) {
                                        String count = viewHolder.tv_zanCount.getText().toString().trim();
                                        int newCount = Integer.parseInt(count) + 1;
                                        viewHolder.tv_zanCount.setText("   " + newCount);
                                        viewHolder.tv_zanCount.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                                                null, null, null);
                                        viewHolder.tv_zanCount.setTextColor(context.getResources().getColor(R.color.text_red));
                                        viewHolder.tv_zanCount.setClickable(false);
                                        zanFlag.remove(localPositon);
                                        zanFlag.add(localPositon, 1);
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



            viewHolder.tv_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReportActivity.class);
                    intent.putExtra("userId", answersBean.getJmi_id() + "");
                    context.startActivity(intent);
                }
            });
            viewHolder.tv_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.tv_mineYanLun.setMaxLines(maxLineLength);
                    viewHolder.tv_down.setVisibility(View.GONE);
                    viewHolder.tv_up.setVisibility(View.VISIBLE);
                    visbleList.remove(position);
                    visbleList.add(position, true);
                }
            });
            viewHolder.tv_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.tv_mineYanLun.setMaxLines(2);
                    viewHolder.tv_down.setVisibility(View.VISIBLE);
                    viewHolder.tv_up.setVisibility(View.GONE);
                    visbleList.remove(position);
                    visbleList.add(position, false);
                }
            });
            LogUtil.i("ll", "position:" + position + ",flag:" + visbleList.get(position));
            if (visbleList.get(position)) {
                viewHolder.tv_down.setVisibility(View.GONE);
                viewHolder.tv_up.setVisibility(View.VISIBLE);
                viewHolder.tv_mineYanLun.setMaxLines(maxLineLength);
            } else {
                viewHolder.tv_mineYanLun.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = viewHolder.tv_mineYanLun.getLineCount();
                        if (lineCount > 2) {
                            lineCountList.remove(position);
                            lineCountList.add(position, lineCount);
                            viewHolder.tv_down.setVisibility(View.VISIBLE);
                            viewHolder.tv_up.setVisibility(View.GONE);
                            viewHolder.tv_mineYanLun.setMaxLines(2);
                            LogUtil.i("nihao", "text:" + viewHolder.tv_mineYanLun.getText());

                        } else {
                            if (lineCountList.get(position) > 2) {
                                viewHolder.tv_down.setVisibility(View.VISIBLE);
                                viewHolder.tv_up.setVisibility(View.GONE);
                            } else {
                                viewHolder.tv_down.setVisibility(View.GONE);
                                viewHolder.tv_up.setVisibility(View.GONE);
                            }
                        }
                    }
                });
            }


            viewHolder.tv_userName.setText(answersBean.getJmi_username());
            YanTaoOptionsBean.DataBean.AnswersBean.ChildBean child = answersBean.getChild();
            if (child != null) {
                String yinYong = child.getJa_content() + "";
                String userName = child.getJmi_username() + "";
                int userNameLength = userName.length();
                String mine = answersBean.getJa_content();
                String spanString = "引用【 " + userName + " " + yinYong + "】";
                int spanLength = spanString.length();
                SpannableString spannableString = new SpannableString(spanString + mine);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3146af")), 4, 4 + userNameLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#9b9d9d")), 5 + userNameLength, spanLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.tv_mineYanLun.setText(spannableString);
            } else {
                viewHolder.tv_mineYanLun.setText(answersBean.getJa_content());
            }
            String ja_qtime = answersBean.getJa_qtime();
            if (ja_qtime.length() > 10) {
                ja_qtime = ja_qtime.substring(0, 10);
            }
            viewHolder.tv_time.setText(ja_qtime);
            Glide.with(context).load(answersBean.getJmi_img() + "").placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(context)).into(viewHolder.iv_touXiang);
            viewHolder.iv_touXiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FriendDynamicActivity.class);
                    intent.putExtra("jm_id", answersBean.getJmi_id() + "");
                    context.startActivity(intent);

                }
            });
            viewHolder.linear_yinYong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OpinionActivity.class);
                    intent.putExtra("ja_ask_id", question.getJp_id() + "");
                    intent.putExtra("yinYongStr", answersBean.getJa_content() + "");
                    intent.putExtra("ja_quote", answersBean.getJa_id() + "");
                    intent.putExtra("user_quote", answersBean.getJa_user_id() + "");
                    intent.putExtra("userName", answersBean.getJmi_username() + "");
                    context.startActivityForResult(intent, ActivityCodeUtil.OPINIONACTIVITY);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return beanList.size() + 1;
    }

    class YanTaoTopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_back)
        ImageView image_back;
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
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.iv_photo)
        ImageView iv_photo;
        @Bind(R.id.iv_touXiang)
        ImageView iv_touXiang;
        @Bind(R.id.tv_userName)
        TextView tv_userName;
        @Bind(R.id.tv_mineYanLun)
        TextView tv_mineYanLun;
        @Bind(R.id.tv_down)
        TextView tv_down;
        @Bind(R.id.tv_up)
        TextView tv_up;
        @Bind(R.id.tv_count_guanDian)
        TextView tv_count_guanDian;
        @Bind(R.id.linear_college)
        LinearLayout linear_college;
        @Bind(R.id.linear_yaoQing)
        LinearLayout linear_yaoQing;
        @Bind(R.id.linear_share)
        LinearLayout linear_share;
        @Bind(R.id.iv_isCollect)
        ImageView iv_isCollect;
        @Bind(R.id.tv_isCollect)
        TextView tv_isCollect;

        public YanTaoTopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class YanTaoOtherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_txt)
        TextView tv_txt;
        @Bind(R.id.tv_userName)
        TextView tv_userName;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_mineYanLun)
        TextView tv_mineYanLun;
        @Bind(R.id.iv_touXiang)
        ImageView iv_touXiang;
        @Bind(R.id.iv_youXuan)
        ImageView iv_youXuan;
        @Bind(R.id.linear_yinYong)
        LinearLayout linear_yinYong;
        @Bind(R.id.tv_down)
        TextView tv_down;
        @Bind(R.id.tv_up)
        TextView tv_up;
        @Bind(R.id.tv_report)
        TextView tv_report;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;

        public YanTaoOtherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

}
