package com.xinda.system.sequence.mapper;

import com.xinda.system.sequence.dto.SysSequence;

/**
 * 序列号Mapper.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:10
 */
public interface SysSequenceMapper {
    int deleteByPrimaryKey(Integer sequenceId);

    int insert(SysSequence record);

    int insertSelective(SysSequence record);

    SysSequence selectByPrimaryKey(Integer sequenceId);

    int updateByPrimaryKeySelective(SysSequence record);

    int updateByPrimaryKey(SysSequence record);

    /**
     * 查询序列信息.
     *
     * @param sysSequence
     * @return SysSequence
     */
    SysSequence selectOfLockForUpdate(SysSequence sysSequence);

}