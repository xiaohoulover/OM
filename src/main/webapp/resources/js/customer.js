/**
 * @Description 客户管理Js文件.
 * @Author Coundy.
 * @Date 2017/3/21 21:31
 * @version 1.0
 */

var saveBtn;
function init_btns() {
    saveBtn = $("#saveBtn").ligerButton({
        text: '保存',
        click: function () {
            $.ligerDialog.waitting("正在保存中,请稍候...");
            var reqDate = customerForm.getData();
            $.ajax({
                url: _basePath + "/customer/saveCustomer",
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON2.stringify(reqDate),
                success: function (resData) {
                    if (resData.success) {
                        window.location = _basePath
                            + "/customer/cm_customer_create.html?customerId="+resData.objData.customerId;
                    } else {
                        $.ligerDialog.error(resData.resMsg);
                    }
                    $.ligerDialog.closeWaitting();
                },
                error: function () {
                    $.ligerDialog.closeWaitting();
                    $.ligerDialog.error("error");
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
    $.getJSON(_basePath + "/customer/getCustomerDetails", parm, function (resdata) {
        customerForm.setData(resdata.objData);
    });
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
 * 删除客户信息.
 * @param gridName
 * @param urlParm
 */
function delete_customer(gridName, urlParm) {
    var data = gridName.getSelectedRows();
    if (data.length < 1)
        return;
    $.ligerDialog.confirm(("确认删除？"), function(yes) {
        if (yes) {
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


