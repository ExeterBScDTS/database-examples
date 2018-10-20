<%@page import="java.sql.Connection,java.sql.DriverManager"%>
<html>
<body>
<%
String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=POTHOLE_REPORTS;user=PotholeAdmin;password=p0tHo13Z$1";
        try (Connection con = DriverManager.getConnection(connectionUrl);) {
            out.println(con);
        }
%>
</body>
</html>
