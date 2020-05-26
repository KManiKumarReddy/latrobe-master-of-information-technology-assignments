set hivevar:studentId=13B81A0579; --Please replace it with your student id
DROP TABLE ${studentId}_twitterdata;

-- Create a table for the input data
CREATE TABLE ${studentId}_twitterdata (
    tokenType STRING, month STRING, count BIGINT, hashtagName STRING)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

-- Load the input data
LOAD DATA LOCAL INPATH 'Input_data/twitter-small.tsv' INTO TABLE ${studentId}_twitterdata;

-- Question 2a
CREATE TABLE ${studentId}_toptweetmonths AS
SELECT month,sum(count) as cumu_tweets
FROM ${studentId}_twitterdata
GROUP BY month
ORDER BY cumu_tweets DESC LIMIT 5;


--Dump the output to file
INSERT OVERWRITE LOCAL DIRECTORY './Task_2a-out/'
ROW FORMAT DELIMITED
    FIELDS TERMINATED BY '\t'
    STORED AS TEXTFILE
    SELECT * FROM ${studentId}_toptweetmonths;
