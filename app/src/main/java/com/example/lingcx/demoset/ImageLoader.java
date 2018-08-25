package com.example.lingcx.demoset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/8/25 下午8:27
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class ImageLoader {
    	public static Bitmap load(Context context, int resId) {

        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        //获取资源图片

        InputStream is = context.getResources().openRawResource(resId);

        Bitmap bitmap = BitmapFactory.decodeStream(is,null, opt);

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return new BitmapDrawable(context.getResources(),bitmap);

        return bitmap;
    }

}
