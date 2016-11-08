package com.framework.android.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import java.io.File;

/**
 * Created by Administrator on 2016/9/18.
 */
public class IntentUtil {
    /**
     * 拨打电话，直接拨打
     *
     * @param context
     * @param phoneNumber
     */
    public static void call(Context context, String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 拨打电话，现实拨号界面
     *
     * @param context
     * @param phoneNumber
     */
    public static void callDial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 发送短信息，跳转到发送短信界面
     *
     * @param context
     * @param phoneNumber
     * @param content
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + (CheckUtil.isEmpty(phoneNumber) ? "": phoneNumber));
        //Uri uri = Uri.parse("smsto:"); //不填写收件人
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", CheckUtil.isEmpty(content) ? "" : content);
        context.startActivity(intent);
    }

    /**
     * 系统设置界面
     * @param context
     * @return
     */
    public static boolean systemSet(Context context){
        PackageManager pm = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info == null) {
            return false;
        }
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", info.packageName, null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", info.packageName);
        }
        context.startActivity(localIntent);
        return  true;
    }
    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    /**
     * 回到home，后台运行
     *
     * @param context
     */
    public static void goHome(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }
    /**
     * 获得打开QQ聊天的Intent
     * @param context
     * @param qqNum qq号码
     * @return
     */
    public static Intent getQQChatActivity(Context context,String qqNum){
        String url="mqqwpa://im/chat?chat_type=wpa&uin="+ qqNum +"&version=1";
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }
    /**
     * 获得打开微信的Intent
     * @return
     */
    public static Intent getWeChatActivity(Context context){
        return context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
    }
    /**
     * 获得系统图库的Intent
     * @return
     */
    public static Intent getPicStorageView(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }
    /**
     * 获得系统照相机的Intent
     * @param file 照片放置的文件夹
     * @param fileName 照片保存的文件名
     * @return
     */
    public static Intent getCameraView(File file ,String fileName){
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(file, fileName)));
        return intent;
    }
    /**
     * 获取剪切图片的Intent
     * @param fromUri 图片源
     * @param outputX 图片宽
     * @param outputY 图片高
     * @param toFile 保存的file(包涵文件名)
     * @return Intent
     */
    public static Intent getCarpPicView(Uri fromUri,int outputX ,int outputY ,File toFile){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fromUri, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(toFile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX",outputX);
        intent.putExtra("outputY",outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    public static Intent getGPSStingActivity(){
        return  new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }
}
