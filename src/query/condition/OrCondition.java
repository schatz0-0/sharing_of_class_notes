package query.condition;

public class OrCondition extends QueryCondition {

    public OrCondition() {
    }

    @Override
    public String getSql() {
        return "or";
    }

    @Override
    public String getField() {
        return null;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean isParam() {
        return false;
    }
}
