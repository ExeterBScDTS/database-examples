# database-examples

## SQL Server example

Install SQL Server if you don't already have it.

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


Install the Visual Studio Code extension (if you are using VS Code IDE)  https://docs.microsoft.com/en-gb/sql/linux/sql-server-linux-develop-use-vscode?view=sql-server-2017

Read the instructions for the JDBC driver 

https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-2017

You will probably want to use Maven to fetch and install the driver.  See ```pom.xml```

### Tutorial
https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html

## Servlet example

This is a very basic example. Accessing the database from a servlet class, or from JSP, is the same as for a Java application.

You must first install the driver file ```mssql-jdbc-7.0.0.jre8.jar``` in ```jetty-distribution/lib/ext```.

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



