create table test_user
(
	id int auto_increment primary key
	, name varchar(255) COMMENT 'ѧ������'
	, age int COMMENT '����'
	, address varchar(255) COMMENT '��ַ'
	, createtime timestamp not null default now() COMMENT '����ʱ��'
	, userclass int COMMENT '�����༶' 
) engine=InnoDB DEFAULT charset=UTF8 COMMENT = 'ѧ����';

insert into test_user (name, age, address, userclass) values ('����', 18, '�㽭����', 1);
insert into test_user (name, age, address, userclass) values ('����', 19, '���պϷ�', 1);