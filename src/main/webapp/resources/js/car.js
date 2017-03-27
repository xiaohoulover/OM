/**
 * Created by Coundy on 2017/3/24.
 */

//删除
function delete_car(grid, url) {
    var data = grid.getSelectedRows();
    if (data.length < 1)
        return;
    $.ligerDialog.confirm(("确认删除？"), function(yes) {
        if (yes) {
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