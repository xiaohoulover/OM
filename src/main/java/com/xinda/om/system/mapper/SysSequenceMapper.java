package com.xinda.om.system.mapper;

import com.xinda.om.system.dto.SysSequence;

/**
 * 序列号Mapper.
 * 
 * @author coudy
 *
 *         2017年2月24日
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