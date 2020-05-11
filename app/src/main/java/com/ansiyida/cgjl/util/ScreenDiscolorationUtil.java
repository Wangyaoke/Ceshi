package com.ansiyida.cgjl.util;

import android.app.Activity;
import android.view.WindowManager;

public class ScreenDiscolorationUtil {
    //屏幕变色
    // ==1：白色
    // <1:暗色
    public static  void ChangeDiscoloration(Activity activity,float degree){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = degree;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }
}
