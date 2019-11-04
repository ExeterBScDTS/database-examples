<%@page import="java.sql.Connection,java.sql.DriverManager"%>
<html>
<body>
<h1>JDBC Connection details</h1>
<%
String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=MESSAGES_TESTDB;user=JavaUser;password=java$12345";
        try (Connection con = DriverManager.getConnection(connectionUrl);) {
            out.println("<p>" + con + "<p>");
            con.close();
        }
%>
</body>
</html>
