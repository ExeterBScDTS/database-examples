-- To run this script in VS Code
-- 1. Connect to database
-- 2. Press CTRL+SHIFT+E
-- See https://docs.microsoft.com/en-us/sql/linux/sql-server-linux-develop-use-vscode?view=sql-server-2017


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

DROP TABLE REPORTS;
GO

CREATE TABLE REPORTS 
(
    REPORT_ID integer IDENTITY(1,1) PRIMARY KEY,
    LOCATION geography NOT NULL,
    DATE_REPORTED smalldatetime,
    DATE_OBSERVED smalldatetime,
    REPORTED_BY varchar(20),
    DEPTH double precision,
    DIAMETER double precision
);
GO

-- Check what tables we have
SELECT * FROM information_schema.tables;
GO

-- Show the columns in the reports table
SELECT * FROM REPORTS;
GO
