package com.yourzeromax.demoutils.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/* *
 * Created by yourzeromax
 * on 2019/1/8
 *
 *
 */

public class DiskLoader implements ImageLoader {
    String baseDisk = Environment.getExternalStorageDirectory().getAbsolutePath() + "/weshow";

    public static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bitmap getImage(String url) {
        File f = new File(baseDisk, getMD5(url));
        try {
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setImage(String url, Bitmap bitmap) {
        File f = new File(baseDisk, getMD5(url));
        try {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
