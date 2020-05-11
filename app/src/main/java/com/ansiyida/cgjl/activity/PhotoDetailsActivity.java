package com.ansiyida.cgjl.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.NewDetailBean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DpPxTools;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.photoview.HackyViewPager;
import com.ansiyida.cgjl.view.photoview.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDetailsActivity extends BaseActivity {

    //图片资源
    private String[] photoArray = null;

    //图片的文字描述，在实际项目中这个是从后台获取到的数据，在这里模拟一下
    private String[] msgArray = null;
    //photoview里面的自定义的方法  重写了onInterceptTouchEvent  onTouchEvent来处理事件
    private boolean moveTop = false;
    //返回按钮
    private float rawY = 0;
    private boolean isMove = false;
    private int initBottom;
    private int endRaw = 0;
    private int endBottom;
    @Bind(R.id.all_click)
    TextView all_click;
    @Bind(R.id.picture_iv_back)
    ImageView picture_iv_back;
    @Bind(R.id.iv_college)
    ImageView iv_college;
    @Bind(R.id.photo_vp)
    HackyViewPager mViewPager;
    @Bind(R.id.tv_soure)
    TextView soure;
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
    //---------------------控制显示隐藏-------------------------
    @Bind(R.id.msg)
    TextView msg;                       //文本展示
    @Bind(R.id.relative_user)
    RelativeLayout relative_user;       //头部信息
    @Bind(R.id.linear_tab)
    LinearLayout linear_tab;            //标签
    @Bind(R.id.linear_bottom)
    LinearLayout linear_bottom;         //底部评论
    @Bind(R.id.tv_save)
    TextView tv_save;

    @Bind(R.id.tv_talkCount)
    TextView tv_talkCount;
    //--------------------控制显示隐藏---------------------------
    private boolean flag = false;
    private boolean animFlag = true;
    private final MyHandler handler = new MyHandler(this);
    private AlphaAnimation animation;
    private AlphaAnimation animation2;
    private int animationDuration = 500;
    private int localPosition = 0;
    private String id;
    private String type;
    private int commentLength = -1;
    private String share_title = "";
    private String share_des = "";
    private String share_img = "";
    private Bitmap myBitmap;

    private static class MyHandler extends Handler {
        private final WeakReference<PhotoDetailsActivity> mActivity;

        public MyHandler(PhotoDetailsActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
            mActivity.get().todo(msg);
        }
    }

    /**
     * 处理消息
     *
     * @param msg 消息标记
     */
    private void todo(Message msg) {
        int what = msg.what;
        switch (what) {
            case 0:                 //1.显示与隐藏
                if (flag) {
                    setVisble(animFlag);
                    animFlag = !animFlag;
                }
                break;
            case 1:                 //保存按钮显示
                tv_save.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_news_view_pager;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getStringExtra("id");
        animation = new AlphaAnimation(1, 0);
        animation.setDuration(animationDuration);
        animation.setFillAfter(true);
        animation2 = new AlphaAnimation(0, 1);
        animation2.setDuration(animationDuration);
        animation2.setFillAfter(true);

    }

    @Override
    protected void initData() {

        // 绑定适配器
        //设置可以滑动监听(viewpager改变的时候调用)
        mViewPager.addOnPageChangeListener(new ViewPagerChangeListener());
//        mViewPager.setCurrentItem(0);
        //设置返回监听事件
        initBottom = DpPxTools.dip2px(this, 47);
        LogUtil.i("bottom", "bottom:" + initBottom);


    }

    @Override
    protected void httpData() {
        Call<NewDetailBean> call = MyApplication.getNetApi().getNewDetail(type, id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        call.enqueue(new Callback<NewDetailBean>() {
            @Override
            public void onResponse(Call<NewDetailBean> call, Response<NewDetailBean> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("json3", "-----------PhotoDetailsActivity---------isSuccessful");
                    NewDetailBean.DataBeanX.ArticleBean article = response.body().getData().getArticle();
                    String jca_strand_id = article.getJca_strand_id();
                    setTab(jca_strand_id);
                    String jca_title = article.getJca_title();
                    if (jca_title != null && !"".equals(jca_title)) {
                        share_title = jca_title;
                    }
                    photoArray = article.getPathcontent();
                    msgArray = article.getTextcontent();
                    share_des = msgArray[0];
                    share_img=photoArray[0];
                    String iscollectar = response.body().getData().getIscollectar();
                    if (iscollectar != null && "Y".equals(iscollectar)) {
                        iv_college.setImageResource(R.mipmap.photo_college_yes);
                    }
                    int com_num = article.getCom_num();
                    if (com_num>0){
                        if (com_num>10000){
                            tv_talkCount.setText(com_num/10000+"万");
                        }else {
                            tv_talkCount.setText(com_num+"");
                        }
                        tv_talkCount.setVisibility(View.VISIBLE);
                    }else {
                        tv_talkCount.setVisibility(View.GONE);
                    }
                    if (msgArray != null && msgArray.length > 0) {
                        msg.setText((localPosition + 1) + "/" + photoArray.length + "  " + msgArray[localPosition]);
                    } else {
                        msg.setText((localPosition + 1) + "/" + photoArray.length);
                    }
                    msg.post(new Runnable() {
                        @Override
                        public void run() {
                            int lineCount = msg.getLineCount();
                            LogUtil.i("lineCount", "position:" + 0 + ",lineCount:" + lineCount);
                            if (lineCount > 6) {
                                isMove = true;
                                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                                int endB = DpPxTools.dip2px(PhotoDetailsActivity.this, (lineCount - 6) * 16);
                                endBottom = initBottom - endB;
                                lp.bottomMargin = endBottom;
                                endRaw = endBottom;
                                msg.setLayoutParams(lp);
                            } else {
                                isMove = false;
                                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                                lp.bottomMargin = initBottom;
                                msg.getBottom();
                                msg.setLayoutParams(lp);
                            }
                        }
                    });
                    soure.setText(article.getJca_soure());
                    mViewPager.setAdapter(new ViewPagerAdapter());


                } else {
                    LogUtil.i("json3", "-----------PhotoDetailsActivity---------unSuccessful");

                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<NewDetailBean> call, Throwable t) {
                LogUtil.i("json3", "-----------PhotoDetailsActivity---------onFailure");

                call.cancel();
            }
        });
    }

    @Override
    protected void setClickListener() {

        msg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        rawY = event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int speed = 2;    //设置速度，最小为1,数字越大滑动越慢
                        if (isMove) {
                            if (rawY > event.getRawY()) {
                                int num = (int) (rawY - event.getRawY());
                                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                                num = num / speed + endRaw;
                                if (num < initBottom) {
                                    lp.bottomMargin = num;
                                    endRaw = num;
                                } else {
                                    lp.bottomMargin = initBottom;
                                    endRaw = initBottom;

                                }

                                msg.setLayoutParams(lp);
//                                moveTop = !moveTop;

                            } else if (rawY < event.getRawY()) {
                                int num = (int) (rawY - event.getRawY());

                                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                                num = num / speed + endRaw;
                                if (num > endBottom) {
                                    lp.bottomMargin = num;
                                    endRaw = num;

                                } else {
                                    lp.bottomMargin = endBottom;
                                    endRaw = endBottom;
                                }
                                msg.setLayoutParams(lp);
//                                moveTop = !moveTop;
//                               LogUtil.i("touch","我要往下了,间距："+(rawY-event.getRawY()));


                            }
//                            LogUtil.i("touch", "endNum:" +endRaw);


                        }

                        break;
                }
                return true;
            }
        });

        all_click.setOnTouchListener(new View.OnTouchListener() {

            private long time1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.sendEmptyMessageDelayed(0, 200);
                        flag = true;
//                        LogUtil.i("action","ACTION_DOWN");
                        break;

                }
                return false;
            }
        });




    }

    @OnClick({R.id.picture_iv_back, R.id.iv_editPingLun, R.id.tv_save, R.id.iv_college, R.id.iv_pingLun, R.id.iv_share})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.picture_iv_back:              //1.返回上一级
                PhotoDetailsActivity.this.finish();
                PhotoDetailsActivity.this.overridePendingTransition(R.anim.defaul2, R.anim.defaul);
                break;
            case R.id.iv_editPingLun:                  //2.写评论
                CommentDialog2 commentDialog2 = new CommentDialog2("优质评论将会优先展示", "", 300, new CommentDialog2.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        Call<DefaultBean> call = MyApplication.getNetApi().saveComment(id, type, inputText, (String) SharedPreferenceUtils.get(PhotoDetailsActivity.this, "app_token", ""), "");
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    ToastUtils.showMessage(PhotoDetailsActivity.this, response.body().getMessage());
                                    commentLength = -1;
                                }
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {

                                call.cancel();
                            }
                        });

                    }
                }, PhotoDetailsActivity.this, "发布");
                commentDialog2.show(PhotoDetailsActivity.this.getSupportFragmentManager(), "comment");

                break;
            case R.id.tv_save:                      //3.保存图片

