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


CREATE TABLE first_dose (
    cid NUMBER,
    dose_name VARCHAR2(100) NOT NULL,
    doctor_name VARCHAR2(100) NOT NULL,
    location VARCHAR2(255),
    CONSTRAINT pk_first_dose PRIMARY KEY (cid),
    CONSTRAINT fk_citizen
        FOREIGN KEY (cid)
        REFERENCES citizen (cid)
        ON DELETE CASCADE
);


CREATE TABLE second_dose (
    cid NUMBER,
    dose_name VARCHAR2(100) NOT NULL,
    doctor_name VARCHAR2(100) NOT NULL,
    location VARCHAR2(255),
CONSTRAINT pk_second_dose PRIMARY KEY (cid),
    CONSTRAINT fk_first_dose
        FOREIGN KEY (cid)
        REFERENCES first_dose (cid)
        ON DELETE CASCADE
);


create TABLE booster_dose (cid NUMBER, dose_name VARCHAR2(100) NOT NULL, doctor_name VARCHAR2(100) NOT NULL,
location VARCHAR2(255), CONSTRAINT pk_booster_dose PRIMARY KEY (cid), CONSTRAINT fk_second_dose FOREIGN KEY (cid) REFERENCES second_dose(cid) ON DELETE CASCADE
);
