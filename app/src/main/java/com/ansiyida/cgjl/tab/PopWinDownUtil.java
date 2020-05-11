package com.ansiyida.cgjl.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.ansiyida.cgjl.util.ToastUtils;

public class PopWinDownUtil {

    private Context context;
    private View contentView;
    private View relayView;
    private PopupWindow popupWindow;
    public PopWinDownUtil(Context context, View contentView, View relayView){
        this.context = context;
        this.contentView = contentView;
        this.relayView = relayView;

        init();
    }
    @SuppressLint("WrongConstant")
    public void init(){
        //内容，高度，宽度
     //   DisplayMetrics dm = new DisplayMetrics();
      //  dm =context.getApplicationContext().getResources().getDisplayMetrics();
     //   int screenWidth = dm.densityDpi;
     //   int screenHeight = dm.densityDpi/4*3;
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
    //    popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight, true);
        //动画效果
      //  popupWindow.setAnimationStyle(R.style.AnimationTopFade);
        //菜单背景色
     //  ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
     //   ColorDrawable dw = new ColorDrawable(0x000000);
     //   popupWindow.setBackgroundDrawable(dw);

        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(onDismissLisener != null){
                    onDismissLisener.onDismiss();

                }
            }
        });
    }
    public void show(){
        //显示位置
      /*  WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.4f;
        window.setAttributes(lp);*/
       popupWindow.showAsDropDown(relayView,0,0);
      //  popupWindow.showAtLocation(relayView, Gravity.CENTER, 0, 0);
    }
    public void hide(){
     /*   WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);*/
     try {
         if(popupWindow != null)
             if(popupWindow.isShowing())
                 popupWindow.dismiss();
     }
   catch (NullPointerException ex)
   {
       ToastUtils.showMessage(context, ex.toString());

   }
       /* if(popupWindow != null && popupWindow.isShowing()){

        }*/
    }

    private OnDismissLisener onDismissLisener;
    public void setOnDismissListener(OnDismissLisener onDismissLisener){
        this.onDismissLisener = onDismissLisener;
    }
    public interface OnDismissLisener{

        void onDismiss();
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }
}
