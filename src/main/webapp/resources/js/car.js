/**
 * Created by Coundy on 2017/3/24.
 */

/**
 * 初始化buttons;
 */
function init_btns() {
    var saveBtn = $("#saveBtn").ligerButton({
        text : '保存',
        click : function() {
            var reqData = carForm.getData();
            reqData.carProjectDtoList = carProjectGrid.currentData.lines == undefined ? null : carProjectGrid.currentData.lines;
            // 校验
            if (!reqData.plateNo) {
                $.ligerDialog.warn("车辆编号未填写!");
                return false;
            }
            if (!reqData.type) {
                $.ligerDialog.warn("车辆类型未填写!");
                return false;
            }

            var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
            setTimeout(function () {
                manager.close();
            }, 1000);
            $.ajax({
                url : _basePath + '/car/saveCar',
                data : JSON2.stringify(reqData),
                type : 'POST',
                dataType : 'json',
                contentType : 'application/json',
                success : function(resData) {
                    if (resData.success) {
                        window.top.f_removeTab("CAR_CREATE");
                        window.top.f_removeTab("CAR_DETAILS");
                        window.top.f_addTab("CAR_DETAILS", '车辆详情', _basePath
                            + "/car/cm_car_create.html?carId=" + resData.objData.carId);
                        /*window.location = _basePath
                         + "/car/cm_car_create.html?carId="+resData.objData.carId;*/
                    }
                },
                error : function() {
                    $.ligerDialog.error("Error");
                }
            });
            return true;
        }
    });
}

//删除
function delete_car(grid, url) {
    var data = grid.getSelectedRows();
    if (data.length < 1)
        return;
    $.ligerDialog.confirm(("确认删除？"), function(yes) {
        if (yes) {
            var manager = $.ligerDialog.waitting('正在处理中,请稍候...');
            setTimeout(function () {
                manager.close();
            }, 1000);
            $.ajax({
                url : url,
                data : JSON2.stringify(data),
                type : 'POST',
                dataType : 'json',
                contentType : 'application/json',
                success : function (resData) {
                    if (resData.success) {
                        $.ligerDialog.success("删除成功！");
                        grid.reload();
                    }
                }
            });
        }
    });
}

//跳转到详情页
function jump_car_detail(carId) {
    // 移除Tab页
    window.top.f_removeTab("CAR_DETAILS");
    // 跳转到详情页
    window.top.f_addTab("CAR_DETAILS", '车辆详情', _basePath
        + "/car/cm_car_create.html?carId=" + carId);
}