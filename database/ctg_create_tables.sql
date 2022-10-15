-- Create tables for Semester 2 2022 CTG example ER Model
PRAGMA foreign_keys = OFF;
drop table if exists LGA;
drop table if exists PopulationStatistics;
drop table if exists HouseholdStatistics;
drop table if exists EducationStatistics;
drop table if exists Outcomes;
PRAGMA foreign_keys = ON;

-- CREATE TABLE LGA (
--   lga_code16        INTEGER NOT NULL,
--    lga_name16        TEXT NOT NULL,
--    lga_type16        CHAR (2),
--    area_sqkm         DOUBLE,
--    latitude          DOUBLE,
--    longitude         DOUBLE,
--    PRIMARY KEY (lga_code16)
-- );

CREATE TABLE LGA (
    lga_code        INTEGER NOT NULL,
    lga_year        INTEGER NOT NULL,
    lga_name        TEXT NOT NULL,
    lga_type        CHAR (2),
    area_sqkm         DOUBLE,
    latitude          DOUBLE,
    longitude         DOUBLE,
    PRIMARY KEY (lga_code, lga_year)
);

CREATE TABLE PopulationStatistics (
    lga_code          INTEGER NOT NULL,
    lga_year          INTEGER NOT NULL,
    indigenous_status TEXT NOT NULL,
    sex               CHAR (1) NOT NULL,
    age               TEXT NOT NULL,
    -- age_min           INTEGER NOT NULL,
    -- age_max           INTEGER NOT NULL,
    count             INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, sex, age)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
);

CREATE TABLE HouseholdStatistics (
    lga_code          INTEGER NOT NULL,
    lga_year          INTEGER NOT NULL,
    indigenous_status TEXT NOT NULL,
    income_bracket    TEXT NOT NULL,
    count             INTEGER NOT NULL,
    -- income_bracket_min  INTEGER NOT NULL,
    -- income_bracket_max  INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, income_bracket)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
);

CREATE TABLE EducationStatistics (
    lga_code                INTEGER NOT NULL,
    lga_year                INTEGER NOT NULL,
    indigenous_status       TEXT NOT NULL,
    sex                     CHAR (1) NOT NULL,
    highest_school_year     TEXT NOT NULL,
    count                   INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, sex, highest_school_year)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
);

CREATE TABLE LTHCStatistics (
    lga_code            INTEGER NOT NULL,
    lga_year            INTEGER NOT NULL,
    indigenous_status   TEXT NOT NULL,
    sex                 CHAR (1) NOT NULL,
    condition           TEXT NOT NULL,
    count               INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, sex, condition)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
);

CREATE TABLE Outcomes (
    outcome_ID          INTEGER NOT NULL,
    title               VARCHAR(500) NOT NULL,
    descrip             VARCHAR(500) NOT NULL,
    PRIMARY KEY (outcome_ID)
);

CREATE TABLE Persona (
    persona_name    TEXT NOT NULL,
    age             INTEGER NOT NULL,
    ethnicity       VARCHAR(100) NOT NULL,
    quote           VARCHAR(100) NOT NULL,
    image_file_path VARCHAR(100) NOT NULL,
    PRIMARY KEY (persona_name)
);

CREATE TABLE PersonaAttribute (
    persona_name    TEXT NOT NULL,
    id              INTEGER NOT NULL,
    attributeType   VARCHAR(500) NOT NULL,
    descrip         VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
    FOREIGN KEY (persona_name) REFERENCES Persona(persona_name)
);