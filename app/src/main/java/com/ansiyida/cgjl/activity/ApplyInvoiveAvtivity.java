package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.dialog.LoadingDialog_apply;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyInvoiveAvtivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.image_noneqy)
    ImageView image_noneqy;
    @Bind(R.id.image_nonegr)
    ImageView image_nonegr;
    @Bind(R.id.editText_td)
    EditText editText_td;
    @Bind(R.id.editText_sjr)
    EditText editText_sjr;
    @Bind(R.id.text_gr)
    TextView text_gr;
    @Bind(R.id.text_qy)
    TextView text_qy;
    @Bind(R.id.text_price)
    TextView text_price;
    @Bind(R.id.editText_lxd)
    EditText editText_lxd;
    @Bind(R.id.editText_emall)
    EditText editText_emall;
    @Bind(R.id.editText_dz)
    EditText editText_dz;
    @Bind(R.id.editText_sh)
    EditText editText_sh;
private String orderIds="";
    private String price="";
    private String type="";
    private LoadingDialog_apply loadingDialog = new LoadingDialog_apply();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {

        return R.layout.activity_applyinvoivce;
    }

    @Override
    protected void initView() {
        text_title.setText("开票详情");
        text_putOut.setText("");
        Intent intent = getIntent();
        orderIds=intent.getStringExtra("invoicenub");
        price=intent.getStringExtra("price");
        image_noneqy.setImageResource(R.mipmap.select_yes);
        text_qy.setTextColor(getResources().getColor(R.color.text_black));
        type="企业单位";
        text_price.setText(price);
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
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    break;
            }
        };
    };
    @OnClick({R.id.image_back,R.id.btn_finish,R.id.image_noneqy,R.id.image_nonegr,R.id.text_gr,R.id.text_qy})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.text_qy:               //1.返回上一级
                image_noneqy.setImageResource(R.mipmap.select_yes);
                image_nonegr.setImageResource(R.mipmap.select_none);
                text_qy.setTextColor(getResources().getColor(R.color.text_black));
                text_gr.setTextColor(getResources().getColor(R.color.black_gray));
                type="企业单位";
                break;
            case R.id.text_gr:               //1.返回上一级
                image_nonegr.setImageResource(R.mipmap.select_yes);
                image_noneqy.setImageResource(R.mipmap.select_none);
                type="个人/非企业单位";
                text_gr.setTextColor(getResources().getColor(R.color.text_black));
                text_qy.setTextColor(getResources().getColor(R.color.black_gray));
                break;
            case R.id.image_noneqy:               //1.返回上一级
                image_noneqy.setImageResource(R.mipmap.select_yes);
                image_nonegr.setImageResource(R.mipmap.select_none);
                text_qy.setTextColor(getResources().getColor(R.color.text_black));
                text_gr.setTextColor(getResources().getColor(R.color.black_gray));

                type="企业单位";
                break;
            case R.id.image_nonegr:               //1.返回上一级
                image_nonegr.setImageResource(R.mipmap.select_yes);
                image_noneqy.setImageResource(R.mipmap.select_none);
                type="个人/非企业单位";
                text_gr.setTextColor(getResources().getColor(R.color.text_black));
                text_qy.setTextColor(getResources().getColor(R.color.black_gray));
                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            case R.id.btn_finish:           //注册
             //   Boolean BUG=isNext();
                String phone = EditTextUtil.getEditTextStr(editText_lxd);
                // String edittext_yanZheng = EditTextUtil.getEditTextStr(editText_yanZheng);
                if (isNext())
                {
                    Call<DefaultBean3> call= MyApplication.getNetApi().invoice_apply((String) SharedPreferenceUtils.get(ApplyInvoiveAvtivity.this, "app_token", ""),type,EditTextUtil.getEditTextStr(editText_td),EditTextUtil.getEditTextStr(editText_sh),"技术/咨询/信息服务费",price,EditTextUtil.getEditTextStr(editText_sjr),EditTextUtil.getEditTextStr(editText_lxd),EditTextUtil.getEditTextStr(editText_dz),EditTextUtil.getEditTextStr(editText_emall),orderIds);
                    call.enqueue(new Callback<DefaultBean3>() {
                        @Override
                        public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                            if (response.isSuccessful()) {
                                DefaultBean3 data = response.body();
                                if ("200".equals(data.getStatus())){
                                    loadingDialog.showDialog(ApplyInvoiveAvtivity.this, "");
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
                                    }).start(); }else {
                                    ToastUtils.showMessage(ApplyInvoiveAvtivity.this,data.getMessage());
                                }

                            }else {
                                ToastUtils.showMessage(ApplyInvoiveAvtivity.this,"未知错误");

                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean3> call, Throwable t) {
                            ToastUtils.showMessage(ApplyInvoiveAvtivity.this,"未知错误");
                            call.cancel();

                        }
                    });
                }

                break;
            default:
                break;

        }
    }


    private boolean isNext(){
        String phone = EditTextUtil.getEditTextStr(editText_lxd);
        String emall = EditTextUtil.getEditTextStr(editText_emall);
        String address = EditTextUtil.getEditTextStr(editText_dz);
        String recevei = EditTextUtil.getEditTextStr(editText_sjr);
        String taitou = EditTextUtil.getEditTextStr(editText_td);
        String suihao = EditTextUtil.getEditTextStr(editText_sh);

        if (TextUtils.isEmpty(phone)){
           // ToastUtils.showMessage(this,"手机号不能为空");
            ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
        }else {
            if (TextUtils.isEmpty(emall)){
           //     ToastUtils.showMessage(this,"邮箱地址不能为空");
                ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
            }else {
                if (!emall.contains(".")|!emall.contains("@")) {
                  //  ToastUtils.showMessage(this, "邮箱格式不正确");
                    ToastUtils.showMessage(this,"请输入正确的邮箱地址");
                } else {
                    if (!PhoneUtil.isMobileNO(phone)) {
                        ToastUtils.showMessage(this, "手机号格式不正确");
                        //ToastUtils.showMessage(this,"请输入正确的手机号码");
                    } else {
                        //此处应该添加验证码判断是否正确
                        if (TextUtils.isEmpty(address)) {
                            //ToastUtils.showMessage(this, "详细地址不能为空");
                            ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
                        } else {
                            if (TextUtils.isEmpty(recevei)) {
                             //   ToastUtils.showMessage(this, "收件人不能为空");
                                ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
                            } else {
                                if (TextUtils.isEmpty(taitou))
                                 //   ToastUtils.showMessage(this, "抬头不能为空");
                                    ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
                                else {
                                    if (TextUtils.isEmpty(suihao)) {
                                       // ToastUtils.showMessage(this, "税号不能为空");
                                        ToastUtils.showMessage(this,"以上输入框都为必填项，不能为空");
                                    } else {

                                                return true;

                                            }

                                        }
                                    }
                        }
                    }
                }
            }
        }

        return false;
    }
    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }

            @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

