package com.xinda.system.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本属性字段扩展.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:57
 */
public class BaseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * LigerUI Grid行Id.
     */
    @JsonInclude(Include.NON_NULL)
    private String __id;

    @JsonInclude(Include.NON_NULL)
    private int __index;

    @JsonInclude(Include.NON_NULL)
    private String __nextid;

    @JsonInclude(Include.NON_NULL)
    private String __previd;

    /**
     * LigerUI Grid行状态.
     */
    @JsonInclude(Include.NON_NULL)
    private String __status;

    /**
     * 表创建时间.
     */
    @JsonIgnore
    private Date creationDate;

    /**
     * 表最后一次更新时间.
     */
    @JsonIgnore
    private Date lastUpdateDate;

    /**
     * 行记录号.
     */
    private Integer objectVersionNum;

    public String get__id() {
        return __id;
    }

    public void set__id(String __id) {
        this.__id = __id;
    }

    public String get__status() {
        return __status;
    }

    public void set__status(String __status) {
        this.__status = __status;
    }

    public int get__index() {
        return __index;
    }

    public void set__index(int __index) {
        this.__index = __index;
    }

    public String get__nextid() {
        return __nextid;
    }

    public void set__nextid(String __nextid) {
        this.__nextid = __nextid;
    }

    public String get__previd() {
        return __previd;
    }

    public void set__previd(String __previd) {
        this.__previd = __previd;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getObjectVersionNum() {
        return objectVersionNum;
    }

    public void setObjectVersionNum(Integer objectVersionNum) {
        this.objectVersionNum = objectVersionNum;
    }
}
