<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@ page import="utils.RequestUtils" %>
<%@ page import="dao.Note" %>
<%@ page import="query.execute.NodeTableExecute" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/6
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Note> noteArrayList = (ArrayList<Note>) session.getAttribute("notesList");
    Note note = RequestUtils.getParamToBean(request, Note.class);
    Random random = new Random();
    int i = random.nextInt(1000000);
    note.setNoteId(i);
    System.out.println(note);
    NodeTableExecute.insertNoteOne(note);
    noteArrayList.add(note);
    session.setAttribute("notesList", noteArrayList);
    response.sendRedirect("notes.jsp");
%>