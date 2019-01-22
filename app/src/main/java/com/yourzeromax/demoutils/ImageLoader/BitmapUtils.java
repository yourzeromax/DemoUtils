package com.yourzeromax.demoutils.ImageLoader;

/* *
 * Created by yourzeromax
 * on 2019/1/8
 *
 *
 */

import android.graphics.Bitmap;

public class BitmapUtils implements ImageLoader {

    private volatile static BitmapUtils bitmapUtils;

    private ImageLoader mDiskLoader, mMemoryLoader, mNetworkLoader;

    public static BitmapUtils getInstance() {
        if (bitmapUtils == null) {
            synchronized (BitmapUtils.class) {
                if (bitmapUtils == null) {
                    bitmapUtils = new BitmapUtils();
                }
            }
        }
        return bitmapUtils;
    }

    private BitmapUtils() {
        mDiskLoader = new DiskLoader();
        mMemoryLoader = new MemoryLoader();
        mNetworkLoader = new NetworkLoader(mDiskLoader, mMemoryLoader);
    }

    @Override
    public Bitmap getImage(String url) {
        Bitmap bitmap = mMemoryLoader.getImage(url);

        if (bitmap == null) {
            bitmap = mDiskLoader.getImage(url);
            mMemoryLoader.setImage(url, bitmap);
        }

        if (bitmap == null) {
            bitmap = mNetworkLoader.getImage(url);
        }
        return bitmap;
    }

    @Override
    public void setImage(String url, Bitmap bitmap) {

    }
}
