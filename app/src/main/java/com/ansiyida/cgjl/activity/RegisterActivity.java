package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.bean.LoginBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.umeng.analytics.pro.c;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

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
    @Bind(R.id.image_clear2)
    ImageView image_clear2;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.editText_phone)
    EditText editText_phone;
    @Bind(R.id.editText_yanZheng)
    EditText editText_yanZheng;
    @Bind(R.id.image_clear)
    ImageView image_clear;
    @Bind(R.id.obtain_yanZheng)
    TextView obtain_yanZheng;
    @Bind(R.id.btn_finish)
    Button btn_finish;
    private boolean flag=false;
    private String imei;
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
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        title.setText("注册");
        imei=(String) SharedPreferenceUtils.get(RegisterActivity.this, "IMEI", "");
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
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
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
                    image_clear3.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    if (!"".equals(msg2)) {
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

                    String msg2 = EditTextUtil.getEditTextStr(editText_password);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }

                    image_clear2.setVisibility(View.VISIBLE);
                }
            }
        });


        //editText的获取焦点变化监听
        editText_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = EditTextUtil.getEditTextStr(editText_password);
                    if (!"".equals(trim)) {
                        image_clear3.setVisibility(View.VISIBLE);
                    } else {
                        image_clear3.setVisibility(View.GONE);

                    }
                } else {
                    image_clear3.setVisibility(View.GONE);
                }
            }
        });
        //editText的获取焦点变化监听
        editText_passwordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    if (!"".equals(trim)) {
                        image_clear2.setVisibility(View.VISIBLE);
                    } else {
                        image_clear2.setVisibility(View.GONE);

                    }
                } else {
                    image_clear2.setVisibility(View.GONE);
                }
            }
        });
    }
    public static String GetNowTime()
    { SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str; }
     @OnClick({R.id.text_xieyi,R.id.image_back,R.id.obtain_yanZheng,R.id.btn_finish,R.id.image_clear,R.id.obtain_yanZheng_tuxing,R.id.image_clear2,R.id.image_clear3})
    public void click(View view){
        try {


            switch (view.getId()) {
                case R.id.image_clear3:              //2.第一个EditText的清除按钮
                    editText_password.setText("");
                    break;
                case R.id.image_clear2:             //3.第二个EditText的清除按钮
                    editText_passwordAgain.setText("");

                    break;
                case R.id.obtain_yanZheng_tuxing:           //图形验证码

                    final Call<ResponseBody> call1 = MyApplication.getNetApi().picture((String) SharedPreferenceUtils.get(RegisterActivity.this, "app_token", ""), uniqueId, "register");
                    call1.enqueue(new Callback<ResponseBody>() {
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Glide.clear(obtain_yanZheng_tuxing);
                                Glide.with(RegisterActivity.this).load("https://cg.calcnext.com/picture/code?key=" + uniqueId + "&type=register").placeholder(Constant.default_yanzhengma).signature(new StringSignature(GetNowTime())).centerCrop().into(obtain_yanZheng_tuxing);
                            }
                        }

                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                    break;
                case R.id.image_back:           //1.返回上一级
                    this.finish();
                    break;
                case R.id.obtain_yanZheng:      //2.获取验证码
                    try {
                        String str1 = EditTextUtil.getEditTextStr(editText_phone);
                        String str_code = EditTextUtil.getEditTextStr(editText_yanZheng_tuxing);
                        // String uniqueId = DeviceUtils.getUniqueId(this);
                        if (PhoneUtil.isMobileNO(str1)) {
                            Call<DefaultBean2> call = MyApplication.getNetApi().sendPhoneYanZheng((String) SharedPreferenceUtils.get(RegisterActivity.this, "app_token", ""), uniqueId, "register", str1);
                            call.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        if ("200".equals(response.body().getStatus())) {
                                            ToastUtils.showMessage(RegisterActivity.this, "验证码已发送！");
                                            timer.start();
                                        } else {
                                            ToastUtils.showMessage(RegisterActivity.this, response.body().getMsg());
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(RegisterActivity.this, t.toString());

                                }
                            });

                        } else {
                            ToastUtils.showMessage(this, "手机号不合法");
                        }
                    } catch (Exception e) {
                        c.e.toString();
                    }
                    break;
                case R.id.text_xieyi:                 //5.用户协议
                    startActivity(new Intent(this, WebActivity.class));
                    break;
                case R.id.btn_finish:           //注册
                    Boolean BUG = isNext();
                    String phone = EditTextUtil.getEditTextStr(editText_phone);
                    // String edittext_yanZheng = EditTextUtil.getEditTextStr(editText_yanZheng);
                    if (flag && isNext()) {
                        Call<DefaultBean3> call = MyApplication.getNetApi().registerUser((String) SharedPreferenceUtils.get(RegisterActivity.this, "app_token", ""), phone, EditTextUtil.getEditTextpass(editText_passwordAgain), EditTextUtil.getEditTextStr(editText_yanZheng), "register");
                        call.enqueue(new Callback<DefaultBean3>() {
                            @Override
                            public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                                if (response.isSuccessful()) {
                                    DefaultBean3 data = response.body();
                                    if ("200".equals(data.getStatus())) {
                                        ToastUtils.showMessage(RegisterActivity.this, "注册成功");
                                        Call<LoginBean> call1 = MyApplication.getNetApi().login((String) SharedPreferenceUtils.get(RegisterActivity.this, "app_token", ""), EditTextUtil.getEditTextStr(editText_phone), EditTextUtil.getEditTextpass(editText_passwordAgain));
                                        call1.enqueue(new Callback<LoginBean>() {
                                            @Override
                                            public void onResponse(Call<LoginBean> call1, Response<LoginBean> response) {
                                                if (response.isSuccessful()) {
                                                    LoginBean body = response.body();
                                                    String status = body.getStatus();
                                                    if ("200".equals(status)) {
                                                        String app_token = body.getData();
                                                        SharedPreferenceUtils.put(RegisterActivity.this, "app_token", app_token);
                                                        SharedPreferenceUtils.put(RegisterActivity.this, "vistor", "true");

                                                        MyApplication.getInstance().finishPartActivityWant("RegisterActivity");
                                                        MyApplication.getInstance().finishPartActivityWant("LoginActivity");
                                                    } else {
                                                        ToastUtils.showMessage(RegisterActivity.this, body.getMessage());
                                                    }

                                                } else {
                                                    ToastUtils.showMessage(RegisterActivity.this, "未知错误");

                                                }

                                                call1.cancel();
                                            }

                                            @Override
                                            public void onFailure(Call<LoginBean> call1, Throwable t) {
                                                ToastUtils.showMessage(RegisterActivity.this, "未知错误");
                                                call1.cancel();

                                            }
                                        });

                                    } else {
                                        ToastUtils.showMessage(RegisterActivity.this, data.getMessage());
                                    }

                                } else {
                                    ToastUtils.showMessage(RegisterActivity.this, "未知错误");

                                }

                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean3> call, Throwable t) {
                                ToastUtils.showMessage(RegisterActivity.this, "未知错误");
                                call.cancel();

                            }
                        });
                    }

                    break;
                case R.id.image_clear:          //4.手机号清空按钮
                    editText_phone.setText("");

                    break;

                default:
                    break;

            }
        }
        catch (Exception ex)
        {
            LogUtil.i("timed_out", ex.toString());
        }
    }
    void returnResult(String sdata) {
       Intent result = new Intent();
       result.putExtra("register", sdata);
        setResult(3000 ,result);
        finish();
    }
    /**
     *  “下一步”按钮是否可点击
     * */
    private void isNextClick(boolean fla) {
        if (fla) {
            flag = true;
            btn_finish.setBackgroundResource(R.drawable.shape_login_bt);
        } else {
            flag = false;
            btn_finish.setBackgroundResource(R.drawable.shape_login_bt_none);
        }
    }
    private boolean isNext(){
        String phone = EditTextUtil.getEditTextStr(editText_phone);
        String yanzhengma = EditTextUtil.getEditTextStr(editText_yanZheng);
        String str_code = EditTextUtil.getEditTextStr(editText_yanZheng_tuxing);
        String phone_password = EditTextUtil.getEditTextStr(editText_password);
        String phone_password_again = EditTextUtil.getEditTextStr(editText_passwordAgain);
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showMessage(this,"手机号不能为空");
        }else {
            if (TextUtils.isEmpty(yanzhengma)){
                ToastUtils.showMessage(this,"短信验证码不能为空");
            }else {
                if (!PhoneUtil.isMobileNO(phone)){
                    ToastUtils.showMessage(this,"手机号格式不正确");
                }else {
                    //此处应该添加验证码判断是否正确
                    if (TextUtils.isEmpty(str_code)){
                        ToastUtils.showMessage(this,"图形验证码不能为空");

                    }else {
                        if (TextUtils.isEmpty(str_code)) {
                            ToastUtils.showMessage(this, "图形验证码不能为空");
                        }

                        else {
                            if (TextUtils.isEmpty(phone_password))
                                ToastUtils.showMessage(this, "请输入用户密码");
                            else {
                                if (phone_password.length() < 6 || phone_password.length() > 16) {
                                    ToastUtils.showMessage(this, "密码长度要在6~16字符之间");
                                } else {
                                    if (TextUtils.isEmpty(phone_password_again)) {
                                        ToastUtils.showMessage(this, "请再次输入密码");
                                    } else {
                                        if (!phone_password.equals(phone_password_again)) {
                                            ToastUtils.showMessage(this, "两次密码输入不一致");
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

        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (timer != null) {
                timer.cancel();
            }
        }
        catch (Exception ex)
        {
            LogUtil.i("timed_out", ex.toString());
        }
    }
}
