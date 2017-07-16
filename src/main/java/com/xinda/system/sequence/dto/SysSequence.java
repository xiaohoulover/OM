package com.xinda.system.sequence.dto;

import com.xinda.system.sys.dto.BaseDto;
import org.springframework.util.StringUtils;

import javax.persistence.Id;

/**
 * 序列号Dto.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:56
 */
public class SysSequence extends BaseDto {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键Id.
     */
    @Id
    private Integer sequenceId;

    /**
     * 序列类型.
     */
    private String seqType;

    /**
     * 初始值.
     */
    private Integer initSeqValue;

    /**
     * 步长.
     */
    private Integer stepLength;

    /**
     * 序列号长度.
     */
    private Integer seqLength;

    /**
     * 下一个序列值.
     */
    private Integer nextSeqValue;
    /**
     * 前缀Code.
     */
    private String preSeqCode;

    private String pk1Value;

    private String pk2Value;

    private String pk3Value;

    public SysSequence() {

    }

    public SysSequence(String seqType, Integer initSeqValue, Integer stepLength, Integer seqLength, String preSeqCode,
                       String pk1Value, String pk2Value, String pk3Value) {
        super();
        this.seqType = seqType;
        this.initSeqValue = initSeqValue;
        this.stepLength = stepLength;
        this.seqLength = seqLength;
        this.preSeqCode = preSeqCode;
        this.pk1Value = StringUtils.isEmpty(pk1Value) == true ? "-1" : pk1Value;
        this.pk2Value = StringUtils.isEmpty(pk1Value) == true ? "-1" : pk2Value;
        this.pk3Value = StringUtils.isEmpty(pk1Value) == true ? "-1" : pk2Value;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    public Integer getInitSeqValue() {
        return initSeqValue;
    }

    public void setInitSeqValue(Integer initSeqValue) {
        this.initSeqValue = initSeqValue;
    }

    public Integer getStepLength() {
        return stepLength;
    }

    public void setStepLength(Integer stepLength) {
        this.stepLength = stepLength;
    }

    public Integer getSeqLength() {
        return seqLength;
    }

    public void setSeqLength(Integer seqLength) {
        this.seqLength = seqLength;
    }

    public Integer getNextSeqValue() {
        return nextSeqValue;
    }

    public void setNextSeqValue(Integer nextSeqValue) {
        this.nextSeqValue = nextSeqValue;
    }

    public String getPreSeqCode() {
        return preSeqCode;
    }

    public void setPreSeqCode(String preSeqCode) {
        this.preSeqCode = preSeqCode;
    }

    public String getPk1Value() {
        return pk1Value;
    }

    public void setPk1Value(String pk1Value) {
        this.pk1Value = pk1Value;
    }

    public String getPk2Value() {
        return pk2Value;
    }

    public void setPk2Value(String pk2Value) {
        this.pk2Value = pk2Value;
    }

    public String getPk3Value() {
        return pk3Value;
    }

    public void setPk3Value(String pk3Value) {
        this.pk3Value = pk3Value;
    }

}