$(function () {
    // 消息中心

    // 头部快捷菜单

    // 主菜单
    $.ajax({
        url: ctxPath + "/sys/menu",
        dataType: "JSON",
        success: function (data, textStatus) {
            // 数据、请求状态
            $.each(data, function (index, domEle) {
                $("#menu-show").append(
                      "<dl id=\"" + domEle.code + "\">" +
                        "<dt><i class=\"Hui-iconfont\">" + domEle.icon + "</i> " + domEle.name + "<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>" +
                        "<dd>" +
                        "<ul>" +
                            "<li><a data-href=\"" + domEle.url + "\" data-title=\"" + domEle.name + "\" href=\"javascript:void(0)\">" + domEle.name + "</a></li>" +
                        "</ul>" +
                        "</dd>" +
                      "</dl>");
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("[" + XMLHttpRequest.status + "][" + textStatus + "][" + errorThrown + "]", {icon: 2});
        }
    });
});