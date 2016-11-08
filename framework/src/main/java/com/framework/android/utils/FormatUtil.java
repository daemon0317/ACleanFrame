package com.framework.android.utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Daemon on 2016/11/8.
 */

public class FormatUtil {
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
    private static HashMap<String, String> str2map(String mapStr) {
        if (CheckUtil.isEmpty(mapStr)) return  null;
        HashMap<String, String> map = null;
        try {
            JSONObject object = new JSONObject(mapStr);
            if (object != null && object.length() != 0){
                Iterator<String> kes = object.keys();
                map = new HashMap<>();
                while (kes.hasNext()){
                    String key = kes.next();
                    map.put(key,object.getString(key));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 格式化价格
     * @param str 价格字符串
     * @return ￥0.00
     */
    public static String formatPrice(String str) {
        double i = Double.parseDouble(str);
        return "￥" + new DecimalFormat("#0.00").format(i);
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
}
