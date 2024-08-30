CREATE TABLE citizen (
    cid NUMBER PRIMARY KEY,
    firstname VARCHAR2(100) NOT NULL,
lastname VARCHAR2(100) NOT NULL,
contact NUMBER(10) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR2(255) UNIQUE,
address varchar(100) NOT NULL,
dosescount number default 0 not null

);


CREATE TABLE first_dose ( cid NUMBER, dose_name VARCHAR2(100) NOT NULL, doctor_name VARCHAR2(100) NOT NULL, location VARCHAR2(255), dose_date date, CONSTRAINT pk_first_dose PRIMARY KEY (cid), CONSTRAINT fk_citizen FOREIGN KEY (cid) REFERENCES citizen (cid) ON DELETE CASCADE );


CREATE TABLE second_dose ( cid NUMBER, dose_name VARCHAR2(100) NOT NULL, doctor_name VARCHAR2(100) NOT NULL, location VARCHAR2(255), dose_date date, CONSTRAINT pk_second_dose PRIMARY KEY (cid), CONSTRAINT fk_first_dose FOREIGN KEY (cid) REFERENCES first_dose (cid) ON DELETE CASCADE );


create TABLE booster_dose (cid NUMBER, dose_name VARCHAR2(100) NOT NULL, doctor_name VARCHAR2(100) NOT NULL, location VARCHAR2(255), dose_date date, CONSTRAINT pk_booster_dose PRIMARY KEY (cid), CONSTRAINT fk_second_dose FOREIGN KEY (cid) REFERENCES second_dose(cid) ON DELETE CASCADE );

-- Trigger for the first_dose table
CREATE OR REPLACE TRIGGER trg_update_dosescount_first_dose
AFTER INSERT ON first_dose
FOR EACH ROW
BEGIN
  UPDATE citizen
  SET dosescount = dosescount + 1
  WHERE cid = :NEW.cid;
END;
/

-- Trigger for the second_dose table
CREATE OR REPLACE TRIGGER trg_update_dosescount_second_dose
AFTER INSERT ON second_dose
FOR EACH ROW
BEGIN
  UPDATE citizen
  SET dosescount = dosescount + 1
  WHERE cid = :NEW.cid;
END;
/

-- Trigger for the booster_dose table
CREATE OR REPLACE TRIGGER trg_update_dosescount_booster_dose
AFTER INSERT ON booster_dose
FOR EACH ROW
BEGIN
  UPDATE citizen
  SET dosescount = dosescount + 1
  WHERE cid = :NEW.cid;
END;
/




INSERT INTO citizen (cid, firstname, lastname, address, contact, dob, email) VALUES
(5, 'William', 'Brown', '202 Birch Drive, Springfield, IL', 5557894561, TO_DATE('1978-07-22', 'YYYY-MM-DD'), 'william.brown@example.com');


desc citizen;

select * from first_dose;

select * from second_dose;

select * from citizen;

update citizen set dosescount = 1 where cid=6;

select * from first_dose;

update first_dose set dose_name = 'COVISHIELD' where cid = 1

delete from first_dose where cid=6;
insert into first_dose values (4, 'COVAXIN', 'Dr. Bonte', 'Yerwada Hospital', TO_DATE('2023-08-27', 'YYYY-MM-DD'))
insert into first_dose values (6, 'COVISHIELD', 'Dr. Kale', 'Kharadi Hospital', TO_DATE('2023-08-26', 'YYYY-MM-DD'))


select * from second_dose;

insert into second_dose values (4, 'COVAXIN', 'Dr. Bonte', 'Yerwada Hospital', TO_DATE('2024-02-27', 'YYYY-MM-DD'))
insert into second_dose values (6, 'COVISHIELD', 'Dr. Kale', 'Kharadi Hospital', TO_DATE('2024-02-26', 'YYYY-MM-DD'))


insert into booster_dose values (4, 'COVAXIN', 'Dr. Bonte', 'Yerwada Hospital', TO_DATE('2024-07-27', 'YYYY-MM-DD'))
select * from booster_dose;

