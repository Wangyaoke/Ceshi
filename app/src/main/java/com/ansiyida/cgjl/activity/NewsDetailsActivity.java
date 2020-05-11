package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsDetailsAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.BuyListDetail;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.NewBean;
import com.ansiyida.cgjl.bean.NewDetailBean;
import com.ansiyida.cgjl.bean.ViewpointDetail;
import com.ansiyida.cgjl.bean.cgjl_bean.AdvertisementBean;
import com.ansiyida.cgjl.bean.cpml_detail_listbean;
import com.ansiyida.cgjl.bean.law_detail_listbean;
import com.ansiyida.cgjl.bean.purchaseDemand_detailn;
import com.ansiyida.cgjl.bean.qyml_detail_listbean;
import com.ansiyida.cgjl.bean.yzml_detail_listbean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.dialog.LoadingDialog_cellect;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends BaseActivity {
    @Bind(R.id.linear_title)
    LinearLayout linear_title;
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_title1)
    TextView text_title1;
    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Bind(R.id.recyclerView_newsDetailsActivity)
    RecyclerView recyclerView;
    @Bind(R.id.iv_college_top)
    ImageView iv_college;
    @Bind(R.id.iv_college_sucess)
    ImageView iv_college_sucess;

    private LoadingDialog_cellect loadingDialog;
    private NewsDetailsAdapter newsDetailsAdapter;
    private cpml_detail_listbean detailBean_CPML;
    private ViewpointDetail viewpointDetail;
    private yzml_detail_listbean detailBean_yzml;
    private qyml_detail_listbean detailBean_qyml;
    private purchaseDemand_detailn detailBean_cgxq;
    private ArrayList<NewBean> lists;
    private NewDetailBean detailBean;
    private law_detail_listbean detailBean_law;
    private BuyListDetail detailBean_buy;
    private boolean flag = true;
    private String type;
    private String id;
    private String idcollect;
    private String title;
    private String GENRE="";
    List<CommentBean.DataBean.ContentBean> comentBeanList;
    private int localPosition = 1;
    private int pageSize = 20;    //分页加载，每页页数
    private ArrayList<Integer> zanList;
    private String share_title = "";
    private String share_des = "";
    private String share_img = "";
    private Bitmap myBitmap;
    private GestureDetector mDetector;
    private ItemTouchHelper itemTouchHelper;
    private AnimationDrawable animationDrawable;
    private Intent intent;
    private AdvertisementBean advertisementBean;

    /**
     * 处理消息
     * @param msg 消息标记
     */

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //更新UI
            switch (msg.what) {
                case 1:
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    break;
            }
        };
    };
    private void todo(Message msg) {
//        webView.setVisibility(View.VISIBLE);
        int what = msg.what;
        if (what == 0) {
            //获取评论列表
            newsDetailsAdapter.setFanKui();
            getComment();
        } else if (what == 1) {
            //移动文章可是区域滚动
            if (flag) {
                recyclerView.smoothScrollToPosition(5);
                flag = !flag;
            } else {
                recyclerView.smoothScrollToPosition(0);
                flag = !flag;
            }
        } else if (what == 2) {
            //延时loading消失
            cancelAnimation();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_board_details;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        comentBeanList = new ArrayList<>();
        loadingDialog = new LoadingDialog_cellect();
        bindScrollLeftAndRight();
    }

    @Override
    protected void initData() {
        getAdvertisement();
         intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getStringExtra("id");
        idcollect= intent.getStringExtra("idcollect");
        title= intent.getStringExtra("title");
        setResult(200,intent);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        lists = new ArrayList<>();
        zanList = new ArrayList<>();
        detailBean = new NewDetailBean();
        viewpointDetail=new ViewpointDetail();
        detailBean_law=new law_detail_listbean();
         detailBean_buy=new BuyListDetail();
        detailBean_CPML=new cpml_detail_listbean();
        detailBean_qyml=new qyml_detail_listbean();
        detailBean_yzml=new yzml_detail_listbean();
        detailBean_cgxq=new purchaseDemand_detailn();
        newsDetailsAdapter = new NewsDetailsAdapter(this, lists,detailBean_law, detailBean,detailBean_buy, true, comentBeanList, zanList,type,this.getWindow(),detailBean_CPML,detailBean_yzml,detailBean_qyml,detailBean_cgxq,viewpointDetail);

        recyclerView.setAdapter(newsDetailsAdapter);
        if("P".equals(type)|"L".equals(type)|"VP".equals(type) |"ZK".equals(type))
        {
            if("true".equals(idcollect))
                iv_college.setImageResource(R.mipmap.yantao_college_yes);
            else
                iv_college.setImageResource(R.mipmap.icon_subscription);}
            else if("CP".equals(type))
            {
                text_title.setVisibility(View.GONE);
                text_title1.setText(title);
                linear_title.setVisibility(View.VISIBLE);
                text_title1.setVisibility(View.VISIBLE);
                iv_college.setVisibility(View.INVISIBLE);
                iv_share.setVisibility(View.INVISIBLE);
            }
            else if("YZ".equals(type))
            {
                text_title.setVisibility(View.GONE);
                text_title1.setText(title);
                linear_title.setVisibility(View.VISIBLE);
                text_title1.setVisibility(View.VISIBLE);
                iv_college.setVisibility(View.GONE);
                iv_share.setVisibility(View.GONE);
            }
            else if("QY".equals(type))
            {
                text_title.setVisibility(View.GONE);
                text_title1.setText(title);
                text_title1.setVisibility(View.VISIBLE);
                linear_title.setVisibility(View.VISIBLE);
                iv_college.setVisibility(View.GONE);
                iv_share.setVisibility(View.GONE);
            }
            else if("CX".equals(type)|"CX_dy".equals(type))
            {  if("true".equals(idcollect))
                iv_college.setImageResource(R.mipmap.yantao_college_yes);
            else
                iv_college.setImageResource(R.mipmap.icon_subscription);}

    }

    @Override
    protected void httpData() {
        httpDataa();
    }


    protected void httpDataa() {
        final long timeBegin = System.currentTimeMillis();
        if("P".equals(type)) {
            Call<BuyListDetail> call = MyApplication.getNetApi().get_buy_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));
        call.enqueue(new Callback<BuyListDetail>() {
            @Override
            public void onResponse(Call<BuyListDetail> call, Response<BuyListDetail> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                    LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                    BuyListDetail body = response.body();
                    if (body != null) {
                        BuyListDetail.DataBean data = body.getData();
                        if (data != null) {
                            newsDetailsAdapter.setBodyData_buy(body);
                            share_title=data.gettitle();
                            share_des=data.getcontent();
                            if("true".equals(body.getData().getisCollection()))
                            { idcollect="true";
                                    iv_college.setImageResource(R.mipmap.yantao_college_yes);}
                                else
                            {
                                idcollect="false";
                                    iv_college.setImageResource(R.mipmap.icon_subscription);}


                        }
                    }
                } else {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<BuyListDetail> call, Throwable t) {
                LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                cancelAnimation();
                call.cancel();
            }
        });
    }
       else if("VP".equals(type)| "ZK".equals(type))
        {
            Call<ViewpointDetail> call = MyApplication.getNetApi().get_viewpoint_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));
            call.enqueue(new Callback<ViewpointDetail>() {
                @Override
                public void onResponse(Call<ViewpointDetail> call, Response<ViewpointDetail> response) {
                    if (response.isSuccessful()) {
                        LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                        LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                        ViewpointDetail body = response.body();
                        if (body != null) {
                            ViewpointDetail.DataBean data = body.getData();
                            if (data != null) {
                                newsDetailsAdapter.setBodyData_viewpoint(body);
                                share_title=data.gettitle();
                                share_des=data.getcontent();
                                if("true".equals(body.getData().getisCollection()))
                                { idcollect="true";
                                    iv_college.setImageResource(R.mipmap.yantao_college_yes);}
                                else
                                {
                                    idcollect="false";
                                    iv_college.setImageResource(R.mipmap.icon_subscription);}

                            }
                        }
                                          } else {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                    }

                    call.cancel();
                }

                @Override
                public void onFailure(Call<ViewpointDetail> call, Throwable t) {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                    cancelAnimation();
                    call.cancel();
                }
            });
        }
      else  if("CX".equals(type)|"CX_dy".equals(type))
        {
            Call<purchaseDemand_detailn> call = MyApplication.getNetApi().get_purchaseDemand_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));
            call.enqueue(new Callback<purchaseDemand_detailn>() {
                @Override
                public void onResponse(Call<purchaseDemand_detailn> call, Response<purchaseDemand_detailn> response) {
                    if (response.isSuccessful()) {

                        LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                        LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                        purchaseDemand_detailn body = response.body();

                        if (body != null) {
                            purchaseDemand_detailn.DataBean data = body.getData();
                            if (data != null) {
                                Log.e("采购需求", "onResponse: "+ "走到这里了");
                                newsDetailsAdapter.setBodyData_buy(body);
                                share_title=data.getcurrent_bean().gettitle();
                                 share_des=data.getcurrent_bean().getquota();
                                                 }
                        }
                        //     handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                    }
                    // handler.sendEmptyMessageDelayed(2, 200);
                    call.cancel();
                }

                @Override
                public void onFailure(Call<purchaseDemand_detailn> call, Throwable t) {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                    cancelAnimation();
                    call.cancel();
                }
            });
        }
       else if("CP".equals(type))
        {

            Call<cpml_detail_listbean> call = MyApplication.getNetApi().get_product_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));
            call.enqueue(new Callback<cpml_detail_listbean>() {
                @Override
                public void onResponse(Call<cpml_detail_listbean> call, Response<cpml_detail_listbean> response) {
                    if (response.isSuccessful()) {
                        LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                        LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                        cpml_detail_listbean body = response.body();
                        if (body != null) {
                            cpml_detail_listbean.DataBean data = body.getData();
                            if (data != null) {

                                newsDetailsAdapter.setBodyData_cpml(body);


                            }
                        }
                    } else {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<cpml_detail_listbean> call, Throwable t) {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                    cancelAnimation();
                    call.cancel();
                }
            });
        }
        else if("YZ".equals(type))
        {
            Log.e("table", "httpData: "+id );
            Call<yzml_detail_listbean> call = MyApplication.getNetApi().get_proprietor_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));


            call.enqueue(new Callback<yzml_detail_listbean>() {
                @Override
                public void onResponse(Call<yzml_detail_listbean> call, Response<yzml_detail_listbean> response) {
                    if (response.isSuccessful()) {
                        LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                        LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                        yzml_detail_listbean body = response.body();
                        if (body != null) {
                            yzml_detail_listbean.DataBean data = body.getData();
                            if (data != null) {
                                newsDetailsAdapter.setBodyData_yzml(body);
                            }
                        }
                        //     handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");
                    }
                    // handler.sendEmptyMessageDelayed(2, 200);
                    call.cancel();
                }

                @Override
                public void onFailure(Call<yzml_detail_listbean> call, Throwable t) {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                    cancelAnimation();
                    call.cancel();
                }
            });
        }
        else if("QY".equals(type))
        {
            Call<qyml_detail_listbean> call = MyApplication.getNetApi().get_company_detail(id, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""));


            call.enqueue(new Callback<qyml_detail_listbean>() {
                @Override
                public void onResponse(Call<qyml_detail_listbean> call, Response<qyml_detail_listbean> response) {
                    if (response.isSuccessful()) {
                        LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                        LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                        qyml_detail_listbean body = response.body();
                        if (body != null) {
                            qyml_detail_listbean.DataBean data = body.getData();
                            if (data != null) {
                                newsDetailsAdapter.setBodyData_qyml(body);
                            }
                        }
                        //     handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                    }
                    // handler.sendEmptyMessageDelayed(2, 200);
                    call.cancel();
                }

                @Override
                public void onFailure(Call<qyml_detail_listbean> call, Throwable t) {
                    LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                    cancelAnimation();
                    call.cancel();
                }
            });
        }
    else
        {
            {
                Call<law_detail_listbean> call = MyApplication.getNetApi().get_law_detail(id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<law_detail_listbean>() {
                    @Override
                    public void onResponse(Call<law_detail_listbean> call, Response<law_detail_listbean> response) {
                        if (response.isSuccessful()) {
                            LogUtil.i("time", "”,总计用时:" + (System.currentTimeMillis() - timeBegin) + "毫秒");
                            LogUtil.i("json3", "-----------newsDetailsActivity---------isSuccessful");
                            law_detail_listbean body = response.body();
                            if (body != null) {
                                law_detail_listbean.DataBean data = body.getData();
                                if (data != null) {
                                    law_detail_listbean.DataBean.current_bean article = data.getcurrent_bean();
                                    if (article != null) {
                                        newsDetailsAdapter.setBodyData_law(body);
                                        share_title=data.getcurrent_bean().gettitle();
                                        share_des=data.getcurrent_bean().getcontent();
                                    }


                                }
                            }
                        } else {
                            LogUtil.i("json3", "-----------newsDetailsActivity---------unSuccessful");

                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<law_detail_listbean> call, Throwable t) {
                        LogUtil.i("json3", "-----------newsDetailsActivity---------onFailure");
                        cancelAnimation();
                        call.cancel();
                    }
                });
            }

        }
    }

    @Override
    protected void setClickListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                          }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        final int FLIP_DISTANCE=200;//控制手机差距
        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(e1!=null&&e2!=null){
                    if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                        newsDetailsAdapter.activityUseTabEnglishChinese();
                        return true;
                    }
                    if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                        newsDetailsAdapter.activityUseTabEnglishChinese();
                        return true;
                    }
                    if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                        Log.i("MYTAG", "向上滑...");
                        return true;
                    }
                    if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                        Log.i("MYTAG", "向下滑...");
                        return true;
                    }

                    Log.d("TAG", e2.getX() + " " + e2.getY());
                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lists != null) {
            lists.clear();
            lists = null;
        }
        if (zanList != null) {
            zanList.clear();
            zanList = null;
        }
        if (myBitmap != null) {
            myBitmap.recycle();
            myBitmap = null;
        }
        cancelAnimation();
        UMShareAPI.get(this).release();
    }

    @OnClick({R.id.iv_pingLun, R.id.iv_editPingLun, R.id.picture_iv_back, R.id.iv_college_top, R.id.iv_share,R.id.iv_back,R.id.iv_chinaToEnglish,R.id.iv_share2})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_pingLun:                   //1.点击查看其他用户评论按钮
                if (flag) {
                    recyclerView.scrollToPosition(3);
                } else {
                    recyclerView.scrollToPosition(1);
                }
           //     handler.sendEmptyMessageDelayed(1, 100);
                break;
            case R.id.iv_editPingLun:               //2.发表评论按钮

                CommentDialog2 commentDialog2 = new CommentDialog2("优质评论将会优先展示", "", 300, new CommentDialog2.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        LogUtil.i("biaoqing", "text:" + inputText);
                        Call<DefaultBean> call = MyApplication.getNetApi().saveComment(id, type, inputText, (String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""), "");
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    ToastUtils.showMessage(NewsDetailsActivity.this, response.body().getMessage());
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
                }, NewsDetailsActivity.this, "发布");
                commentDialog2.show(NewsDetailsActivity.this.getSupportFragmentManager(), "comment");

                break;
            case R.id.picture_iv_back:              //3.返回上一级
                this.finish();
                break;
                case R.id.iv_college_top:                   //4.收藏按钮
                  //
                    if("P".equals(type))
                        GENRE="purchaseInfo";
                    else if("CX".equals(type) || "CX_dy".equals(type))
                        GENRE="purchaseDemand";
                    else if ("VP".equals(type))
                        GENRE = "viewpointInfo";
                    else
                        GENRE="policyInfo";

                    String vistor = (String) SharedPreferenceUtils.get(this, "vistor", "");
                    if(vistor.equals("true")) {
                        if ("false".equals(idcollect)) {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews((String) SharedPreferenceUtils.get(this, "app_token", ""), GENRE, id);
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        // ToastUtils.showMessage(NewsDetailsActivity.this, response.body().getMessage());
                                        if ("200".equals(response.body().getStatus())) {
                                            loadingDialog.showDialog(NewsDetailsActivity.this, "");
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
                                            iv_college.setImageResource(R.mipmap.yantao_college_yes);
                                            iv_college_sucess.setVisibility(View.VISIBLE);
                                            try {
                                                Thread.sleep(500);
                                            } catch (Exception e) {
                                                e.toString();
                                            }
                                            iv_college_sucess.setVisibility(View.GONE);
                                            idcollect = "true";
                                            SharedPreferenceUtils.put(NewsDetailsActivity.this,"ClickCollege",idcollect);
                                        } else
                                            ToastUtils.showMessage(NewsDetailsActivity.this, "登录后才能收藏");
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {
                                    call.cancel();
                                }
                            });
                        } else {
                            Call<DefaultBean> call = MyApplication.getNetApi().collegeNews_del((String) SharedPreferenceUtils.get(this, "app_token", ""), GENRE, id);
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        ToastUtils.showMessage(NewsDetailsActivity.this, response.body().getMessage());
                                        if ("200".equals(response.body().getStatus())) {
                                            SharedPreferenceUtils.put(NewsDetailsActivity.this,"ClickCollege","false");
                                            iv_college.setImageResource(R.mipmap.icon_subscription_default3x);
                                            ToastUtils.showMessage(NewsDetailsActivity.this, "成功取消收藏！");
                                        }
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });
                            idcollect = "false";
                            //   iv_college.setImageResource(R.mipmap.icon_subscription_default3x);
                            //  ToastUtils.showMessage(this, "取消收藏成功");
                        }
                    }
                    else
                        ToastUtils.showMessage(NewsDetailsActivity.this, "登录后才能收藏");
                break;
            case R.id.iv_share:                     //5.分享按钮
                Log.e("sharee", "shareId: "+id );
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
                            UMImage image = new UMImage(NewsDetailsActivity.this.getApplicationContext(), myBitmap);
                      //      UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                            UMWeb web=new UMWeb("");
                            if("P".equals(type))
                                web = new UMWeb("https://cg.calcnext.com/detail#/?type=purchaseInfo&id" + id);
                            else  if("CX".equals(type)|"CX_dy".equals(type))
                                web = new UMWeb("https://cg.calcnext.com/detail#/?type=purchaseDemand&id=" + id);
                            else
                                web = new UMWeb("https://cg.calcnext.com/detail#/?type=policyInfo&id=" + id);


                            ToastUtils.showMessage(NewsDetailsActivity.this,web.toString());
                            web.setTitle(share_title);//标题
                            web.setThumb(image);  //缩略图
                            web.setDescription(share_des);//描述
                            new ShareAction(NewsDetailsActivity.this)
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
                                    SHARE_MEDIA.WEIXIN,
                                    SHARE_MEDIA.WEIXIN_CIRCLE,
                                    SHARE_MEDIA.SINA,
                                    SHARE_MEDIA.QQ,
                                    SHARE_MEDIA.QZONE
                            };
                    UMImage image = new UMImage(NewsDetailsActivity.this.getApplicationContext(), myBitmap);
                 // UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                    UMWeb web=new UMWeb("");
                    if("P".equals(type))
                        web = new UMWeb("https://cg.calcnext.com/detail#/?type=purchaseInfo&id=" + id);
                        else  if("CX".equals(type)|"CX_dy".equals(type))
                        web = new UMWeb("https://cg.calcnext.com/detail#/?type=purchaseDemand&id=" + id);
                    else
                        //正常分享
                        web = new UMWeb("https://cg.calcnext.com/detail/#/?type=viewpoint&id=" + id);
                    String s = web.toString();
                    Log.e("share", "click: "+s);
                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(NewsDetailsActivity.this)
                            .withMedia(web)
                            .setDisplayList(displaylist)
                            .setCallback(umShareListener)
                            .open();
                }
                break;
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.iv_chinaToEnglish:       //7.中英文切换按钮
                newsDetailsAdapter.activityUseTabEnglishChinese();
                break;
            case R.id.iv_share2:               //8.右下角分享按钮

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
                            UMImage image = new UMImage(NewsDetailsActivity.this.getApplicationContext(), myBitmap);
                            UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);
                            web.setTitle(share_title);//标题
                            web.setThumb(image);  //缩略图
                            web.setDescription(share_des);//描述
                            new ShareAction(NewsDetailsActivity.this)
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
                    UMImage image = new UMImage(NewsDetailsActivity.this.getApplicationContext(), myBitmap);
                    UMWeb web = new UMWeb(Constant.URL + "web/detail/forword/" + type + "/" + id);

                    web.setTitle(share_title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(share_des);//描述
                    new ShareAction(NewsDetailsActivity.this)
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
            Log.e("分享", "onResult: "+platform.toString() );
            ToastUtils.showMessage(NewsDetailsActivity.this, "分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(NewsDetailsActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(NewsDetailsActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(NewsDetailsActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                ToastUtils.showMessage(NewsDetailsActivity.this, "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(NewsDetailsActivity.this, "分享失败,msg:" + t.getMessage());

            }
            LogUtil.i("share", "分享失败,msg:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage(NewsDetailsActivity.this,"取消分享");
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void getComment() {
        Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String) SharedPreferenceUtils.get(this, "app_token", ""), type, id, "1", pageSize + "", "");
        call2.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("kj", "1");
                    newsDetailsAdapter.setCommentBeans(response.body().getData().getContent());
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
                WindowManager.LayoutParams lp = NewsDetailsActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                NewsDetailsActivity.this.getWindow().setAttributes(lp);
            }
        });
    }

    private void juBaoClick(final Button btn, final String juBaoId) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("jubao", "type:" + type + ",id:" + id + ",juBaoId:" + juBaoId);
                Call<DefaultBean> call = MyApplication.getNetApi().http_report((String) SharedPreferenceUtils.get(NewsDetailsActivity.this, "app_token", ""), "A", type, juBaoId, id);
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            if ("0001".equals(response.body().getStatus())) {
                                ToastUtils.showMessage(NewsDetailsActivity.this, "举报成功");

                            } else {
                                ToastUtils.showMessage(NewsDetailsActivity.this, response.body().getMessage());
                            }

                        } else {
                            ToastUtils.showMessage(NewsDetailsActivity.this, "异常错误1");
                        }
                        popupWindow_report.dismiss();

                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {
                        ToastUtils.showMessage(NewsDetailsActivity.this, "异常错误2");
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
            animationEnd = AnimationUtil.createOutAnimation(NewsDetailsActivity.this, 500);

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
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
    private   void bindScrollLeftAndRight(){
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (newsDetailsAdapter.isScroll()){
                    //拖拽时支持的方向向上向下
                    int dragFlags= ItemTouchHelper.UP| itemTouchHelper.DOWN;
                    //滑动的时候支持的方向为左右
                    int swipeFlags=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                    //必须调用makeMovementFlags（）方法通知 TouchHelper支持的种类
                    if (viewHolder instanceof NewsDetailsAdapter.WebViewHolder){
                        return makeMovementFlags(dragFlags,swipeFlags);
                    }else {
                        return 0;
                    }
                }else {
                    return 0;
                }

            }
            //上下拖动回调次方法。
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            //左右滑动回调此方法。
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    newsDetailsAdapter.activityUseTabEnglishChinese();
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void cancelAnimation(){
        if (animationDrawable != null) {
            animationDrawable.stop();
          //  iv_animation.setVisibility(View.GONE);

        }
    }
    private void getAdvertisement() {
        int max=100;
        int min=0;
        Random random = new Random();
        int num = random.nextInt(max)%(max-min+1) + min;
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.URL+"taobao/ad?keyword=&pageSize=1&pageNum="+num)
                .build();

        httpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Toast.makeText(NewsDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if(response.isSuccessful()) {
                    String getData = response.body().string();
                    if(getData.isEmpty()){
                        SharedPreferenceUtils.put(NewsDetailsActivity.this, "Advertisement", "");
                    }else {
                        SharedPreferenceUtils.put(NewsDetailsActivity.this, "Advertisement", getData);
                    }
                }else{
                    SharedPreferenceUtils.put(NewsDetailsActivity.this,"Advertisement","");
                }
            }
        });
    }

}
