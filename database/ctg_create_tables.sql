-- Create tables for Semester 2 2022 CTG example ER Model
PRAGMA foreign_keys = OFF;
drop table if exists LGA;
drop table if exists PopulationStatistics;
PRAGMA foreign_keys = ON;

CREATE TABLE LGA (
    lga_code16        INTEGER NOT NULL,
    lga_name16        TEXT NOT NULL,
    lga_type16        CHAR (2),
    area_sqkm         DOUBLE,
    latitude          DOUBLE,
    longitude         DOUBLE,
    PRIMARY KEY (lga_code16)
);

CREATE TABLE PopulationStatistics (
    lga_code16        INTEGER NOT NULL,
    indigenous_status TEXT NOT NULL,
    sex               CHAR (1) NOT NULL,
    age               TEXT NOT NULL,
    count             INTEGER NOT NULL,
    PRIMARY KEY (lga_code16, indigenous_status, sex, age)
    FOREIGN KEY (lga_code16) REFERENCES LGA(lga_code16)
);

