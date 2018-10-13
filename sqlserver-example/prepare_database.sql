-- Create a new database called 'POTHOLE_REPORTS'
USE master;
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = 'POTHOLE_REPORTS'
)
CREATE DATABASE POTHOLE_REPORTS;
GO



USE POTHOLE_REPORTS;
GO

CREATE TABLE REPORTS 
(
    REPORT_ID integer NOT NULL,
    LOCATION geography NOT NULL,
    DATE_REPORTED smalldatetime,
    DATE_OBSERVED smalldatetime,
    REPORTED_BY varchar(20),
    DEPTH double precision,
    DIAMETER double precision,
    PRIMARY KEY (REPORT_ID) 
);
GO

-- Check what tables we have
SELECT * FROM information_schema.tables;
GO

-- Show the columns in the reports table
SELECT * FROM REPORTS;
GO
