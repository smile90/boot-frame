$(function () {
    // 消息中心

    // 头部快捷菜单

    // 主菜单
    $.ajax({
        url: ctxPath + "/sys/menu",
        dataType: "JSON",
        async: false,
        success: function (data, textStatus) {
            // 数据、请求状态
            $.each(data, function (index, domEle) {
                if (domEle.parentCode == null || domEle.parentCode == "") {
                    $("#menu-show").append(
                        "<dl id=\"" + domEle.code + "\">" +
                        "<dt><i class=\"Hui-iconfont\">" + domEle.icon + "</i> " + domEle.name + "<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>" +
                        "<dd><ul></ul></dd>" +
                        "</dl>");
                } else {
                    $("#" + domEle.parentCode + " dd ul").append("<li><a data-href=\"" + domEle.url + "\" data-title=\"" + domEle.name + "\" href=\"javascript:void(0)\">" + domEle.name + "</a></li>");
                }
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("[" + XMLHttpRequest.status + "][" + textStatus + "][" + errorThrown + "]", {icon: 2});
        }
    });
});