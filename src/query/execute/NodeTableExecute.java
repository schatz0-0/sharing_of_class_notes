package query.execute;

import dao.Note;
import query.LambdaQuery;
import query.TableExecute;

import java.util.List;


public class NodeTableExecute extends TableExecute<Note> {

    public static List<Note> getNoteList(Note note) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId)
                .eq(Note::getNoteId, note.getNoteId()).and()
                .eq(Note::getContent, note.getContent()).and()
                .eq(Note::getShared, note.getShared()).and()
                .eq(Note::getUserId, note.getUserId());
        NodeTableExecute execute = new NodeTableExecute();
        List<Note> notes = execute.selectList(query);
        return notes;
    }

    public static List<Note> getNoteListByUserId(Note note) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId)
                .eq(Note::getUserId, note.getUserId());
        NodeTableExecute execute = new NodeTableExecute();
        List<Note> notes = execute.selectList(query);
        return notes;
    }

    public static List<Note> getNoteListByAny(Integer userId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId)
                .eq(Note::getUserId, userId).or()
                .eq(Note::getShared, 1);
        NodeTableExecute execute = new NodeTableExecute();
        List<Note> notes = execute.selectList(query);
        return notes;
    }

    public static List<Note> getNoteListAll() {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId);
        NodeTableExecute execute = new NodeTableExecute();
        List<Note> notes = execute.selectList(query);
        return notes;
    }

    public static Integer updateNoteById(Integer userId, Integer noteId, Boolean flag) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getUserId, userId).and()
                .eq(Note::getNoteId, noteId).set(Note::getShared, flag);
        NodeTableExecute execute = new NodeTableExecute();
        return execute.update(query);
    }

    public static Integer updateNoteAll(Boolean flag, Integer noteId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getNoteId, noteId).set(Note::getShared, flag);
        NodeTableExecute execute = new NodeTableExecute();
        return execute.update(query);
    }

}
