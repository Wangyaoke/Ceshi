package com.ansiyida.cgjl.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by chenyaoxiang on 2017/11/7.
 */
public class BaseTools {
    /** 获取屏幕的宽度 */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
