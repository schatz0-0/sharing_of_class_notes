<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.Note" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/11/26
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Note> notesList = (ArrayList<Note>) session.getAttribute("notesList");
    Integer userId = (Integer) session.getAttribute("userId");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${notesList}" var="note" varStatus="vs">
    <form action="updateNote.jsp">
        <input type="text" hidden="hidden" name="noteId" value="${note.noteId}">
        用户id：${note.userId}
        是否分享：
        <td style="width: 20%">${note.shared}</td>
        日志内容：
        <td style="width: 20%"><input type="text" name="content" value="${note.content}"></td>
            <%--        <td style="width: 20%"><input type="button" value="删除" onclick="del(${note.noteId})"></td>--%>
            <%--        <td style="width: 20%"><input type="submit" value="更新"></td>--%>
        <a href="noteController.yc?noteId=${note.noteId}&shared=${note.shared}&userId=${note.userId}&userIdC=${userId}">
            是否隐藏 </a>
        <input type="submit" value="修改内容"><br>
    </form>
    <br/>
</c:forEach>

<form action="addNote.jsp" method="post">
    <input type="text" hidden="hidden" name="userId" value="${userId}">
    <textarea id="content" rows="10" cols="30" name="content"></textarea><br>
    <input type="radio" name="shared" value="0" checked>不共享
    <input type="radio" name="shared" value="1">共享<br>
    <input type="submit" value="发布笔记"><br>
</form>
<a href="adminController.ac?userId=${userId}"> 管理员后台 </a>
</body>
</html>
<script>
    function del(x) {
        window.location.href = "deleteNote.jsp?id=" + x;
    }
</script>