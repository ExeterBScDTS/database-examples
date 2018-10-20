<%@page import="java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement"%>
<html>
<body>
<%
String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=POTHOLE_REPORTS;user=PotholeAdmin;password=p0tHo13Z$1";
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            out.println(con);
        }
%>
</body>
</html>
