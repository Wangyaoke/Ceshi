package com.ansiyida.cgjl;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ansiyida.cgjl.activity.cgjl_activity.SecretProcurementActivity;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.fragment.ChannalFragment;
import com.ansiyida.cgjl.fragment.DYNewFragment;
import com.ansiyida.cgjl.fragment.Fragment1;
import com.ansiyida.cgjl.fragment.Fragment2;
import com.ansiyida.cgjl.fragment.Fragment3;
import com.ansiyida.cgjl.fragment.Fragment5;
import com.ansiyida.cgjl.fragment.cgjl_fragment.CivilimilitaryTypeFragment;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.util.permissions.PermissionListener;
import com.ansiyida.cgjl.util.permissions.PermissionUtil;
import com.umeng.message.PushAgent;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {
    @Bind(R.id.Civil_military_name)
    TextView CivilMilitaryName;
    @Bind(R.id.Civil_military_rel)
    RelativeLayout CivilMilitaryRel;
    @Bind(R.id.rb5_mainAcitivity)
    RadioButton rb5;
    @Bind(R.id.JmRh)
    RadioButton JmRh;
    @Bind(R.id.search_frame)
    FrameLayout searchFrame;
    /**
     * 当前选中的栏目
     */
    private int columnSelectIndex = 0;
    @Bind(R.id.rg_mainAcitivity)
    RadioGroup rg;
    @Bind(R.id.rbserch_mainAcitivity)
    RadioButton rbserch;

    @Bind(R.id.rb1_mainAcitivity)
    RadioButton rb1;
    @Bind(R.id.rb2_mainAcitivity)
    RadioButton rb2;
    @Bind(R.id.rb3_mainAcitivity)
    RadioButton rb3;
    @Bind(R.id.fragLayout_tab)
    FrameLayout fragLayout_tab;
    @Bind(R.id.isTest)
    TextView isText;

    private Fragment1 frag1;
    private Fragment2 frag2;
    private Fragment3 frag3;
    private DYNewFragment fragmen;
    private Fragment5 frag5;
    private ChannalFragment channalFragment;
    private FragmentTransaction transaction;
    private FrameLayout fragment_container;
    private DbMannager mannager;
    private boolean returnShow = false;
    private PermissionUtil permissionUtil;
    private String searchStr = "";
    private String city = "";
    private AMapLocationClient aMapLocationClient;
    private CivilimilitaryTypeFragment civilimilitaryTypeFragment;
    private boolean CiviBol = false;
    private long exitTime = 0;
    private boolean searchhideBol =false;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //更新UI
            switch (msg.what) {
                case 1:
                    if ("".equals((String) SharedPreferenceUtils.get(MainActivity.this, "city_loction", ""))) {
                        frag1.loctioncity = city;
                        SharedPreferenceUtils.put(MainActivity.this, "city_loction", city);
                        frag1.setlocation();
                        frag1.inthttpdate();
                    } else if (!city.equals((String) SharedPreferenceUtils.get(MainActivity.this, "city_loction", ""))) {
                        SharedPreferenceUtils.put(MainActivity.this, "city_loction", city);
                        frag1.loctioncity = city;
                        frag1.setlocation();
                    } else {
                        frag1.loctioncity = (String) SharedPreferenceUtils.get(MainActivity.this, "city_loction", "");
                        frag1.setlocation();
                    }
                    break;
            }
        }

        ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("http://47.74.147.41:8081/".equals(Constant.URL)) {
            isText.setVisibility(View.VISIBLE);
        } else {
            isText.setVisibility(View.GONE);
        }
        GoogleAssistant(MainActivity.this,"Android首页","MainActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        try {
            aMapLocationClient = new AMapLocationClient(getApplicationContext());
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            aMapLocationClientOption.setOnceLocation(true);
            aMapLocationClientOption.setOnceLocationLatest(true);
            aMapLocationClientOption.setNeedAddress(true);
            aMapLocationClient.setLocationOption(aMapLocationClientOption);
            ApplicationAuthority();
            Bundle savedInstanceState = getSavedInstanceState();
            int where = getIntent().getIntExtra("where", -1);
            if (savedInstanceState == null) {
                fragment_container = findView(R.id.fragment_container);
                transaction = getSupportFragmentManager().beginTransaction();
                if (frag1 == null) {
                    if (where != 0) {
                        frag1 = new Fragment1();
                        transaction.add(R.id.fragment_container, frag1);
                    }
                } else {
                    if (where != 0) {
                        transaction.show(frag1);
                    }
                }
                transaction.show(frag1);
                transaction.commit();
                //判断是否用户登录，未登录则游客登录
                TouristLogin();

            }
        } catch (Exception ex) {
            LogUtil.i("erro", ex.toString());
        }
    }
    @Override
    protected void initData() {
        rb1.setChecked(true);
    }

    @Override
    protected void httpData() {
        onclick();
    }

    private void onclick() {
        CivilMilitaryRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleAssistant(MainActivity.this,"Android军民融合","MainActivity");
                returnShow = true;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                if (civilimilitaryTypeFragment == null) {
                    civilimilitaryTypeFragment = new CivilimilitaryTypeFragment();
                    transaction.add(R.id.fragment_container, civilimilitaryTypeFragment);
                } else {
                    transaction.show(civilimilitaryTypeFragment);
                }
                transaction.commit();
                JmRh.setChecked(true);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                if (JmRh.isChecked()) {
                    CivilMilitaryName.setTextColor(getResources().getColor(R.color.tab_2_day));
                    transaction.show(civilimilitaryTypeFragment);
                } else {
                    CivilMilitaryName.setTextColor(getResources().getColor(R.color.tab_1_day));
                    switch (checkedId) {
                        case R.id.rb1_mainAcitivity:
                            GoogleAssistant(MainActivity.this,"Android首页","MainActivity");
                            rb1.setChecked(true);
                            returnShow = false;
                            if (frag1 == null) {
                                frag1 = new Fragment1();
                                transaction.add(R.id.fragment_container, frag1);
                            } else {
                                transaction.show(frag1);
                                frag1.refreshLocal();
                            }
                            if (civilimilitaryTypeFragment != null) {
                                transaction.hide(civilimilitaryTypeFragment);
                            }
                            break;
                        case R.id.rb2_mainAcitivity:
                            GoogleAssistant(MainActivity.this,"Android学院","MainActivity");
                            rb2.setChecked(true);
                            returnShow = true;
                            if (frag2 == null) {
                                frag2 = new Fragment2();
                                transaction.add(R.id.fragment_container, frag2);
                            } else {
                                transaction.show(frag2);
                                frag2.refresh_date();
                            }
                            break;
                        case R.id.rb3_mainAcitivity:
                            GoogleAssistant(MainActivity.this,"Android订阅","MainActivity");
                            returnShow = true;
                            rb3.setChecked(true);
                            if (fragmen == null) {
                                fragmen = new DYNewFragment();
                                transaction.add(R.id.fragment_container, fragmen);
                            } else {
                                transaction.show(fragmen);
                            }
                            break;
                        case R.id.rbserch_mainAcitivity:
                            GoogleAssistant(MainActivity.this,"Android搜索","MainActivity");
                            searchhideBol =true;
                            searchFrame.setVisibility(View.VISIBLE);
                            if (frag3 == null) {
                                frag3 = new Fragment3();
                                transaction.add(R.id.search_frame, frag3);
                            } else {
                                transaction.remove(frag3);
                                frag3 = new Fragment3();
                                transaction.add(R.id.search_frame, frag3);
                            }
                            break;
                        case R.id.rb5_mainAcitivity:
                            GoogleAssistant(MainActivity.this,"Android我的","MainActivity");
                            returnShow = true;
                            rb5.setChecked(true);
                            if (frag5 == null) {
                                frag5 = new Fragment5();
                                transaction.add(R.id.fragment_container, frag5);
                            } else {
                                transaction.show(frag5);
                                frag5.httpUserInfo();
                            }
                            break;
                        default:
                            break;

                    }
                }
                transaction.commit();
            }
        });
    }

    public void searchhide() {
        getSupportFragmentManager().beginTransaction().remove(frag3).commit();
        rb1.setChecked(true);
        searchhideBol=false;
        frag3 = null;
        searchFrame.setVisibility(View.GONE);
    }

    @Override
    protected void setClickListener() {
    }

    public void choiceSearch() {//搜索界面
        rbserch.setChecked(true);
    }

    public void back() {
        rb1.setChecked(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//上层界面返回数据
        super.onActivityResult(requestCode, resultCode, data);
        if (frag5 != null)
            frag5.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityCodeUtil.MINETABACTIVITY) {
            frag5.httpUserInfo();
        } else if (requestCode == ActivityCodeUtil.LOGINACTIVITY) {
            frag5.setLoginView();
        } else if (requestCode == ActivityCodeUtil.SETTINGACTIVITY) {
            frag5.setLoginView();
        } else if (requestCode == ActivityCodeUtil.FANSACTIVITY || requestCode == ActivityCodeUtil.PERSONALDYNAMICACTIVITY
                || requestCode == ActivityCodeUtil.CAREACTIVITY || requestCode == ActivityCodeUtil.MESSAGEACTIVITY
                || requestCode == ActivityCodeUtil.HISTORYACTIVITY || requestCode == ActivityCodeUtil.COLLEGEACTIVITY
                || requestCode == ActivityCodeUtil.EDITACTIVITY || requestCode == ActivityCodeUtil.SUBSCRIBEACTIVITY
        ) {
            frag5.httpUserInfo();
        } else if (resultCode == ActivityCodeUtil.DY) {
            fragmen.btnRefresh();
        }

    }

    private void init() {
        mannager = DbMannager.getInstance();
    }

    private boolean isQuit = true;

    @Override
    protected void onResume() {

        try {
            if ("true".equals(ActivityCodeUtil.refresh)) {
                fragmen.btnRefresh();
                ActivityCodeUtil.refresh = "";
            }
            if (frag1 != null)
                frag1.refreshLocal();
            if (frag2 != null)
                frag2.refresh_date();
            if (fragmen != null)
                fragmen.refresgdate();

        } catch (NullPointerException ex) {

            LogUtil.i("null", ex.toString());
        }
        super.onResume();
    }

    public void refresh_dydate() {
        if (fragmen != null)
            fragmen.refresgdate();
    }

    @Override
    public void onBackPressed() {
        if (frag3 != null) {
            if (frag3.frame3show)
                frag3.onBackPressed();
            else {
                frag3.cursorvisible();
                onStateNotSaved();
                if (returnShow) {
                    rb1.setChecked(true);
                } else {
                    if(searchhideBol==false) {
                        if ((System.currentTimeMillis() - exitTime) > 2000) {
                            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                                    Toast.LENGTH_SHORT).show();
                            exitTime = System.currentTimeMillis();
                        } else {
                            finish();
                            System.exit(0);
                        }
                    }
                }
            }
        }
        else {
            if (returnShow) {
                rb1.setChecked(true);
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序",
                            Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (JCVideoPlayer.backPress()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //---------------------------自定义函数-----------------------------------------------
    public void hideAllFragment(FragmentTransaction transaction) {
        if (frag1 != null) {
            transaction.hide(frag1);
        }
        if (frag2 != null) {
            transaction.hide(frag2);
        }
        if (frag3 != null) {
            transaction.hide(frag3);
            frag3.frame3show = false;
        }
        if (fragmen != null) {
            transaction.hide(fragmen);
        }
        if (frag5 != null) {
            transaction.hide(frag5);
        }
        if (civilimilitaryTypeFragment != null) {
            transaction.hide(civilimilitaryTypeFragment);
        }
    }

    // private CodeUtils codeUtils;
    public void setReturn(boolean flag2) {
        returnShow = flag2;
        LogUtil.i("xxx", "setReturn:" + returnShow);

    }

    private boolean flag = true;


    public void showTab() {
        setReturn(true);
        LogUtil.i("xxx", "showTab:" + returnShow);
        if (flag) {
            fragLayout_tab.setVisibility(View.VISIBLE);
            flag = !flag;
        } else {
            channalFragment.fragmentScrollOrigin();
        }

    }

    public void cancelTab() {
        LogUtil.i("xxx", "cancelTab");

        channalFragment.fragmentScrollTop();
        channalFragment.cancelFragment();
    }

    public void updateData(int position) {
        if (frag1 != null) {
            //  frag1.setData(1);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String clickTab = intent.getStringExtra("clickTab");
        String channelName = intent.getStringExtra("channelName");
        LogUtil.i("clickTab", "clickTab:" + clickTab);
        searchStr = clickTab;
        if (clickTab != null) {
            choiceSearch();
        } else if (channelName != null) {
            mannager.changeChoiceChannel(channelName);
            String idString = mannager.getChoiceChannelId("channel2");
            LogUtil.i("idString", "idString:" + idString);
            if (idString != null) {
                Call<ResponseBody> call = MyApplication.getNetApi().uploadChannel(idString, (String) SharedPreferenceUtils.get(this, "app_token", ""));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            LogUtil.i("json3", "json:" + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            LogUtil.i("json3", "catch");

                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LogUtil.i("json3", "onFailure");

                        call.cancel();
                    }
                });
            } else {
                LogUtil.i("json3", "idString==null");

            }
            int channelPosition = mannager.getChannelPosition(channelName);
            if (channelPosition > 0) {
                channelPosition--;
            }
        }
    }

    //---------------------------自定义函数End-----------------------------------------------
    public void addFragment() {
        GoogleAssistant(MainActivity.this,"Android订阅","MainActivity");
        transaction = getSupportFragmentManager().beginTransaction();
        returnShow = true;
        if (fragmen == null) {
            fragmen = new DYNewFragment();
            transaction.add(R.id.fragment_container, fragmen).commit();
        } else {
            transaction.show(fragmen).commit();
        }
        fragLayout_tab.setVisibility(View.GONE);
        rb1.setChecked(true);
        rb3.setChecked(true);
    }
    //申请权限
    private void ApplicationAuthority() {
        permissionUtil = new PermissionUtil(this);
        permissionUtil.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
            @Override
            public void onGranted() {
                PushAgent.getInstance(MainActivity.this).onAppStart();
                init();
                if ("Y".equals((String) SharedPreferenceUtils.get(MainActivity.this, "isFirstOpens", "Y"))) {
                    SharedPreferenceUtils.put(MainActivity.this, "isFirstOpens", "O");
                    SharedPreferenceUtils.put(MainActivity.this, "city_loction", "");
                }
                AMapLocationListener aMapLocationListener = new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation amapLocation) {
                        if (amapLocation != null) {
                            if (amapLocation.getErrorCode() == 0) {
                                String city_1 = amapLocation.getCity();//城市信息
                                city = city_1;
                                SharedPreferenceUtils.put(MainActivity.this, "city_loction_static", city);
                            } else {
                                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                                Log.e("AmapError", "location Error, ErrCode:"
                                        + amapLocation.getErrorCode() + ", errInfo:"
                                        + amapLocation.getErrorInfo());
                            }
                        }
                    }

                };
                aMapLocationClient.setLocationListener(aMapLocationListener);
                aMapLocationClient.startLocation();
                //  new LongTimeTask().execute("New Text");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (frag1 == null) ;
                            while (city.equals(""))
                                aMapLocationClient.startLocation();
                            Message message = new Message();
                            message.what = 1;
                            mHandler.sendMessage(message);
                        } catch (Exception e) {
                            LogUtil.i("start:", e.toString());
                        }

                    }
                }).start();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                ToastUtils.showMessage(MainActivity.this, "您刚拒绝了一个必要权限");
            }

            @Override
            public void onShouldShowRationale(List<String> deniedPermission) {
                LogUtil.i("permisson", "-----3----");

            }
        });
    }
    //游客登录方法
    private void TouristLogin() {
        if (!"true".equals(((String) SharedPreferenceUtils.get(this, "vistor", "")))) {
            final Call<DefaultBean2> call1 = MyApplication.getNetApi().visitor(DeviceUtils.getUniqueId(MainActivity.this));
            call1.enqueue(new Callback<DefaultBean2>() {
                @Override
                public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                    if (response.isSuccessful()) {
                        SharedPreferenceUtils.put(MainActivity.this, "app_token", response.body().getdate());
                    }
                }

                @Override
                public void onFailure(Call<DefaultBean2> call, Throwable t) {
                    ToastUtils.showMessage(MainActivity.this, t.toString());
                    call.cancel();
                }

            });
        }
    }
    //获取IMSI号
    public static String getIMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = telephonyManager.getSubscriberId();
            if (null == imsi) {
                imsi = "";
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

