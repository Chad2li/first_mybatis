package com.first.mybatis.pojo;


import java.util.Date;


/**
 * 学生表
 * 2014-08-20 14:50:33 Created By GenPojo Utils
 * @version 1.0
 *
 **/
public class UserPojo
{
	public transient static final String TABLE_NAME = "TEST_USER";
	/**
	 * 
	 */
	private int id;

	/**
	 * 学生姓名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 所属班级
	 */
	private int userclass;

	/* Getter 和 Setter 方法 */
	public int getId(){ return this.id; }
	public void setId(int id){ this.id = id; }
	public String getName(){ return this.name; }
	public void setName(String name){ this.name = name; }
	public int getAge(){ return this.age; }
	public void setAge(int age){ this.age = age; }
	public String getAddress(){ return this.address; }
	public void setAddress(String address){ this.address = address; }
	public Date getCreatetime(){ return this.createtime; }
	public void setCreatetime(Date createtime){ this.createtime = createtime; }
	public int getUserclass(){ return this.userclass; }
	public void setUserclass(int userclass){ this.userclass = userclass; }
	public UserPojo() { super(); }

	@Override
	public String toString()
	{
		return "UserPojo [id = " + this.id + ", name = " + this.name
			 + ", age = " + this.age + ", address = " + this.address
			 + ", createtime = " + this.createtime + ", userclass = " + this.userclass + "]";
	}
}