CREATE VIEW ES2016 AS 
SELECT e.lga_code, 
       e.lga_year, 
       ls.lga_name, 
       ls.state, 
       ls.lga_type, 
       e.indigenous_status, 
       e.sex, 
       e.highest_school_year, 
       e.count,
       RANK () OVER (PARTITION BY e.indigenous_status, e.sex, e.highest_school_year 
                     ORDER BY e.count DESC) AS 'rank2016' 
   FROM EducationStatistics e JOIN LGA_State ls ON e.lga_code = ls.lga_code AND e.lga_year = ls.lga_year
   WHERE e.lga_year = 2016;

CREATE VIEW ES2016VS2021 AS 
SELECT e1.lga_code AS '2016 LGA Code',
       e1.lga_year AS '2016 LGA Year',
       e1.lga_name AS '2016 LGA Name',
       e1.state AS '2016 LGA State',
       e1.lga_type AS '2016 LGA Type',
       e2.lga_code AS '2021 LGA Code',
       e2.lga_year AS '2021 LGA Year',
       e2.lga_name AS '2021 LGA Name',
       e2.state AS '2021 LGA State',
       e2.lga_type AS '2021 LGA Type',
       e1.indigenous_status AS '2016 Indigenous Status',
       e1.sex AS '2016 Sex',
       e1.highest_school_year AS '2016 Highest School Year Completed',
       e2.indigenous_status AS '2021 Indigenous Status',
       e2.sex AS '2021 Sex',
       e2.highest_school_year AS '2021 Highest School Year Completed',
       e1.count AS '2016 Result',
       e2.count AS '2021 Result', 
       `rank2016`,
       `rank2021` 
   FROM ES2016 AS e1 LEFT OUTER JOIN ES2021 AS e2 ON e1.lga_code = e2.lga_code AND 
                                                     e1.indigenous_status = e2.indigenous_status AND 
                                                     e1.sex = e2.sex AND
                                                     e1.highest_school_year = e2.highest_school_year
                                                     
UNION ALL

SELECT e1.lga_code AS '2016 LGA Code',
       e1.lga_year AS '2016 LGA Year',
       e1.lga_name AS '2016 LGA Name',
       e1.state AS '2016 LGA State',
       e1.lga_type AS '2016 LGA Type',
       e2.lga_code AS '2021 LGA Code',
       e2.lga_year AS '2021 LGA Year',
       e2.lga_name AS '2021 LGA Name',
       e2.state AS '2021 LGA State',
       e2.lga_type AS '2021 LGA Type',
       e1.indigenous_status AS '2016 Indigenous Status',
       e1.sex AS '2016 Sex',
       e1.highest_school_year AS '2016 Highest School Year Completed',
       e2.indigenous_status AS '2021 Indigenous Status',
       e2.sex AS '2021 Sex',
       e2.highest_school_year AS '2021 Highest School Year Completed',
       e1.count AS '2016 Result',
       e2.count AS '2021 Result', 
       `rank2016`,
       `rank2021` 
   FROM ES2021 AS e2 LEFT OUTER JOIN ES2016 AS e1 ON e1.lga_code = e2.lga_code AND 
                                                     e1.indigenous_status = e2.indigenous_status AND 
                                                     e1.sex = e2.sex AND
                                                     e1.highest_school_year = e2.highest_school_year;

CREATE VIEW ES2021 AS 
SELECT e.lga_code, 
       e.lga_year, 
       ls.lga_name, 
       ls.state, 
       ls.lga_type, 
       e.indigenous_status, 
       e.sex, 
       e.highest_school_year, 
       e.count, 
       RANK () OVER (PARTITION BY e.indigenous_status, e.sex, e.highest_school_year 
                     ORDER BY e.count DESC) AS 'rank2021'  
   FROM EducationStatistics e JOIN LGA_State ls ON e.lga_code = ls.lga_code AND e.lga_year = ls.lga_year
   WHERE e.lga_year = 2021;

