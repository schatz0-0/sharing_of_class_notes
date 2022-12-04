<%@ page import="query.execute.NodeTableExecute" %>
<%@ page import="dao.Note" %>
<%@ page import="utils.RequestUtils" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/1
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    Integer id = Integer.valueOf(request.getParameter("id"));
    NodeTableExecute.deleteUserById(id);
    Note note = RequestUtils.getParamToBean(request, Note.class);
    Integer userIdC = Integer.valueOf(request.getParameter("userIdC"));
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