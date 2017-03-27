package com.xinda.system.sys.service;

import com.xinda.system.sys.dto.SysSequence;

/**
 * 序列号生成接口方法.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:11
 */
public interface ISysSequenceService {

    /**
     * 查询序列，存在则lock这条记录.
     *
     * @param sysSequence
     * @return SysSequence
     */
    SysSequence lockSysSequenceRecord(SysSequence sysSequence);

    /**
     * 新增序列.
     *
     * @param sysSequence
     * @return SysSequence
     */
    SysSequence insertSysSequence(SysSequence sysSequence);

    /**
     * 更新序列信息.
     *
     * @param sysSequence
     * @return SysSequence
     */
    SysSequence updateSysSequence(SysSequence sysSequence);

    /**
     * 生成序列对象.
     *
     * @param sysSequence
     * @return String
     */
    SysSequence generateSysSequence(SysSequence sysSequence);

    /**
     * 获取完整的编号.
     *
     * @param sysSequence
     * @return String
     */
    String getSequenceNumber(SysSequence sysSequence);

}
