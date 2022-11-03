PRAGMA FOREIGN_KEYS = OFF;
DROP TABLE IF EXISTS LGA;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Outcomes;
DROP TABLE IF EXISTS AgeBracket;
DROP TABLE IF EXISTS IncomeBracket;
DROP TABLE IF EXISTS EducationStatistics;
DROP TABLE IF EXISTS LTHCStatistics;
DROP TABLE IF EXISTS HouseholdStatistics;
DROP TABLE IF EXISTS PopulationStatistics;
DROP TABLE IF EXISTS State;
DROP TABLE IF EXISTS PersonaAttribute;
PRAGMA FOREIGN_KEYS = ON;

CREATE TABLE LGA (
   lga_code              INTEGER NOT NULL,
   lga_year              INTEGER NOT NULL,
   lga_name              TEXT NOT NULL,
   lga_type              CHAR (2),
   area_sqkm             DOUBLE,
   latitude              DOUBLE,
   longitude             DOUBLE,
   PRIMARY KEY (lga_code, lga_year)
);

CREATE TABLE Persona (
    persona_name    TEXT NOT NULL,
    age             INTEGER NOT NULL,
    ethnicity       VARCHAR(100) NOT NULL,
    quote           VARCHAR(100) NOT NULL,
    image_file_path VARCHAR(100) NOT NULL,
    PRIMARY KEY (persona_name)
);

CREATE TABLE Outcomes (
    outcome_ID          INTEGER NOT NULL,
    title               VARCHAR(500) NOT NULL,
    descrip             VARCHAR(500) NOT NULL,
    PRIMARY KEY (outcome_ID)
);

CREATE TABLE AgeBracket (
   age_bracket           TEXT NOT NULL,
   min_age               INTEGER NOT NULL,
   max_age               INTEGER NOT NULL,
   PRIMARY KEY (age_bracket)
);

CREATE TABLE IncomeBracket (
   income_bracket        TEXT NOT NULL,
   min_income            INTEGER NOT NULL,
   max_income            INTEGER NOT NULL,
   PRIMARY KEY (income_bracket)
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

CREATE TABLE HouseholdStatistics (
    lga_code             INTEGER NOT NULL,
    lga_year             INTEGER NOT NULL,
    indigenous_status    TEXT NOT NULL,
    income_bracket       TEXT NOT NULL,
    count                INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, income_bracket)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
    FOREIGN KEY (income_bracket) REFERENCES IncomeBracket(income_bracket)
);

CREATE TABLE PopulationStatistics (
    lga_code             INTEGER NOT NULL,
    lga_year             INTEGER NOT NULL,
    indigenous_status    TEXT NOT NULL,
    sex                  CHAR (1) NOT NULL,
    age                  TEXT NOT NULL,
    count                INTEGER NOT NULL,
    PRIMARY KEY (lga_code, lga_year, indigenous_status, sex, age)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    FOREIGN KEY (lga_year) REFERENCES LGA(lga_year)
    FOREIGN KEY (age) REFERENCES AgeBracket(age_bracket)
);

CREATE TABLE State (
    lga_starting_number  INTEGER NOT NULL,
    state                TEXT NOT NULL,
    PRIMARY KEY (lga_starting_number)
);

CREATE TABLE PersonaAttribute (
    persona_name    TEXT NOT NULL,
    id              INTEGER NOT NULL,
    attributeType   VARCHAR(500) NOT NULL,
    descrip         VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
    FOREIGN KEY (persona_name) REFERENCES Persona(persona_name)
);