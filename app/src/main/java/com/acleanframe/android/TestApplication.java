package com.acleanframe.android;

import com.acleanframe.android.volley.VolleyPanel;
import com.framework.android.base.BaseApplication;
import com.framework.android.plugin.PluginBasicPanel;
import com.framework.android.plugin.PluginManager;

/**
 * com.acleanframe.android
 * Created by daemon on 2016/5/25 0025.
 * 说明：测试框架的初始化，只需要继承 BaseApplication
 *
 * @see BaseApplication
 * @see com.framework.android.AClean 使用的接口
 *
 */
public class TestApplication extends BaseApplication {
    /**
     * 加入第三方插件
     * @param manager
     * @see PluginManager#addPlugin(String, PluginBasicPanel)
     */
    @Override
    protected void registerPlugin(PluginManager manager) {
        manager.addPlugin("volley",new VolleyPanel());
    }
}
