package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ansiyida.cgjl.File.PayResult;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.MessageBean;
import com.ansiyida.cgjl.bean.WXPayBean;
import com.ansiyida.cgjl.bean.buyvipaliPaybean;
import com.ansiyida.cgjl.bean.buyvipbean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyvipActivity extends BaseActivity {
    @Bind(R.id.select)
    ImageView select;
    @Bind(R.id.select_wx)
    ImageView select_wx;
    @Bind(R.id.image_back)
    ImageView back;
    @Bind(R.id.money)
    TextView money;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.month_one_text1)
    TextView month_one_text1;
    @Bind(R.id.month_one_text2)
    TextView month_one_text2;
    @Bind(R.id.month_six_text1)
    TextView month_six_text1;
    @Bind(R.id.month_six_text2)
    TextView month_six_text2;
    @Bind(R.id.year_one_text1)
    TextView year_one_text1;
    @Bind(R.id.year_one_text2)
    TextView year_one_text2;
    @Bind(R.id.month_one)
    LinearLayout month_one;
    @Bind(R.id.month_six)
    LinearLayout month_six;
    @Bind(R.id.year_one)
    LinearLayout year_one;
    @Bind(R.id.buy_vip)
    TextView buy_vip;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    private ArrayList<MessageBean.DataBean.ListBean> list;
   // private LoadingDialog loadingDialog;
    private int payMonth;
    private int price;
    private WXPayBean wxPayBean;
    private IWXAPI api;
    private String pay_type="";
    private String pay_price="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(BuyvipActivity.this,"Android购买会员","BuyvipActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.buy_vip;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        title.setText("购买会员");
        text_putOut.setText("购买记录");
        back.setVisibility(View.VISIBLE);
        wxPayBean=new WXPayBean();
        api = WXAPIFactory.createWXAPI(this, "wx8855ca7656c29232");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }


    @Override
    protected void setClickListener() {



    }
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //更新UI
            switch (msg.what)
            {
                case 1:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();
                    //Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000"))
                    {
                        Toast.makeText(BuyvipActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();}
                    else
                        { Toast.makeText(BuyvipActivity.this, "支付失败", Toast.LENGTH_SHORT).show(); }

                    break;
            }
        };
    };
    protected void select() {
        int BlueColor = getResources().getColor(R.color.color_blue);
        month_one_text1.setTextColor(BlueColor);
        month_one_text2.setTextColor(BlueColor);
        month_one.setBackgroundResource(R.drawable.shape_detail_like_bg_1);
        month_six_text1.setTextColor(BlueColor);
        month_six_text2.setTextColor(BlueColor);
        month_six.setBackgroundResource(R.drawable.shape_detail_like_bg_1);
        year_one_text1.setTextColor(BlueColor);
        year_one_text2.setTextColor(BlueColor);
        year_one.setBackgroundResource(R.drawable.shape_detail_like_bg_1);
    }
    @OnClick({R.id.text_putOut,R.id.image_back,R.id.text_pay,R.id.text_wx_pay,R.id.buy_vip,R.id.month_one,R.id.month_six,R.id.year_one,R.id.select,R.id.select_wx})
    public void click(View view){
        int WhiteColor = getResources().getColor(R.color.white);
        switch (view.getId()){
            case R.id.select:           //1.返回上一级
                select.setImageResource(R.mipmap.select_yes);
                select_wx.setImageResource(R.mipmap.select_none);
                pay_type="zfb";
                break;
            case R.id.select_wx:           //1.返回上一级

                select_wx.setImageResource(R.mipmap.select_yes);
                select.setImageResource(R.mipmap.select_none);
                pay_type="wx";
                break;
            case R.id.text_pay:           //1.返回上一级

                select.setImageResource(R.mipmap.select_yes);
                select_wx.setImageResource(R.mipmap.select_none);
                pay_type="zfb";
                break;
            case R.id.text_wx_pay:           //1.返回上一级

                select_wx.setImageResource(R.mipmap.select_yes);
                select.setImageResource(R.mipmap.select_none);
                pay_type="wx";
                break;
            case R.id.text_putOut:           //1.返回上一级
                Intent intent_college = new Intent(this, BuyHistoryActivity.class);
                intent_college.putExtra("titleName", "购买记录");
                startActivityForResult(intent_college, ActivityCodeUtil.COLLEGEACTIVITY);
                break;
            case R.id.month_one:           //1.返回上一级
                pay_price="12";
                payMonth=1;
                        price=12;
                select();
                month_one_text1.setTextColor(WhiteColor);
                month_one_text2.setTextColor(WhiteColor);
                month_one.setBackgroundResource(R.drawable.shape_blue);
                money.setText("12元");
                break;
            case R.id.month_six:           //1.返回上一级
                pay_price="60";
                payMonth=6;
                price=50;
                money.setText("60元");
                select();
                month_six_text1.setTextColor(WhiteColor);
                month_six_text2.setTextColor(WhiteColor);
                month_six.setBackgroundResource(R.drawable.shape_blue);
                break;
            case R.id.year_one:           //1.返回上一级
                pay_price="98";
                payMonth=12;
                price=98;
                money.setText("98元");
                select();
                year_one_text1.setTextColor(WhiteColor);
                year_one_text2.setTextColor(WhiteColor);
                year_one.setBackgroundResource(R.drawable.shape_blue);
                break;
            case R.id.image_back:           //1.返回上一级
                this.finish();
                break;
            case R.id.buy_vip:           //1.返回上一级
                if(!"".equals(pay_type)&&!"".equals(pay_price)) {
                    if ("wx".equals(pay_type)) {
                        if ("true".equals(SharedPreferenceUtils.get(this, "vistor", ""))) {
                            Call<buyvipbean> call = MyApplication.getNetApi().buyvip((String) SharedPreferenceUtils.get(BuyvipActivity.this, "app_token", ""), payMonth+"", pay_price);
                            call.enqueue(new Callback<buyvipbean>() {
                                @Override
                                public void onResponse(Call<buyvipbean> call, Response<buyvipbean> response) {
                                    if (response.isSuccessful()) {
                                        buyvipbean body = response.body();
                                        if (body.getData() != null) {
                                            wxPayBean.setAppid(body.getData().getappid());
                                            wxPayBean.setNonce_str(body.getData().getnoncestr());
                                            wxPayBean.setpackage_vip(body.getData().getpackage());
                                            wxPayBean.setpartnerid(body.getData().getpartnerid());
                                            wxPayBean.setprepayid(body.getData().getprepayid());
                                            wxPayBean.setsign(body.getData().getsign());
                                            wxPayBean.settimestamp(body.getData().gettimestamp());

                                            PayReq req = new PayReq();
                                            req.appId = wxPayBean.getappid();
                                            req.partnerId = wxPayBean.getpartnerid();
                                            req.prepayId = wxPayBean.getprepayid();
                                            req.nonceStr = wxPayBean.getnonce_str();
                                            req.timeStamp = wxPayBean.gettimestamp();
                                            req.packageValue = wxPayBean.getpackage_vip();
                                            req.sign = wxPayBean.getsign();
                                            api.sendReq(req);
                                            finish();
                                        } else {
                                            ToastUtils.showMessage(BuyvipActivity.this, body.getMessage());
                                        }

                                    } else {
                                        ToastUtils.showMessage(BuyvipActivity.this, "未知错误");

                                    }

                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<buyvipbean> call, Throwable t) {
                                    ToastUtils.showMessage(BuyvipActivity.this, "未知错误");

                                    call.cancel();

                                }
                            });
                        } else
                            this.startActivityForResult(new Intent(this, LoginActivity.class), ActivityCodeUtil.LOGINACTIVITY);
                    } else if ("zfb".equals(pay_type)) {
                        if ("true".equals(SharedPreferenceUtils.get(this, "vistor", ""))) {
                            Call<buyvipaliPaybean> call = MyApplication.getNetApi().buyvip_aliPay((String) SharedPreferenceUtils.get(BuyvipActivity.this, "app_token", ""), payMonth+"", pay_price);
                            call.enqueue(new Callback<buyvipaliPaybean>() {
                                @Override
                                public void onResponse(Call<buyvipaliPaybean> call, Response<buyvipaliPaybean> response) {
                                    if (response.isSuccessful()) {
                                        final buyvipaliPaybean body = response.body();
                                        if (body.getData() != null) {
                                            Runnable payRunnable = new Runnable() {

                                                @Override
                                                public void run() {
                                                    PayTask alipay = new PayTask(BuyvipActivity.this);
                                                    Map<String, String> result = alipay.payV2(body.getData(), true);
                                                    Message msg = new Message();
                                                    msg.what = 1;
                                                    msg.obj = result;
                                                    mHandler.sendMessage(msg);
                                                }
                                            };
                                            // 必须异步调用
                                            Thread payThread = new Thread(payRunnable);
                                            payThread.start();

                                        } else {
                                            ToastUtils.showMessage(BuyvipActivity.this, body.getMessage());
                                        }

                                    } else {
                                        ToastUtils.showMessage(BuyvipActivity.this, "未知错误");

                                    }

                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<buyvipaliPaybean> call, Throwable t) {
                                    ToastUtils.showMessage(BuyvipActivity.this, "未知错误");

                                    call.cancel();

                                }
                            });
                        } else
                        this.startActivityForResult(new Intent(this, LoginActivity.class), ActivityCodeUtil.LOGINACTIVITY);
                    }
                }
                else
                {
                    if("".equals(pay_type)&&"".equals(pay_price))
                    Toast.makeText(BuyvipActivity.this, "请选择支付方式和支付金额！", Toast.LENGTH_SHORT).show();
                    else if("".equals(pay_type))
                    Toast.makeText(BuyvipActivity.this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(BuyvipActivity.this, "请选择支付金额！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list!=null){
            list.clear();
            list=null;
        }
    }
}
