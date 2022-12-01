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
    ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${userList}" var="user" varStatus="vs">
    <tr>
        <td align="center">${user.noteId}</td>
        <td align="center">${note.userId}</td>
        <td align="center">${note.shared}</td>
        <td align="center">${note.content}</td>
        <a href="noteController.yc?noteId=${note.noteId}&shared=${note.shared}&userId=${note.userId}&userIdC=${userId}">
            是否隐藏 </a>
    </tr>
    <br/>
</c:forEach>
<a href="adminController.ac?userId=${userId}"> 管理员后台 </a>
</body>
</html>
