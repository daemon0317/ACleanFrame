package com.framework.android.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明：
 */
public class DCheck {
    /**
     * 检验是否是手机号码
     *
     * @Title: isMobileNO
     * @param phoneNum 手机号码
     * @return 设定文件
     * @return boolean 返回类型
     */
    public static boolean isPhone(String phoneNum) {
        String phone = "(1[0-9][0-9]{1}){1}\\d{8}";
        Pattern p = Pattern.compile(phone);
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0)
            return true;
        else
            return false;
    }
    /**
     * 判断数组不能为空
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list){
        if(list == null || list.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * 判断字符串是否全是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }
    /**
     * 验证是否是邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true,否则返回false
     */
    public static boolean isChinese(String str){
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否是html
     * @param str
     * @return
     */
    public static boolean isHtml(String str) {
        if (isEmpty(str)) return false;
        Pattern pattern = Pattern.compile("^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$");
        return pattern.matcher(str).matches();
    }

    public static boolean isContains(String cityName, String locationAddr) {
        if (isEmpty(locationAddr)) return false;
        if (isEmpty(cityName)) return false;
        return cityName.contains(locationAddr);
    }
}
