package com.ansiyida.cgjl.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.util.LogUtil;

import static fm.jiecao.jcvideoplayer_lib.JCMediaManager.TAG;

/**
 * Created by ansiyida on 2018/3/12.
 */
public class LoadingDialog_apply {
    private Dialog progDialog = null;

    private static LoadingDialog_apply loadingDialog;

    public static LoadingDialog_apply getInstance() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog_apply();
        }
        return loadingDialog;
    }

    /**
     * 显示dialog
     *
     * 为参数的dialog
     */
    public void showDialog() {
        Context context= MyApplication.getmContext();
        progDialog = new Dialog(context, R.style.load_theme_dialog);
        progDialog.setContentView(R.layout.loading_dialog_collect);
        progDialog.setCanceledOnTouchOutside(false);
//      progDialog.setCancelable(false);//设置按返回键是否响应返回，这是不响应
        progDialog.show();

        progDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    LogUtil.i("LoadingDialog", "----progDialog.setOnKeyListener---:");

                }
                return false;
            }
        });

        TextView testView = (TextView) progDialog.findViewById(R.id.loading);
        testView.setText("提交成功");
    }

    /**
     * 显示dialog
     *
     * @param context 上下文
     */
    public void showDialog(Context context) {
        progDialog = new Dialog(context, R.style.load_theme_dialog);
        progDialog.setContentView(R.layout.loading_dialog_collect);
        progDialog.setCanceledOnTouchOutside(false);
//      progDialog.setCancelable(false);//设置按返回键是否响应返回，这是不响应
        progDialog.show();

        progDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    LogUtil.i("LoadingDialog", "----progDialog.setOnKeyListener---:");
                }
                return false;
            }
        });

        TextView testView = (TextView) progDialog.findViewById(R.id.loading);
        testView.setText("提交成功");
    }

    /**
     * 显示dialog
     *
     * @param context 上下文
     * @param text 提示内容
     */
    public void showDialog(Context context, String text) {
        progDialog = new Dialog(context, R.style.load_theme_dialog);
        progDialog.setContentView(R.layout.loading_dialog_apply);
        progDialog.setCanceledOnTouchOutside(false);
//      progDialog.setCancelable(false);//设置按返回键是否响应返回，这是不响应
        progDialog.show();

        progDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    LogUtil.i("LoadingDialog", "----progDialog.setOnKeyListener---:");
                }
                return false;
            }
        });

        TextView testView = (TextView) progDialog.findViewById(R.id.loading);
        if ("".equals(text)){
            text="提交成功";
        }
        testView.setText(text);
    }

    public void disMissDialog() {
        try
        {
        if (progDialog != null) {
            if (progDialog.isShowing()) {
                progDialog.dismiss();
            }
            progDialog = null;
        }
        System.gc();
    }
    catch (IllegalArgumentException ex)
    {
        Log.i(TAG, ex.toString());
    }
    }

    public boolean isDialogShow() {
        boolean result = false;
        if (progDialog != null) {
            result = progDialog.isShowing();
        }
        return result;
    }

}
