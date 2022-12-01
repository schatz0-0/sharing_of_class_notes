package controller;

import dao.Note;
import query.execute.NodeTableExecute;
import utils.RequestUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class noteController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Note note = RequestUtils.getParamToBean(req, Note.class);
        NodeTableExecute.getNoteList(note);
        req.setAttribute("userId", note.getUserId());
        List<Note> notesList = null;
        if ("4".equals(note.getUserId())) {
            notesList = NodeTableExecute.getNoteListAll();
        } else {
            notesList = NodeTableExecute.getNoteListByUserId(note);
        }
        req.setAttribute("notesList", notesList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("notes.jsp");
        requestDispatcher.forward(req, resp);
    }
}
