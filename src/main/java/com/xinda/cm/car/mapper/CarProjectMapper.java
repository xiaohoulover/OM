package com.xinda.cm.car.mapper;

import com.xinda.cm.car.dto.CarProjectDto;

import java.util.List;

/**
 * 车辆项目映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:06.
 */
public interface CarProjectMapper {
    int deleteByPrimaryKey(Integer projectId);

    int insert(CarProjectDto record);

    int insertSelective(CarProjectDto record);

    CarProjectDto selectByPrimaryKey(Integer projectId);

    int updateByPrimaryKeySelective(CarProjectDto record);

    int updateByPrimaryKey(CarProjectDto record);

    /**
     * 根据carId获取Car项目信息.
     *
     * @param carId
     * @return
     */
    List<CarProjectDto> selectByCarId(Integer carId);

    /**
     * 删除车辆项目信息.
     *
     * @param carId
     */
    void deleteCarProjectsByCarId(Integer carId);

}