CREATE VIEW ESST22Comparison AS 
SELECT e1.lga_code AS 'code_2016',
       e1.lga_year AS 'year_2016',
       e1.lga_name AS 'name_2016',
       e1.state AS 'state_2016',
       e1.lga_type AS 'type_2016',
       e2.lga_code AS 'code_2021',
       e2.lga_year AS 'year_2021',
       e2.lga_name AS 'name_2021',
       e2.state AS 'state_2021',
       e2.lga_type AS 'type_2021',
       e1.indigenous_status AS 'status_2016',
       e1.sex AS 'sex_2016',
       e1.highest_school_year AS 'hs_year_completed_2016',
       e2.indigenous_status AS 'status_2021',
       e2.sex AS 'sex_2021',
       e2.highest_school_year AS 'hs_year_completed_2021',
       e1.count AS 'result_2016',
       e2.count AS 'result_2021',
       e1.proportion AS 'proportion_2016',
       e2.proportion AS 'proportion_2021',  
       e1.rank AS 'rank_2016',
       e2.rank AS 'rank_2021' 
   FROM ESST22Rank2016 AS e1 LEFT OUTER JOIN ESST22Rank2021 AS e2 ON e1.lga_code = e2.lga_code AND 
                                                       e1.indigenous_status = e2.indigenous_status AND 
                                                       e1.sex = e2.sex AND
                                                       e1.highest_school_year = e2.highest_school_year
                                                     
UNION ALL

SELECT e1.lga_code AS 'code_2016',
       e1.lga_year AS 'year_2016',
       e1.lga_name AS 'name_2016',
       e1.state AS 'state_2016',
       e1.lga_type AS 'type_2016',
       e2.lga_code AS 'code_2021',
       e2.lga_year AS 'year_2021',
       e2.lga_name AS 'name_2021',
       e2.state AS 'state_2021',
       e2.lga_type AS 'type_2021',
       e1.indigenous_status AS 'status_2016',
       e1.sex AS 'sex_2016',
       e1.highest_school_year AS 'hs_year_completed_2016',
       e2.indigenous_status AS 'status_2021',
       e2.sex AS 'sex_2021',
       e2.highest_school_year AS 'hs_year_completed_2021',
       e1.count AS 'result_2016',
       e2.count AS 'result_2021',
       e1.proportion AS 'proportion_2016',
       e2.proportion AS 'proportion_2021',  
       e1.rank AS 'rank_2016',
       e2.rank AS 'rank_2021' 
   FROM ESST22Rank2021 AS e2 LEFT OUTER JOIN ESST22Rank2016 AS e1 ON e1.lga_code = e2.lga_code AND 
                                                     e1.indigenous_status = e2.indigenous_status AND 
                                                     e1.sex = e2.sex AND
                                                     e1.highest_school_year = e2.highest_school_year;   

CREATE VIEW ESST22Proportion AS 
SELECT *, 
       SUM(e.count) OVER (PARTITION BY e.lga_code, e.lga_year, e.sex, e.highest_school_year 
                        ORDER BY e.lga_code, e.lga_year, e.sex, e.highest_school_year) AS 'population_count',
       (((e.count * 1.0) / SUM(e.count) OVER (PARTITION BY e.lga_code, e.lga_year, e.sex, e.highest_school_year 
                                    ORDER BY e.lga_code, e.lga_year, e.sex, e.highest_school_year))) AS 'proportion'
   FROM EducationStatistics e JOIN LGA_State ls ON e.lga_code = ls.lga_code AND 
                                                   e.lga_year = ls.lga_year;

CREATE VIEW ESST22Rank AS 
SELECT *,
        RANK () OVER (PARTITION BY lga_year, indigenous_status, sex, highest_school_year 
                      ORDER BY lga_year, indigenous_status, sex, highest_school_year, proportion DESC) AS 'rank' 
   FROM ESST22Proportion 
   ORDER BY rank ASC;

CREATE VIEW ESST22Rank2016 AS 
SELECT * 
   FROM ESST22Rank 
   WHERE lga_year = 2016;

CREATE VIEW ESST22Rank2021 AS 
SELECT * 
   FROM ESST22Rank 
   WHERE lga_year = 2021;

