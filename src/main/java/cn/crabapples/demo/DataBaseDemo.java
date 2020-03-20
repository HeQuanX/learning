package cn.crabapples.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
    private static final Logger logger = LoggerFactory.getLogger(DataBaseDemo.class);
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/learn?&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(CLASS_NAME);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        useStatement();
        usePrepareStatement();
    }

    public static void useStatement() throws SQLException, ClassNotFoundException {
        String sql = "select * from menu_info";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        formatResultSet(resultSet);
        connection.close();
    }

    public static void usePrepareStatement() throws SQLException, ClassNotFoundException {
        String sql = "select * from menu_info where id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "001");
        ResultSet resultSet = preparedStatement.executeQuery();
        formatResultSet(resultSet);
        connection.close();
    }


    public static void formatResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            Map<String, Object> map = new HashMap<>(count);
            for (int i = 1; i <= count; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            logger.info("查询到的信息:\n[{}]\t", map);
        }
    }
}
