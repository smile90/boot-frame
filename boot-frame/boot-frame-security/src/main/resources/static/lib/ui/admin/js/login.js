$(function() {
    // 注册登录方法
    $("#loginForm").ajaxForm({
        success: function(data, statusText) {
            $("#showMsg").text(data.showMsg);
            return false;
        },
        error: function (data) {
            $("#showMsg").text(data.showMsg);
            return false;
        }
    });
});