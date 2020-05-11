package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSecondActivity extends BaseActivity {
    @Bind(R.id.obtain_yanZheng_tuxing)
    ImageView obtain_yanZheng_tuxing;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.editText_password)
    EditText editText_password;
    @Bind(R.id.editText_passwordAgain)
    EditText editText_passwordAgain;
    @Bind(R.id.image_clear)
    ImageView image_clear;
    @Bind(R.id.image_clear2)
    ImageView image_clear2;
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.btn_finish)
    Button btn_finish;

    private boolean flag=false;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register_activity_second;
    }

    @Override
    protected void initView() {
        title.setText("注册");
        phone = getIntent().getStringExtra("phone");
        image_clear.setVisibility(View.GONE);
        image_clear2.setVisibility(View.GONE);
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
                    image_clear.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = EditTextUtil.getEditTextStr(editText_passwordAgain);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    image_clear.setVisibility(View.VISIBLE);
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
                        image_clear.setVisibility(View.VISIBLE);
                    } else {
                        image_clear.setVisibility(View.GONE);

                    }
                } else {
                    image_clear.setVisibility(View.GONE);
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
    @OnClick({R.id.image_back,R.id.image_clear,R.id.image_clear2,R.id.btn_finish,R.id.tv_xieYi})
    public void click(View view){
        switch (view.getId()){
            case R.id.image_back:               //1.返回上一级
                this.finish();

                break;
            case R.id.image_clear:              //2.第一个EditText的清除按钮
                editText_password.setText("");
                break;
            case R.id.image_clear2:             //3.第二个EditText的清除按钮
                editText_passwordAgain.setText("");

                break;
            case R.id.btn_finish:               //4.完成按钮


                break;
            case R.id.tv_xieYi:                 //5.用户协议
                startActivity(new Intent(this,WebActivity.class));
                break;

            default:break;
        }
    }

    private boolean isNext(){
        String phone = EditTextUtil.getEditTextStr(editText_password);
        String yanzhengma = EditTextUtil.getEditTextStr(editText_passwordAgain);

        if (phone.length()<6||phone.length()>16){
            ToastUtils.showMessage(this, "密码长度要在6~16字符之间");
        }else {
            if (TextUtils.isEmpty(yanzhengma)){
                ToastUtils.showMessage(this,"请再次输入密码");
            }else {
                if (!phone.equals(yanzhengma)){
                    ToastUtils.showMessage(this,"两次密码输入不一致");
                }else {
                    if (!checkbox.isChecked()){
                        ToastUtils.showMessage(this,"请阅读用户协议");

                    }else {


                        return true;

                    }
                }

            }

        }

        return false;
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
}
