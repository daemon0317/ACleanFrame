package com.framework.android.core;

import android.app.Application;

import com.framework.android.annotation.ViewInjectorPort;
import com.framework.android.plugin.PluginBasicPanel;
import com.framework.android.plugin.PluginManager;

/**
 * com.framework.android.core
 * Created by daemon on 2016/5/25 0025.
 * 说明：
 */
public class ACleanContextWrapper extends ACleanContext{

    private Application context;
    private PluginManager pluginManager;
    private ViewInjectorPort injector;

    public ACleanContextWrapper(Application application) {
        this.context = application;
        pluginManager = new PluginManager(application);
        isFrameView = false;
    }

    @Override
    public PluginBasicPanel getPlugin(String key) {
        return pluginManager.getPlugin(key);
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public void setViewInjector(ViewInjectorPort injector) {
        this.injector = injector;
    }
}
