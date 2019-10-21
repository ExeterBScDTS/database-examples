-- This script creates a new database called MESSAGES_TESTDB
-- 
--
USE master;
GO

-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = 'MESSAGES_TESTDB'
)
CREATE DATABASE MESSAGES_TESTDB;
GO

USE MESSAGES_TESTDB;
GO

-- Now create a table for messages
-- You'll get an error message if the table already exists 
CREATE TABLE MESSAGES 
(
    MSG_ID integer IDENTITY(1,1) PRIMARY KEY,
    ITEM_ID integer NOT NULL,
    USER_ID integer NOT NULL,
    REPLY_TO integer NULL,
    DATE_TIME smalldatetime,
    MSG_TXT text
)
GO

-- And create stored procedures
DROP PROCEDURE pr_create_msg
DROP PROCEDURE pr_list_msg
DROP PROCEDURE pr_delete_msg
GO

CREATE PROCEDURE pr_create_msg @item integer, @user integer, @reply integer, @date smalldatetime, @txt text
AS  
    INSERT MESSAGES (ITEM_ID, USER_ID, REPLY_TO, DATE_TIME, MSG_TXT)
    VALUES (@item, @user, @reply, @date, @txt)  
GO 

CREATE PROCEDURE pr_list_msg @item integer 
AS
    SET NOCOUNT ON
    SELECT MSG_ID, ITEM_ID, USER_ID, REPLY_TO, DATE_TIME, MSG_TXT
    FROM MESSAGES WHERE ITEM_ID = @item
GO

CREATE PROCEDURE pr_delete_msg @item integer 
AS
    SET NOCOUNT ON
    DELETE FROM MESSAGES WHERE ITEM_ID = @item


-- Test the create procedure
DECLARE @now smalldatetime = GETDATE()
EXECUTE pr_create_msg 1, 1, NULL, @now, "Hello, world!"

-- Test the list procedure
EXECUTE pr_list_msg @item=1
GO

-- Test the delete procedure
EXECUTE pr_delete_msg @item=1
GO

-- Test the list procedure
EXECUTE pr_list_msg @item=2
GO

-- Test the delete procedure
--EXECUTE pr_delete_msg @item=2
--GO

