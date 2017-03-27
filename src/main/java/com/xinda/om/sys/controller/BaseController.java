package com.xinda.om.sys.controller;

import com.xinda.om.sys.contant.BaseConstants;
import com.xinda.om.sys.exception.BaseException;
import com.xinda.om.system.dto.ResponseJsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础Controller. (实现基于@ExceptionHandler注解方式进行异常处理)
 * 不管是dao、service还是controller都只管抛出exception，而不进行处理，异常逐层往上抛出后，最终会由Spring框架捕获到，
 * 然后根据配置由不同的类进行处理
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:12
 */
@Controller
public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);


    /*
     * 日期格式化.
     *
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat(BaseConstants.DATE_TIME_FORMAT), true));
    }

    /**
     * 方法一：可实现根据不同的异常类来跳转到不同的页面.
     *
     * @param request
     * @param ex
     * @return
     *
     */
    /*
     * @ExceptionHandler public String exceptionHandlerToPage(HttpServletRequest
	 * request, Exception ex) { request.setAttribute("ex", ex);
	 * 
	 * if (ex instanceof OrderException) return "order-error";
	 * 
	 * // ...
	 * 
	 * return "error"; }
	 */

    /**
     * 方法二：当只需要返回提示信息并不进行页面跳转时，可自定义返回的对象.需在方法上加上@ResponseBody注解.
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Exception ex) {
        if (logger.isErrorEnabled()) {
            logger.error(ex.getMessage(), ex);
        }
        if (isAjaxRequest(request)) {
            // 定义返回消息
            ResponseJsonData resJsonData = new ResponseJsonData(false);
            if (ex instanceof BaseException) {
                BaseException e = (BaseException) ex;
                resJsonData.setCode(e.getCode());
            }
            resJsonData.setResMsg(ex.getMessage());
            return resJsonData;
        } else {
            return "error";
        }
    }

    /**
     * 判断是否ajax请求.
     *
     * @param request HttpServletRequest
     * @return 是否ajax请求.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return (header != null && "XMLHttpRequest".equalsIgnoreCase(header));
    }

}
