package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoBigActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.ProjectInformationActivity;
import com.ansiyida.cgjl.activity.qytable_scape_activity;
import com.ansiyida.cgjl.activity.yztable_scape_activity;
import com.ansiyida.cgjl.bean.BuyListDetail;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.NewBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.NewDetailBean;
import com.ansiyida.cgjl.bean.ViewpointDetail;
import com.ansiyida.cgjl.bean.cgjl_bean.AdvertisementBean;
import com.ansiyida.cgjl.bean.cpml_detail_listbean;
import com.ansiyida.cgjl.bean.law_detail_listbean;
import com.ansiyida.cgjl.bean.purchaseDemand_detailn;
import com.ansiyida.cgjl.bean.qyml_detail_listbean;
import com.ansiyida.cgjl.bean.wintenderbean;
import com.ansiyida.cgjl.bean.yzml_detail_listbean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

//import com.ansiyida.cgjl.binding.Bind;

/**
 * Created by ansiyida on 2018/1/29.
 */
public class NewsDetailsAdapter extends RecyclerView.Adapter {
    private Window window;
    private NewsDetailsActivity context;
    private ArrayList<NewBean2> lists2;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean> lists;
    private ArrayList<Integer> zanFlag;
    private NewDetailBean detailBean;
    private law_detail_listbean detailBean_law;
    private ViewpointDetail viewpointDetail;
    private cpml_detail_listbean detailBean_CPML;
    private yzml_detail_listbean detailBean_yzml;
    private qyml_detail_listbean detailBean_qyml;
    private purchaseDemand_detailn detailBean_cgxq;
    private BuyListDetail detailBean_buy;
    private boolean flag;
    List<CommentBean.DataBean.ContentBean> comentBeanList;
    private int china_height = 0;
    private int english_height = 0;
    private List<JuBaoBean.DataBean> data = null;
    private ArrayList<Integer> comentCountList = new ArrayList<>();
    private int changeFlag = 0;
    private ImageView imageView;
    private boolean isScroll = false;
    private String type;
    private AdvertisementBean advertisementBean;
    private int width;

