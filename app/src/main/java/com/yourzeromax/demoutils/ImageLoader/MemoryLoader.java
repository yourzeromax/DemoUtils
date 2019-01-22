package com.yourzeromax.demoutils.ImageLoader;

/* *
 * Created by yourzeromax
 * on 2019/1/8
 *
 *
 */

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryLoader implements ImageLoader {
    private long maxMemory = Runtime.getRuntime().maxMemory() / 8;
    private LruCache<String, Bitmap> lruCache;

    MemoryLoader() {
        lruCache = new LruCache<String, Bitmap>((int) maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getImage(String url) {
        return lruCache.get(url);
    }

    @Override
    public void setImage(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
