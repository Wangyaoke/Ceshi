package com.ansiyida.cgjl.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.ansiyida.cgjl.MyApplication;

import java.util.UUID;

/**
 * Created by chenyaoxiang on 2017/12/26.
 */
public class PhoneUtil {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9

------------------------------------------------
    13(老)号段：130、131、132、133、134、135、136、137、138、139
    14(新)号段：145、147
    15(新)号段：150、151、152、153、154、155、156、157、158、159
    17(新)号段：170、171、173、175、176、177、178
    18(3G)号段：180、181、182、183、184、185、186、187、188、189
    */
        ///String telRegex="^(0\\\\d{2,3}-\\\\d{7,8}(-\\\\d{3,5}){0,1})|(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8})$";  |
        String telRegex = "[1][3456789]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
    /**
     * 获得手机的机器标识码
     */
    @SuppressLint("MissingPermission")
    private String getPhoneUniqueId() {
        //调用android的api
//        /**
//         * IMEI(International Mobile Equipment Identity)是国际移动设备身份码的缩写，国际移动装备辨识码，
//         * 是由15位数字组成的"电子串号"，它与每台手机一一对应，而且该码是全世界唯一的。
//         * 每一只手机在组装完成后都将被赋予一个全球唯一的一组号码，
//         * 这个号码从生产到交付使用都将被制造生产的厂商所记录。
//         */
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String device_IMEI = telephonyManager.getDeviceId();
//        Log.i("tag", "device_IMEI---:" + device_IMEI );
        Context context=MyApplication.getmContext();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//        String uniqueId = deviceUuid.toString();
//        LogUtil.i("HttpServiceClient", "uniqueId---:" + uniqueId);
        return deviceUuid.toString();
    }
}
