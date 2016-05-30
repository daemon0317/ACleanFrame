package com.framework.android.core;

import com.framework.android.annotation.ViewInjectorImpl;
import com.framework.android.annotation.ViewInjectorPort;
import com.framework.android.plugin.PluginBasicPanel;
import com.framework.android.plugin.PluginManager;

/**
 * com.framework.android.core
 * Created by daemon on 2016/5/25 0025.
 * 说明：AClean的Context对象
 */
public abstract class ACleanContext {

    public boolean isFrameView;

    public abstract PluginBasicPanel getPlugin(String key);

    public abstract PluginManager getPluginManager();

    public abstract void setViewInjector(ViewInjectorPort injector);

    public void register() {
        getPluginManager().init();//插件初始化
        ViewInjectorImpl.registerInstance();//View 注入初始化
    }
}
