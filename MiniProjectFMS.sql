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
-------------------------------------------------------------------------------

-- 회원가입
INSERT INTO FMS_MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, ?,?,?,'?',?,'?','?',DEFAULT);


-- 아이디 중복검사
SELECT COUNT(*) FROM FMS_MEMBER
WHERE MEMBER_ID = ?


--##################################################################---
-- Client TABLE --

CREATE  TABLE FMS_CLIENT(
	CLIENT_NO NUMBER PRIMARY KEY, 
	CLIENT_NM VARCHAR(30) NOT NULL,
	CLIENT_SSN VARCHAR2(14) NOT NULL,
	CLIENT_PHO VARCHAR2(12) NOT NULL,
	CLIENT_ADDRESS VARCHAR2(100) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE
);
--컬럼추가 
ALTER TABLE FMS_CLIENT ADD CLIENT_FL CHAR(1) DEFAULT 'N' NOT NULL;
-- 컬럼수정
ALTER TABLE FMS_CLIENT MODIFY CLIENT_ADDRESS VARCHAR2(600);

COMMENT ON COLUMN FMS_CLIENT.CLIENT_NO  IS '고객 번호';
COMMENT ON COLUMN FMS_CLIENT.CLIENT_NM  IS '고객 이름';
COMMENT ON COLUMN FMS_CLIENT.CLIENT_SSN  IS '고객 주민번호';
COMMENT ON COLUMN FMS_CLIENT.CLIENT_PHO  IS '고객 전화번호';
COMMENT ON COLUMN FMS_CLIENT.CLIENT_ADDRESS  IS '고객 주소';
COMMENT ON COLUMN FMS_CLIENT.ENROLL_DATE  IS '고객 가입일자';
COMMENT ON COLUMN FMS_CLIENT.CLIENT_FL  IS '고객 탈퇴여부(Y/N)';

-- 고객 번호  시퀀스 생성
CREATE SEQUENCE SEQ_CLIENT_NO
START WITH 1
INCREMENT BY 1
NOCYCLE 
NOCACHE; 

-- 고객 임시 등록
INSERT INTO FMS_CLIENT
VALUES(SEQ_CLIENT_NO.NEXTVAL, '고객1','640820-1023456',01012341234,'성동구',DEFAULT);

ROLLBACK;
COMMIT;

SELECT * FROM FMS_CLIENT; 

--------------------------------------------------------------------

-- 고객 등록
INSERT INTO FMS_CLIENT
VALUES(SEQ_CLIENT_NO.NEXTVAL, ?,?,?,?,DEFAULT);


-- 고객 중복검사(주민번호)
SELECT COUNT(*) FROM FMS_CLIENT
WHERE CLIENT_SSN = ?


-- 고객 삭제 
UPDATE FMS_CLIENT SET
CLIENT_FL  = 'Y'	
WHERE CLIENT_NO = ? 


-- 고객 목록 조회
SELECT *
FROM FMS_CLIENT
WHERE CLIENT_FL ='N'
ORDER BY CLIENT_NO  DESC;

-- 고객 정보 수정 고유번호 확인
SELECT COUNT(*) FROM FMS_CLIENT
WHERE CLIENT_NO = ?


-- 고객 수정 이름 
UPDATE FMS_CLIENT  SET
CLIENT_NM  = ?	
WHERE CLIENT_NO = ? 

-- 고객 수정 전화번호
UPDATE FMS_CLIENT  SET
CLIENT_PHO  = ?	
WHERE CLIENT_NO = ? 

-- 고객 수정 주소
UPDATE FMS_CLIENT  SET
CLIENT_ADDRESS  = ?	
WHERE CLIENT_NO = ?

SELECT * FROM FMS_CLIENT; 

--##################################################################---
-- ARTICLE TABLE --
CREATE  TABLE FMS_ARTICLE(
	ARTICLE_NO NUMBER PRIMARY KEY, 
	ARTICLE_NM VARCHAR(30) NOT NULL,
	ARTICLE_QUANTITY NUMBER NOT NULL,
	ARTICLE_UNIT VARCHAR2(30) NOT NULL,
	ARTICLE_SL DATE,
	ARTICLE_DOM DATE,
	ARTICLE_RD DATE DEFAULT SYSDATE,
	ARTICLE_DD DATE ,
	MEMBER_NO NUMBER REFERENCES FMS_MEMBER(MEMBER_NO),
	MEMBER_STORE VARCHAR2(100) REFERENCES FMS_MEMBER(MEMBER_STORE),
	CLIENT_NO NUMBER REFERENCES FMS_CLIENT(CLIENT_NO)
	ARTICLE_OFFER NUMBER DEFAULT 0
);

-- 컬럼수정
ALTER TABLE FMS_ARTICLE MODIFY ARTICLE_DD DATE default null;

-- 컬럼추가
ALTER TABLE FMS_ARTICLE ADD MEMBER_STORE VARCHAR2(100) REFERENCES FMS_MEMBER(MEMBER_STORE);
ALTER TABLE FMS_ARTICLE ADD ARTICLE_OFFER NUMBER DEFAULT 0;

ALTER TABLE FMS_ARTICLE DROP COLUMN ARTICLE_OFFER;
--DROP TABLE FMS_ARTICLE;

COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_NO  IS '물품 번호';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_NM  IS '물품 명칭';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_QUANTITY  IS '물품 수량';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_UNIT  IS '단위';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_SL  IS '유통기한';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_DOM  IS '제조일자';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_RD  IS '등록일자';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_DD  IS '제공일자';
COMMENT ON COLUMN FMS_ARTICLE.MEMBER_NO  IS '회원 번호';
COMMENT ON COLUMN FMS_ARTICLE.CLIENT_NO  IS '고객 번호';
COMMENT ON COLUMN FMS_ARTICLE.ARTICLE_OFFER  IS '물품 제공 수량';

