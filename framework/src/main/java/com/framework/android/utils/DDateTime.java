package com.framework.android.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明：日期转换
 */
public class DDateTime {
    private static SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final long DAY_MILLIS = 1000 * 60 * 60 * 24;
    private static final long HOUR_MILLIS = 1000 * 60 * 60;
    private static final long MINUTE_MILLIS = 1000 * 60;
    /**
     *
     * @param time
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(long time) {
        if (0 == time) {
            return null;
        }
        return mDateFormat.format(new Date(time));
    }

    /**
     *
     * @param time 时间戳字符串
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDate(String time, String pattern) {
        if (TextUtils.isEmpty(time)) return "";
        long dateTime = DData.str2long(time);
        if (dateTime == 0 ) return "";
        if (TextUtils.isEmpty(pattern)) pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String str = null;
        try {
            str = formatter.format(dateTime);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 得到一个时间距离当前时间的天数
     * @param time 时间戳字符串
     * @param def 默认显示
     * @return xx天xx小时
     */
    public static String getDiffToNow(String time ,String def) {
        if (TextUtils.isEmpty(time)) return def;
        long dateTime =  DData.str2long(time);
        if (dateTime == 0 ) return def;
        long millis = dateTime - System.currentTimeMillis();
        if (millis <= 0) return def;
        StringBuffer sb = new StringBuffer();
        if (millis >= DAY_MILLIS){
            int days = (int) Math.floor(millis/DAY_MILLIS);
            millis = millis - days * DAY_MILLIS;
            sb.append(days).append("天");
        }
        if (millis >= HOUR_MILLIS){
            int hours = (int) Math.floor(millis/HOUR_MILLIS);
            millis = millis - hours * HOUR_MILLIS;
            sb.append(hours).append("小时");
        }else if (sb.length() > 0){
            sb.append("0").append("小时");
        }
        return sb.toString();
    }
}
