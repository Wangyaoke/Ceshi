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
import android.view.KeyEvent;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ListPopWindowAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitaryBean;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionBean;
import com.ansiyida.cgjl.bean.cgjl_bean.QyZjBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialParkSettledInActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.OnePage_Scroll)
    ScrollView OnePageScroll;
    @Bind(R.id.TwoPage_Scroll)
    ScrollView TwoPageScroll;
    @Bind(R.id.next_Page)
    Button nextPage;
    @Bind(R.id.SaveAndSubmit_btn)
    Button SaveAndSubmitBtn;
    //第一页
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.company_name_edit)
    EditText companyNameEdit;
    @Bind(R.id.company_name_num)
    TextView companyNameNum;
    @Bind(R.id.company_website_edit)
    EditText companyWebsiteEdit;
    @Bind(R.id.company_website_num)
    TextView companyWebsiteNum;
    @Bind(R.id.company_legalperson_edit)
    EditText companyLegalpersonEdit;
    @Bind(R.id.company_legalperson_num)
    TextView companyLegalpersonNum;

    @Bind(R.id.company_RegistrationAddress_edit)
    EditText companyRegistrationAddressEdit;
    @Bind(R.id.company_RegistrationAddress_num)
    TextView companyRegistrationAddressNum;
    @Bind(R.id.company_IndustryCategory_choose)
    RadioButton companyIndustryCategoryChoose;
    @Bind(R.id.company_Mainbusiness_edit)
    EditText companyMainbusinessEdit;
    @Bind(R.id.company_Mainbusiness_num)
    TextView companyMainbusinessNum;
    @Bind(R.id.company_IntroductionEnterprises_edit)
    EditText companyIntroductionEnterprisesEdit;
    @Bind(R.id.company_IntroductionEnterprises_num)
    TextView companyIntroductionEnterprisesNum;
    @Bind(R.id.company_MainBusinessIntroduction_edit)
    EditText companyMainBusinessIntroductionEdit;
    @Bind(R.id.company_MainBusinessIntroduction_num)
    TextView companyMainBusinessIntroductionNum;
    @Bind(R.id.company_Levelofmilitaryparticipation_choose)
    RadioButton companyLevelofmilitaryparticipationChoose;

    //第二页
    @Bind(R.id.Industrialpark_choose)
    RadioButton IndustrialparkChoose;
    @Bind(R.id.IndustrialparkArea_edit)
    EditText IndustrialparkAreaEdit;
    @Bind(R.id.IndustrialparkArea_num)
    TextView IndustrialparkAreaNum;
    @Bind(R.id.IndustrialparkPurpose_choose)
    RadioButton IndustrialparkPurposeChoose;
    @Bind(R.id.OtherNeeds_edit)
    EditText OtherNeedsEdit;
    @Bind(R.id.OtherNeeds_num)
    TextView OtherNeedsNum;
    @Bind(R.id.HousekeepingTime_choose)
    RadioButton HousekeepingTimeChoose;
    @Bind(R.id.HousekeepingDuration_choose)
    RadioButton HousekeepingDurationChoose;
    @Bind(R.id.Contacts_edit)
    EditText ContactsEdit;
    @Bind(R.id.Contacts_num)
    TextView ContactsNum;
    @Bind(R.id.Phone_edit)
    EditText PhoneEdit;
    @Bind(R.id.Phone_num)
    TextView PhoneNum;
    @Bind(R.id.Email_edit)
    EditText EmailEdit;
    @Bind(R.id.Email_num)
    TextView EmailNum;
    @Bind(R.id.industrialpark_name)
    TextView industrialparkName;
    private ListPopupWindow listPopupWindow;
    private boolean OneBol = false;
    private boolean TwoBol = false;
    private List<String> HYList = new ArrayList<>();
    private List<String> ListPopWindow = new ArrayList<>();
    private ListPopWindowAdapter listPopWindowAdapter;
    private String mode;
    private Drawable drawableDown;
    private Drawable drawableUp;
    private String companyname;
    private String companywebsite;
    private String companyLegalperson;
    private String registerAddress;
    private String companyMainbusiness;
    private String introductionEnter;
    private String companyMainBusinessIntro;
    private String hyTypeStr;
    private String levelTypeStr;
    private String industrparkName;
    private String industrialparkNameStr;
    private String industrialparkAreaEditStr;
    private String otherNeedsEditStr;
    private String contactsEditStr;
    private String phoneEditStr;
    private String emailEditStr;
    private String industrialparkChooseStr;
    private String industrialparkPurposeChooseStr;
    private String housekeepingTimeChooseStr;
    private String housekeepingDurationChooseStr;
    private boolean peBol;
    private int id;
    private String PageBJ = "one";
    private String[] HYArray = new String[]{"专业设备制造业", "通用设备制造业"};
    private String[] McjArray = new String[]{"想转型参与", "有产品想参与", "有产品已参与"};
    private String[] CYYArray = new String[]{"产业园入住", "金融服务"};
    private String[] CYYYTArray = new String[]{"研发", "生产", "研发+生产"};
    private String[] YFSJArray = new String[]{"立即用房", "三个月后用房", "半年后用房", "可协商时间"};
    private String[] YFSCArray = new String[]{"1年", "2年", "3年", "4年", "5年", "6年", "7年", "8年", "9年", "10年", "长期"};
    private String address;
    private List<String> industrialParklist = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Gson gson = new Gson();
            QyZjBean qyZjBean = gson.fromJson((String) msg.obj, QyZjBean.class);
            boolean contains = ((String) msg.obj).contains("成功");
            if(contains){
                popwindow();
            }else{
                ToastUtils.showMessage(IndustrialParkSettledInActivity.this,"提交失败！");
            }
        }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_application_information;
    }

    @Override
    protected void initView() {
        listPopupWindow = new ListPopupWindow(this);
        listPopWindowAdapter = new ListPopWindowAdapter(ListPopWindow, this);
        listPopupWindow.setAdapter(listPopWindowAdapter);
        listPopupWindow.setWidth(ListPopupWindow.MATCH_PARENT);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setModal(true);
    }

    @Override
    protected void initData() {
        getHYType();
        getIndustrial();
        //获取上个页面传值
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        address = intent.getStringExtra("address");
        industrialparkName.setText(address);
        drawableDown = getResources().getDrawable(R.mipmap.introdusi_down);
        drawableUp = getResources().getDrawable(R.mipmap.introdusi_up);
        drawableDown.setBounds(0, 0, drawableDown.getMinimumWidth(), drawableDown.getMinimumHeight());
        drawableUp.setBounds(0, 0, drawableUp.getMinimumWidth(), drawableUp.getMinimumHeight());
        //初始化数据
        companyLevelofmilitaryparticipationChoose.setText(McjArray[0]);
        IndustrialparkPurposeChoose.setText(CYYYTArray[0]);
        HousekeepingTimeChoose.setText(YFSJArray[0]);
        HousekeepingDurationChoose.setText(YFSCArray[0]);
    }

    private void getIndustrial() {
        String apptoken = (String) SharedPreferenceUtils.get(this, "app_token", "");
        Call<CiViMilitaryBean> ciViMilitaryBeanCall = MyApplication.getNetApi().getcivilMilitary(apptoken, 1, 20, mode, address);
        ciViMilitaryBeanCall.enqueue(new Callback<CiViMilitaryBean>() {
            @Override
            public void onResponse(Call<CiViMilitaryBean> call, Response<CiViMilitaryBean> response) {
                if (response.isSuccessful()) {
                    CiViMilitaryBean body = response.body();
                    if (body.getStatus() == 200) {
                        List<CiViMilitaryBean.DataBean.ListBean> list = body.getData().getList();
                        Log.e("列表", "onResponse: " + body.getData().toString());
                        for (int i = 0; i < list.size(); i++) {
                            industrialParklist.add(list.get(i).getName());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<CiViMilitaryBean> call, Throwable t) {
                Log.e("CiviFragment", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        nextPage.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        SaveAndSubmitBtn.setOnClickListener(this);
        companyIndustryCategoryChoose.setOnClickListener(this);
        companyLevelofmilitaryparticipationChoose.setOnClickListener(this);
        IndustrialparkChoose.setOnClickListener(this);
        IndustrialparkPurposeChoose.setOnClickListener(this);
        HousekeepingTimeChoose.setOnClickListener(this);
        HousekeepingDurationChoose.setOnClickListener(this);
        IndustrialparkChoose.setOnClickListener(this);
        IndustrialparkChoose.setOnClickListener(this);
        companyMainBusinessIntroductionEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (companyMainBusinessIntroductionEdit.getText().length() > 0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        companyIntroductionEnterprisesEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (companyIntroductionEnterprisesEdit.getText().length() > 0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        OtherNeedsEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (OtherNeedsEdit.getText().length() > 0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
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
        companyNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    companyNameEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    companyNameEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    companyNameNum.setText(lenght + "");
                } else {
                    companyNameNum.setText("最多五十字!");
                }
            }
        });
        companyWebsiteEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    companyWebsiteEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    companyWebsiteEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    companyWebsiteNum.setText(lenght + "");
                } else {
                    companyWebsiteNum.setText("最多五十字!");
                }
            }
        });
        companyLegalpersonEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    companyLegalpersonEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    companyLegalpersonEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    companyLegalpersonNum.setText(lenght + "");
                } else {
                    companyLegalpersonNum.setText("最多五十字!");
                }
            }
        });
        companyRegistrationAddressEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    companyRegistrationAddressEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    companyRegistrationAddressEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    companyRegistrationAddressNum.setText(lenght + "");
                } else {
                    companyRegistrationAddressNum.setText("最多五十字!");
                }
            }
        });
        companyMainbusinessEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    companyMainbusinessEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    companyMainbusinessEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    companyMainbusinessNum.setText(lenght + "");
                } else {
                    companyMainbusinessNum.setText("最多五十字!");
                }
            }
        });
        companyIntroductionEnterprisesEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    companyIntroductionEnterprisesEdit.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    companyIntroductionEnterprisesEdit.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    companyIntroductionEnterprisesNum.setText(lenght + "");
                } else {
                    companyIntroductionEnterprisesNum.setText("最多五百字!");
                }
            }
        });
        companyMainBusinessIntroductionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    companyMainBusinessIntroductionEdit.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    companyMainBusinessIntroductionEdit.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    companyMainBusinessIntroductionNum.setText(lenght + "");
                } else {
                    companyMainBusinessIntroductionNum.setText("最多五百字!");
                }
            }
        });
        IndustrialparkAreaEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    IndustrialparkAreaEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    IndustrialparkAreaEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    IndustrialparkAreaNum.setText(lenght + "");
                } else {
                    IndustrialparkAreaNum.setText("最多五十字!");
                }
            }
        });
        OtherNeedsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    OtherNeedsEdit.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    OtherNeedsEdit.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    OtherNeedsNum.setText(lenght + "");
                } else {
                    OtherNeedsNum.setText("最多五百字!");
                }
            }
        });
        ContactsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    ContactsEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    ContactsEdit.setSelection(50);//让光标移至末端
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
        PhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    PhoneEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    PhoneEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    PhoneNum.setText(lenght + "");
                } else {
                    PhoneNum.setText("最多五十字!");
                }
            }
        });
        EmailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) { //判断EditText中输入的字符数是不是已经大于6
                    EmailEdit.setText(s.toString().substring(0, 50)); //设置EditText只显示前面6位字符
                    EmailEdit.setSelection(50);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 50 - number;
                if (lenght > 0) {
                    EmailNum.setText(lenght + "");
                } else {
                    EmailNum.setText("最多五十字!");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                if (PageBJ.equals("two")) {
                    PageBJ = "one";
                    OnePageScroll.setVisibility(View.VISIBLE);
                    TwoPageScroll.setVisibility(View.GONE);
                } else {
                    this.finish();
                }
                break;
            case R.id.next_Page:
                OneBol = judgeOnePage();
                if (OneBol) {
                    try {
                        IndustrialparkChoose.setText(industrialParklist.get(0));
                    } catch (Exception e) {
                        Log.e("Exception", "onClick: " + e.getMessage());
                    }
                    PageBJ = "two";
                    OnePageScroll.setVisibility(View.GONE);
                    TwoPageScroll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.SaveAndSubmit_btn:
                TwoBol = judgeTwoPage();
                peBol = judgePhoneAndEmail();
                if (OneBol && TwoBol && peBol) {
                    //提交接口
                    submit();
                }
                break;
            case R.id.company_IndustryCategory_choose:
                mode = "HYType";
                companyIndustryCategoryChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, companyIndustryCategoryChoose);
                break;
            case R.id.company_Levelofmilitaryparticipation_choose:
                mode = "McJ";
                companyLevelofmilitaryparticipationChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, companyLevelofmilitaryparticipationChoose);
                break;
            case R.id.Industrialpark_choose:
                mode = "CYY";
                IndustrialparkChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, IndustrialparkChoose);
                break;
            case R.id.IndustrialparkPurpose_choose:
                mode = "CYYYT";
                IndustrialparkPurposeChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, IndustrialparkPurposeChoose);
                break;
            case R.id.HousekeepingTime_choose:
                mode = "YFSJ";
                HousekeepingTimeChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, HousekeepingTimeChoose);
                break;
            case R.id.HousekeepingDuration_choose:
                mode = "YFSC";
                HousekeepingDurationChoose.setCompoundDrawables(null, null, drawableDown, null);
                ListPopwindow(mode, HousekeepingDurationChoose);
                break;
        }
    }


    private void submit() {
        final String app_token = (String) SharedPreferenceUtils.get(IndustrialParkSettledInActivity.this, "app_token", "");
        //第一页数据
        getOnePageData();
        //第二页
        getTwoPageData();
        FormBody build = new FormBody.Builder()
                .add("companyName", companyname)
                .add("companyWebsite", companywebsite)
                .add("companyLegal", companyLegalperson)
                .add("companyAddress", registerAddress)
                .add("tradeType", hyTypeStr)
                .add("businessKeyword", companyMainbusiness)
                .add("companySummary", introductionEnter)
                .add("businessSummary", companyMainBusinessIntro)
                .add("degree", levelTypeStr)
                .add("parkAddress", industrialparkNameStr)
                .add("parkName", industrialparkChooseStr)
                .add("parkDemand", industrialparkAreaEditStr)
                .add("parkPurpose", industrialparkPurposeChooseStr)
                .add("otherDemand", otherNeedsEditStr)
                .add("startTime", housekeepingTimeChooseStr)
                .add("duration", housekeepingDurationChooseStr)
                .add("contacts", contactsEditStr)
                .add("contactsPhone", phoneEditStr)
                .add("contactsEmail", emailEditStr)
                .add("civilMilitaryId", id + "")
                .build();
        String format = String.format(Constant.URL + "industrialPack?");
        Request build1 = new Request.Builder()
                .addHeader("token",app_token)
                .url(format)
                .put(build)
                .build();
        MyApplication.client.newCall(build1).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("onResponse", "onResponse: " + e.getMessage());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                handler.sendMessageDelayed(handler.obtainMessage(0,response.body().string()),500);
            }
        });
    }
    private void getTwoPageData() {
        industrialparkNameStr = industrialparkName.getText().toString();
        industrialparkAreaEditStr = IndustrialparkAreaEdit.getText().toString();
        otherNeedsEditStr = OtherNeedsEdit.getText().toString();
        contactsEditStr = ContactsEdit.getText().toString();
        phoneEditStr = PhoneEdit.getText().toString();
        emailEditStr = EmailEdit.getText().toString();
        industrialparkChooseStr = IndustrialparkChoose.getText().toString();
        industrialparkPurposeChooseStr = IndustrialparkPurposeChoose.getText().toString();
        housekeepingTimeChooseStr = HousekeepingTimeChoose.getText().toString();
        housekeepingDurationChooseStr = HousekeepingDurationChoose.getText().toString();
    }
    private void getOnePageData() {
        companyname = companyNameEdit.getText().toString();
        companywebsite = companyWebsiteEdit.getText().toString();
        companyLegalperson = companyLegalpersonEdit.getText().toString();
        registerAddress = companyRegistrationAddressEdit.getText().toString();
        companyMainbusiness = companyMainbusinessEdit.getText().toString();
        introductionEnter = companyIntroductionEnterprisesEdit.getText().toString();
        companyMainBusinessIntro = companyMainBusinessIntroductionEdit.getText().toString();
        hyTypeStr = companyIndustryCategoryChoose.getText().toString();
        levelTypeStr = companyLevelofmilitaryparticipationChoose.getText().toString();
    }
    private boolean judgeOnePage() {
        getOnePageData();
        if(companyname.isEmpty() || companywebsite.isEmpty()  || companyMainbusiness.isEmpty() || introductionEnter.isEmpty()||  hyTypeStr.isEmpty() || levelTypeStr.isEmpty() ||companyMainBusinessIntro.isEmpty()){
            ToastUtils.showMessage(IndustrialParkSettledInActivity.this,"本页面数据不能为空！");
            return false;
        }else{
            return true;
        }
    }
    private boolean judgeTwoPage() {
        getTwoPageData();
        if(industrialparkNameStr.isEmpty() || industrialparkAreaEditStr.isEmpty()|| contactsEditStr.isEmpty() || phoneEditStr.isEmpty() || emailEditStr.isEmpty() || industrialparkChooseStr.isEmpty()|| industrialparkPurposeChooseStr.isEmpty()
            || housekeepingTimeChooseStr.isEmpty() || housekeepingDurationChooseStr.isEmpty()){
            ToastUtils.showMessage(IndustrialParkSettledInActivity.this,"本页面数据不能为空！");
            return false;
        }else{
            return true;
        }
    }

    private boolean judgePhoneAndEmail() {
        Pattern EMAIL = Pattern.compile(Constant.REGEX_EMAIL);
        boolean emailbol= false;
        if(EMAIL.matcher(EmailEdit.getText().toString()).matches()){
            emailbol=true;
        }else{
            ToastUtils.showMessage(IndustrialParkSettledInActivity.this,"邮箱格式不正确！");
        }

        if( emailbol){
            return true;
        }else{
            return false;
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
                    }
                }
            }
            @Override
            public void onFailure(Call<OptionBean> call, Throwable t) {

            }
        });
    }
    public void popwindow(){
        View view = View.inflate(this,R.layout.pop_ok_animation,null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double  width = (double) dm.widthPixels/2;
        final PopupWindow popupWindow  = new PopupWindow(view, (int) width, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(IndustrialParkSettledInActivity.this,(float)1);
                finish();
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
        backgroundAlpha(this,(float) 0.5);
        dismissImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                backgroundAlpha(IndustrialParkSettledInActivity.this,(float)1);
                finish();
            }
        });
        popupWindow.showAtLocation(imageBack, Gravity.CENTER,0,0);
        //开启动画
        pop_ok_image.setImageResource(R.mipmap.application_ok);
 /*       AnimationDrawable animationDrawable = (AnimationDrawable) pop_ok_image.getDrawable();
        animationDrawable.start();*/
    }
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
    public void ListPopwindow(String mode,RadioButton radioButton){
        ListPopWindow.clear();
        if(mode.equals("HYType")){
            for (int i = 0; i <HYList.size(); i++) {
                ListPopWindow.add(HYList.get(i));
            }
        }else if(mode.equals("McJ")){

            for (int i = 0; i <McjArray.length; i++) {
                ListPopWindow.add(McjArray[i]);
            }
        }else if(mode.equals("CYY")){

            for (int i = 0; i <industrialParklist.size(); i++) {
                ListPopWindow.add(industrialParklist.get(i));
            }
        }else if(mode.equals("CYYYT")){

            for (int i = 0; i <CYYYTArray.length; i++) {
                ListPopWindow.add(CYYYTArray[i]);
            }
        }else if(mode.equals("YFSJ")){

            for (int i = 0; i <YFSJArray.length; i++) {
                ListPopWindow.add(YFSJArray[i]);
            }
        }else if(mode.equals("YFSC")){

            for (int i = 0; i <YFSCArray.length; i++) {
                ListPopWindow.add(YFSCArray[i]);
            }
            listPopupWindow.setHeight(400);
        }
        listPopupWindow.setWidth(radioButton.getMeasuredWidth());
        listPopupWindow.setAnchorView(radioButton);
        listPopupWindow.show();
        listPopWindowAdapter.notifyDataSetChanged();
    }
    private void changeText(int position){
        if(mode.equals("HYType")){
            companyIndustryCategoryChoose.setText(ListPopWindow.get(position));
        }else if(mode.equals("McJ")){
            companyLevelofmilitaryparticipationChoose.setText(ListPopWindow.get(position));
        }else if(mode.equals("CYY")){
            IndustrialparkChoose.setText(ListPopWindow.get(position));
        }else if(mode.equals("CYYYT")){
            IndustrialparkPurposeChoose.setText(ListPopWindow.get(position));
        }else if(mode.equals("YFSJ")){
            HousekeepingTimeChoose.setText(ListPopWindow.get(position));
        }else if(mode.equals("YFSC")){
            HousekeepingDurationChoose.setText(ListPopWindow.get(position));
        }
        listPopupWindow.dismiss();
    }
    private void changeDrawableRight() {
        if(mode.equals("HYType")){
            companyIndustryCategoryChoose.setCompoundDrawables(null,null,drawableUp,null);
        }else if(mode.equals("McJ")){
            companyLevelofmilitaryparticipationChoose.setCompoundDrawables(null,null,drawableUp,null);
        }else if(mode.equals("CYY")){
            IndustrialparkChoose.setCompoundDrawables(null,null,drawableUp,null);
        }else if(mode.equals("CYYYT")){
            IndustrialparkPurposeChoose.setCompoundDrawables(null,null,drawableUp,null);
        }else if(mode.equals("YFSJ")){
            HousekeepingTimeChoose.setCompoundDrawables(null,null,drawableUp,null);
        }else if(mode.equals("YFSC")){
            HousekeepingDurationChoose.setCompoundDrawables(null,null,drawableUp,null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        textTitle.setFocusableInTouchMode(true);
        textTitle.requestFocus();
        textTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                   if(PageBJ.equals("two")){
                       PageBJ="one";
                       OnePageScroll.setVisibility(View.VISIBLE);
                       TwoPageScroll.setVisibility(View.GONE);
                       Log.e("页数", PageBJ );
                       return true;
                   }
                }
                return false;
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ListPopWindow.clear();
        ListPopWindow=null;
    }
}
