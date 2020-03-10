-- 회원관리 테이블
-- 테이블 구조 `usertbl`

create table usertbl(
	idx number not null primary key ,		-- 고유번호, 자동증가
	name varchar2(20) not null,			-- 회원이름
	userid varchar2(20) unique,			-- 회원 ID
	passwd varchar2(50) not null,			-- 비밀번호
	tel varchar2(14) not null,			-- 전화(지역/핸드폰)
	email varchar2(100),				-- 이메일
	first_time date default sysdate,		-- 가입날짜
	last_time date					-- 마지막 로그인날짜
);
create sequence usertbl_seq_idx;
