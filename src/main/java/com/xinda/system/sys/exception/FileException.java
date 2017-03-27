package com.xinda.system.sys.exception;

/**
 * 文件管理统一异常.
 *
 * @Author Coundy.
 * @Date 2017/3/27 23:09
 */
public class FileException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统模块消息Code.
     */
    private static final String DEFAULT_CODE = "FILE";


    public static final String UPLOAD_FILE_ERROR = "上传文件失败!";

    /**
     * 构造方法.用户代码中声明抛出异常信息.
     *
     * @param resMsg 消息描述
     */
    public FileException(String resMsg) {
        super(DEFAULT_CODE, resMsg);
    }

    public FileException(String code, String resMsg) {
        super(code, resMsg);
    }

}
