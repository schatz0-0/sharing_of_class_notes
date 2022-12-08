package query.execute;

import dao.Note;
import query.LambdaQuery;
import query.TableExecute;
import utils.BeanUtils;

import java.util.List;


public class NodeTableExecute extends TableExecute<Note> {
    
    public static NodeTableExecute getInstance() {
        return BeanUtils.getSingleTon(NodeTableExecute.class, obj -> new NodeTableExecute());
    }
    
    public static List<Note> getNoteList(Note note) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId)
                .eq(Note::getNoteId, note.getNoteId()).and().eq(Note::getContent, note.getContent()).and()
                .eq(Note::getShared, note.getShared()).and().eq(Note::getUserId, note.getUserId());
        List<Note> notes = getInstance().selectList(query);
        return notes;
    }
    
    public static List<Note> getNoteListByUserId(Note note) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId)
                .eq(Note::getUserId, note.getUserId());
        List<Note> notes = getInstance().selectList(query);
        return notes;
    }
    
    public static List<Note> getNoteListByAny(Integer userId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getUserId, userId)
                .or().eq(Note::getShared, 1);
        List<Note> notes = getInstance().selectList(query);
        return notes;
    }
    
    public static List<Note> getNoteListAll() {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId);
        List<Note> notes = getInstance().selectList(query);
        return notes;
    }
    
    public static Integer updateNoteById(Integer userId, Integer noteId, Boolean flag) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getUserId, userId)
                .and().eq(Note::getNoteId, noteId).set(Note::getShared, flag);
        return getInstance().update(query);
    }
    
    public static Integer updateNoteAll(Boolean flag, Integer noteId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getNoteId, noteId)
                .set(Note::getShared, flag);
        return getInstance().update(query);
    }
    
    public static Integer updateNoteContent(String content, Integer noteId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.eq(Note::getNoteId, noteId).set(Note::getContent, content);
        return getInstance().update(query);
    }
    
    public static Integer deleteUserById(Integer id, Integer userId) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        if(userId == 4) {
            query.eq(Note::getNoteId, id);
        } else {
            query.eq(Note::getNoteId, id).and().eq(Note::getUserId, userId);
        }
        return getInstance().delete(query);
    }
    
    public static Boolean insertNoteOne(Note note) {
        return getInstance().save(note);
    }
}
