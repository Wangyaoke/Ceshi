package com.ansiyida.cgjl.activity.cgjl_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ListPopWindowAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialShopCommunicateActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.services_save)
    Button servicesSave;

    @Bind(R.id.Company_name)
    EditText CompanyName;
    @Bind(R.id.Company_num)
    TextView CompanyNum;
    @Bind(R.id.ProjectProfile)
    EditText ProjectProfileEdit;
    @Bind(R.id.ProjectProfile_num)
    TextView ProjectProfileNum;
    @Bind(R.id.company_IndustryCategory_choose)
    RadioButton companyIndustryCategoryChoose;
    @Bind(R.id.financing_num)
    TextView financingNum;
    @Bind(R.id.Rotation_choose)
    RadioButton RotationChoose;
    @Bind(R.id.Annualincome_edit)
    EditText AnnualincomeEdit;
    @Bind(R.id.Annualincome_num)
    TextView AnnualincomeNum;
    @Bind(R.id.Contacts)
    EditText Contacts;
    @Bind(R.id.Contacts_num)
    TextView ContactsNum;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.financingEdit)
    EditText financingEdit;
    @Bind(R.id.applicationService_scroll)
    ScrollView applicationServiceScroll;
    @Bind(R.id.retrun_SY)
    Button retrunSY;
    @Bind(R.id.submit_success_Rel)
    RelativeLayout submitSuccessRel;
    private String phoneStr;
    private String projectProfileEditStr;
    private String companyIndustryCategoryChooseStr;
    private String financingEditStr;
    private String annualincomeEditStr;
    private String companyNameStr;
    private String contactsStr;
    private String rotationChooseStr;
    private ListPopupWindow listPopupWindow;
    private List<String> PopWindowList = new ArrayList<>();
    private ListPopWindowAdapter listPopWindowAdapter;
    private Drawable drawableDown;
    private Drawable drawableUp;
    private String mode;
    private int id;
    private String [] ROTAaraay = new String[]{"种子轮","天使轮","preA轮","A轮","A+轮","B轮","C轮","BAT轮","preIPO"};
    private List<String> HYList=new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("onResponse", "onResponse: " + msg.obj);
            Gson gson = new Gson();
            boolean contains = ((String) msg.obj).contains("成功");
            if(contains){
                popwindow();
            }else{
                ToastUtils.showMessage(IndustrialShopCommunicateActivity.this,"提交失败！");
            }
        }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_application_services;
    }

    @Override
    protected void initView() {
        listPopupWindow = new ListPopupWindow(this);
        listPopWindowAdapter = new ListPopWindowAdapter(PopWindowList, this);
        listPopupWindow.setAdapter(listPopWindowAdapter);
        listPopupWindow.setModal(true);
    }

    @Override
    protected void initData() {
        getHYType();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        textTitle.setText("立即沟通");
        RotationChoose.setText(ROTAaraay[0]);
        drawableDown = getResources().getDrawable(R.mipmap.introdusi_down);
        drawableUp = getResources().getDrawable(R.mipmap.introdusi_up);
        drawableDown.setBounds(0, 0, drawableDown.getMinimumWidth(), drawableDown.getMinimumHeight());
        drawableUp.setBounds(0, 0, drawableUp.getMinimumWidth(), drawableUp.getMinimumHeight());
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        servicesSave.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        retrunSY.setOnClickListener(this);
        CompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 20) { //判断EditText中输入的字符数是不是已经大于6
                    CompanyName.setText(s.toString().substring(0, 20)); //设置EditText只显示前面6位字符
                    CompanyName.setSelection(20);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 20 - number;
                if (lenght > 0) {
                    CompanyNum.setText(lenght + "");
                } else {
                    CompanyNum.setText("最多二十字!");
                }
            }
        });
        ProjectProfileEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    ProjectProfileEdit.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    ProjectProfileEdit.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    ProjectProfileNum.setText(lenght + "");
                } else {
                    ProjectProfileNum.setText("最多五百字!");
                }
            }
        });
        ProjectProfileEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (ProjectProfileEdit.getText().length() > 0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        financingEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    financingEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    financingEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    financingNum.setText(lenght + "");
                } else {
                    financingNum.setText("最多五十字!");
                }
            }
        });
        AnnualincomeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    AnnualincomeEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    AnnualincomeEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    AnnualincomeNum.setText(lenght + "");
                } else {
                    AnnualincomeNum.setText("最多五十字!");
                }
            }
        });
        Contacts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    Contacts.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    Contacts.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    ContactsNum.setText(lenght + "");
                } else {
                    ContactsNum.setText("最多五十字!");
                }
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    phone.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    phone.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    phoneNum.setText(lenght + "");
                } else {
                    phoneNum.setText("最多五十个字!");
                }
            }
        });

        companyIndustryCategoryChoose.setOnClickListener(this);
        RotationChoose.setOnClickListener(this);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeText(position);
            }
        });
        listPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeDrawableRight();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                this.finish();
                break;
            case R.id.services_save:

                boolean NullBol = judgeNUll();
                if (NullBol) {
                    boolean PaternBol = judgePE();
                    if (PaternBol) {
                        submit();
                    }
                }
                break;
            case R.id.company_IndustryCategory_choose:
                mode = "HYLX";
                companyIndustryCategoryChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, companyIndustryCategoryChoose);
                break;
            case R.id.Rotation_choose:
                mode = "ROTA";
                RotationChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, RotationChoose);
                break;
            case R.id.retrun_SY:
                this.finish();
                break;
        }
    }

    private void submit() {
        getpageData();
        String app_token = (String)SharedPreferenceUtils.get(this, "app_token", "");

        FormBody build = new FormBody.Builder()
                .add("financialServeId", id+"")
                .add("companyName", companyNameStr)
                .add("projectIntroduce", projectProfileEditStr)
                .add("tradeKind", companyIndustryCategoryChooseStr)
                .add("financingDemand", financingEditStr)
                .add("round",rotationChooseStr)
                .add("yearIncome", annualincomeEditStr)
                .add("linkman", contactsStr)
                .add("phone", phoneStr)
                .build();
        String format = String.format(Constant.URL + "financialServe/exchange?");
        Request build1 = new Request.Builder()
                .addHeader("token",app_token)
                .url(format)
                .post(build)
                .build();
        MyApplication.client.newCall(build1).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("onResponse", "onResponse: " + e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                handler.sendMessageDelayed(handler.obtainMessage(0, response.body().string()), 500);
            }
        });

    }

    private boolean judgePE() {
        getpageData();
        return true;
    }

    private boolean judgeNUll() {
        getpageData();
        if (phoneStr.isEmpty() || projectProfileEditStr.isEmpty() || companyIndustryCategoryChooseStr.isEmpty() || financingEditStr.isEmpty() || rotationChooseStr.isEmpty() || annualincomeEditStr.isEmpty() || contactsStr.isEmpty() || companyNameStr.isEmpty()) {
            ToastUtils.showMessage(IndustrialShopCommunicateActivity.this, "请将信息填写完善！");
            return false;
        } else {
            return true;
        }
    }

    private void getpageData() {
        companyNameStr = CompanyName.getText().toString();
        projectProfileEditStr = ProjectProfileEdit.getText().toString();
        companyIndustryCategoryChooseStr = companyIndustryCategoryChoose.getText().toString();
        financingEditStr = financingEdit.getText().toString();
        rotationChooseStr = RotationChoose.getText().toString();
        annualincomeEditStr = AnnualincomeEdit.getText().toString();
        contactsStr = Contacts.getText().toString();
        phoneStr = phone.getText().toString();
    }

    public void popwindow() {
        View view = View.inflate(this, R.layout.pop_ok_animation, null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double width = (double) dm.widthPixels / 2;
        final PopupWindow popupWindow = new PopupWindow(view, (int) width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(IndustrialShopCommunicateActivity.this, (float) 1);
                applicationServiceScroll.setVisibility(View.GONE);
                submitSuccessRel.setVisibility(View.VISIBLE);
                imageBack.setVisibility(View.GONE);
            }
        });
        ImageView pop_ok_image = view.findViewById(R.id.pop_ok_image);
        TextView AuditCenter = view.findViewById(R.id.go_AuditCenter);
        ImageView dismissImage = view.findViewById(R.id.dismiss_pop_ok);
        TextView textView = view.findViewById(R.id.pop_text);
        LinearLayout linearLayout = view.findViewById(R.id.pop_textLinear);
        TextView textView1 = view.findViewById(R.id.text_ok);
        textView1.setVisibility(View.GONE);
        textView.setTextSize(15);
        textView.setText("提交成功");
        textView.setTextColor(getResources().getColor(R.color.color_blue));
        linearLayout.setVisibility(View.GONE);
        backgroundAlpha(this, (float) 0.5);
        //dismissPOpwindow
        dismissImage.setVisibility(View.GONE);
        //popupWindow.showAsDropDown(FRRepresentative);
        popupWindow.showAtLocation(imageBack, Gravity.CENTER, 0, 0);
        //开启动画
        pop_ok_image.setImageResource(R.mipmap.application_ok);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
                applicationServiceScroll.setVisibility(View.GONE);
                submitSuccessRel.setVisibility(View.VISIBLE);
                imageBack.setVisibility(View.GONE);
            }

        }, 2000);

    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void ListPopwindow(String mode, RadioButton radioButton) {
        PopWindowList.clear();
        if (mode.equals("HYLX")) {
            for (int i = 0; i < HYList.size(); i++) {
                PopWindowList.add(HYList.get(i));
            }
            listPopupWindow.setWidth(companyIndustryCategoryChoose.getMeasuredWidth());
            listPopupWindow.setAnchorView(companyIndustryCategoryChoose);
        } else if (mode.equals("ROTA")) {
            for (int i = 0; i < ROTAaraay.length; i++) {
                PopWindowList.add(ROTAaraay[i]);
            }
            listPopupWindow.setWidth(RotationChoose.getMeasuredWidth());
            listPopupWindow.setHeight(400);
            listPopupWindow.setAnchorView(RotationChoose);
        }
        listPopupWindow.show();
        listPopWindowAdapter.notifyDataSetChanged();
    }

    private void changeText(int position) {
        if (mode.equals("HYLX")) {
            companyIndustryCategoryChoose.setText(PopWindowList.get(position));
        } else if (mode.equals("ROTA")) {
            RotationChoose.setText(PopWindowList.get(position));
        }
        listPopupWindow.dismiss();
    }

    private void changeDrawableRight() {
        if (mode.equals("HYLX")) {
            companyIndustryCategoryChoose.setCompoundDrawables(null, null, drawableUp, null);
        } else if (mode.equals("ROTA")) {
            RotationChoose.setCompoundDrawables(null, null, drawableUp, null);
        }
    }
    private void getHYType() {
        Call<OptionBean> call = MyApplication.getNetApi().getoption((String) SharedPreferenceUtils.get(this, "app_token", ""), 1);
        call.enqueue(new Callback<OptionBean>() {
            @Override
            public void onResponse(Call<OptionBean> call, Response<OptionBean> response) {
                if (response.isSuccessful()) {
                    OptionBean body = response.body();
                    if (body.getStatus() == 200) {
                        companyIndustryCategoryChoose.setText(body.getData().get(0).getName());
                        for (int i = 0; i <body.getData().size() ; i++) {
                            HYList.add(body.getData().get(i).getName());
                        }
                        companyIndustryCategoryChoose.setText(body.getData().get(0).getName());
                    }
                }
            }
            @Override
            public void onFailure(Call<OptionBean> call, Throwable t) {

            }
        });
    }
}
