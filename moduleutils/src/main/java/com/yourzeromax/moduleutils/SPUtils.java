package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    /*
    * you need add some String TAG in here.
    * */
    private static final String CONFIG = "config";

    /**
     * 获取SharedPreferences实例对象
     *@param context 上下文
     * @param fileName 文件名称
     */
    public static SharedPreferences getSharedPreference(Context context,String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


}
