package com.framework.android.core;

/**
 * com.chinatown.mylibrary.core
 * Created by daemon on 2016/2/26 0026.
 * 说明：配置
 */
public class Constants {
    //file
    public static final String APP_ROOT_DIRECTORY_NAME = "/ChinaTown";
    public static final String IMAGE = "image";
    public static final String CACHE = "cache";
    public static final String DATA = "data";
    public static final String TEMP = "temp";
    public static final String UPDATE = "update";
    public static final String LOGFILE = "log";
    public static final String DOWNLOAD = "download";

    public static final String DB_NAME = "china_town";
    public static final int DB_VERSION = 1;


    //BUG处理
    public static boolean IS_DEBUG = true;
    public static final boolean IS_LOGFILE = false;
    public static final String CRASH_MSG = "程序开了小差！对不起了！";

    public static final String SHARE_NAME = "SHARE_DATA";

    public static final String THEME = "theme";
    public static final String LANG = "lang";

    public static final String  DOMAIN = "http://124.232.137.177:8080/mobile/index.php";

    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;
}
