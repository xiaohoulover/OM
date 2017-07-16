package com.xinda.um.user.controller;

import com.xinda.system.sys.controller.BaseController;
import com.xinda.system.sys.dto.ResponseJsonData;
import com.xinda.system.login.exception.LoginException;
import com.xinda.um.user.dto.SysUser;
import com.xinda.um.user.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 创建用户信息.
     *
     * @param request
     * @param response
     * @param sysUser
     * @return
     * @throws LoginException
     */
    @RequestMapping("/user/saveSysUser")
    @ResponseBody
    public ResponseJsonData createSysUser(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestBody SysUser sysUser) throws LoginException {
        return new ResponseJsonData(sysUserService.createSysUser(sysUser));
    }

    /**
     * 批量保存用户信息.
     *
     * @param request
     * @param response
     * @param sysUsers
     * @return
     */
    @RequestMapping("/user/batchSaveSysUsers")
    @ResponseBody
    public ResponseJsonData batchSaveOrUpdateSysUsers(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestBody List<SysUser> sysUsers) throws LoginException {
        return new ResponseJsonData(sysUserService.batchSaveOrUpdateSysUsers(sysUsers));
    }

    /**
     * 查询用户信息.
     *
     * @param request
     * @param response
     * @param sysUser
     * @param page
     * @param pagesize
     * @return
     * @throws LoginException
     */
    @RequestMapping("/user/querySysUserByParam")
    @ResponseBody
    public ResponseJsonData querySysUserByParam(HttpServletRequest request,
                                                HttpServletResponse response,
                                                SysUser sysUser, int page, int pagesize) throws LoginException {
        return new ResponseJsonData(sysUserService.querySysUser(sysUser, page, pagesize));
    }

    /**
     * 删除用户信息.
     *
     * @param request
     * @param response
     * @param sysUsers
     * @return
     * @throws LoginException
     */
    @RequestMapping("/user/deleteSysUsers")
    @ResponseBody
    public ResponseJsonData deleteSysUsers(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody List<SysUser> sysUsers) throws LoginException {
        return new ResponseJsonData(sysUserService.deleteSysUsers(sysUsers));
    }

}
