package com.framework.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明：一些常用的页面调用
 */
public class DIntent {
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
//    public static void gotoQQChatWebView(Context context, WebView view , String url){
//        view.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                view.loadUrl(url);
//                return true;
//            }
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                String url = request.getUrl().getPath();
//                if (url.startsWith("http") || url.startsWith("https")) {
//                    return super.shouldInterceptRequest(view, request);
//                }else {
//                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                    context,startActivity(in);
//                    return null;
//                }
//            }
//        });
//    }
}
