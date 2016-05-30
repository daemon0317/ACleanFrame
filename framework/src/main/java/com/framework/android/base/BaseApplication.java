package com.framework.android.base;

import android.app.Application;

import com.framework.android.AClean;
import com.framework.android.core.ACleanContext;
import com.framework.android.core.ACleanContextWrapper;
import com.framework.android.plugin.PluginManager;

/**
 * com.framework.android.base
 * Created by daemon on 2016/5/25 0025.
 */
public abstract class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ACleanContext aClean = new ACleanContextWrapper(this);
        registerPlugin(aClean.getPluginManager());
        AClean.onCreate(aClean);

    }

    protected abstract void registerPlugin(PluginManager pluginManager);


}
