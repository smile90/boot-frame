$(function () {
    // 消息中心

    // 头部快捷菜单

    // 主菜单
    $.ajax({
        url: ctxPath + "/sys/menu",
        dataType: "JSON",
        success: function (data, textStatus) {
            // 数据、请求状态
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("[" + XMLHttpRequest.status + "][" + textStatus + "][" + errorThrown + "]", {icon: 2});
        }
    });
});