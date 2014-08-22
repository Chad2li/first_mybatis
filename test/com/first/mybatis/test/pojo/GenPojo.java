package com.first.mybatis.test.pojo;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Map.Entry;

import static java.sql.Types.*;


public class GenPojo
{
	/**
	 * 根据数据库对象生成POJO对象代码
	 * 
	 * @param table
	 *            数据库表名
	 * @param pojo
	 *            POJO 对象类名
	 * @param seqs
	 *            使用到的序列
	 * @return 返回 POJO 类的 JAVA 代码
	 * @throws Exception
	 */
	public static String genPojo(Connection conn, String pkg, String table, String pojo,
			Map<String, String> seqs) throws Exception
	{
		if (null == conn || conn.isClosed())
			throw new RuntimeException("Method[genPojo] 数据库连接池为空或已关闭");

		if (null == table || table.trim().length() < 1)
        throw new RuntimeException("Method[genPojo] 表名不能为空");

		if (null == pojo || pojo.trim().length() < 1)
			throw new RuntimeException("Method[genPojo] POJO名不能为空");

		pkg = null == pkg ? "" : pkg.trim();

		seqs = null == seqs ? new HashMap<String, String>() : seqs;

		return genClass(conn, pkg, table, pojo, seqs);
	}


	/**
	 * 生成POJO的类结构
	 * 
	 * @param conn
	 * @param pkg
	 * @param table
	 * @param pojo
	 * @param seqs
	 * @return
	 * @throws Exception
	 */
	public static String genClass(Connection conn, String pkg, String table, String pojo,
			Map<String, String> seqs) throws Exception
	{
		StringBuilder head = new StringBuilder();
		StringBuilder sb = new StringBuilder();
        List<String> members = new ArrayList<String>();
        Map<String, String> comments = getComment(conn, table);

		sb.append("/**\n * ").append(null ==  comments.get("table")? "" : comments.get("table"))
        .append("\n * ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append(" Created By GenPojo Utils")
        .append("\n * @version 1.0")
        .append("\n *\n **/\n")
		//.append("@Entity\n").append("@Table(name = \"").append(table.toUpperCase()).append("\")\n")
        .append("public class ").append(pojo).append("\n").append("{\n")//.append("\t@Transient\n")
        .append("\tpublic transient static final String TABLE_NAME = \"")
        .append(table.toUpperCase()).append("\";\n");
		/*for (Iterator<Entry<String, String>> it = seqs.entrySet().iterator(); it.hasNext();)
		{
			Entry<String, String> e = it.next();

			if (null == e.getKey() || e.getKey().trim().length() < 1)
				continue;

			if (null == e.getValue() || e.getValue().trim().length() < 1)
				continue;

			sb.append("\t@Transient\n").append("\tpublic static final String SEQ_").append(
					e.getKey().toUpperCase()).append(" = \"").append(e.getValue().toUpperCase())
					.append("\";\n");
		}*/

		// 生成属性和 getter setter 方法
		boolean hasDate = genMember(conn, table, sb, members, comments);

		// 类包名和导入
		head.append("package ").append(pkg).append(";\n").append("\n").append("\n");
		if (hasDate)
			head.append("import java.util.Date;\n");

		head//.append("import javax.persistence.Entity;\n")
                //.append("import javax.persistence.Table;\n")
				//.append("import javax.persistence.Transient;\n")
				.append("\n").append("\n");

		// 生成无参构造器
		sb.append("\tpublic ").append(pojo).append("() {").append(" super(); }\n");

        // 生成 toString 方法
        genToString(members, sb, pojo);

        sb.append("\n}");

		return head.append(sb.toString()).toString();
	}


	/**
	 * 生成属性和 getter setter 方法
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public static boolean genMember(Connection conn, String table, StringBuilder sb, List<String> members, Map<String, String> comments)
			throws Exception
	{
		boolean hasDate = false;
		
		//Map<String, String> colsComm = getColumnComm(conn, table);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM " + table;

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int len = rsmd.getColumnCount();

			// StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
            sb2.append("\t/* Getter 和 Setter 方法 */\n");

			for (int i = 1; i <= len; i++)
			{
				String name = rsmd.getColumnLabel(i);
                members.add(name);

				/*
				 * INTEGER 4 Number 2 String 12 Date 93
				 */
				int type = rsmd.getColumnType(i);

				String clazz = "Object";

				switch (type)
				{
                    case Types.NUMERIC:
					case Types.INTEGER:
						clazz = "int";
						break;
					case Types.VARCHAR:
						clazz = "String";
						break;
					case Types.TIMESTAMP:
						clazz = "Date";
						hasDate = true;
						break;
				}

				//String comm = null == colsComm.get(name.toUpperCase()) ? "" : colsComm.get(name.toUpperCase());
				String[] memberStr = genSubMember(name, clazz, comments.get(name.toLowerCase()));
				// System.out.println("name >> " + name + ", type >> " + type +
				// ", clazz >> " + clazz);

				sb.append(memberStr[0]);
				sb2.append(memberStr[1]);
			}
			sb.append(sb2.toString());
		}
		finally
		{
			if (null != rs && !rs.isClosed())
				rs.close();
			
			if (null != ps && !ps.isClosed())
				ps.close();
		}
		return hasDate;
	}


	/**
	 * 生成单一属性， 及其 getter setter 方法
	 * 
	 * @param name
	 * @param clazz
	 * @return 返回字符串数组有两个元素，0 为属性字符串， 1 为 getter 和 setter 方法
	 */
	public static String[] genSubMember(String name, String clazz, String common)
	{
		name = name.toLowerCase();
		String setName = name.substring(0, 1).toUpperCase() + name.substring(1);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb.append("\t/**\n").append("\t * ").append(null == common ? "" : common).append("\n").append("\t */\n").append(
				"\tprivate ").append(clazz).append(" ").append(name).append(";\n").append("\n");

		sb2.append("\tpublic ").append(clazz).append(" get").append(setName).append("(){ return this.").append(name).append("; }\n")
                .append("\tpublic void set").append(setName).append("(").append(clazz).append(" ").append(
				name).append("){ this.").append(name).append(" = ")	.append(name).append("; }\n");

		return new String[] { sb.toString(), sb2.toString() };
	}

	/**
	 * 获取表和字段注释
	 * @param conn
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getComment(Connection conn, String table) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "show create table " + table.toUpperCase();
        String coms = "";
        Map<String, String> comMap = new HashMap<String, String>();
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next())
				coms = rs.getString(2);
		}
		finally
		{
			if (null != rs && !rs.isClosed())
				rs.close();
			if (null != ps && !ps.isClosed())
				ps.close();
		}

        if (null == coms || coms.trim().length() < 1) return comMap;

        // 解析注释
        String[] comArr = coms.split("\n");
        if (null == comArr || comArr.length < 2) return comMap;

        for (String c : comArr)
        {
            if (null == c || c.trim().length() < 1) continue;
            if (c.indexOf("COMMENT") < 0) continue;

            c = c.trim();
            String name = "";
            String com = "";
            if (c.equals(comArr[comArr.length - 1]))// 表注释
            {
                name = "table";
                com = c.substring(c.lastIndexOf("COMMENT") + 9, c.length() - 1);
            }
            else // 字段注释
            {
                name = c.substring(1, c.indexOf(" ") - 1).toLowerCase();
                com = c.substring(c.lastIndexOf(" '") + 2, c.length() - 2);
            }

            comMap.put(name, com);
        }

		return comMap;
	}
	
	/**
	 * 获取列注释
	 * @param conn
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, String> getColumnComm(Connection conn, String table) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COLUMN_NAME, COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME = '" + table.toUpperCase() + "'";
		Map<String, String> cols = new HashMap<String, String>();
		try
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next())
				cols.put(rs.getString(1), rs.getString(2));
		}
		finally
		{
			if (null != rs && !rs.isClosed())
				rs.close();
			if (null != ps && !ps.isClosed())
				ps.close();
		}
		return cols;
	}

    /**
     * 生成 POJO 的 toString 方法
     * @param members
     *              POJO 对象属性名称集合
     * @param sb
     *              拼接字符串对象
     * @param pojoName
     *              POJO 名称
     */
    public static void genToString(List<String> members, StringBuilder sb, String pojoName)
    {
        sb.append("\n\t@Override\n\tpublic String toString()\n\t{\n\t\treturn \"").append(pojoName)
        .append(" [");

        for (int i = 0; i < members.size(); i++)
        {
            if (0 < i && 0 == i % 2)
                sb.append("\n\t\t\t");

            if (0 < i)
                sb.append(" + \", ");

            sb.append(members.get(i)).append(" = \" + this.").append(members.get(i));
        }

        sb.append(" + \"]\";\n\t}");
    }
}
