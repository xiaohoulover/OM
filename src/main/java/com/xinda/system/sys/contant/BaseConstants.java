package com.xinda.system.sys.contant;

/**
 * 统一的常量类.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:11
 */
public class BaseConstants {

    /**
     * 默认当前页数.
     */
    public static final String DEFAULT_PAGE_NUM = "1";

    /**
     * 默认每页显示条数.
     */
    public static final String DEFAULT_PAGE_SIZE = "10";

    /**
     * 日期转换格式.
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

    /**
     * 时区.
     */
    public static final String TIME_ZONE = "GMT+08";

    /**
     * 订单状态-未发货.
     */
    public static final String ORDER_STATUS_NEW = "NEW";

    /**
     * 订单状态-未发货.
     */
    public static final String ORDER_STATUS_SAVE = "SAVE";

    /**
     * 订单状态-已发货.
     */
    public static final String ORDER_STATUS_COMP = "COMP";

    /**
     * grid行状态-增加.
     */
    public static final String BASE_DTO_ADD = "add";

    /**
     * grid行状态-删除.
     */
    public static final String BASE_DTO_DELETE = "delete";

    /**
     * grid行状态-更新.
     */
    public static final String BASE_DTO_UPDATE = "update";

    /**
     * 序列类型-订单.
     */
    public static final String SEQ_TYPE_OM = "OM";

    /**
     * 订单编号前缀.
     */
    public static final String ORDER_NUMBER_PRECODE = "XD";

    /**
     * 订单编号中日期code.
     */
    public static final String ORDER_NUMBER_DATECODE = "yyMMdd";

    /**
     * 订单编号序列初始化值.
     */
    public static final int OM_INIT_SEQ_VALUE = 1;

    /**
     * 订单编号序列步长.
     */
    public static final int OM_SEQ_STEP_LENGTH = 1;

    /**
     * 订单编号序列长度.
     */
    public static final int OM_SEQ_LENGTH = 3;

    //File
    /**
     * 文件保存目录.
     */
    public static final String FILE_SAVE_DIR = "D:/ZiLiao/";

    //System
    /**
     * 验证码存储时Key值.
     */
    public static final String VERIFICATION_KEY = "verification_key";
    /**
     * 验证码图片宽度.
     */
    public static final int VERIFICATION_CODE_WIDTH = 120;
    /**
     * 验证码图片高度.
     */
    public static final int VERIFICATION_CODE_HEIGHT = 36;
    /**
     * 验证码图片中字数.
     */
    public static final int VERIFICATION_CODE_COUNT = 4;
    /**
     * 验证码图片中字体大小.
     */
    public static final int VERIFICATION_CODE_FONT_HEIGHT = 28;
    /**
     * 图片上干扰线数.
     */
    public static final int INTERFERE_LINE_COUNT = 20;
    /**
     * 验证码产生随机数范围.
     */
    public static final char[] VERIFICATION_CODE_ARRAY = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};

}
