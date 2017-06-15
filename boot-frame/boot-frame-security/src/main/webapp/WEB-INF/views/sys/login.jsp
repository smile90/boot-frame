<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/resources/common/head.jsp" %>

    <link href="/static/lib/ui/admin/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <title>后台登录</title>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div class="loginBox">
        <form id="loginForm" class="form form-horizontal" action="/sys/login" method="post">
            <!-- TODO -->
            <div id="showMsg" class="row cl col-xs-12 text-c"></div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input name="username" type="text" placeholder="账户" class="input-text size-L"/>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input name="password" type="password" placeholder="密码" class="input-text size-L"/>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input class="input-text size-L" type="text" placeholder="验证码" style="width:150px;"/>
                    <img src=""/> <a id="kanbuq" href="javascript:;">看不清，换一张</a>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="online" id="online" value=""/>
                        使我保持登录状态
                    </label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"/>
                    <input name="" type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;"/>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="/resources/common/foot.jsp" %>

<script type="text/javascript" src="/static/lib/ui/admin/js/login.js"></script>
</body>
</html>