CREATE VIEW ESST32 AS 
SELECT *, 
       SUM(count) OVER (PARTITION BY lga_code, lga_year, sex, highest_school_year ORDER BY lga_code, lga_year, sex, highest_school_year) AS 'population_count',
       (((count * 1.0) / SUM(count) OVER (PARTITION BY lga_code, lga_year, sex, highest_school_year ORDER BY lga_code, lga_year, sex, highest_school_year)) * 100) AS 'proportion'
   FROM EducationStatistics
   WHERE lga_year = 2021;

CREATE VIEW HS2016 AS 
SELECT h.lga_code,
       h.lga_year,
       ls.lga_name,
       ls.state, 
       ls.lga_type, 
       h.indigenous_status, 
       h.income_bracket, 
       h.count, 
       RANK () OVER (PARTITION BY h.indigenous_status, h.income_bracket 
                     ORDER BY h.count DESC) AS 'rank2016' 
   FROM HouseholdStatistics h JOIN LGA_State ls ON h.lga_code = ls.lga_code AND 
                                                   h.lga_year = ls.lga_year 
   WHERE h.lga_year = 2016;

CREATE VIEW HS2016VS2021 AS 
SELECT h1.lga_code AS '2016 LGA Code',
       h1.lga_year AS '2016 LGA Year',
       h1.lga_name AS '2016 LGA Name',
       h1.state AS '2016 LGA State',
       h1.lga_type AS '2016 LGA Type',
       h2.lga_code AS '2021 LGA Code',
       h2.lga_year AS '2021 LGA Year',
       h2.lga_name AS '2021 LGA Name',
       h2.state AS '2021 LGA State',
       h2.lga_type AS '2021 LGA Type',
       h1.indigenous_status AS '2016 Indigenous Status',
       h1.income_bracket AS '2016 Income Bracket',
       h2.indigenous_status AS '2021 Indigenous Status',
       h2.income_bracket AS '2021 Income Bracket',
       h1.count AS '2016 Result',
       h2.count AS '2021 Result', 
       h1.rank2016 AS '2016 Rank',
       h2.rank2021 AS '2021 Rank' 
    FROM HS2016 AS h1 LEFT OUTER JOIN HS2021 AS h2 ON h1.lga_code = h2.lga_code AND 
                                                      h1.indigenous_status = h2.indigenous_status AND 
                                                      h1.income_bracket = h2.income_bracket
                                                     
UNION ALL

SELECT h1.lga_code AS '2016 LGA Code',
       h1.lga_year AS '2016 LGA Year',
       h1.lga_name AS '2016 LGA Name',
       h1.state AS '2016 LGA State',
       h1.lga_type AS '2016 LGA Type',
       h2.lga_code AS '2021 LGA Code',
       h2.lga_year AS '2021 LGA Year',
       h2.lga_name AS '2021 LGA Name',
       h2.state AS '2021 LGA State',
       h2.lga_type AS '2021 LGA Type',
       h1.indigenous_status AS '2016 Indigenous Status',
       h1.income_bracket AS '2016 Income Bracket',
       h2.indigenous_status AS '2021 Indigenous Status',
       h2.income_bracket AS '2021 Income Bracket',
       h1.count AS '2016 Result',
       h2.count AS '2021 Result',
       h1.rank2016 AS '2016 Rank',
       h2.rank2021 AS '2021 Rank' 
    FROM HS2021 AS h2 LEFT OUTER JOIN HS2016 AS h1 ON h1.lga_code = h2.lga_code AND 
                                                      h1.indigenous_status = h2.indigenous_status AND 
                                                      h1.income_bracket = h2.income_bracket 
    WHERE h1.lga_code IS NULL;

CREATE VIEW HS2021 AS 
SELECT h.lga_code,
       h.lga_year,
       ls.lga_name,
       ls.state, 
       ls.lga_type, 
       h.indigenous_status, 
       h.income_bracket, 
       h.count, 
       RANK () OVER (PARTITION BY h.indigenous_status, h.income_bracket 
                     ORDER BY h.count DESC) AS 'rank2021' 
   FROM HouseholdStatistics h JOIN LGA_State ls ON h.lga_code = ls.lga_code AND 
                                                   h.lga_year = ls.lga_year 
   WHERE h.lga_year = 2021;

