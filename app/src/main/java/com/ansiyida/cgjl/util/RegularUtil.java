package com.ansiyida.cgjl.util;

import java.util.regex.Pattern;
/*
* 正则验证工具类
* */
public class RegularUtil {
    static Pattern PatternEMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    static Pattern Patternphone = Pattern.compile("^(0\\d{2,3}-\\d{7,8}(-\\d{3,5}){0,1})|((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}");
    //手机号验证
    public static boolean RegularPhone(String phone){
        if(Patternphone.matcher(phone).matches()){
            return true;
        }else{
            return false;
        }
    }

    //邮箱验证
    public static boolean RegularEmail(String email){
        if(PatternEMAIL.matcher(email).matches()){
            return true;
        }else{
            return false;
        }
    }


}
