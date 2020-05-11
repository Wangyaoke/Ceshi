package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.CollectActivity;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseInfoSearchBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ansiyida.cgjl.util.ResUtil.getResources;

public class PurchaseSearchAdapter extends RecyclerView.Adapter<PurchaseSearchAdapter.ViewHolder> {
    private List<PurchaseInfoSearchBean.DataBean> list ;
    private Context context;
    private String keyword;
    private String mode;

    public PurchaseSearchAdapter(List<PurchaseInfoSearchBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setKeyword(String keyword,String mode,List<PurchaseInfoSearchBean.DataBean> list){
        this.keyword = keyword;
        this.mode = mode;
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchasesearch_search_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.newsSource.setText(list.get(position).getProvince()+"");
        if(list.get(position).getProvince()==null || list.get(position).getProvince().equals("")){
            holder.newsSource.setText("全国");
        }
        if(mode.equals("title")){
            String userId = list.get(position).getTitle();
            if(userId.indexOf("<r>")!=-1) {
                try {
                    SpannableString spannableString = null;

                    String userIdJiequ = userId.substring(userId.indexOf("<r>"));
                    String str = userIdJiequ;
                    String substring = str.substring(0, str.indexOf("</r>"));
                    String replace = substring.replace("<r>", "");
                    String replace1 = replace.replace("</r>", "");

                    String title = list.get(position).getTitle().replace("<r>", "");
                    String titlereplace = title.replace("</r>", "");
                    spannableString = matcherSearchText(context.getResources().getColor(R.color.yellow), titlereplace, replace1);

                    holder.title.setMaxLines(2);
                    holder.contentlin.setVisibility(View.GONE);
                    holder.title.setText(spannableString);
                } catch (Exception e) {
                    holder.title.setMaxLines(2);
                    holder.contentlin.setVisibility(View.GONE);
                    holder.title.setText(list.get(position).getTitle() + "");
                }
            }else{
                holder.title.setMaxLines(2);
                holder.contentlin.setVisibility(View.GONE);
                holder.title.setText(list.get(position).getTitle() + "");
            }
        }else if(mode.equals("full")){
            SpannableString spannableString = null;
            String userId = list.get(position).getTitle();
            holder.contentlin.setVisibility(View.VISIBLE);
            if(userId.indexOf("<r>")!=-1) {
                try {
                    String userIdJiequ = userId.substring(userId.indexOf("<r>"));
                    String str = userIdJiequ;
                    String substring = str.substring(0, str.indexOf("</r>"));
                    String replace = substring.replace("<r>", "");
                    String replace1 = replace.replace("</r>", "");
                    String title = list.get(position).getTitle().replace("<r>", "");
                    String titlereplace = title.replace("</r>", "");
                    spannableString = matcherSearchText(context.getResources().getColor(R.color.yellow), titlereplace, replace1);

                    holder.title.setMaxLines(2);
                    holder.title.setText(spannableString);
                }catch (Exception e){
                    holder.title.setMaxLines(2);
                    holder.title.setText(userId);
                }
            }else{
                holder.title.setMaxLines(2);
                holder.contentlin.setVisibility(View.VISIBLE);
                holder.title.setText(list.get(position).getTitle());
            }
            try {
                SpannableString spannableString1 = null;
                String content = list.get(position).getContent().toString();
                String contentsubString = content.substring(content.indexOf("<r>"));
                String contentsubstring2 = contentsubString.substring(0, contentsubString.indexOf("</r>"));
                String replacecontent = contentsubstring2.replace("<r>", "");
                String replacecontent2 = replacecontent.replace("</r>", "");
                String contentreplace = content.replace("<r>", "");
                String contentreplace2 = contentreplace.replace("</r>", "");
                spannableString1 = matcherSearchText(context.getResources().getColor(R.color.yellow), contentreplace2, replacecontent2);
                holder.content.setMaxLines(3);
                holder.content.setText(spannableString1);
            }catch (Exception e){
                String content = list.get(position).getContent().toString();
                String replace = content.replace("<p>", "");
                String replace1 = replace.replace("</p>", "");
                holder.content.setMaxLines(3);
                holder.content.setText(replace1);
            }


        }
        if(mode.equals("title")) {
            holder.title.setTextColor(Color.parseColor("#333333"));
            for (int i = 0; i < Constant.titlelist.size(); i++) {
                if (Constant.titlelist.get(i).equals(list.get(position).getId())) {
                    holder.title.setTextColor(getResources().getColor(R.color.news_search_time));
                }
            }
        }
        if(mode.equals("full")){
            holder.title.setTextColor(Color.parseColor("#333333"));
            holder.content.setTextColor(Color.parseColor("#333333"));
            for (int i = 0; i < Constant.fulllist.size(); i++) {
                if (Constant.fulllist.get(i).equals(list.get(position).getId())) {
                    holder.title.setTextColor(getResources().getColor(R.color.news_search_time));
                    holder.content.setTextColor(getResources().getColor(R.color.news_search_time));
                }
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(list.get(position).getCreateTime());
        Date date = new Date(lt);
        holder.creedtime.setText(simpleDateFormat.format(date));
        if(list.get(position).isIsCollection()){
            holder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
        }else{
            holder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
        }
        if("成交公告".equals(list.get(position).getType())) {
            holder.newsTime.setTextColor(getResources().getColor(R.color.font_main_p));
            holder.newsTime.setBackgroundResource(R.drawable.shape_regular);
            holder.newsTime.setText(list.get(position).getType());
        }
        else if("公开招标公告".equals(list.get(position).getType()))
        {
            holder.newsTime.setTextColor(getResources().getColor(R.color.green));
            holder.newsTime.setBackgroundResource(R.drawable.shape_newdetail_fankui_none);
            holder.newsTime.setText(list.get(position).getType());
        }else if("更正公告".equals(list.get(position).getType())){
            holder.newsTime.setTextColor(getResources().getColor(R.color.gkggtext));
            holder.newsTime.setBackgroundResource(R.drawable.shape_newdetail_fankui_none);
            holder.newsTime.setText(list.get(position).getType());
        }else if("中标公告".equals(list.get(position).getType())){
            holder.newsTime.setTextColor(getResources().getColor(R.color.vip));
            holder.newsTime.setBackgroundResource(R.drawable.shape_newdetail_fankui_none);
            holder.newsTime.setText(list.get(position).getType());
        }else if("废标流标".equals(list.get(position).getType())){
            holder.newsTime.setTextColor(getResources().getColor(R.color.subscribe_item_selected_stroke_night_day));
            holder.newsTime.setBackgroundResource(R.drawable.shape_newdetail_fankui_none);
            holder.newsTime.setText(list.get(position).getType());
        }else{
            holder.newsTime.setText(list.get(position).getType());
        }



        if ("中国政府采购网".equals(list.get(position).getSource())) {
            holder.news_source.setText("政府采购网");
        } else if ("全军武器装备采购网".equals(list.get(position).getSource())) {
            holder.news_source.setText("装备采购网");
        } else if ("中国人民解放军总医院后勤招标采购中心".equals(list.get(position).getSource())) {
            holder.news_source.setText("总医院采购网");
        } else if ("武警物资采购网".equals(list.get(position).getSource())) {
            holder.news_source.setText("武警采购网");
        }else if("机电产品招标投标电子交易平台".equals(list.get(position).getSource())){
            holder.news_source.setText("机电招标平台");
        } else {
            holder.news_source.setText(list.get(position).getSource());
        }
        holder.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("true".equals(SharedPreferenceUtils.get(context, "vistor", ""))) {
                        if (!list.get(position).isIsCollection()) {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseInfo",list.get(position).getId());
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        //  ToastUtils.showMessage(context, response.body().getMessage());
                                        if ("200".equals(response.body().getStatus())) {
                                            ToastUtils.showMessage(context,"收藏成功");
                                            holder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
                                            list.get(position).setIsCollection(true);
                                        } else
                                            ToastUtils.showMessage(context, "登录后才能收藏");
                                    }
                                    notifyDataSetChanged();
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });
                        } else {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseInfo",list.get(position).getId());
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        if ("200".equals(response.body().getStatus())) {
                                            holder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
                                            ToastUtils.showMessage(context, "成功取消收藏！");
                                            list.get(position).setIsCollection(false);
                                            if (context.getClass() == CollectActivity.class)
                                                ((CollectActivity) context).refreshed();
//}
                                        }
                                    }
                                    notifyDataSetChanged();
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });
                        }
                } else
                    ToastUtils.showMessage(context, "登录后才能收藏");

            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("title")) {
                    Constant.titlelist.add(list.get(position).getId());
                    holder.title.setTextColor(getResources().getColor(R.color.news_search_time));
                }else if(mode.equals("full")){
                    Constant.fulllist.add(list.get(position).getId());
                    holder.title.setTextColor(getResources().getColor(R.color.news_search_time));
                    holder.content.setTextColor(getResources().getColor(R.color.news_search_time));
                }
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("type", "P");
                intent.putExtra("id", list.get(position).getId());
                if(list.get(position).isIsCollection()) {
                    intent.putExtra("idcollect", "true");
                } else {
                    intent.putExtra("idcollect", "false");
                }
                purchaseSearchListnear.intent(intent);
            }
        });
        holder.line_hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("title")) {
                    Constant.titlelist.add(list.get(position).getId());
                }else{
                    Constant.fulllist.add(list.get(position).getId());
                    holder.content.setTextColor(getResources().getColor(R.color.news_search_time));
                }
                holder.title.setTextColor(getResources().getColor(R.color.news_search_time));
                Intent intent = new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("type", "P");
                intent.putExtra("id", list.get(position).getId());
                if(list.get(position).isIsCollection()) {
                    intent.putExtra("idcollect", "true");
                } else {
                    intent.putExtra("idcollect", "false");
                }
                purchaseSearchListnear.intent(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            if(list!=null) {
                return list.size();
            }else if(list.size()>0){
                return list.size();
            }
        }catch (Exception e){

        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.line_hd)
        RelativeLayout line_hd;
        @Bind(R.id.visible_sourceUser)
        LinearLayout linear_user;
        @Bind(R.id.visible_sourceNews)
        LinearLayout linear_news;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        @Bind(R.id.delete_pic_fragment1)
        ImageView dismiss;
        @Bind(R.id.title_pic_fragment1)
        TextView title;
        @Bind(R.id.newsSource_pic_fragment1)
        TextView newsSource;
        @Bind(R.id.newsTime_pic_fragment1)
        TextView newsTime;
        @Bind(R.id.gonggao_time)
        TextView creedtime;
        @Bind(R.id.news_source)
        TextView news_source;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;
        @Bind(R.id.line_history_top)
        LinearLayout line_history_top;
        @Bind(R.id.line_history_time)
        TextView line_history_time;
        @Bind(R.id.line_history_count)
        TextView line_history_count;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.content_lin)
        LinearLayout contentlin;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public static SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }


    public interface  PurchaseSearchListnear{
        void intent(Intent intent);
    }
    public PurchaseSearchListnear purchaseSearchListnear;
    public void setPurchaseSearchListnear(PurchaseSearchListnear purchaseSearchListnear){
        this.purchaseSearchListnear = purchaseSearchListnear;
    }
}
