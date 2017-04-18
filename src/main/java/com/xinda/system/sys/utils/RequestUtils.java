package com.xinda.system.sys.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是否是Ajax请求.
 *
 * @Author Coundy.
 * @Date 2017/4/14 14:24.
 */
public class RequestUtils {

    public static final String X_REQUESTED_WIDTH = "X-Requested-With";
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * 判断是否ajax请求.
     *
     * @param request HttpServletRequest
     * @return 是否ajax请求.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xr = request.getHeader(X_REQUESTED_WIDTH);
        return (xr != null && XML_HTTP_REQUEST.equalsIgnoreCase(xr));
    }

}
