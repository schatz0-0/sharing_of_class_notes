package query.condition;

public class AndCondition extends QueryCondition {

    public AndCondition() {
    }

    @Override
    public String getSql() {
        return "and";
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
