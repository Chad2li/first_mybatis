create table TEST_USER
(
  id         NUMBER not NULL
  , name       VARCHAR2(255)
  , age        NUMBER
  , address    VARCHAR2(255)
  , createtime DATE default SYSDATE not NULL
  , CONSTRAINTS PK_TEST_USER PRIMARY KEY (ID)
) ;

COMMENT ON TABLE TEST_USER IS '�����û���' ;
COMMENT ON COLUMN TEST_USER.name IS '�û�����' ;
COMMENT ON COLUMN TEST_USER.age IS '�û�����' ;
COMMENT ON COLUMN TEST_USER.address IS '�û���ַ' ;
COMMENT ON COLUMN TEST_USER.createtime IS '�û�����ʱ��' ;


CREATE SEQUENCE seq_test_user_id
MINVALUE 1
MAXVALUE 999999999
START WITH 1
INCREMENT BY 1
NOCACHE ;