CREATE VIEW HSST22Comparison AS 
SELECT h1.lga_code AS 'code_2016',
       h1.lga_year AS 'year_2016',
       h1.lga_name AS 'name_2016',
       h1.state AS 'state_2016',
       h1.lga_type AS 'type_2016',
       h2.lga_code AS 'code_2021',
       h2.lga_year AS 'year_2021',
       h2.lga_name AS 'name_2021',
       h2.state AS 'state_2021',
       h2.lga_type AS 'type_2021',
       h1.indigenous_status AS 'status_2016',
       h1.income_bracket AS 'income_2016',
       h2.indigenous_status AS 'status_2021',
       h2.income_bracket AS 'income_2021',
       h1.count AS 'result_2016',
       h2.count AS 'result_2021',
       h1.proportion AS 'proportion_2016',
       h2.proportion AS 'proportion_2021',  
       h1.rank AS 'rank_2016',
       h2.rank AS 'rank_2021' 
   FROM HSST22Rank2016 AS h1 LEFT OUTER JOIN HSST22Rank2021 AS h2 ON h1.lga_code = h2.lga_code AND 
                                                       h1.indigenous_status = h2.indigenous_status AND 
                                                       h1.income_bracket = h2.income_bracket 
                                                     
UNION ALL

SELECT h1.lga_code AS 'code_2016',
       h1.lga_year AS 'year_2016',
       h1.lga_name AS 'name_2016',
       h1.state AS 'state_2016',
       h1.lga_type AS 'type_2016',
       h2.lga_code AS 'code_2021',
       h2.lga_year AS 'year_2021',
       h2.lga_name AS 'name_2021',
       h2.state AS 'state_2021',
       h2.lga_type AS 'type_2021',
       h1.indigenous_status AS 'status_2016',
       h1.income_bracket AS 'income_2016',
       h2.indigenous_status AS 'status_2021',
       h2.income_bracket AS 'income_2021',
       h1.count AS 'result_2016',
       h2.count AS 'result_2021',
       h1.proportion AS 'proportion_2016',
       h2.proportion AS 'proportion_2021',  
       h1.rank AS 'rank_2016',
       h2.rank AS 'rank_2021' 
   FROM HSST22Rank2021 AS h2 LEFT OUTER JOIN HSST22Rank2016 AS h1 ON h1.lga_code = h2.lga_code AND 
                                                     h1.indigenous_status = h2.indigenous_status AND 
                                                     h1.income_bracket = h2.income_bracket;
                                            
CREATE VIEW HSST22Proportion AS 
SELECT *, 
       SUM(h.count) OVER (PARTITION BY h.lga_code, h.income_bracket 
                        ORDER BY h.lga_code, h.lga_year, h.income_bracket) AS 'population_count',
       (((h.count * 1.0) / SUM(h.count) OVER (PARTITION BY h.lga_code, h.lga_year, h.income_bracket 
                                    ORDER BY h.lga_code, h.lga_year, h.income_bracket))) AS 'proportion'
   FROM HouseholdStatistics h JOIN LGA_State ls ON h.lga_code = ls.lga_code AND 
                                                   h.lga_year = ls.lga_year;

CREATE VIEW HSST22Rank AS 
SELECT *,
        RANK () OVER (PARTITION BY lga_year, indigenous_status, income_bracket  
                      ORDER BY lga_year, indigenous_status, income_bracket, proportion DESC) AS 'rank' 
   FROM HSST22Proportion 
   ORDER BY rank ASC;

CREATE VIEW HSST22Rank2016 AS 
SELECT * 
   FROM HSST22Rank 
   WHERE lga_year = 2016;

CREATE VIEW HSST22Rank2021 AS 
SELECT * 
   FROM HSST22Rank 
   WHERE lga_year = 2021;

CREATE VIEW HSST32 AS 
SELECT *, 
       SUM(count) OVER (PARTITION BY lga_code, lga_year, h.income_bracket ORDER BY lga_code, lga_year, h.income_bracket) AS 'population_count',
       (((count * 1.0) / SUM(count) OVER (PARTITION BY lga_code, lga_year, h.income_bracket ORDER BY lga_code, lga_year, h.income_bracket)) * 100) AS 'proportion'
   FROM HouseholdStatistics h JOIN IncomeBracket i ON h.income_bracket = i.income_bracket 
   WHERE lga_year = 2021;

