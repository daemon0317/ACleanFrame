package com.framework.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

/**
 * com.chinatown.mylibrary.utils
 * Created by daemon on 2016/2/26 0026.
 * 说明 ：图片操作
 */
public class DImage {
    /**
     * 获得正方形的一个圆角图片
     * @param bitmap
     * @param pixels 圆角 px
     * @param width px
     * @param height px
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        Bitmap pic = comp(bitmap, width, height, 10, true, true);
        Bitmap output = Bitmap.createBitmap(pic.getWidth(),
                pic.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xffffffff;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, pic.getWidth(), pic.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(output, null, rect, paint);
        return output;
    }
    /**
     * 按比例压缩图片
     *
     * @param image
     * @param width 0 默认800f
     * @param height 0 默认480f
     * @param max_size 压缩大小 kb
     * @param isAdjust 是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
     * @param isZip 是否压缩大小 true 压缩
     * @return
     */
    public static Bitmap comp(Bitmap image, int width, int height, int max_size ,boolean isAdjust ,boolean isZip) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        int hh = width;//这里设置高度为800f
        int ww = height;//这里设置宽度为480f
        if (hh == 0) {
            hh = 800;
        }
        if (ww == 0) {
            ww = 480;
        }
        float sx = new BigDecimal(ww).divide(new BigDecimal(w), 2, BigDecimal.ROUND_DOWN).floatValue();
        float sy = new BigDecimal(hh).divide(new BigDecimal(h), 2, BigDecimal.ROUND_DOWN).floatValue();
        if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
            sx = (sx < sy ? sx : sy);
            sy = sx;// 哪个比例小一点，就用哪个比例
        }
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);
        Bitmap bitmap = Bitmap.createBitmap(image, 0, 0, ww, hh, matrix, true);
        if (isZip){
            bitmap = zip(bitmap,max_size);
        }
        return bitmap;//压缩好比例大小后再进行质量压缩
    }
    private static Bitmap zip(Bitmap map, int max_size){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int options = 100;
        map.compress(Bitmap.CompressFormat.JPEG, options, out);
        while (out.toByteArray().length / 1024 > max_size) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            options -= 10;//每次都减少10
            out.reset();//重置baos即清空baos
            map.compress(Bitmap.CompressFormat.JPEG, options, out);//这里压缩options%，把压缩后的数据存放到baos中
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return  BitmapFactory.decodeStream(in, null, newOpts);
    }
}
