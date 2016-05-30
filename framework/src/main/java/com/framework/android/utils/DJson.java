package com.framework.android.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.framework.android.util
 * Created by daemon on 2016/5/23 0023.
 * 说明：
 */
public class DJson {

    private static Gson gson = new Gson();

    public static <T> T getObject(String str, Class<T> cls) {
        T obj = null;
        try {
            obj = gson.fromJson(str,cls);
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }
        return obj;
    }
    public static <T> ArrayList<T> getArray(String str, Class<T> cls) {
        ArrayList<T> obj = null;
        try {
            obj = gson.fromJson(str,new TypeToken<ArrayList<T>>(){}.getType());
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }
        return obj;
    }

    /**
     *
     * @param str
     * @param cls  exp new TypeToken<HashMap<String,String>>(){}.getType()
     * @return
     */
    public static  HashMap getMap(String str, Type cls) {
        HashMap obj = null;
        try {
            obj = gson.fromJson(str, cls);
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }
        return obj;
    }
    public static String toString(Object obj) {
        if (obj == null) return null;
        String str = gson.toJson(obj);
        return str;
    }

    public static <E> String toString(List<E> list) {
        if (list == null || list.size() == 0) return null;
        String str = gson.toJson(list, new TypeToken<ArrayList<E>>(){}.getType());
        return str;
    }

    /**
     *
     * @param map
     * @param cls exp new TypeToken<HashMap<String,String>>(){}.getType()
     * @return
     */
    public static String toString(Class<? extends Map> map, Type cls) {
        if (map == null) return null;
        String str = gson.toJson(map, cls);
        return str;
    }
}
