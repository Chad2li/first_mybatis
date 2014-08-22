package com.first.mybatis.test.test1;

import java.sql.*;

/**
 * Created by pc on 2014/8/20.
 * 1	id >>	INT	4
 * 2	name >>	VARCHAR	12
 * 3	age >>	INT	4
 * 4	address >>	VARCHAR	12
 * 5	createtime >>	TIMESTAMP	93
 * 6	userclass >>	INT	4
 *
 */
public class Test1
{
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://localhost:3306/chad";

    private static final String user = "root";

    private static final String pwd = "root";


    public static void main(String[] args) throws Exception
    {
        Connection conn = getConn();

        PreparedStatement ps = conn.prepareStatement("select * from test_user");

        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            System.exit(1);

        ResultSetMetaData rsmd = rs.getMetaData();

        int len = rsmd.getColumnCount();

        for (int i = 1; i <= len; i++)
        {
            String name = rsmd.getColumnName(i);
            int type = rsmd.getColumnType(i);
            String typeStr = rsmd.getColumnTypeName(i);
            System.out.println(i + "\t" + name + " >>\t" + typeStr + "\t" + type);
        }
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
