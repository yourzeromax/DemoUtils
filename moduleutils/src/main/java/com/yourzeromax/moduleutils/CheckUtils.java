package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

public class CheckUtils {

    /**
     * Method name: isEmpty <BR>
     * Description: 判断字符串是否为空.是返回true.否则返回false <BR>
     * Remark: <BR>
     *
     * @param str
     * @return boolean<BR>
     */
    public static boolean isEmpty(String str) {

        return trim(str).equals("")  ? true : false;
    }

    /**
     * Method name: trim <BR>
     * Description: 去空格方法,可去全角空格 <BR>
     * Remark: <BR>
     *
     * @param str
     * @return String<BR>
     */
    public static String trim(String str) {

        if (str == null) {
            return "";
        }
        int s = 0;
        int len = str.length();
        while ((s < len) && ((str.charAt(s) == ' ') || (str.charAt(s) == '　'))) {
            s++;
        }
        while ((s < len)
                && ((str.charAt(len - 1) == ' ') || (str.charAt(len - 1) == '　'))) {
            len--;
        }
        return ((s > 0) || len < str.length()) ? str.substring(s, len) : str;
    }
}
