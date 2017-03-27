package com.xinda.om.order.mapper;

import com.xinda.om.order.dto.DisbursementDto;

import java.util.List;

/**
 * DisbursementMapper映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:39
 */
public interface DisbursementMapper {
    int deleteByPrimaryKey(Integer disbursementId);

    int insert(DisbursementDto record);

    int insertSelective(DisbursementDto record);

    DisbursementDto selectByPrimaryKey(Integer disbursementId);

    int updateByPrimaryKeySelective(DisbursementDto record);

    int updateByPrimaryKey(DisbursementDto record);

    /**
     * 查询代垫费用.
     *
     * @param disbursementDto
     * @return
     */
    List<DisbursementDto> selectDisbursementDtosByParms(DisbursementDto disbursementDto);

    void deleteByOrderId(Integer orderId);

}