-- 시퀀스 삭제
DROP SEQUENCE SEQ_ARTICLE_NO;

-- 회원 번호  시퀀스 생성
CREATE SEQUENCE SEQ_ARTICLE_NO
START WITH 1
INCREMENT BY 1
NOCYCLE 
NOCACHE; 

-- 임시 등록
INSERT INTO FMS_ARTICLE
VALUES(SEQ_ARTICLE_NO.NEXTVAL, '쌀20kg','300','ea',
TO_DATE('2030-08-21','YYYY-MM-DD'),
TO_DATE('2021-09-21','YYYY-MM-DD'),
DEFAULT, null, 4, null);

ROLLBACK;
COMMIT;
SELECT * FROM FMS_ARTICLE
ORDER BY ARTICLE_NO; 

-- 지점만 수정
SELECT ARTICLE_NO,ARTICLE_NM,ARTICLE_QUANTITY,ARTICLE_UNIT,
ARTICLE_SL,ARTICLE_DOM,ARTICLE_RD,MEMBER_NM,MEMBER_STORE
FROM FMS_ARTICLE
JOIN FMS_MEMBER USING (MEMBER_NO)
WHERE MEMBER_STORE = (SELECT MEMBER_STORE FROM FMS_MEMBER
					 WHERE MEMBER_NO = 4);
-------------------------------------------------------------
					
-- 물품 등록	
INSERT INTO FMS_ARTICLE
VALUES(SEQ_ARTICLE_NO.NEXTVAL, ?,?,?,
TO_DATE(?,'YYYY-MM-DD'),
TO_DATE(?,'YYYY-MM-DD'),
DEFAULT, null, ?, NULL,DEFAULT);		

-- 물품 삭제 (복구X)
DELETE FROM FMS_ARTICLE
WHERE ARTICLE_NO = 2

SELECT * FROM FMS_ARTICLE
ORDER BY ARTICLE_NO; 

-- 물품 재고 조회
SELECT ARTICLE_NO,ARTICLE_NM,ARTICLE_QUANTITY,ARTICLE_UNIT,
TO_CHAR(ARTICLE_SL, 'YYYY-MM-DD')ARTICLE_SL,
TO_CHAR(ARTICLE_DOM, 'YYYY-MM-DD')ARTICLE_DOM,
TO_CHAR(ARTICLE_RD, 'YYYY-MM-DD')ARTICLE_RD,MEMBER_NM,MEMBER_STORE
FROM FMS_ARTICLE
JOIN FMS_MEMBER USING (MEMBER_NO)
ORDER BY ARTICLE_NO 

--물품 정보 수정 (물품번호 검사)
SELECT COUNT(*) FROM FMS_ARTICLE
WHERE ARTICLE_NO = ?

-- 물품 정보 수정 (물품 명칭)
UPDATE FMS_ARTICLE  SET
ARTICLE_NM  = ?	
WHERE ARTICLE_NO = ? 

-- 물품 정보 수정 (물품 수량,단위)
UPDATE FMS_ARTICLE  SET
ARTICLE_QUANTITY  = ?,
ARTICLE_UNIT = ?
WHERE ARTICLE_NO = ? 


-- 물품 정보 수정 (물품 유통기한)
UPDATE FMS_ARTICLE  SET
ARTICLE_SL  = TO_DATE('2025-05-05', 'YYYY-MM-DD')
WHERE ARTICLE_NO = 2

SELECT * FROM FMS_ARTICLE
ORDER BY ARTICLE_NO; 

-- 물품 정보 수정 (물품 제조일자)
UPDATE FMS_ARTICLE  SET
ARTICLE_DOM  = TO_DATE(?, 'YYYY-MM-DD')
WHERE ARTICLE_NO = ?

-- 물품 제공등록 (등록 매장 확인)
SELECT COUNT(*)
FROM FMS_ARTICLE
JOIN FMS_MEMBER USING (MEMBER_NO)
WHERE  ARTICLE_NO = ?
AND MEMBER_STORE = (SELECT MEMBER_STORE FROM FMS_MEMBER
				 WHERE MEMBER_NO = ?)
					 
-- 물품 제공등록(재고 수량 감소)
UPDATE FMS_ARTICLE  SET
ARTICLE_QUANTITY  = (ARTICLE_QUANTITY -7)	
WHERE ARTICLE_QUANTITY > 0 		
AND ARTICLE_NO = 7
				 
-- 물품 제공등록 (고객 수량 증가)
UPDATE FMS_ARTICLE  SET
ARTICLE_OFFER  = (ARTICLE_OFFER +7)
WHERE ARTICLE_NO = 7				 
				 
-- 물품 제공등록 (제공일자)
UPDATE FMS_ARTICLE  SET
ARTICLE_DD  = SYSDATE
WHERE ARTICLE_NO = ?


-- 물품 제공현황
SELECT ARTICLE_NO, ARTICLE_NM, ARTICLE_OFFER,
ARTICLE_UNIT,ARTICLE_SL,ARTICLE_DOM,ARTICLE_DD,MEMBER_STORE
FROM FMS_ARTICLE
LEFT JOIN FMS_CLIENT USING (CLIENT_NO)
LEFT JOIN FMS_MEMBER USING (MEMBER_NO)
WHERE ARTICLE_OFFER > 0








SELECT * FROM FMS_CLIENT

------------------------
 SELECT * FROM FMS_ARTICLE
ORDER BY ARTICLE_NO; 
				 
ROLLBACK;
COMMIT;



