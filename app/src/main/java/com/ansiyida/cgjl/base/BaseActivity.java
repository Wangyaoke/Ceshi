package com.ansiyida.cgjl.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.listener.IHttpStatues;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.NetType;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chen on 2017/11/7.
 */
public abstract class BaseActivity extends AppCompatActivity implements IHttpStatues {
    private Bundle savedInstanceState;
    private String uniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if (initStatusBarType()) {//隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        } else {
            setStatusBarFullTransparent();
            setStateColor(this,true);
        }

        setContentView(R.layout.activity_base);
        LinearLayout mChildContentLinear = (LinearLayout) findViewById(R.id.ll_child_content);
        MyApplication.getInstance().addActivity(this);
        //加载子页面的布局文件
        View view = LayoutInflater.from(this).inflate(getContentView(), null);
        //导入包要导入线程布局-父亲控件是什么就导入什么的包
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //把子页面的布局文件添加进来
        mChildContentLinear.addView(view, params);
        ButterKnife.bind(this);
        initView();
        initData();
        httpData();
        setClickListener();
        uniqueId = DeviceUtils.getUniqueId(this);
        //控制手机屏幕竖屏显示
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    public void httpCheck() {
        int apnType = NetWorkUtils.getAPNType(this);
        onHttpStatues(apnType);
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showInputMethod() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 加载子页的布局 --子类实现
     */
    protected abstract int getContentView();

    /**
     * 初始化控件id
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化数据
     */
    protected abstract void httpData();

    /**
     * 点击事件的监听
     */
    protected abstract void setClickListener();

    /**
     * 初始化状态栏类型
     */
    protected boolean initStatusBarType() {
        return false;
    }

    public static void setStateColor(Activity activity, boolean flag) {
        setMiuiStatusBarDarkMode(activity, flag);
        setMeizuStatusBarDarkIcon(activity, flag);
        if (flag) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }
    public void GoogleAssistant(Activity context,String itemName, String CurrentString){
        MyApplication.mFirebaseAnalytics.setUserProperty("favorite_food", "用户属性"); // 2. 上报用户属性favorite_food
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        MyApplication.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        MyApplication.mFirebaseAnalytics.setCurrentScreen(context,CurrentString,context.getClass().getSimpleName());

    }
    @Override
    public void onHttpStatues(int connectType) {
        if (connectType == NetType.TYPE_NO) {
            ToastUtils.showMessage(this, "无网络连接");
        } else if (connectType == NetType.TYPE_WIFI) {
            ToastUtils.showMessage(this, "Wifi连接");

        } else if (connectType == NetType.TYPE_2G) {
            ToastUtils.showMessage(this, "2G连接");

        } else if (connectType == NetType.TYPE_3G) {
            ToastUtils.showMessage(this, "3G连接");

        } else if (connectType == NetType.TYPE_4G) {
            ToastUtils.showMessage(this, "4G连接");
        }
    }
    /**
     * 退出登录操作
     */
    public void exitLogin(){
        SharedPreferenceUtils.put(this, "app_token", DeviceUtils.getUniqueId(this));
        SharedPreferenceUtils.put(this, "user_touXiang", "");
        SharedPreferenceUtils.put(this, "userName", "");
        SharedPreferenceUtils.put(this, "jianJie", "");
        SharedPreferenceUtils.put(this, "strand_id", "");
        SharedPreferenceUtils.put(this, "jmi_type", "");
        SharedPreferenceUtils.put(this, "vipLevel", "");
        SharedPreferenceUtils.put(this, "phoneNum", "");
        SharedPreferenceUtils.put(this, "vistor", "");
        final Call<DefaultBean2> call1 = MyApplication.getNetApi().visitor(uniqueId);
        call1.enqueue(new Callback<DefaultBean2>() {
            public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                if (response.isSuccessful()) {
                    SharedPreferenceUtils.put(BaseActivity.this, "app_token", response.body().getdate());
                }
            }

            public void onFailure(Call<DefaultBean2> call, Throwable t) {
                ToastUtils.showMessage(BaseActivity.this, t.toString());
                call.cancel();
            }
        });
    }
    /**
     * 更改MIUI系统(小米手机)的状态栏颜色
     */
    private static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更改Flyme系统（魅族手机）状态栏颜色
     */
    private static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);//销毁栈中的activity对象
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
