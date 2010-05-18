create database hibernateDB;
create table events(EVENT_ID int auto_increment primary key,EVENT_DATE timestamp,title varchar(50));
create table Person(PERSON_ID int auto_increment primary key,age int,firstname varchar(30),lastname varchar(30));
create table PERSON_EVENT(PERSON_ID int,EVENT_ID int, primary key(PERSON_ID,EVENT_ID));