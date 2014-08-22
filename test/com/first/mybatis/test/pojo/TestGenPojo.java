package com.first.mybatis.test.pojo;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有Oracle Number 全部转换为 int
 * @author chad
 *
 */
public class TestGenPojo
{
//	private static final String clazz = "oracle.jdbc.driver.OracleDriver";
	
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";

    private static final String clazz = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pwd = "root";
    private static final String url = "jdbc:mysql://localhost:3306/chad";

    public static void main(String[] args) throws Exception
    {
        //saveClass(null, null, null);

        genClass();
    }
	
	public static void genClass() throws Exception
	{
		loadDriver();
		Connection conn = getConnection();
		Map<String, String> seqs = new HashMap<String, String>();
		//seqs.put("id", "seq_ll_custom_id");

        String pkg = "com.first.mybatis.pojo";
        String table = "test_class";
        String pojo = "ClassPojo";
        String str = GenPojo.genPojo(conn, pkg, table, pojo, seqs);
		
		if (null != conn && !conn.isClosed())
			conn.close();

		System.out.println(str);

        saveClass(pkg, pojo, str);
	}

    /**
     * 生成 JAVA 文件
     * @param pkg
     *                  JAVA 文件包
     * @param clazz
     *                  JAVA 文件名
     * @param content
     *                  JAVA 文件内容
     */
    public static void saveClass(String pkg, String clazz, String content) throws Exception
    {
        String filePath = TestGenPojo.class.getClassLoader().getResource("").getPath();

        filePath = filePath.replace("out/production/", "");

        pkg = pkg.replaceAll("\\.", "/");

        filePath = filePath + "/src/" + pkg + "/" + clazz + ".java";

        System.out.println("saveClass >> " + filePath);

        File f = new File(filePath);

        PrintWriter out = new PrintWriter(f);

        out.write(content);
        out.flush();
        out.close();
    }
	
	public static void loadDriver() throws Exception
	{
		Class.forName(clazz);
	}
	
	public static Connection getConnection() throws Exception
	{
		return DriverManager.getConnection(url, user, pwd);
	}
	
}