CREATE VIEW LGA_State AS 
SELECT *
   FROM LGA l JOIN State s ON (l.lga_code LIKE s.lga_starting_number || '_%');

CREATE VIEW LGAS AS 
SELECT DISTINCT(lga_code)
       FROM LGA;

CREATE VIEW LGAS2016 AS 
SELECT lga_code, lga_year, lga_name, state, lga_type, area_sqkm, latitude, longitude
      FROM LGA_State
      WHERE lga_year = 2016;

CREATE VIEW LGAS2016VS2021 AS 
SELECT l16.lga_code AS '2016 Code', l16.lga_year AS '2016 Year', l16.lga_name AS '2016 Name', l16.lga_type AS '2016 Type', 
       l21.lga_code AS '2021 Code', l21.lga_year AS '2021 Year', l21.lga_name AS '2021 Name', l21.lga_type AS '2021 Type'
   FROM LGAS2016 l16 LEFT OUTER JOIN LGAS2021 l21 ON l16.lga_code = l21.lga_code
   
UNION ALL

SELECT l16.lga_code AS '2016 Code', l16.lga_year AS '2016 Year', l16.lga_name AS '2016 Name', l16.lga_name AS '2016 Name', 
       l21.lga_code AS '2021 Code', l21.lga_year AS '2021 Year', l21.lga_name AS '2021 Name', l21.lga_type AS '2021 Type'
   FROM LGAS2021 l21 LEFT OUTER JOIN LGAS2016 l16 ON l21.lga_code = l16.lga_code
   WHERE l16.lga_code IS NULL
   GROUP BY `2016 Code`, `2016 Year`, `2016 Name`, `2016 Type`, `2021 Code`, `2021 Year`, `2021 Name`, `2021 Type`;

CREATE VIEW LGAS2021 AS 
SELECT lga_code, lga_year, lga_name, state, lga_type, area_sqkm, latitude, longitude
      FROM LGA_State
      WHERE lga_year = 2021;

CREATE VIEW LTHCST32 AS 
SELECT *, 
       SUM(count) OVER (PARTITION BY lga_code, sex, condition ORDER BY lga_code, sex, condition) AS 'population_count',
       (((count * 1.0) / SUM(count) OVER (PARTITION BY lga_code, sex, condition ORDER BY lga_code, sex, condition)) * 100) AS 'proportion'
   FROM LTHCStatistics;

CREATE VIEW POPULATIONSTATISTICS2016 AS 
SELECT *
      FROM PopulationStatistics 
      WHERE lga_year = 2016;

CREATE VIEW POPULATIONSTATISTICS2016VS2021 AS 
SELECT p1.lga_code AS '2016 LGA Code',
       p1.lga_year AS '2016 Year',
       p1.indigenous_status AS '2016 Indigenous Status',
       p1.sex AS '2016 Sex',
       p1.age AS '2016 Age',
       p1.count AS '2016 Count',
       p2.lga_code AS '2021 LGA Code',
       p2.lga_year AS '2021 Year',
       p2.indigenous_status AS '2021 Indigenous Status',
       p2.sex AS '2021 Sex',
       p2.age AS '2021 Age',
       p2.count AS '2021 Count'
   FROM POPULATIONSTATISTICS2016 AS p1 LEFT OUTER JOIN POPULATIONSTATISTICS2021 AS p2 ON p1.lga_code = p2.lga_code AND 
                                                                                         p1.indigenous_status = p2.indigenous_status AND 
                                                                                         p1.sex = p2.sex AND
                                                                                         p1.age = p2.age

UNION ALL

SELECT *
   FROM POPULATIONSTATISTICS2021 AS p2 LEFT OUTER JOIN POPULATIONSTATISTICS2016 AS p1 ON p1.lga_code = p2.lga_code AND 
                                                                                 p1.indigenous_status = p2.indigenous_status AND 
                                                                                 p1.sex = p2.sex AND
                                                                                 p1.age = p2.age
   WHERE p1.lga_code IS NULL
   GROUP BY p1.lga_code, p1.indigenous_status, p1.sex, p1.age, p2.lga_code, p2.indigenous_status, p2.sex, p2.age;

