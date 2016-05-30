/*
    ShengDao Android Client, NLog
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.framework.android.utils;

import android.util.Log;

import com.framework.android.cose.Constants;
import com.framework.android.cose.thread.Runtask;
import com.framework.android.cose.thread.ThreadPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * [A brief description]
 *	
 * @author devin.hu
 * @version 1.0
 * @date 2013-9-17
 *
 **/
public class DLog {

	private static final String LOG_FORMAT = "%1$s\n%2$s";
	private static boolean debug = Constants.IS_DEBUG;
	private static boolean logFile = Constants.IS_LOGFILE;
	private static String logFilePath ;
	public static void init(String path){
		logFilePath = path;
	}
	public static void d(String tag, Object... args) {
		log(Log.DEBUG, null, tag, args);
	}

	public static void i(String tag, Object... args) {
		log(Log.INFO, null, tag, args);
	}

	public static void w(String tag, Object... args) {
		log(Log.WARN, null, tag, args);
	}

	public static void e(Throwable ex) {
		log(Log.ERROR, ex, null);
	}

	public static void e(String tag, Object... args) {
		log(Log.ERROR, null, tag, args);
	}

	public static void e(Throwable ex, String tag, Object... args) {
		log(Log.ERROR, ex, tag, args);
	}

	private static void log(int priority, Throwable ex, String tag, Object... args) {
		String log = "";
		if (ex == null) {
			if(args != null && args.length > 0){
				for(Object obj : args){
					log += String.valueOf(obj);
				}
			}
		} else {
			String logMessage = ex.getMessage();
			String logBody = Log.getStackTraceString(ex);
			log = String.format(LOG_FORMAT, logMessage, logBody);
		}
		if(debug) {
			Log.println(priority, tag, log);
		}
		if(logFile){
			writeLog(tag, log, null, "VERBOSE");
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

		ThreadPool.go(new Runtask<Void, Void>(tag, msg, tr, priority) {
			@Override
			public Void runInBackground() {
				synchronized (DLog.class) {
					String tag = (String) objs[0];
					String msg = (String) objs[1];
					Throwable tr = (Throwable) objs[2];
					String priority = (String) objs[3];

					if (!logFilePath.endsWith(File.separator)) {
						logFilePath = logFilePath + File.separator;
					}

					String filename = logFilePath
							+ DDateTime.formatDateTime(System.currentTimeMillis())
							+ ".log";
					File logFile = new File(filename);

					OutputStream os = null;
					try {
						if (!logFile.exists()) {
							logFile.createNewFile();
						}

						os = new FileOutputStream(logFile, true);

						String formatMsg = DDateTime.formatDateTime(System.currentTimeMillis()) + "\r\n[" + priority + "][" + tag + "]: \r\n"
								+ "User Message: " + msg + "\r\n"
								+ (null == tr ? "" :

								"Throwable Message: " + tr.getMessage() + "\r\n"
										+ "Throwable StackTrace: " + (tr.getStackTrace())
						)
								+ "\r\n";
						os.write(formatMsg.getBytes("utf-8"));
						os.flush();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						if (null != os) {
							try {
								os.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}


				}
				return null;
			}
		});
	}
}
