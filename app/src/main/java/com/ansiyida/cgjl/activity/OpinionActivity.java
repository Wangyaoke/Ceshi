package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpinionActivity extends BaseActivity {
    @Bind(R.id.editText)
    EditText editText;
    private String initStr;
    private String beforeStr = "";
    private String userName;
    private int initLength;
    private int userNameLength;
    private String ja_ask_id;
    private String ja_quote;
    private String user_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_opinion;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        Intent intent = getIntent();
        initStr =intent.getStringExtra("yinYongStr");
        ja_ask_id = intent.getStringExtra("ja_ask_id");
        ja_quote = intent.getStringExtra("ja_quote");
        userName = intent.getStringExtra("userName");
        if (ja_quote==null){
            ja_quote="";
        }
        user_quote = intent.getStringExtra("user_quote");
        if (user_quote==null){
            user_quote="";
        }
        LogUtil.i("iq","jaquo:"+ja_quote+",user:"+user_quote);
        if (initStr != null && !"".equals(initStr)) {
            userNameLength = userName.length();
            initStr = "引用【 " + userName + " " + initStr + "】";
            initLength = initStr.length();
            setSpan(initStr);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        if (initStr != null && !"".equals(initStr)) {

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    LogUtil.i("xxx", "before:" + s.toString());
                    beforeStr = s.toString();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    LogUtil.i("xxx", "change:" + s.toString());

                }

                @Override
                public void afterTextChanged(Editable s) {
                    LogUtil.i("xxx", "after:" + s.toString());
                    String str1 = s.toString();
                    int length = str1.length();
                    if (length < initLength) {
                        setSpan(initStr);
                    } else {
                        String sub = str1.substring(0, initLength);
                        if (!sub.equals(initStr)) {
                            setSpan(beforeStr);
                        }
                    }
                }
            });

        }
    }

    @OnClick({R.id.image_back, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:                               //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:                              //2.发表观点按钮
                if (initStr!=null&&!"".equals(initStr)) {
                    String editTextStr= EditTextUtil.getEditTextStr(editText);
                    String substring = editTextStr.substring(initLength, editTextStr.length());
                    if (!"".equals(substring.trim())){
                        Call<DefaultBean> call= MyApplication.getNetApi().sendOptions((String) SharedPreferenceUtils.get(this,"app_token",""),ja_ask_id,substring,ja_quote,user_quote);
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()){
                                    String status = response.body().getStatus();
                                    if (status!=null&&"0001".equals(status)){
                                        setResult(520);
                                        ToastUtils.showMessage(OpinionActivity.this,"发表成功");
                                    }else {
                                        ToastUtils.showMessage(OpinionActivity.this, response.body().getMessage());
                                    }
                                }else {
                                    ToastUtils.showMessage(OpinionActivity.this,"发表失败");

                                }
                                call.cancel();
                                OpinionActivity.this.finish();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {
                                ToastUtils.showMessage(OpinionActivity.this,"发表失败");
                                call.cancel();
                                OpinionActivity.this.finish();
                            }
                        });
                    }else {
                        ToastUtils.showMessage(this,"请输入有效内容");
                    }
                }else {
                    String editTextStr= EditTextUtil.getEditTextStr(editText);
                    if (!"".equals(editTextStr)){
                        Call<DefaultBean> call= MyApplication.getNetApi().sendOptions((String) SharedPreferenceUtils.get(this,"app_token",""),ja_ask_id,editTextStr,ja_quote,user_quote);
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()){
                                    String status = response.body().getStatus();
                                    if (status!=null&&"0001".equals(status)){
                                        setResult(520);
                                        ToastUtils.showMessage(OpinionActivity.this,"发表成功");
                                    }else {
                                        ToastUtils.showMessage(OpinionActivity.this, response.body().getMessage());
                                    }
                                }else {
                                    ToastUtils.showMessage(OpinionActivity.this,"发表失败");

                                }
                                call.cancel();
                                OpinionActivity.this.finish();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {
                                ToastUtils.showMessage(OpinionActivity.this,"发表失败");
                                call.cancel();
                                OpinionActivity.this.finish();
                            }
                        });
                    }else {
                        ToastUtils.showMessage(this,"请输入有效内容");
                    }
                }

                    break;

        }
    }

    private void setSpan(String spanString) {
        SpannableString spannableString = new SpannableString(spanString);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#3146af")), 4, 4 + userNameLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#9b9d9d")), 5 + userNameLength, initLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setText(spannableString);
        editText.setSelection(initLength);
    }
}
