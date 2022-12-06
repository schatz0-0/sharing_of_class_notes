package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author lin
 */
public class SessionUtils {
    
    private final static List<Class<?>> WRAPPER_CLASS = Arrays.asList(Integer.class, Boolean.class);
    
    public static <T> T getParamToBean(HttpServletRequest request, Class<T> clazz) {
        try {
            request.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();
            Field[] fields = clazz.getDeclaredFields();
            T t = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = session.getAttribute(field.getName());
                if (value == null || "".equals(value)) {
                    continue;
                }
                if (Integer.class.equals(field.getType())) {
                    field.set(t, Integer.valueOf(value + ""));
                    continue;
                }
                if (Boolean.class.equals(field.getType())) {
                    field.set(t, Boolean.valueOf(value + ""));
                    continue;
                }
                field.set(t, value);
                field.setAccessible(false);
            }
            return t;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    
    
}
