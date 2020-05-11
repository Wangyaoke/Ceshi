package com.ansiyida.cgjl.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.utils.Utils;
import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.CG_dy_Activity;
import com.ansiyida.cgjl.activity.ClassifiedActivity1;
import com.ansiyida.cgjl.activity.CollectActivity;
import com.ansiyida.cgjl.activity.InvoivedetalAvtivity;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoDetailsActivity;
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.activity.bidding_class_activity;
import com.ansiyida.cgjl.activity.dy_setting_layout;
import com.ansiyida.cgjl.bean.BannerBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.caigoulist;
import com.ansiyida.cgjl.dialog.LoadingDialog_cellect;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.BetterRecyclerView;
import com.ansiyida.cgjl.view.Entity;
import com.ansiyida.cgjl.view.RecyclerBanner;
import com.ansiyida.cgjl.view.SlidingButtonView;
import com.ansiyida.cgjl.view.marquee.MarqueeView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ansiyida.cgjl.util.ResUtil.getResources;

public class NewsOneAdapter extends RecyclerView.Adapter implements SlidingButtonView.IonSlidingButtonListener {
    private SlidingButtonView mMenu = null;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private String city_Serch="";
    private String all_city="";
    private int pageNum = 1;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private ArrayList<NewBean2> lists1;
    private LoadingDialog_cellect loadingDialog;
    private Context context;
    private Window window;
    private int playerPosition = -1;
    private View.OnClickListener listener;
    private long lastClickTime = 0L;
    // 两次点击间隔不能少于1000ms0
    private static final int FAST_CLICK_DELAY_TIME = 1000;

    private int[] backgroundArray = {R.mipmap.yt_1, R.mipmap.yt_2, R.mipmap.yt_3, R.mipmap.yt_4,
            R.mipmap.yt_5, R.mipmap.yt_6, R.mipmap.yt_7, R.mipmap.yt_8, R.mipmap.yt_9, R.mipmap.yt_10,
            R.mipmap.yt_11, R.mipmap.yt_12
    };

    public NewsOneAdapter(ArrayList<NewBean2> lists, Context context, Window window) {
        try {
            this.lists = lists;
            this.context = context;
            this.window = window;
        } catch (Exception e) {
            e.toString();
        }
    }

    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //更新UI
            switch (msg.what)
            {
                case 1:
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    break;
            }
        };
    };
//是用来获取当前项Item(position参数)是哪种类型的布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:         //1.快报新闻
                return new MyViewHolderFastNews(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_fastnew_fragment1, parent, false));
            case 2:         //2.图片（三张横排）新闻

                return new MyViewHolderThreePic(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_threepic_fragment1, parent, false));

            case 3:         //3.图片一张大图新闻
                return new MyViewHolderBigPic(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_bigpic_fragment1, parent, false));

            case 4:         //4.图片一张小图新闻
                return new MyViewHolderOnePic(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_pic_fragment1, parent, false));

            case 5:         //5.Banner图新闻(基于RecycleView,2/3屏)
                return new MyViewHolderHorizontalBanner(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_banner_fragment1, parent, false));

            case 6:         //6.研讨新闻(主要样式)

                return new MyViewHolderYanTao(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_yantao_fragment1, parent, false));

            case 7:         //7.视频新闻
                return new MyViewHolderVideo(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_video_fragment1, parent, false));

            case 8:         //8.研讨新闻2（在好友动态中探讨列表展示样式）
                return new MyViewHolderYanTao2(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_yantao_dynamic, parent, false));

            case 9:         //9.纯文本新闻
            return new MyViewHolderTxt1(LayoutInflater.from(parent.getContext()).inflate(R.layout.caigoulist_shouye, parent, false));

            case 10:        //10.Banner图新闻(基于RecyclerBanner)
                return new MyViewHolderHorizontalBanner2(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_banner_viewpager, parent, false));
            case 11:        //11.Banner图新闻(基于RecycleView,全屏)
                return new MyViewHolderHorizontalBanner3(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_update_banner_fragment1, parent, false));
            case 12:         //12法律法规
                return new MyViewHolderTxt_law(LayoutInflater.from(parent.getContext()).inflate(R.layout.lawlist_shouye, parent, false));
            case 13:         //更多栏
                return new MyViewHolderTxt_more(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_more, parent, false));
            case 14:
                return new MyViewHolderTxt_yzfx(LayoutInflater.from(parent.getContext()).inflate(R.layout.yzfz_layout, parent, false));
            case 15:
                return new MyViewHolderTxt_qyml(LayoutInflater.from(parent.getContext()).inflate(R.layout.yqml1_layout, parent, false));
            case 16:
                return new MyViewHolderTxt_yzfx_table(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yzml_biddingtable, parent, false));
            case 17:
                return new MyViewHolderTxt_qyml_table(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qyml_biddingtable, parent, false));
            case 18:
                return new MyViewHolderTxt_dy(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
            case 19:
                return new MyViewHolderTxt_recyclerView(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recyclerview, parent, false));
            case 20:
                return new MyViewHolderTxt_sm(LayoutInflater.from(parent.getContext()).inflate(R.layout.sm_layout, parent, false));
            case 21:
                return new MyViewHolderTxt_bvip(LayoutInflater.from(parent.getContext()).inflate(R.layout.buyhostor_vip, parent, false));
            case 22:
                return new MyViewHolderTxt_yzfx_table_scape(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_yzml_biddingtable_scpae, parent, false));
            case 23:
                return new MyViewHolderTxt_qyml_table_s(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_qyml_biddingtable_s, parent, false));
            case 24:
                return new MyViewHolderTxt_viewpoint(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_viewpoint_pic, parent, false));
            default:        //图片（三张横排）新闻(默认样式)
                return new MyViewHolderThreePic(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_threepic_fragment1, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = 9;
        try
        {
        NewBean2 newBean2 = lists.get(position);
        LogUtil.i("xoxo", "title:" + newBean2.getTitle() + "-----------Type:" + newBean2.getArtype() + ",imgArray:" + newBean2.getImgArray());
        switch (newBean2.getArtype()) {
            case "TB_y":
                type = 17;
                break;
            case "TB_y_s":
                type = 23;
                break;
            case "SM":
                type = 20;
                break;
            case "CX":
                type = 12;
                break;
            case "VP":
                type = 24;
                break;
            case "ZK":
                type= 24;
            case "CX_dy":
                type = 12;
                break;
            case "TB":
                type = 16;
                break;
            case "TB_S":
                type = 22;
                break;
            case "YZ":
                type = 14;
                break;
            case "QY":
                type = 15;
                break;
            case "CP":
                type = 4;
                break;
            case "P":
                if ("".equals(newBean2.getImg().trim())) {
                    type = 9;
                } else {
                    type = 4;
                }
                break;
            case "S":
                String isVideo = newBean2.getIsVideo();
                if (isVideo != null && "是".equals(isVideo)) {
                    type = 7;
                } else {
                    type = 4;
                }
                break;
            case "T":
                String[] imgArray = newBean2.getImgArray();
                if (imgArray != null) {
                    if (newBean2.getImgArray().length >= 3) {
                        type = 2;
                    } else {
                        type = 3;
                    }
                }

                break;
            case "C":
                if ("".equals(newBean2.getImg().trim())) {
                    type = 9;
                } else {
                    type = 4;
                }
                break;
            case "热点":
                type = 10;

                break;
            case "D":
                type = 6;
            case "L":
                type = 12;
                break;
            case "PP":
                type = 13;
                break;
            case "PM":
                type = 19;
                break;
            case "DY":
                type = 18;
                break;
            case "VIP":
                type = 21;
            case "APPLY":
                type = 21;
                break;
            case "VIP_H":
                    type = 21;
                break;
            case "BUY_D":
                    type = 21;
                break;
        }
        }
        catch (Exception e)
        {
            e.toString();
        }
        return type;
    }

    public int getPlayPosition() {
        return playerPosition;
    }
    boolean allopen = false;

    public void setAllopen(boolean allopen) {
        this.allopen = allopen;

    }
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        loadingDialog = new LoadingDialog_cellect();
        final  ImageView iv;
        final NewBean2 newBean2 = lists.get(position);
        Log.e("onBindViewHolder", newBean2.getTitle()+"" );
        if (holder instanceof MyViewHolderOnePic) {//------------------------------------------------------------------------4.图片一张小图新闻
            MyViewHolderOnePic viewHolder = (MyViewHolderOnePic) holder;
            if ("CP".equals(newBean2.getArtype())) {
                //视频
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", "CP");
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("title", newBean2.getTitle());
                                    context.startActivity(intent);
                    }
                });
                viewHolder.user_name.setText(newBean2.getContent());
                Glide.with(context.getApplicationContext()).load(newBean2.getImg()).centerCrop().into(viewHolder.onePic_pic_fragment1);
                viewHolder.title.setText(newBean2.getTitle());
            }
        /*    else {
                viewHolder.iv_play.setVisibility(View.GONE);
                viewHolder.tv_vTime.setVisibility(View.GONE);

                //普通
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", newBean2.getArtype());
                        intent.putExtra("id", newBean2.getId());
                        context.startActivity(intent);
                    }
                });
            }
            int type = 0;
            if (type == 0) {
                viewHolder.linear_user.setVisibility(View.VISIBLE);
                viewHolder.linear_news.setVisibility(View.GONE);

            } else {
                viewHolder.linear_user.setVisibility(View.GONE);
                viewHolder.linear_news.setVisibility(View.VISIBLE);

            }
            viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopuWindow_dismiss(newBean2.getId(), newBean2.getArtype(), position);
                }
            });
            viewHolder.title.setText(newBean2.getTitle());
            Glide.with(context.getApplicationContext()).load(newBean2.getImg()).centerCrop().into(viewHolder.onePic_pic_fragment1);
            String author = newBean2.getAuthor();
            if (author.length() > 10) {
                author = author.substring(0, 10) + "...";
            }
            viewHolder.newsSource.setText(author);
//            long dismis =System.currentTimeMillis()- TimeUtils.dateToStamp(jca_time);
//            long day=dismis/1000/(24*60*60);
//            if (day>0){
//                viewHolder.newsTime.setText(day+"天前");
//            }else {
//                long hour=dismis/(60*60);
//                if (hour>0){
//                    viewHolder.newsTime.setText(hour+"小时前");
//                }else {
//                    long minite=dismis/60;
//                    if (minite==0){
//                        minite=1;
//                    }
//                    viewHolder.newsTime.setText(minite+"分钟前");
//
//                }
//            }
            viewHolder.newsTime.setText(newBean2.getDateformat());
            viewHolder.user_name.setText(newBean2.getAuthor());
            if (newBean2.getZhiDing() != null && "yes".equals(newBean2.getZhiDing())) {
                viewHolder.tv_zhiDing.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_zhiDing.setVisibility(View.GONE);

            }
            String number = newBean2.getComnum();
            if (number != null && !"".equals(number)) {
                viewHolder.talkCount.setText(number);
            }*/


        }
       else if (holder instanceof MyViewHolderTxt_viewpoint) {//------------------------------------------------------------------------4.图片一张小图新闻
            MyViewHolderTxt_viewpoint viewHolder = (MyViewHolderTxt_viewpoint) holder;
            if ("VP".equals(newBean2.getArtype())) {
                //视频
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", "VP");
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("title", newBean2.getTitle());
                        context.startActivity(intent);
                    }
                });
                viewHolder.source.setText(newBean2.getsource());
                viewHolder.time.setText(newBean2.getVtime());
                if(newBean2.getImg().equals("") || newBean2.getImg() ==null){
                    viewHolder.onepicRel.setVisibility(View.GONE);
                }else {
                    viewHolder.onepicRel.setVisibility(View.VISIBLE);
                }
                Glide.with(context.getApplicationContext()).load(newBean2.getImg()).into(viewHolder.pic);
                viewHolder.title_pic_fragment1.setText(newBean2.getContent());

            }else if("ZK".equals(newBean2.getArtype())){
                //视频
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", "ZK");
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("title", newBean2.getTitle());
                        context.startActivity(intent);
                    }
                });

                viewHolder.source.setText(newBean2.getsource());
                viewHolder.time.setText(newBean2.getVtime());
                Glide.with(context.getApplicationContext()).load(newBean2.getImg()).centerCrop().into(viewHolder.pic);
                viewHolder.title_pic_fragment1.setText(newBean2.getContent());
            }
            }

        else if (holder instanceof MyViewHolderBigPic) {//------------------------------------------------------------------------3.图片一张大图新闻
            MyViewHolderBigPic viewHolder = (MyViewHolderBigPic) holder;
            String type = newBean2.getArtype();
            if ("T".equals(type)) {
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PhotoDetailsActivity.class);
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("type", newBean2.getArtype());
                        context.startActivity(intent);
                    }
                });

                viewHolder.title.setText(newBean2.getTitle());
                String img = newBean2.getImg();
