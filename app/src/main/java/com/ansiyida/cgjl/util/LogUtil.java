package com.ansiyida.cgjl.util;

import android.util.Log;

/**
 * Created by chenyaoxiang on 2017/11/07.
 * 用法：
 * // 直接使用Log
 * LogUtil.d(TAG, "ddddddd");
 * LogUtil.e(TAG, "eeeeeee");
 * LogUtil.i(TAG ,"iiiiiii");
 * LogUtil.v(TAG, "vvvvvvv");
 * LogUtil.w(TAG, "wwwwwww");
 * <p>
 * //不需要再在类中定义TAG，打印类名,方法名,行号等.并定位行
 * LogUtil.d1("ddddddd");
 * LogUtil.e1("eeeeeee");
 * LogUtil.i1("iiiiiii");
 * LogUtil.v1("vvvvvvv");
 * LogUtil.w1("wwwwwww");
 */

public class LogUtil {
//    private static boolean LOGV = true;
//    private static boolean LOGD = true;
//    private static boolean LOGI = true;
//    private static boolean LOGW = true;
//    private static boolean LOGE = true;

    private static boolean LOGV = false;
    private static boolean LOGD = false;
    private static boolean LOGI = false;
    private static boolean LOGW = false;
    private static boolean LOGE = false;

    // 直接使用Log
    public static void v(String tag, String mess) {
        if (LOGV) {
            Log.v(tag, mess);
        }
    }

    public static void d(String tag, String mess) {
        if (LOGD) {
            Log.d(tag, mess);
        }
    }

    public static void i(String tag, String mess) {
        if (LOGI) {
            Log.i(tag, mess);
        }
    }

    public static void w(String tag, String mess) {
        if (LOGW) {
            Log.w(tag, mess);
        }
    }

    public static void e(String tag, String mess) {
        if (LOGE) {
            Log.e(tag, mess);
        }
    }

    //不需要再在类中定义TAG，打印类名,方法名,行号等.并定位行
    public static void v1(String mess) {
        if (LOGV) {
            Log.v(getTag(), getMsgFormat(mess));
        }
    }

    public static void d1(String mess) {
        if (LOGD) {
            Log.d(getTag(), getMsgFormat(mess));
        }
    }

    public static void i1(String mess) {
        if (LOGI) {
            Log.i(getTag(), getMsgFormat(mess));
        }
    }

    public static void w1(String mess) {
        if (LOGW) {
            Log.w(getTag(), getMsgFormat(mess));
        }
    }

    public static void e1(String mess) {
        if (LOGE) {
            Log.e(getTag(), getMsgFormat(mess));
        }
    }


    /**
     * 获取到调用者的类名
     *
     * @return 调用者的类名
     */
    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行
     *
     * @return
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(LogUtil.class.getName())) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }

    /**
     * 输出格式定义
     *
     * @param msg
     * @return
     */
    private static String getMsgFormat(String msg) {
        return msg + " ;" + getFunctionName();
    }


}
