package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.VideoDetailAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.NewDetailBean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDetailsActivity extends BaseActivity {
    @Bind(R.id.videoPlayer_detail)
    JCVideoPlayerStandard player;
    @Bind(R.id.recycleView_detail)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_videoDetailsActivity)
    XRefreshView refreshView;
    @Bind(R.id.iv_college)
    ImageView iv_college;
    private ArrayList<Integer> list;
    private ArrayList<Integer> zanFlag;
    private String id;
    private String type;
    private NewDetailBean newDetailBean;
    private VideoDetailAdapter adapter;
    private int pageNum = 0;
    private int pageSize = 10;
    private List<CommentBean.DataBean.ContentBean> comentBeanList;
    private String video;
    private String img;
    private String share_title = "";
    private String share_des = "";
    private String share_img = "";
    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        zanFlag = new ArrayList<>();
        newDetailBean = new NewDetailBean();
        comentBeanList = new ArrayList<>();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        adapter = new VideoDetailAdapter(list, this, zanFlag, newDetailBean, comentBeanList, supportFragmentManager);
        recyclerView.setAdapter(adapter);
        //允许加载更多
        refreshView.setPullLoadEnable(false);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {
        Call<NewDetailBean> call = MyApplication.getNetApi().getNewDetail(type, id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        call.enqueue(new Callback<NewDetailBean>() {
            @Override
            public void onResponse(Call<NewDetailBean> call, Response<NewDetailBean> response) {
                if (response.isSuccessful()) {
                    NewDetailBean body = response.body();
                    NewDetailBean.DataBeanX.ArticleBean article = body.getData().getArticle();

                    String jca_img = article.getJca_img();
                    String jca_title = article.getJca_title();
                    if (jca_title != null && !"".equals(jca_title)) {
                        share_title = jca_title;
                    }
                    String jca_des = article.getJca_des();
                    if (jca_des != null && !"".equals(jca_des)) {
                        share_des = jca_des;
                    }
                    if (jca_img != null && !"".equals(jca_img)) {
                        share_img = jca_img;
                    }
                    String vieo_path = article.getVieo_path();
                    String iscollectar = body.getData().getIscollectar();
                    if (iscollectar != null && "Y".equals(iscollectar)) {
                        iv_college.setImageResource(R.mipmap.yantao_college_yes);
                    }
                    boolean setUp = player.setUp(vieo_path, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                    if (setUp) {
                        Glide.with(VideoDetailsActivity.this.getApplicationContext()).load(jca_img).skipMemoryCache(true).into(player.thumbImageView);
                    }
                    adapter.setNewDetailBean(body);
                    getComment();

                } else {
                    ToastUtils.showMessage(VideoDetailsActivity.this, "获取视频描述失败");

                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<NewDetailBean> call, Throwable t) {
                ToastUtils.showMessage(VideoDetailsActivity.this, "获取视频描述失败");
                call.cancel();
            }
        });
    }

    @Override
    protected void setClickListener() {

        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                LogUtil.i("xfre", "onRefresh");
            }

            @Override
            public void onRefresh(boolean isPullDown) {
                LogUtil.i("xfre", "onRefresh(boolean isPullDown)");

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageNum++;
                Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String)SharedPreferenceUtils.get(VideoDetailsActivity.this,"app_token",""),type, id, pageNum + "", pageSize + "", "");
                call2.enqueue(new Callback<CommentBean>() {
                    @Override
                    public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                        if (response.isSuccessful()) {
                            LogUtil.i("kj", "1");
                            adapter.setLoadComments(response.body().getData().getContent());
                            refreshView.stopLoadMore();

                        } else {
                            LogUtil.i("kj", "2");
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<CommentBean> call, Throwable t) {
                        LogUtil.i("kj", "3");
                        call.cancel();
                    }
                });

            }

            @Override
            public void onRelease(float direction) {
                LogUtil.i("xfre", "onRelease");

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {
                LogUtil.i("xfre", "onHeaderMove");

            }
        });


    }

    @OnClick({R.id.image_back, R.id.iv_pingLun, R.id.iv_editPingLun, R.id.picture_iv_back, R.id.iv_college, R.id.iv_share})
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.iv_pingLun:                   //1.点击查看其他用户评论按钮
//                if (flag) {
//                    recyclerView.scrollToPosition(3);
//                } else {
//                    recyclerView.scrollToPosition(1);
//                }
//                handler.sendEmptyMessageDelayed(1, 100);
//                break;
            case R.id.iv_editPingLun:               //2.发表评论按钮

                CommentDialog2 commentDialog2 = new CommentDialog2("优质评论将会优先展示", "", 300, new CommentDialog2.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        Call<DefaultBean> call = MyApplication.getNetApi().saveComment(id, type, inputText, (String) SharedPreferenceUtils.get(VideoDetailsActivity.this, "app_token", ""), "");
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    ToastUtils.showMessage(VideoDetailsActivity.this, response.body().getMessage());
                                    getComment();
                                }
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {

                                call.cancel();
                            }
                        });

                    }
                }, VideoDetailsActivity.this, "发布");
                commentDialog2.show(VideoDetailsActivity.this.getSupportFragmentManager(), "comment");

                break;
            case R.id.picture_iv_back:              //3.底部返回上一级
                this.finish();
                break;
            case R.id.iv_college:                   //4.收藏按钮
                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews(type, id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            ToastUtils.showMessage(VideoDetailsActivity.this, response.body().getMessage());
                            if ("0001".equals(response.body().getStatus())) {
                                iv_college.setImageResource(R.mipmap.yantao_college_yes);
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {

                        call.cancel();
                    }
                });
                break;
            case R.id.iv_share:                     //5.分享按钮

                if (!"".equals(share_img)) {
                    Glide.with(this.getApplicationContext()).load(share_img).asBitmap().skipMemoryCache(true).into(new SimpleTarget<Bitmap>(80, 80) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (myBitmap == null) {
                                myBitmap = resource;
                            }
                            final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                                    {
                                            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                            SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                                    };
                            UMImage image = new UMImage(VideoDetailsActivity.this.getApplicationContext(), myBitmap);
                            UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                            web.setTitle(share_title);//标题
                            web.setThumb(image);  //缩略图
                            web.setDescription(share_des);//描述
                            new ShareAction(VideoDetailsActivity.this)
                                    .withMedia(web)
                                    .setDisplayList(displaylist)
                                    .setCallback(umShareListener)
                                    .open();
                        }
                    });
                } else {
                    if (myBitmap == null) {
                        myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);

                    }
                    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                            {
                                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                            };
                    UMImage image = new UMImage(VideoDetailsActivity.this.getApplicationContext(), myBitmap);
                    UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(VideoDetailsActivity.this)
                            .withMedia(web)
                            .setDisplayList(displaylist)
                            .setCallback(umShareListener)
                            .open();
                }
                break;
            case R.id.image_back:                   //6.顶部返回上一级

                this.finish();
                break;

        }
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(VideoDetailsActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(VideoDetailsActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(VideoDetailsActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(VideoDetailsActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {

                ToastUtils.showMessage(VideoDetailsActivity.this, "您未安装微信客户端，无法分享");
            }else {
                ToastUtils.showMessage(VideoDetailsActivity.this, "分享失败,msg:" + t.getMessage());

            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage(VideoDetailsActivity.this, "分享取消");

        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        int currentState = player.currentState;//2是正在播放状态||5是暂停状态
        LogUtil.i("state", "onRestart:state:" + currentState);
        if (currentState == 5) {
            player.startButton.performClick();//如果当前是暂停，那么这个方法就会播放，反之则暂停
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JCVideoPlayer.releaseAllVideos();
        int currentState = player.currentState;
        LogUtil.i("state", "onPause:state:" + currentState);
        if (currentState == 2) {
            player.startButton.performClick();//如果当前是暂停，那么这个方法就会播放，反之则暂停

        }

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }
        if (comentBeanList != null) {
            comentBeanList.clear();
            comentBeanList = null;
        }
        if (zanFlag != null) {
            zanFlag.clear();
            zanFlag = null;
        }
        if (newDetailBean != null) {
            newDetailBean = null;
        }
        if (myBitmap != null) {
            myBitmap.recycle();
            myBitmap = null;
        }
        if (player != null) {

            player.resetProgressAndTime();
            player.release();
//            player.cancelDismissControlViewTimer();
            player.destroyDrawingCache();
            player = null;
        }
        JCMediaManager.textureView = null;
        JCVideoPlayerStandard.releaseAllVideos();
        LogUtil.i("live", "onDestroy");
        UMShareAPI.get(this).release();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (JCVideoPlayer.backPress()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getComment() {
        pageNum = 1;
        Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String)SharedPreferenceUtils.get(this,"app_token",""),type, id, pageNum + "", pageSize + "", "");
        call2.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("kj", "1");
                    adapter.setComments(response.body().getData().getContent());

                } else {
                    LogUtil.i("kj", "2");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {
                LogUtil.i("kj", "3");

                call.cancel();
            }
        });
    }

    private void juBaoClick(final Button btn, final String juBaoId) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("jubao", "type:" + type + ",id:" + id + ",juBaoId:" + juBaoId);
                Call<DefaultBean> call = MyApplication.getNetApi().http_report((String) SharedPreferenceUtils.get(VideoDetailsActivity.this, "app_token", ""), "A", type, juBaoId, id);
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            if ("0001".equals(response.body().getStatus())) {
                                ToastUtils.showMessage(VideoDetailsActivity.this, "举报成功");

                            } else {
                                ToastUtils.showMessage(VideoDetailsActivity.this, response.body().getMessage());
                            }

                        } else {
                            ToastUtils.showMessage(VideoDetailsActivity.this, "异常错误1");
                        }
                        popupWindow_report.dismiss();

                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {
                        ToastUtils.showMessage(VideoDetailsActivity.this, "异常错误2");
                        popupWindow_report.dismiss();

                        call.cancel();

                    }
                });
            }
        });
    }


    private List<JuBaoBean.DataBean> data = null;

    public void report() {
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
                WindowManager.LayoutParams lp = VideoDetailsActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                VideoDetailsActivity.this.getWindow().setAttributes(lp);
            }
        });
    }

    private Animation animationEnd;

    private void endAnimation() {
        if (animationEnd == null) {
            animationEnd = AnimationUtil.createOutAnimation(VideoDetailsActivity.this, 500);

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
