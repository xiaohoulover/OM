package com.xinda.cm.car.mapper;

import com.xinda.cm.car.dto.CarInfoDto;

import java.util.List;

/**
 * 车辆映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:45.
 */
public interface CarInfoMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(CarInfoDto record);

    int insertSelective(CarInfoDto record);

    CarInfoDto selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(CarInfoDto record);

    int updateByPrimaryKey(CarInfoDto record);

    /**
     * 查询所有的车辆信息.
     *
     * @param carInfoDto
     * @return
     */
    List<CarInfoDto> queryCarsByParams(CarInfoDto carInfoDto);

    /**
     * 删除车辆信息
     *
     * @param carInfoDto
     */
    void deleteCars(CarInfoDto carInfoDto);
}
