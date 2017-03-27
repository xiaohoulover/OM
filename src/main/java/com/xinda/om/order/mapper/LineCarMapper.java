package com.xinda.om.order.mapper;

import com.xinda.om.order.dto.LineCarDto;

import java.util.List;

/**
 * 订单行-车辆映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:50.
 */
public interface LineCarMapper {
    int deleteByPrimaryKey(Integer lineCarId);

    int insert(LineCarDto record);

    int insertSelective(LineCarDto record);

    LineCarDto selectByPrimaryKey(Integer lineCarId);

    int updateByPrimaryKeySelective(LineCarDto record);

    int updateByPrimaryKey(LineCarDto record);

    /**
     * 查询订单Car信息.
     *
     * @param lineCarDto
     * @return
     */
    List<LineCarDto> selectLineCarsByParms(LineCarDto lineCarDto);
}
