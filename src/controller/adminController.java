package controller;


import dao.User;
import query.execute.UserTableExecute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class adminController extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(req.getParameter("userId"));
        if (userId == 4) {
            List<User> allUsers = UserTableExecute.getAllUsers();
            session.setAttribute("userList", allUsers);
            resp.sendRedirect("userList.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
