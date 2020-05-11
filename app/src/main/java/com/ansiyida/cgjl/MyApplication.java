package com.ansiyida.cgjl;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.ansiyida.cgjl.adapter.IntegerDefault0Adapter;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.http.NetApi;
import com.ansiyida.cgjl.http.NetctiyApi;
import com.ansiyida.cgjl.tab.SQLHelper;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ansiyida on 2017/11/8.
 */
public class MyApplication extends Application implements GlideModule {
    private static MyApplication instance;
    private static Retrofit retrofit;
    private static Retrofit retrofit_city;
    private static NetApi netApi;
    private static NetctiyApi netcityApi;
    public static Gson gson;
    private static Context mContext;
    private SQLHelper sqlHelper;
    private List<Activity> activities = new ArrayList<>();
    public  static  OkHttpClient client;

    public static  FirebaseAnalytics mFirebaseAnalytics;

    static {
        //设置全局的header构造器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader( Context context,  RefreshLayout layout) {
                ClassicsHeader classicsHeader = new ClassicsHeader(context);
                classicsHeader.setEnableLastTime(false);
                classicsHeader.setDrawableSize(20).setBackgroundColor(context.getResources().getColor(R.color.white));
                return classicsHeader;
            }
        });

        //设置全局得Footer构造器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter( Context context,  RefreshLayout layout) {
                ClassicsFooter classicsFooter = new ClassicsFooter(context);
                classicsFooter.setDrawableSize(20).setBackgroundColor(context.getResources().getColor(R.color.white));
                return classicsFooter;
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        mContext = getApplicationContext();
        client = new OkHttpClient.Builder()
                //.protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

//        业务链接
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .client(client).build();

        retrofit_city = new Retrofit.Builder()
                .baseUrl("http://api.map.baidu.com/")
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .client(client).build();
        netApi = retrofit.create(NetApi.class);
        netcityApi= retrofit_city.create(NetctiyApi.class);

        String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        if ("".equals(app_token)) {
            SharedPreferenceUtils.put(this, "app_token", DeviceUtils.getUniqueId(this));
        }
        initUM();

        registerAppKey();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
    private void initUM() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        String channelName = AnalyticsConfig.getChannel(this);
        if (channelName==null||"".equals(channelName)){
            channelName="APP-AN-SAMSUNG";
        }                                
        UMConfigure.init(this, "5bfca249b465f59168000322", channelName, UMConfigure.DEVICE_TYPE_PHONE, "5987091fc9af81e0f3afd5a9f0a19258");
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtil.i("token", "device:" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.i("token", "onFailure");
            }
        });

        UMConfigure.setEncryptEnabled(true);
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setSessionContinueMillis(1000);
    }
    /**
     * 注册各个平台的配置appkey，
     * (建议放在全局Application或者程序入口)
     */
    private void registerAppKey() {
        UMShareAPI.get(this);
        Config.DEBUG = true;
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx8855ca7656c29232", "552f6344d6b424a35dfae9ee94b0fdac");

        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("1424240373", "c9de95e222dc1c721ceeb4e24ffb2fff","http://www.calcnext.com");
//        PlatformConfig.setSinaWeibo( "3716932488",      "f8d8d63e30773525b919d7bf64a1f1e3","");

        //            appid appkey
        PlatformConfig.setQQZone("101512447", "5852470732bd6c1e3c6ac0604de7d028");

    }
    //dcl模式
    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                instance = new MyApplication();
            }
        }
        return instance;
    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(instance);
        return sqlHelper;
    }

    public static NetApi getNetApi() {
        return netApi;
    }
    public static NetctiyApi getNetApi_city() {
        return netcityApi;
    }
    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Integer.class, new IntegerDefault0Adapter()).registerTypeAdapter(int.class, new IntegerDefault0Adapter()).create();
        }
        return gson;
    }

    /**
     * 销毁部分activity
     * 想移除的对象的
     * activityName 是  MainActivity
     */
    public void finishPartActivityWant(String activityName) {
        String getActivityName = null;
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                getActivityName = activity.getClass().getName();
                getActivityName = getActivityName.substring(getActivityName.lastIndexOf(".") + 1);
                if (activityName.equals(getActivityName)) {
                    activity.finish();
                }
            }
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        LogUtil.i("MyApplication", "acitvityName---:" + activity.getClass().getName());
        if (activities != null && activities.size() > 0) {
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        } else {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level==TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ActivityManager activityManager= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (null!=activityManager){
            ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            builder.setDecodeFormat(memoryInfo.lowMemory? DecodeFormat.PREFER_RGB_565:DecodeFormat.PREFER_ARGB_8888);
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void registerComponents(Context context, Glide glide) {

    }

}
