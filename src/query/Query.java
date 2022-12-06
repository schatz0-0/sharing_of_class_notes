package query;

import query.condition.QueryCondition;

import java.util.List;

public interface Query<T> {
    
    /**
     * 获取查询的sql语句.
     *
     * @return 查询字段
     */
    String getSqlSelect();
    
    String getSqlCondition();
    
    String getSqlUpdate();
    
    List<QueryCondition> getConditionList();
    
    List<String> getColumnList();
    
    List<QueryCondition> getSqlUpdateList();
    
}
