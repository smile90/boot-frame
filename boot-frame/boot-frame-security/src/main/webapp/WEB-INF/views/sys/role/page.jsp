<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/resources/common/taglib.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/resources/common/meta.jsp" %>

    <title>管理后台</title>
</head>

<body>
查看是否有权限！
<ul>
    <shiro:hasPermission name="sys_role_add"><a href="${ctxPath}/sys/role/add"></a></shiro:hasPermission>
    <shiro:hasPermission name="sys_role_delete"><li>删除</li></shiro:hasPermission>
    <shiro:hasPermission name="sys_role_update"><li>修改</li></shiro:hasPermission>
    <shiro:hasPermission name="sys_role_query"><li>查询</li></shiro:hasPermission>
</ul>

<%@ include file="/resources/common/footer.jsp" %>
<script type="text/javascript" src="${ctxPath}/static/lib/ui/admin/js/index.js"></script>

</body>
</html>