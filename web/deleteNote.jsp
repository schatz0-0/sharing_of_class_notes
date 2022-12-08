<%@ page import="query.execute.NodeTableExecute" %>
<%@ page import="dao.Note" %>
<%@ page import="utils.RequestUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="query.execute.UserTableExecute" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/1
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer noteId = Integer.valueOf(request.getParameter("noteId"));
    Integer userIdC = Integer.valueOf(request.getParameter("userIdC"));
    Integer userId = Integer.valueOf(request.getParameter("userId"));
    System.out.println(noteId);
    NodeTableExecute.deleteUserById(noteId, userIdC);
    List<Note> notesList = null;
    if (userIdC == 4) {
        notesList = NodeTableExecute.getNoteListAll();
    } else {
        notesList = NodeTableExecute.getNoteListByAny(userId);
    }
    session.setAttribute("notesList", notesList);
    response.sendRedirect("notes.jsp");
%>