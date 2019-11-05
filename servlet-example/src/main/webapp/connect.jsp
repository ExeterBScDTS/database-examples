<%@page import="java.sql.Connection,java.sql.DriverManager"%>
<html>
<body>
<h1>JDBC Connection details</h1>
<%
int i = 0;
out.println("<p>" + i + "</p>");
String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=MESSAGES_TESTDB;user=JavaUser;password=java$12345";
        try (Connection con = DriverManager.getConnection(connectionUrl);) {
            out.println("<p>" + con + "<p>");
            con.close();
        }
%>
<h2>HELLO</h2>
</body>
</html>
