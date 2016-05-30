package com.framework.android.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.framework.android.core.Constants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明：
 */
public class DFile {

    public static final int SIZE_TYPE_B = 1;// 获取文件大小单位为B的double值
    public static final int SIZE_TYPE_KB = 2;// 获取文件大小单位为KB的double值
    public static final int SIZE_TYPE_MB = 3;// 获取文件大小单位为MB的double值
    public static final int SIZE_TYPE_GB = 4;// 获取文件大小单位为GB的double值

    // 得到当前外部存储设备的目录
    public static String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    private String system_cache;
    private Context mContext;
    // 获取扩展SD卡设备状态
    private String SDState = Environment.getExternalStorageState();

    public DFile(Context context) {
        mContext = context;
        system_cache = context.getCacheDir().toString();
    }

    /**
     * 判断是否已经安装SD卡
     *
     * @return boolean 返回类型
     * @Title: isSDCardExist
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * 在SD卡上创建目录
     *
     * @param dir 目录路径/名称
     * @return
     */
    public File createSDDir(String dir) {
        File dirFile = new File(SDCardRoot + dir + File.separator);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    /**
     * 创建文件
     * @param dir 文件夹路径
     * @param fileName 文件名
     * @return
     */
    public File createFile(String dir, String fileName) {
        File dirFile = new File(dir + File.separator + fileName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    /**
     * 获取APP文件夹
     * @param str 文件夹名称 没有会自动创建一个
     * @see Constants#IMAGE,Constants#CACHE,Constants#DATA,Constants#TEMP,Constants#UPDATE,Constants#DOWNLOAD
     * @return File
     */
    public File getFiles(String str) {
        File file = null;
        if (isSDCardExist()) {
            file = createFile(createSDDir(Constants.APP_ROOT_DIRECTORY_NAME).toString(), str);
        } else {
            file = createFile(createCacheDir(Constants.APP_ROOT_DIRECTORY_NAME).toString(), str);
        }
        return file;
    }
    /**
     * 在缓存上创建目录
     *
     * @return File 返回类型
     */
    public File createCacheDir(String dir) {
        File dirFile = new File(system_cache + dir + File.separator);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    //写数据到SD中的文件
    public boolean writeFileSdcardFile(String dir, String filename, String str) throws IOException {
        boolean isSucceed = false;
        try{
            int size = str.getBytes().length;
            // 拥有可读可写权限，并且有足够的容量
            if (SDState.equals(Environment.MEDIA_MOUNTED) && size < getSDAvailableSize(SIZE_TYPE_B)){
                createSDDir(dir);
                File file = createFile(dir, filename);
                FileOutputStream out = new FileOutputStream(file);
                byte [] bytes = str.getBytes();
                out.write(bytes);
                out.flush();
                isSucceed = true;
                out.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return isSucceed;
    }
    //写数据到SD中的文件
    public boolean writeFileSdcardFile(String dir, String filename, InputStream input){
        File file = null;
        OutputStream output = null;
        boolean isSucceed = false;
        try {
            int size = input.available();
            // 拥有可读可写权限，并且有足够的容量
            if (SDState.equals(Environment.MEDIA_MOUNTED) && size < getSDAvailableSize(SIZE_TYPE_B)){
                createSDDir(dir);
                file = createFile(dir, filename);
                output = new BufferedOutputStream(new FileOutputStream(file));
                byte buffer[] = new byte[4 * 1024];
                int temp;
                while ((temp = input.read(buffer)) != -1) {
                    output.write(buffer, 0, temp);
                }
                output.flush();
                isSucceed = true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSucceed;
    }
    //读SD中的文件
    public byte[] readFileSdcardFile(String dir, String filename){
        String res="";
        File file = new File(dir,filename);

        return readFileSdcardFile(file);
    }
    /**
     * 获取SD卡的剩余容量,单位是Byte
     * @param sizeType 获取大小的类型
     * @see #SIZE_TYPE_B#SIZE_TYPE_KB#SIZE_TYPE_MB#SIZE_TYPE_GB;
     * @return
     */
    public double getSDAvailableSize(int sizeType) {
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            // 取得sdcard文件路径
            File pathFile = Environment
                    .getExternalStorageDirectory();
            android.os.StatFs statFs = new android.os.StatFs(pathFile.getPath());
            // 获取SDCard上每个block的SIZE
            long nBlocSize = statFs.getBlockSize();
            // 获取可供程序使用的Block的数量
            long nAvailaBlock = statFs.getAvailableBlocks();
            // 计算 SDCard 剩余大小Byte
            long nSDFreeSize = nAvailaBlock * nBlocSize;
            return FormatFileSize(nSDFreeSize, sizeType);
        }
        return 0;
    }
    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型
     * @see #SIZE_TYPE_B#SIZE_TYPE_KB#SIZE_TYPE_MB#SIZE_TYPE_GB;
     * @return double值的大小
     */
    public double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormatFileSize(blockSize, sizeType);
    }
    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    private long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        return size;
    }
    /**
     * 获取指定文件夹
     *
     * @param file
     * @return
     * @throws Exception
     */
    private long getFileSizes(File file) throws Exception {
        long size = 0;
        File files[] = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getFileSizes(files[i]);
            } else {
                size = size + getFileSize(files[i]);
            }
        }
        return size;
    }
    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private double FormatFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#0.00");
        double fileSize = 0;
        switch (sizeType) {
            case SIZE_TYPE_B:
                fileSize = Double.valueOf(df.format((double) fileS));
                break;
            case SIZE_TYPE_KB:
                fileSize = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZE_TYPE_MB:
                fileSize = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZE_TYPE_GB:
                fileSize = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
        }
        return fileSize;
    }

    /**
     *
     * @param uri  Uri toString
     * @return
     */
    public byte[] readFileSdcardFile(Uri uri) {
        String path = getRealPathFromURI(uri);
        if (DCheck.isEmpty(path)) return null;
        File file = new File(path);
        return readFileSdcardFile(file);
    }

    private byte[] readFileSdcardFile(File file) {
//        String res = null;
        if (!file.exists()) {
            return null;
        }
        byte [] buffer = null;
        FileInputStream fin = null;
        try{
            fin = new FileInputStream(file);
            int length = fin.available();
            buffer = new byte[length];
            fin.read(buffer);
//            res = new String(buffer, Charset.forName("UTF-8"));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (fin != null){
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = mContext.getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    //    String Name = File.getName();  //获得文件或文件夹的名称：
//    String parentPath = File.getParent();  //获得文件或文件夹的父目录
//    String path = File.getAbsoultePath();//绝对路经
//    String path = File.getPath();//相对路经
//    File.createNewFile();//建立文件
//    File.mkDir(); //建立文件夹
//    File.isDirectory(); //判断是文件或文件夹
//    File[] files = File.listFiles();  //列出文件夹下的所有文件和文件夹名
//    File.renameTo(dest);  //修改文件夹和文件名
//    File.delete();  //删除文件夹或文件
}
