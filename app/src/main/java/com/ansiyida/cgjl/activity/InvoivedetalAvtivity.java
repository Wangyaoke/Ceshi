package com.ansiyida.cgjl.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.invoice_detail_listbean;
import com.ansiyida.cgjl.dialog.LoadingDialog_apply;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoivedetalAvtivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.editText_td)
    TextView editText_td;
    @Bind(R.id.editText_sjr)
    TextView editText_sjr;
    @Bind(R.id.text_price)
    TextView text_price;
    @Bind(R.id.editText_content)
    TextView editText_content;
    @Bind(R.id.editText_time)
    TextView editText_time;
    @Bind(R.id.editText_dz)
    TextView editText_dz;
    @Bind(R.id.editText_sh)
    TextView editText_sh;
    @Bind(R.id.jilu)
    TextView jilu;
    @Bind(R.id.start_end)
    TextView start_end;
private String orderIds="";
    private ArrayList<NewBean2> lists;
    private LoadingDialog_apply loadingDialog = new LoadingDialog_apply();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {

        return R.layout.activity_applyinvoivce_detal;
    }

    @Override
    protected void initView() {
        text_title.setText("开票详情");
        text_putOut.setText("");
        lists = new ArrayList<>();
        Intent intent = getIntent();
        orderIds=intent.getStringExtra("id");
        Call<invoice_detail_listbean> call = MyApplication.getNetApi().get_invoice_detail(orderIds,((String) SharedPreferenceUtils.get(InvoivedetalAvtivity.this, "app_token", "")));
        call.enqueue(new Callback<invoice_detail_listbean>() {
            @Override
            public void onResponse(Call<invoice_detail_listbean> call, Response<invoice_detail_listbean> response) {
                if (response.isSuccessful()) {
                    invoice_detail_listbean body = response.body();
                    List<invoice_detail_listbean.DataBean.order_bean> list = body.getData().getorder_bean();
                    if ("200".equals(body.getStatus()))
                    {
                        lists.clear();
                       editText_td.setText(body.getData().getinvoice().getinvoiceTitle());
                        editText_dz.setText(body.getData().getinvoice().getaddress());
                        editText_sh.setText(body.getData().getinvoice().getdutyNo());
                        editText_sjr.setText(body.getData().getinvoice().getrecipients());
                        editText_time.setText(TimeUtils.mmtime_Time(body.getData().getinvoice().getcreateTime()));
                        editText_content.setText(body.getData().getinvoice().getcontent());
                        text_price.setText(body.getData().getinvoice().getmoney());
                        for (invoice_detail_listbean.DataBean.order_bean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setId(listBean.getid());
                            newBean.setcreateTime(TimeUtils.mmtime_Time(listBean.getcreateTime()));
                            newBean.setisPay(listBean.getisPay());
                            newBean.setisDelete(listBean.getisDelete());
                            newBean.setprice(listBean.getprice());
                            newBean.setpayMethod(listBean.getpayMethode());
                            newBean.setpayStatus(listBean.getpayStatus());
                            newBean.setorderNumber(listBean.getorderNumber());
                            newBean.setuserId(listBean.getuserId());
                            newBean.setshowDate(listBean.getshowDate());
                            newBean.setpayTime(TimeUtils.mmtime_Time(listBean.getpayTime()));
                            lists.add(newBean);
                        }
                        jilu.setText("一张发票包含"+lists.size()+""+"条购买记录");
                        start_end.setText("");
                    }
                }

                call.cancel();
            }

            @Override
            public void onFailure(Call<invoice_detail_listbean> call, Throwable t) {
                ToastUtils.showMessage(InvoivedetalAvtivity.this, t.toString());
                call.cancel();
            }
        });
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

    @OnClick({R.id.image_back,R.id.line_goto,R.id.text_kefu})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.line_goto:               //1.返回上一级
                Intent intent_read = new Intent(this, InvoivedetaillistAvtivity.class);
                intent_read.putExtra("id", orderIds);
                //  intent_read.putExtra("price", lists.get(0).getprovince());
                this.startActivity(intent_read);
                break;
            case R.id.text_kefu:               //1.返回上一级
                initPopuWindow_dropMenu("拨打客服电话：010-87654321");
                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;

            default:
                break;

        }
    }
    //动态权限申请后处理
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }else {
                    ToastUtils.showMessage(InvoivedetalAvtivity.this, "您刚拒绝了一个必要权限");
                }break;
            default:super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } }
    /**
     * 智库号弹框
     */
    private PopupWindow popupWindow2;
    private View contentView2;

    private void initPopuWindow_dropMenu(String html) {
        final Activity activity = this;
        if (popupWindow2 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView2 = mLayoutInflater.inflate(R.layout.popwindow_dilog, null);
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
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.CALL_PHONE);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[] {
                                Manifest.permission.CALL_PHONE
                        }, 123);
                        return;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + "01053620367");
                        intent.setData(data);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + "01053620367");
                    intent.setData(data);
                    startActivity(intent);
                }

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
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "01087654321");
                intent.setData(data);
                startActivity(intent);


            }
        });

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setFocusable(true);
        popupWindow2.showAtLocation(contentView2, Gravity.CENTER, 0, 0);
        popupWindow2.update();
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }


    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }

}

