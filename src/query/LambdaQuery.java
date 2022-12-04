package query;

import dao.Note;
import query.condition.*;
import utils.FiledUtils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaQuery<T> implements Query<T> {
    /**
     * 大写字母正则.
     */


    /**
     * 查询字段.
     */
    private final List<String> columnList = new ArrayList<>();

    /**
     * 更新字段
     */
    private final List<QueryCondition> updateList = new ArrayList<>();

    /**
     * 条件集合.
     */
    private final List<QueryCondition> conditionList = new ArrayList<>();


    /**
     * 设置查询字段.
     *
     * @param columns 查询字段集合
     * @return 条件构造器
     */
    @SafeVarargs
    public final LambdaQuery<T> select(SFunction<T, ?>... columns) {
        this.columnList.addAll(Arrays.stream(columns).map(this::columnToString).collect(Collectors.toList()));
        return this;
    }

    /**
     * 相等.
     *
     * @param column 列
     * @param value  值
     * @return 条件构造器
     */
    public LambdaQuery<T> eq(SFunction<T, ?> column, Object value) {
        QueryCondition condition = new EqualCondition(columnToString(column), value);
        conditionList.add(condition);
        return this;
    }

    /**
     * 相等.
     *
     * @param column 列
     * @param value  值
     * @return 条件构造器
     */
    public LambdaQuery<T> ne(SFunction<T, ?> column, Object value) {
        QueryCondition condition = new NotEqualCondition(columnToString(column), value);
        conditionList.add(condition);
        return this;
    }

    /**
     * 或者.
     *
     * @return 条件构造器
     */
    public LambdaQuery<T> or() {
        QueryCondition condition = new OrCondition();
        conditionList.add(condition);
        return this;
    }

    /**
     * 并且.
     *
     * @return 条件构造器
     */
    public LambdaQuery<T> and() {
        QueryCondition condition = new AndCondition();
        conditionList.add(condition);
        return this;
    }

    /**
     * set.
     *
     * @return 条件构造器
     */
    public LambdaQuery<T> set(SFunction<T, ?> column, Object value) {
        QueryCondition condition = new EqualCondition(columnToString(column), value);
        updateList.add(condition);
        return this;
    }


    /**
     * 列转数据库字段.
     *
     * @param column 列
     * @return 字段
     */
    public final String columnToString(SFunction<T, ?> column) {
        try {
            Class<?> clazz = column.getClass();
            Method method = clazz.getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) method.invoke(column);
            String implMethodName = lambda.getImplMethodName();
            return FiledUtils.toUnderline(implMethodName.substring("get".length()));
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public String getSqlSelect() {
        return String.join(",", this.columnList);
    }

    @Override
    public String getSqlCondition() {
        if (conditionList.isEmpty()) {
            return "";
        }
        return "where " + this.conditionList.stream().map(QueryCondition::getSql).collect(Collectors.joining(" "));
    }

    @Override
    public String getSqlUpdate() {
        if (updateList.isEmpty()) {
            return "";
        }
        return "set " + this.updateList.stream().map(QueryCondition::getSql).collect(Collectors.joining(","));
    }

    @Override
    public List<QueryCondition> getConditionList() {
        return this.conditionList;
    }

    @Override
    public List<String> getColumnList() {
        return columnList;
    }

    @Override
    public List<QueryCondition> getSqlUpdateList() {
        return updateList;
    }

    public static void main(String[] args) {
        LambdaQuery<Note> query = new LambdaQuery<>();
        query.select(Note::getNoteId, Note::getContent, Note::getShared, Note::getUserId).eq(Note::getNoteId, 1);
        System.out.println(query.getSqlSelect());
        System.out.println(query.getSqlCondition());
    }
}
