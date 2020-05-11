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
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.obtain_yanZheng)
    TextView obtain_yanZheng;
    @Bind(R.id.editText_yanZheng)
    EditText editText_yanZheng;
    @Bind(R.id.editText_password)
    EditText editText_password;
    @Bind(R.id.editText_passwordAgain)
    EditText editText_passwordAgain;
    @Bind(R.id.editText_phone)
    EditText editText_phone;

    @Bind(R.id.image_clear1)
    ImageView image_clear1;
    @Bind(R.id.image_clear2)
    ImageView image_clear2;
    @Bind(R.id.image_clear3)
    ImageView image_clear3;

    @Bind(R.id.btn_finish)
    Button btn_finish;
    private String uniqueId;
    private boolean flag=false;

    //倒计时
    private CountDownTimer timer = new CountDownTimer(60*1000, 1000) {

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
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        title.setText("修改密码");
        uniqueId = DeviceUtils.getUniqueId(this);
    }

    @Override
    protected void initData() {
        image_clear1.setVisibility(View.GONE);
        image_clear2.setVisibility(View.GONE);
        image_clear3.setVisibility(View.GONE);


    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        editText_yanZheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (TextUtils.isEmpty(s1)){
                    isNextClick(false);
                }else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_password);

                    String msg4 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    String msg5 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2)  &&!"".equals(msg4)&&!"".equals(msg5)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
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
                    image_clear1.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);

                    String msg4 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    String msg5 = EditTextUtil.getEditTextStr(editText_phone);
                    if (!"".equals(msg2) &&!"".equals(msg4)&&!"".equals(msg5)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
                    image_clear1.setVisibility(View.VISIBLE);
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
                    image_clear3.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_yanZheng);
                    String msg3 = EditTextUtil.getEditTextpass(editText_passwordAgain);
                    String msg4 = EditTextUtil.getEditTextpass(editText_password);

                    if (!"".equals(msg2) && !"".equals(msg3) &&!"".equals(msg4)) {
                        isNextClick(true);
                    } else {
                        isNextClick(false);

                    }
                    image_clear3.setVisibility(View.VISIBLE);
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

        //editText的获取焦点变化监听
        editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String trim = EditTextUtil.getEditTextStr(editText_phone);
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
    }

    @OnClick({R.id.image_back,R.id.image_clear1,R.id.image_clear2,R.id.image_clear3,R.id.btn_finish,R.id.obtain_yanZheng})
    public void click(View view){
        switch (view.getId()){
            case R.id.obtain_yanZheng_tuxing:           //图形验证码
                final Call<ResponseBody> call1 = MyApplication.getNetApi().picture((String) SharedPreferenceUtils.get(ChangePasswordActivity.this, "app_token", ""), uniqueId, "modify");
                call1.enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                        }
                    }

                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
            case R.id.image_back:           //1.返回上一级
                this.finish();

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
                                Call<DefaultBean3> call = MyApplication.getNetApi().changeUser((String) SharedPreferenceUtils.get(ChangePasswordActivity.this, "app_token", ""), phone, EditTextUtil.getEditTextpass(editText_passwordAgain), EditTextUtil.getEditTextStr(editText_yanZheng), "modify_password");
                                call.enqueue(new Callback<DefaultBean3>() {
                                    @Override
                                    public void onResponse(Call<DefaultBean3> call, Response<DefaultBean3> response) {
                                        if (response.isSuccessful()) {
                                            DefaultBean3 data = response.body();
                                            if ("200".equals(data.getStatus())) {
                                                ToastUtils.showMessage(ChangePasswordActivity.this, "修改密码成功");
                                                MyApplication.getInstance().finishPartActivityWant("RegisterActivity");
                                                exitLogin();
                                                ChangePasswordActivity.this.finish();
                                                startActivity(new Intent(ChangePasswordActivity.this,LoginActivity.class));
                                            } else {
                                                ToastUtils.showMessage(ChangePasswordActivity.this, data.getMessage());
                                            }

                                        } else {
                                            ToastUtils.showMessage(ChangePasswordActivity.this, "未知错误");

                                        }

                                        call.cancel();
                                    }

                                    @Override
                                    public void onFailure(Call<DefaultBean3> call, Throwable t) {
                                        ToastUtils.showMessage(ChangePasswordActivity.this, "未知错误");
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
                    if (PhoneUtil.isMobileNO(str1)) {
                        Call<DefaultBean2> call = MyApplication.getNetApi().sendPhoneYanZheng((String) SharedPreferenceUtils.get(ChangePasswordActivity.this, "app_token", ""), uniqueId, "modify", str1);
                        call.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    //    boolean flag = response.body().isFlag();
                                    if ("200".equals( response.body().getStatus())) {
                                        ToastUtils.showMessage(ChangePasswordActivity.this, "验证码已发送！");
                                        timer.start();
                                    } else {
                                        ToastUtils.showMessage(ChangePasswordActivity.this, response.body().getMsg());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(ChangePasswordActivity.this, t.toString());

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
            case R.id.image_clear3:      //6.手机号清空按钮
                editText_phone.setText("");
                break;


            default:break;

        }

    }

    /**
     *  “下一步”按钮是否可点击
     * */
    private void isNextClick(boolean fla) {
        if (fla) {
            flag = true;
            btn_finish.setBackgroundResource(R.drawable.shape_btn_setting_click);
        } else {
            flag = false;
            btn_finish.setBackgroundResource(R.drawable.shape_btn_setting_none);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
