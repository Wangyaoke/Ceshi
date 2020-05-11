package com.ansiyida.cgjl.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.VersionBean;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SDCardUtils;
import com.ansiyida.cgjl.util.ScreenUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.util.permissions.PermissionListener;
import com.ansiyida.cgjl.util.permissions.PermissionUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    @Bind(R.id.backgroundview)
    RelativeLayout backgroundview;
    private final Handler mHandler = new MyHandler(this);
    private DbMannager mannager;

    /**
     * 使用静态的内部类，不会持有当前对象的引用
     */
    private static class MyHandler extends Handler {
        private final WeakReference<SplashActivity> mActivity;

        public MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() == null) {
                return;
            }
            mActivity.get().toTo(msg);
        }
    }

    /**
     * 处理消息
     * @param msg 消息标记
     */
    private void toTo(Message msg) {
        switch (msg.what) {
            case 0:                     //1.进入首页
                if ("N".equals((String) SharedPreferenceUtils.get(this, "isFirstOpens", "N"))) {
                    startActivity(new Intent(this, GuideActivity.class));
                } else {
                   //startActivity(new Intent(this, BigDataListActivity.class));
                   startActivity(new Intent(this, MainActivity.class));
                }
                this.finish();
                overridePendingTransition(R.anim.defaul2, R.anim.defaul);
                break;
            case 1:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(SplashActivity.this,"AndroidSplash","MainActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
      int height=ScreenUtil.getScreenHeight(this);
      if(height>1920)
          backgroundview.setBackgroundResource(R.mipmap.begin_page1);
      else
        backgroundview.setBackgroundResource(R.mipmap.begin_page);
        PermissionUtil permissionUtil = new PermissionUtil(this);
        permissionUtil.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                LogUtil.i("permisson", "-----1----");
                mannager = DbMannager.getInstance();
                if (!mannager.isTableHave("interst_tab")) {
                    SDCardUtils.deleteFile(Constant.dbPath + "ansiyida.db");
                    mannager.closeDatabase();
                }
                mHandler.sendEmptyMessageDelayed(1, 200);
                Call<VersionBean> call = MyApplication.getNetApi().getVersion();
                call.enqueue(new Callback<VersionBean>() {
                    @Override
                    public void onResponse(Call<VersionBean> call, Response<VersionBean> response) {
                        if (response.isSuccessful()) {
                            VersionBean.DataBean data = response.body().getData();
                            if (data != null) {
                                String version = data.getVersion();
                                String appVersionName = DeviceUtils.getAppVersionName(SplashActivity.this);
                                LogUtil.i("version", "versionName:" + appVersionName + ",updataPoint:" + (String) SharedPreferenceUtils.get(SplashActivity.this, "updatePoint", ""));
                                if (!version.equals(appVersionName) && !appVersionName.equals((String) SharedPreferenceUtils.get(SplashActivity.this, "updatePoint", ""))) {
                                    initPopuWindow_report();
                                } else {
                                    mHandler.sendEmptyMessageDelayed(0, 1500);
                                }
                            } else {
                                mHandler.sendEmptyMessageDelayed(0, 1500);
                            }

                        } else {
                            mHandler.sendEmptyMessageDelayed(0, 1500);
                        }
                    }

                    @Override
                    public void onFailure(Call<VersionBean> call, Throwable t) {
                        mHandler.sendEmptyMessageDelayed(0, 1500);
                    }
                });
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                ToastUtils.showMessage(SplashActivity.this, "您刚拒绝了一个必要权限");
            }

            @Override
            public void onShouldShowRationale(List<String> deniedPermission) {
                LogUtil.i("permisson", "-----3----");

            }
        });

//        LogUtil.i("value","version:"+mannager.getValueForKey("version"));
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
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private PopupWindow popupWindow_version;
    private View contentView_version;

    public void initPopuWindow_report() {
        if (popupWindow_version == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_version = mLayoutInflater.inflate(R.layout.popwindow_version, null);
            popupWindow_version = new PopupWindow(contentView_version, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out = (RelativeLayout) contentView_version.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView_version.findViewById(R.id.relative_in);
        TextView tv_cancel = (TextView) contentView_version.findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) contentView_version.findViewById(R.id.tv_sure);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //取消按钮
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtils.put(SplashActivity.this, "updatePoint", DeviceUtils.getAppVersionName(SplashActivity.this) + "");
                mHandler.sendEmptyMessageDelayed(0, 1500);
                popupWindow_version.dismiss();
            }
        });
        //确定按钮
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtils.put(SplashActivity.this, "updatePoint", DeviceUtils.getAppVersionName(SplashActivity.this) + "");
//                mHandler.sendEmptyMessageDelayed(0, 0);
                popupWindow_version.dismiss();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://18042899642.fx.sj.360.cn/qcms/view/t/detail?id=3979045");
                intent.setData(content_url);
                startActivity(intent);
                SplashActivity.this.finish();

            }
        });

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_version.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_version.setOutsideTouchable(true);
        popupWindow_version.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow_version.showAtLocation(contentView_version, Gravity.CENTER, 0, 0);
        popupWindow_version.update();
        popupWindow_version.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

}
