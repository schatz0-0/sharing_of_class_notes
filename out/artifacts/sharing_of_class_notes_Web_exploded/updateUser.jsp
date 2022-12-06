<%@ page import="utils.RequestUtils" %>
<%@ page import="dao.User" %>
<%@ page import="query.execute.UserTableExecute" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.SessionUtils" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/1
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    User user = RequestUtils.getParamToBean(request, User.class);
    UserTableExecute.updateUser(user);
    List<User> allUsers = UserTableExecute.getAllUsers();
    session.setAttribute("userList", allUsers);
    response.sendRedirect("userList.jsp");
%>