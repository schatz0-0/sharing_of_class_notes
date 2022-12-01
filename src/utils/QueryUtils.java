package utils;

import dao.Note;

public class QueryUtils {

    public static <T> T queryById(Long id) {
        return null;
    }

    public static void main(String[] args) {
        Note node = QueryUtils.queryById(11L);
    }

}
