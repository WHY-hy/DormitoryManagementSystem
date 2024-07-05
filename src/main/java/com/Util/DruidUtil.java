package com.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库的工具类
 */

public class DruidUtil {

    //声明数据源对象
    private static DataSource dataSource = null;

    //创建数据库连接池
    public static void init()
    {
        //1.加载配置文件
        Properties properties = new Properties();

        //2.获取文件的路径
        InputStream inputStream = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");

        //3.把文件流传入到连接池中去
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.初始化Druid连接池
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5.关闭输入流
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回Connection对象
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //归还连接,有结果集的
    public static void close(ResultSet resultSet, Connection connection, Statement statement)
    {
        //1.关闭结果集
        if (resultSet != null)
        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //2.关闭执行SQL的句柄
        if (statement != null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //3.关闭连接
        if (connection != null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //归还连接,没有结果集的
    public static void close(Statement statement, Connection connection)
    {
        if (statement != null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取数据源对象
    public static DataSource getDataSource()
    {
        return dataSource;
    }
}
