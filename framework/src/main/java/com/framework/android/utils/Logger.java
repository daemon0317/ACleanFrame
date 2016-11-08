package com.framework.android.utils;

import com.framework.android.config.LogConfig;

/**
 * 作者： Daemon on 2016/9/10.
 * 说明：日志包装工具类
 */
public class Logger {

        /**
         * 是否开启调试，发布时请把DEBUG改为false
         */
        public static boolean debug = true;
        /**
         * 是否在客户端记录用户操作
         */
        public static boolean logFile = false;

        private static String logFilePath;

        public static void initLogger(LogConfig logConfig){
            if(null == logConfig){
                return;
            }
            debug = logConfig.isDebug();
            logFile = logConfig.isLogFile();
            logFilePath = logConfig.getLogFilePath();
        }

        public static void v(String tag, String msg) {
            if(debug) {
                android.util.Log.v(tag, msg);
            }
            if(logFile ){
                writeLog(tag, msg, null, "VERBOSE");
            }
        }
        public static void v(String tag, String msg, Throwable tr) {
            if(debug) {
                android.util.Log.v(tag, msg, tr);
            }
            if(logFile ){
                writeLog(tag, msg,tr, "VERBOSE");
            }
        }
        public static void d(String tag, String msg) {
            if(debug) {
                android.util.Log.d(tag, msg);
            }
            if(logFile ){
                writeLog(tag, msg, null, "debug");
            }
        }
        public static void d(String tag, String msg, Throwable tr) {
            if(debug) {
                android.util.Log.d(tag, msg, tr);
            }
            if(logFile ){
                writeLog(tag, msg, tr, "debug");
            }
        }
        public static void i(String tag, String msg) {
            if(debug) {
                android.util.Log.i(tag, msg);
            }
            if(logFile ){
                writeLog(tag, msg, null, "INFO");
            }
        }
        public static void i(String tag, String msg, Throwable tr) {
            if(debug) {
                android.util.Log.i(tag, msg, tr);
            }
            if(logFile ){
                writeLog(tag, msg, tr, "INFO");
            }
        }
        public static void w(String tag, String msg) {
            if(debug) {
                android.util.Log.w(tag, msg);
            }
            if(logFile ){
                writeLog(tag, msg, null, "WARN");
            }
        }
        public static void w(String tag, String msg, Throwable tr) {
            if(debug) {
                android.util.Log.w(tag, msg, tr);
            }
            if(logFile ){
                writeLog(tag, msg, tr, "WARN");
            }
        }
        public static void w(String tag, Throwable tr) {
            if(debug) {
                android.util.Log.w(tag, tr);
            }
            if(logFile){
                writeLog(tag, "", tr, "WARN");
            }
        }
        public static void e(String tag, String msg) {
            if(debug) {
                android.util.Log.e(tag, msg);
            }
            if(logFile ){
                writeLog(tag, msg, null, "ERROR");
            }
        }
        public static void e(String tag, String msg, Throwable tr) {
            if(debug) {
                android.util.Log.e(tag, msg, tr);
            }
            if(logFile ){
                writeLog(tag, msg, tr, "ERROR");
            }
        }
        public static void e(String tag, Throwable tr) {
            if(debug) {
                android.util.Log.e(tag, "", tr);
            }
            if(logFile ){
                writeLog(tag, "", tr, "ERROR");
            }
        }

        /**
         * 记录日志线程
         * @param tag
         * @param msg
         * @param tr
         * @param priority
         */
        private static void writeLog(String tag, String msg, Throwable tr, String priority){
            //TODO
//            ThreadPool.getInstances().start(new LogWriteFileAble(logFilePath,tag,msg,tr,priority));
        }

}
