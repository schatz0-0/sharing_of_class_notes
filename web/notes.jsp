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
    ArrayList<Note> notesList = (ArrayList<Note>) request.getAttribute("notesList");
    Integer userId = (Integer) request.getAttribute("userId");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${notesList}" var="note" varStatus="vs">
    <form action="noteList.jsp">
        <input type="text" hidden="hidden" name="userId" value="${note.noteId}">
        用户id：
        <td style="width: 20%"><input type="text" name="username" value="${note.userId}"></td>
        是否分享：
        <td style="width: 20%">${note.shared}</td>
        日志内容：
        <td style="width: 20%"><input type="text" name="password" value="${note.content}"></td>
            <%--        <td style="width: 20%"><input type="button" value="删除" onclick="del(${note.noteId})"></td>--%>
            <%--        <td style="width: 20%"><input type="submit" value="更新"></td>--%>
        <a href="noteController.yc?noteId=${note.noteId}&shared=${note.shared}&userId=${note.userId}&userIdC=${userId}">
            是否隐藏 </a>
    </form>
    <br/>
</c:forEach>
<a href="adminController.ac?userId=${userId}"> 管理员后台 </a>
</body>
</html>
<script>
    function del(x) {
        window.location.href = "deleteNote.jsp?id=" + x;
    }
</script>