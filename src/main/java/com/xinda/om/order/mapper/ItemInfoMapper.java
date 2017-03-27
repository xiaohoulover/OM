package com.xinda.om.order.mapper;

import com.xinda.om.order.dto.ItemInfoDto;

import java.util.List;

/**
 * ItemMapper映射接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:27
 */
public interface ItemInfoMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemInfoDto record);

    int insertSelective(ItemInfoDto record);

    ItemInfoDto selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemInfoDto record);

    int updateByPrimaryKey(ItemInfoDto record);

    /**
     * 查询运输商品信息.
     *
     * @param itemInfoDto
     * @return
     */
    List<ItemInfoDto> selectItemInfoByParms(ItemInfoDto itemInfoDto);

    /**
     * 根据订单头Id删除商品信息。
     *
     * @param salesOrgId 订单头Id
     */
    void deleteByOrderId(Integer salesOrgId);

}