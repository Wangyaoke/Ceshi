package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.YanTaoAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.YanTaoDetailBean;
import com.ansiyida.cgjl.bean.YanTaoOptionsBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.DpPxTools;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YanTaoActivity extends BaseActivity {
    @Bind(R.id.recyclerView_yanTaoActivity)
    RecyclerView recyclerView;
    @Bind(R.id.relative_top)
    RelativeLayout relative_top;
    @Bind(R.id.xrefreshView_yanTaoActivity)
    XRefreshView refreshView;
    @Bind(R.id.iv_touXiang)
    ImageView iv_touXiang;
    @Bind(R.id.tv_userName)
    TextView tv_userName;
    @Bind(R.id.iv_zan)
    ImageView iv_zan;
    @Bind(R.id.tv_zan)
    TextView tv_zan;
    private YanTaoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private int instance;
    private String id;
    private int pageNum = 1;
    private int pageCount = 10;
    private ArrayList<YanTaoOptionsBean.DataBean.AnswersBean> beanList;
    private ArrayList<Integer> lineCountList;
    private ArrayList<Boolean> visbleList;
    private String ja_ask_id;
    private String share_title = "";
    private String share_des = "";
    private String share_img = "";
    private Bitmap myBitmap;
    private boolean isClick = true;
    private String thumbus_id;
    private ArrayList<Integer> zanFlag;
    private int imgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yan_tao;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        Intent intent = getIntent();
        id = intent.getStringExtra("jc_id");
        imgUrl = intent.getIntExtra("img_id",0);
        if (imgUrl ==0){
            imgUrl =R.mipmap.yt_1;
        }
//        id = "15";
//        type_id ="6";
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        instance = DpPxTools.dip2px(this, 192);
        relative_top.setVisibility(View.GONE);
        beanList = new ArrayList<>();
        visbleList = new ArrayList<>();
        lineCountList = new ArrayList<>();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scollYDistance = getScollYDistance();
                if (scollYDistance > instance) {
                    relative_top.setVisibility(View.VISIBLE);
                } else {
                    relative_top.setVisibility(View.GONE);

                }

            }
        });

        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        zanFlag = new ArrayList<>();

    }

    private int getScollYDistance() {
        int position = mLayoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = mLayoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }


    @Override
    protected void initData() {

    }

    public void httpOptions() {
        pageNum = 1;
        LogUtil.i("id", "id:" + id);
        Call<YanTaoOptionsBean> call = MyApplication.getNetApi().yanTaoOptions((String) SharedPreferenceUtils.get(this, "app_token", ""), id, pageNum + "", pageCount + "");
        call.enqueue(new Callback<YanTaoOptionsBean>() {
            @Override
            public void onResponse(Call<YanTaoOptionsBean> call, Response<YanTaoOptionsBean> response) {
                if (response.isSuccessful()) {
                    YanTaoOptionsBean body = response.body();
                    if (body != null) {
                        YanTaoOptionsBean.DataBean data = body.getData();
                        if (data != null) {
                            List<YanTaoOptionsBean.DataBean.AnswersBean> answers = data.getAnswers();
                            if (answers != null && answers.size() > 0) {
                                for (YanTaoOptionsBean.DataBean.AnswersBean ss : answers) {
                                    if ("Y".equals(ss.getIsThumbsUp())) {
                                        zanFlag.add(1);
                                    } else {
                                        zanFlag.add(0);
                                    }
                                }
                                beanList.clear();
                                beanList.addAll(answers);
                                int temp = lineCountList.get(0);
                                boolean bool = visbleList.get(0);
                                visbleList.clear();
                                visbleList.add(bool);
                                lineCountList.clear();
                                lineCountList.add(temp);
                                int length = beanList.size();
                                for (int x = 0; x < length; x++) {
                                    lineCountList.add(0);
                                    visbleList.add(false);
                                }
                                answers.clear();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }


                } else {
                    ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误2_1");
                }
                refreshView.stopRefresh();
                call.cancel();
            }

            @Override
            public void onFailure(Call<YanTaoOptionsBean> call, Throwable t) {
                ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误2_2");
                call.cancel();
                refreshView.stopRefresh();
            }
        });

    }

    public void httpOptionsLoadMore() {
        pageNum++;
        LogUtil.i("yyy", "token:" + (String) SharedPreferenceUtils.get(this, "app_token", ""));
        LogUtil.i("yyy", "id:" + id + ",pageNum:" + pageNum + ",pageCount:" + pageCount);
        Call<YanTaoOptionsBean> call = MyApplication.getNetApi().yanTaoOptions((String) SharedPreferenceUtils.get(this, "app_token", ""), id, pageNum + "", pageCount + "");
        call.enqueue(new Callback<YanTaoOptionsBean>() {
            @Override
            public void onResponse(Call<YanTaoOptionsBean> call, Response<YanTaoOptionsBean> response) {
                if (response.isSuccessful()) {
                    List<YanTaoOptionsBean.DataBean.AnswersBean> answers = response.body().getData().getAnswers();
                    if (answers != null && answers.size() > 0) {
                        for (YanTaoOptionsBean.DataBean.AnswersBean ss : answers) {
                            if ("Y".equals(ss.getIsThumbsUp())) {
                                zanFlag.add(1);
                            } else {
                                zanFlag.add(0);
                            }
                        }
                        beanList.addAll(answers);
                        int length = answers.size();
                        for (int x = 0; x < length; x++) {
                            lineCountList.add(0);
                            visbleList.add(false);
                        }
                        answers.clear();
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showMessage(YanTaoActivity.this, "已加载到底部");
                        pageNum--;
                    }

                } else {
                    ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误2_1");
                }

                call.cancel();
                refreshView.stopLoadMore();
            }

            @Override
            public void onFailure(Call<YanTaoOptionsBean> call, Throwable t) {
                ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误2_2");
                call.cancel();
                refreshView.stopLoadMore();
            }
        });

    }

    @Override
    protected void httpData() {
        LogUtil.i("id", "id:" + id);
        Call<YanTaoDetailBean> call = MyApplication.getNetApi().yanTaoDetailQuestion((String) SharedPreferenceUtils.get(this, "app_token", ""), id);
        call.enqueue(new Callback<YanTaoDetailBean>() {
            @Override
            public void onResponse(Call<YanTaoDetailBean> call, Response<YanTaoDetailBean> response) {
                if (response.isSuccessful()) {
                    YanTaoDetailBean.DataBean data = response.body().getData();
                    if (data != null) {
                        YanTaoDetailBean.DataBean.QuestionBean question = data.getQuestion();
                        lineCountList.add(0);
                        visbleList.add(false);
                        ja_ask_id = question.getJp_id() + "";
                        String jmi_img = question.getJmi_img() + "";
                        if ("null".equals(jmi_img)) {
                            jmi_img = "";
                        }
                        if ("Y".equals(question.getIsClick())) {
                            isClick = true;
                            String clickNumStr = question.getClickNum() + "";
                            LogUtil.i("clickNumStr", "clickNumStr:" + clickNumStr);

                            if ("null".equals(clickNumStr) || "".equals(clickNumStr)) {
                                clickNumStr = "0";
                            } else {
                                clickNumStr=(int)(double)question.getClickNum()+"";
                            }
                            tv_zan.setText("赞" + clickNumStr);
                            tv_zan.setTextColor(Color.parseColor("#FE3060"));
                            iv_zan.setImageResource(R.mipmap.zan2);
                        } else {
                            String clickNumStr = question.getClickNum() + "";
                            LogUtil.i("clickNumStr", "clickNumStr:" + clickNumStr);
                            if ("null".equals(clickNumStr) || "".equals(clickNumStr)) {
                                clickNumStr = "0";
                            } else {
                                clickNumStr=(int)(double)question.getClickNum()+"";
                            }
                            tv_zan.setText("赞" + clickNumStr);
                            isClick = false;
                        }

                        thumbus_id = question.getJp_id() + "";
                        share_img = question.getJp_img();
                        share_title = question.getJp_title();
                        share_des = question.getJp_content();
                        if (share_des == null) {
                            share_des = "";
                        }
                        Glide.with(YanTaoActivity.this).load(jmi_img).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(YanTaoActivity.this)).into(iv_touXiang);
                        tv_userName.setText(question.getJmi_username() + "");
                        adapter = new YanTaoAdapter(YanTaoActivity.this, question, beanList, lineCountList, visbleList, zanFlag,imgUrl);
                        recyclerView.setAdapter(adapter);
                        httpOptions();
                    }

                } else {
                    ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误1_1");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<YanTaoDetailBean> call, Throwable t) {

                call.cancel();
                ToastUtils.showMessage(YanTaoActivity.this, "YT未知错误1_2");

            }
        });
    }

    @Override
    protected void setClickListener() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                httpOptions();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpOptionsLoadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

    }

    private List<JuBaoBean.DataBean> data = null;

    @OnClick({R.id.image_back, R.id.linear_guanDian, R.id.linear_zan, R.id.linear_report})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.显示与隐藏的返回上一级按钮
                this.finish();
                break;
            case R.id.linear_guanDian:          //2.发表观点
                if (ja_ask_id != null) {
                    Intent intent = new Intent(this, OpinionActivity.class);
                    intent.putExtra("ja_ask_id", ja_ask_id);
                    startActivityForResult(intent, ActivityCodeUtil.OPINIONACTIVITY);
                }

                break;
            case R.id.linear_zan:               //3.点赞
                if (!isClick) {
                    LogUtil.i("zan", "id:" + thumbus_id);
                    Call<DefaultBean> call = MyApplication.getNetApi().addgread(thumbus_id, "Z", "up", (String) SharedPreferenceUtils.get(this, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String message = response.body().getMessage();
                                if ("0001".equals(response.body().getStatus())) {
                                    iv_zan.setImageResource(R.mipmap.zan2);
                                    String str = tv_zan.getText().toString().trim();
                                    int zanCount = Integer.parseInt(str.substring(1));
                                    zanCount++;
                                    tv_zan.setText("赞" + zanCount);
                                    tv_zan.setTextColor(Color.parseColor("#FE3060"));
                                    isClick = true;
                                } else {
                                    ToastUtils.showMessage(YanTaoActivity.this, message);
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


                break;
            case R.id.linear_report:            //4.举报
                if (data == null) {
                    Call<JuBaoBean> call = MyApplication.getNetApi().getJuBao("20");
                    call.enqueue(new Callback<JuBaoBean>() {
                        @Override
                        public void onResponse(Call<JuBaoBean> call, Response<JuBaoBean> response) {
                            if (response.isSuccessful()) {
                                JuBaoBean body = response.body();
                                data = body.getData();
                                if (data != null && data.size() > 0) {
                                    LogUtil.i("cc", "我走的if");
                                    initPopuWindow_report(data);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<JuBaoBean> call, Throwable t) {

                        }
                    });
                } else {
                    LogUtil.i("cc", "我走的else");
                    initPopuWindow_report(data);

                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 520) {
            if (requestCode == ActivityCodeUtil.OPINIONACTIVITY) {
                httpOptions();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (beanList != null) {
            beanList.clear();
            beanList = null;
        }
        if (lineCountList != null) {
            lineCountList.clear();
            lineCountList = null;
        }
        if (visbleList != null) {
            visbleList.clear();
            visbleList = null;
        }
        if (animationEnd != null) {
            animationEnd.cancel();
        }
    }

    /**
     * 举报弹框
     */
    private PopupWindow popupWindow_report;
    private View contentView_report;

    public void initPopuWindow_report(List<JuBaoBean.DataBean> data) {
        if (popupWindow_report == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_report = mLayoutInflater.inflate(R.layout.popwindow_report, null);
            popupWindow_report = new PopupWindow(contentView_report, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relativeLayout = (RelativeLayout) contentView_report.findViewById(R.id.relative);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnimation();
            }
        });
        Button cancle = (Button) contentView_report.findViewById(R.id.cancel_btn);
        Button juBao_1 = (Button) contentView_report.findViewById(R.id.juBao_1);
        Button juBao_2 = (Button) contentView_report.findViewById(R.id.juBao_2);
        Button juBao_3 = (Button) contentView_report.findViewById(R.id.juBao_3);
        Button juBao_4 = (Button) contentView_report.findViewById(R.id.juBao_4);
        Button juBao_5 = (Button) contentView_report.findViewById(R.id.juBao_5);
        Button juBao_6 = (Button) contentView_report.findViewById(R.id.juBao_6);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnimation();
            }
        });
        int length = data.size();
        if (length > 6) {
            length = 6;
        }
        for (int x = 0; x < length; x++) {
            if (x == 0) {
                juBao_1.setText(data.get(x).getJl_name());
                juBao_1.setVisibility(View.VISIBLE);
                juBaoClick(juBao_1, data.get(x).getJl_id() + "");
            } else if (x == 1) {
                juBao_2.setText(data.get(x).getJl_name());
                juBao_2.setVisibility(View.VISIBLE);
                juBaoClick(juBao_2, data.get(x).getJl_id() + "");
            } else if (x == 2) {
                juBao_3.setText(data.get(x).getJl_name());
                juBao_3.setVisibility(View.VISIBLE);
                juBaoClick(juBao_3, data.get(x).getJl_id() + "");
            } else if (x == 3) {
                juBao_4.setText(data.get(x).getJl_name());
                juBao_4.setVisibility(View.VISIBLE);
                juBaoClick(juBao_4, data.get(x).getJl_id() + "");
            } else if (x == 4) {
                juBao_5.setText(data.get(x).getJl_name());
                juBao_5.setVisibility(View.VISIBLE);
                juBaoClick(juBao_5, data.get(x).getJl_id() + "");
            } else if (x == 5) {
                juBao_6.setText(data.get(x).getJl_name());
                juBao_6.setVisibility(View.VISIBLE);
                juBaoClick(juBao_6, data.get(x).getJl_id() + "");
            }
        }

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_report.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_report.setOutsideTouchable(true);
        popupWindow_report.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow_report.showAtLocation(contentView_report, Gravity.CENTER, 0, 0);
        popupWindow_report.update();
        popupWindow_report.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_report.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = YanTaoActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                YanTaoActivity.this.getWindow().setAttributes(lp);
            }
        });
    }

    public void share() {
        if (share_img != null && !"".equals(share_img)) {
            LogUtil.i("share3", "if,share_img:" + share_img);
            Glide.with(this.getApplicationContext()).load(share_img).asBitmap().into(new SimpleTarget<Bitmap>(80, 80) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    LogUtil.i("share3", "onResourceReady");
                    if (myBitmap == null) {
                        myBitmap = resource;
                    }
                    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                            {
                                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                            };
                    UMImage image = new UMImage(YanTaoActivity.this.getApplicationContext(), myBitmap);
                    UMWeb web = new UMWeb(Constant.URL + "web/discuss/questions/" + "/" + id);
                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(YanTaoActivity.this)
                            .withMedia(web)
                            .setDisplayList(displaylist)
                            .setCallback(umShareListener)
                            .open();
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    LogUtil.i("share3", "onLoadFailed");
                    if (myBitmap == null) {
                        myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);

                    }
                    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                            {
                                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                            };
                    UMImage image = new UMImage(YanTaoActivity.this.getApplicationContext(), myBitmap);
                    UMWeb web = new UMWeb(Constant.URL + "web/discuss/questions/" + "/" + id);
                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(YanTaoActivity.this)
                            .withMedia(web)
                            .setDisplayList(displaylist)
                            .setCallback(umShareListener)
                            .open();
                }
            });
        } else {
            LogUtil.i("share3", "else");
            if (myBitmap == null) {
                myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);

            }
            final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                    {
                            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                            SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                    };
            UMImage image = new UMImage(YanTaoActivity.this.getApplicationContext(), myBitmap);
            UMWeb web = new UMWeb(Constant.URL + "web/discuss/questions/" + "/" + id);
            web.setTitle(share_title);//标题
            web.setThumb(image);  //缩略图
            web.setDescription(share_des);//描述
            new ShareAction(YanTaoActivity.this)
                    .withMedia(web)
                    .setDisplayList(displaylist)
                    .setCallback(umShareListener)
                    .open();
        }
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(YanTaoActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(YanTaoActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(YanTaoActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(YanTaoActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                ToastUtils.showMessage(YanTaoActivity.this, "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(YanTaoActivity.this, "分享失败,msg:" + t.getMessage());

            }
            LogUtil.i("share", "分享失败,msg:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };

    private void juBaoClick(final Button btn, final String juBaoId) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("jubao", "type:" + "C" + ",id:" + id + ",juBaoId:" + juBaoId);
                Call<DefaultBean> call = MyApplication.getNetApi().http_report((String) SharedPreferenceUtils.get(YanTaoActivity.this, "app_token", ""), "A", "C", juBaoId, id);
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            if ("0001".equals(response.body().getStatus())) {
                                ToastUtils.showMessage(YanTaoActivity.this, "举报成功");

                            } else {
                                ToastUtils.showMessage(YanTaoActivity.this, response.body().getMessage());
                            }

                        } else {
                            ToastUtils.showMessage(YanTaoActivity.this, "异常错误1");
                        }
                        popupWindow_report.dismiss();

                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {
                        ToastUtils.showMessage(YanTaoActivity.this, "异常错误2");
                        popupWindow_report.dismiss();

                        call.cancel();

                    }
                });
            }
        });
    }


    private Animation animationEnd;

    private void endAnimation() {
        if (animationEnd == null) {
            animationEnd = AnimationUtil.createOutAnimation(YanTaoActivity.this, 500);

        }
        animationEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                popupWindow_report.dismiss();
                animation.cancel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        popupWindow_report.getContentView().startAnimation(animationEnd);
    }

}
