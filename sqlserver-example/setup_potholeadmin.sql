
-- This will need to be done from an Admin account -
USE master;
GO

CREATE LOGIN PotholeAdmin WITH PASSWORD = 'p0tHo13Z$1'
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'PotholeAdmin')
BEGIN
    CREATE USER PotholeAdmin FOR LOGIN PotholeAdmin
    EXEC sp_addrolemember N'db_owner', N'PotholeAdmin'
END;
GO

-- This is required to enable the PotholeAdmin account to login
-- if Window users only were enabled when SQL Server was installed.
-- This works for SQL Server Express.
EXEC xp_instance_regwrite N'HKEY_LOCAL_MACHINE',N'Software\Microsoft\MSSQLServer\MSSQLServer', N'LoginMode', REG_DWORD,2
GO

-- Give permissions to everyone, or could just give them to PotholeAdmin.
GRANT CREATE DATABASE TO PUBLIC;
GO

GRANT CREATE TABLE TO PUBLIC;
GO
