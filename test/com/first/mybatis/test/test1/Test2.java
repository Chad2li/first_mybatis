package com.first.mybatis.test.test1;

import java.sql.*;

/**
 * Created by pc on 2014/8/20.
 */
public class Test2
{
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://localhost:3306/chad?useUnicode=true&amp;characterEncoding=UTF-8";

    private static final String user = "root";

    private static final String pwd = "root";


    public static void main(String[] args) throws Exception
    {
        Connection conn = getConn();

        PreparedStatement ps = conn.prepareStatement("show create table test_user");

        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            System.exit(1);

        System.out.println(rs.getString(2));
    }

    public static Connection getConn() throws Exception
    {
        return DriverManager.getConnection(url, user, pwd);
    }

    static
    {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
