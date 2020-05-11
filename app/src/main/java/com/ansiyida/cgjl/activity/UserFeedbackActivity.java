package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class UserFeedbackActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.text_putOut)
    TextView putOut;
    @Bind(R.id.edit_userFeedback)
    EditText edit_feedback;
    @Bind(R.id.tv_editCount)
    TextView editCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_user_feedback;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("用户反馈");
        putOut.setText("提交");
        putOut.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
//        edit_feedback.setOnKeyListener(new EditText.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//               // TODO Auto-generated method stub
//               String temp = edit_feedback.getText().toString();
//                edit_feedback.setText("");
//                edit_feedback.append(EditTextUtil.ToDBC(temp));
//                return false;
//            }
//        });

        edit_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                LogUtil.i("length","length:"+str.getBytes().length);
                int lenth=s.toString().length();
                editCount.setText(lenth+"");
            }
        });
    }
    @OnClick({R.id.image_back,R.id.text_putOut})
    public void click(View view){
        switch (view.getId()){
            case R.id.image_back:           //1.返回上一级
                this.finish();

                break;
            case R.id.text_putOut:           //2.发布
                String editTextStr = EditTextUtil.getEditTextStr(edit_feedback);
                if(!TextUtils.isEmpty(editTextStr)){
                    Call<DefaultBean> call= MyApplication.getNetApi().userFeedBack("","F","",editTextStr,(String) SharedPreferenceUtils.get(this, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                String status = response.body().getStatus();
                                if ("0001".equals(status)) {
                                    ToastUtils.showMessage(UserFeedbackActivity.this, "发布成功，感谢您的积极反馈");

                                }else {
                                    ToastUtils.showMessage(UserFeedbackActivity.this, response.body().getMessage());
                                }
                                UserFeedbackActivity.this.finish();
                            }else {
                                ToastUtils.showMessage(UserFeedbackActivity.this, "未知错误");
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(UserFeedbackActivity.this, "未知错误");

                        }
                    });
                }else {
                    ToastUtils.showMessage(this,"请填写反馈内容");

                }


                break;

            default:break;
        }
    }
}
