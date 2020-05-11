package com.ansiyida.cgjl.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.bean.DefaultBean4;
import com.ansiyida.cgjl.bean.LoginBean;
import com.ansiyida.cgjl.bean.UserBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.util.UMUtil;
import com.umeng.analytics.pro.c;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.btn_login)
    TextView btn_login;           //登录按钮
    @Bind(R.id.editText_phone)
    EditText editText_phone;    //手机号输入框
    @Bind(R.id.editText_password)
    EditText editText_password; //密码输入框
    @Bind(R.id.image_clear1)
    ImageView image_clear1;     //手机号一键删除
    @Bind(R.id.image_clear2)
    ImageView image_clear2;     //密码一键删除
    @Bind(R.id.image_pass_non)
    ImageView image_pass_non;
    UMShareAPI mShareAPI;

    @Bind(R.id.login)
    TextView login;
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.login_img)
    ImageView loginImg;
    @Bind(R.id.register_img)
    ImageView registerImg;
    @Bind(R.id.LOGIN_LIN)
    LinearLayout LOGINLIN;
    @Bind(R.id.REGISTER_LIN)
    LinearLayout REGISTERLIN;
    private String jmi_soure;//来源
    private Boolean showPassword = true;
    private boolean flag = false;
    private LoadingDialog loadingDialog;


    @Bind(R.id.register_editText_password)
    EditText register_editText_password;
    @Bind(R.id.editText_passwordAgain)
    EditText editText_passwordAgain;
    @Bind(R.id.image_clear3)
    ImageView image_clear3;
    @Bind(R.id.register_image_clear2)
    ImageView register_image_clear2;
    @Bind(R.id.register_editText_phone)
    EditText register_editText_phone;
    @Bind(R.id.editText_yanZheng)
    EditText editText_yanZheng;
    @Bind(R.id.image_clear)
    ImageView image_clear;
    @Bind(R.id.obtain_yanZheng)
    TextView obtain_yanZheng;
    @Bind(R.id.btn_finish)
    TextView btn_finish;
    private boolean register_flag = false;
    private String imei;
    private String uniqueId;

    //倒计时
    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
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
        GoogleAssistant(LoginActivity.this,"Android登录注册","LoginActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //注册
        imei = (String) SharedPreferenceUtils.get(LoginActivity.this, "IMEI", "");
        uniqueId = DeviceUtils.getUniqueId(this);
        // title.setText("登录");
        //text_putOut.setText("注册");text_reg
        image_clear1.setVisibility(View.GONE);
        image_clear2.setVisibility(View.GONE);
        mShareAPI = UMShareAPI.get(this.getApplicationContext());
        loadingDialog = new LoadingDialog();
    }

    @Override
    protected void initData() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(LoginActivity.this).setShareConfig(config);
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
                    image_clear1.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_password);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    image_clear1.setVisibility(View.VISIBLE);
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
                    image_clear2.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_phone);
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
        editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(trim)) {
                        image_clear1.setVisibility(View.VISIBLE);
                    } else {
                        image_clear1.setVisibility(View.GONE);

                    }
                } else {
                    image_clear1.setVisibility(View.GONE);
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
                        image_clear2.setVisibility(View.VISIBLE);
                    } else {
                        image_clear2.setVisibility(View.GONE);

                    }
                } else {
                    image_clear2.setVisibility(View.GONE);
                }
            }
        });


        //注册
        register_editText_phone.addTextChangedListener(new TextWatcher() {
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
        register_editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        register_editText_password.addTextChangedListener(new TextWatcher() {
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
        register_editText_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    @OnClick({R.id.imageback,R.id.login,R.id.register,R.id.text_xieyi, R.id.image_pass_non, R.id.tv_forgetPassword, R.id.btn_login, R.id.image_clear1, R.id.image_clear2, R.id.tv_qq, R.id.tv_weiXin, R.id.tv_xinLang, R.id.register_text_xieyi, R.id.obtain_yanZheng, R.id.btn_finish, R.id.image_clear, R.id.register_image_clear2, R.id.image_clear3})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.imageback:
                finish();
                break;
            case R.id.login:
                login.setTextSize(20);
                register.setTextSize(16);
                login.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                register.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                loginImg.setVisibility(View.VISIBLE);
                registerImg.setVisibility(View.INVISIBLE);
                LOGINLIN.setVisibility(View.VISIBLE);
                REGISTERLIN.setVisibility(View.GONE);
                ClearRegister();
                break;
            case R.id.register:
                login.setTextSize(16);
                register.setTextSize(20);
                login.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                register.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                loginImg.setVisibility(View.INVISIBLE);
                registerImg.setVisibility(View.VISIBLE);
                LOGINLIN.setVisibility(View.GONE);
                REGISTERLIN.setVisibility(View.VISIBLE);
                ClearLogin();
                break;
            case R.id.image_pass_non:                 //5.用户协议
                if (showPassword) {
                    image_pass_non.setImageResource(R.mipmap.pass_no);
                    editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText_password.setSelection(editText_password.getText().toString().length());
                    showPassword = !showPassword;
                } else {
                    image_pass_non.setImageResource(R.mipmap.pass_kj);
                    editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText_password.setSelection(editText_password.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.text_xieyi:                 //5.用户协议
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.tv_forgetPassword:        //3.忘记密码
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_login:                //4.登录按钮
                if (isNext()) {
                    if (PhoneUtil.isMobileNO(editText_phone.getText().toString().trim())) {
                        String uniqueId = DeviceUtils.getUniqueId(this);
                        Call<LoginBean> call = MyApplication.getNetApi().login((String) SharedPreferenceUtils.get(LoginActivity.this, "app_token", ""), EditTextUtil.getEditTextStr(editText_phone), EditTextUtil.getEditTextpass(editText_password));
                        call.enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                                if (response.isSuccessful()) {
                                    LoginBean body = response.body();
                                    String status = body.getStatus();
                                    if ("200".equals(status)) {
                                        String app_token = body.getData();
                                        SharedPreferenceUtils.put(LoginActivity.this, "app_token", app_token);
                                        SharedPreferenceUtils.put(LoginActivity.this, "vistor", "true");

                                        Call<UserBean> call1 = MyApplication.getNetApi().getUserInfo(((String) SharedPreferenceUtils.get(LoginActivity.this, "app_token", "")));
                                        call1.enqueue(new Callback<UserBean>() {
                                            @Override
                                            public void onResponse(Call<UserBean> call1, Response<UserBean> response) {
                                                if (response.isSuccessful()) {
                                                    UserBean body = response.body();
                                                    if ("200".equals(body.getStatus())) {
                                                        UserBean.DataBean member = response.body().getData();
                                                        SharedPreferenceUtils.put(LoginActivity.this, "isActive", member.getisActive());
                                                        SharedPreferenceUtils.remove(LoginActivity.this,"ThreeLogin");
                                                        SharedPreferenceUtils.put(LoginActivity.this,"UserId",member.getid());
                                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.hideSoftInputFromInputMethod(null,0);
                                                        LoginActivity.this.finish();
                                                    }
                                                }
                                                call1.cancel();
                                            }

                                            @Override
                                            public void onFailure(Call<UserBean> call1, Throwable t) {

                                                call1.cancel();
                                            }
                                        });

                                    } else {
                                        ToastUtils.showMessage(LoginActivity.this, body.getMessage());
                                    }

                                } else {
                                    ToastUtils.showMessage(LoginActivity.this, "未知错误");

                                }

                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {
                                ToastUtils.showMessage(LoginActivity.this, "未知错误");

                                call.cancel();

                            }
                        });

                    } else {
                        ToastUtils.showMessage(this, "手机号输入格式错误");
                    }
                }
                break;
            case R.id.image_clear1:             //5.手机号一键删除
                editText_phone.setText("");
                break;
            case R.id.image_clear2:             //6.密码一键删除
                editText_password.setText("");
                break;
            case R.id.tv_qq:
                //7.QQ登录
                loadingDialog.showDialog(this, "授权中...");
                jmi_soure = "C";
                if (UMUtil.isAppInstalled(this, "com.tencent.mobileqq")) {
                    mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                } else {
                    ToastUtils.showMessage(this, "您未安装QQ客户端，无法使用该功能");
                }

                break;
            case R.id.tv_weiXin:               //8.微信登录
                loadingDialog.showDialog(this, "授权中...");
                jmi_soure = "B";
                if (UMUtil.isAppInstalled(this, "com.tencent.mm")) {
                    mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                } else {
                    ToastUtils.showMessage(this, "您未安装微信客户端，无法使用该功能");

                }
                break;
            case R.id.tv_xinLang:               //9.新浪登录
                jmi_soure = "D";
                loadingDialog.showDialog(this, "授权中...");
                if (UMUtil.isAppInstalled(this, "com.sina.weibo")) {
                    mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.SINA, authListener);
                } else {
                    ToastUtils.showMessage(this, "您未安装微博客户端，无法使用该功能");
                }
                break;
            //注册
            case R.id.image_clear3:              //2.第一个EditText的清除按钮
                register_editText_password.setText("");
                break;
            case R.id.register_image_clear2:             //3.第二个EditText的清除按钮
                editText_passwordAgain.setText("");

                break;
            case R.id.obtain_yanZheng:      //2.获取验证码
                try {
                    String str1 = EditTextUtil.getEditTextStr(register_editText_phone);
                    if (PhoneUtil.isMobileNO(str1)) {
                        Call<DefaultBean2> call = MyApplication.getNetApi().sendPhoneYanZheng((String) SharedPreferenceUtils.get(LoginActivity.this, "app_token", ""), uniqueId, "register",str1);
                        call.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    if ("200".equals(response.body().getStatus())) {
                                        ToastUtils.showMessage(LoginActivity.this, "验证码已发送！");
                                        timer.start();
                                    } else {
                                        ToastUtils.showMessage(LoginActivity.this, response.body().getMsg());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(LoginActivity.this, t.toString());

                            }
                        });

                    } else {
                        ToastUtils.showMessage(this, "手机号不合法");
                    }
                } catch (Exception e) {
                    c.e.toString();
                }
                break;
            case R.id.register_text_xieyi:                 //5.用户协议
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.btn_finish:           //注册
                String phone = EditTextUtil.getEditTextStr(register_editText_phone);
                if(isRegisterNext()) {
                    Call<DefaultBean3> call = MyApplication.getNetApi().registerUser((String) SharedPreferenceUtils.get(LoginActivity.this, "app_token", ""), phone, EditTextUtil.getEditTextpass(editText_passwordAgain), EditTextUtil.getEditTextStr(editText_yanZheng), "register");
                    call.enqueue(new Callback<DefaultBean3>() {
                        @Override
                        public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                            if (response.isSuccessful()) {
                                DefaultBean3 data = response.body();
                                if ("200".equals(data.getStatus())) {
                                    ToastUtils.showMessage(LoginActivity.this, "注册成功");
                                    login.setTextSize(20);
                                    register.setTextSize(16);
                                    login.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                    register.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                    loginImg.setVisibility(View.VISIBLE);
                                    registerImg.setVisibility(View.INVISIBLE);
                                    LOGINLIN.setVisibility(View.VISIBLE);
                                    REGISTERLIN.setVisibility(View.GONE);
                                    ClearRegister();
                                } else {
                                    ToastUtils.showMessage(LoginActivity.this, data.getMessage());
                                }

                            } else {
                                ToastUtils.showMessage(LoginActivity.this, "未知错误");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean3> call, Throwable t) {
                            ToastUtils.showMessage(LoginActivity.this, "未知错误");
                            call.cancel();
                        }
                    });
                }
                break;
            case R.id.image_clear:          //4.手机号清空按钮
                register_editText_phone.setText("");
                break;
            default:
                break;

        }
    }

    private boolean isRegisterNext() {
        obtain_yanZheng.setText("发送短信验证码");
        if(register_editText_phone.getText().toString().isEmpty() || register_editText_password.getText().toString().isEmpty() ||editText_passwordAgain.getText().toString().isEmpty() ||editText_yanZheng.getText().toString().isEmpty() ||obtain_yanZheng.getText().toString().isEmpty() ){
            ToastUtils.showMessage(this,"请确认您的信息是否全部填写！");
            return false;
        }else{
            if(!Constant.Patternphone.matcher(register_editText_phone.getText().toString()).matches()){
                ToastUtils.showMessage(this,"您的手机号格式不正确！");
                return false;
            }else{
                return true;
            }
        }
    }

    private boolean isNext() {
        String phone = EditTextUtil.getEditTextStr(editText_phone);
        String phone_password = EditTextUtil.getEditTextStr(editText_password);
        if (phone.isEmpty() || phone_password.isEmpty()) {
            ToastUtils.showMessage(this, "手机号或密码不能为空！");
            return false;
        } else {
            if (!Constant.Patternphone.matcher(phone).matches()) {
                ToastUtils.showMessage(this, "手机号格式不正确");
                return false;
            }else{
                return true;
            }
        }
    }

    private UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Set<String> seting = data.keySet();
            for (String key : seting) {
                LogUtil.i("login", "key:" + key + ",value:" + data.get(key));
            }
            String singleTab = null;      //唯一标识
            String name = null;           //昵称
            String headUrl = null;        //头像
            String accessToken = null;
            String open_id = null;
            String loginType = null;
            if ("B".equals(jmi_soure)) {
                //微信登录
                singleTab = data.get("unionid");
                name = data.get("name");
                headUrl = data.get("iconurl");
                accessToken = data.get("accessToken");
                ;
                open_id = data.get("openid");
                ;
                loginType = "WeChat";
            } else if ("C".equals(jmi_soure)) {
                //QQ登录
                singleTab = data.get("uid");
                name = data.get("name");
                headUrl = data.get("iconurl");
                accessToken = data.get("accessToken");
                ;
                open_id = data.get("openid");
                ;
                loginType = "QQ";
            } else if ("D".equals(jmi_soure)) {
                //新浪微博登录
                singleTab = data.get("uid");
                name = data.get("name");
                headUrl = data.get("iconurl");
                accessToken = data.get("accessToken");
                ;
                open_id = data.get("id");
                ;
                loginType = "SinaTwitter";
            }
            Call<DefaultBean4> call = MyApplication.getNetApi().thirdLogin("", loginType, accessToken, open_id);
            call.enqueue(new Callback<DefaultBean4>() {
                @Override
                public void onResponse(Call<DefaultBean4> call, Response<DefaultBean4> response) {
                    if (response.isSuccessful()) {
                        DefaultBean4 body = response.body();
                        if ("200".equals(body.getStatus())) {
                            String app_token = body.getData();
                            SharedPreferenceUtils.put(LoginActivity.this, "app_token", app_token);
                            SharedPreferenceUtils.put(LoginActivity.this, "vistor", "true");
                            ToastUtils.showMessage(LoginActivity.this, "登录成功");
                            Call<UserBean> call1 = MyApplication.getNetApi().getUserInfo(((String) SharedPreferenceUtils.get(LoginActivity.this, "app_token", "")));
                            call1.enqueue(new Callback<UserBean>() {
                                @Override
                                public void onResponse(Call<UserBean> call1, Response<UserBean> response) {
                                    if (response.isSuccessful()) {
                                        UserBean body = response.body();
                                        if ("200".equals(body.getStatus())) {
                                            UserBean.DataBean member = response.body().getData();
                                            SharedPreferenceUtils.put(LoginActivity.this, "isActive", member.getisActive());

                                            SharedPreferenceUtils.put(LoginActivity.this, "ThreeLogin","true");
                                            if(member.getphone()==null) {
                                                Intent intent_read = new Intent(LoginActivity.this, BindPhoneActivity.class);
                                                intent_read.putExtra("title", "第三方登录绑定");
                                                intent_read.putExtra("btn", "绑定");
                                                startActivity(intent_read);
                                                LoginActivity.this.finish();
                                            }else {
                                               // SharedPreferenceUtils.put(LoginActivity.this, "phoneNum", member.getphone() + "");
                                                LoginActivity.this.finish();
                                            }
                                        }
                                    }

                                    call1.cancel();
                                }

                                @Override
                                public void onFailure(Call<UserBean> call1, Throwable t) {

                                    call1.cancel();
                                }
                            });

                        } else {
                            ToastUtils.showMessage(LoginActivity.this, body.getMessage());
                        }
                    }
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<DefaultBean4> call, Throwable t) {
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }
            });
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtils.showMessage(LoginActivity.this, "失败");
            LogUtil.i("login", "errorMsg:" + t.getMessage());
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtils.showMessage(LoginActivity.this, "取消了");
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }
    };

    private void isNextClick(boolean fla) {
        if (fla) {
            flag = true;
            // btn_login.setBackgroundResource(R.drawable.shape_login_bt);
        } else {
            flag = false;
            //  btn_login.setBackgroundResource(R.drawable.shape_login_bt);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Intent i = getIntent();
        String register = i.getStringExtra("register") + "";
        if (resultCode == 3000) {
            if ("true".equals(register))
                this.finish();
        }
    }

    /**
     * 绑定手机号弹框
     */
    private PopupWindow popupWindow_bindPhone;
    private View contentView_bindPhone;

    public void initPopuWindow_dismiss() {
        if (popupWindow_bindPhone == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_bindPhone = mLayoutInflater.inflate(R.layout.popwindow_bindphone, null);
            popupWindow_bindPhone = new PopupWindow(contentView_bindPhone, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        EditText editText_phone = (EditText) contentView_bindPhone.findViewById(R.id.editText_phone);
        EditText editText_yanZheng = (EditText) contentView_bindPhone.findViewById(R.id.editText_yanZheng);
        final TextView obtain_yanZheng = (TextView) contentView_bindPhone.findViewById(R.id.obtain_yanZheng);
        final Button btn_finish = (Button) contentView_bindPhone.findViewById(R.id.btn_finish);
        //倒计时

        final CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {

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
        obtain_yanZheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
        timer.cancel();
        obtain_yanZheng.setText("获取验证码");
        obtain_yanZheng.setClickable(true);
        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow_bindPhone.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_bindPhone.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_bindPhone.setOutsideTouchable(true);
        popupWindow_bindPhone.setFocusable(true);
        popupWindow_bindPhone.showAtLocation(contentView_bindPhone, Gravity.CENTER, 0, 0);
        popupWindow_bindPhone.update();
        popupWindow_bindPhone.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
                timer.cancel();
            }
        });
    }
    public static String GetNowTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    public void ClearLogin(){
        editText_phone.setText("");
        editText_password.setText("");
    }
    public void ClearRegister(){
        register_editText_phone.setText("");
        register_editText_password.setText("");
        editText_passwordAgain.setText("");
        editText_yanZheng.setText("");
        obtain_yanZheng.setText("发送短信验证码");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) {
            if (loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
            loadingDialog = null;
        }
    }

}