//                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),photoArray[localPosition]);
//                MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
//                bitmap.recycle();
                ToastUtils.showMessage(this, "保存成功");
                Glide.with(PhotoDetailsActivity.this).load(photoArray[localPosition]).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ToastUtils.showMessage(PhotoDetailsActivity.this, "保存成功");
                        MediaStore.Images.Media.insertImage(PhotoDetailsActivity.this.getContentResolver(), resource, "title", "description");
                        resource.recycle();
                    }
                });
                break;
            case R.id.iv_college:                   //4.底部收藏按钮

                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews(type, id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            ToastUtils.showMessage(PhotoDetailsActivity.this, response.body().getMessage());
                            if ("0001".equals(response.body().getStatus())) {
                                iv_college.setImageResource(R.mipmap.photo_college_yes);
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
            case R.id.iv_pingLun:                   //5.底部评论按钮
                if (commentLength != -1) {
                    LogUtil.i("comment", "if");
                    if (commentLength > 0) {
                        Intent intent = new Intent(PhotoDetailsActivity.this, CommentActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("type", type);
                        startActivity(intent);
                    } else {
                        ToastUtils.showMessage(this, "暂无评论");
                    }
                } else {
                    LogUtil.i("comment", "else");
                    Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String)SharedPreferenceUtils.get(this,"app_token",""),type, id, "1", "20", "");
                    call2.enqueue(new Callback<CommentBean>() {
                        @Override
                        public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                            if (response.isSuccessful()) {
                                LogUtil.i("kj", "1");
                                List<CommentBean.DataBean.ContentBean> content = response.body().getData().getContent();
                                if (content != null) {
                                    commentLength = content.size();
                                    if (commentLength > 0) {
                                        Intent intent = new Intent(PhotoDetailsActivity.this, CommentActivity.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("type", type);
                                        startActivity(intent);
                                    } else {
                                        ToastUtils.showMessage(PhotoDetailsActivity.this, "暂无评论");

                                    }
                                }


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


                break;
            case R.id.iv_share:                   //6.底部分享按钮
                if (!"".equals(share_img)) {
                    Glide.with(this.getApplicationContext()).load(share_img).asBitmap().into(new SimpleTarget<Bitmap>(80, 80) {
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
                            UMImage image = new UMImage(PhotoDetailsActivity.this.getApplicationContext(), myBitmap);
                            UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                            web.setTitle(share_title);//标题
                            web.setThumb(image);  //缩略图
                            web.setDescription(share_des);//描述
                            new ShareAction(PhotoDetailsActivity.this)
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
                    UMImage image = new UMImage(PhotoDetailsActivity.this.getApplicationContext(), myBitmap);
                    UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(PhotoDetailsActivity.this)
                            .withMedia(web)
                            .setDisplayList(displaylist)
                            .setCallback(umShareListener)
                            .open();
                }


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
            ToastUtils.showMessage(PhotoDetailsActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(PhotoDetailsActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(PhotoDetailsActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(PhotoDetailsActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {

                ToastUtils.showMessage(PhotoDetailsActivity.this, "您未安装微信客户端，无法分享");
            }else {
                ToastUtils.showMessage(PhotoDetailsActivity.this, "分享失败,msg:" + t.getMessage());

            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage(PhotoDetailsActivity.this, "分享取消");

        }
    };
    // 查看大图viewpager适配器
    private class ViewPagerAdapter extends PagerAdapter {

        @SuppressLint("InflateParams")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.news_picture_item, null);
            PhotoView picture_iv_item = (PhotoView) view.findViewById(R.id.picture_iv_item);

            // 给imageview设置一个tag，保证异步加载图片时不会乱序
//            picture_iv_item.setImageResource(photoArray[position]);
            Glide.with(PhotoDetailsActivity.this).load(photoArray[position]).skipMemoryCache(true).into(picture_iv_item);
            //把view加载到父容器中
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return photoArray.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }


    // viewpager切换监听器
    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrollStateChanged(int arg0) {
            flag = false;
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(final int arg0) {
            localPosition = arg0;
            //设置文字
            String str = "";
            if (msgArray != null && msgArray.length > 0) {
                msg.setText((arg0 + 1) + "/" + photoArray.length + "  " + msgArray[arg0]);
            } else {
                msg.setText((arg0 + 1) + "/" + photoArray.length);
            }
            if (msgArray != null && msgArray.length > 0) {
                msg.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = msg.getLineCount();
                        LogUtil.i("lineCount", "position:" + arg0 + ",lineCount:" + lineCount);
                        if (lineCount > 6) {
                            isMove = true;
                            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                            int endB = DpPxTools.dip2px(PhotoDetailsActivity.this, (lineCount - 6) * 16);
                            endBottom = initBottom - endB;
                            lp.bottomMargin = endBottom;
                            endRaw = endBottom;
                            msg.setLayoutParams(lp);
                        } else {
                            isMove = false;
                            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msg.getLayoutParams();
                            lp.bottomMargin = initBottom;
                            msg.getBottom();
                            msg.setLayoutParams(lp);
                        }
                    }
                });

            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PhotoDetailsActivity.this.finish();
        PhotoDetailsActivity.this.overridePendingTransition(R.anim.defaul2, R.anim.defaul);

    }

    private void setVisble(boolean boo) {
        //msg、relative_user、linear_tab、linear_bottom

        if (boo) {
            msg.startAnimation(animation);
            relative_user.startAnimation(animation);
            linear_tab.startAnimation(animation);
            linear_bottom.startAnimation(animation);
//            handler.sendEmptyMessageDelayed(1,animationDuration);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    msg.setVisibility(View.GONE);
                    relative_user.setVisibility(View.GONE);
                    linear_tab.setVisibility(View.GONE);
                    linear_bottom.setVisibility(View.GONE);
                    tv_save.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {

            msg.startAnimation(animation2);
            relative_user.startAnimation(animation2);
            linear_tab.startAnimation(animation2);
            linear_bottom.startAnimation(animation2);
            tv_save.setVisibility(View.GONE);

            animation2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    msg.setVisibility(View.VISIBLE);
                    relative_user.setVisibility(View.VISIBLE);
                    linear_tab.setVisibility(View.VISIBLE);
                    linear_bottom.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (myBitmap!=null){
            myBitmap.recycle();
            myBitmap=null;
        }
        if (animation!=null){
            animation.cancel();
            animation=null;
        }
        if (animation2!=null){
            animation2.cancel();
            animation2=null;
        }
        if (photoArray!=null){
            photoArray=null;
        }
        if (msgArray!=null){
            msgArray=null;
        }
        UMShareAPI.get(this).release();
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

    private void setTab(String jca_soure) {
        if (jca_soure != null && !"".equals(jca_soure) && !"null".equals(jca_soure)) {
            jca_soure = jca_soure.replace("，", ",");
            String[] split = jca_soure.split(",");
            int length = split.length;
            for (int x = 0; x < length; x++) {
                if (x == 0) {
                    tab1.setText(split[x]);
                    tab1.setVisibility(View.VISIBLE);
                    setTextSize(tab1);
                } else if (x == 1) {
                    tab2.setText(split[x]);
                    tab2.setVisibility(View.VISIBLE);
                    setTextSize(tab2);
                } else if (x == 2) {
                    tab3.setText(split[x]);
                    tab3.setVisibility(View.VISIBLE);
                    setTextSize(tab3);
                } else if (x == 3) {
                    tab4.setText(split[x]);
                    tab4.setVisibility(View.VISIBLE);
                    setTextSize(tab4);
                } else if (x == 4) {
                    tab5.setText(split[x]);
                    tab5.setVisibility(View.VISIBLE);
                    setTextSize(tab5);
                }
            }
        }
    }
}
