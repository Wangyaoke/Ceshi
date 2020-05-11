package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class PrivacyActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.bindNum)
    TextView bindNum;
    private String oldPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("账号和隐私设置");
        oldPhone=(String) SharedPreferenceUtils.get(this, "phoneNum", "null");
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

    @Override
    protected void onResume() {
        super.onResume();
        String phone = (String) SharedPreferenceUtils.get(this, "phoneNum", "null");
        if (phone.length()==11){
            bindNum.setText(phoneMi(phone));

        }else {
            bindNum.setText("绑定手机号");
        }
        if (!oldPhone.equals(phone)){
            ToastUtils.showMessage(this,"修改成功");
            oldPhone=phone;

        }
    }

    /**
   *    按钮点击
   * */
    @OnClick({R.id.relative_phoneNum,R.id.relative_changePassword,R.id.image_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.relative_phoneNum:        //1.绑定或更改绑定手机号
                if ("绑定手机号".equals(bindNum.getText())){
                    startActivity(new Intent(this,BindPhoneActivity.class));
                }else {
                    startActivity(new Intent(this,ChangePhoneActivity.class));
                }

                break;
            case R.id.relative_changePassword:  //2.修改密码
                startActivity(new Intent(this,ChangePasswordActivity.class));

                break;
            case R.id.image_back:               //3.返回上一级
                this.finish();
                break;

            default:break;
        }
    }
    private String phoneMi(String phone){
        String phoneBegin=phone.substring(0,3);
        String phoneEnd=phone.substring(7, 11);
        return phoneBegin+"****"+phoneEnd;
    }

}
