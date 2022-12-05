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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class noteController extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Note note = RequestUtils.getParamToBean(req, Note.class);
        Integer userIdC = Integer.valueOf(req.getParameter("userIdC"));
        NodeTableExecute.getNoteList(note);
        req.setAttribute("userId", userIdC);
        List<Note> notesList = null;
        if (userIdC == 4) {
            NodeTableExecute.updateNoteAll(!note.getShared(), note.getNoteId());
            notesList = NodeTableExecute.getNoteListAll();
        } else {
            NodeTableExecute.updateNoteById(userIdC, note.getNoteId(), !note.getShared());
            notesList = NodeTableExecute.getNoteListByAny(userIdC);
        }
        req.setAttribute("notesList", notesList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("notes.jsp");
        requestDispatcher.forward(req, resp);
    }
}
