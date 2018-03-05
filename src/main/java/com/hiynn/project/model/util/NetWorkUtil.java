package com.hiynn.project.model.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lihuafeng
 * 
 */
public final class NetWorkUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetWorkUtil.class);

    private static String myID;

    private NetWorkUtil() {

    }

    /**
	  * @return String
	 */
	public static String getMyHostName() {
		String retStr = null;
		Enumeration<NetworkInterface> allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			Exception e1 = new Exception(
					"NetworkInterface getNetworkInterfaces failed", e);
			LOGGER.error(e1.getMessage());
		}
		if (allNetInterfaces == null) {
			return null;
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = allNetInterfaces.nextElement();
			Enumeration<InetAddress> addresses = netInterface
					.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = addresses.nextElement();
				if (ip instanceof Inet4Address) {
					if (!ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						retStr = ip.getHostName();
						break;
					}
				}
			}
		}

		return retStr;
	}
	
	/**
	  * @return String
	 */
	public static String getMyIpAdress() {
		String retStr = null;
		InetAddress ip = null;
		try {
			 ip = InetAddress.getLocalHost();
			 retStr = ip.getHostAddress();
		} catch (UnknownHostException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return retStr;
	}

    /**
     * @return String
     */
    public static String getMyID() {
        if (myID == null) {
            myID = getmyid();
        }
        return myID;
    }

    private static String getmyid() {

        StringBuilder sb = new StringBuilder();
        sb.append(getMyIpAdress());
        sb.append(":tid:");
        sb.append(Thread.currentThread().getId());
        return sb.toString();
    }
}
