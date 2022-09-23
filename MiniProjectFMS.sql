-- Member TABLE --
CREATE  TABLE FMS_MEMBER(
	MEMBER_NO NUMBER PRIMARY KEY, 
	MEMBER_ID VARCHAR(30) NOT NULL,
	MEMBER_PW VARCHAR(30) NOT NULL,
	MEMBER_NM VARCHAR(30) NOT NULL,
	MEMBER_SSN VARCHAR2(14) NOT NULL,
	MEMBER_PHO VARCHAR2(12) NOT NULL,
	MEMBER_ADDRESS VARCHAR2(100) NOT NULL,
	MEMBER_STORE VARCHAR2(100) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN FMS_MEMBER.MEMBER_NO  IS '회원 번호';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_ID  IS '회원 아이디';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_PW  IS '회원 비밀번호';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_NM  IS '회원 이름';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_SSN  IS '회원 주민번호';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_PHO  IS '회원 전화번호';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_ADDRESS  IS '회원 주소';
COMMENT ON COLUMN FMS_MEMBER.MEMBER_STORE  IS '회원 담당매장';
COMMENT ON COLUMN FMS_MEMBER.ENROLL_DATE  IS '회원 가입일자';

-- 회원 번호  시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO
START WITH 1
INCREMENT BY 1
NOCYCLE 
NOCACHE; 


INSERT INTO FMS_MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01','pass01','유저일','220922-1023456',01012345678,'마포구 아현동', '마포점',DEFAULT);
INSERT INTO FMS_MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02','pass02','유저이','940820-1023456',01012345678,'서초구 서초동', '서초점',DEFAULT);
INSERT INTO FMS_MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03','pass03','유저삼','020115-1023456',01012345678,'영등포구 당산1동', '영등포점',DEFAULT);

SELECT * FROM FMS_MEMBER; 

COMMIT;