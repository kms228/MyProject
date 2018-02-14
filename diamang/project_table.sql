
/* Drop Tables */

DROP TABLE deliver CASCADE CONSTRAINTS;
DROP TABLE payment CASCADE CONSTRAINTS;
DROP TABLE buyboard CASCADE CONSTRAINTS;
DROP TABLE field CASCADE CONSTRAINTS;
DROP TABLE wishilist CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE class CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE image CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE buyboard
(
	buy_num number NOT NULL,
	pnum number(10) NOT NULL,
	mnum number NOT NULL,
	buy_date date NOT NULL,
	PRIMARY KEY (buy_num)
);


CREATE TABLE class
(
	classnum number NOT NULL,
	name varchar2(15),
	PRIMARY KEY (classnum)
);


CREATE TABLE deliver
(
	dnum number NOT NULL,
	buy_num number NOT NULL,
	dstate varchar2(15),
	daddr varchar2(120),
	PRIMARY KEY (dnum)
);


CREATE TABLE field
(
	fieldnum number NOT NULL,
	classnum number NOT NULL,
	name varchar2(15),
	PRIMARY KEY (fieldnum)
);


CREATE TABLE grade
(
	gradeNum number(5) NOT NULL,
	drate number(10,4),
	name varchar2(12) NOT NULL,
	PRIMARY KEY (gradeNum)
);


CREATE TABLE image
(
	num number NOT NULL,
	rv_num number NOT NULL,
	orgname varchar2(30),
	savename varchar2(70),
	PRIMARY KEY (num)
);


CREATE TABLE item
(
	pnum number(10) NOT NULL,
	item_name varchar2(50) NOT NULL,
	price number NOT NULL,
	regdate date NOT NULL,
	stock number,
	savename varchar2(70),
	orgname varchar2(70),
	classnum number NOT NULL,
	PRIMARY KEY (pnum)
);


CREATE TABLE members
(
	mnum number NOT NULL,
	id varchar2(15) NOT NULL,
	pwd varchar2(15) NOT NULL,
	name varchar2(30),
	birthday date NOT NULL,
	email varchar2(40) NOT NULL UNIQUE,
	address varchar2(150) NOT NULL,
	phone varchar2(20) NOT NULL,
	gradeNum number(5) NOT NULL,
	accAmt number(6) DEFAULT 0,
	PRIMARY KEY (mnum)
);


CREATE TABLE payment
(
	pnum number NOT NULL,
	buy_num number NOT NULL,
	pcnt number(4,0),
	price number,
	PRIMARY KEY (pnum)
);


CREATE TABLE qna
(
	mnum number NOT NULL,
	num number,
	title varchar2(30),
	content varchar2(500),
	regdate date,
	hit number,
	ref number,
	lev number,
	step number
);


CREATE TABLE review
(
	rv_num number NOT NULL,
	mnum number NOT NULL,
	title varchar2(30),
	content varchar2(500),
	regdate date,
	hit number,
	ref number,
	lev number,
	step number,
	star number,
	PRIMARY KEY (rv_num)
);


CREATE TABLE wishilist
(
	num number NOT NULL,
	mnum number NOT NULL,
	pnum number(10) NOT NULL,
	regdate date NOT NULL,
	PRIMARY KEY (num)
);



/* Create Foreign Keys */

ALTER TABLE deliever
	ADD FOREIGN KEY (buy_num)
	REFERENCES buyboard (buy_num)
;


ALTER TABLE payment
	ADD FOREIGN KEY (buy_num)
	REFERENCES buyboard (buy_num)
;


ALTER TABLE field
	ADD FOREIGN KEY (classnum)
	REFERENCES class (classnum)
;


ALTER TABLE item
	ADD FOREIGN KEY (classnum)
	REFERENCES class (classnum)
;


ALTER TABLE members
	ADD FOREIGN KEY (gradeNum)
	REFERENCES grade (gradeNum)
;


ALTER TABLE buyboard
	ADD FOREIGN KEY (pnum)
	REFERENCES item (pnum)
;


ALTER TABLE wishilist
	ADD FOREIGN KEY (pnum)
	REFERENCES item (pnum)
;


ALTER TABLE buyboard
	ADD FOREIGN KEY (mnum)
	REFERENCES members (mnum)
;


ALTER TABLE qna
	ADD FOREIGN KEY (mnum)
	REFERENCES members (mnum)
;


ALTER TABLE review
	ADD FOREIGN KEY (mnum)
	REFERENCES members (mnum)
;


ALTER TABLE wishilist
	ADD FOREIGN KEY (mnum)
	REFERENCES members (mnum)
;


ALTER TABLE image
	ADD FOREIGN KEY (rv_num)
	REFERENCES review (rv_num)
;



