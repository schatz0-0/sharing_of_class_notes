<%@ page import="query.execute.UserTableExecute" %>
<%@ page import="dao.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/1
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    Integer id = Integer.valueOf(request.getParameter("id"));
    UserTableExecute.deleteUserById(id);
    List<User> allUsers = UserTableExecute.getAllUsers();
    request.setAttribute("userList", allUsers);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("userList.jsp");
    requestDispatcher.forward(request, response);
%>
