package utils;

import query.execute.NodeTableExecute;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BeanUtils {
    
    private final static Map<Class<?>, Object> OBJECT_MAP = new HashMap<>();
    
    /**
     * 获取单例对象.
     *
     * @param clazz           类
     * @param mappingFunction 当类对应的对象不存在时的，new的方法
     * @param <T>             泛型
     * @return 类对应的对象
     */
    public static <T> T getSingleTon(Class<T> clazz, Function<? super Class<?>, ?> mappingFunction) {
        Object o = OBJECT_MAP.get(clazz);
        // 如果为空
        if (o == null) {
            // 给map加锁
            synchronized (OBJECT_MAP) {
                // 再次获取，因为加锁期间可能会发生改变
                o = OBJECT_MAP.get(clazz);
                // 再次判空
                if (o == null) {
                    // 执行函数式方法
                    o = mappingFunction.apply(clazz);
                    // 存放进去
                    OBJECT_MAP.put(clazz, o);
                }
            }
        }
        // 转换并返回对象
        return (T) o;
    }
    
    public static void main(String[] args) {
        NodeTableExecute obj1 = getSingleTon(NodeTableExecute.class, obj -> new NodeTableExecute());
        NodeTableExecute obj2 = getSingleTon(NodeTableExecute.class, obj -> new NodeTableExecute());
        System.out.println(obj1 == obj2);
    }
    
}
