package com.first.mybatis.test.test1;

import java.sql.*;

/**
 * Created by pc on 2014/8/22.
 */
public class Test4 {
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String url = "jdbc:mysql://localhost:3306/chad?useUnicode=true&amp;characterEncoding=UTF-8";

    private static final String user = "root";

    private static final String pwd = "root";

    public static void main(String[] args) throws Exception {
        Connection conn = getConn();

        CallableStatement cs = conn.prepareCall("{call getUserAndClass(?, ?)}");

        cs.setInt(1, 1);
        cs.setInt(2, 1);;

        boolean hasNextResult = cs.execute();

        int no = 0;
        while (hasNextResult)
        {
            ResultSet rs = cs.getResultSet();
            System.out.println("ResultSet NO: " + no);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnLen = rsmd.getColumnCount();
            while (rs.next())
            {
                for (int i = 1; i <= columnLen; i++)
                {
                    String name = rsmd.getColumnName(i);
                    Object val = rs.getObject(i);
                    System.out.println("\t" +  name + " >> " + val);
                }
            }
            no++;
            hasNextResult = cs.getMoreResults();
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
