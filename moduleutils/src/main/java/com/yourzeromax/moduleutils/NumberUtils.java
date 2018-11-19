package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NumberUtils {

    public static double parseDouble(String s) {
        double ret = 0.0;
        try {
            if (!TextUtils.isEmpty(s)) {
                ret = Double.parseDouble(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static float parseFloat(String s) {
        float ret = 0f;
        try {
            if (!TextUtils.isEmpty(s)) {
                ret = Float.parseFloat(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static long parseLong(String s) {
        long ret = 0L;
        try {
            if (!TextUtils.isEmpty(s)) {
                if (s.indexOf(".") > 0) {
                    s = s.substring(0, s.indexOf("."));
                }
                ret = Long.parseLong(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int parseInt(String s) {
        int ret = 0;
        try {
            if (!TextUtils.isEmpty(s)) {
                if (s.indexOf(".") > 0) {
                    s = s.substring(0, s.indexOf("."));
                }
                ret = Integer.parseInt(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int getListCount(List list) {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    public static String getFormattedLargeValue(float value) {
        String ret;
        if (value >= 10000) {
            String[] suffix = new String[]{
                    "", "k", "m", "b", "t"
            };
            DecimalFormat format = new DecimalFormat("###E00");
            ret = format.format(value);
            int numericValue1 = Character.getNumericValue(ret.charAt(ret.length() - 1));
            int numericValue2 = Character.getNumericValue(ret.charAt(ret.length() - 2));
            int combined = Integer.valueOf(numericValue2 + "" + numericValue1);
            ret = ret.replaceAll("E[0-9][0-9]", suffix[combined / 3]);
            while (ret.length() > 5 || ret.matches("[0-9]+\\.[a-z]")) {
                ret = ret.substring(0, ret.length() - 2) + ret.substring(ret.length() - 1);
            }
        } else {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
            numberFormat.setMinimumFractionDigits(0);
            numberFormat.setMaximumFractionDigits(1);
            numberFormat.setGroupingUsed(false);
            ret = numberFormat.format(value);
        }
        return ret;
    }

    public static float strToFloatOnebit(String temp) {  //add huangzs
        float   scale   =   Float.parseFloat(temp.trim());
        DecimalFormat   fnum   =   new   DecimalFormat("##0.0");
        String   dd=fnum.format(scale);
        return Float.parseFloat(dd);
    }
}
