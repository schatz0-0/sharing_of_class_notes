<%@ page import="query.execute.UserTableExecute" %>
<%@ page import="utils.RequestUtils" %>
<%@ page import="dao.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2022/12/6
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<User> userList = (ArrayList<User>) session.getAttribute("userList");
    User user = RequestUtils.getParamToBean(request, User.class);
    UserTableExecute.insertUser(user);
    userList.add(user);
    session.setAttribute("userList", userList);
    response.sendRedirect("userList.jsp");
%>