CREATE VIEW POPULATIONSTATISTICS2021 AS 
SELECT *
      FROM PopulationStatistics 
      WHERE lga_year = 2021;

CREATE VIEW PS2016 AS 
SELECT p.lga_code, 
       p.lga_year, 
       ls.lga_name, 
       ls.state, 
       ls.lga_type, 
       p.indigenous_status, 
       p.sex, 
       p.age, 
       p.count,
       RANK () OVER (PARTITION BY p.indigenous_status, p.sex, p.age 
                     ORDER BY p.count DESC) AS 'rank2016' 
   FROM PopulationStatistics p JOIN LGA_State ls ON p.lga_code = ls.lga_code AND p.lga_year = ls.lga_year
   WHERE p.lga_year = 2016;

CREATE VIEW PS2016VS2021 AS 
SELECT p1.lga_code AS '2016 LGA Code',
       p1.lga_year AS '2016 LGA Year',
       p1.lga_name AS '2016 LGA Name',
       p1.state AS '2016 LGA State',
       p1.lga_type AS '2016 LGA Type',
       p2.lga_code AS '2021 LGA Code',
       p2.lga_year AS '2021 LGA Year',
       p2.lga_name AS '2021 LGA Name',
       p2.state AS '2021 LGA State',
       p2.lga_type AS '2021 LGA Type',
       p1.indigenous_status AS '2016 Indigenous Status',
       p1.sex AS '2016 Sex',
       p1.age AS '2016 Age',
       p2.indigenous_status AS '2021 Indigenous Status',
       p2.sex AS '2021 Sex',
       p2.age AS '2021 Age',
       p1.count AS '2016 Result',
       p2.count AS '2021 Result',
       `rank2016`,
       `rank2021` 
   FROM PS2016 AS p1 LEFT OUTER JOIN PS2021 AS p2 ON p1.lga_code = p2.lga_code AND 
                                                     p1.indigenous_status = p2.indigenous_status AND 
                                                     p1.sex = p2.sex AND
                                                     p1.age = p2.age
                                                     
UNION ALL

SELECT p1.lga_code AS '2016 LGA Code',
       p1.lga_year AS '2016 LGA Year',
       p1.lga_name AS '2016 LGA Name',
       p1.state AS '2016 LGA State',
       p1.lga_type AS '2016 LGA Type',
       p2.lga_code AS '2021 LGA Code',
       p2.lga_year AS '2021 LGA Year',
       p2.lga_name AS '2021 LGA Name',
       p2.state AS '2021 LGA State',
       p2.lga_type AS '2021 LGA Type',
       p1.indigenous_status AS '2016 Indigenous Status',
       p1.sex AS '2016 Sex',
       p1.age AS '2016 Age',
       p2.indigenous_status AS '2021 Indigenous Status',
       p2.sex AS '2021 Sex',
       p2.age AS '2021 Age',
       p1.count AS '2016 Result',
       p2.count AS '2021 Result',
       `rank2016`,
       `rank2021`
   FROM PS2021 AS p2 LEFT OUTER JOIN PS2016 AS p1 ON p1.lga_code = p2.lga_code AND 
                                                     p1.indigenous_status = p2.indigenous_status AND 
                                                     p1.sex = p2.sex AND
                                                     p1.age = p2.age
   WHERE p1.lga_code IS NULL;

CREATE VIEW PS2021 AS 
SELECT p.lga_code, 
       p.lga_year, 
       ls.lga_name, 
       ls.state, 
       ls.lga_type, 
       p.indigenous_status, 
       p.sex, 
       p.age, 
       p.count,
       RANK () OVER (PARTITION BY p.indigenous_status, p.sex, p.age 
                     ORDER BY p.count DESC) AS 'rank2021' 
   FROM PopulationStatistics p JOIN LGA_State ls ON p.lga_code = ls.lga_code AND p.lga_year = ls.lga_year
   WHERE p.lga_year = 2021;

