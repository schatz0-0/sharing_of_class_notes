<%@ page import="dao.Note" %>
<%@ page import="utils.RequestUtils" %>
<%@ page import="query.execute.NodeTableExecute" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.SessionUtils" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/1
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Note note = SessionUtils.getParamToBean(request, Note.class);
    Integer userIdC = Integer.valueOf(session.getAttribute("userIdC") + "");
    System.out.println(note + "????????????????");
    NodeTableExecute.getNoteList(note);
    request.setAttribute("userId", userIdC);
    List<Note> notesList = null;
    if (userIdC == 4) {
        NodeTableExecute.updateNoteAll(!note.getShared(), note.getNoteId());
        notesList = NodeTableExecute.getNoteListAll();
    } else {
        NodeTableExecute.updateNoteById(userIdC, note.getNoteId(), !note.getShared());
        notesList = NodeTableExecute.getNoteListByAny(userIdC);
    }
    request.setAttribute("notesList", notesList);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("notes.jsp");
    requestDispatcher.forward(request, response);
%>