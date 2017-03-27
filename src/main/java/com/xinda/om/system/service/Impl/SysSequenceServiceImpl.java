package com.xinda.om.system.service.Impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinda.om.system.dto.SysSequence;
import com.xinda.om.system.mapper.SysSequenceMapper;
import com.xinda.om.system.service.ISysSequenceService;

/**
 * 序列号生成实现类.
 * 
 * @author coudy
 *
 *         2017年2月24日
 */
@Service
@Transactional
public class SysSequenceServiceImpl implements ISysSequenceService {

	@Autowired
	private SysSequenceMapper sysSequenceMapper;

	@Override
	public SysSequence lockSysSequenceRecord(SysSequence sysSequence) {
		return sysSequenceMapper.selectOfLockForUpdate(sysSequence);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SysSequence insertSysSequence(SysSequence sysSequence) {
		sysSequenceMapper.insertSelective(sysSequence);
		return sysSequence;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SysSequence updateSysSequence(SysSequence sysSequence) {
		sysSequenceMapper.updateByPrimaryKeySelective(sysSequence);
		return sysSequence;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SysSequence generateSysSequence(SysSequence sysSequence) {
		SysSequence seq = lockSysSequenceRecord(sysSequence);
		if (null == seq) {// insert
			seq = sysSequence;
			sysSequence.setNextSeqValue(seq.getInitSeqValue());
			insertSysSequence(seq);
		} else {// update
			seq.setNextSeqValue(seq.getNextSeqValue() + 1);
			updateSysSequence(seq);
		}
		return seq;
	}

	@Override
	public String getSequenceNumber(SysSequence sysSequence) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(sysSequence.getPreSeqCode());

		SysSequence seq = generateSysSequence(sysSequence);
		sBuilder.append(StringUtils.leftPad(seq.getNextSeqValue().toString(), seq.getSeqLength(), '0'));

		return sBuilder.toString();
	}

}
