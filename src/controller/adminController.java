package controller;


import dao.Note;
import utils.JdbcUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userId = req.getParameter("user_id");

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            if("4".equals(userId)) {

            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("notes.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
    }
}
