package com.ansiyida.cgjl.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/28.
 */
public class UMUtil {
    /**
     * 判断APP是否安装
     *
     * @param context 上下文
     * @param packageName 包名
     * @Return
     * QQ包名：com.tencent.mobileqq 微信包名：com.tencent.mm，微博包名：com.sina.weibo
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取系统中安装的所有软件信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
