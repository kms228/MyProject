
/* Drop Tables */

DROP TABLE orderlist CASCADE CONSTRAINTS;
DROP TABLE buyboard CASCADE CONSTRAINTS;
DROP TABLE wishlist CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE field CASCADE CONSTRAINTS;
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
	mnum number NOT NULL,
	buy_date date NOT NULL,
	state varchar2(20),
	addr nvarchar2(120),
	caddr varchar2(45),
	accprice number,
	drate number(10,4),
	PRIMARY KEY (buy_num)
);


CREATE TABLE class
(
	classnum number NOT NULL,
	name varchar2(15),
	PRIMARY KEY (classnum)
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
	gnum nvarchar2(15) NOT NULL,
	grade varchar2(15),
	drate number(10,4),
	PRIMARY KEY (gnum)
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
	fieldnum number NOT NULL,
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
	gnum nvarchar2(15) NOT NULL,
	PRIMARY KEY (mnum)
);


CREATE TABLE orderlist
(
	order_num number NOT NULL,
	amount number(4,0),
	price number,
	pnum number(10) NOT NULL,
	buy_num number NOT NULL,
	PRIMARY KEY (order_num)
);


CREATE TABLE qna
(
	mnum number NOT NULL,
	qnum number,
	title varchar2(30),
	content varchar2(500),
	regdate date,
	hit number,
	refer number,
	lev number,
	step number
	PRIMARY KEY (qnum)
);


CREATE TABLE review
(
	rv_num number NOT NULL,
	mnum number NOT NULL,
	title varchar2(30),
	content varchar2(500),
	regdate date,
	hit number,
	refer number,
	lev number,
	step number,
	star number,
	PRIMARY KEY (rv_num)
);


CREATE TABLE wishlist
(
	num number NOT NULL,
	mnum number NOT NULL,
	pnum number(10) NOT NULL,
	regdate date NOT NULL,
	PRIMARY KEY (num)
);



/* Create Foreign Keys */

ALTER TABLE orderlist
	ADD FOREIGN KEY (buy_num)
	REFERENCES buyboard (buy_num)
;


ALTER TABLE field
	ADD FOREIGN KEY (classnum)
	REFERENCES class (classnum)
;


ALTER TABLE item
	ADD FOREIGN KEY (fieldnum)
	REFERENCES field (fieldnum)
;


ALTER TABLE members
	ADD FOREIGN KEY (gnum)
	REFERENCES grade (gnum)
;


ALTER TABLE orderlist
	ADD FOREIGN KEY (pnum)
	REFERENCES item (pnum)
;


ALTER TABLE wishlist
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


ALTER TABLE wishlist
	ADD FOREIGN KEY (mnum)
	REFERENCES members (mnum)
;


ALTER TABLE image
	ADD FOREIGN KEY (rv_num)
	REFERENCES review (rv_num)
;