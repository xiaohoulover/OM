package com.xinda.cm.car.dto;

import com.xinda.system.sys.dto.BaseDto;

import java.util.List;

/**
 * 车辆信息Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:44.
 */
public class CarInfoDto extends BaseDto {
    /**
     * 主键Id.
     */
    private Integer carId;
    /**
     * 车牌号.
     */
    private String plateNo;
    /**
     * 类型.
     */
    private String type;
    /**
     * 油耗比.
     */
    private Float fuelConsumptionRatio;
    /**
     * 车辆项目集合.
     */
    private List<CarProjectDto> carProjectDtoList;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo == null ? null : plateNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Float getFuelConsumptionRatio() {
        return fuelConsumptionRatio;
    }

    public void setFuelConsumptionRatio(Float fuelConsumptionRatio) {
        this.fuelConsumptionRatio = fuelConsumptionRatio;
    }

    public List<CarProjectDto> getCarProjectDtoList() {
        return carProjectDtoList;
    }

    public void setCarProjectDtoList(List<CarProjectDto> carProjectDtoList) {
        this.carProjectDtoList = carProjectDtoList;
    }
}
