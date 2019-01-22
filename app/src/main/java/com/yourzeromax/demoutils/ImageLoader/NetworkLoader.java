package com.yourzeromax.demoutils.ImageLoader;

import android.graphics.Bitmap;
import android.os.AsyncTask;


/* *
 * Created by yourzeromax
 * on 2019/1/8
 *
 *
 */

public class NetworkLoader implements ImageLoader {

    private ImageLoader mDiskLoader, mMemoryLoader;

    public NetworkLoader(ImageLoader mDiskLoader, ImageLoader mMemoryLoader) {
        this.mDiskLoader = mDiskLoader;
        this.mMemoryLoader = mMemoryLoader;
    }

    @Override
    public Bitmap getImage(String url) {
        Bitmap bitmap = null;
        ImageTask imageTask = new ImageTask();
        try {
            bitmap = imageTask.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDiskLoader.setImage(url, bitmap);
        mMemoryLoader.setImage(url, bitmap);
        return bitmap;
    }

    @Override
    public void setImage(String url, Bitmap bitmap) {

    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        public ImageTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }
    }
}
