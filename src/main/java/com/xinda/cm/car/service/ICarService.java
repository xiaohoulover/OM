package com.xinda.cm.car.service;

import com.xinda.cm.car.dto.CarInfoDto;

import java.util.List;

/**
 * 车辆管理接口..
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:45.
 */
public interface ICarService {

    /**
     * 保存车辆信息.
     *
     * @param carInfoDto
     * @return
     */
    CarInfoDto saveCar(CarInfoDto carInfoDto);

    /**
     * 获取车辆信息.
     *
     * @param carId
     * @return
     */
    CarInfoDto getCarDetails(Integer carId);

    /**
     * 查询所有车辆信息.
     *
     * @param carInfoDto 参数对象
     * @return
     */
    List<CarInfoDto> queryCarsByParams(CarInfoDto carInfoDto);

    /**
     * 删除车辆信息
     *
     * @param carInfoDtos 待删除集合
     */
    void deleteCars(List<CarInfoDto> carInfoDtos);

}
