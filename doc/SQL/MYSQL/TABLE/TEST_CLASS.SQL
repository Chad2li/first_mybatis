create table test_class
(
	id int auto_increment primary key
	, name varchar(255) COMMENT '班级名'
	, level int not null default 1 COMMENT '级别，1 班级 2 专业'
	, createtime timestamp not null default now() COMMENT '创建时间'
	, parent int not null default 0 COMMENT '所属专业，level 为 1 时，指向所属专业， level 为 2 时为 0' 
) engine=InnoDB DEFAULT charset=UTF8 COMMENT = '班级专业表';

insert into test_class (name, level) values ('计算机科学', 1);
insert into test_class (name, level, parent) values ('计算机', 2, 1);