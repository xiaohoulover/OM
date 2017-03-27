package com.xinda.system.sys.dto;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 由于LigerUI要求返回的数据是统一的json格式. 这里做一个数据转换.
 *
 * @Author Coundy.
 * @Date 2017/3/21 16:57
 */
public class ResponseJsonData {

    /**
     * 返回对象数据.
     */
    private Object objData;

    /**
     * 返回集合数据.
     */
    private List<?> lines;

    /**
     * 返回 成功与否标识.
     */
    private boolean success = true;

    /**
     * 返回信息Code.
     */
    private String code;

    /**
     * 返回信息.
     */
    private String resMsg;

    /**
     * 总数.
     */
    private Long total;

    public ResponseJsonData() {
    }

    public ResponseJsonData(boolean success) {
        setSuccess(success);
    }

    public ResponseJsonData(Object obj) {
        this(true);
        this.objData = obj;
    }

    public ResponseJsonData(List<?> list) {
        this(true);
        setLines(list);
        if (list instanceof Page) {
            setTotal(((Page<?>) list).getTotal());
        } else {
            setTotal((long) list.size());
        }
    }

    public Object getObjData() {
        return objData;
    }

    public void setObjData(Object objData) {
        this.objData = objData;
    }

    public List<?> getLines() {
        return lines;
    }

    public void setLines(List<?> lines) {
        this.lines = lines;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
