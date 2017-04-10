/**
 * @description 扩展LigerUI 框架.
 * @Author Coundy.
 * @Date 2017/3/21 18:49
 */
!(function ($) {

    // 声明全局变量
    window.BaseCommonUI = {
        version: '1.0',
    };

    /**
     * 修改LigerUI返回数据接受参数名.
     *
     */
    $.extend($.ligerDefaults.Grid, {
        root: 'lines',
        record: 'total',
        pageSize: 10
    })

    /**
     * Gird中下拉款渲染.
     *
     */
    BaseCommonUI.gridSelectRender = function (rowdata, index, value, obj) {
        var v = value;
        $.each(obj.editor.data, function (i, n) {
            if (n.value == value) {
                v = n.text;
                return false;
            }
        });
        return v;
    };

    /**
     * 头行查询.
     *
     * @param form
     *            Form名称
     * @param grid
     *            Grid名称
     */
    BaseCommonUI.formToGridQuery = function (form, grid) {
        var data = form.getData();
        for (var key in data) {
            if (!data[key]) {
                delete data[key]
            } else if (data[key] instanceof Date) {
                data[key] = data[key].parseStr("@YYYY@-@MM@-@DD@");
            }
        }
        grid.set('parms', data);
        grid.loadData(1);
    };

    /**
     * 日期字段格式转换.
     *
     * @param form
     *            Form名字
     * @param format
     *            格式化样式
     */
    BaseCommonUI.dateFormat = function (form, format) {
        var data = form.getData();
        for (var key in data) {
            if (data[key] instanceof Date) {
                data[key] = data[key].parseStr(format);
            }
        }
        return data;
    };

    /**
     * 日期往前或往后多少天的推算获取.
     *
     * @param date
     *            计算日期
     * @param days
     *            天数.正数表示加上，负数表示减去
     * @param format
     *            转换后格式
     * @returns
     */
    BaseCommonUI.dateCalculation = function (date, days, format) {
        if (date instanceof Date) {
            date.setDate(date.getDate() + days);
            return date.parseStr(format);
        }
        return;
    }

    /**
     * 日期类型转换为字符串.
     *
     * @param format
     * @returns
     */
    var MONTHS = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG",
        "SEP", "OCT", "NOV", "DEC"];
    var WEEKs = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday"];
    var WEKs = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
    Date.prototype.parseStr = function (format) {
        var YYYY = String(this.getFullYear()); // 2011
        var YY = String(YYYY.substr(2)); // 11
        format = format.replaceAll("@YYYY@", YYYY);
        format = format.replaceAll("@YY@", YY);

        var M = this.getMonth() + 1;
        var MM = (M < 10) ? "0" + M : M;
        var MMM = MONTHS[M - 1];
        format = format.replaceAll("@MMM@", MMM);
        format = format.replaceAll("@MM@", MM);
        format = format.replaceAll("@M@", M);

        var D = this.getDate();
        var DD = (D < 10) ? "0" + D : D;
        format = format.replaceAll("@DD@", DD);
        format = format.replaceAll("@D@", D);

        var h = this.getHours();
        var hh = (h < 10) ? "0" + h : h;
        format = format.replaceAll("@hh@", hh);
        format = format.replaceAll("@h@", h);

        var m = this.getMinutes();
        var mm = (m < 10) ? "0" + m : m;
        format = format.replaceAll("@mm@", mm);
        format = format.replaceAll("@m@", m);

        var s = this.getSeconds();
        var ss = (s < 10) ? "0" + s : s;
        format = format.replaceAll("@ss@", ss);
        format = format.replaceAll("@s@", s);

        var dayOfWeek = this.getDay();
        format = format.replaceAll("@WEEK@", WEEKs[dayOfWeek]);
        format = format.replaceAll("@WEK@", WEKs[dayOfWeek]);
        return format;
    }

    String.prototype.replaceAll = function (s1, s2) {
        return this.replace(new RegExp(s1, "gm"), s2);
    }

    /**
     * 精度取值.
     * @param number 值
     * @param fractionDigits 精度
     * @returns {Number}
     */
    BaseCommonUI.roundPrecision = function (number, fractionDigits) {
        with (Math) {
            return round(number * pow(10, fractionDigits)) / pow(10, fractionDigits);
        }
    }

})(jQuery)