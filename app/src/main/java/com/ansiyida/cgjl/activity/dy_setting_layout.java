package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.dydetail_bean;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.dialog.WheelDialogFragment;
import com.ansiyida.cgjl.dialog.WheelDialogFragment1;
import com.ansiyida.cgjl.dialog.WheelDialogFragment2;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.GetCityUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.CursorEditText;
import com.umeng.analytics.pro.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dy_setting_layout extends BaseActivity {

    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.zcxx_line)
    LinearLayout zcxx_line;
    @Bind(R.id.smcg_line)
    LinearLayout smcg_line;
    @Bind(R.id.cgxq_line)
    LinearLayout cgxq_line;
    @Bind(R.id.zcxx)
    ImageView zcxx;
    @Bind(R.id.cgxq)
    ImageView cgxq;
    @Bind(R.id.smcg)
    ImageView smcg;
    @Bind(R.id.folded1)
    TextView folded1;
    @Bind(R.id.folded2)
    TextView folded2;
    @Bind(R.id.folded3)
    TextView folded3;
    @Bind(R.id.dip_blue1)
    TextView dip_blue1;
    @Bind(R.id.dip_blue2)
    TextView dip_blue2;
    @Bind(R.id.dip_blue3)
    TextView dip_blue3;
    @Bind(R.id.editText_cgname)
    CursorEditText editText_cgname;
    @Bind(R.id.editText_cg_key1)
    CursorEditText editText_cg_key1;
    @Bind(R.id.image_tuisong_sm)
    ImageView image_tuisong_sm;
    @Bind(R.id.btn_finish_sm)
    TextView btn_finish_sm;
    @Bind(R.id.image_tuisong_cg)
    ImageView image_tuisong_cg;
    @Bind(R.id.btn_finish_cg)
    TextView btn_finish_cg;
    @Bind(R.id.smcg_name)
    CursorEditText smcgName;

    private String id = "";
    private String ismodify = "";
    private dydetail_bean.DataBean dydetail;
    private List<infoTypeBeanBean.DataBean> infoType;
    private List<sourceTypeBean.DataBean> sourceType;
    private List<String> types;
    private List<String> names;
    private Bundle bundle;
    private EditText editText_dyname;
    private TextView editText_key1;
    private TextView Text_country;
    private TextView Text_type;
    private TextView Text_source;
    private TextView keywoerd;
    private ImageView tuisong;
    private int type_id = 0;
    private int source_id = 0;
    private String sheng = "";
    private String shi = "";
    private String genre = "";
    private Boolean tuisong_dy = false;
    private Boolean tuisong_cg = false;
    private Boolean tuisong_sm = false;
    private HashMap type_dy = new HashMap();
    private HashMap source_dy = new HashMap();

    private CursorEditText smcg_key;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(dy_setting_layout.this,"Android添加订阅","dy_setting_layout");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_dyadd;
    }

    @Override
    protected void initView() {
        types = new ArrayList<>();
        names = new ArrayList<>();
        Text_country = (TextView) findViewById(R.id.Text_country);
        Text_type = (TextView) findViewById(R.id.Text_type);
        Text_source = (TextView) findViewById(R.id.Text_source);
        keywoerd = (TextView) findViewById(R.id.editText_key1);
        tuisong = (ImageView) findViewById(R.id.image_tuisong);
        editText_dyname = (CursorEditText) findViewById(R.id.editText_dyname);
        editText_key1 = (CursorEditText) findViewById(R.id.editText_key1);
        smcg_key = findViewById(R.id.smcg_key1);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.getStringExtra("keyword") != null) {
            String keyword = intent.getStringExtra("keyword");
            smcg_key.setText(keyword);
        }

        genre = intent.getStringExtra("genre");
        if (intent.getStringExtra("id") != null)
            id = intent.getStringExtra("id");
        if (intent.getStringExtra("ismodify") != null)
            ismodify = intent.getStringExtra("ismodify");
        if (ismodify.equals("true"))
            text_title.setText("编辑订阅");
        else
            text_title.setText("添加订阅");
        if (ismodify.equals("true")) {
            if (genre.equals("purchaseInfo")) {
                Call<dydetail_bean> call = MyApplication.getNetApi().getID_dy(id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<dydetail_bean>() {
                    @Override
                    public void onResponse(Call<dydetail_bean> call, Response<dydetail_bean> response) {
                        if (response.isSuccessful()) {
                            String status = response.body().getStatus();
                            if ("200".equals(status)) {
                                dydetail = response.body().getData();
                                flod_non();
                                flod_zc();
                                Text_country.setText(dydetail.getprovince() + dydetail.getaddress());
                                if ("".equals(dydetail.gettype()) | "null".equals(dydetail.gettype() + ""))
                                    Text_type.setText("全部");
                                else
                                    Text_type.setText(dydetail.gettype());
                                if ("".equals(dydetail.getsource()) | "null".equals(dydetail.getsource() + ""))
                                    Text_type.setText("全部");
                                else
                                    Text_source.setText(dydetail.getsource());
                                editText_key1.setText(keyword_mod(dydetail.getkeyword()));
                                editText_dyname.setText(dydetail.getname());
                                sheng = dydetail.getprovince();
                                shi = dydetail.getaddress();
                                type_id = dydetail.gettypeId();
                                source_id = dydetail.getsourceId();
                                if (dydetail.getstatus()) {
                                    tuisong.setImageResource(R.mipmap.button_open);
                                    tuisong_dy = true;
                                } else {
                                    tuisong.setImageResource(R.mipmap.button_close);
                                    tuisong_dy = false;
                                }
                            } else {
                                ToastUtils.showMessage(dy_setting_layout.this, response.body().getMessage());
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<dydetail_bean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(dy_setting_layout.this, c.e.toString());

                    }
                });
            } else if (genre.equals("purchaseDemand")) {
                Call<dydetail_bean> call = MyApplication.getNetApi().getID_dy(id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<dydetail_bean>() {
                    @Override
                    public void onResponse(Call<dydetail_bean> call, Response<dydetail_bean> response) {
                        if (response.isSuccessful()) {
                            String status = response.body().getStatus();
                            if ("200".equals(status)) {
                                dydetail = response.body().getData();
                                flod_non();
                                flod_cg();
                                //  Text_country.setText(dydetail.getprovince() + dydetail.getaddress());
                                //   Text_type.setText(dydetail.gettype());
                                //   Text_source.setText(dydetail.getsource());
                                editText_cg_key1.setText(keyword_mod(dydetail.getkeyword()));
                                editText_cgname.setText(dydetail.getname());
                                //    sheng = dydetail.getprovince();
                                //    shi = dydetail.getaddress();
                                //   type_id = dydetail.gettypeId();
                                //     source_id = dydetail.getsourceId();
                                if (dydetail.getstatus()) {
                                    image_tuisong_cg.setImageResource(R.mipmap.button_open);
                                    tuisong_cg = true;
                                } else {
                                    image_tuisong_cg.setImageResource(R.mipmap.button_close);
                                    tuisong_cg = false;
                                }
                            } else {
                                ToastUtils.showMessage(dy_setting_layout.this, response.body().getMessage());
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<dydetail_bean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(dy_setting_layout.this, c.e.toString());

                    }
                });
            } else if (genre.equals("purchaseSecret")) {
                Call<dydetail_bean> call = MyApplication.getNetApi().getID_dy(id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<dydetail_bean>() {
                    @Override
                    public void onResponse(Call<dydetail_bean> call, Response<dydetail_bean> response) {
                        if (response.isSuccessful()) {
                            String status = response.body().getStatus();
                            if ("200".equals(status)) {
                                dydetail = response.body().getData();
                                flod_non();
                                flod_sm();
                                smcgName.setText(dydetail.getname());
                                if (dydetail.getstatus()) {
                                    image_tuisong_sm.setImageResource(R.mipmap.button_open);
                                    tuisong_sm = true;
                                } else {
                                    image_tuisong_sm.setImageResource(R.mipmap.button_close);
                                    tuisong_sm = false;
                                }
                            } else {
                                ToastUtils.showMessage(dy_setting_layout.this, response.body().getMessage());
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<dydetail_bean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(dy_setting_layout.this, c.e.toString());
                    }
                });
            }
            smcgName.setSelection(smcgName.getText().length());
            smcg_key.setSelection(smcg_key.getText().length());

            editText_cgname.setSelection(editText_cgname.getText().length());
            editText_cg_key1.setSelection(editText_cg_key1.getText().length());

            editText_dyname.setSelection(editText_dyname.getText().length());


        }
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {

    }

    @OnClick({R.id.image_back, R.id.image_tuisong_sm, R.id.image_tuisong_cg, R.id.relalaout_country, R.id.relalayout_type, R.id.relalaout_source, R.id.image_tuisong, R.id.btn_finish, R.id.btn_finish_sm, R.id.btn_finish_cg, R.id.flod_sm, R.id.flod_zc, R.id.flod_cg})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.flod_sm:               //1.返回上一级
                if (smcg_line.getVisibility() > 0) {
                    flod_non();
                    flod_sm();
                } else
                    flod_non();
                break;
            case R.id.flod_cg:               //1.返回上一级
                if (cgxq_line.getVisibility() > 0) {
                    flod_non();
                    flod_cg();
                } else
                    flod_non();
                break;
            case R.id.flod_zc:               //1.返回上一级
                if (zcxx_line.getVisibility() > 0) {
                    flod_non();
                    flod_zc();
                } else
                    flod_non();
                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            case R.id.btn_finish:               //1.返回上一级
                Log.e("订阅", "click: "+type_id );
                if (!ismodify.equals("true")) {
                    if (sheng.equals("") & shi.equals("")&sheng.equals("全国") & shi.equals("全国"))
                        shi = "";
                    if (keyword_non(editText_key1.getText().toString())) {
                        if (name_length(EditTextUtil.getEditTextStr(editText_dyname))) {
                            Call<DefaultBean2> call_add;
                            if(type_id == 0 || source_id ==0){
                                call_add = MyApplication.getNetApi().add_dy((String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_dyname), keyword_sub(editText_key1.getText().toString()), "purchaseInfo", sheng, shi
                                        , null, null, tuisong_dy);
                            }else{
                                call_add = MyApplication.getNetApi().add_dy((String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_dyname), keyword_sub(editText_key1.getText().toString()), "purchaseInfo", sheng, shi
                                        , type_id, source_id, tuisong_dy);
                            }

                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            //   ToastUtils.showMessage(dy_setting_layout.this, "添加订阅成功");
                                            initPopuWindow_cache();
                                            // startActivity(new Intent(dy_setting_layout.this,));}
                                        } else {
                                            ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                                }
                            });
                        }
                    }
                } else {
                    if (keyword_non(editText_key1.getText().toString())) {
                        if (name_length(EditTextUtil.getEditTextStr(editText_dyname))) {
                            Call<DefaultBean2> call_add;
                            if(type_id == 0 || source_id == 0){
                                call_add = MyApplication.getNetApi().post_dy(id, (String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_dyname), keyword_sub(editText_key1.getText().toString()), "purchaseInfo", sheng, shi
                                        , null, null, tuisong_dy);
                            }else{
                                call_add = MyApplication.getNetApi().post_dy(id, (String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_dyname), keyword_sub(editText_key1.getText().toString()), "purchaseInfo", sheng, shi
                                        , type_id, source_id, tuisong_dy);
                            }
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            //  ToastUtils.showMessage(dy_setting_layout.this, "修改订阅成功");
                                            initPopuWindow_cache();

                                            // startActivity(new Intent(dy_setting_layout.this,));}
                                        } else {
                                            ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                                }
                            });
                        }
                    }
                }
                break;
            case R.id.btn_finish_cg:               //1.返回上一级
                if (!ismodify.equals("true")) {
                    if (keyword_non(EditTextUtil.getEditTextStr(editText_cg_key1))) {
                        if (name_length(EditTextUtil.getEditTextStr(editText_cgname))) {
                            Call<DefaultBean2> call_add = MyApplication.getNetApi().add_dy_cg((String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_cgname), keyword_sub(EditTextUtil.getEditTextStr(editText_cg_key1)), "purchaseDemand", tuisong_cg);
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {
                                            //  ToastUtils.showMessage(dy_setting_layout.this, "添加订阅成功");
                                            initPopuWindow_cache();
                                            // startActivity(new Intent(dy_setting_layout.this,));}
                                        } else {
                                            ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                        }

                                    }

                                }
                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                                }
                            });
                        }
                    }
                } else {
                    if (keyword_non(EditTextUtil.getEditTextStr(editText_cg_key1))) {
                        if (name_length(EditTextUtil.getEditTextStr(editText_cgname))) {
                            Call<DefaultBean2> call_add = MyApplication.getNetApi().post_dy_cx(id, (String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), EditTextUtil.getEditTextStr(editText_cgname), keyword_sub(EditTextUtil.getEditTextStr(editText_cg_key1)), "purchaseDemand", tuisong_cg);
                            call_add.enqueue(new Callback<DefaultBean2>() {
                                @Override
                                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                    if (response.isSuccessful()) {
                                        DefaultBean2 body = response.body();
                                        if ("200".equals(response.body().getStatus())) {

                                            initPopuWindow_cache();

                                        } else {
                                            ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                        }

                                    }


                                }

                                @Override
                                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                    ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                                }
                            });
                        }
                    }
                }
                break;
            case R.id.btn_finish_sm:               //1.返回上一级
                if (!ismodify.equals("true")) {
                    if (!smcg_key.getText().toString().isEmpty()) {
                        Call<DefaultBean2> call_add = MyApplication.getNetApi().add_dy_sm((String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), smcg_key.getText().toString(), "purchaseSecret", tuisong_sm);
                        call_add.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    DefaultBean2 body = response.body();
                                    if ("200".equals(response.body().getStatus())) {
                                        //  ToastUtils.showMessage(dy_setting_layout.this, "添加订阅成功");
                                        initPopuWindow_cache();
                                        // startActivity(new Intent(dy_setting_layout.this,));}
                                    } else {
                                        ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                    }

                                }

                            }
                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                            }
                        });


                    } else {
                        ToastUtils.showMessage(this,"关键字不能为空！");
                    }
                } else {
                    if (!smcg_key.getText().toString().isEmpty()) {
                        Call<DefaultBean2> call_add = MyApplication.getNetApi().post_dy_sm(id, (String) SharedPreferenceUtils.get(this.getApplicationContext(), "app_token", ""), smcgName.getText().toString(), smcg_key.getText().toString(), "purchaseSecret", tuisong_sm);
                        call_add.enqueue(new Callback<DefaultBean2>() {
                            @Override
                            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                                if (response.isSuccessful()) {
                                    DefaultBean2 body = response.body();
                                    if ("200".equals(response.body().getStatus())) {
                                        initPopuWindow_cache();
                                    } else {
                                        ToastUtils.showMessage(dy_setting_layout.this, body.getMsg());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                                ToastUtils.showMessage(dy_setting_layout.this, t.toString());

                            }
                        });
                    }else{
                        ToastUtils.showMessage(this,"关键字不能为空哦！");
                    }
                }
                break;
            case R.id.image_tuisong:               //1.返回上一级
                tuisong_dy = !tuisong_dy;
                if (tuisong_dy)
                    tuisong.setImageResource(R.mipmap.button_open);
                else
                    tuisong.setImageResource(R.mipmap.button_close);
                //     this.finish();
                break;
            case R.id.image_tuisong_sm:               //1.返回上一级
                tuisong_sm = !tuisong_sm;
                if (tuisong_sm)
                    image_tuisong_sm.setImageResource(R.mipmap.button_open);
                else
                    image_tuisong_sm.setImageResource(R.mipmap.button_close);
                //     this.finish();
                break;
            case R.id.image_tuisong_cg:               //1.返回上一级
                tuisong_cg = !tuisong_cg;
                if (tuisong_cg)
                    image_tuisong_cg.setImageResource(R.mipmap.button_open);
                else
                    image_tuisong_cg.setImageResource(R.mipmap.button_close);
                //     this.finish();
                break;
            case R.id.relalaout_source:           //1.返回上一级
                try {
                    bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));

                    Call<sourceTypeBean> call1 = MyApplication.getNetApi().getsource("");
                    // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                    call1.enqueue(new Callback<sourceTypeBean>() {
                        @Override
                        public void onResponse(Call<sourceTypeBean> call1, Response<sourceTypeBean> response) {
                            if (response.isSuccessful()) {
                                sourceTypeBean body = response.body();
                                //  caigoulist body = response.body();

                                if (body != null) {
                                    //  sourceTypeBean.DataBean.listBean list = body.getData();
                                    sourceType = body.getData();
                                    if (sourceType != null && sourceType.size() > 0) {
                                        names.clear();
                                        source_dy.clear();
                                        names.add("全部");
                                        for (sourceTypeBean.DataBean listbean : sourceType) {
                                            names.add(listbean.getcode());
                                            source_dy.put(listbean.getcode(), listbean.getid());
                                        }
                                        //      dropdownButton3.setData(names,"来源",null,edit_view_source);
                                        // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                                        bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) names.toArray(new String[names.size()]));
                                        // dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                                        // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                                        WheelDialogFragment2 dialogFragment1 = WheelDialogFragment2.newInstance(WheelDialogFragment2.class, bundle);
                                        dialogFragment1.setWheelDialogListener(new WheelDialogFragment2.OnWheelDialogListener() {
                                            @Override
                                            public void onClickLeft(DialogFragment dialog, String value) {
                                                dialog.dismiss();
                                            }

                                            @Override
                                            public void onClickRight(DialogFragment dialog, String value) {
                                                dialog.dismiss();
                                                Text_source.setText(value);
                                                if (!"全部".equals(value))
                                                    source_id = (int) source_dy.get(value);
                                                else
                                                    source_id = 0;
                                            }

                                            @Override
                                            public void onValueChange(DialogFragment dialog, String value) {
                                                //     Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        dialogFragment1.show(getSupportFragmentManager(), "");

                                    }

                                }

                            }

                            call1.cancel();
                            //    httpData_RecyclerView("","",sourecetext_drop,citytext_drop,typetext_drop);
                        }

                        @Override
                        public void onFailure(Call<sourceTypeBean> call1, Throwable t) {

                            call1.cancel();
                        }
                    });
                } catch (Exception e) {
                    e.toString();
                    //  Toast.makeText(getApplicationContext(),  e.toString(), Toast.LENGTH_SHORT).show();

                }
                break;
           /*  case R.id. editText_key1:

                 Intent intent_key = new Intent(this, keywod_activity.class);
                 startActivityForResult(intent_key, ActivityCodeUtil.KEYWORD);
                 editText_key1.setText("未填写");
                 break;*/
            case R.id.relalaout_country:           //1.返回上一级
                sheng = "";
                try {
                    bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
                    bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL,GetCityUtil. GroupNameArray);//(String[]) types.toArray(new String[types.size()])
                    bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL1, GetCityUtil.childNameArray[0]);
                    WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
                    dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            shi = value;
                            Text_country.setText(sheng + value);
                            // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onValueChanged(DialogFragment dialog, int value) {
                            //  bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL1, childNameArray[value]);
                            if (GetCityUtil.GroupNameArray[value] == "全国" || GetCityUtil.GroupNameArray[value] == "北京" || GetCityUtil.GroupNameArray[value] == "天津市" || GetCityUtil.GroupNameArray[value] == "上海市" || GetCityUtil.GroupNameArray[value] == "重庆市" || GetCityUtil.GroupNameArray[value] == "台湾省"
                                    || GetCityUtil.GroupNameArray[value] == "香港特别行政区" || GetCityUtil.GroupNameArray[value] == "澳门特别行政区")
                                sheng = "";
                            else
                                sheng = GetCityUtil.GroupNameArray[value];
                            WheelDialogFragment.wheelView1.refreshByNewDisplayedValues(GetCityUtil.childNameArray[value]);
                        }

                    });

                    dialogFragment.show(getSupportFragmentManager(), "");
                } catch (Exception ee) {
                    ee.toString();
                }
                break;
            case R.id.relalayout_type:           //1.返回上一级
                bundle = new Bundle();
                bundle.putBoolean(WheelDialogFragment1.DIALOG_BACK, false);
                bundle.putBoolean(WheelDialogFragment1.DIALOG_CANCELABLE, false);
                bundle.putBoolean(WheelDialogFragment1.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                bundle.putString(WheelDialogFragment1.DIALOG_LEFT, "取消");
                bundle.putString(WheelDialogFragment1.DIALOG_RIGHT, "确定");
                //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                Call<infoTypeBeanBean> call = MyApplication.getNetApi().getinfoType("");
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<infoTypeBeanBean>() {
                    @Override
                    public void onResponse(Call<infoTypeBeanBean> call, Response<infoTypeBeanBean> response) {
                        if (response.isSuccessful()) {
                            infoTypeBeanBean body = response.body();
                            //  caigoulist body = response.body();

                            if (body != null) {
                                infoType = body.getData();

                                if (infoType != null && infoType.size() > 0) {
                                    types.clear();
                                    type_dy.clear();
                                    types.add("全部");
                                    for (infoTypeBeanBean.DataBean listBean : infoType) {
                                        if (!"采购需求".equals(listBean.getname()) && !"涉密采购".equals(listBean.getname())) {
                                            types.add(listBean.getname());
                                            type_dy.put(listBean.getname(), listBean.getid());
                                        }
                                    }
                                    bundle.putStringArray(WheelDialogFragment1.DIALOG_WHEEL, (String[]) types.toArray(new String[types.size()]));
                                    // dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                                    // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                                    WheelDialogFragment1 dialogFragment2 = WheelDialogFragment1.newInstance(WheelDialogFragment1.class, bundle);
                                    dialogFragment2.setWheelDialogListener(new WheelDialogFragment1.OnWheelDialogListener() {
                                        @Override
                                        public void onClickLeft(DialogFragment dialog, String value) {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void onClickRight(DialogFragment dialog, String value) {
                                            dialog.dismiss();
                                            Text_type.setText(value);
                                            if (!"全部".equals(value))
                                                type_id = (int) type_dy.get(value);
                                            else
                                                type_id = 0;
                                        }

                                        @Override
                                        public void onValueChange(DialogFragment dialog, String value) {
                                            //   Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    dialogFragment2.show(getSupportFragmentManager(), "");

                                }

                            }

                        }

                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<infoTypeBeanBean> call, Throwable t) {

                        call.cancel();
                    }
                });

                break;
            default:
                break;

        }
    }

    private boolean name_length(String name) {
        if (name.length() > 15) {
            ToastUtils.showMessage(this, "名称不能超过15个字");
            return false;
        } else {

            return true;
        }
    }

    private boolean keyword_non(String keyword) {
        if (keyword.split("\\s+").length > 3) {
            ToastUtils.showMessage(this, "关键词超过三个");
            return false;
        } else {
            for (int i = 0; i < keyword.split(" ").length; i++)
                if (keyword.split(" ")[i].length() > 15) {
                    ToastUtils.showMessage(this, "关键词超过15个字");
                    return false;
                }
            return true;
        }
    }

    private String keyword_sub(String keyword) {
        String keyword_sub = "";
        if (keyword.contains(" ")) {
            for (int i = 0; i < keyword.split("\\s+").length; i++) {
                if (i == (keyword.split("\\s+").length - 1))
                    keyword_sub = keyword_sub + keyword.split("\\s+")[i];
                else
                    keyword_sub = keyword_sub + keyword.split("\\s+")[i] + ",";
            }
            return keyword_sub;
        } else {
            keyword_sub = keyword;
            return keyword_sub;
        }
    }

    private String keyword_mod(String keyword) {
        String keyword_sub = "";
        if (keyword.contains(",")) {
            for (int i = 0; i < keyword.split(",").length; i++) {
                if (i == (keyword.split(",").length - 1))
                    keyword_sub = keyword_sub + keyword.split(",")[i];
                else
                    keyword_sub = keyword_sub + keyword.split(",")[i] + " ";
            }
            return keyword_sub;
        } else {
            keyword_sub = keyword;
            return keyword_sub;
        }
    }

    private void flod_non() {
        cgxq_line.setVisibility(View.GONE);
        zcxx_line.setVisibility(View.GONE);
        smcg_line.setVisibility(View.GONE);
        zcxx.setImageResource(R.mipmap.icon_down_default);
        // dip_blue2.setVisibility(View.GONE);
        folded2.setText("展开");
        smcg.setImageResource(R.mipmap.icon_down_default);
        //   dip_blue1.setVisibility(View.GONE);
        folded1.setText("展开");
        cgxq.setImageResource(R.mipmap.icon_down_default);
        //    dip_blue3.setVisibility(View.GONE);
        folded3.setText("展开");
    }

    private void flod_cg() {
        cgxq_line.setVisibility(View.VISIBLE);
        cgxq.setImageResource(R.mipmap.icon_up3x);
        dip_blue2.setVisibility(View.VISIBLE);
        folded2.setText("收起");
    }

    private void flod_sm() {

        smcg_line.setVisibility(View.VISIBLE);
        smcg.setImageResource(R.mipmap.icon_up3x);
        dip_blue3.setVisibility(View.VISIBLE);
        folded3.setText("收起");
    }

    private void flod_zc() {

        zcxx_line.setVisibility(View.VISIBLE);
        zcxx.setImageResource(R.mipmap.icon_up3x);
        dip_blue1.setVisibility(View.VISIBLE);
        folded1.setText("收起");
    }

    public void returnResult() {
        // Intent result = new Intent();
        //  result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.DY, null);
        finish();
    }

    public void returnResum() {
        ActivityCodeUtil.refresh = "true";
        // setResult(ActivityCodeUtil.DY , null);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//上层界面返回数据
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ActivityCodeUtil.KEYWORD) {
            if (data != null) {
                String keyword = data.getStringExtra("keyword");
                keywoerd.setText(keyword);
            }
        }

    }

    /**
     * 删除弹窗
     */
    private PopupWindow popupWindow_cache;
    private View contentView_cache;

    public void initPopuWindow_cache() {
        if (popupWindow_cache == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_cache = mLayoutInflater.inflate(R.layout.popwindow_write, null);
            popupWindow_cache = new PopupWindow(contentView_cache, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out = (RelativeLayout) contentView_cache.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView_cache.findViewById(R.id.relative_in);
        ImageView relative_view = (ImageView) contentView_cache.findViewById(R.id.obtain_yanZheng_tuxing);

        relative_view.setBackgroundResource(R.drawable.anim_rocket_write);


        AnimationDrawable rocketAnimation = (AnimationDrawable) relative_view.getBackground();
        if (rocketAnimation.isRunning()) {
            //停止动画
            rocketAnimation.stop();
        }
        rocketAnimation.start();
        TextView tv_sure = (TextView) contentView_cache.findViewById(R.id.tv_sure);

        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResum();
            }
        });


        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_cache.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_cache.setOutsideTouchable(true);
        popupWindow_cache.setFocusable(true);
        popupWindow_cache.showAtLocation(contentView_cache, Gravity.CENTER, 0, 0);
        popupWindow_cache.update();
//        popupWindow_cache.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_cache.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
