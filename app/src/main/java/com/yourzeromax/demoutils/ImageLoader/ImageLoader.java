package com.yourzeromax.demoutils.ImageLoader;

/* *
 * Created by yourzeromax
 * on 2019/1/8
 *
 *
 */

import android.graphics.Bitmap;

public interface ImageLoader {
    Bitmap getImage(String url);

    void setImage(String url, Bitmap bitmap);
}
