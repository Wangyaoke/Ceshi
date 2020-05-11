package com.ansiyida.cgjl.http;

import android.os.Environment;
import com.ansiyida.cgjl.R;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Created by ansiyida on 2017/11/8.
 */
public class Constant {
    //---------------------------------------------------------1.网络部分-----------------------------------------------------------------------
    //public static final String URL = "http://47.74.147.41:8081/";         //测试
    public static final String URL = "https://cg.calcnext.com/";          //正式
    //public static final String URL = "http://192.168.32.140:8111/";
    //---------------------------------------------------------2.储存部分-----------------------------------------------------------------------
    
    private static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private static String getData_PATH() {
        String path = "";
        if (!externalMemoryAvailable()) {
            path = Environment.getDataDirectory() + "/data/com.ansiyida.mrqb";
        } else {
            path = Environment.getExternalStorageDirectory() + "/Android/data/com.ansiyida.mrqb";
        }
        return path;
    }

    public static String DbAbsolutePath =getData_PATH();
    //语音文件存储路径
    public static String dbPath = DbAbsolutePath + "/database/";
    public static String picPath = DbAbsolutePath + "/picture/";
    public static String picPath_cp = DbAbsolutePath + "/picturecp/";
    public static String cachePath = DbAbsolutePath + "/cache/";
    public static int default_touXiang= R.mipmap.default_touxiang;
    public static int default_yanzhengma= R.mipmap.common_loading_circle;
    public static final List<String> fulllist = new ArrayList<>();
    public static final List<String> titlelist = new ArrayList<>();
    public static String searchType= "title";
    public static String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static Pattern Patternphone = Pattern.compile("^(0\\d{2,3}-\\d{7,8}(-\\d{3,5}){0,1})|((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}");
}