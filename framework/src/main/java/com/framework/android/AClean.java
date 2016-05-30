package com.framework.android;

import android.content.Context;

import com.framework.android.annotation.ViewInjectorImpl;
import com.framework.android.annotation.ViewInjectorPort;
import com.framework.android.base.BaseFrameView;
import com.framework.android.core.ACleanContext;
import com.framework.android.plugin.PluginBasicPanel;

/**
 * com.framework.android
 * Created by daemon on 2016/5/25 0025.
 * 说明：框架集中的操作文件
 */
public class AClean {
    private static volatile AClean aClean;
    private ACleanContext mContext;
    private ViewInjectorPort injector;

    public AClean(ACleanContext context) {
        mContext = context;
    }

    public static void onCreate(ACleanContext context) {
        if (aClean == null) {
            synchronized (AClean.class) {
                if (aClean == null) {
                    aClean = new AClean(context);
                    context.register();
                }
            }
        }
    }
    public static PluginBasicPanel getPlugin(String key){
        return aClean.mContext.getPlugin(key);
    }

    public static void initViewInjector(ViewInjectorImpl injector) {
        aClean.mContext.setViewInjector(injector);
    }

    public static ACleanContext getContext() {
        return aClean.mContext;
    }

    public static BaseFrameView getFrameView(Context context) {
        return null;
    }
}
