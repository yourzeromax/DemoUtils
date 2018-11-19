package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiNetwork(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    public static String getNetworkType(Context context) {
        String result = "";
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                int type = networkInfo.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_MOBILE:
                        if (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_1xRTT
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_IDEN) {
                            result = "2G";
                        } else if (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_UMTS
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_HSDPA
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_HSUPA
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_HSPA
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_B
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EHRPD
                                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_HSPAP) {
                            result = "3G";
                        } else if (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE) {
                            result = "4G";
                        } else {
                            result = "2G";
                        }
                        break;
                    case ConnectivityManager.TYPE_WIFI:
                        result = "WiFi";
                        break;
                    default:
                        result = "";
                        break;
                }
            }
        }
        return result;
    }

    public static boolean isVpnConnected() {
        try {
            Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
            if(niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if(!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())){
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
}
