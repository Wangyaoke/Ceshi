package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.bean.UserBean;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.umeng.analytics.pro.c;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BindPhoneActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.editText_phone)
    EditText editText_phone;
    @Bind(R.id.editText_pass)
    EditText editText_pass;
    @Bind(R.id.editText_yanZheng)
    EditText editText_yanZheng;
    @Bind(R.id.image_clear)
    ImageView image_clear;
    @Bind(R.id.image_pass)
    ImageView image_pass;
    @Bind(R.id.obtain_yanZheng)
    TextView obtain_yanZheng;
    @Bind(R.id.btn_finish)
    Button btn_finish;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.password_rel)
    RelativeLayout passwordRel;
    @Bind(R.id.text_line)
    TextView textLine;
    private boolean flag = false;
    private String uniqueId;
    //倒计时
    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            obtain_yanZheng.setClickable(false);
            obtain_yanZheng.setTextColor(getResources().getColor(R.color.btn_none_click));
            obtain_yanZheng.setText((millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            obtain_yanZheng.setClickable(true);
            obtain_yanZheng.setText("重新获取");
            obtain_yanZheng.setTextColor(getResources().getColor(R.color.tab_2_day));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {

        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        btn_finish.setText(intent.getStringExtra("btn"));
        String phone = getIntent().getStringExtra("phone");

        if (title.equals("第三方登录绑定")) {
            this.title.setText("绑定手机号");
            textLine.setVisibility(View.GONE);
            imageBack.setVisibility(View.GONE);
            textPutOut.setVisibility(View.VISIBLE);
            textPutOut.setText("跳过");
        } else {
            this.title.setText(title);
            imageBack.setVisibility(View.VISIBLE);
            textPutOut.setVisibility(View.GONE);

        }

        if (phone != null && !"".equals(phone)) {
            editText_phone.setText(phone);
        }
        String strEdit1 = getEditTextStr(editText_phone);
        if ("".equals(strEdit1)) {
            image_clear.setVisibility(View.GONE);
        }

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
        textPutOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                    isNextClick(true);
                } else {
                    String msg2 = getEditTextStr(editText_yanZheng);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(true);
                    }
                    image_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        editText_pass.addTextChangedListener(new TextWatcher() {
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
                    image_pass.setVisibility(View.GONE);
                    isNextClick(true);
                } else {
                    String msg2 = getEditTextStr(editText_yanZheng);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(true);

                    }
                    image_pass.setVisibility(View.VISIBLE);
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
                    isNextClick(true);
                } else {
                    String msg2 = getEditTextStr(editText_phone);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(true);

                    }
                }
            }
        });

        //editText的获取焦点变化监听
        editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = getEditTextStr(editText_phone);
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

    private boolean isNext() {
        String phone = EditTextUtil.getEditTextStr(editText_phone);
        String yanzhengma = EditTextUtil.getEditTextStr(editText_yanZheng);
        String phone_password = EditTextUtil.getEditTextStr(editText_pass);
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showMessage(this, "手机号不能为空");
        } else {
            if (TextUtils.isEmpty(yanzhengma)) {
                ToastUtils.showMessage(this, "短信验证码不能为空");
            } else {
                if (!PhoneUtil.isMobileNO(phone)) {
                    ToastUtils.showMessage(this, "手机号格式不正确");
                } else {
                    if ("".equals(phone_password)) {
                        ToastUtils.showMessage(this, "请输入密码");
                    } else {
                        if (phone_password.length() < 6 || phone_password.length() > 16) {
                            ToastUtils.showMessage(this, "密码长度要在6~16字符之间");
                        }
                    }
                }
            }
        }
        return false;
    }

    @OnClick({R.id.image_back, R.id.image_clear, R.id.image_pass, R.id.btn_finish, R.id.obtain_yanZheng})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.obtain_yanZheng_tuxing:           //图形验证码
                break;
            case R.id.image_back:           //1.返回上一级
                this.finish();
                break;
            case R.id.image_pass:          //2.手机号清空按钮
                editText_pass.setText("");
                break;
            case R.id.image_clear:          //2.手机号清空按钮
                editText_phone.setText("");
                break;
            case R.id.btn_finish:           //3.完成按钮
                String phone = EditTextUtil.getEditTextStr(editText_phone);
                String pass = EditTextUtil.getEditTextpass(editText_pass);
                if(!phone.equals("") && !pass.equals("") && !EditTextUtil.getEditTextStr(editText_yanZheng).equals("")){
                    Log.e("绑定", "true ");
                    Call<DefaultBean3> call = MyApplication.getNetApi().changeUser((String) SharedPreferenceUtils.get(BindPhoneActivity.this, "app_token", ""), phone, pass, EditTextUtil.getEditTextStr(editText_yanZheng), "modify_phone");
                    call.enqueue(new Callback<DefaultBean3>() {
                        @Override
                        public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                            if (response.isSuccessful()) {
                                DefaultBean3 data = response.body();
                                if ("200".equals(data.getStatus())) {
                                    ToastUtils.showMessage(BindPhoneActivity.this, "绑定成功");
                                    MyApplication.getInstance().finishPartActivityWant("RegisterActivity");
                                    Call<UserBean> call1 = MyApplication.getNetApi().getUserInfo(((String) SharedPreferenceUtils.get(BindPhoneActivity.this, "app_token", "")));
                                    call1.enqueue(new Callback<UserBean>() {
                                        @Override
                                        public void onResponse(Call<UserBean> call1, Response<UserBean> response) {
                                            if (response.isSuccessful()) {
                                                UserBean body = response.body();
                                                if ("200".equals(body.getStatus())) {
                                                    UserBean.DataBean member = response.body().getData();
                                                    SharedPreferenceUtils.put(BindPhoneActivity.this, "phoneNum", member.getphone());
                                                    Intent result = new Intent();
                                                    setResult(1111, result);
                                                    BindPhoneActivity.this.finish();
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

                                    ToastUtils.showMessage(BindPhoneActivity.this, data.getMessage());
                                }

                            } else {
                                ToastUtils.showMessage(BindPhoneActivity.this, "未知错误");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean3> call, Throwable t) {
                            ToastUtils.showMessage(BindPhoneActivity.this, "未知错误");
                            call.cancel();
                        }
                    });
                }else{
                    Log.e("绑定", "false ");
                   ToastUtils.showMessage(this,"请填写完整信息!");
                }

                break;
            case R.id.obtain_yanZheng:       //4.获取验证码
                try {
                    String str1 = EditTextUtil.getEditTextStr(editText_phone);
                    // String uniqueId = DeviceUtils.getUniqueId(this);
                    if (PhoneUtil.isMobileNO(str1)) {
                        Call<DefaultBean2> call = MyApplication.getNetApi().sendPhoneYanZheng((String) SharedPreferenceUtils.get(BindPhoneActivity.this, "app_token", ""), uniqueId, "modify", str1);
                        call.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    //    boolean flag = response.body().isFlag();
                                    if ("200".equals(response.body().getStatus())) {
                                        ToastUtils.showMessage(BindPhoneActivity.this, "验证码已发送！");
                                        timer.start();
                                    } else {
                                        ToastUtils.showMessage(BindPhoneActivity.this, response.body().getMsg());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(BindPhoneActivity.this, t.toString());

                            }
                        });

                    } else {
                        ToastUtils.showMessage(this, "手机号不合法");
                    }
                } catch (Exception e) {
                    c.e.toString();
                }

                break;
            default:
                break;

        }

    }

    private String getEditTextStr(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * “下一步”按钮是否可点击
     */
    private void isNextClick(boolean fla) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
