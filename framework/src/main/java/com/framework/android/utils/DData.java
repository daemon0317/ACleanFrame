package com.framework.android.utils;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明：数据转换处理
 */
public class DData {
    private static String MONEY = "¥";

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static  int str2Int(String str) {
        if (TextUtils.isEmpty(str)) return 0;
        int result = 0;
        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 格式化价格
     * @param str 价格字符串
     * @return ￥0.00
     */
    public static String formatPrice(String str) {
        double i = Double.parseDouble(str);
        return MONEY + new DecimalFormat("#0.00").format(i);
    }

    /**
     * 字符串转换成double类型
     * @param str
     * @return
     */
    public static double str2double(String str) {
        if (TextUtils.isEmpty(str)) return  0;
        double result = 0;
        try {
            result = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static long str2long(String str){
        if (TextUtils.isEmpty(str)) return 0;
        long lg = 0;
        try {
            lg = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return lg;
    }
    private static String map2str(HashMap<String, String> map) {
        if (map == null || map.size() == 0) return  "";
        JSONObject object = new JSONObject(map);
        return object.toString();
    }

}
