package com.framework.android.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framework.android.AClean;
import com.framework.android.core.ACleanContext;
import com.framework.android.exception.NotInitializeError;

/**
 * com.framework.android.base
 * Created by daemon on 2016/5/27 0027.
 * 说明：基本activity框架
 */
public class BaseActivity extends AppCompatActivity {

    private ACleanContext aClean;
    private BaseFrameView frameView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        aClean = AClean.getContext();
        frameView = onFrameView(this);
        if (frameView != null){
            setContentView(frameView.get());
        }else if (aClean.isFrameView){
            frameView = AClean.getFrameView(this);
            setContentView(frameView.get());
        }else{
            throw new NotInitializeError("extends BaseActivity need to define a FrameView");
        }
    }
    protected BaseFrameView onFrameView(Context context){
        return null;
    }
    protected void showMessage(String msg){
        frameView.showMessage(msg);
    }
    protected void hideMessage(){
        frameView.hideMessage();
    }
    protected void showError(String err,View.OnClickListener listener){
        frameView.showError(err, listener);
    }
    protected void hideError(){
        frameView.hideError();
    }
    protected void showLoading(String str){
        frameView.showLoading(str);
    }
    protected void hideLoading(){
        frameView.hideLoading();
    }
}
