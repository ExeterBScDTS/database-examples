
-- This will need to be done from an Admin account.
-- Type CMD in Windows search and then hit Ctrl-Shift-Enter
-- Run script with
-- C:\>SQLCMD -i messages-test-db-account.sql
USE master;
GO

-- Password rules will likely require a 'complicated' and long password
CREATE LOGIN JavaUser WITH PASSWORD = 'java$12345'
GO

-- Set default db for this user.
EXEC sp_defaultdb @loginame='JavaUser', @defdb='MESSAGES_TESTDB'
GO

-- Now make user the owner of the database. For an operational system 
-- take more care with roles granted.
USE MESSAGES_TESTDB;
CREATE USER JavaUser FOR LOGIN JavaUser
EXEC sp_addrolemember N'db_owner', N'JavaUser'
GO

-- This may be required to enable the new account to login
-- if Window users only were enabled when SQL Server was installed.
-- This works for SQL Server Express.
-- EXEC xp_instance_regwrite N'HKEY_LOCAL_MACHINE',N'Software\Microsoft\MSSQLServer\MSSQLServer', N'LoginMode', REG_DWORD,2
-- GO

