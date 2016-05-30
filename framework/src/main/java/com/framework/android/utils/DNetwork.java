package com.framework.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * com.framework.android.util
 * Created by daemon on 2016/3/25 0025.
 * 说明：网络查询和网络状态
 */
public class DNetwork {
    public enum netType{
        FAST, SLOW, NoNO
    }
    public static boolean isConnect(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public static netType getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI ||
                        mNetworkInfo.getType() == ConnectivityManager.TYPE_VPN ||
                        mNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET ||
                        mNetworkInfo.getType() == ConnectivityManager.TYPE_WIMAX ){
                    return netType.FAST;
                }else if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE_DUN ||
                        mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE ){
                    return netType.SLOW;
                }else{
                    return netType.NoNO;
                }
            }
        }
        return netType.NoNO;
    }
}
