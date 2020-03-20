package cn.crabapples.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static final String USERNAME = "learn";
    private static final String PASSWORD = "learn";
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 创建数据库连接
     *
     * @return 返回一个到数据库的连接
     */
    private static Connection getConnection() {
        try {
            Class.forName(CLASS_NAME);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            logger.warn("连接创建失败:[{}]", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        List<Map<String, Object>> statementResult = formatResultSet(useStatement(connection));
        logger.info("使用[statement]查询结束，结果:\n[{}]\t", statementResult);
        List<Map<String, Object>> prepareStatementResult = formatResultSet(usePrepareStatement(connection));
        logger.info("使用[prepareStatement]查询结束，结果:\n[{}]\t", prepareStatementResult);
        connection.close();
    }

    /**
     * 使用Statement查询(每次查询时创建sql语句)
     *
     * @return 查询到的结果集
     * @throws SQLException sql异常
     */
    private static ResultSet useStatement(Connection connection) throws SQLException {
        String sql = "select * from menu_info";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        logger.info("使用[Statement]查询结束,结果集:[{}]", resultSet);
        return resultSet;
    }

    /**
     * 使用PrepareStatement查询(使用预编译SQL查询，可动态替换查询参数)
     *
     * @return 查询到的结果集
     * @throws SQLException sql异常
     */
    private static ResultSet usePrepareStatement(Connection connection) throws SQLException {
        String sql = "select * from menu_info where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "001");
        ResultSet resultSet = preparedStatement.executeQuery();
        logger.info("使用[PrepareStatement]查询结束,结果集:[{}]", resultSet);
        return resultSet;
    }

    /**
     * 格式化查询到的数据
     *
     * @param resultSet 查询结果集
     * @return 格式化之后的结果集
     * @throws SQLException sql异常
     */
    private static List<Map<String, Object>> formatResultSet(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            Map<String, Object> map = new HashMap<>(count);
            for (int i = 1; i <= count; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            result.add(map);
            logger.debug("当前行格式化后的数据:\n[{}]\t", map);
        }
        logger.info("格式化后的数据:\n[{}]\t", result);
        return result;
    }
}
