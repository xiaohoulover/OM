package com.xinda.cm.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.cm.car.dto.CarInfoDto;
import com.xinda.cm.car.dto.CarProjectDto;
import com.xinda.cm.car.mapper.CarInfoMapper;
import com.xinda.cm.car.mapper.CarProjectMapper;
import com.xinda.cm.car.service.ICarService;
import com.xinda.system.sys.contant.BaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 车辆管理接口实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:45.
 */
@Service
@Transactional
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private CarProjectMapper carProjectMapper;

    @Override
    public CarInfoDto saveCar(CarInfoDto carInfoDto) {
        //车辆头信息操作
        if (null == carInfoDto.getCarId()) {
            carInfoMapper.insertSelective(carInfoDto);
        } else {
            carInfoMapper.updateByPrimaryKeySelective(carInfoDto);
        }
        //车辆项目操作
        if (null != carInfoDto.getCarProjectDtoList()) {
            for (CarProjectDto carProjectDto : carInfoDto.getCarProjectDtoList()) {
                carProjectDto.setCarId(carInfoDto.getCarId());
                switch (carProjectDto.get__status()) {
                    case BaseConstants.BASE_DTO_ADD:
                        carProjectMapper.insertSelective(carProjectDto);
                        break;
                    case BaseConstants.BASE_DTO_UPDATE:
                        carProjectMapper.updateByPrimaryKeySelective(carProjectDto);
                        break;
                    case BaseConstants.BASE_DTO_DELETE:
                        carProjectMapper.deleteByPrimaryKey(carProjectDto.getProjectId());
                        break;
                    default:
                        break;
                }
            }
        }
        return carInfoDto;
    }

    @Override
    public CarInfoDto getCarDetails(Integer carId) {
        CarInfoDto carInfoDto = carInfoMapper.selectByPrimaryKey(carId);
        carInfoDto.setCarProjectDtoList(carProjectMapper.selectByCarId(carId));
        return carInfoDto;
    }

    @Override
    public List<CarInfoDto> queryCarsByParams(int page, int pagesize, CarInfoDto carInfoDto) {
        PageHelper.startPage(page, pagesize);
        return carInfoMapper.queryCarsByParams(carInfoDto);
    }

    @Override
    public List<CarInfoDto> getCarsByParams(CarInfoDto carInfoDto) {
        List<CarInfoDto> carInfoDtos = carInfoMapper.queryCarsByParams(carInfoDto);
        for (CarInfoDto infoDto : carInfoDtos) {
            infoDto.setCarProjectDtoList(carProjectMapper.selectByCarId(infoDto.getCarId()));
        }
        return carInfoDtos;
    }

    @Override
    public void deleteCars(List<CarInfoDto> carInfoDtos) {
        for (CarInfoDto carInfoDto : carInfoDtos) {
            //头
            carInfoMapper.deleteCars(carInfoDto);
            //行
            if (null != carInfoDto.getCarProjectDtoList()) {
                carProjectMapper.deleteCarProjectsByCarId(carInfoDto.getCarId());
            }
        }
    }
}
