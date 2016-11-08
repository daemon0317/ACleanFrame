package com.framework.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 作者： Daemon on 2016/9/10.
 * 说明：
 */
public class PrefsUtil {

    private static PrefsUtil prefsUtil;
    private Map<String,SharedPreferencesUtil> maps;
    public synchronized static PrefsUtil getInstance(){
        if (prefsUtil == null){
            prefsUtil = new PrefsUtil();
        }
        return prefsUtil;
    }
    public SharedPreferencesUtil get(Context context,String name) {
        if (prefsUtil.maps.get(name) == null){
            SharedPreferencesUtil util = new SharedPreferencesUtil();
            util.prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            util.editor = util.prefs.edit();
            prefsUtil.maps.put(name,util);
        }
        return prefsUtil.maps.get(name);
    }

    private PrefsUtil() {
        maps = new HashMap<>();
    }
    public static class SharedPreferencesUtil {
         SharedPreferences prefs;
         SharedPreferences.Editor editor;
        public boolean getBoolean(String key, boolean defaultVal){
            return this.prefs.getBoolean(key, defaultVal);
        }
        public boolean getBoolean(String key){
            return this.prefs.getBoolean(key, false);
        }


        public String getString(String key, String defaultVal){
            return this.prefs.getString(key, defaultVal);
        }
        public String getString(String key){
            return this.prefs.getString(key, null);
        }

        public int getInt(String key, int defaultVal){
            return this.prefs.getInt(key, defaultVal);
        }
        public int getInt(String key){
            return this.prefs.getInt(key, 0);
        }


        public float getFloat(String key, float defaultVal){
            return this.prefs.getFloat(key, defaultVal);
        }
        public float getFloat(String key){
            return this.prefs.getFloat(key, 0f);
        }

        public long getLong(String key, long defaultVal){
            return this.prefs.getLong(key, defaultVal);
        }
        public long getLong(String key){
            return this.prefs.getLong(key, 0l);
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public Set<String> getStringSet(String key, Set<String> defaultVal){
            return this.prefs.getStringSet(key, defaultVal);
        }
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public Set<String> getStringSet(String key){
            return this.prefs.getStringSet(key, null);
        }

        public Map<String, ?> getAll(){
            return this.prefs.getAll();
        }


        public SharedPreferencesUtil putString(String key, String value){
            editor.putString(key, value);
//        editor.commit();
            return this;
        }

        public SharedPreferencesUtil putInt(String key, int value){
            editor.putInt(key, value);
//        editor.commit();
            return this;
        }

        public SharedPreferencesUtil putFloat(String key, float value){
            editor.putFloat(key, value);
//        editor.commit();
            return this;
        }

        public SharedPreferencesUtil putLong(String key, long value){
            editor.putLong(key, value);
//        editor.commit();
            return this;
        }

        public SharedPreferencesUtil putBoolean(String key, boolean value){
            editor.putBoolean(key, value);
//        editor.commit();
            return this;
        }
        public void commit(){
            editor.commit();
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public SharedPreferencesUtil putStringSet(String key, Set<String> value){
            editor.putStringSet(key, value);
            editor.commit();
            return this;
        }
    }


}
