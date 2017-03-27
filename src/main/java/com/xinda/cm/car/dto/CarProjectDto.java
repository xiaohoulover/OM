package com.xinda.cm.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xinda.om.sys.contant.BaseConstants;
import com.xinda.om.system.dto.BaseDto;

import java.util.Date;

/**
 * 车辆项目Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:03.
 */
public class CarProjectDto extends BaseDto {
    /**
     * 主键Id.
     */
    private Integer projectId;
    /**
     * 车辆信息主键Id.
     */
    private Integer carId;
    /**
     * 项目名称.
     */
    private String projectName;
    /**
     * 有效期从.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date validPeriodFrom;
    /**
     * 有效期至.
     */
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT, timezone = BaseConstants.TIME_ZONE)
    private Date validPeriodTo;
    /**
     * 备注.
     */
    private String remark;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Date getValidPeriodFrom() {
        return validPeriodFrom;
    }

    public void setValidPeriodFrom(Date validPeriodFrom) {
        this.validPeriodFrom = validPeriodFrom;
    }

    public Date getValidPeriodTo() {
        return validPeriodTo;
    }

    public void setValidPeriodTo(Date validPeriodTo) {
        this.validPeriodTo = validPeriodTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
