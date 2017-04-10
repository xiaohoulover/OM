/**
 * @description 订单JS文件.
 * @version 1.0
 * @author coudy
 */

var fileDownBtn, fileUploadBtn, deleteBtn, editBtn, saveBtn;
/**
 * 初始化订单页面Button.
 */
function init_order_btn() {

    $("#remark").attr("disabled", true);

    fileUploadBtn = $("#fileUploadBtn").ligerButton({
        text: '文件上传',
        click: function () {
            $.ligerDialog.open({
                title: '上传文件',
                url: _basePath + '/file/fm_file_upload.html',
                width: 600,
                height: 300,
                buttons: [{
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.hide();
                    }
                }]
            });
        }
    });

    fileDownBtn = $("#fileDownBtn").ligerButton({
        text: '文件下载',
        click: function () {
            $.ligerDialog.open({
                title: '下载文件',
                url: _basePath + '/file/fm_file_download.html',
                width: 800,
                height: 500,
                buttons: [{
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.hide();
                    }
                }]
            });
        }
    });

    deleteBtn = $("#deleteBtn").ligerButton({
        text: '删除',
        click: function () {
            // 删除订单
            $.ligerDialog.confirm("确认删除？", function (yes) {
                if (yes) {
                    var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
                    setTimeout(function () {
                        manager.close();
                    }, 1000);
                    var array = new Array();
                    array.push(get_request_data());
                    $.ajax({
                        url: _basePath + '/om/deleteOrder',
                        data: JSON2.stringify(array),
                        type: 'POST',
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (resdata) {
                            if (resdata.success) {
                                $.ligerDialog.success("删除成功！");
                                window.top.f_removeTab("ORDER_DETAILS");
                                window.top.f_addTab("ORDER_CREATE", '新建订单', _basePath
                                    + "/order/om_order_create.html");
                            }
                        }
                    });
                    return true;
                } else {
                    return false;
                }
            });
        }
    });

    editBtn = $("#editBtn").ligerButton({
        text: '修改',
        click: function () {
            // 修改订单
            save_or_submit(false);
        }
    });

    saveBtn = $("#saveBtn").ligerButton({
        text: '保存',
        click: function () {
            // 保存订单
            save_or_submit(true);
        }
    });

}

/**
 * @param param 是否为空.
 * 初始化默认新增行.
 */
function add_default_rows(param) {
    // 代垫费用
    if (param == null) {
        // 运输商品
        itemGrid.addRow();
        // 车辆信息
        carGrid.addRow();

        for (var i = 0; i < disbursementData.length; i++) {
            disbursementGrid.addRow({
                type: disbursementData[i].value
            });
        }
    } else {
        if (param.length > 0) {
            for (var i = 0; i < disbursementData.length; i++) {

                for (var j = 0; j<param.length; j++) {
                    if (disbursementData[i].value == param[j].type) {
                        disbursementGrid.addRow({
                            disbursementId : param[j].disbursementId,
                            type: disbursementData[i].value,
                            amount : param[j].amount,
                            remark : param[j].remark,
                            salesOrderId : param.salesOrderId
                        });
                        break;
                    }
                    if (j+1 == param.length) {
                        disbursementGrid.addRow({
                            type: disbursementData[i].value
                        });
                    }
                }
            }
        }
    }
}

/**
 * 初始化订单页面.
 */
function init_order_page(parm) {
    if (parm) {// 订单详情
        $.getJSON(_basePath + "/om/getOrderDetails", parm, function (data) {
            orderForm.setData(data);
            customerForm.setData(data.customer);
            liger.get("customerId").setValue(data.customerId);
            liger.get("customerId").setText(data.customer.customerName);
            liger.get("customerTypeId").setValue(data.customerTypeId);
            liger.get("customerTypeId").setText(data.customer.customerType.businessType);

            customerForm.setData({
                "businessPrice": data.customer.customerType.businessPrice,
                "managerName": data.customer.customerType.managerName,
                "managerPhone": data.customer.customerType.managerPhone,
                "receiver": data.customer.customerType.receiver,
                "receiptLocation": data.customer.customerType.receiptLocation,
                "receivingContact": data.customer.customerType.receivingContact,
                "contactPhone": data.customer.customerType.contactPhone,
                "billBoardLocation": data.customer.customerType.billBoardLocation,
                "loadingLocation": data.customer.customerType.loadingLocation,
                "dischargeLocation": data.customer.customerType.dischargeLocation,
                "counterLocation": data.customer.customerType.counterLocation,
                "remark": data.customer.customerType.remark
            });

            var obj = {};
            obj.lines = data.itemInfoDtos;
            itemGrid.loadData(obj);
            obj.lines = data.lineCarDtos;
            carGrid.loadData(obj);

            add_default_rows(data.disbursementDtos);

            if ("COMP" == data.orderStatus) {
                // order
                liger.get("orderStatus").set({
                    'readonly': true
                });
                liger.get("shippingDate").set({
                    'readonly': true
                });
                liger.get("customerId").set({
                    'readonly': true
                });
                // car
                $("[toolbarid='om_car_add']").hide();
                $("[toolbarid='om_car_delete']").hide();

                $("#remark").attr("disabled", true);
                $("[toolbarid='om_item_delete']").hide();
                $("[toolbarid='om_item_add']").hide();
                deleteBtn.setDisabled(true);
                saveBtn.setDisabled(true);
            } else {
                editBtn.setDisabled(true);
            }
        });

    } else {// 订单创建
        liger.get('createDate').setValue(new Date());

        add_default_rows(null);

        fileUploadBtn.setDisabled(true);
        fileDownBtn.setDisabled(true);
        editBtn.setDisabled(true);
        deleteBtn.setDisabled(true);
    }
}

