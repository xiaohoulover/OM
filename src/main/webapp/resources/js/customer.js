/**
 * @Description 客户管理Js文件.
 * @Author Coundy.
 * @Date 2017/3/21 21:31
 * @version 1.0
 */

var saveBtn, deleteBtn;
function init_btns() {
    saveBtn = $("#saveBtn").ligerButton({
        text: '保存',
        click: function () {
            var reqData = customerForm.getData();
            reqData.customerTypeList = customerGrid.currentData.lines  == undefined ? null : customerGrid.currentData.lines;
            //校验
            if (!reqData.customerName) {
                $.ligerDialog.warn("客户名称未填写!");
                return false;
            }
            if (null == reqData.customerTypeList) {
                $.ligerDialog.warn("业务类型信息不能为空!");
                return false;
            }
            $.ligerDialog.confirm(("确认保存？"), function (yes) {
                if (yes) {
                    var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
                    setTimeout(function () {
                        manager.close();
                    }, 1000);
                    $.ajax({
                        url: _basePath + "/customer/saveCustomer",
                        type: 'POST',
                        dataType: 'json',
                        contentType: 'application/json',
                        data: JSON2.stringify(reqData),
                        success: function (resData) {
                            if (resData.success) {
                                window.top.f_removeTab("CUSTOMER_CREATE");
                                window.top.f_removeTab("CUSTOMER_DETAILS");
                                window.top.f_addTab("CUSTOMER_DETAILS", '客户详情', _basePath
                                    + "/customer/cm_customer_create.html?customerId=" + resData.objData.customerId);
                                /*window.location = _basePath
                                 + "/customer/cm_customer_create.html?customerId="+resData.objData.customerId;*/
                            } else {
                                $.ligerDialog.error(resData.resMsg);
                            }
                        },
                        error: function () {
                            $.ligerDialog.error("Error");
                        }
                    });
                }
            });
        }
    });

    deleteBtn = $("#deleteBtn").ligerButton({
        text: '删除',
        click: function () {
            var reqData = customerForm.getData();
            $.ligerDialog.confirm(("确认删除？"), function (yes) {
                if (yes) {
                    var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
                    setTimeout(function () {
                        manager.close();
                    }, 1000);
                    $.ajax({
                        url: _basePath + "/customer/deleteCustomer?customerId="+reqData.customerId,
                        type: 'POST',
                        dataType: 'json',
                        contentType: 'application/json',
                        data: JSON2.stringify(reqData),
                        success: function (resData) {
                            if (resData.success) {
                                window.top.f_removeTab("CUSTOMER_CREATE");
                                window.top.f_removeTab("CUSTOMER_DETAILS");
                                window.top.f_addTab("CUSTOMER_CREATE", '新建客户', _basePath
                                    + "/customer/cm_customer_create.html?");
                                /*window.location = _basePath
                                 + "/customer/cm_customer_create.html?customerId="+resData.objData.customerId;*/
                            } else {
                                $.ligerDialog.error(resData.resMsg);
                            }
                        },
                        error: function () {
                            $.ligerDialog.error("Error");
                        }
                    });
                }
            });
        }
    });
}

/**
 * 获取Customer信息，加载页面数据.
 * @param parm
 */
function load_data(parm) {
    if (parm) {
        $.getJSON(_basePath + "/customer/getCustomerDetails", parm, function (resdata) {
            customerForm.setData(resdata);
            var obj = {};
            obj.lines = resdata.customerTypeList;
            customerGrid.loadData(obj);
        });

        deleteBtn.setEnabled(true);
    } else {
        deleteBtn.setDisabled(true);
    }
}

/**
 * 跳转到客户详情页.
 * @param customerId
 */
function jump_customer_detail(customerId) {
    // 移除Tab页
    window.top.f_removeTab("CUSTOMER_DETAILS");
    window.top.f_addTab("CUSTOMER_DETAILS", '客户详情', _basePath
        + "/customer/cm_customer_create.html?customerId=" + customerId);
}

/**
 * 删除客户类型信息.
 * @param gridName
 * @param urlParm
 */
function delete_customerType(gridName, urlParm) {
    var data = gridName.getSelectedRows();
    if (data.length < 1)
        return;
    $.ligerDialog.confirm(("确认删除？"), function(yes) {
        if (yes) {
            var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
            setTimeout(function () {
                manager.close();
            }, 1000);
            $.ajax({
                url : urlParm,
                type : 'POST',
                dataType : 'json',
                contentType : 'application/json',
                data : JSON2.stringify(data),
                success : function(resdata) {
                    if (resdata.success) {
                        $.ligerDialog.success("删除成功！");
                        gridName.reload();
                    }
                },
                error : function () {
                    $.ligerDialog.error('error');
                }
            });
        }
    });
}