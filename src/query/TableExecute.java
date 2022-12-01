package query;

import query.condition.QueryCondition;
import utils.FiledUtils;
import utils.JdbcUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class TableExecute<T> {

    public TableExecute() {
    }

    private String getTableName() {
        return FiledUtils.toUnderline(getClazz().getSimpleName());
    }

    public T selectOne(Query<T> query) {
        List<T> resultList = selectList(query);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    public List<T> selectList(Query<T> query) {
        List<T> resultList = new ArrayList<>();
        String sqlSelect = "select " + query.getSqlSelect() + " from " + getTableName();
        String sqlCondition = query.getSqlCondition();
        String sql = String.join(" ", sqlSelect, sqlCondition);
        System.out.println(sql);
        List<QueryCondition> conditionList = query.getConditionList();
        List<String> columnList = query.getColumnList();
        try {
            Connection connection = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setCondition(preparedStatement, conditionList);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(getResultBean(resultSet, columnList));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return resultList;
    }

    public Integer update(Query<T> query) {
        String sqlSelect = "update " + getTableName();
        String sqlUpdate = query.getSqlUpdate();
        String sqlCondition = query.getSqlCondition();
        String sql = String.join(" ", sqlSelect, sqlUpdate);
        sql = String.join(" ", sql, sqlCondition);
        System.out.println(sql);
        List<QueryCondition> UpdateList = query.getSqlUpdateList();
        List<QueryCondition> conditionList = query.getConditionList();
        try {
            Connection connection = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setUpdate(preparedStatement, conditionList, UpdateList);
            return preparedStatement.executeUpdate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    public Integer delete(Query<T> query) {
        String sqlSelect = "delete from " + getTableName();
        String sqlCondition = query.getSqlCondition();
        String sql = String.join(" ", sqlSelect, sqlCondition);
        System.out.println(sql);
        List<QueryCondition> conditionList = query.getConditionList();
        try {
            Connection connection = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setCondition(preparedStatement, conditionList);
            return preparedStatement.executeUpdate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    public boolean save(T t) {
        return saveBatch(Collections.singletonList(t)) == 1;
    }

    public Integer saveBatch(Collection<T> collection) {
        String sqlSelect = "insert into " + getTableName();
        Class<T> clazz = getClazz();
        Map<String, Field> fieldMap = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(field -> FiledUtils.toUnderline(field.getName()), field -> field,
                        (last, curr) -> curr, LinkedHashMap::new));
        Set<String> fieldList = fieldMap.keySet();
        String fields = String.join(",", fieldList);
        String params = fieldList.stream().map(f -> "?").collect(Collectors.joining(","));
        String sql = String.join(" ", sqlSelect, "(", fields, ")", "values", "(", params, ")");
        System.out.println(sql);
        PreparedStatement preparedStatement;
        try {
            Connection connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (T t : collection) {
                setValue(preparedStatement, t, fieldMap);
                preparedStatement.addBatch();
            }
            return preparedStatement.executeBatch().length;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    private void setValue(PreparedStatement preparedStatement, T t, Map<String, Field> fieldMap) throws Exception {
        Set<String> fieldList = fieldMap.keySet();
        int i = 0;
        for (String field : fieldList) {
            i++;
            Field currentField = fieldMap.get(field);
            currentField.setAccessible(true);
            preparedStatement.setObject(i, currentField.get(t));
            currentField.setAccessible(false);
        }
    }


    private T getResultBean(ResultSet resultSet, List<String> columnList) throws InstantiationException, IllegalAccessException, SQLException {
        Class<T> clazz = getClazz();
        Map<String, Field> fieldMap = Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(field -> FiledUtils.toUnderline(field.getName()), field -> field));
        T t = clazz.newInstance();
        for (String column : columnList) {
            Field field = fieldMap.get(column);
            Object value = resultSet.getObject(column);
            field.setAccessible(true);
            field.set(t, value);
            field.setAccessible(false);
        }
        return t;
    }

    private void setUpdate(PreparedStatement preparedStatement, List<QueryCondition> conditionList, List<QueryCondition> updateList) throws SQLException {
        for (int i = 0; i < updateList.size(); i++) {
            QueryCondition condition = updateList.get(i);
            preparedStatement.setObject(i + 1, condition.getValue());
        }
        List<QueryCondition> paramList = conditionList.stream().filter(QueryCondition::isParam).collect(Collectors.toList());
        for (int i = 0; i < paramList.size(); i++) {
            QueryCondition condition = paramList.get(i);
            if (condition.isParam()) {
                preparedStatement.setObject(updateList.size() + i + 1, condition.getValue());
            }
        }
    }

    private void setCondition(PreparedStatement preparedStatement, List<QueryCondition> conditionList) throws SQLException {
        List<QueryCondition> paramList = conditionList.stream().filter(QueryCondition::isParam).collect(Collectors.toList());
        for (int i = 0; i < paramList.size(); i++) {
            QueryCondition condition = paramList.get(i);
            if (condition.isParam()) {
                preparedStatement.setObject(i + 1, condition.getValue());
            }
        }
    }

    private Class<T> getClazz() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        return (Class<T>) actualTypeArguments[0];
    }

}
