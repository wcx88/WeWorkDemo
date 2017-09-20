package com.winning.global.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


public class ParamUtil {
	
	public static String getAllParamsStr(HttpServletRequest request) {
        Enumeration names = request.getParameterNames();
        StringBuilder sbParams = new StringBuilder();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (sbParams.length() > 0) {
                sbParams.append("&");
            }
            sbParams.append(name).append("=").append(request.getParameter(name));
        }
	    return sbParams.toString();
	}

	public static JSONObject getAllParam(HttpServletRequest request) {
        Enumeration names = request.getParameterNames();
        JSONObject allParam = new JSONObject();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            allParam.put(name, request.getParameter(name));
        }
	    return allParam;
	}

	public static String getString(HttpServletRequest request, String name) {
	    return getString(request, name, "");
    }
	public static String getString(HttpServletRequest request, String name, String def) {
	    String value = request.getParameter(name);
	    return (value != null ? value : def);
    }
	public static int getInt(HttpServletRequest request, String name) {
	    return getInt(request, name, 0);
    }
	public static int getInt(HttpServletRequest request, String name, int def) {
	    String value = request.getParameter(name);
	    return (value != null ? Integer.parseInt(value) : def);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