CREATE VIEW PSST22Comparison AS 
SELECT p1.lga_code AS 'code_2016',
       p1.lga_year AS 'year_2016',
       p1.lga_name AS 'name_2016',
       p1.state AS 'state_2016',
       p1.lga_type AS 'type_2016',
       p2.lga_code AS 'code_2021',
       p2.lga_year AS 'year_2021',
       p2.lga_name AS 'name_2021',
       p2.state AS 'state_2021',
       p2.lga_type AS 'type_2021',
       p1.indigenous_status AS 'status_2016',
       p1.sex AS 'sex_2016',
       p1.age AS 'age_2016',
       p2.indigenous_status AS 'status_2021',
       p2.sex AS 'sex_2021',
       p2.age AS 'age_2021',
       p1.count AS 'result_2016',
       p2.count AS 'result_2021',
       p1.proportion AS 'proportion_2016',
       p2.proportion AS 'proportion_2021',  
       p1.rank AS 'rank_2016',
       p2.rank AS 'rank_2021' 
   FROM PSST22Rank2016 AS p1 LEFT OUTER JOIN PSST22Rank2021 AS p2 ON p1.lga_code = p2.lga_code AND 
                                                       p1.indigenous_status = p2.indigenous_status AND 
                                                       p1.sex = p2.sex AND 
                                                       p1.age = p2.age  
                                                     
UNION ALL

SELECT p1.lga_code AS 'code_2016',
       p1.lga_year AS 'year_2016',
       p1.lga_name AS 'name_2016',
       p1.state AS 'state_2016',
       p1.lga_type AS 'type_2016',
       p2.lga_code AS 'code_2021',
       p2.lga_year AS 'year_2021',
       p2.lga_name AS 'name_2021',
       p2.state AS 'state_2021',
       p2.lga_type AS 'type_2021',
       p1.indigenous_status AS 'status_2016',
       p1.sex AS 'sex_2016',
       p1.age AS 'age_2016',
       p2.indigenous_status AS 'status_2021',
       p2.sex AS 'sex_2021',
       p2.age AS 'age_2021',
       p1.count AS 'result_2016',
       p2.count AS 'result_2021',
       p1.proportion AS 'proportion_2016',
       p2.proportion AS 'proportion_2021',  
       p1.rank AS 'rank_2016',
       p2.rank AS 'rank_2021' 
   FROM PSST22Rank2021 AS p2 LEFT OUTER JOIN PSST22Rank2016 AS p1 ON p1.lga_code = p2.lga_code AND 
                                                     p1.indigenous_status = p2.indigenous_status AND 
                                                     p1.sex = p2.sex AND 
                                                     p1.age = p2.age;

CREATE VIEW PSST22Proportion AS 
SELECT *, 
       SUM(p.count) OVER (PARTITION BY p.lga_code, p.lga_year, p.sex, p.age 
                        ORDER BY p.lga_code, p.lga_year, p.sex, p.age) AS 'population_count',
       (((p.count * 1.0) / SUM(p.count) OVER (PARTITION BY p.lga_code, p.lga_year, p.sex, p.age 
                                    ORDER BY p.lga_code, p.lga_year, p.sex, p.age))) AS 'proportion'
   FROM PopulationStatistics p JOIN LGA_State ls ON p.lga_code = ls.lga_code AND 
                                                   p.lga_year = ls.lga_year;

CREATE VIEW PSST22Rank AS 
SELECT *,
        RANK () OVER (PARTITION BY lga_year, indigenous_status, sex, age  
                      ORDER BY lga_year, indigenous_status, sex, age, proportion DESC) AS 'rank' 
   FROM PSST22Proportion 
   ORDER BY rank ASC;  

CREATE VIEW PSST22Rank2016 AS 
SELECT * 
   FROM PSST22Rank 
   WHERE lga_year = 2016;

CREATE VIEW PSST22Rank2021 AS 
SELECT * 
   FROM PSST22Rank 
   WHERE lga_year = 2021;

CREATE VIEW PSST32 AS 
SELECT *, 
       SUM(count) OVER (PARTITION BY lga_code, lga_year, sex, age ORDER BY lga_code, lga_year, sex, age) AS 'population_count',
       (((count * 1.0) / SUM(count) OVER (PARTITION BY lga_code, lga_year, sex, age ORDER BY lga_code, lga_year, sex, age)) * 100) AS 'proportion'
   FROM PopulationStatistics p JOIN AgeBracket a ON p.age = a.age_bracket 
   WHERE lga_year = 2021;                                                                                                                                                                                                                                                                               