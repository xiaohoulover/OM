package com.xinda.cm.car.controller;

import com.xinda.cm.car.dto.CarInfoDto;
import com.xinda.cm.car.service.ICarService;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.controller.BaseController;
import com.xinda.system.sys.dto.ResponseJsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆管理Controller.
 *
 * @Author Coundy.
 * @Date 2017/3/21 15:43.
 */
@Controller
public class CarController extends BaseController {

    @Autowired
    private ICarService carService;

    /**
     * 保存车辆信息.
     *
     * @param carInfoDto 车辆参数对象
     * @return
     */
    @RequestMapping("/car/saveCar")
    @ResponseBody
    public ResponseJsonData saveCar(@RequestBody CarInfoDto carInfoDto) {
        return new ResponseJsonData(carService.saveCar(carInfoDto));
    }

    /**
     * 获取车辆信息.
     *
     * @param carId
     * @return
     */
    @RequestMapping(value = "/car/getCarDetails", method = RequestMethod.GET)
    @ResponseBody
    public CarInfoDto getCarDetails(Integer carId) {
        return carService.getCarDetails(carId);
    }

    /**
     * 查询页-根据参数条件查询车辆信息(带分页).
     *
     * @param carInfoDto
     * @return
     */
    @RequestMapping(value = "/car/queryCarsByParams", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData queryCarsByParams(@RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_NUM) int page,
                                              @RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_SIZE) int pagesize,
                                              CarInfoDto carInfoDto) {
        return new ResponseJsonData(carService.queryCarsByParams(page, pagesize, carInfoDto));
    }

    /**
     * 订单创建页-车辆信息查询.
     *
     * @param carInfoDto
     * @return
     */
    @RequestMapping(value = "/car/getCarsByParams", method = RequestMethod.POST)
    @ResponseBody
    public List<CarInfoDto> getCarsByParams(CarInfoDto carInfoDto) {
        return carService.getCarsByParams(carInfoDto);
    }

    /**
     * 删除车辆信息.
     *
     * @return
     */
    @RequestMapping(value = "/car/deleteCars", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData deleteCars(@RequestBody List<CarInfoDto> carInfoDtoList) {
        carService.deleteCars(carInfoDtoList);
        return new ResponseJsonData(true);
    }

}
