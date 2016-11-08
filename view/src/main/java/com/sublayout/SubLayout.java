package com.sublayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: wangjie
 * Date: 14-4-24
 * Time: 下午6:12
 * To change this template use File | Settings | File Templates.
 */
public class SubLayout implements ISubLayout ,OnActivityLifeCycleFullListener
//        , ABActivityViewer,
{
    public Context context;
    public View layout;
    private boolean inited;
//    private ABBasePresenter presenter;

    public SubLayout(Context context) {
        this(context, false);
    }

    public SubLayout(Context context, boolean autoBindActivityLifeCycle) {
        this.context = context;
//        if (autoBindActivityLifeCycle && context instanceof ABActivityCommon) {
//            ((ABActivityCommon) context).getBaseActivityManager().registerOnActivityLifeCycleListeners(this);
//        }
    }

    public void initLayout() {
        inited = true;
    }

    public void setInited(boolean isInited) {
        inited = isInited;
    }

    public void setContentView(int resLayout) {
        layout = LayoutInflater.from(context).inflate(resLayout, null);
    }

    public void setContentView(View view) {
        layout = view;
    }

    public View getLayout() {
        return layout;
    }

    public boolean isInited() {
        return inited;
    }


    public View findViewById(int resId) {
        return layout.findViewById(resId);
    }


    /**
     * ******************* ABActivityViewer impl ********************
     */

//    @Override
//    public void showToastMessage(String msg) {
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void showInfoDialog(String message) {
//        showInfoDialog(null, message);
//    }
//
//    @Override
//    public void showInfoDialog(String title, String message) {
//        new AlertDialog.Builder(context)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("OK", null)
//                .show();
//    }
//
//    @Override
//    public void showLoadingDialog(String message) {
//
//    }
//
//    @Override
//    public void cancelLoadingDialog() {
//
//    }
//
//    @Override
//    public void showInfoDialog(String title, String message, String okButtonText) {
//        new AlertDialog.Builder(context)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(okButtonText, null)
//                .show();
//    }
//
//
    /**
     * 绑定Activity的生命周期（需要调用ctivity.getBaseActivityManager().registerOnActivityLifeCycleListeners(this);）
     */
    @Override
    public void onActivityStartCallBack() {
    }

    @Override
    public void onActivityRestartCallBack() {
    }

    @Override
    public void onActivityStopCallBack() {
    }

    @Override
    public void onActivitySaveInstanceStateCallBack(Bundle outState) {
    }

    @Override
    public void onActivityRestoreInstanceStateCallBack(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreateCallback(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityResumeCallback() {
    }

    @Override
    public void onActivityPauseCallback() {
    }

    @Override
    public void onActivityDestroyCallback() {
//        if (context instanceof ABActivityCommon) {
//            ((ABActivityCommon) context).getBaseActivityManager().unregisterOnActivityStopListener(this);
//        }
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
//
//
//    @Override
//    public void registerPresenter(ABBasePresenter presenter) {
//        this.presenter = presenter;
//    }
//
//    @Override
//    public void closeAllTask() {
//        presenter.closeAllTask();
//    }
}
