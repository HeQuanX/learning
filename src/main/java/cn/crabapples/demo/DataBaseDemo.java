package cn.crabapples.demo;

import java.sql.*;

/**
 * TODO 数据库连接演示
 *
 * @author Mr.He
 * 2020/3/20 2:27
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class DataBaseDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/learn?&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "learn";
    private static final String PASSWORD = "learn";
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(CLASS_NAME);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from menu_info");
        while (resultSet.next()) {
            System.err.println(resultSet.getString(1));
        }
    }
}
