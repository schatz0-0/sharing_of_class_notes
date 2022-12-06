package query.condition;

public abstract class QueryCondition {
    
    public abstract String getField();
    
    public abstract Object getValue();
    
    public abstract String getSql();
    
    public boolean isParam() {
        return true;
    }
}
