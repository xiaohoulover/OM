package com.xinda.um.user.controller;

import com.xinda.system.sys.controller.BaseController;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理Controller.
 *
 * @Author Coundy.
 * @Date 2017/3/27 21:49.
 */
@Controller
public class SysUserController extends BaseController {
    Logger logger = Logger.getLogger(SysUserController.class);

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/user/getUserById")
    @ResponseBody
    public String toUserHtml() {
        logger.info("Controller ...");
        SysUser user = sysUserService.getSysUserById(1);
        logger.info(user);
        return "index";
    }
}
