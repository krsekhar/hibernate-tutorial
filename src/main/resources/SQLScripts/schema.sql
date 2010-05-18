create database hibernateDB;
create table events(EVENT_ID int auto_increment primary key,EVENT_DATE timestamp,title varchar(50));
create table Person(PERSON_ID int auto_increment primary key,age int,firstname varchar(30),lastname varchar(30));
create table PERSON_EVENT(PERSON_ID int,EVENT_ID int, primary key(PERSON_ID,EVENT_ID));
create table person_email_addr(PERSON_ID int ,FOREIGN KEY(PERSON_ID) references person(PERSON_ID) , EMAIL_ADR varchar(30) , primary key(PERSON_ID ,EMAIL_ADR))