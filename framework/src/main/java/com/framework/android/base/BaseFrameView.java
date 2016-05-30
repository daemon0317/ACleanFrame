package com.framework.android.base;

import android.view.View;

/**
 * com.framework.android.base
 * Created by daemon on 2016/5/27 0027.
 * 说明：
 */
public class BaseFrameView extends BaseView{
    @Override
    View get() {
        return null;
    }

    @Override
    void showMessage(String msg) {
    }

    public void hideMessage() {

    }

    public void showError(String err, View.OnClickListener listener) {

    }

    public void hideError() {

    }

    public void showLoading(String str) {

    }

    public void hideLoading() {

    }
}
