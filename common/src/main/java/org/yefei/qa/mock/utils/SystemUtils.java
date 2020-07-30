package org.yefei.qa.mock.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yefei
 * @date: 2020/7/30
 */
public class SystemUtils {
    public static String getHostName() {
        return org.apache.commons.lang3.SystemUtils.getHostName() == null ? getHostNameForLiunx() : org.apache.commons.lang3.SystemUtils.getHostName();
    }

    private static String getHostNameForLiunx() {
        try {
            return (InetAddress.getLocalHost()).getHostName();
        } catch (UnknownHostException uhe) {
            String host = uhe.getMessage(); // host = "hostname: hostname"
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }
}
