package com.huipengpay.sdk.core;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {


    public static String getRequestRealIp(HttpServletRequest request) {
        String realIp = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null) {
            //获取真实的IP
            realIp = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isNotBlank(realIp)) {
            realIp = realIp.split(",")[0];
        }

        return isPrivateIPAddress(realIp) ? "127.0.0.1" : realIp;
    }

    /**
     * 判断是否是私有ip地址
     */
    public static boolean isPrivateIPAddress(String ipAddress) {
        InetAddress ia;
        try {
            ia = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return true;
        }
        return ia.isAnyLocalAddress() || ia.isLinkLocalAddress() || ia.isLoopbackAddress();
    }

}
