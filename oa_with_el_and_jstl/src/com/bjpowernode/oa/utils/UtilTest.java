package com.bjpowernode.oa.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilTest {

    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from dept;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()) {
            String no = resultSet.getString(1);
            String name = resultSet.getString(2);
            String loc = resultSet.getString(3);
            System.out.println(no + "\t" + name + "\t" + loc);
        }

        DBUtil.close(connection, preparedStatement, resultSet);
    }
}
