package com.framework.android.plugin;

import android.app.Application;

/**
 * com.framework.android.plugin
 * Created by daemon on 2016/5/25 0025.
 * 说明：插件的注册和初始化
 */
public interface PluginBasicPanel {
    /**
     * 初始化
     */
    void initializer(Application application);
}
