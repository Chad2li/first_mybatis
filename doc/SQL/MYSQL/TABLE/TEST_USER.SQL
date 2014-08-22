create table test_user
(
	id int auto_increment primary key
	, name varchar(255) COMMENT '学生姓名'
	, age int COMMENT '年龄'
	, address varchar(255) COMMENT '地址'
	, createtime timestamp not null default now() COMMENT '创建时间'
	, userclass int COMMENT '所属班级' 
) engine=InnoDB DEFAULT charset=UTF8 COMMENT = '学生表';

insert into test_user (name, age, address, userclass) values ('张三', 18, '浙江杭州', 1);
insert into test_user (name, age, address, userclass) values ('李四', 19, '安徽合肥', 1);