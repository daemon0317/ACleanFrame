package com.framework.android.plugin;

import android.app.Application;

import com.framework.android.exception.NotInitializeError;
import com.framework.android.utils.CheckUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * com.framework.android.plugin
 * Created by daemon on 2016/5/25 0025.
 * 说明：插件管理类
 */
public class PluginManager {
    private Application mContext;
    private Map<String,PluginBasicPanel> plugsMap;

    public PluginManager(Application context) {
        mContext = context;
    }

    public void init() {
        if (plugsMap != null && plugsMap.size() > 0){
            for (String key : plugsMap.keySet()){
                plugsMap.get(key).initializer(mContext);
            }
        }
    }

    public void addPlugin(String tag, PluginBasicPanel plug) {
        if (plugsMap == null){
            plugsMap = new HashMap<>();
        }
        plugsMap.put(tag,plug);
    }

    public PluginBasicPanel getPlugin(String tag) {
        if (plugsMap == null || CheckUtil.isEmpty(tag))
            throw new NotInitializeError("");
        return  plugsMap.get(tag);
    }
}