    public NewsDetailsAdapter(NewsDetailsActivity context, ArrayList<NewBean> lists, law_detail_listbean detailBean_law, NewDetailBean detailBean, BuyListDetail detailBean_buy, boolean flag, List<CommentBean.DataBean.ContentBean> comentBeanList, ArrayList<Integer> zanFlag, String type, Window window, cpml_detail_listbean detailBean_CPML, yzml_detail_listbean detailBean_yzml, qyml_detail_listbean detailBean_qyml, purchaseDemand_detailn detailBean_cgxq, ViewpointDetail viewpointDetail) {
        this.context = context;
        this.lists = lists;
        this.detailBean = detailBean;
        this.flag = flag;
        this.comentBeanList = comentBeanList;
        this.zanFlag = zanFlag;
        this.detailBean_law=detailBean_law;
        this.type=type;
        this.detailBean_buy=detailBean_buy;
        this.window=window;
        this.detailBean_CPML=detailBean_CPML;
        this.detailBean_yzml=detailBean_yzml;
        this.detailBean_qyml=detailBean_qyml;
        this.detailBean_cgxq=detailBean_cgxq;
        this.viewpointDetail=viewpointDetail;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:             //1.新闻详情页头部
                return new TopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_new_datails_top, parent, false));
            case 2:             //2.新闻内容部分
                return new WebViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_web, parent, false));

            case 3:             //3.新闻尾部
                return new BottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_newdetail_bottom, parent, false));

            case 4:             //4.评论部分
                return new UserTalkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_comment, parent, false));
            case 5:
                return new BottomlawViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_law_datails_bottom, parent, false));
            case 6:
                return new BottomcpmlViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cpml_top, parent, false));
            case 7:
                return new ContentcpmlViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cpml_content, parent, false));
            case 8:
                return new ContentcpmlViewHolder_yzml_content(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yzml_content, parent, false));
            case 9:
                return new ContentcpmlViewHolder_yzml_top(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yzml_biddinglable, parent, false));
            case 10:
                return new ContentcpmlViewHolder_qyml_content(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qyml_content, parent, false));
            case 11:
                return new ContentcpmlViewHolder_qyml_top(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qyml_biddinglable, parent, false));
            case 12:             //cgxq
                return new ContentcpmlViewHolder_cgxq_conten(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cgxq_content, parent, false));
            case 13:
                return new BottomlawViewHolder_cx(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cx_datails_bottom, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
           // 精灵智库
            if (viewpointDetail.getMessage() != null && viewpointDetail.getMessage().equals("成功")) {
                final ViewpointDetail.DataBean article = viewpointDetail.getData();
                if (holder instanceof WebViewHolder) {
                    //------------------------------------------------------------------------1.WebView部分
                    String html_body = article.getcontent();
                   // Log.e("智库精灵", article.getcontent() );
                    if(article.gettitle().contains("苏杨")){
                        width=80;
                    }else{
                        width = 100;
                    }
                    final WebViewHolder viewHolder = (WebViewHolder) holder;
                    //声明WebSettings子类
                    WebSettings webSettings = viewHolder.webView.getSettings();
                    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                    webSettings.setJavaScriptEnabled(true);
                    // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
                    // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
                    //设置自适应屏幕，两者合用
                    webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                    webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
                    //缩放操作
                    webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                    webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                    webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

                    //其他细节操作
                    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
                    webSettings.setAllowFileAccess(true); //设置可以访问文件
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
                    webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
                    webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
                    webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//
                    viewHolder.webView.addJavascriptInterface(new AndroidAndJSInterface(), "android");

                    if (flag) {
                        // 默认
                        if (viewHolder.webView != null) {
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    } else {
                        //原文
                        if (viewHolder.webView != null) {
                            //在我这里这个显示是乱码，所以用下面这种
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    }
//展示广
                    if(advertisementBean!=null) {
                        AdvertisementBean.DataBean dataBean = advertisementBean.getData().get(0);
                        if(dataBean.getTitle().toString().isEmpty()){
                            viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        }else{
                            viewHolder.Advertisement_rel.setVisibility(VISIBLE);
                        }
                        Glide.with(context).load(dataBean.getPictUrl()).into(viewHolder.Advertisement_image);
                        viewHolder.Advertisement_title.setText(dataBean.getTitle());
                        viewHolder.Advertisement_press.setText(dataBean.getNick());
                        if(dataBean.getReservePrice().equals("null") ||dataBean.getReservePrice().equals("Null") || dataBean.getReservePrice().equals("NULL")){
                            viewHolder.Advertisement_price.setText("");
                        }else {
                            viewHolder.Advertisement_price.setText("￥" + dataBean.getReservePrice());
                        }
                        viewHolder.AdvertisementGoBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isPkgInstalled(context, "com.taobao.taobao")) {
                                   /* Intent intent = new Intent();
                                    intent.setAction("Android.intent.action.VIEW");
                                    String url = "https://item.taobao.com/item.htm?ali_refid=a3_430406_1007:1124066525:N:485184283370953001_0_100:d45485b3013535b0cc4164b7cd5b7523&ali_trackid=1_d45485b3013535b0cc4164b7cd5b7523&spm=a21bo.50862.201874-sales.8.UYm99R&id="+(String)SharedPreferenceUtils.get(context,"TaoBaoShopId","");
                                    intent.setData(Uri.parse(url));
                                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);*/
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                    context.startActivity(intent);
                                }else{
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                        context.startActivity(intent);
                                    }catch (Exception e){
                                        Log.e("跳转失败", "onClick: "+SharedPreferenceUtils.get(context,"TaoBaoShopUri","") );
                                        Log.e("跳转失败", "onClick: "+e.getMessage() );
                                    }
                                }
                            }
                        });
                    }else{
                        viewHolder.Advertisement_rel.setVisibility(View.GONE);
                    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                }
                else if (holder instanceof BottomcpmlViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    String str="";
                    final BottomcpmlViewHolder viewHolder = (BottomcpmlViewHolder) holder;
                    if ("VP".equals(type)) {
                        Glide.with(context.getApplicationContext()).load(article.getimg()).centerCrop().into(viewHolder.iv_photo);
                        viewHolder.tv_time.setText(TimeUtils.mmtime_Time(article.getpublishTime()));
                        viewHolder.top_title.setText(article.gettitle());
                        viewHolder.tv_soure.setVisibility(VISIBLE);
                        viewHolder.tv_soure.setText("来源:"+article.gettypes());
                        if(article.getHits()!=null && !article.getHits().equals("")) {
                            viewHolder.Read.setVisibility(VISIBLE);
                            viewHolder.Read.setText("阅读:" + article.getHits());
                            viewHolder.iv_photo.setVisibility(View.GONE);
                        }
                    }
                }
            }
            //采购需求
            if (detailBean_law.getMessage() != null && detailBean_law.getMessage().equals("成功")) {
                final law_detail_listbean.DataBean.current_bean article = detailBean_law.getData().getcurrent_bean();
                final law_detail_listbean.DataBean.before_bean article_befor = detailBean_law.getData().getbefore_bean();
                final law_detail_listbean.DataBean.after_bean article_after = detailBean_law.getData().getafter_bean();
                final BuyListDetail.DataBean article_buy = detailBean_buy.getData();
                width=100;
                if (holder instanceof WebViewHolder) {                    //------------------------------------------------------------------------1.WebView部分
                    LogUtil.i("shan", "WebViewHolder");
                    String html_body = article.getcontent();
                    //   String html_en_body = article.getJca_en_cont();
                    final WebViewHolder viewHolder = (WebViewHolder) holder;
                    //声明WebSettings子类
                    WebSettings webSettings = viewHolder.webView.getSettings();
                    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                    webSettings.setJavaScriptEnabled(true);
                    // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
                    // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
                    //设置自适应屏幕，两者合用
                    webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                    webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
                    //缩放操作
                    webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                    webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                    webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

                    //其他细节操作
                    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
                    webSettings.setAllowFileAccess(true); //设置可以访问文件
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
                    webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
                    webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
                    webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//
                    viewHolder.webView.addJavascriptInterface(new AndroidAndJSInterface(), "android");

                    if (flag) {
                        // 默认
                        if (viewHolder.webView != null) {
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    } else {
                        //原文
                        if (viewHolder.webView != null) {
                            //在我这里这个显示是乱码，所以用下面这种
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    }
                    //展示广告
                    if(advertisementBean!=null) {
                        AdvertisementBean.DataBean dataBean = advertisementBean.getData().get(0);
                        if(dataBean.getTitle().toString().isEmpty()){
                            viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        }else{
                            viewHolder.Advertisement_rel.setVisibility(VISIBLE);
                        }
                        Glide.with(context).load(dataBean.getPictUrl()).into(viewHolder.Advertisement_image);
                        viewHolder.Advertisement_title.setText(dataBean.getTitle());
                        viewHolder.Advertisement_press.setText(dataBean.getNick());
                        if(dataBean.getReservePrice().equals("null") ||dataBean.getReservePrice().equals("Null") || dataBean.getReservePrice().equals("NULL")){
                            viewHolder.Advertisement_price.setText("");
                        }else {
                            viewHolder.Advertisement_price.setText("￥" + dataBean.getReservePrice());
                        }
                        viewHolder.AdvertisementGoBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isPkgInstalled(context, "com.taobao.taobao")) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                    context.startActivity(intent);
                                    /*Intent intent = new Intent();
                                    intent.setAction("Android.intent.action.VIEW");
                                    String url = "https://item.taobao.com/item.htm?ali_refid=a3_430406_1007:1124066525:N:485184283370953001_0_100:d45485b3013535b0cc4164b7cd5b7523&ali_trackid=1_d45485b3013535b0cc4164b7cd5b7523&spm=a21bo.50862.201874-sales.8.UYm99R&id="+(String)SharedPreferenceUtils.get(context,"TaoBaoShopId","");
                                    intent.setData(Uri.parse(url));
                                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);*/
                                }else{
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                        context.startActivity(intent);
                                    }catch (Exception e){
                                        Log.e("跳转失败", "onClick: "+e.getMessage() );
                                    }
                                }
                            }
                        });
                    }else{
                        viewHolder.Advertisement_rel.setVisibility(View.GONE);
                    }


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                } else if (holder instanceof TopViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    LogUtil.i("shan", "TopViewHolder");
                    final TopViewHolder viewHolder = (TopViewHolder) holder;
                    if ("P".equals(type)) {
                        viewHolder.tv_title.setText(article_buy.gettitle());
                       /* viewHolder.tv_name.setVisibility(VISIBLE);
                        viewHolder.tv_name.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
                        viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.color_blue));*/
                        viewHolder.tv_time.setVisibility(VISIBLE);
                        viewHolder.tv_laiYuan.setVisibility(VISIBLE);
                        viewHolder.tv_laiYuan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri content_url = Uri.parse(article_buy.getlink());
                                intent.setData(content_url);
                                context.startActivity(intent);
                                Log.e("公告", "TopViewHolder" );
                            }
                        });
                        viewHolder.tv_time.setText(TimeUtils.mmtime_Time(article_buy.getcreateTime()));
                        viewHolder.tv_laiYuan.setText(article_buy.getsource());

                    } else {
                        viewHolder.tv_title.setText(article.gettitle());

                    }

                } else if (holder instanceof BottomlawViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    String str="";
                    final BottomlawViewHolder viewHolder = (BottomlawViewHolder) holder;
                    if (!"L".equals(type)) {
                    } else {
                        viewHolder.iv_befor.setVisibility(View.VISIBLE);
                        viewHolder.iv_after.setVisibility(View.VISIBLE);
                        viewHolder.linear_bug.setVisibility(View.GONE);
                        if (article_befor == null)
                        {  str="上一篇:<font color='#4e86ed'>当前是第一篇,没有上一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_befor.setText(Html.fromHtml(str));}
                        else {
                            str="上一篇:"+"<font color='#4e86ed'>"+article_befor.gettitle()+"</font>";
                            viewHolder.iv_befor.setText(Html.fromHtml(str));
                            viewHolder.iv_befor.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("type", "L");
                                    intent.putExtra("id", article_befor.getid());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());

                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                        if (article_after == null)
                        {
                            str="下一篇:<font color='#4e86ed'>当前是最后一篇,没有下一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_after.setText(Html.fromHtml(str));}

                        else {
                            str="下一篇:"+"<font color='#4e86ed'>"+article_after.gettitle()+"</font>";
                            viewHolder.iv_after.setText(Html.fromHtml(str));
                            viewHolder.iv_after.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("type", "L");
                                    intent.putExtra("id", article_after.getid());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                    }

                }
            }
            //产品名录
            if (detailBean_CPML.getMessage() != null && detailBean_CPML.getMessage().equals("成功")) {
                final cpml_detail_listbean.DataBean.current_bean article = detailBean_CPML.getData().getcurrent_bean();
                final cpml_detail_listbean.DataBean.before_bean article_befor = detailBean_CPML.getData().getbefore_bean();
                final cpml_detail_listbean.DataBean.after_bean article_after = detailBean_CPML.getData().getafter_bean();

                if (holder instanceof ContentcpmlViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    final ContentcpmlViewHolder viewHolder = (ContentcpmlViewHolder) holder;
                    if ("CP".equals(type)) {
                        viewHolder.tv_name.setText(article.getcompanySummary().getname());
                        viewHolder.tv_zyly.setText(article.getmajorField());
                        viewHolder.tv_xjcd.setText(article.getdegree());
                        viewHolder.tv_xxly.setText(article.getsource());
                        viewHolder.tv_keyword.setText(article.getproductKeyword());
                        viewHolder.tv_content.setText(article.getproductSummary());
                    }
                } else if (holder instanceof BottomcpmlViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    final BottomcpmlViewHolder viewHolder = (BottomcpmlViewHolder) holder;
                    if ("CP".equals(type)) {
                        Glide.with(context.getApplicationContext()).load(article.getimg()).centerCrop().into(viewHolder.iv_photo);
                        viewHolder.tv_time.setText("发布于"+TimeUtils.mmtime_Time(article.getpublishTime()));
                        viewHolder.top_title.setText(article.gettitle());
                    }

                } else if (holder instanceof BottomlawViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    String str="";
                    final BottomlawViewHolder viewHolder = (BottomlawViewHolder) holder;
                    if ("CP".equals(type)) {
                        viewHolder.iv_befor.setVisibility(View.VISIBLE);
                        viewHolder.iv_after.setVisibility(View.VISIBLE);
                        viewHolder.linear_bug.setVisibility(View.GONE);
                        if (article_befor == null)
                        {  str="上一篇:<font color='#4e86ed'>当前是第一篇,没有上一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_befor.setText(Html.fromHtml(str));}
                        else {
                            str="上一篇:"+"<font color='#4e86ed'>"+article_befor.gettitle()+"</font>";
                            viewHolder.iv_befor.setText(Html.fromHtml(str));
                            viewHolder.iv_befor.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("type", "CP");
                                    intent.putExtra("id", article_befor.getid());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                                    intent.putExtra("title", article_befor.gettitle());
                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                        if (article_after == null)
                        {
                            str="下一篇:<font color='#4e86ed'>当前是最后一篇,没有下一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_after.setText(Html.fromHtml(str));}

                        else {
                            str="下一篇:"+"<font color='#4e86ed'>"+article_after.gettitle()+"</font>";
                            viewHolder.iv_after.setText(Html.fromHtml(str));
                            viewHolder.iv_after.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("type", "CP");
                                    intent.putExtra("id", article_after.getid());
                                    intent.putExtra("title", article_after.gettitle());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                        //展示广告
                        if(advertisementBean!=null) {
                            AdvertisementBean.DataBean dataBean = advertisementBean.getData().get(0);
                            if(dataBean.getTitle().toString().isEmpty()){
                                viewHolder.Advertisement_rel.setVisibility(View.GONE);
                            }else{
                                viewHolder.Advertisement_rel.setVisibility(VISIBLE);
                            }
                            Glide.with(context).load(dataBean.getPictUrl()).into(viewHolder.Advertisement_image);
                            viewHolder.Advertisement_title.setText(dataBean.getTitle());
                            viewHolder.Advertisement_press.setText(dataBean.getNick());
                            if(dataBean.getReservePrice().equals("null") ||dataBean.getReservePrice().equals("Null") || dataBean.getReservePrice().equals("NULL")){
                                viewHolder.Advertisement_price.setText("");
                            }else {
                                viewHolder.Advertisement_price.setText("￥" + dataBean.getReservePrice());
                            }
                            viewHolder.AdvertisementGoBuy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (isPkgInstalled(context, "com.taobao.taobao")) {
                                         /* Intent intent = new Intent();
                                    intent.setAction("Android.intent.action.VIEW");
                                    String url = "https://item.taobao.com/item.htm?ali_refid=a3_430406_1007:1124066525:N:485184283370953001_0_100:d45485b3013535b0cc4164b7cd5b7523&ali_trackid=1_d45485b3013535b0cc4164b7cd5b7523&spm=a21bo.50862.201874-sales.8.UYm99R&id="+(String)SharedPreferenceUtils.get(context,"TaoBaoShopId","");
                                    intent.setData(Uri.parse(url));
                                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);*/
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                        context.startActivity(intent);
                                    }else{
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                            context.startActivity(intent);
                                        }catch (Exception e){
                                            Log.e("跳转失败", "onClick: "+e.getMessage() );
                                        }
                                    }
                                }
                            });
                        }else{
                            viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        }
                    }

                }
            }
            if (detailBean_qyml.getMessage()!= null && detailBean_qyml.getMessage().equals("成功")) {
                final qyml_detail_listbean.DataBean article = detailBean_qyml.getData();

                if (holder instanceof ContentcpmlViewHolder_qyml_content) {//--------------------------------------------------------------------------3.新闻头部部分
                    final ContentcpmlViewHolder_qyml_content viewHolder = (ContentcpmlViewHolder_qyml_content) holder;
                    if ("QY".equals(type)) {
                        viewHolder.corporation.setText(article.getcorporation());
                        viewHolder.companyName.setText(article.getcompanyName());
                        if(article.getwebsite()!=null)
                        viewHolder.website.setText(article.getwebsite());
                        viewHolder.tradeType.setText(article.gettradeType());
                        viewHolder.majorField.setText(article.getmajorField());
                        viewHolder.businessKeyword.setText(article.getbusinessKeyword());
                        viewHolder.companySummary.setText(article.getcompanySummary());
                        viewHolder.businessSummary.setText(article.getbusinessSummary());
                        viewHolder.website.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String weburl="";
                                if((!"".equals(article.getwebsite()))&&(article.getwebsite()!=null))
                                {
                                    if(!article.getwebsite().contains("http"))
                                        weburl="http://"+article.getwebsite();
                                    else
                                        weburl=article.getwebsite();
                                    Intent intent = new Intent();
                                    intent.setAction("android.intent.action.VIEW");
                                    Uri content_url = Uri.parse(weburl);
                                    intent.setData(content_url);
                                    context.startActivity(intent);}
                                else
                                    ToastUtils.showMessage(context, "该公司没有网站！");

                            }
                        });
                        viewHolder.qygs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String weburl="";
                                if((!"".equals(article.getbusinessInfo()))&&(article.getbusinessInfo()!=null))
                                {
                                    if(!article.getbusinessInfo().contains("http"))
                                        weburl="http://"+article.getbusinessInfo();
                                    else
                                        weburl=article.getbusinessInfo();
                                    Intent intent = new Intent();
                                    intent.setAction("android.intent.action.VIEW");
                                   Uri content_url = Uri.parse((String)weburl);
                                    intent.setData(content_url);
                                    context.startActivity(intent);}
                                else
                                    ToastUtils.showMessage(context, "该公司没有网站！");

                            }
                        });

                    }

                }
                else if (holder instanceof ContentcpmlViewHolder_qyml_top) {//--------------------------------------------------------------------------3.新闻头部部分
                    LogUtil.i("shan", "TopViewHolder");
                    final ContentcpmlViewHolder_qyml_top viewHolder = (ContentcpmlViewHolder_qyml_top) holder;
                    if ("QY".equals(type)) {
                        lists2 = new ArrayList<>();
                        adapter = new NewsOneAdapter(lists2, context, context.getWindow());
                        // adapter1 = new NewsOneAdapter(lists1, activity, getActivity().getWindow());
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        viewHolder.recyclerView.setLayoutManager(mLayoutManager);
                        //recyclerView.setAdapter(adapter1);
                        viewHolder.recyclerView.setAdapter(adapter);
                        if ("QY".equals(type)) {
                            viewHolder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    return;
                                }
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                    super.onScrolled(recyclerView, dx, dy);
                                    return;
                                }
                            });
                            viewHolder.iv_full.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    qytable_scape_activity.detailBean_qyml_scape=detailBean_qyml;
                                    Intent intent = new Intent(context, qytable_scape_activity.class);
                                    (context).startActivity(intent);
                                }
                            });
                            if (NetWorkUtils.isNetworkConnected(context)) {
                                Call<wintenderbean> call = MyApplication.getNetApi().getwinTender((String) SharedPreferenceUtils.get(context, "app_token", ""),detailBean_qyml.getData().getcompanyName());
                                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                                call.enqueue(new Callback<wintenderbean>() {
                                    @Override
                                    public void onResponse(Call<wintenderbean> call, Response<wintenderbean> response) {
                                        if (response.isSuccessful()) {
                                            wintenderbean body = response.body();
                                            //  caigoulist body = response.body();

                                            if (body != null) {
                                                List<wintenderbean.DataBean> list = body.getData();

                                                if (list != null && list.size() > 0) {

                                                    //lists2.clear();
                                                    for (wintenderbean.DataBean listBean : list) {
                                                        NewBean2 newBean = new NewBean2();
                                                        newBean.setArtype("TB_y");
                                                        List<wintenderbean.DataBean.companySummaries_bean> list_company = listBean.getcompanySummaries();
                                                        if (list_company != null && list_company.size() > 0) {
                                                            String company = "";
                                                            String company_id = "";
                                                            for (wintenderbean.DataBean.companySummaries_bean listBean_company : list_company) {
                                                                company = company + listBean_company.getname();
                                                                company_id= company_id + listBean_company.getid();
                                                            }
                                                            newBean.setcompany(company);
                                                            newBean.setcompany_id(company_id);
                                                        }
                                                        newBean.setTitle(listBean.getpurchaseInfoSummary().gettitle());
                                                        newBean.setmoney(listBean.getpurchaseInfoSummary().getmoney());
                                                        newBean.setyz(listBean.getproprietorSummary_bean().getname());
                                                        newBean.setyzid(listBean.getproprietorSummary_bean().getid());
                                                        newBean.setproject_id(listBean.getpurchaseInfoSummary().getid());
                                                        lists2.add(newBean);
                                                    }
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }
                                        }
                                        call.cancel();
                                    }

                                    @Override
                                    public void onFailure(Call<wintenderbean> call, Throwable t) {
                                        call.cancel();
                                    }
                                });
                            } else {
                                 ToastUtils.showMessage(context, "当前网络不可用");
                            }
                        }
                    }
                }
            }
            //采购需求
            if (detailBean_cgxq.getMessage()!= null && detailBean_cgxq.getMessage().equals("成功")) {
                final purchaseDemand_detailn.DataBean.current_bean article = detailBean_cgxq.getData().getcurrent_bean();
                final purchaseDemand_detailn.DataBean.before_bean article_befor = detailBean_cgxq.getData().getbefore_bean();
                final purchaseDemand_detailn.DataBean.after_bean article_after = detailBean_cgxq.getData().getafter_bean();
                if (holder instanceof ContentcpmlViewHolder_cgxq_conten) {//--------------------------------------------------------------------------3.新闻头部部分

                     ContentcpmlViewHolder_cgxq_conten viewHolder = (ContentcpmlViewHolder_cgxq_conten) holder;
                    if ("CX".equals(type)| "CX_dy".equals(type)) {
                        //这段代码是赋值展示操作
                        viewHolder.tv_title.setText(article.gettitle());
                        viewHolder.tv_function.setText(article.getfunction());
                        String replace = article.getquota().replace("<p>", "");
                        String replace1 = replace.replace("</p>", "");
                        viewHolder.tv_quota.setText(replace1);
                        viewHolder.tv_link.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri content_url = Uri.parse(article.getlink());
                                intent.setData(content_url);
                                context.startActivity(intent);
                            }
                        });
                        viewHolder.tv_pushtime.setText(TimeUtils.mmtime_Time(article.getcreateTime()+""));
                        viewHolder.tv_endtime.setText(TimeUtils.mmtime_Time(article.getendTime())+"");
                    }
                } else if (holder instanceof BottomlawViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    String str="";
                    final BottomlawViewHolder viewHolder = (BottomlawViewHolder) holder;
                        viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        viewHolder.iv_befor.setVisibility(View.VISIBLE);
                        viewHolder.iv_after.setVisibility(View.VISIBLE);
                        viewHolder.linear_bug.setVisibility(View.GONE);
                        if (article_befor == null)
                        {  str="上一篇:<font color='#4e86ed'>当前是第一篇,没有上一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_befor.setText(Html.fromHtml(str));}
                        else {
                            str="上一篇:"+"<font color='#4e86ed'>"+article_befor.gettitle()+"</font>";
                            viewHolder.iv_befor.setText(Html.fromHtml(str));
                          //  viewHolder.iv_befor.setText("上一篇：" + article_befor.gettitle());
                            viewHolder.iv_befor.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                   // intent.putExtra("type", "CX");
                                    intent.putExtra("type", "CX");
                                    intent.putExtra("id", article_befor.getid());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());

                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                        if (article_after == null)
                        {
                            str="下一篇:<font color='#4e86ed'>当前是最后一篇,没有下一篇</font>";
                            // tv.setText(Html.fromHtml(str));
                            viewHolder.iv_after.setText(Html.fromHtml(str));}
                        //    viewHolder.iv_after.setText("下一篇：" + "没有了");

                        else {
                            str="下一篇:"+"<font color='#4e86ed'>"+article_after.gettitle()+"</font>";
                            viewHolder.iv_after.setText(Html.fromHtml(str));
                           // viewHolder.iv_after.setText("下一篇：" + article_after.gettitle());
                            viewHolder.iv_after.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                                    intent.putExtra("type", "CX");
                                    intent.putExtra("id", article_after.getid());
                                    //  LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                                    context.startActivity(intent);
                                    context.finish();
                                }
                            });
                        }
                    }

                else if (holder instanceof BottomlawViewHolder_cx) {//--------------------------------------------------------------------------3.新闻头部部分
                    final BottomlawViewHolder_cx viewHolder = (BottomlawViewHolder_cx) holder;
                    //展示广告
                    if(advertisementBean!=null) {
                        AdvertisementBean.DataBean dataBean = advertisementBean.getData().get(0);
                        if(dataBean.getTitle().toString().isEmpty()){
                            viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        }else{
                            viewHolder.Advertisement_rel.setVisibility(VISIBLE);
                        }
                        Glide.with(context).load(dataBean.getPictUrl()).into(viewHolder.Advertisement_image);
                        viewHolder.Advertisement_title.setText(dataBean.getTitle());
                        viewHolder.Advertisement_press.setText(dataBean.getNick());
                        if(dataBean.getReservePrice().equals("null") ||dataBean.getReservePrice().equals("Null") || dataBean.getReservePrice().equals("NULL")){
                            viewHolder.Advertisement_price.setText("");
                        }else {
                            viewHolder.Advertisement_price.setText("￥" + dataBean.getReservePrice());
                        }
                        viewHolder.AdvertisementGoBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isPkgInstalled(context, "com.taobao.taobao")) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                    context.startActivity(intent);
                                }else{
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                        context.startActivity(intent);
                                    }catch (Exception e){
                                        Log.e("跳转失败", "onClick: "+e.getMessage() );
                                    }
                                }
                            }
                        });
                    }else{
                        viewHolder.Advertisement_rel.setVisibility(View.GONE);
                    }

                    viewHolder.tv_bug.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initPopuWindow_dismiss(article.getid(), "", position,"purchaseDemand",viewHolder.tv_bug,viewHolder.tv_bug_non);
                        }
                    });

                }

            }
            if (detailBean_yzml.getMessage()!= null && detailBean_yzml.getMessage().equals("成功")) {
                final yzml_detail_listbean.DataBean.proprietorInfo_bean article = detailBean_yzml.getData().getproprietorInfo();
                final List<yzml_detail_listbean.DataBean.tender_bean> list = detailBean_yzml.getData().gettender();

                if (holder instanceof ContentcpmlViewHolder_yzml_content) {//--------------------------------------------------------------------------3.新闻头部部分
                    LogUtil.i("shan", "TopViewHolder");
                    final ContentcpmlViewHolder_yzml_content viewHolder = (ContentcpmlViewHolder_yzml_content) holder;
                    if ("YZ".equals(type)) {
                        viewHolder.qy_today.setText(article.gettodayNum()+"条");
                        viewHolder.tv_qyhistory.setText(article.getupdateNum()+"条");

                    }

                } else if (holder instanceof ContentcpmlViewHolder_yzml_top) {//--------------------------------------------------------------------------3.新闻头部部分
                    LogUtil.i("shan", "TopViewHolder");

                    final ContentcpmlViewHolder_yzml_top viewHolder = (ContentcpmlViewHolder_yzml_top) holder;
                    lists2 = new ArrayList<>();
                    // lists1 = new ArrayList<>();
                    adapter = new NewsOneAdapter(lists2, context, context.getWindow());
                    // adapter1 = new NewsOneAdapter(lists1, activity, getActivity().getWindow());
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    viewHolder.recyclerView.setLayoutManager(mLayoutManager);
                    //recyclerView.setAdapter(adapter1);
                    viewHolder.recyclerView.setAdapter(adapter);
                    if ("YZ".equals(type)) {
                        viewHolder.iv_full.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                yztable_scape_activity.detailBean_yzml_scape=detailBean_yzml;
                                Intent intent = new Intent(context, yztable_scape_activity.class);
                                (context).startActivity(intent);

                            }
                        });
                        for (yzml_detail_listbean.DataBean.tender_bean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("TB");
                            newBean.setTitle(listBean.getpurchaseInfoSummary().gettitle());
                            newBean.setLable("暂无");
                            newBean.setZhiDing(listBean.getpurchaseInfoSummary().getmoney());
                            newBean.setId(listBean.getpurchaseInfoSummary().getid());
                            lists2.add(newBean);
                        }
                        adapter.notifyDataSetChanged();

                    }

                }

            }
            if (detailBean_buy.getMessage() != null && detailBean_buy.getMessage().equals("成功")) {

                final BuyListDetail.DataBean article_buy = detailBean_buy.getData();
                if (holder instanceof WebViewHolder) {                    //------------------------------------------------------------------------1.WebView部分
                    LogUtil.i("shan", "WebViewHolder");

                    String html_body = article_buy.getcontent();
                    //   String html_en_body = article.getJca_en_cont();
                    final WebViewHolder viewHolder = (WebViewHolder) holder;
                    //声明WebSettings子类
                    WebSettings webSettings = viewHolder.webView.getSettings();

                    //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
                    webSettings.setJavaScriptEnabled(true);
                    // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
                    // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
                    //设置自适应屏幕，两者合用
                    webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
                    webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
                    //缩放操作
                    webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                    webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
                    webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
                    //其他细节操作
                    webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
                    webSettings.setAllowFileAccess(true); //设置可以访问文件
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
                    webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
                    webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
                    webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//
                    viewHolder.webView.addJavascriptInterface(new AndroidAndJSInterface(), "android");

                    if (flag) {
                        // 默认
                        if (viewHolder.webView != null) {
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    } else {
                        //原文
                        if (viewHolder.webView != null) {
                            //在我这里这个显示是乱码，所以用下面这种
                            viewHolder.webView.loadDataWithBaseURL(null, getHtml(html_body), "text/html", "utf-8", null);
                        }
                    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                } else if (holder instanceof TopViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    LogUtil.i("shan", "TopViewHolder");
                    final TopViewHolder viewHolder = (TopViewHolder) holder;

                    viewHolder.tv_title.setText(article_buy.gettitle());
                    viewHolder.tv_name.setVisibility(VISIBLE);
                    viewHolder.tv_title.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
                    viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.color_blue));
                    viewHolder.tv_time.setVisibility(VISIBLE);
                    viewHolder.tv_laiYuan.setVisibility(VISIBLE);
                    viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(article_buy.getlink());
                            intent.setData(content_url);
                            context.startActivity(intent);
                        }
                    });
                    viewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ProjectInformationActivity.class);
                            intent.putExtra("title",article_buy.gettitle());
                            intent.putExtra("source",article_buy.getsource());
                            context.startActivity(intent);
                        }
                    });
                    viewHolder.tv_time.setText(TimeUtils.mmtime_Time(article_buy.getpublishTime()));
                    viewHolder.tv_laiYuan.setText(article_buy.getsource());


                } else if (holder instanceof BottomlawViewHolder) {//--------------------------------------------------------------------------3.新闻头部部分
                    final BottomlawViewHolder viewHolder = (BottomlawViewHolder) holder;
                    viewHolder.iv_befor.setVisibility(View.GONE);
                    viewHolder.iv_after.setVisibility(View.GONE);
                    if(!detailBean_buy.getData().getisReportErrors())
                    {
                        viewHolder.linear_bug.setVisibility(View.VISIBLE);
                        viewHolder.tv_bug.setVisibility(View.VISIBLE);
                        viewHolder.tv_bug_non.setVisibility(View.GONE);
                        viewHolder.tv_bug.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    initPopuWindow_dismiss(detailBean_buy.getData().getid(), "", position,"purchaseInfo",viewHolder.tv_bug,viewHolder.tv_bug_non);
                                         }
                        });
                    }
                    else {
                        viewHolder.linear_bug.setVisibility(View.VISIBLE);
                        viewHolder.tv_bug.setVisibility(View.GONE);
                        viewHolder.tv_bug_non.setVisibility(View.VISIBLE);
                    }
                    //展示广告
                    if(advertisementBean!=null) {
                        AdvertisementBean.DataBean dataBean = advertisementBean.getData().get(0);
                        if(dataBean.getTitle().toString().isEmpty()){
                            viewHolder.Advertisement_rel.setVisibility(View.GONE);
                        }else{
                            viewHolder.Advertisement_rel.setVisibility(VISIBLE);
                        }
                        Glide.with(context).load(dataBean.getPictUrl()).into(viewHolder.Advertisement_image);
                        viewHolder.Advertisement_title.setText(dataBean.getTitle());
                        viewHolder.Advertisement_press.setText(dataBean.getNick());
                        if(dataBean.getReservePrice().equals("null") ||dataBean.getReservePrice().equals("Null") || dataBean.getReservePrice().equals("NULL")){
                            viewHolder.Advertisement_price.setText("");
                        }else {
                            viewHolder.Advertisement_price.setText("￥" + dataBean.getReservePrice());
                        }
                        viewHolder.AdvertisementGoBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isPkgInstalled(context, "com.taobao.taobao")) {
                                    /* Intent intent = new Intent();
                                    intent.setAction("Android.intent.action.VIEW");
                                    String url = "https://item.taobao.com/item.htm?ali_refid=a3_430406_1007:1124066525:N:485184283370953001_0_100:d45485b3013535b0cc4164b7cd5b7523&ali_trackid=1_d45485b3013535b0cc4164b7cd5b7523&spm=a21bo.50862.201874-sales.8.UYm99R&id="+(String)SharedPreferenceUtils.get(context,"TaoBaoShopId","");
                                    intent.setData(Uri.parse(url));
                                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);*/
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                    context.startActivity(intent);
                                }else{
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String)SharedPreferenceUtils.get(context,"TaoBaoShopUri","")));
                                        context.startActivity(intent);
                                    }catch (Exception e){
                                        Log.e("跳转失败", "onClick: "+e.getMessage() );
                                    }
                                }
                            }
                        });
                    }else{
                        viewHolder.Advertisement_rel.setVisibility(View.GONE);
                    }

                }
            }
        }
        catch (Exception e)
        {
            e.toString();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    /**
     * 防止adapter刷新屏幕闪烁
     */
    @Override
    public long getItemId(int position) {

        return position;
    }

    public void activityUseTabEnglishChinese() {
        if (isScroll) {
         //   context.click_chinaToEnglish();
            flag = !flag;
            notifyDataSetChanged();
            LogUtil.i("scroll", "不可以进行手势");
        } else {
            LogUtil.i("scroll", "可以进行手势");
        }

    }

    public boolean isScroll() {
        return isScroll;
    }

    class UserTalkViewHolder extends RecyclerView.ViewHolder {
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
        @Bind(R.id.text_huiFu)
        TextView text_huiFu;

        @Bind(R.id.tv_talk)
        TextView tv_talk;


        public UserTalkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_care)
        ImageView iv_care;
        int tag = 0;
        int tag_chinaToEnglish = 0;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.tv_laiYuan)
        TextView tv_laiYuan;
        @Bind(R.id.linear_txt)
        LinearLayout linear_txt;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class BottomlawViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_befor)
        TextView iv_befor;
        @Bind(R.id.tv_after)
        TextView iv_after;
        @Bind(R.id.linear_txt)
        LinearLayout linear_txt;
        @Bind(R.id.linear_bug)
        LinearLayout linear_bug;
        @Bind(R.id.tv_bug)
        TextView tv_bug;
        @Bind(R.id.tv_bug_non)
        TextView tv_bug_non;

        @Bind(R.id.Advertisement_image)
        ImageView Advertisement_image;
        @Bind(R.id.Advertisement_title)
        TextView Advertisement_title;
        @Bind(R.id.Advertisement_press)
        TextView Advertisement_press;
        @Bind(R.id.Advertisement_price)
        TextView Advertisement_price;
        @Bind(R.id.Advertisement_rel)
        RelativeLayout Advertisement_rel;
        @Bind(R.id.go_buy)
        Button AdvertisementGoBuy;
        public BottomlawViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class BottomlawViewHolder_cx extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_bug)
        TextView tv_bug;
        @Bind(R.id.tv_bug_non)
        TextView tv_bug_non;

        @Bind(R.id.Advertisement_image)
        ImageView Advertisement_image;
        @Bind(R.id.Advertisement_title)
        TextView Advertisement_title;
        @Bind(R.id.Advertisement_press)
        TextView Advertisement_press;
        @Bind(R.id.Advertisement_price)
        TextView Advertisement_price;
        @Bind(R.id.Advertisement_rel)
        RelativeLayout Advertisement_rel;
        @Bind(R.id.go_buy)
        Button AdvertisementGoBuy;
        public BottomlawViewHolder_cx(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class BottomcpmlViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_photo)
        ImageView iv_photo;
        @Bind(R.id.tv_time)
        TextView tv_time;
        @Bind(R.id.top_title)
        TextView top_title;
        @Bind(R.id.tv_soure)
        TextView tv_soure;
        @Bind(R.id.Read)
        TextView Read;
        public BottomcpmlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_zyly)
        TextView tv_zyly;
        @Bind(R.id.tv_xjcd)
        TextView tv_xjcd;
        @Bind(R.id.tv_xxly)
        TextView tv_xxly;
        @Bind(R.id.tv_keyword)
        TextView tv_keyword;
        @Bind(R.id.tv_content)
        TextView tv_content;
        public ContentcpmlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder_qyml_content extends RecyclerView.ViewHolder {
        @Bind(R.id.corporation)
        TextView corporation;
        @Bind(R.id.companyName)
        TextView companyName;
        @Bind(R.id.website)
        TextView website;
        @Bind(R.id.tradeType)
        TextView tradeType;
        @Bind(R.id.majorField)
        TextView majorField;
        @Bind(R.id.businessKeyword)
        TextView businessKeyword;
        @Bind(R.id.companySummary)
        TextView companySummary;
        @Bind(R.id.businessSummary)
        TextView businessSummary;
        @Bind(R.id.qygs)
        TextView qygs;
        public ContentcpmlViewHolder_qyml_content(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder_yzml_content extends RecyclerView.ViewHolder {
        @Bind(R.id.qy_today)
        TextView qy_today;
        @Bind(R.id.tv_qyhistory)
        TextView tv_qyhistory;

        public ContentcpmlViewHolder_yzml_content(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder_yzml_top extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_full)
        ImageView iv_full;
        @Bind(R.id.recyclerView_fragment1)
        RecyclerView recyclerView;


        public ContentcpmlViewHolder_yzml_top(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder_qyml_top extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_full)
        ImageView iv_full;
        @Bind(R.id.recyclerView_fragment1)
        RecyclerView recyclerView;
       @Bind(R.id.xrefreshView_fragment1)
        XRefreshView xrefreshView;


        public ContentcpmlViewHolder_qyml_top(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class ContentcpmlViewHolder_cgxq_conten extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_pushtime)
        TextView tv_pushtime;
        @Bind(R.id.tv_endtime)
        TextView tv_endtime;
        @Bind(R.id.tv_function)
        TextView tv_function;
        @Bind(R.id.tv_quota)
        TextView tv_quota;
        @Bind(R.id.tv_link)
        TextView tv_link;

        public ContentcpmlViewHolder_cgxq_conten(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class BottomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.btn1)
        Button btn1;
        @Bind(R.id.btn2)
        Button btn2;
        @Bind(R.id.btn3)
        Button btn3;
        @Bind(R.id.btn4)
        Button btn4;
        @Bind(R.id.btn5)
        Button btn5;
        @Bind(R.id.btn6)
        Button btn6;
        @Bind(R.id.tv_like)
        TextView tv_like;
        @Bind(R.id.tv_bad)
        TextView tv_bad;
        @Bind(R.id.tv_report)
        TextView tv_report;
        @Bind(R.id.tv_readCount)
        TextView readCount;
        @Bind(R.id.tv_channelName)
        TextView tv_channelName;
        @Bind(R.id.tv_xiangGuan1)
        TextView tv_xiangGuan1;
        @Bind(R.id.tv_xiangGuan2)
        TextView tv_xiangGuan2;
        @Bind(R.id.tv_xiangGuan3)
        TextView tv_xiangGuan3;
        @Bind(R.id.tv_xiangGuan4)
        TextView tv_xiangGuan4;
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

        public BottomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class WebViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.webView)
        WebView webView;

        @Bind(R.id.Advertisement_image)
        ImageView Advertisement_image;
        @Bind(R.id.Advertisement_title)
        TextView Advertisement_title;
        @Bind(R.id.Advertisement_press)
        TextView Advertisement_press;
        @Bind(R.id.Advertisement_price)
        TextView Advertisement_price;
        @Bind(R.id.Advertisement_rel)
        RelativeLayout Advertisement_rel;
        @Bind(R.id.go_buy)
        Button AdvertisementGoBuy;
        public WebViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private void setFanKui(final Button btn, final String fanKuiId, final String type, final String id,final String isSelected) {
        final String str = btn.getText().toString().trim();
        if ("Y".equals(isSelected)){
            btn.setBackgroundResource(R.drawable.shape_newdetail_fankui_yes);
            btn.setTextColor(0xffffffff);
            btn.setClickable(false);
        }else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.i("feed", "id:" + id + ",type:" + type + ",fanKuiId:" + fanKuiId);
                    Call<DefaultBean> call = MyApplication.getNetApi().userFeedBack(id, type, fanKuiId, "",(String)SharedPreferenceUtils.get(context,"app_token",""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String status = response.body().getStatus();
                                if ("0001".equals(status)){
                                    btn.setBackgroundResource(R.drawable.shape_newdetail_fankui_yes);
                                    btn.setTextColor(0xffffffff);
                                    btn.setClickable(false);
                                    ToastUtils.showMessage(context.getApplicationContext(), "反馈成功");
                                } else {
                                    ToastUtils.showMessage(context.getApplicationContext(), response.body().getMessage());
                                }
                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), "未知错误");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(context.getApplicationContext(), "未知错误");

                        }
                    });

                }
            });
        }


    }

    public void setBodyData(NewDetailBean detailBean) {
        this.detailBean = detailBean;
        lists.add(new NewBean(1, "", null, null));
        lists.add(new NewBean(2, "", null, null));
        getAdvertisement();
    }
    public void setBodyData_law(law_detail_listbean detailBean) {
        this.detailBean_law = detailBean;
        lists.add(new NewBean(1, "", null, null));
        lists.add(new NewBean(2, "", null, null));
        lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_buy(BuyListDetail detailBean) {
        this.detailBean_buy= detailBean;
        lists.add(new NewBean(1, "", null, null));
        lists.add(new NewBean(2, "", null, null));
        lists.add(new NewBean(5,  "", null, null));
     //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_viewpoint(ViewpointDetail detailBean) {
        this.viewpointDetail= detailBean;
        lists.add(new NewBean(6, "", null, null));
        lists.add(new NewBean(2, "", null, null));
       // lists.add(new NewBean(5,  "", null, null));
        //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_buy(purchaseDemand_detailn detailBean) {
        this.detailBean_cgxq= detailBean;
        lists.add(new NewBean(12, "", null, null));
        lists.add(new NewBean(13, "", null, null));
        lists.add(new NewBean(5,  "", null, null));
        //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_cpml(cpml_detail_listbean  detailBean) {
        this.detailBean_CPML  = detailBean;
        lists.add(new NewBean(6, "", null, null));
        lists.add(new NewBean(7, "", null, null));
        lists.add(new NewBean(5,  "", null, null));
        //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_yzml(yzml_detail_listbean  detailBean) {
        this.detailBean_yzml  = detailBean;
        lists.add(new NewBean(8, "", null, null));
        lists.add(new NewBean(9, "", null, null));

        //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setBodyData_qyml(qyml_detail_listbean  detailBean) {
        this.detailBean_qyml  = detailBean;

        lists.add(new NewBean(10, "", null, null));
        lists.add(new NewBean(11,  "", null, null));
        //   lists.add(new NewBean(5,  "", null, null));
        getAdvertisement();
    }
    public void setCommentBeans(List<CommentBean.DataBean.ContentBean> contentList) {
        comentBeanList.clear();
        comentCountList.clear();
        comentBeanList.addAll(contentList);
        int size = comentBeanList.size();
        if (comentBeanList != null && size > 0) {
            int listLength = lists.size();
            zanFlag.clear();
            if (listLength > 3) {
                for (int x = listLength - 1; x >= 3; x--) {
                    lists.remove(x);
                }
            }
            LogUtil.i("length", "listLengthBegin:" + lists.size());

            for (int x = 0; x < size; x++) {
                lists.add(new NewBean(4, "", null, null));
                comentBeanList.get(x);
                String isGread = contentList.get(x).getIsGread();
                if ("Y".equals(isGread)) {
                    zanFlag.add(1);
                } else {
                    zanFlag.add(0);

                }
                comentCountList.add(comentBeanList.get(x).getCommnum());
            }
        }
        LogUtil.i("length", "listLength:" + lists.size() + ",flagLength:" + zanFlag.size());
        notifyItemChanged(3);

    }

    public void setLoadComments(List<CommentBean.DataBean.ContentBean> contentList) {
        int size = contentList.size();
        if (comentBeanList != null && size > 0) {
            comentBeanList.addAll(contentList);
            for (int x = 0; x < size; x++) {
                lists.add(new NewBean(4, "", null, null));
                String isGread = contentList.get(x).getIsGread();
                if ("Y".equals(isGread)) {
                    zanFlag.add(1);
                } else {
                    zanFlag.add(0);

                }
                comentCountList.add(contentList.get(x).getCommnum());
            }
            notifyDataSetChanged();

        } else {
            ToastUtils.showMessage(context, "已加载到底部");
        }

    }

    public void setFanKui() {
        lists.add(new NewBean(3, "", null, null));
        notifyItemChanged(3);
    }

    private void setClick(final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("clickTab", textView.getText().toString().trim());
                context.startActivity(intent);
                context.finish();
            }
        });
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


    /**
     * 不感兴趣弹窗
     */
    private PopupWindow popupWindow_dismiss;
    private View contentView_dismiss;
    private int choiceNum = 0;
   private List<JuBaoBean.DataBean> data_bug;
    private ArrayList<String> choiceList = new ArrayList<>();
    private HashMap<String, String> choiceMap = new HashMap<>();

    public void initPopuWindow_dismiss(final String id, final String type, final int position, final String genre,final View view1,final View view2) {
        if (popupWindow_dismiss == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            contentView_dismiss = mLayoutInflater.inflate(R.layout.popwindow_dismiss, null);
            popupWindow_dismiss = new PopupWindow(contentView_dismiss, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        final CheckBox cb1 = (CheckBox) contentView_dismiss.findViewById(R.id.cb1);                     //不感兴趣
        final CheckBox cb2 = (CheckBox) contentView_dismiss.findViewById(R.id.cb2);                     //内容质量差
        final CheckBox cb3 = (CheckBox) contentView_dismiss.findViewById(R.id.cb3);                     //来源：联合早报
        final CheckBox cb4 = (CheckBox) contentView_dismiss.findViewById(R.id.cb4);                     //内容太水
        final CheckBox cb5 = (CheckBox) contentView_dismiss.findViewById(R.id.cb5);                     //重复推荐
        final CheckBox cb6 = (CheckBox) contentView_dismiss.findViewById(R.id.cb6);                     //不想看：选美

        final TextView sure = (TextView) contentView_dismiss.findViewById(R.id.btn_sure);                   //确定按钮
        final TextView btn_cancel= (TextView) contentView_dismiss.findViewById(R.id.btn_cancel);
        final TextView choiceCount = (TextView) contentView_dismiss.findViewById(R.id.tv_choiceCount);  //已选个数
        final TextView txt_title = (TextView) contentView_dismiss.findViewById(R.id.txt_title);         //弹框标题
        RelativeLayout relative_cancel = (RelativeLayout) contentView_dismiss.findViewById(R.id.relative_cancel);
        RelativeLayout relative_child = (RelativeLayout) contentView_dismiss.findViewById(R.id.relative_child);
        cb1.setChecked(false);
        cb2.setChecked(false);

        choiceList.clear();
      //  choiceMap.clear();

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                 /*   Drawable simLockStatusIcon =context.getResources().getDrawable(R.drawable.shape_btn_setting_none);
                    simLockStatusIcon.setBounds(0, 0, simLockStatusIcon.getMinimumWidth(), simLockStatusIcon.getMinimumHeight());
                    buttonView.setCompoundDrawables(null, null, simLockStatusIcon , null);*/
                    buttonView.setTextColor(Color.BLUE);
                    buttonView.setBackground(context.getResources().getDrawable(R.drawable.shape_bug_bt));
                 //   choiceNum++;
                    choiceList.add(buttonView.getText().toString().trim());
                }
                else {
                    buttonView.setTextColor(Color.parseColor("#474747"));
                    buttonView.setBackground(context.getResources().getDrawable(R.drawable.shape_bug_black_bt));
                  //  choiceNum--;
                    choiceList.remove(buttonView.getText().toString().trim());
                }
             /*   if (choiceNum == 0) {
                   // sure.setVisibility(View.GONE);
                   // choiceCount.setVisibility(View.GONE);
                  //  txt_title.setVisibility(View.VISIBLE);
                } else {
                    sure.setVisibility(View.VISIBLE);
                    choiceCount.setVisibility(View.VISIBLE);
                    txt_title.setVisibility(View.GONE);
                    choiceCount.setText("已选" + choiceNum + "个理由");
                }*/
            }
        };
        relative_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_dismiss.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_dismiss.dismiss();
            }
        });
        relative_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceList.size() > 0) {
                    StringBuffer sb = new StringBuffer();
                    for (String str : choiceList) {
                        sb.append(str + ",");
                    }
                    LogUtil.i("feed", "id:" + id + ",type:" + type + ",idlist:" + sb.toString().trim());
                    Call<DefaultBean> call = MyApplication.getNetApi().postbug((String) SharedPreferenceUtils.get(context, "app_token", ""),id,genre,sb.toString().trim() );
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String status = response.body().getStatus();
                                if ("200".equals(status)) {
                                 //   removePosition(position);
                                    ToastUtils.showMessage(context, "感谢您的提交，我们会抓紧解决");
                                    view1.setVisibility(View.GONE);
                                    view2.setVisibility(View.VISIBLE);
                                    popupWindow_dismiss.dismiss();
                                } else {
                                    ToastUtils.showMessage(context.getApplicationContext(), response.body().getMessage());
                                }
                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), "未知错误");
                            }
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(context.getApplicationContext(), "未知错误");

                        }
                    });
                } else {
                  //  popupWindow_dismiss.dismiss();
                    ToastUtils.showMessage(context, "选择提交内容");
                }
            }

        });
        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp);
        popupWindow_dismiss.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_dismiss.setOutsideTouchable(true);
        popupWindow_dismiss.setFocusable(true);
        popupWindow_dismiss.showAtLocation(contentView_dismiss, Gravity.CENTER, 0, 0);
        popupWindow_dismiss.update();
        popupWindow_dismiss.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
    }
    public String getHtml(String html_body) {
        if(width==0){
            width=100;
        }
        WindowManager wm1 = context.getWindowManager();
        width = PX_DP(context,(wm1.getDefaultDisplay().getWidth()));

        String html = "<!DOCTYPE  html>\n" +
                "<html><head><meta  charset=\"utf-8\">\n" +
                "<title></title>\n" +
                "<meta  content=\"width=device-width,  initial-scale=1.0,  minimum-scale=1.0,  maximum-scale=1.0,  user-scalable=no\"  name=\"viewport\"  />\n" +
                "<meta  http-equiv=\"Content-Type\"  content=\"text/html;  charset=UTF-8\"  />\n" +
                "<meta  http-equiv=\"Pragma\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Cache-Control\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Expires\"  content=\"0\"  />\n" +
                "<meta  http-equiv=\"Access-Control-Allow-Origin\"  content=\"*\" />\n" +
                "<meta  name=\"format-detection\"  content=\"telephone=no\"  />\n" +
                "<style>  \n" +
                "body{  \n" +
                "\tpadding:0  0px;\n" +
                "\tfont-size:18px;\n" +
                "\tmargin:0;\n" +
                "\tbackground-color:#fff;color:#333;  \n" +
                "\tfont-family:\"Microsoft  YaHei\\\",\\\"Open  Sans\\\",\\\"Helvetica  Neue\\\",Helvetica,Arial,sans-serif\";\n" +
                "\tfont-weight:300;  word-break:  break-all;}\n" +
                "p{text-indent: 0px; text-align:left;line-height:20px;font-size:14px !important; margin:0px;}\n" +
                "span{text-align:left;line-height:30px;font-size:16px !important; }\n" +
                "strong{font-size:16px; text-color:#FF333333; }\n" +
                "blockquote  p{text-indent: 0px; margin-top:3px;}\n" +
                "blockquote{padding:16px;    background-color:#f6f6f6;  font-size:16px;  display:  block;  -webkit-margin-before:  1em;  -webkit-margin-after:  0em;-webkit-margin-start:  0px;-webkit-margin-end:  0px;}\n" +
                "img{max-width:100%;  height:auto; margin-left：0px;}\n" +
                "div{text-align:left;line-height:30px;font-size:18px !important;}\n" +
                "h1{font-size:24px;}\n" +
                "h2{font-size:21px;}\n" +
                "h3{font-size:19px;}\n" +
                "iframe,img  {max-width:  100%;  }\n" +
                "a{color:#4d8ae7;}\n" +
                "</style>\n" +
                "  <script>\n" +
                "\t\tfunction getImgArray(){\n" +
                "\t\t\tvar arr=document.getElementsByTagName('img');\n" +
                "\t\t\tvar imgArray=[];\n" +
                "\t\t\tfor(var x=0;x<arr.length;x++){\n" +
                "\t\t\t\timgArray[x]=arr[x].src;\n" +
                "\t\t\t}\n" +
                "\t\t\treturn imgArray;\n" +
                "\t\t}\t\n" +
                "  </script>" +
                " <style>\n" +
                "  img {\n" +
                "\tmax-width:"+width+"px!important;\n" +
                "\theight:auto !important;\n" +
                "  }\n" +
                "  </style>" +
                "  </style>" +
                "</head>  \n" +
                "<body>\n" +
                html_body +
                "</body>\n" +
                "</html>";
        html = html.replace("<img", "<img onclick='window.android.showToast(getImgArray(),this.src)'");
        html = html.replace("text-align:center;text-indent:2em;", "text-align:center;");
        return html;
    }



    class AndroidAndJSInterface {
        @JavascriptInterface
        public void showToast(String[] imgArray, String img) {
            int length = imgArray.length;
            StringBuffer stringBuffer = new StringBuffer();
            HashMap<String, Integer> imgMap = new HashMap<>();
            for (int x = 0; x < length; x++) {
                String img2 = imgArray[x];
                imgMap.put(img2, x);
                if (x == length - 1) {
                    stringBuffer.append(img2);
                } else {
                    stringBuffer.append(img2 + ",");
                }
            }
            Intent intent = new Intent(context, PhotoBigActivity.class);
            String str = stringBuffer.toString();
            intent.putExtra("picString", str);
            intent.putExtra("fromPosition", imgMap.get(img));
            context.startActivity(intent);
        }
    }

    public static boolean isPkgInstalled(Context context, String pkgName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    private void getAdvertisement() {
       try {
           String advertisement = (String) SharedPreferenceUtils.get(context, "Advertisement", "");
           if(advertisement.toString().isEmpty()){
               advertisementBean =null;
               Log.e("广告", "无数据+"+advertisement );
           }else {
               Log.e("广告", "有数据+" + advertisement);
               Gson gson = new Gson();
               advertisementBean = gson.fromJson(advertisement, AdvertisementBean.class);
               String ShopUri = advertisementBean.getData().get(0).getItemUrl();
               SharedPreferenceUtils.put(context, "TaoBaoShopUri", "https:"+ShopUri);
               SharedPreferenceUtils.put(context,"TaoBaoShopId",advertisementBean.getData().get(0).getNumIid()+"");
           }
           notifyDataSetChanged();
       }catch (Exception e){
           Log.e("NewsDetailAdvertisement", e.getMessage() );
           notifyDataSetChanged();
       }
    }
    public static int PX_DP(Context context, float PxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (PxValue / scale + 0.5f-80);
    }

}
