package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import android.util.Log;

public class DebugUtils {

    /**
     * you should override the TAG with your project name.
     */
    public static final String TAG = "DebugUtils";


    /**
     * make this Logs debug or release.
     */
    public static final boolean DEBUG = true;

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void e(String error) {

        if (DEBUG) {

            Log.e(TAG, error);
        }
    }
}
