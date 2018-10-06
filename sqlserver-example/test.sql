-- Create a new database called 'POTHOLE_REPORTS'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = 'POTHOLE_REPORTS'
)
CREATE DATABASE POTHOLE_REPORTS
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

-- This will need to be done from an Admin account -
-- CREATE LOGIN PotholeAdmin WITH PASSWORD = 'p0tHo13Z$1'
-- GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'PotholeAdmin')
BEGIN
    CREATE USER PotholeAdmin FOR LOGIN PotholeAdmin
    EXEC sp_addrolemember N'db_owner', N'PotholeAdmin'
END;
GO