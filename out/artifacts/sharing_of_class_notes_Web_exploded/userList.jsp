<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.Note" %>
<%@ page import="dao.User" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/11/26
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<User> userList = (ArrayList<User>) session.getAttribute("userList");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${userList}" var="user" varStatus="vs">
    <form action="updateUser.jsp">
        <input type="text" hidden="hidden" name="userId" value="${user.userId}">
        账号：
        <td style="width: 20%"><input type="text" name="username" value="${user.username}"></td>
        密码：
        <td style="width: 20%"><input type="text" name="password" value="${user.password}"></td>
        <td style="width: 20%"><input type="button" value="删除" onclick="del(${user.userId})"></td>
        <td style="width: 20%"><input type="submit" value="更新"></td>
    </form>
    <br/>
</c:forEach>
<%="==============添加账号==================="
%>
<form action="insertUser.jsp" method="post">
    账号：<input type="text" name="username">
    密码：<input type="text" name="password">
    <input type="submit" value="增加用戶"><br>
</form>
</body>
</html>
<script>
    function del(x) {
        window.location.href = "deleteUser.jsp?id=" + x;
    }
</script>