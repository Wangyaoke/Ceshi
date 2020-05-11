package com.ansiyida.cgjl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.dialog.LoadingDialog_tj;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.GlideCacheUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity{
    @Bind(R.id.relative_changePassword)
    RelativeLayout relative_changePassword;
    @Bind(R.id.bindNum)
    TextView bindNum;
    @Bind(R.id.bindNum1)
    TextView bindNum1;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.image_wifiPhoto)
    ImageView wifiPhoto;
    @Bind(R.id.image_wifiVideo)
    ImageView wifiVideo;
    @Bind(R.id.image_tuiSong)
    ImageView tuiSong;
    @Bind(R.id.exit_login)
    TextView exit_login;
    @Bind(R.id.text_huanCunCount)
    TextView text_huanCunCount;
    @Bind(R.id.relative_zhangHao)
    RelativeLayout relative_zhangHao;
    @Bind(R.id.tv_version)
    TextView tv_version;
    private GlideCacheUtil cacheUtil;
    private Bitmap myBitmap;
    private String oldPhone;
    private LoadingDialog_tj loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(SettingActivity.this,"Android设置","SettingActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        loadingDialog = new LoadingDialog_tj();
        setStateColor(this, true);//设置状态栏字体颜色
        oldPhone=(String) SharedPreferenceUtils.get(this, "phoneNum", "null");
    }
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //更新UI
            switch (msg.what)
            {
                case 1:
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    break;
            }
        };
    };
    @Override
    protected void initData() {
        title.setText("设置");
        boolean photoFlag= (boolean) SharedPreferenceUtils.get(this,"wifiPhoto",false);
        boolean videoFlag= (boolean) SharedPreferenceUtils.get(this,"wifiVideo",false);
        boolean tuiSongFlag= (boolean) SharedPreferenceUtils.get(this,"tuiSong",false);

        setButtonStatues(wifiPhoto, photoFlag);
        setButtonStatues(wifiVideo,videoFlag);
        setButtonStatues(tuiSong,tuiSongFlag);
        String phone = (String) SharedPreferenceUtils.get(this, "phoneNum", "null");
        if("".equals(phone)||"null".equals(phone)) {
            relative_changePassword.setVisibility(View.GONE);
            bindNum.setVisibility(View.GONE);
            bindNum1.setVisibility(View.GONE);}
        else {
            relative_changePassword.setVisibility(View.VISIBLE);
            bindNum.setVisibility(View.VISIBLE);
            bindNum1.setVisibility(View.VISIBLE);
        }
        cacheUtil = GlideCacheUtil.getInstance();
        text_huanCunCount.setText(cacheUtil.getCacheSize(this));

    }

    @Override
    protected void httpData() {
        tv_version.setText("V"+DeviceUtils.getAppVersionName(this));
    }

    @Override
    protected void setClickListener() {

    }
    @OnClick({R.id.relative_haoping,R.id.relative_phoneNum,R.id.relative_changePassword,R.id.image_back,R.id.image_wifiPhoto,R.id.image_wifiVideo,R.id.image_tuiSong,R.id.relative_zhangHao,R.id.relative_clearHuanCun,
            R.id.exit_login,R.id.relative_tuiJian,R.id.relative_banbenshengming,R.id.image2})
    public void click(View view){
        switch (view.getId()){
            case R.id.image2:           //1.返回上一层按钮
                loadingDialog.showDialog(this, "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    try {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    } catch (Exception e) {
                        LogUtil.i("start:", e.toString());
                    }
                    }
                }).start();
                break;
            case R.id.relative_haoping:           //1.返回上一层按钮
                String manufacturer = Build.MANUFACTURER;
                //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
                if ("huawei".equalsIgnoreCase(manufacturer)) {
                    if (TextUtils.isEmpty("com.ansiyida.cgjl")) return;
                    Uri uri = Uri.parse("market://details?id=" + "com.ansiyida.cgjl");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    if (!TextUtils.isEmpty("com.huawei.appmarket")) {
                        intent.setPackage("com.huawei.appmarket");
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    //安装了应用宝
                    if(isMobile_spExist()){
                        launchAppDetail(this,"com.ansiyida.cgjl","com.tencent.android.qqdownloader");
                    }else {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse("https://sj.qq.com/myapp/detail.htm?apkName=com.tencent.android.qqdownloader");
                        intent.setData(content_url);
                        this.startActivity(intent);
                    }
                }
                break;
            case R.id.image_back:           //1.返回上一层按钮
                this.finish();
                break;
            case R.id.relative_banbenshengming:           //1.返回上一层按钮
                loadingDialog.showDialog(this, "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Message message = new Message();
                            message.what = 1;
                            mHandler.sendMessage(message);
                        } catch (Exception e) {
                            LogUtil.i("start:", e.toString());
                        }

                    }
                }).start();
                break;
            case R.id.image_wifiPhoto:      //2.wifi加载网络图片按钮
                boolean photoFlag= (boolean) SharedPreferenceUtils.get(this,"wifiPhoto",false);
                setButtonStatues(wifiPhoto,!photoFlag);
                SharedPreferenceUtils.put(this, "wifiPhoto", !photoFlag);

                break;
            case R.id.image_wifiVideo:     //3.wifi自动播放视频按钮
                boolean videoFlag= (boolean) SharedPreferenceUtils.get(this,"wifiVideo",false);
                setButtonStatues(wifiVideo,!videoFlag);
                SharedPreferenceUtils.put(this, "wifiVideo", !videoFlag);


                break;
            case R.id.image_tuiSong:       //4.推送按钮
                boolean tuiSongFlag= (boolean) SharedPreferenceUtils.get(this,"tuiSong",false);
                setButtonStatues(tuiSong,!tuiSongFlag);
                SharedPreferenceUtils.put(this,"tuiSong",!tuiSongFlag);
                break;
            case R.id.relative_zhangHao:   //5.账号与隐私设置
                            startActivity(new Intent(this, PrivacyActivity.class));
                break;

            case R.id.relative_clearHuanCun://6.清理缓存
                initPopuWindow_cache();
                break;
            case R.id.exit_login:           //7.退出登录
                exitLogin();
                bindNum.setText("绑定手机号");
                SettingActivity.this.finish();
                break;

            case R.id.relative_tuiJian:             //9.推荐给好友
                if (myBitmap == null) {
                    myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
                }
                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                        };
                UMImage image = new UMImage(SettingActivity.this.getApplicationContext(), myBitmap);
                UMWeb web = new UMWeb("https://cg.calcnext.com/downLoadApp");
                web.setTitle("采购精灵-提供军工行业一站式采购服务");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("采购精灵");//描述
                new ShareAction(SettingActivity.this)
                        .withMedia(web)
                        .setDisplayList(displaylist)
                        .setCallback(umShareListener)
                        .open();

                break;
            case R.id.relative_phoneNum:        //1.绑定或更改绑定手机号
                if("true".equals(SharedPreferenceUtils.get(SettingActivity.this, "vistor", ""))) {   // if ("USER_LOGIN_TOKEN".equals(((String)SharedPreferenceUtils.get(this,"app_token","")).substring(0,16))){
                    if ("绑定手机号".equals(bindNum.getText())){
                        Intent intent_read = new Intent(this, BindPhoneActivity.class);
                        intent_read.putExtra("title", "绑定手机");
                        intent_read.putExtra("btn", "绑定");
                        this.startActivityForResult(intent_read, 1111);
                    }else {
                        Intent intent_read = new Intent(this, BindPhoneActivity.class);
                        intent_read.putExtra("title", "修改绑定手机");
                        intent_read.putExtra("btn", "立即更换");
                       // startActivity(intent_read);
                        this.startActivityForResult(intent_read, 1111);
                    }
                }else {
                  //  ToastUtils.showMessage(SettingActivity.this, "请登录");
                    startActivity(new Intent(this,LoginActivity.class));
                }


                break;
            case R.id.relative_changePassword:  //2.修改密码
                if ("USER_LOGIN_TOKEN".equals(((String)SharedPreferenceUtils.get(SettingActivity.this,"app_token","")).substring(0,16))){
                    startActivity(new Intent(this,ChangePasswordActivity.class));
                }else {
                    ToastUtils.showMessage(SettingActivity.this, "请登录");
                }


                break;

            default:break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 ) {
            if (data != null) {
                String phone = (String) SharedPreferenceUtils.get(this, "phoneNum", "null");
                if (phone.length()==11){
                    bindNum.setText(phoneMi(phone));

                }else {
                    bindNum.setText("绑定手机号");
                }
            }
        }


    }
    protected void onResume() {
        super.onResume();
        String phone = (String) SharedPreferenceUtils.get(this, "phoneNum", "null");
        if("true".equals(SharedPreferenceUtils.get(SettingActivity.this, "vistor", ""))) {
            exit_login.setVisibility(View.VISIBLE);
            bindNum1.setVisibility(View.VISIBLE);
            if (phone.length() == 11) {
                bindNum.setText(phoneMi(phone));

            } else {
                bindNum.setText("绑定手机号");
            }
            if (!oldPhone.equals(phone)) {
                ToastUtils.showMessage(this, "修改成功");
                oldPhone = phone;
            }
        }else{
            exit_login.setVisibility(View.GONE);
            bindNum1.setVisibility(View.GONE);
            relative_changePassword.setVisibility(View.GONE);
        }
    }
    private String phoneMi(String phone){
        String phoneBegin=phone.substring(0,3);
        String phoneEnd=phone.substring(7, 11);
        return phoneBegin+"****"+phoneEnd;
    }
    private void setButtonStatues(ImageView imageView,boolean flag){
        if (flag){
            imageView.setImageResource(R.mipmap.button_open);
        }else {
            imageView.setImageResource(R.mipmap.button_close);
        }
    }
    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(SettingActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(SettingActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(SettingActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(SettingActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                ToastUtils.showMessage(SettingActivity.this, "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(SettingActivity.this, "分享失败,msg:" + t.getMessage());

            }
            LogUtil.i("share", "分享失败,msg:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };
    /**
     * 智库号弹框
     */
    private PopupWindow popupWindow2;
    private View contentView2;

    private void initPopuWindow_dropMenu(String html) {

        if (popupWindow2 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView2 = mLayoutInflater.inflate(R.layout.popwindow_dilog_bb, null);
            popupWindow2 = new PopupWindow(contentView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
//        String html="<p>移动端智库号的认证暂未开通，请使用电脑浏览器访问<font color=\"#0a8cb0\">www.jisuanweilai.com.cn</font>完成认证</p>";

//        String html="移动端智库号的认证暂未开通，请使用电脑浏览器访问www.jisuanweilai.com.cn完成认证";
        RelativeLayout relative_out = (RelativeLayout) contentView2.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView2.findViewById(R.id.relative_in);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });

     /*   int posBegin = html.indexOf("w");
        int posEnd = html.lastIndexOf("m") + 1;
        SpannableStringBuilder spannable = new SpannableStringBuilder(html);
        spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.tab_2_day)), posBegin, posEnd
                , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        //  TextView text = (TextView) contentView2.findViewById(R.id.text_popWindow);
        //  text.setText(html);



             //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setFocusable(true);
        popupWindow2.showAtLocation(contentView2, Gravity.CENTER, 0, 0);
        popupWindow2.update();
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
    /**
     * 清除缓存
     */
    private PopupWindow popupWindow_cache;
    private View contentView_cache;
    public void initPopuWindow_cache() {
        if (popupWindow_cache == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_cache = mLayoutInflater.inflate(R.layout.popwindow_cache, null);
            popupWindow_cache = new PopupWindow(contentView_cache, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out= (RelativeLayout) contentView_cache.findViewById(R.id.relative_out);
        RelativeLayout relative_in= (RelativeLayout) contentView_cache.findViewById(R.id.relative_in);
        TextView tv_sure= (TextView) contentView_cache.findViewById(R.id.tv_sure);
        TextView tv_cancel= (TextView) contentView_cache.findViewById(R.id.tv_cancel);
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
                cacheUtil.clearImageDiskCache(SettingActivity.this, new CacheFinish() {
                    @Override
                    public void isFinish() {
                        text_huanCunCount.setText("0M");
                        ToastUtils.showMessage(SettingActivity.this, "清除完成");
                    }
                });
                popupWindow_cache.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();

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

    public interface CacheFinish{
         void isFinish();
    }
    public boolean isMobile_spExist() {
        PackageManager manager = this.getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo pI = pkgList.get(i);
            if (pI.packageName.equalsIgnoreCase("com.tencent.android.qqdownloader"))
                return true;
        }
        return false;
    }
    public void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SettingActivity.this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
