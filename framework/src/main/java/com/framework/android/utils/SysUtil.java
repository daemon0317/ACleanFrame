package com.framework.android.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.List;

import static com.framework.android.utils.FileUtil.TAG;

/**
 * Created by Daemon on 2016/11/8.
 */

public class SysUtil {
    /**
     * 判断GPS是否打开
     * @return
     */
    public static boolean isGPSEnabled(Context context){
        LocationManager locationMgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 判断当前应用程序处于前台还是后台
     * 需要添加权限: <uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static boolean isApplicationBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                Logger.d(TAG, "isBackground: " + true);
                return true;
            }
        }
        Logger.d(TAG, "isBackground: " + false);
        return false;
    }
    /**
     * 判断手机是否处理睡眠
     *
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        Logger.d(TAG, isSleeping ? "手机睡眠中.." : "手机未睡眠...");
        return isSleeping;
    }
    /**
     * 判断当前设备是否为手机
     *
     * @param context
     * @return
     * @author wangjie
     */
    public static boolean isPhone(Context context) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int type = telephony.getPhoneType();
        if (type == TelephonyManager.PHONE_TYPE_NONE) {
            Logger.i(TAG, "Current device is Tablet!");
            return false;
        } else {
            Logger.i(TAG, "Current device is phone!");
            return true;
        }
    }
    /**
     * 检查设备是否有相机
     */
    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    /**
     * 判断当前是否为主线程运行
     * @return
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 判断当前是否运行在后台
     * @return
     */
    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    /**
     * 检查权限授权
     * @param context
     * @param permission
     * @return
     */
    public static boolean isPermission(Context context, String permission){
        if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

}
