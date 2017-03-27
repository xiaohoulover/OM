package com.xinda.om.order.controller;

import com.xinda.fm.file.mapper.FileManagerMapper;
import com.xinda.om.sys.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Controller测试类.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:10
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @Autowired
    private FileManagerMapper fileOperateMapper;


}
