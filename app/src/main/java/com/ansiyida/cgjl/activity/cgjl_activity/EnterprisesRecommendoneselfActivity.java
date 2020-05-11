package com.ansiyida.cgjl.activity.cgjl_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionChildBean;
import com.ansiyida.cgjl.dialog.WheelDialogFragment2;
import com.ansiyida.cgjl.dialog.WheelDialogFragment_zj;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.cgjl_view.FloatLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.FormBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterprisesRecommendoneselfActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.relative_top)
    RelativeLayout relativeTop;
    @Bind(R.id.FR_representative)
    EditText FRRepresentative;
    @Bind(R.id.QY_Name)
    EditText QYName;
    @Bind(R.id.Business_license)
    EditText BusinessLicense;
    @Bind(R.id.ZY_business)
    EditText ZYBusiness;
    @Bind(R.id.ywgjz_num)
    TextView ywgjzNum;
    @Bind(R.id.ZY_field)
    EditText ZYField;
    @Bind(R.id.QY_website)
    EditText QYWebsite;
    @Bind(R.id.GS_introduction)
    EditText GSIntroduction;
    @Bind(R.id.gs_num)
    TextView gsNum;
    @Bind(R.id.ZYYW_introduction)
    EditText ZYYWIntroduction;
    @Bind(R.id.yw_num)
    TextView ywNum;
    @Bind(R.id.text_save)
    TextView textSave;
    @Bind(R.id.HY_Type)
    EditText HYType;
    @Bind(R.id.scroll)
    ScrollView scrollView;
    private boolean judgeNUll = false;
    private List<String> TypeList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private String[][] initlist;
    private List<String> QYTypeList = new ArrayList<>();
    private boolean mBottomFlag = false;
    //滑动距离的最大边界
    private int mOffsetHeight;
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
                ToastUtils.showMessage(EnterprisesRecommendoneselfActivity.this,"提交失败！");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(EnterprisesRecommendoneselfActivity.this,"Android企业自荐","EnterprisesRecommendoneselfActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_enterprises_recommendoneself;
    }

    @Override
    protected void initView() {
        textTitle.setText("企业自荐");
        HYType.setText("请选择行业类别");
        ZYField.setText("请选择专业领域");
    }

    @Override
    protected void initData() {
        initlist = initlist();
        getQYType();
    }

    @Override
    protected void httpData() {
        if (judgeNUll) {
            if (NetWorkUtils.isNetworkConnected(this)) {
                FormBody build = new FormBody.Builder()
                        .add("corporation", FRRepresentative.getText().toString())
                        .add("companyName", QYName.getText().toString())
                        .add("website", QYWebsite.getText().toString())
                        .add("tradeType", HYType.getText().toString())
                        .add("majorField", ZYField.getText().toString())
                        .add("businessKeyword", ZYBusiness.getText().toString())
                        .add("companySummary", GSIntroduction.getText().toString())
                        .add("businessSummary", ZYYWIntroduction.getText().toString())
                        .add("businessLicense", BusinessLicense.getText().toString())
                        .add("parentId", "")
                        .build();
                String format = String.format(Constant.URL + "company?");
                Request build1 = new Request.Builder()
                        .addHeader("token", (String) SharedPreferenceUtils.get(EnterprisesRecommendoneselfActivity.this, "app_token", ""))
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
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setClickListener() {
        ZYField.setOnClickListener(this);
        HYType.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        textSave.setOnClickListener(this);
        GSIntroduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    GSIntroduction.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    GSIntroduction.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    gsNum.setText(lenght + "");
                } else {
                    gsNum.setText("最多五百字!");
                }
            }
        });
        ZYYWIntroduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于6
                    ZYYWIntroduction.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    ZYYWIntroduction.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    ywNum.setText(lenght + "");
                } else {
                    ywNum.setText("最多五百字!");
                }
            }
        });
        ZYBusiness.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 100) { //判断EditText中输入的字符数是不是已经大于6
                    ZYBusiness.setText(s.toString().substring(0, 100)); //设置EditText只显示前面6位字符
                    ZYBusiness.setSelection(100);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 100 - number;
                if (lenght >= 0) {
                    ywgjzNum.setText(lenght + "");
                } else {
                    ywgjzNum.setText("最多一百字!");
                }
            }
        });
        GSIntroduction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(GSIntroduction.getText().length()>0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        ZYYWIntroduction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /*告诉父组件不要拦截他的触摸事件*/

                if(ZYYWIntroduction.getText().length()>0){

                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        ZYBusiness.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(ZYBusiness.getText().length()>0){
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_save:
                judgeNUll = judgeNUll();
                //^|
                //String regex = "^\\d{15}$ | (?:(?![IOZSV])[\\dA-Z]){2}\\d{6}(?:(?![IOZSV])[\\dA-Z]){10}$ ";
                if (judgeNUll) {
                    if(!ZYField.getText().toString().equals("请选择专业领域")) {
                        if(!HYType.getText().toString().equals("请选择行业类别")){
                            httpData();
                        }else{
                            ToastUtils.showMessage(EnterprisesRecommendoneselfActivity.this,"请选择行业类别！");
                        }
                    }else{
                        ToastUtils.showMessage(EnterprisesRecommendoneselfActivity.this,"请选择专业领域！");
                    }
                }
                break;
            case R.id.HY_Type:
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                    TypeList.clear();
                    for (int i = 0; i < QYTypeList.size(); i++) {
                        TypeList.add(QYTypeList.get(i));
                    }
                    bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) TypeList.toArray(new String[TypeList.size()]));
                    WheelDialogFragment_zj dialogFragment1 = WheelDialogFragment_zj.newInstance(WheelDialogFragment_zj.class, bundle);
                    dialogFragment1.setWheelDialogListener(new WheelDialogFragment_zj.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            HYType.setText(value);

                        }

                        @Override
                        public void onValueChange(DialogFragment dialog, String value) {
                            //     Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialogFragment1.show(getSupportFragmentManager(), "");
                } catch (Exception e) {
                    e.toString();

                }
                break;
            case R.id.ZY_field:
                ZYLYPop();
                break;
        }
    }

    public boolean judgeNUll() {


        if (FRRepresentative.getText().toString().isEmpty() && QYName.getText().toString().isEmpty() && BusinessLicense.getText().toString().isEmpty()
                && ZYBusiness.getText().toString().isEmpty() && ZYField.getText().toString().isEmpty() && QYWebsite.getText().toString().isEmpty()
                && GSIntroduction.getText().toString().isEmpty() && ZYYWIntroduction.getText().toString().isEmpty()) {
            ToastUtils.showMessage(EnterprisesRecommendoneselfActivity.this, "以上信息均需要全部填写完毕。");
            return false;
        } else if (!FRRepresentative.getText().toString().isEmpty() && !QYName.getText().toString().isEmpty() && !BusinessLicense.getText().toString().isEmpty()
                && !ZYBusiness.getText().toString().isEmpty() && !ZYField.getText().toString().isEmpty() && !QYWebsite.getText().toString().isEmpty()
                && !GSIntroduction.getText().toString().isEmpty() && !ZYYWIntroduction.getText().toString().isEmpty() ) {
            if(BusinessLicense.getText().toString().length()== 15 || BusinessLicense.getText().toString().length()==18){
                return  true;
            }else{
                ToastUtils.showMessage(EnterprisesRecommendoneselfActivity.this,"您的营业执照号码不正确！");
                return false;
            }
        } else {
            ToastUtils.showMessage(this, "以上信息均需要全部填写完毕");
            return false;
        }
    }

    public void popwindow() {
        View view = View.inflate(this, R.layout.pop_ok_animation, null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double width = (double) dm.widthPixels / 1.5;
        final PopupWindow popupWindow = new PopupWindow(view, (int) width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(EnterprisesRecommendoneselfActivity.this, (float) 1);
                finish();
            }
        });
        ImageView pop_ok_image = view.findViewById(R.id.pop_ok_image);
        ImageView pop_ok_image80 = view.findViewById(R.id.pop_ok_image80);
        TextView AuditCenter = view.findViewById(R.id.go_AuditCenter);
        ImageView dismissImage = view.findViewById(R.id.dismiss_pop_ok);
        pop_ok_image80.setVisibility(View.VISIBLE);
        pop_ok_image.setVisibility(View.GONE);
        backgroundAlpha(this, (float) 0.5);
        //dismissPOpwindow
        dismissImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                backgroundAlpha(EnterprisesRecommendoneselfActivity.this, (float) 1);
                finish();
            }
        });
        AuditCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterprisesRecommendoneselfActivity.this, AuditCenterActivity.class);
                startActivity(intent);
            }
        });
        //popupWindow.showAsDropDown(FRRepresentative);
        popupWindow.showAtLocation(FRRepresentative, Gravity.CENTER, 0, 0);
        //开启动画
        pop_ok_image80.setImageResource(R.drawable.lottery_animlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) pop_ok_image80.getDrawable();
        animationDrawable.start();
    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void ZYLYPop() {
        View view = View.inflate(this, R.layout.zyly_popwindow, null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double width = (double) dm.widthPixels / 1.5;
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(EnterprisesRecommendoneselfActivity.this, (float) 0.5);
        final FloatLayout myFloatLayout = view.findViewById(R.id.MyFloatLayout);
        Button qx_btn = view.findViewById(R.id.qx_btn);
        Button qd_btn = view.findViewById(R.id.qd_btn);
        myFloatLayout.setAdapter(initlist, list);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(EnterprisesRecommendoneselfActivity.this, (float) 1);
            }
        });
        qx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        qd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                list = myFloatLayout.getList();
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    if (s.equals("")) {
                        s = list.get(i);
                    } else {
                        s = s + " " + list.get(i);
                    }
                }
                ZYField.setText(s);
            }
        });
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.showAtLocation(FRRepresentative, Gravity.CENTER, 0, 0);
    }

    private void getQYType() {
        Call<OptionChildBean> call = MyApplication.getNetApi().getoptioAll((String) SharedPreferenceUtils.get(this, "app_token", ""), 1);
        call.enqueue(new Callback<OptionChildBean>() {
            @Override
            public void onResponse(Call<OptionChildBean> call, Response<OptionChildBean> response) {
                if (response.isSuccessful()) {
                    OptionChildBean body = response.body();
                    if (body.getStatus() == 200) {
                        for (int i = 0; i < body.getData().getChildren().size(); i++) {
                            QYTypeList.add(body.getData().getChildren().get(i).getName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OptionChildBean> call, Throwable t) {

            }
        });
    }

    private String[][] initlist() {
        final List<OptionChildBean.DataBean.ChildrenBeanX> children = new ArrayList();
        final String[][] userArray = new String[1][1];
        Call<OptionChildBean> call = MyApplication.getNetApi().getoptioAll((String) SharedPreferenceUtils.get(this, "app_token", ""), 3);
        call.enqueue(new Callback<OptionChildBean>() {
            @Override
            public void onResponse(Call<OptionChildBean> call, Response<OptionChildBean> response) {
                if (response.isSuccessful()) {
                    OptionChildBean body = response.body();
                    if (body.getStatus() == 200) {
                        children.addAll(body.getData().getChildren());
                        userArray[0] = new String[children.size()];
                        for (int i = 0; i < userArray[0].length; i++) {
                            if (!children.get(i).getName().equals("企业-专业领域")) {
                                userArray[0][i] = children.get(i).getName();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<OptionChildBean> call, Throwable t) {

            }
        });
        return userArray;
    }
}
