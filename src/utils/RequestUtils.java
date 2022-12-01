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
                String parameter = request.getParameter(field.getName());
                if (parameter != null && parameter.equals("")) {

                } else if (parameter != null && Integer.class.equals(field.getType())) {
                    field.set(t, Integer.valueOf(parameter));
                } else if (parameter != null && Boolean.class.equals(field.getType())) {
                    field.set(t, Boolean.valueOf(parameter));
                } else {
                    field.set(t, parameter);
                }
                field.setAccessible(false);
            }
            return t;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


}
