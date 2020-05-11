package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.PhoneUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.image_clear1)
    ImageView clear1;
    @Bind(R.id.image_clear2)
    ImageView clear2;
    @Bind(R.id.editText1)
    EditText editText1;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.btn_next)
    Button btnNext;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("修改绑定手机");
        String strEdit1 = getEditTextStr(editText1);
        if ("".equals(strEdit1)) {
            clear1.setVisibility(View.GONE);
        }
        clear2.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        //editText的输入变化监听
        editText1.addTextChangedListener(new TextWatcher() {
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
                    clear1.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg2 = getEditTextStr(editText2);
                    if (!"".equals(msg2)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    clear1.setVisibility(View.VISIBLE);
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
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
                    clear2.setVisibility(View.GONE);
                    isNextClick(false);
                } else {
                    String msg1 = getEditTextStr(editText1);
                    if (!"".equals(msg1)) {
                        isNextClick(true);

                    } else {
                        isNextClick(false);

                    }
                    clear2.setVisibility(View.VISIBLE);
                }
            }
        });

        //editText的获取焦点变化监听
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isClearCancle(editText1, clear1);
                } else {
                    clear1.setVisibility(View.GONE);
                }
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isClearCancle(editText2, clear2);
                } else {
                    clear2.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.image_back, R.id.btn_next, R.id.image_clear1, R.id.image_clear2})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:           //1.返回上一级
                this.finish();

                break;
            case R.id.btn_next:             //2.下一步按钮
                if (flag&&checkPhoneNum()) {
                    String editTextStr = getEditTextStr(editText2);
                    Intent intent=new Intent(this,BindPhoneActivity.class);
                    intent.putExtra("phone",editTextStr);
                    startActivity(intent);
                }
                break;
            case R.id.image_clear1:         //3.edit1中的清空按钮
                editText1.setText("");

                break;
            case R.id.image_clear2:         //4.edit2中的清空按钮
                editText2.setText("");

                break;

            default:
                break;

        }
    }
    /**
     *  “下一步”按钮是否可点击
     * */
    private void isNextClick(boolean fla) {
        if (fla) {
            flag = true;
            btnNext.setBackgroundResource(R.drawable.shape_btn_setting_click);
        } else {
            flag = false;
            btnNext.setBackgroundResource(R.drawable.shape_btn_setting_none);
        }
    }
    /**
     *  每个EditText上面的小叉号是否隐藏
     * */
    private void isClearCancle(EditText editText, ImageView iv) {
        String trim = getEditTextStr(editText);
        if (!"".equals(trim)) {
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.GONE);

        }
    }
    private String getEditTextStr(EditText editText){
        return editText.getText().toString().trim();
    }
    private boolean checkPhoneNum(){
        String str1=getEditTextStr(editText1);

        if(!TextUtils.isEmpty(str1)){
            if (!PhoneUtil.isMobileNO(str1)){
                ToastUtils.showMessage(this,"绑定手机号不合法");
                return false;
            }else {
                String str2=getEditTextStr(editText2);
                if (!PhoneUtil.isMobileNO(str2)){
                    ToastUtils.showMessage(this,"新手机号不合法");
                    return false;
                }else {
                    String phoneNum= (String) SharedPreferenceUtils.get(this,"phoneNum","null");
                    if (str1.equals(phoneNum)){
                        if (str2.equals(str1)){
                            ToastUtils.showMessage(this,"您当前绑定的就是该手机号");
                            return false;
                        }else {

                            return true;
                        }
                    }else {
                        ToastUtils.showMessage(this,"绑定手机号输入错误");
                        return false;
                    }
                }
            }
        }
        return false;
    }


}
