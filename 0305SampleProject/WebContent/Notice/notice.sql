-- 공지사항 테이블
-- 테이블 구조 'notice`

CREATE TABLE notice (
  idx number NOT NULL ,				--  고유번호, 자동증가
  subject varchar2(255) NOT NULL ,		--  제목
  contents varchar2(2000) NOT NULL,		--  내용
  regdate date default sysdate,			--  등록일자
  readcnt number default 0,			--  조회수
  PRIMARY KEY  (idx)
);
create sequence notice_seq_idx;
