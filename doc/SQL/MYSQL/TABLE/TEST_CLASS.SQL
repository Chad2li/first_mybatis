create table test_class
(
	id int auto_increment primary key
	, name varchar(255) COMMENT '�༶��'
	, level int not null default 1 COMMENT '����1 �༶ 2 רҵ'
	, createtime timestamp not null default now() COMMENT '����ʱ��'
	, parent int not null default 0 COMMENT '����רҵ��level Ϊ 1 ʱ��ָ������רҵ�� level Ϊ 2 ʱΪ 0' 
) engine=InnoDB DEFAULT charset=UTF8 COMMENT = '�༶רҵ��';

insert into test_class (name, level) values ('�������ѧ', 1);
insert into test_class (name, level, parent) values ('�����', 2, 1);