//                if (img!=null&&!"".equals(img)){
//                    Glide.with(context).load(img).into(viewHolder.bigPic);
//                }else {
//                    Glide.with(context).load(newBean2.getImgArray()[0]).into(viewHolder.bigPic);
//
//                }
                Glide.with(context.getApplicationContext()).load(newBean2.getImgArray()[0]).skipMemoryCache(true).centerCrop().into(viewHolder.bigPic);

                String author = newBean2.getAuthor();
                if (author != null) {
                    if (author.length() > 10) {
                        author = author.substring(0, 10) + "...";
                    }
                } else {
                    author = "未知";
                }
                viewHolder.author.setText(author);
                viewHolder.time.setText(newBean2.getDateformat());
                String number = newBean2.getComnum();
                if (number != null && !"".equals(number)) {
                    viewHolder.talkCount.setText(number);
                }


            } else {
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", newBean2.getArtype());
                        intent.putExtra("id", newBean2.getId());
                        context.startActivity(intent);
                    }
                });
            }
            viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  initPopuWindow_dismiss(newBean2.getId(), newBean2.getArtype(), position);
                }
            });

            if (newBean2.getZhiDing() != null && "yes".equals(newBean2.getZhiDing())) {
                viewHolder.tv_zhiDing.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_zhiDing.setVisibility(View.GONE);

            }


        } else if (holder instanceof MyViewHolderThreePic) {//------------------------------------------------------------------------2.图片（三张横排）新闻
            MyViewHolderThreePic viewHolder = (MyViewHolderThreePic) holder;
            String artype = newBean2.getArtype();
            if ("T".equals(artype)) {
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PhotoDetailsActivity.class);
//                        intent.putExtra("msgArray", newBean.getPicMsg());
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("type", newBean2.getArtype());
                        context.startActivity(intent);
                    }
                });
            } else {
                viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsDetailsActivity.class);
                        intent.putExtra("type", newBean2.getArtype());
                        intent.putExtra("id", newBean2.getId());
                        context.startActivity(intent);
                    }
                });
            }
            viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   initPopuWindow_dismiss(newBean2.getId(), newBean2.getArtype(), position);
                }
            });
            String[] imgArray = newBean2.getImgArray();
            int lenth = imgArray.length;
            if (lenth > 3) {
                viewHolder.picNum.setText(lenth + "图");
                viewHolder.picNum.setVisibility(View.VISIBLE);
            } else {
                viewHolder.picNum.setVisibility(View.GONE);
            }
            Glide.with(context.getApplicationContext()).load(newBean2.getImgArray()[0]).centerCrop().into(viewHolder.pic_one);
            Glide.with(context.getApplicationContext()).load(newBean2.getImgArray()[1]).centerCrop().into(viewHolder.pic_two);
            Glide.with(context.getApplicationContext()).load(newBean2.getImgArray()[2]).centerCrop().into(viewHolder.pic_three);

            String author = newBean2.getAuthor();
            if (author != null) {
                if (author.length() > 10) {
                    author = author.substring(0, 10) + "...";
                }
            } else {
                author = "未知";
            }
            viewHolder.author.setText(author);
            viewHolder.time.setText(newBean2.getDateformat());
            viewHolder.title.setText(newBean2.getTitle());
            String number = newBean2.getComnum();
            if (number != null && !"".equals(number)) {
                viewHolder.talkCount.setText(number);
            }
            if (newBean2.getZhiDing() != null && "yes".equals(newBean2.getZhiDing())) {
                viewHolder.tv_zhiDing.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_zhiDing.setVisibility(View.GONE);

            }


        } else if (holder instanceof MyViewHolderFastNews) {//------------------------------------------------------------------------1.快报新闻
            MyViewHolderFastNews viewHolder = (MyViewHolderFastNews) holder;
//            viewHolder.mMarquee1.startWithList(fastNewsList);
            viewHolder.mMarquee1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, TextView textView) {

                }
            });
        } else if (holder instanceof MyViewHolderHorizontalBanner) {//------------------------------------------------------------------------5.Banner图新闻
            MyViewHolderHorizontalBanner viewHolder = (MyViewHolderHorizontalBanner) holder;
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.recyclerView_banner_fragment1.setLayoutManager(mLayoutManager);
            BannerBean bannerBean = newBean2.getBannerBean();
            if (bannerBean != null) {
                List<BannerBean.DataBean> data = bannerBean.getData();
                if (data != null && data.size() > 0) {
                    ArrayList<String> picUrl = new ArrayList<>();
                    ArrayList<String> picName = new ArrayList<>();
                    ArrayList<String> jumpUrl = new ArrayList<>();
                    int lenth = data.size();
                    for (BannerBean.DataBean dataBean : data) {
                        picUrl.add(dataBean.getJal_images());
                        picName.add(dataBean.getJal_desc());
                        jumpUrl.add(dataBean.getJai_url());
                    }
                    viewHolder.recyclerView_banner_fragment1.setAdapter(new BannerAdapter(picUrl, picName, jumpUrl, context));
                }
            }


        }
        else if (holder instanceof MyViewHolderTxt_recyclerView) {//------------------------------------------------------------------------5.Banner图新闻
          final MyViewHolderTxt_recyclerView viewHolder = (MyViewHolderTxt_recyclerView) holder;

           // lists = new ArrayList<>();
            lists1 = new ArrayList<>();
            adapter = new NewsOneAdapter(lists1, context, window);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext());
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            viewHolder.recyclerView.setLayoutManager(mLayoutManager);
            viewHolder.recyclerView.setAdapter(adapter);
            //允许加载更多
            viewHolder.refreshView.setPullLoadEnable(true);
            //允许下拉刷新
            viewHolder.refreshView.setPullRefreshEnable(true);
            //滑动到底部自动加载更多
            viewHolder.refreshView.setAutoLoadMore(false);

            //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
            // recyclerView_top.setVisibility(View.VISIBLE);
            pageNum = 1;
     /*   if (loadingDialog != null) {
            loadingDialog.showDialog(getActivity(), "");
        }*/
            if (NetWorkUtils.isNetworkConnected(context)) {
                if("全国".equals((String) SharedPreferenceUtils.get(context, "city_loction", "")))
                { city_Serch="";
                all_city="";}
                else
                {  city_Serch=(String) SharedPreferenceUtils.get(context, "city_loction", "");
                    all_city="";}
                Call<caigoulist> call = MyApplication.getNetApi().getcaigou((String) SharedPreferenceUtils.get(context, "app_token", ""),pageNum+"","20","","",city_Serch,all_city,"","sort");
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<caigoulist>() {
                    @Override
                    public void onResponse(Call<caigoulist> call, Response<caigoulist> response) {
                        if (response.isSuccessful()) {
                            caigoulist body = response.body();
                            //  caigoulist body = response.body();

                            if (body != null) {
                                List<caigoulist.DataBean> list = body.getData();

                                if (list != null && list.size() > 0) {
                                    LogUtil.i("yantao", "length:" + list.size());
                                    lists1.clear();
                             /*   NewBean2 newBean1 = new NewBean2();
                                newBean1.setArtype("PP");
                                lists.add(newBean1);*/
                                    for (caigoulist.DataBean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("P");
                                        newBean.setAuthor(listBean.getprovince());
                                        // newBean.setComnum("");
                                        newBean.setIsCollect(listBean.getisCollection());
                                        //  newBean.setClickNum("");
                                        newBean.setContent(listBean.getcontent());
                                        //  newBean.setDateformat("");
                                        newBean.setId(listBean.getid());
                                        newBean.setLable(listBean.gettype());
                                        newBean.setImg("");
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setIsVideo("否");
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        newBean.setSort(listBean.getsource());
                                        lists1.add(newBean);
                                    }
                                    adapter.notifyDataSetChanged();
                                    viewHolder.iv_empty.setVisibility(View.GONE);
                                    viewHolder.tv_txt_no.setVisibility(View.GONE);

                                } else {
                                    LogUtil.i("yantao", "else");
                                    viewHolder.iv_empty.setVisibility(View.VISIBLE);
                                    viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                                    lists1.clear();
                                    NewBean2 newBean1 = new NewBean2();
                                    newBean1.setArtype("PP");
                                    lists1.add(newBean1);
                                    adapter.notifyDataSetChanged();
                                }

                            } else {
                                viewHolder.iv_empty.setVisibility(View.VISIBLE);
                                viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                                lists1.clear();
                                NewBean2 newBean1 = new NewBean2();
                                newBean1.setArtype("PP");
                                lists1.add(newBean1);
                                adapter.notifyDataSetChanged();
                                //  httpData();
                            }

                        } else {
                            viewHolder.iv_empty.setVisibility(View.VISIBLE);
                            viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                            lists1.clear();
                            NewBean2 newBean1 = new NewBean2();
                            newBean1.setArtype("PP");
                            lists1.add(newBean1);
                            adapter.notifyDataSetChanged();
                            // httpData();
                        }
                        viewHolder.refreshView.stopRefresh();
                        LogUtil.i("yantao", "Gone--1");
                        viewHolder.iv_repeat.setVisibility(View.GONE);
                        viewHolder.iv_repeat.setClickable(false);
                   /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<caigoulist> call, Throwable t) {
                        call.cancel();
                        LogUtil.i("yantao", "onFailure");
                        viewHolder.refreshView.stopRefresh();
                        LogUtil.i("yantao", "Gone--2");
                        viewHolder.iv_repeat.setVisibility(View.GONE);
                        viewHolder.iv_repeat.setClickable(false);
                    /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                    }
                });


            } else {
          /*  NewBean2 newBean1 = new NewBean2();
            newBean1.setArtype("PP");
            lists.add(newBean1);*/
                viewHolder.iv_repeat.setVisibility(View.VISIBLE);
                ToastUtils.showMessage(context, "当前网络不可用");
                viewHolder.iv_repeat.setClickable(true);
                viewHolder.refreshView.stopRefresh();
            /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
            }
            viewHolder.refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {
                    //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                    // recyclerView_top.setVisibility(View.VISIBLE);
                    pageNum = 1;
     /*   if (loadingDialog != null) {
            loadingDialog.showDialog(getActivity(), "");
        }*/
                    if (NetWorkUtils.isNetworkConnected(context)) {
                        if("全国".equals((String) SharedPreferenceUtils.get(context, "city_loction", "")))
                        {    city_Serch="";
                        all_city="";}
                        else
                        {
                            city_Serch=(String) SharedPreferenceUtils.get(context, "city_loction", "");
                            all_city="";
                        }
                        Call<caigoulist> call = MyApplication.getNetApi().getcaigou((String) SharedPreferenceUtils.get(context, "app_token", ""),pageNum+"","20","","",city_Serch,all_city,"","sort");
                        // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                        call.enqueue(new Callback<caigoulist>() {
                            @Override
                            public void onResponse(Call<caigoulist> call, Response<caigoulist> response) {
                                if (response.isSuccessful()) {
                                    caigoulist body = response.body();
                                    //  caigoulist body = response.body();

                                    if (body != null) {
                                        List<caigoulist.DataBean> list = body.getData();

                                        if (list != null && list.size() > 0) {
                                            LogUtil.i("yantao", "length:" + list.size());
                                            lists1.clear();
                             /*   NewBean2 newBean1 = new NewBean2();
                                newBean1.setArtype("PP");
                                lists.add(newBean1);*/
                                            for (caigoulist.DataBean listBean : list) {
                                                NewBean2 newBean = new NewBean2();
                                                newBean.setArtype("P");
                                                newBean.setAuthor(listBean.getprovince());
                                                // newBean.setComnum("");
                                                newBean.setIsCollect(listBean.getisCollection());
                                                //  newBean.setClickNum("");
                                                newBean.setContent(listBean.getcontent());
                                                //  newBean.setDateformat("");
                                                newBean.setId(listBean.getid());
                                                newBean.setLable(listBean.gettype());
                                                newBean.setImg("");
                                                newBean.setTitle(listBean.gettitle());
                                                newBean.setIsVideo("否");
                                                newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                                newBean.setSort(listBean.getsource());
                                                lists1.add(newBean);
                                            }
                                            adapter.notifyDataSetChanged();
                                            viewHolder.iv_empty.setVisibility(View.GONE);
                                            viewHolder.tv_txt_no.setVisibility(View.GONE);

                                        } else {
                                            LogUtil.i("yantao", "else");
                                            viewHolder.iv_empty.setVisibility(View.VISIBLE);
                                            viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                                            lists1.clear();
                                            NewBean2 newBean1 = new NewBean2();
                                            newBean1.setArtype("PP");
                                            lists1.add(newBean1);
                                            adapter.notifyDataSetChanged();
                                        }

                                    } else {
                                        viewHolder.iv_empty.setVisibility(View.VISIBLE);
                                        viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                                        lists1.clear();
                                        NewBean2 newBean1 = new NewBean2();
                                        newBean1.setArtype("PP");
                                        lists1.add(newBean1);
                                        adapter.notifyDataSetChanged();
                                        //  httpData();
                                    }

                                } else {
                                    viewHolder.iv_empty.setVisibility(View.VISIBLE);
                                    viewHolder.tv_txt_no.setVisibility(View.VISIBLE);
                                    lists1.clear();
                                    NewBean2 newBean1 = new NewBean2();
                                    newBean1.setArtype("PP");
                                    lists1.add(newBean1);
                                    adapter.notifyDataSetChanged();
                                    // httpData();
                                }
                                viewHolder.refreshView.stopRefresh();
                                LogUtil.i("yantao", "Gone--1");
                                viewHolder.iv_repeat.setVisibility(View.GONE);
                                viewHolder.iv_repeat.setClickable(false);
                   /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<caigoulist> call, Throwable t) {
                                call.cancel();
                                LogUtil.i("yantao", "onFailure");
                                viewHolder.refreshView.stopRefresh();
                                LogUtil.i("yantao", "Gone--2");
                                viewHolder.iv_repeat.setVisibility(View.GONE);
                                viewHolder.iv_repeat.setClickable(false);
                    /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                            }
                        });


                    } else {
          /*  NewBean2 newBean1 = new NewBean2();
            newBean1.setArtype("PP");
            lists.add(newBean1);*/
                        viewHolder.iv_repeat.setVisibility(View.VISIBLE);
                        ToastUtils.showMessage(context, "当前网络不可用");
                        viewHolder.iv_repeat.setClickable(true);
                        viewHolder.refreshView.stopRefresh();
            /* if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                    }

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    //上拉加载
                    //   recyclerView_top.setVisibility(View.GONE);
                    // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
                    pageNum++;
                    if("全国".equals((String) SharedPreferenceUtils.get(context, "city_loction", "")))
                    {      city_Serch="";
                    all_city="";}
                    else {
                        city_Serch = (String) SharedPreferenceUtils.get(context, "city_loction", "");
                        all_city="";
                    }
                    Call<caigoulist> call = MyApplication.getNetApi().getcaigou((String) SharedPreferenceUtils.get(context, "app_token", ""),pageNum+"","20","","",city_Serch,all_city,"","sort");

                    //  Call<caigoulist> call = MyApplication.getNetApi().getcaigou("",pageNum+"","10");
                    call.enqueue(new Callback<caigoulist>() {
                        @Override
                        public void onResponse(Call<caigoulist> call, Response<caigoulist> response) {
                            if (response.isSuccessful()) {
                                caigoulist body = response.body();
                                List<caigoulist.DataBean> list = body.getData();
                                if (list != null && list.size() > 0) {
                                    LogUtil.i("yantao", "length:" + list.size());
                                    //     lists.clear();
                                    for (caigoulist.DataBean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("P");
                                        newBean.setAuthor(listBean.getprovince());
                                        // newBean.setComnum("");
                                        newBean.setIsCollect(listBean.getisCollection());
                                        //  newBean.setClickNum("");
                                        newBean.setContent(listBean.getcontent());
                                        //  newBean.setDateformat("");
                                        newBean.setId(listBean.getid());
                                        newBean.setLable(listBean.gettype());
                                        newBean.setImg("");
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setIsVideo("否");
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        newBean.setSort(listBean.getsource());
                                        lists1.add(newBean);
                                    }
                                    adapter.notifyDataSetChanged();
                                    viewHolder.iv_empty.setVisibility(View.GONE);


                                } else {
                                    ToastUtils.showMessage(context, "已加载到底部");
                                    pageNum--;
                                }

                            }
                            viewHolder.refreshView.stopLoadMore();
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<caigoulist> call, Throwable t) {
                            call.cancel();
                            LogUtil.i("yantao", "onFailure");
                            viewHolder.refreshView.stopLoadMore();


                        }
                    });

                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });


        }
        else if (holder instanceof MyViewHolderVideo) {//------------------------------------------------------------------------7.视频新闻
            MyViewHolderVideo viewHolder = (MyViewHolderVideo) holder;
            boolean setUp = viewHolder.videoPlayer.setUp(newBean2.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, newBean2.getTitle());
            if (setUp) {
                Glide.with(context.getApplicationContext()).load(newBean2.getImg()).skipMemoryCache(true).into(viewHolder.videoPlayer.thumbImageView);
            }
            viewHolder.linear_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VideoDetailsActivity.class);
                    intent.putExtra("type", newBean2.getArtype());
                    intent.putExtra("id", newBean2.getId());
                    context.startActivity(intent);
                }
            });
            viewHolder.tv_talkNum.setText(newBean2.getComnum());
            String author = newBean2.getAuthor();
            if (author != null) {
                if (author.length() > 10) {
                    author = author.substring(0, 10) + "...";
                }
            } else {
                author = "未知";
            }
            viewHolder.tv_userName.setText(author);
            viewHolder.relative_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Glide.with(context.getApplicationContext()).load(newBean2.getImg()).asBitmap().skipMemoryCache(true).into(new SimpleTarget<Bitmap>(80, 80) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                                    {
                                            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                            SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                                    };
                            UMImage image = new UMImage(context.getApplicationContext(), resource);
                            UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + newBean2.getArtype() + "/" + newBean2.getId());
                            web.setTitle(newBean2.getTitle());//标题
                            web.setThumb(image);  //缩略图
                            web.setDescription(newBean2.getDes());//描述
                            new ShareAction((Activity) context)
                                    .withMedia(web)
                                    .setDisplayList(displaylist)
                                    .setCallback(umShareListener)
                                    .open();
                        }
                    });
                }
            });
            viewHolder.tv_vTime.setText(newBean2.getVtime());

        } else if (holder instanceof MyViewHolderYanTao) {//------------------------------------------------------------------------6.研讨新闻(主要样式)

            MyViewHolderYanTao viewHolder = (MyViewHolderYanTao) holder;
            viewHolder.relative_jump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, YanTaoActivity.class);
                    intent.putExtra("jc_id", newBean2.getId() + "");
                    intent.putExtra("img_id",backgroundArray[position%12]);
                    context.startActivity(intent);
                }
            });
            viewHolder.title_tanTao.setText(newBean2.getTitle());

            String clickNum = newBean2.getClickNum();
            if (clickNum == null || "".equals(clickNum) || "null".equals(clickNum)) {
                clickNum = "0";
            }
            viewHolder.tv_zanCount.setText(clickNum);
            String comnum = newBean2.getComnum();
            if (comnum == null || "".equals(comnum) || "null".equals(comnum)) {
                comnum = "0";
            }
            viewHolder.tv_optionCount.setText(comnum);
            viewHolder.iv_yanTao.setBackgroundResource(backgroundArray[position%12]);
        } else if (holder instanceof MyViewHolderYanTao2) {//-------------------------------------------------------------8.研讨新闻2（在好友动态中探讨列表展示样式）
            MyViewHolderYanTao2 viewHolder = (MyViewHolderYanTao2) holder;
            viewHolder.relative_jump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, YanTaoActivity.class);
                    LogUtil.i("sd", "id:" + newBean2.getId());
                    intent.putExtra("jc_id", newBean2.getId() + "");
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof MyViewHolderTxt1) {//------------------------------------------------------------------------9.纯文本新闻
            final MyViewHolderTxt1 viewHolder = (MyViewHolderTxt1) holder;
            viewHolder.title.setText(newBean2.getTitle());
            iv = viewHolder.dismiss;
            //author
                String author = newBean2.getLable();
                if ("".equals(newBean2.gethistory_time()) || (newBean2.gethistory_time() == null))
                    viewHolder.line_history_top.setVisibility(View.GONE);
                else {
                    viewHolder.line_history_top.setVisibility(View.VISIBLE);
                    viewHolder.line_history_count.setText("" + newBean2.gethistory_count() + "");
                    viewHolder.line_history_time.setText(newBean2.gethistory_time());
                }
                if(newBean2.getgenre()==null){
                    newBean2.setgenre("");
                }
                if(newBean2.getgenre().equals("viewpointInfo")){
                    viewHolder.newsSource.setText(newBean2.getVtime());
                    viewHolder.newsSource.setTextColor(getResources().getColor(R.color.default_text_night_day));
                    viewHolder.newsSource.setBackgroundColor(Color.WHITE);

                    viewHolder.news_source.setVisibility(View.GONE);
                    viewHolder.creedtime.setVisibility(View.GONE);
                    viewHolder.newsTime.setVisibility(View.GONE);
                    viewHolder.newsSource.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.newsSource.setTextColor(getResources().getColor(R.color.text_blue));
                    viewHolder.newsSource.setBackgroundColor(getResources().getColor(R.color.ggreen));
                    viewHolder.creedtime.setVisibility(View.VISIBLE);
                    viewHolder.news_source.setVisibility(View.VISIBLE);
                    viewHolder.newsTime.setVisibility(View.VISIBLE);
                    viewHolder.newsSource.setVisibility(View.VISIBLE);
                    Log.e("p", "onBindViewHolder: "+newBean2.getSort() );
                    if ("中国政府采购网".equals(newBean2.getSort())) {
                        viewHolder.news_source.setText("政府采购网");
                    } else if ("全军武器装备采购网".equals(newBean2.getSort())) {
                        viewHolder.news_source.setText("装备采购网");
                    } else if ("中国人民解放军总医院后勤招标采购中心".equals(newBean2.getSort())) {
                        viewHolder.news_source.setText("总医院采购网");
                    } else if ("武警物资采购网".equals(newBean2.getSort())) {
                        viewHolder.news_source.setText("武警采购网");
                    }else if("机电产品招标投标电子交易平台".equals(newBean2.getSort())){
                        viewHolder.news_source.setText("机电招标平台");
                    } else {
                        viewHolder.news_source.setText(newBean2.getSort());
                    }
                    viewHolder.newsSource.setText(newBean2.getAuthor());
                    viewHolder.newsTime.setText(newBean2.getLable());
                    if ("公开招标公告".equals(newBean2.getLable())) {
                        viewHolder.newsTime.setTextColor(getResources().getColor(R.color.text_regular));
                        viewHolder.newsTime.setBackgroundResource(R.drawable.shape_regular);
                    } else {
                        viewHolder.newsTime.setTextColor(getResources().getColor(R.color.text_blue));
                        viewHolder.newsTime.setBackgroundResource(R.drawable.shape_newdetail_fankui_none);
                    }
                }

                viewHolder.creedtime.setText(newBean2.getVtime());
                if (newBean2.getIsCollect())
                    viewHolder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
                else
                    viewHolder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
                viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // if(!"false".equals(newBean2.getread_collect()+""))
                        //   {
                        if ("true".equals(SharedPreferenceUtils.get(context, "vistor", ""))) {
                            if (v == iv) {
                                if (!newBean2.getIsCollect()) {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(context, "app_token", ""), newBean2.getgenre(), newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                //  ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {
                                                    loadingDialog.showDialog(context, "");
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                                Message message = new Message();
                                                                message.what = 1;
                                                                mHandler.sendMessage(message);
                                                            } catch (Exception e) {
                                                                LogUtil.i("start:", e.toString());
                                                            }

                                                        }
                                                    }).start();
                                                    iv.setImageResource(R.mipmap.yantao_college_yes);
                                                    newBean2.setIsCollect(true);

                                                }else{
                                                    ToastUtils.showMessage(context,"收藏失败");
                                                }
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                                            call.cancel();
                                        }
                                    });
                                } else {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""),newBean2.getgenre(), newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                if ("200".equals(response.body().getStatus())) {
                                                    iv.setImageResource(R.mipmap.icon_subscription_default3x);
                                                    ToastUtils.showMessage(context, "成功取消收藏！");
                                                    newBean2.setIsCollect(false);
                                                    colletlistnear.collet();
                                                }else{
                                                    ToastUtils.showMessage(context,"取消收藏失败！");
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
                        } else
                            ToastUtils.showMessage(context, "登录后才能收藏");
                    }
                });

           viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferenceUtils.remove(context,"ClickPosition");
                    SharedPreferenceUtils.remove(context,"ClickCollege");
                    SharedPreferenceUtils.put(context,"ClickPosition",position);
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    if(newBean2.getgenre().equals("viewpointInfo")){
                        intent.putExtra("type", "VP");
                        intent.putExtra("id", newBean2.getId());
                        if (newBean2.getIsCollect())
                            intent.putExtra("idcollect", "true");
                        else
                            intent.putExtra("idcollect", "false");
                        LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                    }else {
                        intent.putExtra("type", "P");
                        intent.putExtra("id", newBean2.getId());
                        if (newBean2.getIsCollect())
                            intent.putExtra("idcollect", "true");
                        else
                            intent.putExtra("idcollect", "false");
                        LogUtil.i("laoshu", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId());
                    }
                    context.startActivity(intent);
                }
            });
            String number = newBean2.getComnum();
            if (number != null && !"".equals(number)) {
                viewHolder.talkCount.setText(number);
            }
          //  viewHolder.newsTime.setText(newBean2.getDateformat());
       /*     if (newBean2.getZhiDing() != null && "yes".equals(newBean2.getZhiDing())) {
                viewHolder.tv_zhiDing.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_zhiDing.setVisibility(View.GONE);

            }*/

        }
       else if (holder instanceof MyViewHolderTxt_yzfx) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_yzfx viewHolder = (MyViewHolderTxt_yzfx) holder;
            viewHolder.title.setText(newBean2.getTitle());
            viewHolder.today.setText(newBean2.getLable());
            viewHolder.time.setText(newBean2.getTime());
            viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", newBean2.getArtype());
                    intent.putExtra("id", newBean2.getId());
                    intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });}
        else if (holder instanceof MyViewHolderTxt_qyml) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_qyml viewHolder = (MyViewHolderTxt_qyml) holder;
            viewHolder.title.setText(newBean2.getTitle());
            viewHolder.content.setText(newBean2.getContent());
            viewHolder.time.setText(newBean2.getTime());
            viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", newBean2.getArtype());
                    intent.putExtra("id", newBean2.getId());
                    intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
        }
                else if (holder instanceof MyViewHolderTxt_yzfx_table_scape) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_yzfx_table_scape viewHolder = (MyViewHolderTxt_yzfx_table_scape) holder;
            viewHolder.table_project.setText(newBean2.getTitle());
            viewHolder.table_money.setText(newBean2.getZhiDing());
            viewHolder.table_bidding.setText(newBean2.getLable());
            viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getId());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
        }
        else if (holder instanceof MyViewHolderTxt_yzfx_table) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_yzfx_table viewHolder = (MyViewHolderTxt_yzfx_table) holder;
            viewHolder.table_project.setText(newBean2.getTitle());
            viewHolder.table_money.setText(newBean2.getZhiDing());
            viewHolder.table_bidding.setText(newBean2.getLable());
            Log.e("table", "onBindViewHolder: "+newBean2.getTitle()+"---"+newBean2.getZhiDing()+"---"+newBean2.getLable() );
            Log.e("ta", "onBindViewHolder: "+newBean2.toString());
            viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getId());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
        }
        else if (holder instanceof MyViewHolderTxt_qyml_table) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_qyml_table viewHolder = (MyViewHolderTxt_qyml_table) holder;
            viewHolder.table_project.setText(newBean2.getTitle());
            //viewHolder.table_money.setText(newBean2.getmoney());
            viewHolder.table_result.setText(newBean2.getcompany());
            viewHolder.table_yz.setText(newBean2.getyz());
            viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getproject_id());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
            viewHolder.table_result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "QY");
                    intent.putExtra("id", newBean2.getcompany_id());
                    intent.putExtra("title", newBean2.getcompany());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
            viewHolder.table_yz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "YZ");
                    intent.putExtra("id", newBean2.getyzid());
                    intent.putExtra("title", newBean2.getyz());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
         /*   viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getId());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });*/
        }
        else if (holder instanceof MyViewHolderTxt_qyml_table_s) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_qyml_table_s viewHolder = (MyViewHolderTxt_qyml_table_s) holder;
            viewHolder.table_project.setText(newBean2.getyz());
           // viewHolder.table_money.setText(newBean2.getmoney());
            viewHolder.table_result.setText(newBean2.getcompany());
            viewHolder.table_yz.setText(newBean2.getTitle());
            viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getproject_id());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
            viewHolder.table_result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "QY");
                    intent.putExtra("id", newBean2.getcompany_id());
                    intent.putExtra("title", newBean2.getcompany());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
            viewHolder.table_yz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "YZ");
                    intent.putExtra("id", newBean2.getyzid());
                    intent.putExtra("title", newBean2.getyz());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
         /*   viewHolder.table_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", "P");
                    intent.putExtra("id", newBean2.getId());
                    //intent.putExtra("title", newBean2.getTitle());
                    (context).startActivity(intent);
                    //     ((MainActivity)context).startActivityForResult(intent,1024);
                    //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });*/
        }
        else if (holder instanceof MyViewHolderTxt_dy) {//------------------------------------------------------------------------9.纯文本新闻
           final MyViewHolderTxt_dy viewHolder = (MyViewHolderTxt_dy) holder;
            viewHolder.slidingButtonView.setSlidingButtonListener(this);

            viewHolder.layout_content.getLayoutParams().width = Utils.getScreenWidth(context);
            if("".equals(newBean2.getTime()))
                viewHolder.textreadtime.setText(newBean2.getTime()+"更新");
            else
            {
                viewHolder.textreadtime.setText(newBean2.getTime().split("-")[0]+"年"+newBean2.getTime().split("-")[1]+"月"+newBean2.getTime().split("-")[2]+"日"+" 更新");
            }
            if("purchaseInfo".equals(newBean2.getgenre()))
            {
                viewHolder.dj_line.setVisibility(View.GONE);
                viewHolder.souce_line.setVisibility(View.VISIBLE);
                viewHolder.type_line.setVisibility(View.VISIBLE);
                viewHolder.dq_line.setVisibility(View.VISIBLE);
                viewHolder.key_line.setVisibility(View.VISIBLE);
            }
           else if("purchaseDemand".equals(newBean2.getgenre()))
            {
                viewHolder.dj_line.setVisibility(View.VISIBLE);
                viewHolder.souce_line.setVisibility(View.GONE);
                viewHolder.type_line.setVisibility(View.GONE);
                viewHolder.dq_line.setVisibility(View.GONE);
                viewHolder.key_line.setVisibility(View.VISIBLE);
            }
           else if("purchaseSecret".equals(newBean2.getgenre()))
            {
                viewHolder.souce_line.setVisibility(View.GONE);
                viewHolder.type_line.setVisibility(View.GONE);
                viewHolder.dq_line.setVisibility(View.GONE);
                viewHolder.key_line.setVisibility(View.VISIBLE);
                viewHolder.dj_line.setVisibility(View.GONE);
            }

           // viewHolder.text_hd.setLabelText(newBean2.getupdateNum());
            viewHolder.text_hd.setText("对接"+newBean2.getupdateNum()+"条");
            viewHolder.mine_dy.setText(newBean2.getTitle());
            if((newBean2.getAuthor()+"").equals("")|(newBean2.getAuthor()+"").equals("null"))
                viewHolder.sheng.setText("全国");
                else
            viewHolder.sheng.setText(newBean2.getAuthor());
            viewHolder.type.setText(newBean2.getContent());
            viewHolder.souce.setText(newBean2.getsource());

            viewHolder.layout_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在设置Item的监听时
                        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME){
                            return;
                        }
                        lastClickTime = System.currentTimeMillis();
                        if ("purchaseInfo".equals(newBean2.getgenre())) {
                            Call<DefaultBean2> call_add = MyApplication.getNetApi().read_dy(newBean2.getId(), (String) SharedPreferenceUtils.get(context, "app_token", ""));
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            //     viewHolder.text_hd.setLabelText("0");
                                            Intent intent = new Intent(context, bidding_class_activity.class);
                                            intent.putExtra("keyword", newBean2.getLable());
                                            intent.putExtra("souces", newBean2.getsource());
                                            if (newBean2.getaddress().equals("全国"))
                                                intent.putExtra("address", "");
                                            else
                                                intent.putExtra("address", newBean2.getaddress());
                                            // intent.putExtra("province", newBean2.getprovince());
                                            intent.putExtra("type", newBean2.getContent());
                                            intent.putExtra("title", newBean2.getTitle());
                                            intent.putExtra("id", newBean2.getId());
                                            intent.putExtra("genre", newBean2.getgenre());
                                            (context).startActivity(intent);
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(context, t.toString());

                                }
                            });


                        } else if ("purchaseDemand".equals(newBean2.getgenre())) {
                            Call<DefaultBean2> call_add = MyApplication.getNetApi().read_dy(newBean2.getId(), (String) SharedPreferenceUtils.get(context, "app_token", ""));
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            //viewHolder.text_hd.setLabelText("0");

                                            Intent intent = new Intent(context, CG_dy_Activity.class);
                                            intent.putExtra("keyword", newBean2.getLable());
                                            intent.putExtra("isfinish", "true");
                                            intent.putExtra("title", newBean2.getTitle());
                                            intent.putExtra("id", newBean2.getId());

                                            (context).startActivity(intent);
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(context, t.toString());

                                }
                            });
                        } else if ("purchaseSecret".equals(newBean2.getgenre())) {
                            Call<DefaultBean2> call_add = MyApplication.getNetApi().read_dy(newBean2.getId(), (String) SharedPreferenceUtils.get(context, "app_token", ""));
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            Intent intent = new Intent(context, ClassifiedActivity1.class);
                                            //   viewHolder.text_hd.setLabelText("0");
                                            intent.putExtra("title", newBean2.getTitle());
                                            intent.putExtra("keyword", newBean2.getLable());
                                            intent.putExtra("keyword", newBean2.getLable());
                                            intent.putExtra("id", newBean2.getId());
                                            (context).startActivity(intent);
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(context, t.toString());

                                }
                            });

                        }
                }

            });
          //  viewHolder.slidingButtonView.setCanTouch(true);
            if (allopen) {
                viewHolder.slidingButtonView.openMenu();
               // viewHolder.slidingButtonView.setCanTouch(false);
            } else {
                viewHolder.slidingButtonView.closeMenu();
              //  viewHolder.slidingButtonView.setCanTouch(true);
            }
        /*           viewHolder.layout_content.setLongClickable(true);
            viewHolder.layout_content.setOnTouchListener(new GestureListener(context){


                @Override
                public boolean left() {

                  // if (allopen) {
                  ///      viewHolder.slidingButtonView.openMenu();
                  //      viewHolder.slidingButtonView.setCanTouch(false);
                  //  } else {
                    //    viewHolder.slidingButtonView.closeMenu();
                    //   viewHolder.slidingButtonView.setCanTouch(true);
                //  }
                    return super.left();
                }
                public boolean right() {
                   // viewHolder.slidingButtonView.closeMenu();
                    //viewHolder.slidingButtonView.setCanTouch(true);
                    setAllopen(false);
                    notifyDataSetChanged();
                    return super.right();
                }
         });*/

            if(!newBean2.getLable().contains(","))
            {
                viewHolder.keyword_dy4.setVisibility(View.GONE);
                viewHolder.keyword_dy5.setVisibility(View.GONE);
                viewHolder.keyword_dy6.setVisibility(View.GONE);
             //   TextPaint  mPaint=new TextPaint();
       /*         TextPaint newPaint = new TextPaint();
                float textSize1 = getResources().getDisplayMetrics().scaledDensity * 16;
                newPaint.setTextSize(textSize1);
                float newPaintWidth1 = newPaint.measureText(newBean2.getLable());
              //  ScreenUtils px2dp=new ScreenUtils();
                Rect bounds = new Rect();
                TextPaint paint;
                paint = viewHolder.keyword_dy1.getPaint();
                paint.getTextBounds(newBean2.getLable(), 0, newBean2.getLable().length(), bounds);
                int width = bounds.width();
                int width1 =  ViewGroup.LayoutParams.WRAP_CONTENT;
                final float scale = context.getResources().getDisplayMetrics().density;
               //  return (int) (pxValue / scale + 0.5f);*/
              /*  viewHolder.keyword_dy1.setText(newBean2.getLable());
                viewHolder.keyword_dy2.setText("");
                viewHolder.keyword_dy3.setText("");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
              //  layoutParams.(10, 0, 0, 0);//4个参数按顺序分别是左上右下
                viewHolder.keyword_dy1.setLayoutParams(layoutParams);
                viewHolder.keyword_dy2.setLayoutParams(layoutParams);
                viewHolder.keyword_dy3.setLayoutParams(layoutParams);*/
              //  viewHolder.keyword_dy1.setWidth((int)mPaint.measureText(newBean2.getLable()));
             //   float textSize1 = getResources().getDisplayMetrics().scaledDensity * 16;
             //   mPaint.setTextSize(textSize1);
           //   viewHolder.keyword_dy1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

              //  viewHolder.keyword_dy1.setWidth((int)mPaint.measureText(newBean2.getLable()));
                viewHolder.keyword_dy1.setText(newBean2.getLable());
               // if(viewHolder.keyword_dy1.getWidth()>(viewHolder.key_line.getWidth()-viewHolder.key_lable.getWidth()))
                 //   viewHolder.keyword_dy1.setWidth(viewHolder.key_line.getWidth()-viewHolder.key_lable.getWidth());
                viewHolder.keyword_dy1.setVisibility(View.VISIBLE);
                viewHolder.keyword_dy3.setVisibility(View.GONE);
                viewHolder.keyword_dy2.setVisibility(View.GONE);
            }
            else
            {
           if( newBean2.getLable().split(",").length==2)
           {
               viewHolder.keyword_dy4.setVisibility(View.GONE);
               viewHolder.keyword_dy5.setVisibility(View.GONE);
               viewHolder.keyword_dy6.setVisibility(View.GONE);
            //   LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
               //  layoutParams.(10, 0, 0, 0);//4个参数按顺序分别是左上右下
           //    viewHolder.keyword_dy1.setLayoutParams(layoutParams);
            //   viewHolder.keyword_dy2.setLayoutParams(layoutParams);
           //    viewHolder.keyword_dy3.setLayoutParams(layoutParams);
             //  viewHolder.keyword_dy1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            //   viewHolder.keyword_dy2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
               viewHolder.keyword_dy1.setVisibility(View.VISIBLE);
                viewHolder.keyword_dy1.setText(newBean2.getLable().split(",")[0]);
               viewHolder.keyword_dy2.setVisibility(View.VISIBLE);
               viewHolder.keyword_dy3.setVisibility(View.GONE);
            viewHolder.keyword_dy2.setText(newBean2.getLable().split(",")[1]);

           }
            else
           {
               DisplayMetrics dm = new DisplayMetrics();
               window.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小
               int screen_width = dm.widthPixels;
               TextPaint newPaint = new TextPaint();
               float textSize = getResources().getDisplayMetrics().scaledDensity * 13;
               newPaint.setTextSize(textSize);
               TextPaint newPaint1 = new TextPaint();
               float textSize1 = getResources().getDisplayMetrics().scaledDensity * 16;
               newPaint.setTextSize(textSize1);
               float newPaintWidth =screen_width-(newPaint.measureText("关键字")+context.getResources().getDisplayMetrics().density*37+0.5f);
               float newPaintWidth1 = newPaint.measureText(newBean2.getLable().split(",")[0]);
               float newPaintWidth2 = newPaint.measureText(newBean2.getLable().split(",")[1]);
               float newPaintWidth3 = newPaint.measureText(newBean2.getLable().split(",")[2]);

              if((newPaintWidth1+newPaintWidth2+newPaintWidth3)>newPaintWidth) {
                  viewHolder.keyword_dy1.setVisibility(View.GONE);
                  viewHolder.keyword_dy2.setVisibility(View.GONE);
                  viewHolder.keyword_dy3.setVisibility(View.GONE);
                  viewHolder.keyword_dy4.setVisibility(View.VISIBLE);
                  //    ((TextView)viewHolder.keyword_dy1).setW
                  viewHolder.keyword_dy4.setText(newBean2.getLable().split(",")[0]);
                  viewHolder.keyword_dy5.setVisibility(View.VISIBLE);
                  viewHolder.keyword_dy5.setText(newBean2.getLable().split(",")[1]);
                  viewHolder.keyword_dy6.setVisibility(View.VISIBLE);
                  viewHolder.keyword_dy6.setText(newBean2.getLable().split(",")[2]);
            //       viewHolder.keyword_dy1.setWidth((int)newPaintWidth/ 3);
          //         viewHolder.keyword_dy2.setWidth((int)newPaintWidth/ 3);
          //         viewHolder.keyword_dy3 .setWidth((int)newPaintWidth/ 3);
               //   LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                 //  layoutParams.weight=1;
                //  layoutParams.setMarginStart((int)(5*context.getResources().getDisplayMetrics().density));
                //  viewHolder.keyword_dy3.setLayoutParams(layoutParams);
               }
               else
               {
                   viewHolder.keyword_dy4.setVisibility(View.GONE);
                   viewHolder.keyword_dy5.setVisibility(View.GONE);
                   viewHolder.keyword_dy6.setVisibility(View.GONE);
                   viewHolder.keyword_dy1.setVisibility(View.VISIBLE);
                   //    ((TextView)viewHolder.keyword_dy1).setW
                   viewHolder.keyword_dy1.setText(newBean2.getLable().split(",")[0]);
                   viewHolder.keyword_dy2.setVisibility(View.VISIBLE);
                   viewHolder.keyword_dy2.setText(newBean2.getLable().split(",")[1]);
                   viewHolder.keyword_dy3.setVisibility(View.VISIBLE);
                   viewHolder.keyword_dy3.setText(newBean2.getLable().split(",")[2]);
               }

           }
            }
            viewHolder.line_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopuWindow_cache(newBean2.getId(),position);

                }
            });

            viewHolder.line_write.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("purchaseInfo".equals(newBean2.getgenre())) {
                        Intent intent = new Intent(context, dy_setting_layout.class);

                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("ismodify", "true");
                        intent.putExtra("genre", newBean2.getgenre());
                        (context).startActivity(intent);
                    }
               //     this.startActivityForResult(new Intent(getActivity(), dy_setting_layout.class), ActivityCodeUtil.DY);}
  else if("purchaseDemand".equals(newBean2.getgenre()))
                    {
                        Intent intent = new Intent(context, dy_setting_layout.class);

                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("ismodify", "true");
                        intent.putExtra("genre", newBean2.getgenre());
                        (context).startActivity(intent);
                    }
                    else if("purchaseSecret".equals(newBean2.getgenre()))
                    {
                        Intent intent = new Intent(context, dy_setting_layout.class);
                        intent.putExtra("id", newBean2.getId());
                        intent.putExtra("ismodify", "true");
                        intent.putExtra("genre", newBean2.getgenre());
                        intent.putExtra("keyword",newBean2.getLable());
                        (context).startActivity(intent);
                    }
                }
            });
        }
        else if (holder instanceof MyViewHolderTxt_law) {//------------------------------------------------------------------------9.纯文本新闻
           final MyViewHolderTxt_law viewHolder = (MyViewHolderTxt_law) holder;
            viewHolder.title.setText(newBean2.getTitle());
            iv=viewHolder.dismiss;

            if((newBean2.getcx_collec()+"").equals("CX_TRUE"))
            {       if(newBean2.getendtime()>TimeUtils.timeNow())
            {
                viewHolder.out.setVisibility(View.GONE);
            viewHolder.out1.setVisibility(View.VISIBLE);
            if(newBean2.getwarn())
                viewHolder.out1.setText("取消提醒");
            else
                viewHolder.out1.setText("提醒");
            }

            else
            {  viewHolder.out1.setVisibility(View.GONE);
                viewHolder.out.setVisibility(View.VISIBLE);}}
            if(newBean2.getArtype().equals("CX_dy"))
            {
            //viewHolder.finish_line.setVisibility(View.VISIBLE);
                if(newBean2.getLable().equals("已对接"))
                {
                   viewHolder.finish_line.setVisibility(View.VISIBLE);
                   viewHolder.finish_text.setVisibility(View.VISIBLE);
                   viewHolder.finish_text1.setVisibility(View.GONE);
                    viewHolder.finish_text.setText(newBean2.getLable());
                }
                else
                {
                   viewHolder.finish_line.setVisibility(View.VISIBLE);
                   viewHolder.finish_text1.setVisibility(View.VISIBLE);
                   viewHolder.finish_text.setVisibility(View.GONE);
                    viewHolder.finish_text1.setText(newBean2.getLable());
                }

            }
            viewHolder.creedtime.setText(newBean2.getVtime());
            if ("".equals(newBean2.gethistory_time()) || (newBean2.gethistory_time() == null))
                viewHolder.line_history_top.setVisibility(View.GONE);
            else {
                viewHolder.line_history_top.setVisibility(View.VISIBLE);
                viewHolder.line_history_count.setText("" + newBean2.gethistory_count() + "");
                viewHolder.line_history_time.setText(newBean2.gethistory_time());
            }
            if(newBean2.getIsCollect())
                viewHolder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
            else
                viewHolder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);

            viewHolder.out1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("提醒".equals(viewHolder.out1.getText())) {
                        Call<DefaultBean> call = MyApplication.getNetApi().collegewarn((String) SharedPreferenceUtils.get(context, "app_token", ""), newBean2.getId(), "true");
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    // ToastUtils.showMessage(context, response.body().getMessage());
                                    if ("200".equals(response.body().getStatus())) {
                                        viewHolder.out1.setText("取消提醒");
                                    } else
                                        ToastUtils.showMessage(context, "登录后才能收藏");
                                }
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {

                                call.cancel();
                            }
                        });
                    }
                  else{
                        deleteHistory(position);
                        backgroundAlpha((Activity)context, (float) 0.5);
                    }
                }
            });
            viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("true".equals(SharedPreferenceUtils.get(context, "vistor", ""))) {
                        //    initPopuWindow_dismiss(newBean2.getId(), newBean2.getArtype(), position);
                        if (v == iv)
                        {
                            if ("CX".equals(newBean2.getArtype())|"CX_dy".equals(newBean2.getArtype())) {
                                if (!newBean2.getIsCollect()) {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseDemand", newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                if("CX_dy".equals(newBean2.getArtype())){
                                                    SharedPreferenceUtils.put(context,"CXClickPosition",position);
                                                    SharedPreferenceUtils.put(context,"CXClickCollege","true");
                                                }
                                                // ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {
                                                    loadingDialog.showDialog(context, "");
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                                Message message = new Message();
                                                                message.what = 1;
                                                                mHandler.sendMessage(message);
                                                            } catch (Exception e) {
                                                                LogUtil.i("start:", e.toString());
                                                            }

                                                        }
                                                    }).start();
                                                    iv.setImageResource(R.mipmap.yantao_college_yes);
                                                    newBean2.setIsCollect(true);
                                                    //     iv_college_sucess.setVisibility(View.GONE);

                                                } else
                                                    ToastUtils.showMessage(context, "登录后才能收藏");
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {

                                            call.cancel();
                                        }
                                    });
                                } else {
                                    if("取消提醒".equals(viewHolder.out1.getText()))
                                    {
                                        initPopuWindow_dropMenu(iv,newBean2,position);
                                    }
                                    else
                                    {

                                        Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseDemand", newBean2.getId());
                                        call.enqueue(new Callback<DefaultBean>() {
                                            @Override
                                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                                if (response.isSuccessful()) {
                                                    ToastUtils.showMessage(context, response.body().getMessage());
                                                    if ("200".equals(response.body().getStatus())) {
                                                        if("CX_dy".equals(newBean2.getArtype())){
                                                            SharedPreferenceUtils.put(context,"CXClickPosition",position);
                                                            SharedPreferenceUtils.put(context,"CXClickCollege","false");
                                                        }
                                                        iv.setImageResource(R.mipmap.icon_subscription_default3x);
                                                        ToastUtils.showMessage(context, "成功取消收藏！");
                                                        newBean2.setIsCollect(false);
                                                        cgxqListnear.CGXQ(position,"取消收藏");
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
                                    //   iv_college.setImageResource(R.mipmap.icon_subscription_default3x);
                                    // ToastUtils.showMessage(context, "取消收藏成功");
                                }
                            } else {
                                if (!newBean2.getIsCollect()) {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(context, "app_token", ""), "policyInfo", newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                //   ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {
                                                    loadingDialog.showDialog(context, "");
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                                Message message = new Message();
                                                                message.what = 1;
                                                                mHandler.sendMessage(message);
                                                            } catch (Exception e) {
                                                                LogUtil.i("start:", e.toString());
                                                            }

                                                        }
                                                    }).start();
                                                    iv.setImageResource(R.mipmap.yantao_college_yes);
                                                    newBean2.setIsCollect(true);
                                                    //     iv_college_sucess.setVisibility(View.GONE);

                                                } else
                                                    ToastUtils.showMessage(context, "登录后才能收藏");
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {

                                            call.cancel();
                                        }
                                    });
                                } else {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""), "policyInfo", newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {

                                                    iv.setImageResource(R.mipmap.icon_subscription_default3x);
                                                    ToastUtils.showMessage(context, "成功取消收藏！");
                                                    newBean2.setIsCollect(false);
                                                    if(context.getClass()==CollectActivity.class)
                                                        ((CollectActivity)context).refreshed();
                                                }
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {

                                            call.cancel();
                                        }
                                    });

                                    //   iv_college.setImageResource(R.mipmap.icon_subscription_default3x);
                                    // ToastUtils.showMessage(context, "取消收藏成功");
                                }
                            }
                        }
                    }
                    else
                        ToastUtils.showMessage(context, "登录后才能收藏");
             //   v.setBackgroundResource(R.mipmap.yantao_college_yes);
          //      v.
                }
            });
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", newBean2.getArtype());
                    intent.putExtra("id", newBean2.getId());
                    if(newBean2.getIsCollect())
                        intent.putExtra("idcollect","true");
                    else
                        intent.putExtra("idcollect","false");
                         Log.e("采购需求", "type:" + newBean2.getArtype() + ",id:" + newBean2.getId() );
                   (context).startActivity(intent);
               //     ((MainActivity)context).startActivityForResult(intent,1024);
                //    ((Activity)context).startActivityForResult(intent,1024);
                }
            });
            String number = newBean2.getComnum();
            if (number != null && !"".equals(number)) {
                viewHolder.talkCount.setText(number);
            }
            //  viewHolder.newsTime.setText(newBean2.getDateformat());
       /*     if (newBean2.getZhiDing() != null && "yes".equals(newBean2.getZhiDing())) {
                viewHolder.tv_zhiDing.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_zhiDing.setVisibility(View.GONE);

            }*/

        }
        else if (holder instanceof MyViewHolderTxt_sm) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_sm viewHolder = (MyViewHolderTxt_sm) holder;
            viewHolder.title.setText(newBean2.getTitle());
            iv=viewHolder.dismiss;
            //author
            // String author = newBean2.getLable();
         /*   if (author != null) {
                if (author.length() > 10) {
                    author = author.substring(0, 10) + "...";
                }
            } else {
                author = "未知";
            }*/
            //   viewHolder.newsSource.setText(newBean2.getAuthor());
            //   viewHolder.newsTime.setText(newBean2.getLable());
            viewHolder.publishtime.setText( newBean2.getJca_time());
            viewHolder.secretDegree.setText( newBean2.getLable());
            viewHolder.creedtime.setText(newBean2.getVtime());
            if(newBean2.getIsCollect())
                viewHolder.dismiss.setImageResource(R.mipmap.yantao_college_yes);
            else
                viewHolder.dismiss.setImageResource(R.mipmap.icon_subscription_default3x);
            viewHolder.dismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("true".equals(SharedPreferenceUtils.get(context, "vistor", ""))) {
                        //    initPopuWindow_dismiss(newBean2.getId(), newBean2.getArtype(), position);
                        if (v == iv)

                        {
                            if ("SM".equals(newBean2.getArtype())) {
                                if (!newBean2.getIsCollect()) {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseSecret", newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                // ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {
                                                    loadingDialog.showDialog(context, "");
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                                Message message = new Message();
                                                                message.what = 1;
                                                                mHandler.sendMessage(message);
                                                            } catch (Exception e) {
                                                                LogUtil.i("start:", e.toString());
                                                            }

                                                        }
                                                    }).start();
                                                    iv.setImageResource(R.mipmap.yantao_college_yes);
                                                    newBean2.setIsCollect(true);
                                                    //     iv_college_sucess.setVisibility(View.GONE);

                                                } else
                                                    ToastUtils.showMessage(context, "登录后才能收藏");
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {

                                            call.cancel();
                                        }
                                    });
                                } else {
                                    Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseSecret", newBean2.getId());
                                    call.enqueue(new Callback<DefaultBean>() {
                                        @Override
                                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                            if (response.isSuccessful()) {
                                                ToastUtils.showMessage(context, response.body().getMessage());
                                                if ("200".equals(response.body().getStatus())) {
                                                    iv.setImageResource(R.mipmap.icon_subscription_default3x);
                                                    ToastUtils.showMessage(context, "成功取消收藏！");
                                                    newBean2.setIsCollect(false);
                                                    cgxqListnear.CGXQ(position,"取消收藏");
                                                }
                                            }
                                            call.cancel();
                                        }

                                        @Override
                                        public void onFailure(Call<DefaultBean> call, Throwable t) {

                                            call.cancel();
                                        }
                                    });

                                    //   iv_college.setImageResource(R.mipmap.icon_subscription_default3x);
                                    // ToastUtils.showMessage(context, "取消收藏成功");
                                }
                            }
                        }
                    }
                    else
                        ToastUtils.showMessage(context, "登录后才能收藏");
                    //   v.setBackgroundResource(R.mipmap.yantao_college_yes);
                    //      v.
                }
            });
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPopuWindow_cache1();

                }
            });


        }
        else if (holder instanceof MyViewHolderTxt_more) {//------------------------------------------------------------------------9.纯文本新闻
            MyViewHolderTxt_more viewHolder = (MyViewHolderTxt_more) holder;

            viewHolder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent  intent_read = new Intent(context, bidding_class_activity1.class);
                    intent_read.putExtra("title", "全部推荐");
                    context.startActivity(intent_read);*/

                }
            });
          /*    viewHolder.icon3_mainAcitivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, bidding_class_activity1.class);
                    intent_read.putExtra("title", "涉密采购");
                    context.startActivity(intent_read);

                }
            }); */
            /*  viewHolder.icon4_mainAcitivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, bidding_class_activity1.class);
                    intent_read.putExtra("title", "单一来源公示");
                    context.startActivity(intent_read);

                }
            });*/
         /*    viewHolder.icon5_mainAcitivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, provinceList.class);
                //    intent_read.putExtra("title", "招标广告");
                   context.startActivity(intent_read);
                  //  this.startActivityForResult(new Intent(getActivity(), provinceList.class), REQUEST_CODE_zb);
                }
            });*/
          /*   viewHolder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, bidding_class_activity1.class);
                    intent_read.putExtra("title", "招标公告");
                   // startActivityForResult(intent_read, REQUEST_CODE_zb);
                 //   Intent intent = new Intent(context, NewsDetailsActivity.class);
                   // intent.putExtra("type", newBean2.getArtype());
                 //   intent.putExtra("id", newBean2.getId());
                    context.startActivity(intent_read);

                }
            });*/
          /*    viewHolder.line_qiye.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, qyml_activity.class);
                     context.startActivity(intent_read);

                }
            });*/
         /*     viewHolder.line_cpml.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent_read = new Intent(context, cpml_activity.class);
                   // intent_read.putExtra("title", "招标广告");
                    // startActivityForResult(intent_read, REQUEST_CODE_zb);
                    //   Intent intent = new Intent(context, NewsDetailsActivity.class);
                    // intent.putExtra("type", newBean2.getArtype());
                    //   intent.putExtra("id", newBean2.getId());
                    context.startActivity(intent_read);

                }
            });*/
        /*    viewHolder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("type", newBean2.getArtype());
                    intent.putExtra("id", newBean2.getId());
                    context.startActivity(intent);
                }
            });*/


        }
                  else if (holder instanceof MyViewHolderTxt_bvip) {//------------------------------------------------------------------------9.纯文本新闻
           final MyViewHolderTxt_bvip viewHolder = (MyViewHolderTxt_bvip) holder;

            viewHolder.buy_goods.setText(newBean2.getAuthor());
            viewHolder.buy_type.setText(newBean2.getTitle());
            viewHolder.time_record.setText(newBean2.getVtime());
            if(newBean2.getArtype().equals("BUY_D"))
                viewHolder.line_price.setVisibility(View.VISIBLE);
            else
                viewHolder.line_price.setVisibility(View.GONE);
            viewHolder.buy_price.setText(newBean2.getprice());
if(newBean2.getArtype().equals("APPLY"))
    viewHolder.image_none.setVisibility(View.VISIBLE);
else
    viewHolder.image_none.setVisibility(View.GONE);
            if(newBean2.getArtype().equals("VIP_H"))
                viewHolder.line_detail.setVisibility(View.VISIBLE);
            else
                viewHolder.line_detail.setVisibility(View.GONE);
            viewHolder.text_stuas.setText(newBean2.getSort());
            viewHolder.line_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(newBean2.getIsCollect())
                    {
                        viewHolder.image_none.setImageResource(R.mipmap.select_none);
                        newBean2.setIsCollect(false);
                        notifyDataSetChanged();
                    }
                    else
                        {
                            viewHolder.image_none.setImageResource(R.mipmap.select_yes);
                            newBean2.setIsCollect(true);
                            notifyDataSetChanged();
                    }

                }
            });
            viewHolder.line_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_read = new Intent(context, InvoivedetalAvtivity.class);
                    intent_read.putExtra("id", newBean2.getId());
                  //  intent_read.putExtra("price", lists.get(0).getprovince());
                    context.startActivity(intent_read);

                }
            });
        }
        else if (holder instanceof MyViewHolderHorizontalBanner2) {//-----------------------------------------------10.Banner(基于ViewPager)
            MyViewHolderHorizontalBanner2 viewHolder = (MyViewHolderHorizontalBanner2) holder;

            List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
            BannerBean bannerBean = newBean2.getBannerBean();
            if (bannerBean != null) {
                List<BannerBean.DataBean> data = bannerBean.getData();
                if (data != null && data.size() > 0) {
                    for (BannerBean.DataBean dataBean : data) {
                        urls.add(new Entity(dataBean.getJal_images(), dataBean.getJai_url()));
                    }
                    viewHolder.recyclerBanner.setDatas(urls);
                    viewHolder.recyclerBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
                        @Override
                        public void onClick(RecyclerBanner.BannerEntity entity) {
                            String str = entity.getJump();
                            String[] arr = str.split("/");
                            int length = arr.length;
                            if (length > 2) {
                                String artype = arr[length - 2];
                                String id = arr[length - 1];
                                Intent intent;
                                switch (artype) {
                                    case "P":
                                        intent = new Intent(context, NewsDetailsActivity.class);
                                        intent.putExtra("type", artype + "");
                                        intent.putExtra("id", id + "");
                                        context.startActivity(intent);
                                        break;
                                    case "T":
                                        intent = new Intent(context, PhotoDetailsActivity.class);
                                        intent.putExtra("type", artype + "");
                                        intent.putExtra("id", id + "");
                                        context.startActivity(intent);
                                        break;
                                    case "S":
                                        intent = new Intent(context, VideoDetailsActivity.class);
                                        intent.putExtra("type", artype + "");
                                        intent.putExtra("id", id + "");
                                        context.startActivity(intent);
                                        break;
                                    case "C":
                                        intent = new Intent(context, NewsDetailsActivity.class);
                                        intent.putExtra("type", artype + "");
                                        intent.putExtra("id", id + "");
                                        context.startActivity(intent);
                                        break;
                                    case "D":
                                        intent = new Intent(context, YanTaoActivity.class);
                                        intent.putExtra("jc_id", id + "");
                                        context.startActivity(intent);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    });

                }
            }


        } else if (holder instanceof MyViewHolderHorizontalBanner3) {
            final MyViewHolderHorizontalBanner3 viewHolder = (MyViewHolderHorizontalBanner3) holder;
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context.getApplicationContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.recyclerView_update_banner_fragment1.setLayoutManager(mLayoutManager);
            BannerBean bannerBean = newBean2.getBannerBean();
            if (bannerBean != null) {
                List<BannerBean.DataBean> data = bannerBean.getData();
                if (data != null && data.size() > 0) {
                    ArrayList<String> picUrl = new ArrayList<>();
                    ArrayList<String> picName = new ArrayList<>();
                    ArrayList<String> jumpUrl = new ArrayList<>();
                    final int lenth = data.size();
                    for (BannerBean.DataBean dataBean : data) {
                        picUrl.add(dataBean.getJal_images());
                        picName.add(dataBean.getJal_desc());
                        jumpUrl.add(dataBean.getJai_url());
                    }
                    viewHolder.recyclerView_update_banner_fragment1.setAdapter(new BannerAdapter(picUrl, picName, jumpUrl, context));


                }
            }
        }

    }

    private void removePosition(int position) {
        lists.remove(position);
        notifyDataSetChanged();
     //   if(lists.size()==0)

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setShareClick(View.OnClickListener listener) {
        this.listener = listener;
    }

    //图片一张小图新闻
    class MyViewHolderOnePic extends RecyclerView.ViewHolder {


        @Bind(R.id.relative)
        LinearLayout relativeLayout;
        @Bind(R.id.onePic_pic_fragment1)
        ImageView onePic_pic_fragment1;

        @Bind(R.id.title_pic_fragment1)
        TextView title;

        @Bind(R.id.user_name)
        TextView user_name;


        public MyViewHolderOnePic(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //图片（三张横排）新闻
    class MyViewHolderThreePic extends RecyclerView.ViewHolder {
        @Bind(R.id.visible_sourceUser)
        LinearLayout linear_user;
        @Bind(R.id.visible_sourceNews)
        LinearLayout linear_news;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        @Bind(R.id.delete_pic_fragment1)
        ImageView dismiss;
        @Bind(R.id.iv_pic_one)
        ImageView pic_one;
        @Bind(R.id.iv_pic_two)
        ImageView pic_two;
        @Bind(R.id.iv_pic_three)
        ImageView pic_three;
        @Bind(R.id.tv_picNum)
        TextView picNum;
        @Bind(R.id.newsSource_pic_fragment1)
        TextView author;
        @Bind(R.id.newsTime_pic_fragment1)
        TextView time;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;
        @Bind(R.id.tv_zhiDing)
        TextView tv_zhiDing;
        @Bind(R.id.title_pic_fragment1)
        TextView title;

        public MyViewHolderThreePic(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_more extends RecyclerView.ViewHolder {
      /*  @Bind(R.id.icon1_mainAcitivity)
        RadioButton icon1_mainAcitivity;
        @Bind(R.id.icon2_mainAcitivity)
        RadioButton icon2_mainAcitivity;
        @Bind(R.id.icon3_mainAcitivity)
        RadioButton icon3_mainAcitivity;
        @Bind(R.id.icon4_mainAcitivity)
        RadioButton icon4_mainAcitivity;
        @Bind(R.id.icon5_mainAcitivity)
        RadioButton icon5_mainAcitivity;
        @Bind(R.id.relative)
        RelativeLayout relative;*/
        @Bind(R.id.more)
        TextView more;
     /*   @Bind(R.id.line_cpml)
        LinearLayout line_cpml;
        @Bind(R.id.line_qiye)
        LinearLayout line_qiye;*/
        public MyViewHolderTxt_more(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_bvip extends RecyclerView.ViewHolder {

        @Bind(R.id.time_record)
        TextView time_record;
        @Bind(R.id.buy_goods)
        TextView buy_goods;
        @Bind(R.id.buy_type)
        TextView buy_type;
        @Bind(R.id.image_none)
        ImageView image_none;
        @Bind(R.id.line_detail)
        LinearLayout line_detail;
        @Bind(R.id.line_buy)
        LinearLayout line_buy;
        @Bind(R.id.text_stuas)
        TextView text_stuas;
        @Bind(R.id.buy_price)
        TextView buy_price;
        @Bind(R.id.line_price)
        LinearLayout line_price;
        public MyViewHolderTxt_bvip(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //图片一张大图新闻
    class MyViewHolderBigPic extends RecyclerView.ViewHolder {
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
        @Bind(R.id.bigPic_pic_fragment1)
        ImageView bigPic;
        @Bind(R.id.newsSource_pic_fragment1)
        TextView author;
        @Bind(R.id.newsTime_pic_fragment1)
        TextView time;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;
        @Bind(R.id.tv_zhiDing)
        TextView tv_zhiDing;

        public MyViewHolderBigPic(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //纯文本新闻/
    class MyViewHolderTxt extends RecyclerView.ViewHolder {
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
        @Bind(R.id.tv_zhiDing)
        TextView tv_zhiDing;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;

        public MyViewHolderTxt(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //纯文本新闻
    class MyViewHolderTxt1 extends RecyclerView.ViewHolder {
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
        public MyViewHolderTxt1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //纯文本新闻
    class MyViewHolderTxt_law extends RecyclerView.ViewHolder {
        @Bind(R.id.finish_text)
        TextView finish_text;
        @Bind(R.id.finish_text1)
        TextView finish_text1;
        @Bind(R.id.finish_line)
        LinearLayout finish_line;
        @Bind(R.id.visible_sourceUser)
        LinearLayout linear_user;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        @Bind(R.id.line_history_top)
        LinearLayout line_history_top;
        @Bind(R.id.line_history_time)
        TextView line_history_time;
        @Bind(R.id.line_history_count)
        TextView line_history_count;
        @Bind(R.id.delete_pic_fragment1)
        ImageView dismiss;
        @Bind(R.id.title_pic_fragment1)
        TextView title;


        @Bind(R.id.gonggao_time)
        TextView creedtime;
        @Bind(R.id.out)
        TextView out;
        @Bind(R.id.out1)
        TextView out1;
        // @Bind(R.id.tv_zhiDing)
        //    TextView tv_zhiDing;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;

        public MyViewHolderTxt_law(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_sm extends RecyclerView.ViewHolder {

        @Bind(R.id.visible_sourceUser)
        LinearLayout linear_user;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
        @Bind(R.id.line_history_top)
        LinearLayout line_history_top;
        @Bind(R.id.line_history_time)
        TextView line_history_time;
        @Bind(R.id.line_history_count)
        TextView line_history_count;
        @Bind(R.id.delete_pic_fragment1)
        ImageView dismiss;
        @Bind(R.id.title_pic_fragment1)
        TextView title;
        @Bind(R.id.secretDegree)
        TextView secretDegree;
        @Bind(R.id.publishtime)
        TextView publishtime;

        @Bind(R.id.gonggao_time)
        TextView creedtime;
        // @Bind(R.id.tv_zhiDing)
        //    TextView tv_zhiDing;
        @Bind(R.id.newsTalk_pic_fragment1)
        TextView talkCount;

        public MyViewHolderTxt_sm(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_yzfx extends RecyclerView.ViewHolder {
        @Bind(R.id.today)
        TextView today;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.relative)
        RelativeLayout relative;
        public MyViewHolderTxt_yzfx(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_qyml extends RecyclerView.ViewHolder {
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.relative)
        LinearLayout relative;
        //  android:id="@+id/relative">
        public MyViewHolderTxt_qyml(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_yzfx_table_scape extends RecyclerView.ViewHolder {
        @Bind(R.id.table_project)
        TextView table_project;
        @Bind(R.id.table_money)
        TextView table_money;
        @Bind(R.id.table_bidding)
        TextView table_bidding;

        public MyViewHolderTxt_yzfx_table_scape(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_yzfx_table extends RecyclerView.ViewHolder {
        @Bind(R.id.table_project)
        TextView table_project;
        @Bind(R.id.table_money)
        TextView table_money;
        @Bind(R.id.table_bidding)
        TextView table_bidding;

        public MyViewHolderTxt_yzfx_table(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyViewHolderTxt_viewpoint extends RecyclerView.ViewHolder {
        @Bind(R.id.relative)
        LinearLayout relativeLayout;
        @Bind(R.id.title_pic_fragment1)
        TextView title_pic_fragment1;
        @Bind(R.id.user_touXiang)
        TextView source;
        @Bind(R.id.user_name)
        TextView time;
        @Bind(R.id.onePic_pic_fragment1)
        ImageView pic;
        @Bind(R.id.onepic_rel)
        RelativeLayout onepicRel;
        public MyViewHolderTxt_viewpoint(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_qyml_table_s extends RecyclerView.ViewHolder {
        @Bind(R.id.table_project)
        TextView table_project;
        @Bind(R.id.table_money)
        TextView table_money;
        @Bind(R.id.table_result)
        TextView table_result;
        @Bind(R.id.table_yz)
        TextView table_yz;
        public MyViewHolderTxt_qyml_table_s(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_qyml_table extends RecyclerView.ViewHolder {
        @Bind(R.id.table_project)
        TextView table_project;

        @Bind(R.id.table_result)
        TextView table_result;
        @Bind(R.id.table_yz)
        TextView table_yz;
        public MyViewHolderTxt_qyml_table(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_dy extends RecyclerView.ViewHolder {
        public SlidingButtonView slidingButtonView;
        public ViewGroup layout_content;
        @Bind(R.id.key_lable)
        TextView key_lable;
        @Bind(R.id.key_line)
        LinearLayout key_line;
        @Bind(R.id.line_read)
        LinearLayout line_read;
        @Bind(R.id.dq_line)
        LinearLayout dq_line;
        @Bind(R.id.type_line)
        LinearLayout type_line;
        @Bind(R.id.souce_line)
        LinearLayout souce_line;
        @Bind(R.id.text_hd)
        TextView text_hd;
        @Bind(R.id.textreadtime)
        TextView textreadtime;
        @Bind(R.id.mine_dy)
        TextView mine_dy;
        @Bind(R.id.keyword_dy1)
        TextView keyword_dy1;
        @Bind(R.id.keyword_dy2)
        TextView keyword_dy2;
        @Bind(R.id.keyword_dy3)
        TextView keyword_dy3;
        @Bind(R.id.keyword_dy4)
        TextView keyword_dy4;
        @Bind(R.id.keyword_dy5)
        TextView keyword_dy5;
        @Bind(R.id.keyword_dy6)
        TextView keyword_dy6;
        @Bind(R.id.sheng)
        TextView sheng;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.souce)
        TextView souce;
        @Bind(R.id.line_del)
        LinearLayout line_del;
        @Bind(R.id.line_write)
        LinearLayout line_write;
        @Bind(R.id.dj_line)
        LinearLayout dj_line;
        @Bind(R.id.dj_cg)
        TextView dj_cg;
        public MyViewHolderTxt_dy(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            slidingButtonView = (SlidingButtonView) itemView;
        }
    }
    //快报新闻
    class MyViewHolderFastNews extends RecyclerView.ViewHolder {
        @Bind(R.id.mv_bar1)
        MarqueeView mMarquee1;

        public MyViewHolderFastNews(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    class MyViewHolderTxt_recyclerView extends RecyclerView.ViewHolder {
        @Bind(R.id.recyclerView_fragment1)
        RecyclerView recyclerView;
          @Bind(R.id.xrefreshView_fragment1)
          XRefreshView refreshView;
        @Bind(R.id.iv_empty)
        ImageView iv_empty;
        @Bind(R.id.iv_repeat)
        ImageView iv_repeat;
        @Bind(R.id.tv_txt_no)
        TextView tv_txt_no;
        public MyViewHolderTxt_recyclerView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //Banner图新闻（RecycleView,2/3屏）
    class MyViewHolderHorizontalBanner extends RecyclerView.ViewHolder {
        @Bind(R.id.recyclerView_banner_fragment1)
        BetterRecyclerView recyclerView_banner_fragment1;

        public MyViewHolderHorizontalBanner(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Banner图新闻（ViewPager）
    class MyViewHolderHorizontalBanner2 extends RecyclerView.ViewHolder {
        @Bind(R.id.viewpager_bannerViewHolder)
        RecyclerBanner recyclerBanner;
        @Bind(R.id.tv_imgNum)
        TextView tv_imgNum;

        public MyViewHolderHorizontalBanner2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Banner图新闻（RecycleView,全屏）
    class MyViewHolderHorizontalBanner3 extends RecyclerView.ViewHolder {
        @Bind(R.id.recyclerView_update_banner_fragment1)
        BetterRecyclerView recyclerView_update_banner_fragment1;

        public MyViewHolderHorizontalBanner3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //研讨新闻
    class MyViewHolderYanTao extends RecyclerView.ViewHolder {
        @Bind(R.id.relative)
        RelativeLayout relative_jump;
        @Bind(R.id.title_tanTao)
        TextView title_tanTao;
        @Bind(R.id.tv_zanCount)
        TextView tv_zanCount;
        @Bind(R.id.tv_optionCount)
        TextView tv_optionCount;
        @Bind(R.id.tv_collegeCount)
        TextView tv_collegeCount;
        @Bind(R.id.icon_zan)
        ImageView icon_zan;
        @Bind(R.id.icon_option)
        ImageView icon_option;
        @Bind(R.id.icon_college)
        ImageView icon_college;
        @Bind(R.id.iv_yanTao)
        ImageView iv_yanTao;


        public MyViewHolderYanTao(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //研讨新闻
    class MyViewHolderYanTao2 extends RecyclerView.ViewHolder {
        @Bind(R.id.relative)
        RelativeLayout relative_jump;

        public MyViewHolderYanTao2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //视频新闻
    class MyViewHolderVideo extends RecyclerView.ViewHolder {
        @Bind(R.id.videoPlayer)
        JCVideoPlayerStandard videoPlayer;
        @Bind(R.id.linear_comment)
        LinearLayout linear_comment;
        @Bind(R.id.tv_userName)
        TextView tv_userName;
        @Bind(R.id.tv_talkNum)
        TextView tv_talkNum;
        @Bind(R.id.relative_share)
        RelativeLayout relative_share;
        @Bind(R.id.tv_vTime)
        TextView tv_vTime;


        public MyViewHolderVideo(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setBannerList(BannerBean bannerBean) {
//        this.bannerBean=bannerBean;
        notifyDataSetChanged();
    }

     /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     *
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    public interface IonSlidingViewClickListener {
       void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }


    /**
     * 删除弹窗
     */
    private PopupWindow popupWindow_cache1;
    private View contentView_cache1;
    public void initPopuWindow_cache1() {
        if (popupWindow_cache1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            contentView_cache1= mLayoutInflater.inflate(R.layout.popwindow_sm, null);
            popupWindow_cache1 = new PopupWindow(contentView_cache1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out= (RelativeLayout) contentView_cache1.findViewById(R.id.relative_out);
        LinearLayout relative_in= (LinearLayout) contentView_cache1.findViewById(R.id.relative_in);
        ImageView tv_cancel= (ImageView) contentView_cache1.findViewById(R.id.tv_cancel);
       final TextView tv_sure= (TextView) contentView_cache1.findViewById(R.id.tv_sure);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache1.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if("复制链接".equals(tv_sure.getText()))
                {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    ClipData myClip = ClipData.newPlainText("text", "http://www.weain.mil.cn/fwzn/xccxlc/");
                    cm.setPrimaryClip(myClip);
                    ToastUtils.showMessage(context, "复制成功");
                    tv_sure.setText("前往");
                }
               else if("前往".equals(tv_sure.getText()))
                {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://www.weain.mil.cn/fwzn/xccxlc/");
                    intent.setData(content_url);
                    context.startActivity(intent);
                    tv_sure.setText("复制链接");
                    popupWindow_cache1.dismiss();
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache1.dismiss();

            }
        });

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp =window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp);
        popupWindow_cache1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_cache1.setOutsideTouchable(true);
        popupWindow_cache1.setFocusable(true);
        popupWindow_cache1.showAtLocation(contentView_cache1, Gravity.CENTER, 0, 0);
        popupWindow_cache1.update();
//        popupWindow_cache.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_cache1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
    }

    /**
     * 删除弹窗
     */
    private PopupWindow popupWindow_cache;
    private View contentView_cache;
    public void initPopuWindow_cache(final String id, final int position) {
        if (popupWindow_cache == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            contentView_cache = mLayoutInflater.inflate(R.layout.popwindow_del_sure, null);
            popupWindow_cache = new PopupWindow(contentView_cache, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out= (RelativeLayout) contentView_cache.findViewById(R.id.relative_out);
        LinearLayout relative_in= (LinearLayout) contentView_cache.findViewById(R.id.relative_in);
        ImageView relative_view= (ImageView) contentView_cache.findViewById(R.id.obtain_yanZheng_tuxing);

        relative_view.setBackgroundResource(R.drawable.anim_rocket_thrust);


        AnimationDrawable  rocketAnimation  = (AnimationDrawable) relative_view.getBackground();
        if (rocketAnimation.isRunning()) {
            //停止动画
            rocketAnimation.stop();
        }
        rocketAnimation.start();
        TextView tv_sure= (TextView) contentView_cache.findViewById(R.id.tv_sure);
        TextView tv_cancel= (TextView) contentView_cache.findViewById(R.id.tv_cancel);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DefaultBean2> call = MyApplication.getNetApi().del_dy(id,(String) SharedPreferenceUtils.get(context, "app_token", ""));
                  call.enqueue(new Callback<DefaultBean2>() {
                    @Override
                    public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                        if (response.isSuccessful()) {
                            String status = response.body().getStatus();
                            if ("200".equals(status)) {
                                removePosition(position);
                                if(lists.size()==0)
                                    ((MainActivity)context).refresh_dydate();
                             //   popupWindow_dismiss.dismiss();
                            } else {
                                ToastUtils.showMessage(context.getApplicationContext(), response.body().getMsg());
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean2> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(context.getApplicationContext(), com.umeng.analytics.pro.c.e.toString());

                    }
                });
                popupWindow_cache.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();

            }
        });

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp =window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp);
        popupWindow_cache.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_cache.setOutsideTouchable(true);
        popupWindow_cache.setFocusable(true);
        popupWindow_cache.showAtLocation(contentView_cache, Gravity.CENTER, 0, 0);
        popupWindow_cache.update();
//        popupWindow_cache.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_cache.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
    }
    /**
     * 智库号弹框
     */
    private PopupWindow popupWindow2;
    private View contentView2;

    private void initPopuWindow_dropMenu(final ImageView iv, final NewBean2 newBean_2, final int position) {
       // final Activity activity = getActivity();
        if (popupWindow2 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(context);
            contentView2 = mLayoutInflater.inflate(R.layout.popwindow_dilog_collect, null);
            popupWindow2 = new PopupWindow(contentView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
//        String html="<p>移动端智库号的认证暂未开通，请使用电脑浏览器访问<font color=\"#0a8cb0\">www.jisuanweilai.com.cn</font>完成认证</p>";

//        String html="移动端智库号的认证暂未开通，请使用电脑浏览器访问www.jisuanweilai.com.cn完成认证";
        RelativeLayout relative_out = (RelativeLayout) contentView2.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView2.findViewById(R.id.relative_in);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        }
        });

        TextView close = (TextView) contentView2.findViewById(R.id.iv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        TextView btn_copy = (TextView) contentView2.findViewById(R.id.btn_copy);
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(context, "app_token", ""), "purchaseDemand", newBean_2.getId());
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            ToastUtils.showMessage(context, response.body().getMessage());
                            if ("200".equals(response.body().getStatus())) {

                                iv.setImageResource(R.mipmap.icon_subscription_default3x);
                                ToastUtils.showMessage(context, "成功取消收藏！");
                                popupWindow2.dismiss();
                                newBean_2.setIsCollect(false);
                                cgxqListnear.CGXQ(position,"取消收藏");
                                if (context.getClass() == CollectActivity.class)
                                    ((CollectActivity) context).refreshed();
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
        });

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setFocusable(true);
        popupWindow2.showAtLocation(contentView2, Gravity.CENTER, 0, 0);
        popupWindow2.update();
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
    }
    /**
     * 不感兴趣弹窗
     */
    private PopupWindow popupWindow_dismiss;
    private View contentView_dismiss;
    private int choiceNum = 0;
    private List<JuBaoBean.DataBean> data;
    private ArrayList<String> choiceList = new ArrayList<>();
    private HashMap<String, String> choiceMap = new HashMap<>();

    public void initPopuWindow_dismiss(final String id, final String type, final int position) {
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
        final TextView choiceCount = (TextView) contentView_dismiss.findViewById(R.id.tv_choiceCount);  //已选个数
        final TextView txt_title = (TextView) contentView_dismiss.findViewById(R.id.txt_title);         //弹框标题
        RelativeLayout relative_cancel = (RelativeLayout) contentView_dismiss.findViewById(R.id.relative_cancel);
        RelativeLayout relative_child = (RelativeLayout) contentView_dismiss.findViewById(R.id.relative_child);
        choiceList.clear();
        choiceMap.clear();
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choiceNum++;
                    choiceList.add(choiceMap.get(buttonView.getText().toString().trim()));
                } else {
                    choiceNum--;
                    choiceList.remove(choiceMap.get(buttonView.getText().toString().trim()));
                }
                if (choiceNum == 0) {
                    sure.setVisibility(View.GONE);
                    choiceCount.setVisibility(View.GONE);
                    txt_title.setVisibility(View.VISIBLE);
                } else {
                    sure.setVisibility(View.VISIBLE);
                    choiceCount.setVisibility(View.VISIBLE);
                    txt_title.setVisibility(View.GONE);
                    choiceCount.setText("已选" + choiceNum + "个理由");
                }
            }
        };
        relative_cancel.setOnClickListener(new View.OnClickListener() {
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
                    Call<DefaultBean> call = MyApplication.getNetApi().userFeedBack(id, type, sb.toString().trim(), "", (String) SharedPreferenceUtils.get(context, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String status = response.body().getStatus();
                                if ("0001".equals(status)) {
                                    removePosition(position);
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
                    popupWindow_dismiss.dismiss();
                }
            }

        });

        if (data == null) {
            Call<JuBaoBean> call = MyApplication.getNetApi().getJuBao("2");
            call.enqueue(new Callback<JuBaoBean>() {
                @Override
                public void onResponse(Call<JuBaoBean> call, Response<JuBaoBean> response) {
                    if (response.isSuccessful()) {
                        JuBaoBean body = response.body();
                        data = body.getData();
                        for (JuBaoBean.DataBean dataBean : data) {
                            choiceMap.put(dataBean.getJl_name(), dataBean.getJl_id() + "");
                        }
                        if (data != null && data.size() > 0) {
                            LogUtil.i("cc", "我走的if");
                            int length = data.size();
                            for (int x = 0; x < length; x++) {
                                if (x == 0) {
                                    cb1.setText(data.get(x).getJl_name());
                                    cb1.setVisibility(View.VISIBLE);
                                } else if (x == 1) {
                                    cb2.setText(data.get(x).getJl_name());
                                    cb2.setVisibility(View.VISIBLE);
                                } else if (x == 2) {
                                    cb3.setText(data.get(x).getJl_name());
                                    cb3.setVisibility(View.VISIBLE);
                                } else if (x == 3) {
                                    cb4.setText(data.get(x).getJl_name());
                                    cb4.setVisibility(View.VISIBLE);
                                } else if (x == 4) {
                                    cb5.setText(data.get(x).getJl_name());
                                    cb5.setVisibility(View.VISIBLE);
                                } else if (x == 5) {
                                    cb6.setText(data.get(x).getJl_name());
                                    cb6.setVisibility(View.VISIBLE);
                                }
                            }
                            cb1.setChecked(false);
                            cb2.setChecked(false);
                            cb3.setChecked(false);
                            cb4.setChecked(false);
                            cb5.setChecked(false);
                            cb6.setChecked(false);
                            choiceNum = 0;
                            sure.setVisibility(View.GONE);
                            choiceCount.setVisibility(View.GONE);
                            txt_title.setVisibility(View.VISIBLE);

                        }

                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<JuBaoBean> call, Throwable t) {

                    call.cancel();
                }
            });
        } else {
            LogUtil.i("cc", "我走的else");

            int length = data.size();
            for (int x = 0; x < length; x++) {
                if (x == 0) {
                    cb1.setText(data.get(x).getJl_name());
                    cb1.setVisibility(View.VISIBLE);
                } else if (x == 1) {
                    cb2.setText(data.get(x).getJl_name());
                    cb2.setVisibility(View.VISIBLE);
                } else if (x == 2) {
                    cb3.setText(data.get(x).getJl_name());
                    cb3.setVisibility(View.VISIBLE);
                } else if (x == 3) {
                    cb4.setText(data.get(x).getJl_name());
                    cb4.setVisibility(View.VISIBLE);
                } else if (x == 4) {
                    cb5.setText(data.get(x).getJl_name());
                    cb5.setVisibility(View.VISIBLE);
                } else if (x == 5) {
                    cb6.setText(data.get(x).getJl_name());
                    cb6.setVisibility(View.VISIBLE);
                }
            }
            cb1.setChecked(false);
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);
            cb6.setChecked(false);
            choiceNum = 0;
            sure.setVisibility(View.GONE);
            choiceCount.setVisibility(View.GONE);
            txt_title.setVisibility(View.VISIBLE);
            for (JuBaoBean.DataBean dataBean : data) {
                choiceMap.put(dataBean.getJl_name(), dataBean.getJl_id() + "");
            }
        }


        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);
        cb4.setOnCheckedChangeListener(listener);
        cb5.setOnCheckedChangeListener(listener);
        cb6.setOnCheckedChangeListener(listener);


        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow_dismiss.setBackgroundDrawable(cd);
//        cd.setCallback(null);
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

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(context.getApplicationContext(), "分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(context.getApplicationContext(), "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(context.getApplicationContext(), "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(context.getApplicationContext(), "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {

                ToastUtils.showMessage(context.getApplicationContext(), "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(context.getApplicationContext(), "分享失败,msg:" + t.getMessage());

            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage(context.getApplicationContext(), "分享取消");

        }
    };
    public interface CGXQListnear{
        void CGXQ(int position,String bj);
    }
    public CGXQListnear cgxqListnear;
    public void setCgxqListnear(CGXQListnear cgxqListnear){
        this.cgxqListnear = cgxqListnear;
    }

    public interface Colletlistnear{
        void collet();
    }
    public Colletlistnear colletlistnear;
    public void setColletlistnear(Colletlistnear colletlistnear){
        this.colletlistnear = colletlistnear;
    }

    private void deleteHistory(final int position) {
        final NewBean2 newBean2 = lists.get(position);
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;

        View view = View.inflate(context,R.layout.delete_alert_layout,null);
        final PopupWindow popupWindow = new PopupWindow(view,width/4*3,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView title = view.findViewById(R.id.delete_title);
        Button dismiss =view.findViewById(R.id.dismiss_btn);
        Button sure =view.findViewById(R.id.sure_btn);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

        title.setText("该项目已设置提醒,确定取消吗？");
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DefaultBean> call = MyApplication.getNetApi().collegewarn((String) SharedPreferenceUtils.get(context, "app_token", ""), newBean2.getId(), "false");
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            // ToastUtils.showMessage(context, response.body().getMessage());
                            if ("200".equals(response.body().getStatus())) {
                                cgxqListnear.CGXQ(position,"取消提醒");
                            }else ToastUtils.showMessage(context, "登录后才能收藏");

                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {

                        call.cancel();
                    }
                });
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context,1);
            }
        });
    }
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
