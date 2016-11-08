package com.acleanframe.android.volley;

import android.app.Application;

import com.framework.android.plugin.PluginBasicPanel;
import com.framework.android.utils.Logger;

/**
 * com.acleanframe.android.volley
 * Created by daemon on 2016/5/25 0025.
 * 说明：Volley框架的基本使用集合成一个面板
 */
public class VolleyPanel implements PluginBasicPanel {
    @Override
    public void initializer(Application application) {
        Logger.i("VolleyPanel","initializer succeed");
    }
}
