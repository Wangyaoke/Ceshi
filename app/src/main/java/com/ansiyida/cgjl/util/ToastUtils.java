package com.ansiyida.cgjl.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast 工具类
 *
 * @author v-chenyaoxiang
 */

public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object object = new Object();
    private static int time=Toast.LENGTH_SHORT;

    /**
     * 普通文本显示 默认显示为Toast.LENGTH_LONG
     *
     * @param context
     * @param text
     */

    public static void showMessage(final Context context, final String text) {
        if (context!=null){
            showMessage(context.getApplicationContext(), text, time);
        }
    }

    /**
     * 默认显示为Toast.LENGTH_LONG
     *
     * @param context
     * @param msg
     */

    public static void showMessage(final Context context, final int msg) {
        showMessage(context.getApplicationContext(), msg, time);
    }

    /**
     * 在长文本显示
     *
     * @param context
     * @param msg
     */

    public static void showMessageLong(final Context context, final String msg) {
        showMessage(context.getApplicationContext(), msg, time);
    }

    /**
     * 显示为Toast.LENGTH_SHORT
     *
     * @param context
     * @param msg
     */
    public static void showMessageShort(final Context context, final String msg) {
        showMessage(context.getApplicationContext(), msg, time);
    }

    /**
     * 显示为Toast.LENGTH_SHORT
     *
     * @param context
     * @param msg
     */
    public static void showMessageShort(final Context context, final int msg) {
        showMessage(context.getApplicationContext(), msg, time);
    }

    /**
     * Toast发送文本
     *
     * @param context
     * @param msg
     * @param len
     */
    public static void showMessage(final Context context, final int msg, final int len) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (object) {
                            if (toast != null) {
                                //toast.cancel();
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(context, msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * Toast文本显示
     *
     * @param context
     * @param msg
     * @param len
     */

    public static void showMessage(final Context context, final String msg, final int len) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (object) {
                            if (toast != null) {
                                //toast.cancel();
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(context, msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    /**
     * 关闭当前Toast
     */
    public static void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
