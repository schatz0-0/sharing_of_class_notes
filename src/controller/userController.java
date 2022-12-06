package controller;

import dao.Note;
import dao.User;
import query.execute.NodeTableExecute;
import query.execute.UserTableExecute;
import utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/index.do")
public class userController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // TODO Auto-generated method stub
        this.doPost(req, resp);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 1.设置编码
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        User user = RequestUtils.getParamToBean(req, User.class);
        System.out.println(user.getUsername());
        user = UserTableExecute.getUser(user);
        if (user == null) {
            resp.sendRedirect("index.jsp");
        } else {
            List<Note> notesList = null;
            if (user.getUserId() == 4) {
                notesList = NodeTableExecute.getNoteListAll();
            } else {
                notesList = NodeTableExecute.getNoteListByAny(user.getUserId());
            }
            session.setAttribute("notesList", notesList);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userIdC", user.getUserId());
            resp.sendRedirect("notes.jsp");
        }
        
    }
}
