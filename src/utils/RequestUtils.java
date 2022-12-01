package utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class RequestUtils {

    public static <T> T getParamToBean(HttpServletRequest request, Class<T> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            T t = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(t, request.getParameter(field.getName()));
                field.setAccessible(false);
            }
            return t;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }



}
