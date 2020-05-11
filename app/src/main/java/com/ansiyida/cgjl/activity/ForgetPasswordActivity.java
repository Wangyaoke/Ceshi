package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ansiyida.cgjl.activity.RegisterActivity.GetNowTime;

public class ForgetPasswordActivity extends BaseActivity {
    @Bind(R.id.obtain_yanZheng_tuxing)
    ImageView obtain_yanZheng_tuxing;
    @Bind(R.id.editText_yanZheng_tuxing)
    EditText editText_yanZheng_tuxing;
    @Bind(R.id.editText_password)
    EditText editText_password;
    @Bind(R.id.editText_passwordAgain)
    EditText editText_passwordAgain;
    @Bind(R.id.image_clear3)
    ImageView image_clear3;
    @Bind(R.id.image_clear4)
    ImageView image_clear4;
    @Bind(R.id.image_clear1)
    ImageView image_clear1;
    @Bind(R.id.image_clear2)
    ImageView image_clear2;
    @Bind(R.id.image_clear)
    ImageView image_clear;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.editText_phone)
    EditText editText_phone;
    @Bind(R.id.editText_yanZheng)
    EditText editText_yanZheng;
    @Bind(R.id.obtain_yanZheng)
    TextView obtain_yanZheng;
    @Bind(R.id.btn_finish)
    TextView btn_finish;
    private boolean flag=false;
    private String uniqueId;
    //倒计时
    private CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            obtain_yanZheng.setClickable(false);
            obtain_yanZheng.setText((millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            obtain_yanZheng.setClickable(true);
            obtain_yanZheng.setText("重新获取");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        title.setText("忘记密码");
        isNextClick(false);
        uniqueId = DeviceUtils.getUniqueId(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                if ("".equals(msg)) {
                    image_clear1.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);

                    String msg4 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    String msg5 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2) && !"".equals(msg4)&&!"".equals(msg5)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
                    image_clear1.setVisibility(View.VISIBLE);
                }
            }
        });
        editText_yanZheng_tuxing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                if ("".equals(msg)) {
                    image_clear3.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);

                    String msg4 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    String msg5 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2) && !"".equals(msg4)&&!"".equals(msg5)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
                    image_clear3.setVisibility(View.VISIBLE);
                }
            }
        });
        editText_passwordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                if ("".equals(msg)) {
                    image_clear2.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);
                    String msg4 = EditTextUtil.getEditTextStr(editText_password);
                    String msg5 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2) && !"".equals(msg4)&&!"".equals(msg5)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
                    image_clear2.setVisibility(View.VISIBLE);
                }
            }
        });
        editText_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                if ("".equals(msg)) {
                    image_clear.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    image_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        editText_yanZheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String msg = s.toString();
                if ("".equals(msg)) {
                    image_clear4.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    image_clear4.setVisibility(View.VISIBLE);
                }
            }
        });

        //editText的获取焦点变化监听
        editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(trim)) {
                        image_clear.setVisibility(View.VISIBLE);
                    } else {
                        image_clear.setVisibility(View.GONE);

                    }
                } else {
                    image_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.image_back,R.id.image_clear1,R.id.image_clear2,R.id.image_clear3,R.id.image_clear4,R.id.image_clear,R.id.btn_finish,R.id.obtain_yanZheng,R.id.obtain_yanZheng_tuxing,R.id.text_xieyi})
    public void click(View view){
        switch (view.getId()){
            case R.id.obtain_yanZheng_tuxing:           //图形验证码

                final Call<ResponseBody> call1 = MyApplication.getNetApi().picture((String) SharedPreferenceUtils.get(ForgetPasswordActivity.this, "app_token", ""), uniqueId, "retrieve");
                call1.enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Glide.clear(obtain_yanZheng_tuxing);
                            Glide.with(ForgetPasswordActivity.this).load("https://cg.calcnext.com/picture/code?key="+uniqueId+"&type=retrieve").placeholder(Constant.default_yanzhengma).signature(new StringSignature(GetNowTime())).centerCrop().into(obtain_yanZheng_tuxing);
                        }
                    }

                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
            case R.id.image_back:           //1.返回上一级
                this.finish();

                break;
            case R.id.text_xieyi:                 //5.用户协议
                startActivity(new Intent(this,WebActivity.class));
                break;
            case R.id.image_clear1:         //2.密码清空按钮
                editText_password.setText("");
                break;
            case R.id.image_clear2:         //3.再次输入密码清空按钮
                editText_passwordAgain.setText("");
                break;
            case R.id.btn_finish:           //4.完成按钮
                if (flag){
                    String str1=EditTextUtil.getEditTextpass(editText_password);
                    String str2=EditTextUtil.getEditTextpass(editText_passwordAgain);
                    String phone = EditTextUtil.getEditTextStr(editText_phone);
                    int length=str1.length();
                    if (PhoneUtil.isMobileNO(phone)) {
                        if (length >= 6 && length <= 18) {
                            if (str1.equals(str2)) {
                                Call<DefaultBean3> call = MyApplication.getNetApi().retrievePassword((String) SharedPreferenceUtils.get(ForgetPasswordActivity.this, "app_token", ""), phone, EditTextUtil.getEditTextpass(editText_passwordAgain), EditTextUtil.getEditTextStr(editText_yanZheng), "modify_password");
                                call.enqueue(new Callback<DefaultBean3>() {
                                    @Override
                                    public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                                        if (response.isSuccessful()) {
                                            DefaultBean3 data = response.body();
                                            if ("200".equals(data.getStatus())) {
                                                ToastUtils.showMessage(ForgetPasswordActivity.this, "修改密码成功，请重新登录");
                                                MyApplication.getInstance().finishPartActivityWant("RegisterActivity");
                                                ForgetPasswordActivity.this.finish();
                                            } else {
                                                ToastUtils.showMessage(ForgetPasswordActivity.this, data.getMessage());
                                            }

                                        } else {
                                            ToastUtils.showMessage(ForgetPasswordActivity.this, "未知错误");

                                        }

                                        call.cancel();
                                    }

                                    @Override
                                    public void onFailure(Call<DefaultBean3> call, Throwable t) {
                                        ToastUtils.showMessage(ForgetPasswordActivity.this, "未知错误");
                                        call.cancel();

                                    }
                                });


                            } else {
                                ToastUtils.showMessage(this, "两次密码输入不一致");
                            }
                        } else {
                            ToastUtils.showMessage(this, "密码长度不符合规则");

                        }
                    }
                    else {
                        ToastUtils.showMessage(this, "手机号不合法");
                    }
                }
                break;
            case R.id.obtain_yanZheng:      //5.获取验证码
                try{
                    String str1 = EditTextUtil.getEditTextStr(editText_phone);
                    String str_code = EditTextUtil.getEditTextStr(editText_yanZheng_tuxing);
                    // String uniqueId = DeviceUtils.getUniqueId(this);
                    if (PhoneUtil.isMobileNO(str1)) {

                        Call<DefaultBean2> call = MyApplication.getNetApi().sendPhoneYanZheng((String) SharedPreferenceUtils.get(ForgetPasswordActivity.this, "app_token", ""), uniqueId, "retrieve", str1);
                        call.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    //    boolean flag = response.body().isFlag();
                                    if ("200".equals( response.body().getStatus())) {
                                        ToastUtils.showMessage(ForgetPasswordActivity.this, "验证码已发送！");
                                        timer.start();
                                    } else {
                                        ToastUtils.showMessage(ForgetPasswordActivity.this, response.body().getMsg());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(ForgetPasswordActivity.this, t.toString());

                            }
                        });

                    } else {
                        ToastUtils.showMessage(this, "手机号不合法");
                    }
                }
                catch(Exception e)
                {
                    com.umeng.analytics.pro.c.e.toString();
                }
                break;
            case R.id.image_clear:      //6.手机号清空按钮
                editText_phone.setText("");
                break;
            case R.id.image_clear3:      //6.手机号清空按钮
                editText_yanZheng_tuxing.setText("");
                break;
            case R.id.image_clear4:      //6.手机号清空按钮
                editText_yanZheng.setText("");
                break;

            default:break;

        }

    }
    /**
     *  “下一步”按钮是否可点击
     * */
    private void isNextClick(boolean fla) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
