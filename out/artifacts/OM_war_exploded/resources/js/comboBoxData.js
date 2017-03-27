/**
 * @description 下拉框字段data组装.
 * @version 1.0
 * @author Coundy
 */

/**
 * 订单状态.
 */
var orderStatusData = [{
    value: 'ACCE',
    text: '已受理'
}, {
    value: 'SHIP',
    text: '已运输'
}, {
    value: 'FDBK',
    text: '已反馈'
}, {
    value: 'COMP',
    text: '已完成'
}];

/**
 * 罐柜类型.
 *
 */
var tankTypeData = [{
    value: '1',
    text: '20＇TK'
}, {
    value: '2',
    text: '20＇GP'
}, {
    value: '3',
    text: '40＇GP'
}, {
    value : '4',
    text : '自有柜'
}];

/**
 * 商品危险类别.
 */
var itemCategoryData = [{
    value: '0',
    text: '非危险品'
}, {
    value: '1',
    text: '类别1'
}, {
    value: '2',
    text: '类别2'
}, {
    value: '3',
    text: '类别3'
}, {
    value: '4',
    text: '类别4'
}, {
    value: '5',
    text: '类别5'
}, {
    value: '6',
    text: '类别6'
}, {
    value: '7',
    text: '类别7'
}, {
    value: '8',
    text: '类别8'
}, {
    value: '9',
    text: '类别9'
}];

/**
 * 车辆状态.
 */
var carStatusData = [{
    value: 'SHIP',
    text: '正在运输'
}, {
    value: 'COMP',
    text: '已完成'
}, {
    value: 'WAIT',
    text: '待执行'
}];

/**
 * 车辆类型.
 */
var carTypeData;
carTypeData = [{
    value: 'CF',
    text: '车头'
}, {
    value: 'CP',
    text: '车板'
}];

/**
 * 代垫费用.
 */
var disbursementData = [{
    value: 'FC',
    text: '压车费'
}, {
    value: 'HF',
    text: '加热费'
}, {
    value: 'CF',
    text: '报关费'
}, {
    value: 'FSF',
    text: '换单费'
}, {
    value: 'TSF',
    text: '码头堆存费'
}, {
    value: 'BSF',
    text: '代垫海运费'
}, {
    value: 'WF',
    text: '过磅费'
}, {
    value: 'TMF',
    text: '罐柜维修费'
}, {
    value: 'HBF',
    text: '吊箱费'
}, {
    value: 'TF',
    text: '标签费'
}, {
    value: 'OT',
    text: '其他...'
}];
