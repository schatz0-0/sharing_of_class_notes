package controller;

import dao.Note;
import query.execute.NodeTableExecute;
import utils.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class noteController extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Note note = RequestUtils.getParamToBean(req, Note.class);
        HttpSession session = req.getSession();
        Integer userIdC = Integer.valueOf(req.getParameter("userIdC"));
        NodeTableExecute.getNoteList(note);
        session.setAttribute("userId", userIdC);
        List<Note> notesList = null;
        if (userIdC == 4) {
            NodeTableExecute.updateNoteAll(!note.getShared(), note.getNoteId());
            notesList = NodeTableExecute.getNoteListAll();
        } else {
            NodeTableExecute.updateNoteById(userIdC, note.getNoteId(), !note.getShared());
            notesList = NodeTableExecute.getNoteListByAny(userIdC);
        }
        session.setAttribute("notesList", notesList);
        resp.sendRedirect("notes.jsp");
    }
}
