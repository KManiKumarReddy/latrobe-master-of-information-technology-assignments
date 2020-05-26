set hivevar:studentId=13B81A0579; --Please replace it with your student id
DROP TABLE ${studentId}_vgsales;
DROP TABLE ${studentId}_vgcount;

-- Create a table for the input data
CREATE TABLE ${studentId}_vgsales (
    Name STRING, Platform STRING, Year STRING, Genre STRING, Publisher STRING,
    NA_Sales DOUBLE, EU_sales DOUBLE, JP_Sales DOUBLE, Other_Sales DOUBLE)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\;';


-- Load the input data
LOAD DATA LOCAL INPATH 'Input_data/vgsales-small.csv' INTO TABLE ${studentId}_vgsales;

-- Question 1b
CREATE TABLE ${studentId}_vgcount AS
SELECT Platform,Genre,Count(1) as PlatformGenreCount
FROM ${studentId}_vgsales
GROUP BY Platform,Genre
ORDER BY PlatformGenreCount DESC;


--Dump the output to file
INSERT OVERWRITE LOCAL DIRECTORY './Task_1b-out/'
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY '\t'
    STORED AS TEXTFILE
    SELECT * FROM ${studentId}_vgcount;
