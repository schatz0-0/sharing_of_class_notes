package dao;

public class Note {
    
    private Integer noteId;
    
    private Integer userId;
    
    private Boolean shared;
    
    private String content;
    
    public Note() {
    }
    
    public Note(Integer noteId, Integer userId, Boolean shared, String content) {
        this.noteId = noteId;
        this.userId = userId;
        this.shared = shared;
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Note{" + "noteId=" + noteId + ", userId=" + userId + ", shared=" + shared + ", content='" + content
                + '\'' + '}';
    }
    
    public Integer getNoteId() {
        return noteId;
    }
    
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Boolean getShared() {
        return shared;
    }
    
    public void setShared(Boolean shared) {
        this.shared = shared;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
