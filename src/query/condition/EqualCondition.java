package query.condition;

public class EqualCondition extends QueryCondition {

    private final String field;
    private final Object value;

    public EqualCondition(String filed, Object value) {
        this.field = filed;
        this.value = value;
    }

    @Override
    public String getSql() {
        return field + "=?";
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
