package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiledUtils {
    
    private final static Pattern UPPER_LETTER = Pattern.compile("[A-Z]");
    
    /**
     * 驼峰转下划线.
     *
     * @param str 字符串
     * @return 字段
     */
    public static String toUnderline(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        Matcher matcher = UPPER_LETTER.matcher(str);
        StringBuffer newStr = new StringBuffer();
        if (matcher.find()) {
            matcher.appendReplacement(newStr, matcher.group(0).toLowerCase());
        }
        while (matcher.find()) {
            matcher.appendReplacement(newStr, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(newStr);
        return newStr.toString();
    }
    
    
}
