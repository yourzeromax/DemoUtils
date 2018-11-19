package com.yourzeromax.moduleutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

/**
 * @author yourzeromax
 */
public class FileUtils {

    /**
     * @param c
     * @param fileName
     * @param bitmap
     * @return
     */
    public static String saveFile(Context c, String fileName, Bitmap bitmap) {
        return saveFile(c, "", fileName, bitmap);
    }

    public static String saveFile(Context c, String filePath, String fileName, Bitmap bitmap) {
        byte[] bytes = bitmapToBytes(bitmap);
        return saveFile(c, filePath, fileName, bytes);
    }

    public static byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String saveFile(Context c, String filePath, String fileName, byte[] bytes) {
        String fileFullName = "";
        FileOutputStream fos = null;
        String dateFolder = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
                .format(new Date());
        try {
            String suffix = "";
            if (filePath == null || filePath.trim().length() == 0) {
                filePath = Environment.getExternalStorageDirectory() + "/Max/" + dateFolder + "/";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, fileName + suffix);
            fileFullName = fullFile.getPath();
            fos = new FileOutputStream(new File(filePath, fileName + suffix));
            fos.write(bytes);
        } catch (Exception e) {
            fileFullName = "";
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    fileFullName = "";
                }
            }
        }
        return fileFullName;
    }


    //递归删除指定路径下的所有文件
    public static void deleteFileAll(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();

            for (File f : files) {
                deleteFileAll(f);//递归删除每一个文件
                f.delete();//删除该文件夹
            }
        }
    }//递归删除指定路径下的所有文件

    public static void deleteFileAllBut(File file, String[] but) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile() || file.list().length == 0) {
            if (but.length > 0) {
                for (int i = 0; i < but.length; i++) {
                    if (but[i].equals(file.getName())) {
                        return;
                    }
                }
            }

            file.delete();

        } else {
            File[] files = file.listFiles();

            for (File f : files) {
                deleteFileAllBut(f, but);//递归删除每一个文件
                f.delete();//删除该文件夹
            }
        }
    }


    public static long getFolderSize(Context context, File file) {
        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(context, fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }


    public static long getFolderSizeBut(File file, String[] but) {
        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSizeBut(fileList[i], but);

                } else {
                    boolean conntainBut = false;
                    for (int j = 0; j < but.length; j++) {
                        if (but[j].equals(fileList[i].getName())) {
                            conntainBut = true;
                            break;
                        }
                    }

                    if (conntainBut) {
                        DebugUtils.d("fileutilfolderSize", fileList[i] + "_butsize" + fileList[i].length());
                    } else {
                        size = size + fileList[i].length();
                        DebugUtils.d("fileutilfolderSize", fileList[i].getName() + "totalsize" + size);
                    }
                }
            }
        } catch (Exception e) {
            DebugUtils.d("fileutilfolderSize", "eeeeee");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }
}
