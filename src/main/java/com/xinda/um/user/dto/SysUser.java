package com.xinda.um.user.dto;

import com.xinda.system.sys.dto.BaseDto;

import javax.persistence.Id;

/**
 * 用户登录Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:55
 */
public class SysUser extends BaseDto {

    /**
     * 序列化.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户Id.
     */
    @Id
    private Integer userId;

    /**
     * 登录名.
     */
    private String userName;

    /**
     * 密码.
     */
    private String password;

    /**
     * 用户类型.
     */
    private String userType;

    /**
     * 是否有效.
     */
    private String status;

    //add properties
    /**
     * 旧密码.
     */
    private String oldPassword;

    public SysUser() {
    }

    public SysUser(Integer userId, String userName, String password, String userType, String status, String oldPassword) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
