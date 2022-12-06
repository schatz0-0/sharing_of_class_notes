package utils;

import wrapper.RunnableWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class JdbcUtils {
    
    private static String username = null;
    
    private static String password = null;
    
    private static String url = null;
    
    static {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            username = "root";
            password = "lin123456";
            url = "jdbc:mysql://150.158.162.225:33306/sharing_of_class_notes?useUnicode=true&characterEncoding=utf8";
            // 加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * 释放资源
     */
    public static void close(Statement ps, Connection conn) {
        close(null, ps, conn);
    }
    
    /**
     * 释放资源
     */
    public static void close(ResultSet rs, Statement ps, Connection conn) {
        Optional.ofNullable(rs).ifPresent(stream -> runNoThrow(stream::close));
        Optional.ofNullable(ps).ifPresent(stream -> runNoThrow(stream::close));
        Optional.ofNullable(conn).ifPresent(stream -> runNoThrow(stream::close));
    }
    
    public static void runNoThrow(RunnableWrapper run) {
        try {
            run.run();
        } catch (Throwable ignored) {
        }
    }
}