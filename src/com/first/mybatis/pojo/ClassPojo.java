package com.first.mybatis.pojo;


import java.util.Date;


/**
 * 班级专业表
 * 2014-08-20 14:58:25 Created By GenPojo Utils
 * @version 1.0
 *
 **/
public class ClassPojo
{
	public transient static final String TABLE_NAME = "TEST_CLASS";
	/**
	 * 
	 */
	private int id;

	/**
	 * 班级名
	 */
	private String name;

	/**
	 * 级别，1 班级 2 专业
	 */
	private int level;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 所属专业，level 为 1 时，指向所属专业， level 为 2 时为 0
	 */
	private int parent;

	/* Getter 和 Setter 方法 */
	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }
	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }
	public int getLevel(){ return this.level; }
	public void setLevel(int level){ this.level = level; }
	public Date getCreatetime(){ return this.createtime; }
	public void setCreatetime(Date createtime){ this.createtime = createtime; }
	public int getParent(){ return this.parent; }
	public void setParent(int parent){ this.parent = parent; }
	public ClassPojo() { super(); }

	@Override
	public String toString()
	{
		return "ClassPojo [id = " + this.id + ", name = " + this.name
			 + ", level = " + this.level + ", createtime = " + this.createtime
			 + ", parent = " + this.parent + "]";
	}
}