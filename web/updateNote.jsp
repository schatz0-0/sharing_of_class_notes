<%@ page import="query.execute.NodeTableExecute" %>
<%@ page import="dao.Note" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/6
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer noteId = Integer.valueOf(request.getParameter("noteId"));
    String content = request.getParameter("content");
    NodeTableExecute.updateNoteContent(content, noteId);
    ArrayList<Note> notesList = (ArrayList<Note>) session.getAttribute("notesList");
    for (Note note : notesList) {
        if (note.getNoteId() == noteId) note.setContent(content);
    }
    response.sendRedirect("notes.jsp");
%>