/**
 * 跳转到订单详情页.
 *
 * @param orderId
 *            订单主键Id
 * @param isReload
 *            是否是重新加载
 *
 */
function jump_order_detail(orderId) {
    // 移除Tab页
    window.top.f_removeTab("ORDER_DETAILS");
    window.top.f_addTab("ORDER_DETAILS", '订单详情', _basePath
        + "/order/om_order_create.html?salesOrderId=" + orderId);
}

/**
 * 获取页面值.
 *
 * @returns 页面参数值
 */
function get_request_data() {
    var orderData = BaseCommonUI.dateFormat(orderForm, "@YYYY@-@MM@-@DD@");
    orderData.customerId = customerForm.getData().customerId;
    orderData.customerTypeId = customerForm.getData().customerTypeId;
    orderData.lineCarDtos = carGrid.currentData.lines == undefined ? null
        : carGrid.currentData.lines;
    orderData.itemInfoDtos = itemGrid.currentData.lines == undefined ? null
        : itemGrid.currentData.lines;
    orderData.disbursementDtos = disbursementGrid.currentData.lines == undefined ? null
        : disbursementGrid.currentData.lines;

    return orderData;
}

/**
 * 数据校验.
 *
 * @param reqData
 *            请求数据
 */
function f_validatorData(reqData) {
    // 发货日期
    if (!reqData.shippingDate) {
        $.ligerDialog.warn("运输日期未填写!");
        return false;
    }
    if (!reqData.orderStatus) {
        $.ligerDialog.warn("订单状态未填写!");
        return false;
    }
    if (!reqData.customerId) {
        $.ligerDialog.warn("客户名称未填写!");
        return false;
    }
    if (!reqData.customerTypeId) {
        $.ligerDialog.warn("业务类型未填写!");
        return false;
    }
    // 商品行
    var itemData = reqData.itemInfoDtos;
    for (var i = 0; i < itemData.length; i++) {
        if ("delete" != itemData[i].__status) {
            if (!itemData[i].wayBillNo || !itemData[i].tankNo || !itemData[i].unNo
                || !itemData[i].tankType || !itemData[i].itemName
                || !itemData[i].hazardCategory) {
                $.ligerDialog.warn("运输商品信息中存在字段未填写!");
                return false;
            }
        }
    }
    return true;
}

/**
 * 保存或者提交请求.
 *
 * @param parm
 *            是否是保存
 */
function save_or_submit(parm) {
    var reqDate = get_request_data();
    if (!f_validatorData(reqDate)) {
        return false;
    }
    if (!parm) {
        reqDate.orderStatus = 'FDBK';
    }
    var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
    setTimeout(function () {
        manager.close();
    }, 1000);
    $.ajax({
        url: _basePath + "/om/saveOrder?isSubmit=" + parm,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON2.stringify(reqDate),
        success: function (resData) {
            if (resData.success) {
                window.top.f_removeTab("ORDER_CREATE");
                window.top.f_removeTab("ORDER_DETAILS");
                window.top.f_addTab("ORDER_DETAILS", '订单详情', _basePath
                    + "/order/om_order_create.html?salesOrderId=" + resData.objData.salesOrderId);
            } else {
                $.ligerDialog.error(resData.resMsg);
            }
        }
    });
}

/**
 * 删除订单.
 *
 * @param gridName
 * @param urlParm
 */
function delete_order(gridName, urlParm) {
    var data = gridName.getSelectedRows();
    if (data.length < 1)
        return;
    $.ligerDialog.confirm(("确认删除？"), function (yes) {
        if (yes) {
            var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
            setTimeout(function () {
                manager.close();
            }, 1000);
            $.ajax({
                url: urlParm,
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON2.stringify(data),
                success: function (resdata) {
                    if (resdata.success) {
                        $.ligerDialog.success("删除成功！");
                        gridName.reload();
                    }
                }
            });
        }
    });
}
