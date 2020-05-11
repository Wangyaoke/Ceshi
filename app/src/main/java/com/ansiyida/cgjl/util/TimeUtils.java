package com.ansiyida.cgjl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenyaoxiang on 2017/11/07.
 */
public class TimeUtils {
    //例：time:"2015-12-31"
    public static long timeBegin(String yyyy_MM_dd) {

        String time = yyyy_MM_dd + " 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            long todayBegin=format.parse(time).getTime()/1000+28800;
            long todayBegin = format.parse(time).getTime() / 1000;
            return todayBegin;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long timeEnd(String yyyy_MM_dd) {
        long timeBegin = timeBegin(yyyy_MM_dd);
        if (timeBegin != 0) {
            return timeBegin + (24 * 60 * 60);
        }
        return 0;

    }

    public static long timeNow() {
//        return System.currentTimeMillis()/1000+28800;
        return (long)(System.currentTimeMillis());
    }

    public static String getToday_yyyy_MM_dd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localTime = format.format(new Date());
        String date = localTime.substring(0, 10);
        return date;
    }
    public static int getSecond(String y_m_d_h_m){
        String time = y_m_d_h_m + ":00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            long todayBegin=format.parse(time).getTime()/1000+28800;
            int todayBegin = (int)(format.parse(time).getTime() / 1000);
            return todayBegin;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String getTomorrow_yyyy_MM_dd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localTime = format.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        String date = localTime.substring(0, 10);
        return date;
    }

    public static String getMonthBegin(int year, int Month) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long monthBegin = 0;
        try {
            monthBegin = sim.parse(year + "-" + Month + "-1 3:00:00").getTime() / 1000;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return monthBegin + "";
    }

    public static String getMonthEnd(int year, int Month) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long monthEnd = 0;
        try {
            if (Month == 12) {
                monthEnd = sim.parse((year + 1) + "-1-1 3:00:00").getTime() / 1000;
            } else {
                monthEnd = sim.parse(year + "-" + (Month + 1) + "-1 3:00:00").getTime() / 1000;

            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return monthEnd + "";
    }

    public static boolean isTodayOrNot(int time) {
        long timeBegin = timeBegin(getToday_yyyy_MM_dd());
        long timeEnd = timeEnd(getToday_yyyy_MM_dd());
        if (time >= timeBegin && time <= timeEnd) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间类型转换为时间戳
     * 类型： yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return 返回值
     */
    public static long timeToMillisecond(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long todayBegin = format.parse(time).getTime();
            return todayBegin;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将毫秒值转换时间串
     *
     * @param type 时间类型  "yyyy-MM-dd HH:mm:ss"
     * @param time 时间毫秒值
     * @return 返回值时间串
     */
    public static String getTime(String type, long time) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date d1 = new Date(time);
//        LogUtil.i("tag","format.format(d1)---:" + format.format(d1));
        return format.format(d1);
    }
    public static String mmtime_Time(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(Long.parseLong(time));
//        LogUtil.i("tag","format.format(d1)---:" + format.format(d1));
        return format.format(d1);
    }


    /**
     * 获取昨天的这个时间的时间串
     *
     * @return 返回值
     */
    public String getYesterdayTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(cal.getTimeInMillis() - 24 * 60 * 60 * 1000);
        return format.format(d1);
    }

    /**
     * 将时间字符串转换成毫秒值
     *
     * @param time 参数
     * @return 返回值
     */
    public static long dateToStamp(String time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date!=null){

            return date.getTime();
        }else {
            return 0;
        }
    }

    public static String getMm_ss(int useTime){
        String time=null;
        int min = useTime / 60;
        int sec = useTime % 60;
        if (min == 0) {
            time=sec + " 秒";

        } else {

            time=min + " 分 " + sec + " 秒";
        }
        return time;
    }

}
