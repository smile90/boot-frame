// 消息中心

// 头部快捷菜单

// 显示主菜单
showMenu();

// 主菜单
function showMenu() {
    $.ajax({
        url: ctxPath + "/sys/menu",
        dataType: "text",
        async: false,
        success: function (dataStr, textStatus) {
            // 数据、请求状态
            if (dataStr == null || dataStr == "") {
                return;
            }
            var data = $.parseJSON(dataStr);
            $.each(data, function (index, domEle) {
                appendMenu(domEle);
                if (domEle.children != null) {
                    $.each(domEle.children, function (childrenIndex, childrenDomEle) {
                        appendMenu(childrenDomEle);
                    });
                }
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("[" + XMLHttpRequest.status + "][" + textStatus + "][" + errorThrown + "]", {icon: 2});
        }
    });
}

// 生成菜单
function appendMenu(/** JSON */domEle) {
    var icon = (domEle.icon == null ? "" : domEle.icon);
    var name = (domEle.name == null ? "" : domEle.name);
    var url = (domEle.url == null ? "#" : domEle.url);
    if (domEle.parentCode == null || domEle.parentCode == "" || domEle.parentCode == "ROOT") {
        $("#menu-show").append(
            "<dl id=\"" + domEle.code + "\">" +
            "<dt><i class=\"Hui-iconfont\">" + icon + "</i> " + name + "<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>" +
            "<dd><ul></ul></dd>" +
            "</dl>");
    } else {
        $("#" + domEle.parentCode + " dd ul").append("<li><a data-href=\"" + url + "\" data-title=\"" + name + "\" href=\"javascript:void(0)\"><i class=\"Hui-iconfont\">" + icon + "</i> " + name + "</a></li>");
    }
}