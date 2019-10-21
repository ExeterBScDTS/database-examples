# database-examples

## SQL Server example

Install SQL Server if you don't already have it.

The command line tool ```SQLCMD``` can be used to create and access databases using SQL instructions.

Ensure your server will accept TCP connections and you know which port it is using.  See support.webecs.com/kb/a868/how-do-i-configure-sql-server-express-to-allow-remote-tcp-ip-connections-on-port-1433.aspx

You will also need "mixed mode" logins, which can be set during installation.

If you need to enable mixed mode later, this is the fix.

```C:\Windows\system32>
C:\Windows\system32>sqlcmd -S .\SQLEXPRESS,1433
1> EXEC xp_instance_regwrite N'HKEY_LOCAL_MACHINE',N'Software\Microsoft\MSSQLServer\MSSQLServer', N'LoginMode', REG_DWORD,2
2> GO

(0 rows affected)
1>EXIT
```

Use the CTRL-Shift-Enter 'trick' after typing CMD in the Windows search box to open CMD prompt as admin. 

### Using VS Code to create databases and tables

Managing databases can be done with graphical or command line tools.  See <https://docs.microsoft.com/en-us/sql/tools/overview-sql-tools?view=sql-server-ver15> for a list of these.

Using VS code can be convenient if you are also using it for writing and testing the code for your project.

Install the Visual Studio Code extension

<https://docs.microsoft.com/en-gb/sql/visual-studio-code/sql-server-develop-use-vscode?view=sql-server-ver15>

Create a simple SQL script, be sure to give it a name with the extension ```.sql```

```list-databases.sql```

```sql
USE master
SELECT name FROM sys.databases
GO
```

Create a database connection

Ctrl-Shift-P sql:connect

Select Create

For ```hostname\instance``` enter ```localhost```

For ```[Optional] Database to connect``` hit Enter

For ```Authentication type``` select Integrated

For ```[Optional] Enter a name for this profile``` hit Enter (or give it a name, e.g "SQL Server on localhost")

Note.  The above should work if "mixed mode" logins has been enabled and the default port is used.  If you have more than one database you might need to try other options for ```hostname\instance```, such as ```localhost\SQLEXPRESS``` or ```.\SQLEXPRESS```.

## Simple SQL scripting

<https://docs.microsoft.com/en-us/sql/t-sql/lesson-1-creating-database-objects?view=sql-server-ver15>

## Calling database code from Java

Read the instructions for the JDBC driver 

https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-2017

You will probably want to use Maven to fetch and install the driver.  See ```pom.xml```

### Tutorials

<https://www.microsoft.com/en-us/sql-server/developer-get-started>

<https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html>

## Servlet example

This is a very basic example. Accessing the database from a servlet class, or from JSP, is the same as for a Java application.

You must first install the driver file ```mssql-jdbc-7.?.?.jre8.jar``` in ```jetty-distribution/lib/ext```.

Download the driver from here https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-2017


See https://www.eclipse.org/jetty/documentation/9.4.x/jndi-datasource-examples.html for more information.

## NoSQL example

## MongoDB

https://mongodb.github.io/mongo-java-driver/

https://code.visualstudio.com/docs/azure/mongodb

### Learning

There is a free online course https://university.mongodb.com/courses/M101J/about 

Guides on installation, etc. https://docs.mongodb.com/guides/

Simple example https://www.baeldung.com/java-mongodb Repo here https://github.com/eugenp/tutorials/tree/master/persistence-modules/java-mongodb

### Data modelling

https://docs.mongodb.com/manual/core/data-modeling-introduction/

Converting your Java objects into database documents can be a chore.  Take a look at this guide for best practice.

https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i

Working with dates and times www.ntu.edu.sg/home/ehchua/programming/java/DateTimeCalendar.html



