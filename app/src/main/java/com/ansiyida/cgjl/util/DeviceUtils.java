package com.ansiyida.cgjl.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.umeng.socialize.sina.helper.MD5;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ansiyida on 2018/3/12.
 */
public class DeviceUtils {
    public static String getUniqueId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }


    private static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }
    public static String getLocalIPAddress()
   {
                try
              {
                        for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface.getNetworkInterfaces();
                             mEnumeration.hasMoreElements();)
                        {
                              NetworkInterface intf = mEnumeration.nextElement();

                               for (Enumeration<InetAddress> enumIPAddr = intf.getInetAddresses(); enumIPAddr.hasMoreElements();)
                                  {
                                      InetAddress inetAddress = enumIPAddr.nextElement();
                                     //如果不是回环地址
                                      if (!inetAddress.isLoopbackAddress())
                                        {
                                         //直接返回本地IP地址
                                             return inetAddress.getHostAddress().toString();
                                         }
                               }
              }
       }
          catch (SocketException ex)
          {
             Log.e("Error", ex.toString());
     }
               return "192.168.1.1";
         }
    /**
     * 获取app包名
     */
    public static String getAppPackName(Context context) {
        try {
            String pkName = context.getApplicationContext().getPackageName();
            return pkName;
        } catch (Exception e) {
        }
        return null;
    }
    public static long getTimeSteamp(){
        return System.currentTimeMillis()/1000;
    }
    /**
     * 获取随机数
     */
    public static String gennocestr() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.replaceAll( "\\-","").toUpperCase();
    }
    /**
     * 获取app versionName
     */
    public static String getAppVersionName(Context context) {
        try {
            String pkName = context.getApplicationContext().getPackageName();
            String versionName = context.getApplicationContext().getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取app versionCode
     */
    public static String getAppVersionCode(Context context) {
        try {
            String pkName = context.getApplicationContext().getPackageName();
            int versionCode = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return versionCode + "";
        } catch (Exception e) {
        }
        return null;
    }
}
